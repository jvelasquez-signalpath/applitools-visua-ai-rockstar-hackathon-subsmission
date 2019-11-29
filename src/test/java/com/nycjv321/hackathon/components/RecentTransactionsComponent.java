package com.nycjv321.hackathon.components;

import com.nycjv321.hackathon.models.RecentTransaction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class RecentTransactionsComponent extends Document {
    public RecentTransactionsComponent(WebDriver driver) {
        super(driver);
    }

    public List<RecentTransaction> get() {
        List<WebElement> elements = driver.findElements(By.cssSelector("#transactionsTable tbody tr"));
        return elements.stream().sequential().map(element -> {
            List<WebElement> rowElements = element.findElements(By.tagName("td"));
            return new RecentTransaction(rowElements.get(0).getText(), rowElements.get(1).getText(), rowElements.get(2).getText(), rowElements.get(3).getText(), formatMoney(rowElements.get(4).getText()));
        }).collect(Collectors.toList());
    }

    private BigDecimal formatMoney(String money) {
        return new BigDecimal(money
                .replaceAll(",", "")
                .replaceAll("\\+", "")
                .replaceAll("\\s+", "")
                .replaceAll("USD", "").trim());
    }

    public void sortBy(COLUMN column) {
        find(By.cssSelector(String.format("th[id='%s']", column.name().toLowerCase()))).click();
    }

    public WebElement asWebElement() {
        return find(By.id("transactionsTable"));
    }


    public enum COLUMN {
        STATUS, DATE, DESCRIPTION, CATEGORY, AMOUNT
    }

}
