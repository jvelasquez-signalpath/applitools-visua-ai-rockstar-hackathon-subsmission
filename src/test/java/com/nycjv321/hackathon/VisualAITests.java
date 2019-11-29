package com.nycjv321.hackathon;

import com.nycjv321.hackathon.models.Credentials;
import com.nycjv321.hackathon.plumbing.WebDriverWithEyesTests;
import com.nycjv321.hackathon.components.CompareExpenses;
import com.nycjv321.hackathon.components.LoginForm;
import com.nycjv321.hackathon.components.RecentTransactionsComponent;
import com.nycjv321.hackathon.pages.Dashboard;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Optional;

import static com.nycjv321.hackathon.Reflection.getTestDescription;
import static com.nycjv321.hackathon.components.RecentTransactionsComponent.COLUMN.AMOUNT;
import static java.util.Optional.empty;

public class VisualAITests extends WebDriverWithEyesTests implements TestData {

    @DataProvider(name = "data-provider")
    static Object[][] dataProviderMethod() {
        return new Object[][]{
                {"test missing password", Credentials.create(Optional.of("foo"), empty())},
                {"test missing username", Credentials.create(empty(), Optional.of("bar"))},
                {"test missing password and email", Credentials.create(empty(), Optional.empty())},
                {"test valid credentiaals", Credentials.create(Optional.of("foo"), Optional.of("bar"))}
        };
    }


    @Test(description = "Login Page UI Elements Test")
    public void elementsTest() {
        WebElement loginForm = LoginForm.create(getWebDriver()).asWebElement();
        validateElementContent(loginForm);
    }

    @Test(dataProvider = "data-provider", description = "Data-Driven Test")
    public void dataDrivenTest(Method method, String testDescription, Credentials credentials) {
        if (credentials.areInvalid()) {
            WebElement loginForm = LoginForm.create(getWebDriver())
                    .using(credentials)
                    .attemptToLogin()
                    .asWebElement();
            validateElementContent(loginForm, String.format("%s: %s", getTestDescription(method).orElse(""), testDescription));
        } else {
            LoginForm.create(getWebDriver())
                    .using(credentials)
                    .login();
            validateWindowLayout(String.format("%s: %s", getTestDescription(method).orElse(""), testDescription));
        }
    }


    @Test(description = "Table Sort Test")
    public void tableSortTest() {
        RecentTransactionsComponent recentTransactions = LoginForm.create(getWebDriver())
                .using(Credentials.create(Optional.of("a"), Optional.of("a")))
                .login()
                .getRecentTransactions();
        recentTransactions.sortBy(AMOUNT);
        validateElementContent(recentTransactions.asWebElement());
    }


    @Test(description = "Canvas Chart Test")
    public void canvasChartTest() {
        CompareExpenses compareExpenses = LoginForm.create(getWebDriver())
                .using(Credentials.create(Optional.of("a"), Optional.of("a")))
                .login().compareExpenses();
        validateElementContent(compareExpenses.asWebElement(), "Canvas Chart Test: 2017 - 2018");
        compareExpenses.showDataForNextYear();
        validateElementContent(compareExpenses.asWebElement(), "Canvas Chart Test: 2017 - 2019");
    }

    @Test(description = "Dynamic Content Test")
    public void adsTest() {
        Dashboard dashboard = LoginForm
                .create(getWebDriver())
                .enableAds()
                .using(Credentials.create(Optional.of("a"), Optional.of("a")))
                .login();
        validateRegionLayout(dashboard.asWebElement());
    }

}