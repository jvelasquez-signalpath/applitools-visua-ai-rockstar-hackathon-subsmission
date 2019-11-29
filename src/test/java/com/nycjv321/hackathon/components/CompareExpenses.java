package com.nycjv321.hackathon.components;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CompareExpenses extends Document {
    public CompareExpenses(WebDriver driver) {
        super(driver);
    }

    public void test() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        Map<String, Object> response = (Map<String, Object>) js.executeScript("return myBar.data.datasets[0].data;");

        System.out.println(response);
    }


    public Long count() {
        return (Long) executeJavascript("return myBar.data.datasets.length");
    }

    public List<Long> getDataFor(String label) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //arguments[0].click();", button


        for (int i = 0; i < count(); i++) {
            String resolvedLabel = executeJavascript(String.format("return String(myBar.data.datasets[%d].label);", i));
            if (resolvedLabel.equals(label)) {
                return executeJavascript(String.format("return myBar.data.datasets[%d].data;", i));
            }
        }
        return Collections.emptyList();

    }

    public CompareExpenses showDataForNextYear() {
        find(By.id("addDataset")).click();
        return this;
    }

    public WebElement asWebElement() {
        return find(By.id("canvas"));
    }
}
