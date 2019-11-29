package com.nycjv321.hackathon.plumbing;

import com.nycjv321.hackathon.EyeManager;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class WebDriverWithEyesTests extends WebDriverTests {

    private EyeManager eyeManager;
    protected EyeManager.Instance eyes;
    @BeforeClass(alwaysRun = true)
    public void setupSuite(ITestContext tc) {
        eyeManager = EyeManager.create(tc.getName());
    }

    @BeforeMethod
    public void setup() {
        super.setup();
        eyes = eyeManager.getInstance(getWebDriver());
    }


    @AfterMethod
    public void teardown() {
        super.teardown();
        eyeManager.abort();
    }

    protected void validateElementContent(WebElement element) {
        eyes.elementValidations().validateContent(element);
    }

    protected void validateElementContent(WebElement element, String testName) {
        eyes.elementValidations().validateContent(element, testName);
    }

    protected void validateRegionLayout(WebElement element) {
        eyes.regionValidatons().validateLayout(element);
    }

    protected void validateWindowLayout(String testName) {
        eyes.windowValdations().validateLayout(testName);
    }


}