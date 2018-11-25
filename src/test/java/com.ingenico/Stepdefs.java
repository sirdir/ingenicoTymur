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
import cucumber.api.java8.En;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import static io.restassured.RestAssured.given;

public class Stepdefs implements En {



    String apiKeyId = "5d5a4a2e3bdaf60f"; //todo get from UI
    String secretApiKey = "LHBG2r7n+gINSphx3GkDGvFfu04Cvya5BXWTFXcsFM8="; //todo get from UI

    MyAuth myAuth = new MyAuth(apiKeyId, secretApiKey);
    String dataToSign = myAuth.toDataSignV2("POST", "application/json", "Fri, 06 Jun 2014 13:39:43 GMT", "/v1/3024/hostedcheckouts");

    String authHeaderValue = "GCS v1HMAC:" + apiKeyId + ":" + myAuth.createAuthenticationSignature(dataToSign);


    public Stepdefs() {
        When("^api call$", () -> {
            CommunicatorConfiguration cc = new CommunicatorConfiguration();
//            cc.setApiEndpoint(new URI("https://eu.sandbox.api-ingenico.com/"));
            cc.setApiEndpoint(new URI("https", null, "eu.sandbox.api-ingenico.com", -1, null, null, null));
            cc.setAuthorizationType(AuthorizationType.V1HMAC);
            cc.setApiKeyId(apiKeyId);
            cc.setSecretApiKey(secretApiKey);
            Client client = Factory.createClient(new URI("file:///home/sirdir/IdeaProjects/ingenicoTymur/src/test/resources/configuration.properties"), "5d5a4a2e3bdaf60f", "LHBG2r7n+gINSphx3GkDGvFfu04Cvya5BXWTFXcsFM8=");

            client.enableLogging(SysOutCommunicatorLogger.INSTANCE);

            Request request = new Request();

            HostedCheckoutSpecificInput hostedCheckoutSpecificInput = new HostedCheckoutSpecificInput();
            hostedCheckoutSpecificInput.setLocale("en_GB");
            hostedCheckoutSpecificInput.setVariant("100");

            AmountOfMoney amountOfMoney = new AmountOfMoney();
            amountOfMoney.setAmount(100);
            amountOfMoney.setCurrencyCode("EUR");

            BillingAddress billingAddress = new BillingAddress();
            billingAddress.setCountryCode("NL");

            Customer customer = new Customer();
            customer.setBillingAddress(billingAddress);
            customer.setMerchantCustomerId(3024); //todo may lead issues check if fail with string

            Order order = new Order();
            order.setAmountOfMoney(amountOfMoney);
            order.setCustomer(customer);

            request.setHostedCheckoutSpecificInput(hostedCheckoutSpecificInput);
            request.setOrder(order);

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

            Responce responce = given()
                    .contentType(ContentType.JSON)
                    .header("Authorization", authHeaderValue)
                    .body(request)
                    .formParam("apiVersion", "v1")
                    .formParam("merchantId", "3024")
//                    .header("Date", LocalDateTime.now()) //Fri, 07 Apr 2017 13:06:36 GMT //todo choose one
                    .header("Date", date) //todo choose one
            .when()
                    .post("https://eu.sandbox.api-ingenico.com//{apiVersion}/{merchantId}/hostedcheckouts")
                    .as(Responce.class);
            System.out.println(responce.getPartialRedirectUrl());

        });

        Given("sad children", () -> {
            // Write code here that turns the phrase above into concrete actions
        });

        When("open url in browser and precede with payment", () -> {
            // Write code here that turns the phrase above into concrete actions
        });

        Then("happy children", () -> {
            // Write code here that turns the phrase above into concrete actions
        });

    }
}
