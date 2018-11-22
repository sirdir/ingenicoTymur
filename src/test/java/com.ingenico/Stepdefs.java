package com.ingenico;

import com.ingenico.connect.gateway.sdk.java.Client;
import com.ingenico.connect.gateway.sdk.java.Factory;
import com.ingenico.connect.gateway.sdk.java.domain.definitions.Address;
import com.ingenico.connect.gateway.sdk.java.domain.definitions.AmountOfMoney;
import com.ingenico.connect.gateway.sdk.java.domain.hostedcheckout.CreateHostedCheckoutRequest;
import com.ingenico.connect.gateway.sdk.java.domain.hostedcheckout.CreateHostedCheckoutResponse;
import com.ingenico.connect.gateway.sdk.java.domain.hostedcheckout.definitions.HostedCheckoutSpecificInput;
import com.ingenico.connect.gateway.sdk.java.domain.payment.definitions.Customer;
import com.ingenico.connect.gateway.sdk.java.domain.payment.definitions.Order;
import cucumber.api.java8.En;

import java.net.URI;

public class Stepdefs implements En {
    public Stepdefs() {
        When("^api call$", () -> {
            Client client = Factory.createClient(new URI("file:///C:/Users/sirdir/IdeaProjects/ingenico/src/test/resources/configuration.properties"), "5d5a4a2e3bdaf60f", "LHBG2r7n+gINSphx3GkDGvFfu04Cvya5BXWTFXcsFM8=");

            HostedCheckoutSpecificInput hostedCheckoutSpecificInput = new HostedCheckoutSpecificInput();
            hostedCheckoutSpecificInput.setLocale("en_GB");
            hostedCheckoutSpecificInput.setVariant("100");

            AmountOfMoney amountOfMoney = new AmountOfMoney();
            amountOfMoney.setAmount(2345L);
            amountOfMoney.setCurrencyCode("EUR");

            Address billingAddress = new Address();
            billingAddress.setCountryCode("US");

            Customer customer = new Customer();
            customer.setBillingAddress(billingAddress);
            customer.setMerchantCustomerId("YOURCUSTOMERID");

            Order order = new Order();
            order.setAmountOfMoney(amountOfMoney);
            order.setCustomer(customer);

            CreateHostedCheckoutRequest body = new CreateHostedCheckoutRequest();
            body.setHostedCheckoutSpecificInput(hostedCheckoutSpecificInput);
            body.setOrder(order);

            CreateHostedCheckoutResponse response = client.merchant("3024").hostedcheckouts().create(body);
            System.out.println(response.getPartialRedirectUrl());
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