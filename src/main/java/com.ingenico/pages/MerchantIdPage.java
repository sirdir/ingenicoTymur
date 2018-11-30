package com.ingenico.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MerchantIdPage extends BasePage {

    public MerchantIdPage(WebDriver driver) {
        super(driver);
        sideNavigationMenu = PageFactory.initElements(driver, SideNavigationMenu.class);
    }

    public SideNavigationMenu sideNavigationMenu;

    @FindBy(css = "[ng-repeat='merchant in GC.merchants'] > td:nth-child(2)")
    private WebElement merchantIdText;

    @Step
    public Integer getMerchantId() {

        return Integer.valueOf(merchantIdText.getText());
    }
}
