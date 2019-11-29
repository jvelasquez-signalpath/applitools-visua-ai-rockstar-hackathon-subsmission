package com.nycjv321.hackathon.components;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class Document {

    protected WebDriver driver;

    public Document(WebDriver driver) {
        this.driver = driver;
    }

    protected WebElement find(By by) {
        return driver.findElement(by);
    }
    protected List<WebElement> findMany(By by) {
        return driver.findElements(by);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    protected <T> T executeJavascript(String command) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (T) js.executeScript(command);
    }
}
