package com.nycjv321.hackathon;

import com.nycjv321.hackathon.models.Credentials;
import com.nycjv321.hackathon.models.RecentTransaction;
import com.nycjv321.hackathon.plumbing.WebDriverTests;
import com.nycjv321.hackathon.components.CompareExpenses;
import com.nycjv321.hackathon.components.LoginForm;
import com.nycjv321.hackathon.components.RecentTransactionsComponent;
import com.nycjv321.hackathon.pages.Dashboard;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static com.nycjv321.hackathon.components.RecentTransactionsComponent.COLUMN.AMOUNT;
import static java.util.Optional.empty;
import static org.testng.Assert.*;

public class TraditionalTests extends WebDriverTests implements TestData {

    @DataProvider(name = "data-provider")
    static Object[][] dataProviderMethod() {
        return new Object[][]{
                {Credentials.create(Optional.of("foo"), empty()), Optional.of("Password must be present")},
                {Credentials.create(empty(), Optional.of("bar")), Optional.of("Username must be present")},
                {Credentials.create(empty(), Optional.empty()), Optional.of("Both Username and Password must be present")},
                {Credentials.create(Optional.of("foo"), Optional.of("bar")), Optional.empty()}
        };
    }

    @Test(description = "Login Page UI Elements Test")
    public void elementsTest() {
        LoginForm loginForm = LoginForm.create(getWebDriver());
        assertTrue(loginForm.getElements().password().isDisplayed());
        assertTrue(loginForm.getElements().username().isDisplayed());
        assertTrue(loginForm.getElements().loginButton().isDisplayed());
        assertTrue(loginForm.getElements().rememberMe().isDisplayed());
        assertTrue(loginForm.getElements().header().isDisplayed());
        assertTrue(loginForm.getElements().social().isDisplayed());
    }

    @Test(dataProvider = "data-provider", description = "Data-Driven Test")
    public void dataDrivenTest(Credentials credentials, Optional<String> errorMessage) {
        if (credentials.areInvalid()) {
            assertEquals(LoginForm.create(getWebDriver())
                    .using(credentials)
                    .attemptToLogin().getErrorMessage(), errorMessage);
        } else {
            Dashboard dashboard = LoginForm.create(getWebDriver())
                    .using(credentials)
                    .login();
            assertEquals(dashboard.getTitle(), "ACME demo app");
        }
    }

    @Test(description = "Table Sort Test")
    public void tableSortTest() {
        Dashboard dashboard = LoginForm.create(getWebDriver())
                .using(Credentials.create(Optional.of("a"), Optional.of("a")))
                .login();

        RecentTransactionsComponent recentTransactionsComponent = dashboard.getRecentTransactions();
        List<RecentTransaction> sortedRecentTransactions = recentTransactionsComponent.get();
        sortedRecentTransactions.sort(Comparator.comparing(RecentTransaction::getAmount));

        assertNotEquals(recentTransactionsComponent.get(), sortedRecentTransactions);
        recentTransactionsComponent.sortBy(AMOUNT);
        List<RecentTransaction> currentRecentTransactions = recentTransactionsComponent.get();
        assertEquals(sortedRecentTransactions, currentRecentTransactions);
    }

    @Test
    public void canvasChartTest() throws InterruptedException {
        CompareExpenses compareExpenses = LoginForm.create(getWebDriver())
                .using(Credentials.create(Optional.of("a"), Optional.of("a")))
                .login().compareExpenses();

        // we ensure there are only 2 presented datasets
        assertEquals(compareExpenses.count(), new Long(2));

        assertEquals(compareExpenses.getDataFor(dataSetFor2017.getLabel()), dataSetFor2017.getData());
        assertEquals(compareExpenses.getDataFor(dataSetFor2018.getLabel()), dataSetFor2018.getData());

        // let's add a new dataset (2019)
        compareExpenses.showDataForNextYear();
        // this is used to let the Javascript to finish
        // normally hardcoded sleeps are bad
        Thread.sleep(5000);
        assertEquals(compareExpenses.count(), new Long(3));
        assertEquals(compareExpenses.getDataFor(dataSetFor2019.getLabel()), dataSetFor2019.getData());

    }

    @Test
     public void adsTest() {
        Dashboard dashboard = LoginForm
                .create(getWebDriver())
                .enableAds()
                .using(Credentials.create(Optional.of("a"), Optional.of("a")))
                .login();

        assertTrue(dashboard.getAdOne().isDisplayed());
        assertTrue(dashboard.getAdTwo().isDisplayed());
    }

}

