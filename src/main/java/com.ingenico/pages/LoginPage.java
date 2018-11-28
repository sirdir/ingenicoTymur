package com.ingenico.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private WebElement username;
    private WebElement loginPassword;

    @FindBy(id = "kc-login")
    private WebElement loginBtn;

    @Step
    public DashboardPage loginAs(String email, String password) {
        username.sendKeys(email);
        loginPassword.sendKeys(password);
        loginBtn.click();

        return PageFactory.initElements(driver, DashboardPage.class);
    }
}
