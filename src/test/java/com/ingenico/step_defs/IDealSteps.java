package com.ingenico.step_defs;

import com.ingenico.dto.request.HostedCheckoutRequest;
import com.ingenico.dto.responce.HostedCheckoutResponse;
import com.ingenico.pages.*;
import com.ingenico.utils.MyAuth;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static com.ingenico.utils.DriverFactory.getDriver;
import static io.restassured.RestAssured.given;

public class IDealSteps implements En {

    private static final Integer MERCHANT_ID = 3024;
    private static final String METHOD = "hostedcheckouts";
    private static final String API_VERSION = "v1";
    private static final String CONTENT_TYPE = "application/json; charset=UTF-8";
    private static final String HTTP_METHOD = "POST";
    private String apiKeyId;
    private String secretApiKey;
    private String endOfPaymentUrl;
    private IDealPage idealPage;
    private DashboardPage dashboardPage;
    private PaymentListPage paymentListPage;

    public IDealSteps() {

        Given("^merchant with email (.*) and password (.*) authorized on sandbox$", (String email, String password) -> {
            getDriver().get("https://account-sandbox.globalcollect.com/#/login");

            LoginPage loginPage = PageFactory.initElements(getDriver(), LoginPage.class);
            dashboardPage = loginPage.loginAs(email, password);
        });

        Given("^merchant has api keys$", () -> {
            ApiKeysPage apiKeysPage = dashboardPage.gotToApiKeys();
            apiKeyId = apiKeysPage.getApiKeyId();
            secretApiKey = apiKeysPage.getSecretApiKey();
        });

        When("^api call done$", (DataTable headersValues) -> {
//        When("^api call done$", (String currency, Integer amount, String countryCode, String variant, String locale) -> {
            List<Map<String,String>> data = headersValues.asMaps(String.class,String.class);
            HostedCheckoutRequest request = new HostedCheckoutRequest(data.get(0).get("currency")
                    , Integer.valueOf(data.get(0).get("amount"))
                    , MERCHANT_ID
                    , data.get(0).get("countryCode")
                    , data.get(0).get("variant")
                    , data.get(0).get("locale"));

            String date = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);

            MyAuth myAuth = new MyAuth(apiKeyId, secretApiKey);

            String signature = myAuth.createAuthSignature(HTTP_METHOD, CONTENT_TYPE, date, "/" + API_VERSION + "/" + MERCHANT_ID + "/" + METHOD); //todo

            String authHeaderValue = "GCS v1HMAC:" + apiKeyId + ":" + signature;

            Response response = given()
                    .log().all()
                    .contentType(CONTENT_TYPE)
                    .header("Authorization", authHeaderValue)
                    .body(request)
                    .pathParam("apiVersion", API_VERSION)
                    .pathParam("merchantId", MERCHANT_ID)
                    .pathParam("method", METHOD)
                    .header("Date", date)
                    .when()
                    .post("{apiVersion}/{merchantId}/{method}");
            response.then().statusCode(201);
            HostedCheckoutResponse hostedCheckoutResponse = response
                    .as(HostedCheckoutResponse.class);
            endOfPaymentUrl = hostedCheckoutResponse.getPartialRedirectUrl();
        });

        When("^user open in browser redirect url$", () -> {
            getDriver().get("https://payment." + endOfPaymentUrl);
            paymentListPage = PageFactory.initElements(getDriver(), PaymentListPage.class);
        });


        When("^user precede with payment via bank (.*)$", (String bankName) -> {
            idealPage = paymentListPage.chooseIDealPayment();
            IDealConfirmationPage iDealConfirmationPage = idealPage.selectBankAndPay(bankName);
            iDealConfirmationPage.confirmTransaction();
        });

        Then("^payment is successful$", () -> {
            String actualText = idealPage.getOperationText();
            String expText = "Your payment is successful.";
            Assert.assertEquals(actualText, expText, "payment result");
        });

    }
}
