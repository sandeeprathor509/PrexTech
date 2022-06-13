package com.prex.tech.stepdefinitions.web;

import com.prex.tech.configs.AppConfig;
import com.prex.tech.configs.WebDriverConfig;
import com.prex.tech.drivers.WebDriverBuilder;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * This class is read only do not modify anything here
 */

@RequiredArgsConstructor
@CucumberContextConfiguration
@ContextConfiguration(classes = {AppConfig.class})
public class DriverHook {

    private final WebDriverBuilder webDriverBuilder;

    private final AppConfig appConfig;

    private final WebDriverConfig webDriverConfig;

    @Autowired(required = false)
    WebDriver driver;

    @Given("Launch the browser")
    public void launchTheBrowser() {
        this.driver = webDriverBuilder.setupDriver(appConfig.getPlatformName());
        webDriverConfig.setDriver(this.driver);
        driver.get(appConfig.getUrl());
    }

    @After(order = 2)
    public void addScreenshotsToReport(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
    }

    @After(order = 0)
    public void killDriver() {
        if (driver != null)
            driver.quit();
    }
}