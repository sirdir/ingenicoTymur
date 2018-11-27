package com.ingenico.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public String getApiKeyId() {

        return apiKeyIdText.getText();
    }

    public String getSecretApiKey() {

        return secredApiText.getText();
    }
}
