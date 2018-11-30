package com.ingenico.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class ApiKeysPage extends BasePage {

    public ApiKeysPage(WebDriver driver) {
        super(driver);
    }

    //todo think about simplify locator
    @FindBy(xpath = ".//*[@translate='configCenter.general.keyBox.apiKeyId']/parent::td/following-sibling::td/div")
    private WebElement apiKeyIdText;

    //todo think about simplify locator
    @FindBy(xpath = ".//*[@translate='configCenter.general.keyBox.privateApiKeyId']/parent::td/following-sibling::td/div")
    private WebElement secredApiText;

    @Step
    public String getApiKeyId() {
        wait.until(visibilityOf(apiKeyIdText));

        return apiKeyIdText.getText();
    }

    @Step
    public String getSecretApiKey() {

        return secredApiText.getText();
    }
}
