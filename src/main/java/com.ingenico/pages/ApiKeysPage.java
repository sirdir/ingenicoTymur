package com.ingenico.pages;

import com.paulhammant.ngwebdriver.ByAngularRepeaterColumn;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class ApiKeysPage extends BasePage {

    public ApiKeysPage(WebDriver driver) {
        super(driver);
        ngWebDriver.waitForAngularRequestsToFinish();
        sideNavigationMenu = PageFactory.initElements(driver, SideNavigationMenu.class);
    }

    public SideNavigationMenu sideNavigationMenu;

    @ByAngularRepeaterColumn.FindBy(repeater = "apikey in GC.keys", exact = true, column = "apikey.apiKeyId")
    private WebElement apiKeyIdText;

    @ByAngularRepeaterColumn.FindBy(repeater = "apikey in GC.keys", exact = true, column = "apikey.secretApiKey")
    private WebElement secredApiText;

    @Step
    public String getApiKeyId() {

        return apiKeyIdText.getText();
    }

    @Step
    public String getSecretApiKey() {

        return secredApiText.getText();
    }
}
