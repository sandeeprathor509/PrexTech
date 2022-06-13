package com.prex.tech.stepdefinitions.web;

import com.github.javafaker.Faker;
import com.prex.tech.constants.AppConstants;
import com.prex.tech.pagefactory.web.RewardPage;
import com.prex.tech.utilities.CustomUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import java.util.Map;

public class RewardHook {

    @Autowired
    WebDriver driver;

    @Autowired
    AppConstants appConstants;

    CustomUtils customUtils = new CustomUtils();

    Faker faker = new Faker();

    RewardPage rewardPage;

    @And("User clicks on the create new reward button")
    public void clickCreateNewBtn() throws Exception{
        rewardPage = new RewardPage(driver);
        rewardPage.clickRewardOption();
        rewardPage.clickCreateRewardBtn();
    }

    @Then("Verify the text on the create new reward screen")
    public void verifyRewardScreenText() throws Exception {
        rewardPage = new RewardPage(driver);
        Assert.assertEquals(customUtils.rewardScreenTextVerify(), rewardPage.rewardScreenTextVerification(),
                "Reward screen texts are not matching");
    }

    @And("Click on the next button")
    public void clickNextBtn() throws Exception {
        rewardPage = new RewardPage(driver);
        rewardPage.clickInfoNextBtn();
    }

    @Then("Validate the validation error message for the name")
    public void validateErrMsg() throws Exception {
        rewardPage = new RewardPage(driver);
        Assert.assertEquals(customUtils.validationErrMsg(), rewardPage.nameValidationMsgTxt(),
                "Name validation error message text is not matching");
    }

    @Then("Enter the name in reward details page")
    public void nameInRewardDetailSection() throws Exception {
        rewardPage = new RewardPage(driver);
        rewardPage.enterName(faker.name().firstName());
        appConstants.setName(faker.name().firstName());
    }

    @Then("Validate the date error message on the mechanics page")
    public void mechanicValidationErrorMsg() throws Exception {
        rewardPage = new RewardPage(driver);
        Assert.assertEquals(customUtils.dateValidationErrMsg(), rewardPage.mechanicValidationErrMsg(),
                "Date validation error message is not matching");
    }

    @Then("Fill up the start date and end date")
    public void fillDates(Map<String,String> mapValue) throws Exception {
        rewardPage = new RewardPage(driver);
    }

    @Then("Fill up the start time and end time")
    public void fillTime(Map<String,String> mapValue) throws Exception {
        rewardPage = new RewardPage(driver);
        rewardPage.selectStartTime();
        rewardPage.selectTime(mapValue.get("StartTime"), "00");
        rewardPage.clickStartDate();
        rewardPage.selectDate(mapValue.get("StartDay"));
        rewardPage.selectEndTime();
        rewardPage.selectTime(mapValue.get("EndTime"), "00");
        rewardPage.clickEndDate();
        rewardPage.selectDate(mapValue.get("EndDay"));
    }

    @Then("Submit the reward values")
    public void submitRewardValues() throws Exception {
        rewardPage = new RewardPage(driver);
        rewardPage.submitData();
    }

    @And("Search for the created merchant name in the reward list")
    public void searchForPatient() throws Exception {
        rewardPage = new RewardPage(driver);
        rewardPage.searchForMerchant(appConstants.getName());
        Assert.assertTrue(rewardPage.validateTheMerchant(appConstants.getName()),
                "Created merchant is not displayed");
    }
}
