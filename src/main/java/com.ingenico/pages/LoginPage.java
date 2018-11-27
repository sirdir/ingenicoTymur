package com.ingenico.pages;

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

    public DashboardPage loginAs(String email, String password) {
        username.sendKeys(email);
        loginPassword.sendKeys(password);
        loginBtn.click();

        return PageFactory.initElements(driver, DashboardPage.class);
    }
}
