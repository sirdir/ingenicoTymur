package com.ingenico;


import com.ingenico.connect.gateway.sdk.java.Client;
import com.ingenico.connect.gateway.sdk.java.CommunicatorConfiguration;
import com.ingenico.connect.gateway.sdk.java.Factory;
import com.ingenico.connect.gateway.sdk.java.defaultimpl.AuthorizationType;
import com.ingenico.connect.gateway.sdk.java.logging.SysOutCommunicatorLogger;
import com.ingenico.dto.request.*;
import com.ingenico.dto.responce.Responce;
import cucumber.api.java8.En;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;

import java.net.URI;
import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;

public class Stepdefs implements En {
    public Stepdefs() {
        When("^api call$", () -> {
            CommunicatorConfiguration cc = new CommunicatorConfiguration();
//            cc.setApiEndpoint(new URI("https://eu.sandbox.api-ingenico.com/"));
            cc.setApiEndpoint(new URI("https", null, "eu.sandbox.api-ingenico.com", -1, null, null, null));
            cc.setAuthorizationType(AuthorizationType.V1HMAC);
            cc.setApiKeyId("5d5a4a2e3bdaf60f"); //todo get from UI
            cc.setSecretApiKey("LHBG2r7n+gINSphx3GkDGvFfu04Cvya5BXWTFXcsFM8="); //todo get from UI
            Client client = Factory.createClient(new URI("file:///home/sirdir/IdeaProjects/ingenicoTymur/src/test/resources/configuration.properties"), "5d5a4a2e3bdaf60f", "LHBG2r7n+gINSphx3GkDGvFfu04Cvya5BXWTFXcsFM8=");

            client.enableLogging(SysOutCommunicatorLogger.INSTANCE);

            String xgcsrId = "1cc6daff-a305-4d7b-94b0-c580fd5ba6b4";
            String xgcsmId = "6480071e-039d-4dca-a966-4ce3c1bc201b";

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

            Responce responce = given()
                    .contentType(ContentType.JSON)
                    .header("Authorization", "GCS v1HMAC:d0ad0559ea4cbe7b:yQzj5jiIVfJgb/5itCCpJqCJPFXVwcZUGMR8yTGkjP0=")
                    .header("X-GCS-RequestId", xgcsrId)
                    .header("X-GCS-MessageId", xgcsmId)
                    .body(request)
                    .header("Date", LocalDateTime.now()) //Fri, 07 Apr 2017 13:06:36 GMT
            .when()
                    .post("https://eu.sandbox.api-ingenico.com/")
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
