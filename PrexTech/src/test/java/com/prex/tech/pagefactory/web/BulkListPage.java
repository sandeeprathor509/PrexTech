package com.prex.tech.pagefactory.web;

import com.prex.tech.utilities.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BulkListPage extends CommonUtils {

    WebDriver driver;

    public BulkListPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//li[@data-key='bulk_actions']")
    WebElement bulkOptions;

    @FindBy(xpath = "//button[contains(@class,'ant-btn ant-btn-primary ant-btn-lg')]")
    WebElement uploadOption;

    @FindBy(xpath = "//input[contains(@type,'file')]")
    WebElement finalUploadOption;

    public boolean bulkOptionVisibility(String user){
        if(user.equalsIgnoreCase("admin")){
            return _isElementVisible(bulkOptions);
        } else {
            return false;
        }
    }

    public void clickBulkList() throws Exception {
        _click(bulkOptions);
    }

    public void clickUploadOption(String filePath) throws Exception {
        _click(uploadOption);
        finalUploadOption.sendKeys(filePath);
    }
}
