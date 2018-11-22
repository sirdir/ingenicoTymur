package com.ingenico.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Request {

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