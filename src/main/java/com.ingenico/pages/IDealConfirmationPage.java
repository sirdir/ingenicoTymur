package com.ingenico.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class IDealConfirmationPage extends BasePage {
    public IDealConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "form[name='ideal_issuer_sim'] [name='button.edit']")
    private WebElement button;

    @Step
    public IDealPage confirmTransaction() {
        wait.until(elementToBeClickable(button)).click();

        return PageFactory.initElements(driver, IDealPage.class);
    }
}
