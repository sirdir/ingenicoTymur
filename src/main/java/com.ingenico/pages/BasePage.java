package com.ingenico.pages;

import com.paulhammant.ngwebdriver.NgWebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected NgWebDriver ngWebDriver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.ngWebDriver = new NgWebDriver((JavascriptExecutor) driver);
        wait = new WebDriverWait(driver, 10);
    }

}
