package com.prex.tech.stepdefinitions.web;

import com.prex.tech.configs.AppConfig;
import com.prex.tech.pagefactory.web.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.http.HttpStatus;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import java.util.List;

public class LoginHook {

    @Autowired
    WebDriver driver;

    @Autowired
    AppConfig appConfig;

    LoginPage loginPage;

    @Then("Enter the username and password")
    public void enterTheUsernameAsAndPasswordAs() throws Exception {
        loginPage = new LoginPage(driver);
        loginPage.login(appConfig.getUsername(), appConfig.getPassword());
    }

    @And("Validate the dashboard URL according to the logged in user")
    public void validateDashboardURL() {
        loginPage = new LoginPage(driver);
        Assert.assertEquals(appConfig.getDashboardURL(), loginPage.currentURL(),
                "Current URL is not matching according the user " + appConfig.getUsername());
    }

    @And("Validate the page section display according to the user")
    public void pageSection() {
        loginPage = new LoginPage(driver);
        List<Boolean> verify = loginPage.pageSection(appConfig.getEnv());
        for (Boolean verification : verify) {
            Assert.assertTrue(verification, "section list not being displayed correctly");
        }
    }

    @And("Validate the site accessibility of the dashboard after altering the api")
    public void siteAccessibility() {
        loginPage = new LoginPage(driver);
        if (appConfig.getEnv().equalsIgnoreCase("admin")) {
            Assert.assertEquals(HttpStatus.SC_OK, loginPage.getCurrentResponse(appConfig.getEnv(), appConfig.baseURI()),
                    "Admins rights are not being properly discharged");
        } else {
            Assert.assertEquals(HttpStatus.SC_FORBIDDEN, loginPage.getCurrentResponse(appConfig.getEnv(), appConfig.baseURI()),
                    "Reward rights are not being properly discharged");
        }
    }

    @And("Login through the API")
    public void loginAPI() {
        loginPage = new LoginPage(driver);
        Assert.assertEquals(HttpStatus.SC_CREATED, loginPage.loginThroughAPI(appConfig.getUsername(), appConfig.getPassword(), appConfig.baseURI()),
                "Login into the application through API is not getting successful");
    }
}
