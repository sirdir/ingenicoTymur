package com.ingenico;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingenico.dto.request.Request;
import com.ingenico.dto.responce.Responce;
import com.ingenico.pages.*;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java8.En;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.parsing.Parser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

public class Stepdefs implements En {

    private String apiKeyId;
    private String secretApiKey;
    private String endOfPaymentUrl;
    private static final String CONTENT_TYPE = "application/json; charset=UTF-8";
    private static final String HTTP_METHOD = "POST";
    private WebDriver driver;
    private IDealPage idealPage;

    @Before
    public void beforeScenario() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        RestAssured.defaultParser = Parser.JSON;
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(
                ObjectMapperConfig.objectMapperConfig().jackson2ObjectMapperFactory((cls, charset) -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
                    objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
                    return objectMapper;
                }));
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }

    @After
    public void afterScenario() {
        driver.quit();
    }


    public Stepdefs() {

        Given("sad children", () -> {

            Thread.sleep(5000);
            driver.get("https://account-sandbox.globalcollect.com/#/login");

            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
            DashboardPage dashboardPage = loginPage.loginAs("kubay.timur@gmail.com", "B%5rkrgb");
            Thread.sleep(5000);
            ApiKeysPage apiKeysPage = dashboardPage.gotToApiKeys();
            Thread.sleep(5000);
            apiKeyId = apiKeysPage.getApiKeyId();
            secretApiKey = apiKeysPage.getSecretApiKey();
        });

        When("^api call$", () -> {
            Request request = new Request("EUR", 100, 3024, "NL", "100", "en_GB");

            String date = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);

            MyAuth myAuth = new MyAuth(apiKeyId, secretApiKey);

            String signature = myAuth.createAuthSignature(HTTP_METHOD, CONTENT_TYPE, date, "/v1/3024/hostedcheckouts");

            String authHeaderValue = "GCS v1HMAC:" + apiKeyId + ":" + signature;

            Responce responce = given()
                    .log().all()
                    .contentType(CONTENT_TYPE)
                    .header("Authorization", authHeaderValue)
                    .body(request)
                    .pathParam("apiVersion", "v1")
                    .pathParam("merchantId", "3024")
                    .header("Date", date)
                    .when()
                    .post("https://eu.sandbox.api-ingenico.com/{apiVersion}/{merchantId}/hostedcheckouts")
                    .as(Responce.class);
            System.out.println(responce.getPartialRedirectUrl());
            endOfPaymentUrl = responce.getPartialRedirectUrl();
        });

        When("open url in browser and precede with payment", () -> {
            driver.get("https://payment." + endOfPaymentUrl);
            PaymentListPage paymentListPage = PageFactory.initElements(driver, PaymentListPage.class);
            idealPage = paymentListPage.chooseIDealPayment();
            Thread.sleep(5000);
            IDealConfirmationPage iDealConfirmationPage = idealPage.selectBankAndPay("Issuer Simulation V3 - ING");
            Thread.sleep(5000);
            iDealConfirmationPage.confirmTransaction();
            Thread.sleep(5000);
            // Write code here that turns the phrase above into concrete actions
        });

        Then("happy children", () -> {
            String actualText = idealPage.getOperationText();
            assert actualText.equals("Your payment is successful.");
            // Write code here that turns the phrase above into concrete actions
        });

    }
}
