package com.ingenico;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingenico.connect.gateway.sdk.java.Client;
import com.ingenico.connect.gateway.sdk.java.CommunicatorConfiguration;
import com.ingenico.connect.gateway.sdk.java.Factory;
import com.ingenico.connect.gateway.sdk.java.defaultimpl.AuthorizationType;
import com.ingenico.connect.gateway.sdk.java.logging.SysOutCommunicatorLogger;
import com.ingenico.dto.request.*;
import com.ingenico.dto.responce.Responce;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java8.En;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import static io.restassured.RestAssured.given;

public class Stepdefs implements En {

//    String apiKeyId = "5d5a4a2e3bdaf60f"; //todo get from UI
//    String secretApiKey = "LHBG2r7n+gINSphx3GkDGvFfu04Cvya5BXWTFXcsFM8="; //todo get from UI
    String apiKeyId;
    String secretApiKey;
    String endOfPaymentUrl;
    WebDriver driver;

    @Before
    public void beforeScenario() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @After
    public void afterScenario() {
        driver.quit();
    }


    public Stepdefs() {

        Given("sad children", () -> {
            driver.manage().window().maximize();
            Thread.sleep(5000);
            driver.get("https://account-sandbox.globalcollect.com/#/login");
            driver.findElement(By.id("username")).sendKeys("kubay.timur@gmail.com");
            driver.findElement(By.id("loginPassword")).sendKeys("B%5rkrgb");
            driver.findElement(By.id("kc-login")).click();
            Thread.sleep(5000);
            driver.findElement(By.cssSelector("[data-test-selector='sidebar-link-api-keys']")).click();
            Thread.sleep(5000);
            apiKeyId = driver.findElement(By.xpath("//*[@translate='configCenter.general.keyBox.apiKeyId']/parent::td/following-sibling::td/div")).getText();
            secretApiKey = driver.findElement(By.xpath("//*[@translate='configCenter.general.keyBox.privateApiKeyId']/parent::td/following-sibling::td/div")).getText();
//            String keyId = driver.findElement(By.xpath("//*[@translate='configCenter.general.keyBox.apiKeyId']/parent::td/following-sibling::td/div")).getText();
//            String sKey = driver.findElement(By.xpath("//*[@translate='configCenter.general.keyBox.privateApiKeyId']/parent::td/following-sibling::td/div")).getText();
        });

        When("^api call$", () -> {
            CommunicatorConfiguration cc = new CommunicatorConfiguration();
//            cc.setApiEndpoint(new URI("https://eu.sandbox.api-ingenico.com/"));
            cc.setApiEndpoint(new URI("https", null, "eu.sandbox.api-ingenico.com", -1, null, null, null));
            cc.setAuthorizationType(AuthorizationType.V1HMAC);
            cc.setApiKeyId(apiKeyId);
            cc.setSecretApiKey(secretApiKey);
            Client client = Factory.createClient(new URI("file:///home/sirdir/IdeaProjects/ingenicoTymur/src/test/resources/configuration.properties"), "5d5a4a2e3bdaf60f", "LHBG2r7n+gINSphx3GkDGvFfu04Cvya5BXWTFXcsFM8=");

            client.enableLogging(SysOutCommunicatorLogger.INSTANCE);

            Request request = new Request("EUR", 100, 3024, "NL", "100", "en_GB");

//            HostedCheckoutSpecificInput hostedCheckoutSpecificInput = new HostedCheckoutSpecificInput();
//            hostedCheckoutSpecificInput.setLocale("en_GB");
//            hostedCheckoutSpecificInput.setVariant("100");
//
//            AmountOfMoney amountOfMoney = new AmountOfMoney();
//            amountOfMoney.setAmount(100);
//            amountOfMoney.setCurrencyCode("EUR");
//
//            BillingAddress billingAddress = new BillingAddress();
//            billingAddress.setCountryCode("NL");
//
//            Customer customer = new Customer();
//            customer.setBillingAddress(billingAddress);
//            customer.setMerchantCustomerId(3024); //todo may lead issues check if fail with string
//
//            Order order = new Order();
//            order.setAmountOfMoney(amountOfMoney);
//            order.setCustomer(customer);
//
//            request.setHostedCheckoutSpecificInput(hostedCheckoutSpecificInput);
//            request.setOrder(order);

            RestAssured.defaultParser = Parser.JSON;
            RestAssured.config = RestAssuredConfig.config().objectMapperConfig(
                    ObjectMapperConfig.objectMapperConfig().jackson2ObjectMapperFactory((cls, charset) -> {
                        ObjectMapper objectMapper = new ObjectMapper();
                        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
                        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
                        return objectMapper;
                    }));
            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();


            Calendar calendar = Calendar.getInstance(); // pizgeniy cod iz sdk
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US); // pizgeniy cod iz sdk
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT")); // pizgeniy cod iz sdk
            String date =  dateFormat.format(calendar.getTime()); // pizgeniy cod iz sdk

            MyAuth myAuth = new MyAuth(apiKeyId, secretApiKey);
            String dataToSign = myAuth.toDataSignV2("POST", "application/json; charset=UTF-8", date, "/v1/3024/hostedcheckouts");

            String authHeaderValue = "GCS v1HMAC:" + apiKeyId + ":" + myAuth.createAuthenticationSignature(dataToSign);


            Responce responce = given()
                    .log().all()
                    .contentType("application/json")
                    .header("Authorization", authHeaderValue)
                    .body(request)
                    .pathParam("apiVersion", "v1")
                    .pathParam("merchantId", "3024")
//                    .header("Date", LocalDateTime.now()) //Fri, 07 Apr 2017 13:06:36 GMT //todo choose one
                    .header("Date", date) //todo choose one
            .when()
                    .post("https://eu.sandbox.api-ingenico.com/{apiVersion}/{merchantId}/hostedcheckouts")
                    .as(Responce.class);
            System.out.println("xuy " + responce.getPartialRedirectUrl());
            endOfPaymentUrl = responce.getPartialRedirectUrl();

        });

        When("open url in browser and precede with payment", () -> {
            driver.get("https://payment." + endOfPaymentUrl);
            driver.findElement(By.cssSelector("[data-sortablelisttext='iDEAL']>form>button")).click();
            Thread.sleep(5000);
            Select selector = new Select(driver.findElement(By.id("issuerId")));
            selector.selectByVisibleText("Issuer Simulation V3 - ING");
            driver.findElement(By.id("primaryButton")).click();
            Thread.sleep(5000);
            driver.findElement(By.cssSelector("form[name=ideal_issuer_sim] [name='button.edit']")).click();
            Thread.sleep(5000);
            // Write code here that turns the phrase above into concrete actions
        });

        Then("happy children", () -> {
            String actualText = driver.findElement(By.cssSelector("#paymentoptionswrapper > p")).getText();
            assert actualText.equals("Your payment is successful.");
            // Write code here that turns the phrase above into concrete actions
        });

    }
}
