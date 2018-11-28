package com.ingenico.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends BasePage {
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[data-test-selector='sidebar-link-api-keys']")
    private WebElement apiKeysLink;

    @Step
    public ApiKeysPage gotToApiKeys() {
        apiKeysLink.click();

        return PageFactory.initElements(driver, ApiKeysPage.class);
    }
}
