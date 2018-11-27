package com.ingenico.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IDealConfirmationPage extends BasePage {
    public IDealConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "form[name='ideal_issuer_sim'] [name='button.edit']")
    private WebElement button;

    public IDealPage confirmTransaction() {
        button.click();

        return PageFactory.initElements(driver, IDealPage.class);
    }
}
