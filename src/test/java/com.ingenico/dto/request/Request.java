package com.ingenico.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Request {

    public Request() {

    }

    public Request(String currency, Integer amount, Integer merchId, String countryCode, String variant, String locale) {

        AmountOfMoney amountOfMoney = new AmountOfMoney();
        amountOfMoney.setAmount(amount);
        amountOfMoney.setCurrencyCode(currency);

        BillingAddress billingAddress = new BillingAddress();
        billingAddress.setCountryCode(countryCode);

        Customer customer = new Customer();
        customer.setBillingAddress(billingAddress);
        customer.setMerchantCustomerId(merchId);

        order = new Order();
        order.setAmountOfMoney(amountOfMoney);
        order.setCustomer(customer);

        hostedCheckoutSpecificInput = new HostedCheckoutSpecificInput();
        this.hostedCheckoutSpecificInput.setVariant(variant);
        this.hostedCheckoutSpecificInput.setLocale(locale);

    }

    @JsonProperty("order")
    private Order order;
    @JsonProperty("hostedCheckoutSpecificInput")
    private HostedCheckoutSpecificInput hostedCheckoutSpecificInput;

    @JsonProperty("order")
    public Order getOrder() {
        return order;
    }

    @JsonProperty("order")
    public void setOrder(Order order) {
        this.order = order;
    }

    @JsonProperty("hostedCheckoutSpecificInput")
    public HostedCheckoutSpecificInput getHostedCheckoutSpecificInput() {
        return hostedCheckoutSpecificInput;
    }

    @JsonProperty("hostedCheckoutSpecificInput")
    public void setHostedCheckoutSpecificInput(HostedCheckoutSpecificInput hostedCheckoutSpecificInput) {
        this.hostedCheckoutSpecificInput = hostedCheckoutSpecificInput;
    }

    @Override
    public String toString() {
        return "Request{" +
                "order=" + order +
                ", hostedCheckoutSpecificInput=" + hostedCheckoutSpecificInput +
                '}';
    }
}