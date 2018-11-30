package com.ingenico.dto.responce;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HostedCheckoutResponse {

    @JsonProperty("RETURNMAC")
    private String rETURNMAC;
    @JsonProperty("hostedCheckoutId")
    private String hostedCheckoutId;
    @JsonProperty("partialRedirectUrl")
    private String partialRedirectUrl;

    @JsonProperty("RETURNMAC")
    public String getRETURNMAC() {
        return rETURNMAC;
    }

    @JsonProperty("RETURNMAC")
    public void setRETURNMAC(String rETURNMAC) {
        this.rETURNMAC = rETURNMAC;
    }

    @JsonProperty("hostedCheckoutId")
    public String getHostedCheckoutId() {
        return hostedCheckoutId;
    }

    @JsonProperty("hostedCheckoutId")
    public void setHostedCheckoutId(String hostedCheckoutId) {
        this.hostedCheckoutId = hostedCheckoutId;
    }

    @JsonProperty("partialRedirectUrl")
    public String getPartialRedirectUrl() {
        return partialRedirectUrl;
    }

    @JsonProperty("partialRedirectUrl")
    public void setPartialRedirectUrl(String partialRedirectUrl) {
        this.partialRedirectUrl = partialRedirectUrl;
    }

    @Override
    public String toString() {
        return "HostedCheckoutResponse{" +
                "rETURNMAC='" + rETURNMAC + '\'' +
                ", hostedCheckoutId='" + hostedCheckoutId + '\'' +
                ", partialRedirectUrl='" + partialRedirectUrl + '\'' +
                '}';
    }
}