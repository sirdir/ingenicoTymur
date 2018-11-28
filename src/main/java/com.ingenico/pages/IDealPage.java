package com.ingenico.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class IDealPage extends BasePage {
    public IDealPage(WebDriver driver) {
        super(driver);
    }

    private WebElement issuerId;
    private WebElement primaryButton;

    @FindBy(css = "#paymentoptionswrapper > p")
    private WebElement operationText;

    @Step
    public IDealConfirmationPage selectBankAndPay(String issuerName) {
        Select selector = new Select(issuerId);
        selector.selectByVisibleText(issuerName);
        primaryButton.click();

        return PageFactory.initElements(driver, IDealConfirmationPage.class);
    }

    @Step
    public String getOperationText() {

        return operationText.getText();
    }
}
