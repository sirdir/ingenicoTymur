package com.ingenico.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {

    @JsonProperty("amountOfMoney")
    private AmountOfMoney amountOfMoney;
    @JsonProperty("customer")
    private Customer customer;

    @JsonProperty("amountOfMoney")
    public AmountOfMoney getAmountOfMoney() {
        return amountOfMoney;
    }

    @JsonProperty("amountOfMoney")
    public void setAmountOfMoney(AmountOfMoney amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    @JsonProperty("customer")
    public Customer getCustomer() {
        return customer;
    }

    @JsonProperty("customer")
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "amountOfMoney=" + amountOfMoney +
                ", customer=" + customer +
                '}';
    }
}