package com.nycjv321.hackathon.components;

import com.nycjv321.hackathon.models.Credentials;
import com.nycjv321.hackathon.pages.Dashboard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Optional;

public class LoginForm extends Document {

    private final Elements elements;

    private LoginForm(WebDriver driver) {
        super(driver);
        this.elements = new Elements();
    }

    public static LoginForm create(WebDriver driver) {
        return new LoginForm(driver);
    }


    public LoginForm using(Credentials credentials) {
        credentials.getPassword().ifPresent(password -> elements.password().sendKeys(password));
        credentials.getUserName().ifPresent(username -> elements.username().sendKeys(username));
        return this;
    }

    public LoginForm attemptToLogin() {
        elements.loginButton().click();
        return this;
    }

    public LoginForm enableAds() {
        driver.navigate().to(driver.getCurrentUrl() + "?showAd=true");
        return this;
    }


    public Dashboard login() {
        elements.loginButton().click();
        return Dashboard.create(driver);
    }


    public Optional<String> getErrorMessage() {
        return Optional.ofNullable(find(By.className("alert-warning")).getText());
    }

    public Elements getElements() {
        return elements;
    }

    public WebElement asWebElement() {
        return getElements().asForm();
    }

    public class Elements {

        public WebElement asForm() {
            return find(By.className("auth-box-w"));
        }

        public WebElement password() {
            return find(By.id("password"));
        }

        public WebElement username() {
            return find(By.id("username"));
        }

        public WebElement loginButton() {
            return find(By.id("log-in"));
        }

        public WebElement rememberMe() {
            return find(By.className("form-check-input"));
        }

        public WebElement header() {
            return find(By.className("auth-header"));
        }

        public WebElement social() {
            return find(By.cssSelector(".buttons-w div[style='text-align:center']"));
        }
    }

}
