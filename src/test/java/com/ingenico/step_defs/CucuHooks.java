package com.ingenico.step_defs;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingenico.utils.DriverFactory;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java8.En;
import io.qameta.allure.Attachment;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.parsing.Parser;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static com.ingenico.utils.DriverFactory.*;

public class CucuHooks implements En {

    public CucuHooks() {

        Before(() -> {RestAssured.defaultParser = Parser.JSON;
            RestAssured.baseURI = "https://eu.sandbox.api-ingenico.com/";
            RestAssured.filters(new AllureRestAssured());
            RestAssured.config = RestAssuredConfig.config().objectMapperConfig(
                    ObjectMapperConfig.objectMapperConfig().jackson2ObjectMapperFactory((cls, charset) -> {
                        ObjectMapper objectMapper = new ObjectMapper();
                        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
                        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
                        return objectMapper;
                    }));
            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        });

        AfterStep((Scenario scenario) -> {
            try{
                byte[] screenshot = saveScreenshot(((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES));
                scenario.embed(screenshot, "image/png");
            }
            catch (Exception e) {
                stopDriver();
            }
        });

        After((Scenario scenario) -> stopDriver());

    }

    @Attachment(value = "End of test screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

}
