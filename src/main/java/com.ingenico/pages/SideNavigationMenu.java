package com.ingenico.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class SideNavigationMenu extends BasePage {
    public SideNavigationMenu(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[data-test-selector='sidebar-link-api-keys']")
    private WebElement apiKeysLink;

    @FindBy(css = "[data-test-selector='sidebar-link-merchant-ids']")
    private WebElement merchantIdsLink;

    @Step
    public ApiKeysPage gotToApiKeys() {
        wait.until(elementToBeClickable(apiKeysLink)).click();

        return PageFactory.initElements(driver, ApiKeysPage.class);
    }

    @Step
    public MerchantIdPage gotMerchantId() {
        wait.until(elementToBeClickable(merchantIdsLink)).click();

        return PageFactory.initElements(driver, MerchantIdPage.class);
    }
}
