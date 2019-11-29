package com.nycjv321.hackathon;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.selenium.Eyes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EyeManager {

    private final Eyes eyes;
    private final String suiteName;

    private EyeManager(String suiteName) {
        this.suiteName = suiteName;
        eyes = new Eyes();
        eyes.setApiKey(Configuration.getEyesApiKey().orElseThrow(() -> new RuntimeException("Applitools API key not defined!")));
        eyes.setBatch(new BatchInfo(suiteName));
    }

    public void abort() {
        eyes.abortIfNotClosed();
    }

    public static EyeManager create(String suiteName) {
        return new EyeManager(suiteName);
    }

    public EyeManager.Instance getInstance(WebDriver driver) {
        return new Instance(driver, this);
    }

    public static class Instance {

        private final WebDriver driver;
        private final Eyes eyes;
        private final ElementValidations elementValidations;
        private final String suiteName;
        private final WindowValdations windowValdations;
        private RegionValidatons regionValidatons;

        private Instance(WebDriver driver, EyeManager manager) {
            this.driver = driver;
            this.eyes = manager.eyes;
            this.elementValidations = new ElementValidations();
            this.regionValidatons = new RegionValidatons();
            this.windowValdations = new WindowValdations();
            this.suiteName = manager.suiteName;
        }

        public ElementValidations elementValidations() {
            return elementValidations;
        }

        public RegionValidatons regionValidatons() {
            return regionValidatons;
        }

        public WindowValdations windowValdations() {
            return windowValdations;
        }

        public class WindowValdations {
            private void validateWindow(MatchLevel matchLevel, String testName) {
                eyes.setMatchLevel(matchLevel);
                eyes.open(driver, suiteName, testName);
                eyes.checkWindow();
                eyes.close();
            }

            public void validateLayout(String testName) {
                validateWindow(MatchLevel.LAYOUT, testName);
            }
        }


        public class RegionValidatons {
            private void validateElementRegion(MatchLevel matchLevel, WebElement element, String testName) {
                eyes.setMatchLevel(matchLevel);
                eyes.open(driver, suiteName, testName);
                eyes.checkRegion(element);
                eyes.close();
            }

            public void validateLayout(WebElement element) {
                validateElementRegion(MatchLevel.LAYOUT2, element, Reflection.determineTestName());
            }

        }

        public class ElementValidations {
            private void validateElement(MatchLevel matchLevel, WebElement element, String testName) {
                eyes.setMatchLevel(matchLevel);
                eyes.open(driver, suiteName, testName);
                eyes.checkElement(element);
                eyes.close();
            }

            public void validateContent(WebElement element, String testName) {
                this.validateElement(MatchLevel.CONTENT, element, testName);
            }

            public void validateContent(WebElement element) {
                this.validateElement(MatchLevel.CONTENT, element, Reflection.determineTestName());
            }

        }

    }

}
