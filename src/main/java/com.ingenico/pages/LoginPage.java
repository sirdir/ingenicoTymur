package com.ingenico.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "loginPassword")
    private WebElement loginPassword;

    @FindBy(id = "kc-login")
    private WebElement loginBtn;

    @Step
    public SideNavigationMenu loginAs(String email, String password) {
        username.sendKeys(email);
        loginPassword.sendKeys(password);
        loginBtn.click();

        return PageFactory.initElements(driver, SideNavigationMenu.class);
    }
}
