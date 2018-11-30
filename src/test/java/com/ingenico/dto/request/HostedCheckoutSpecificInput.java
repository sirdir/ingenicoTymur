package com.ingenico.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HostedCheckoutSpecificInput {

    @JsonProperty("variant")
    private String variant;
    @JsonProperty("locale")
    private String locale;

    @JsonProperty("variant")
    public String getVariant() {
        return variant;
    }

    @JsonProperty("variant")
    public void setVariant(String variant) {
        this.variant = variant;
    }

    @JsonProperty("locale")
    public String getLocale() {
        return locale;
    }

    @JsonProperty("locale")
    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public String toString() {
        return "HostedCheckoutSpecificInput{" +
                "variant='" + variant + '\'' +
                ", locale='" + locale + '\'' +
                '}';
    }
}