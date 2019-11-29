package com.nycjv321.hackathon.plumbing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public abstract class WebDriverTests {

    private WebDriver driver;

    static {
        System.setProperty("webdriver.chrome.driver", WebDriverTests.class.getResource("/chromedriver_mac").getPath());
    }

    private WebDriver createWebDriver() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.navigate().to("https://demo.applitools.com/hackathonV2.html");
//        driver.navigate().to("https://demo.applitools.com/hackathon.html");
        return driver;
    }

    protected WebDriver getWebDriver() {
        return this.driver;
    }


    @BeforeMethod
    public void setup() {
        this.driver = createWebDriver();
    }

    @AfterMethod
    public void teardown() {
        this.driver.quit();
    }

}
