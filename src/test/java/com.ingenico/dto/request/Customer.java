package com.ingenico.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {

    @JsonProperty("merchantCustomerId")
    private Integer merchantCustomerId;
    @JsonProperty("billingAddress")
    private BillingAddress billingAddress;

    @JsonProperty("merchantCustomerId")
    public Integer getMerchantCustomerId() {
        return merchantCustomerId;
    }

    @JsonProperty("merchantCustomerId")
    public void setMerchantCustomerId(Integer merchantCustomerId) {
        this.merchantCustomerId = merchantCustomerId;
    }

    @JsonProperty("billingAddress")
    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    @JsonProperty("billingAddress")
    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "merchantCustomerId=" + merchantCustomerId +
                ", billingAddress=" + billingAddress +
                '}';
    }
}