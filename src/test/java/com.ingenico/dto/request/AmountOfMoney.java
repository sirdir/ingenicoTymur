package com.ingenico.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AmountOfMoney {

    @JsonProperty("currencyCode")
    private String currencyCode;
    @JsonProperty("amount")
    private Integer amount;

    @JsonProperty("currencyCode")
    public String getCurrencyCode() {
        return currencyCode;
    }

    @JsonProperty("currencyCode")
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @JsonProperty("amount")
    public Integer getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "AmountOfMoney{" +
                "currencyCode='" + currencyCode + '\'' +
                ", amount=" + amount +
                '}';
    }
}