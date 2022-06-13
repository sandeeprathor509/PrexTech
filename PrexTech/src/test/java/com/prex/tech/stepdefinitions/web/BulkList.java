package com.prex.tech.stepdefinitions.web;

import com.prex.tech.configs.AppConfig;
import com.prex.tech.pagefactory.web.BulkListPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

public class BulkList {

    @Autowired
    WebDriver driver;

    BulkListPage bulkListPage;

    @Autowired
    AppConfig appConfig;

    @Then("Validate that bulk list option is visible to the user")
    public void bulkListOptionVisibility() throws Exception {
        bulkListPage = new BulkListPage(driver);
        if(appConfig.getEnv().equalsIgnoreCase("admin")){
            Assert.assertTrue(bulkListPage.bulkOptionVisibility(appConfig.getEnv()),
                    "Bulklist option is not visible please check the user");
        } else {
            Assert.assertFalse(bulkListPage.bulkOptionVisibility(appConfig.getEnv()),
                    "Bulklist option is visible please check the logged in user credentials");
        }
    }

    @And("Click on the bulk list option")
    public void clickBulkListOption() throws Exception {
        bulkListPage = new BulkListPage(driver);
        if(appConfig.getEnv().equalsIgnoreCase("admin")){
            bulkListPage.clickBulkList();
        }
    }

    @Then("Upload a file through upload option")
    public void uploadFile() throws Exception {
        bulkListPage = new BulkListPage(driver);
        if (appConfig.getEnv().equalsIgnoreCase("admin")) {
            String filePath = appConfig.getCurrentWorkingDir() + "/src/test/resources/features/TestData/annual-enterprise-survey-2020-financial-year-provisional-csv.csv";
            bulkListPage.clickUploadOption(filePath);
        }
    }
}
