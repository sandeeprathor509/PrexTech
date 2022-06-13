package com.prex.tech.pagefactory.web;

import com.prex.tech.utilities.CommonUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RewardPage extends CommonUtils {

    WebDriver driver;

    public RewardPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//li[@data-key='rewards']")
    WebElement rewardOption;

    @FindBy(xpath = "//button[@class='ant-btn ant-btn-primary ant-btn-lg']")
    WebElement createNewBtn;

    @FindBy(xpath = "//div[@class='ant-breadcrumb']")
    WebElement rewardCreateText;

    @FindBy(xpath = "//strong[@class='sc-eCYdqJ fIxulT']")
    WebElement rewardInfoTitleText;

    @FindBy(xpath = "(//div[@class='ant-card-head-title'])[1]")
    WebElement rewardDetailsText;

    @FindBy(xpath = "(//div[@class='ant-steps-item-title'])[1]")
    WebElement infoText;

    @FindBy(xpath = "(//div[@class='ant-steps-item-title'])[2]")
    WebElement mechanicText;

    @FindBy(xpath = "(//div[@class='ant-steps-item-title'])[3]")
    WebElement reviewText;

    @FindBy(xpath = "(//button[contains(@class,'ant-btn ant-btn-primary ant-btn-lg')])[2]")
    WebElement nextBtn;

    @FindBy(xpath = "//button[contains(@class,'ant-btn ant-btn-primary ant-btn-lg')]")
    WebElement mechanicNextBtn;

    @FindBy(xpath = "//div[contains(text(),'Rewards must have a name.')]")
    WebElement nameValidationMsg;

    @FindBy(name = "name_en")
    WebElement nameTextBx;

    @FindBy(name = "subtitle_en")
    WebElement subtitleTxtBx;

    @FindBy(xpath = "(//div[@class='DraftEditor-editorContainer'])[1]")
    WebElement descriptionTxtBx;

    @FindBy(xpath = "(//div[@class='DraftEditor-editorContainer'])[2]")
    WebElement stepToRedeemTxtBx;

    @FindBy(xpath = "(//div[@class='DraftEditor-editorContainer'])[3]")
    WebElement tncTxtBx;

    @FindBy(xpath = "(//div[@class='ant-select-selection__rendered'])[1]")
    WebElement brandTxtBx;

    @FindBy(xpath = "(//div[@class='ant-select-selection__rendered'])[2]")
    WebElement tagsTxtBx;

    @FindBy(xpath = "(//div[@class='css-1tn5mlu-control'])[1]")
    WebElement categoriesTxtBx;

    @FindBy(xpath = "(//div[@class='css-1tn5mlu-control'])[2]")
    WebElement labelsTxtBx;

    @FindBy(xpath = "(//div[@class='ant-select-selection__rendered'])[3]")
    WebElement cataloguesTxtBx;

    @FindBy(xpath = "//label[contains(text(),'Start Date')]")
    WebElement startDateTxt;

    @FindBy(xpath = "//label[contains(text(),'End Date')]")
    WebElement endDateTxt;

    @FindBy(xpath = "(//input[contains(@placeholder,'Select date')])[1]")
    WebElement selectStartDate;

    @FindBy(xpath = "(//input[contains(@placeholder,'Select time')])[1]")
    WebElement selectStartTime;

    @FindBy(xpath = "(//span[contains(@class,'ant-time-picker-icon')])[1]")
    WebElement startDateClock;

    @FindBy(xpath = "(//input[contains(@placeholder,'Select date')])[2]")
    WebElement selectEndDate;

    @FindBy(xpath = "(//input[contains(@placeholder,'Select time')])[2]")
    WebElement selectEndTime;

    @FindBy(xpath = "(//span[contains(@class,'ant-time-picker-icon')])[2]")
    WebElement endDateClock;

    @FindBy(xpath = "//label[contains(@class,'Label-sc-6v5l51-0 cgPDTc')]")
    WebElement dateValidationErrMsg;

    @FindBy(xpath = "//button[contains(@class,'ant-btn ant-btn-primary ant-btn-lg')]")
    WebElement saveSubmitBtn;

    @FindBy(xpath = "//label[contains(@class,'ant-radio-button-wrapper')]/span[contains(text(),'ONLINE')]")
    WebElement onlineRedemption;

    @FindBy(xpath = "//input[contains(@data-testid,'reward-list-searchbar')]")
    WebElement searchTxtBox;

    public void clickCreateRewardBtn() throws Exception {
        _click(createNewBtn);
    }

    public List<String> rewardScreenTextVerification() throws Exception {
        return List.of(
                _getText(rewardCreateText),
                _getText(rewardInfoTitleText),
                _getText(rewardDetailsText),
                _getText(infoText),
                _getText(mechanicText),
                _getText(reviewText)
        );
    }

    public void clickInfoNextBtn() throws Exception {
        try {
            if(nextBtn.isDisplayed())
            _click(nextBtn);
        } catch (NoSuchElementException ex) {
            _click(mechanicNextBtn);
        }

    }

    public String nameValidationMsgTxt() throws Exception {
        return _getText(nameValidationMsg);
    }

    public void selectDate(String date) throws Exception {
        _click(By.xpath("//div[contains(text(),'" + date + "')]"));
    }

    public void enterName(String name) throws Exception {
        _sendKeys(nameTextBx, name);
    }

    public String mechanicValidationErrMsg() throws Exception {
        _scrollToElement(onlineRedemption);
        return _getText(dateValidationErrMsg);
    }

    public void clickEndDate() throws Exception {
        _scrollToElement(onlineRedemption);
        _click(selectEndDate);
    }

    public void clickStartDate() throws Exception {
        _scrollToElement(onlineRedemption);
        _click(selectStartDate);
    }

    public void selectTime(String hours, String seconds) throws Exception {
        _click(By.xpath("(//div[contains(@class,'ant-time-picker-panel-select')]//li[contains(text(),'" + hours + "')])[1]"));
        _click(By.xpath("(//div[contains(@class,'ant-time-picker-panel-select')]//li[contains(text(),'" + seconds + "')])[2]"));
    }

    public void selectStartTime() throws Exception {
        _scrollToElement(onlineRedemption);
        _click(selectStartTime);
        _click(startDateClock);
        _click(selectStartTime);
    }

    public void selectEndTime() throws Exception {
        _click(selectEndTime);
        _click(endDateClock);
        _click(selectEndTime);
    }

    public void submitData() throws Exception {
        _click(saveSubmitBtn);
    }

    public void clickRewardOption() throws Exception {
        _isElementVisible(rewardOption);
        _click(rewardOption);
    }

    public void searchForMerchant(String merchant) throws Exception {
        _click(rewardOption);
        _sendKeys(searchTxtBox, merchant);
    }

    public boolean validateTheMerchant(String merchant){
        return _isElementVisible(By.xpath("//a[contains(@href,'/dashboard/p/rewards/show')]/div[contains(text(),'"+merchant+"')]"));
    }
}
