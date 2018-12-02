package com.ingenico.pages;

import com.paulhammant.ngwebdriver.ByAngularRepeaterColumn;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MerchantIdPage extends BasePage {

    public MerchantIdPage(WebDriver driver) {
        super(driver);
        ngWebDriver.waitForAngularRequestsToFinish();
        sideNavigationMenu = PageFactory.initElements(driver, SideNavigationMenu.class);
    }

    public SideNavigationMenu sideNavigationMenu;


    @ByAngularRepeaterColumn.FindBy(repeater = "merchant in GC.merchants", exact = true, column = "merchant.id")
    private WebElement merchantIdText;

    @Step
    public Integer getMerchantId() {

        return Integer.valueOf(merchantIdText.getText());
    }
}
