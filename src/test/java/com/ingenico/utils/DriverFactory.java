package com.ingenico.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {

        if (driver != null) {
            return driver;
        }

        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.addArguments("window-size=1920,1080");
        if ("true".equals(System.getProperty("headless"))) {
            chromeOptions.setHeadless(true);
        }

        driver = new ChromeDriver(chromeOptions);

        return driver;
    }

    public static void stopDriver() {
        try {
            driver.quit();
        }
        finally {
            driver = null;
        }
    }

}
