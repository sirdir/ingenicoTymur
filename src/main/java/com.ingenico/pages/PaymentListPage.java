package com.ingenico.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentListPage extends BasePage {

    public PaymentListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[data-sortablelisttext='iDEAL']>form>button")
    private WebElement idealBtn;

    @Step
    public IDealPage chooseIDealPayment() {
        idealBtn.click();

        return PageFactory.initElements(driver, IDealPage.class);
    }
}
