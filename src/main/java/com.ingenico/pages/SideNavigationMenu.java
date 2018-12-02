package com.ingenico.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SideNavigationMenu extends BasePage {
    public SideNavigationMenu(WebDriver driver) {
        super(driver);
        ngWebDriver.waitForAngularRequestsToFinish();
    }

    @FindBy(css = "[data-test-selector='sidebar-link-api-keys']")
    private WebElement apiKeysLink;

    @FindBy(css = "[data-test-selector='sidebar-link-merchant-ids']")
    private WebElement merchantIdsLink;

    @Step
    public ApiKeysPage openApiKeys() {
        apiKeysLink.click();

        return PageFactory.initElements(driver, ApiKeysPage.class);
    }

    @Step
    public MerchantIdPage openMerchantId() {
        merchantIdsLink.click();

        return PageFactory.initElements(driver, MerchantIdPage.class);
    }
}
