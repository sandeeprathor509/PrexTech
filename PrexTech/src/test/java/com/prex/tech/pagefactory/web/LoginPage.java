package com.prex.tech.pagefactory.web;

import com.prex.tech.utilities.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginPage extends CommonUtils {

    WebDriver driver;

    private static String TOKEN = null;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "email")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(xpath = "//button[contains(span,'Login')]")
    WebElement loginBtn;

    @FindBy(xpath = "//li[@data-key='rewards']")
    WebElement rewardOption;

    @FindBy(xpath = "//li[@data-key='business_intelligence']")
    WebElement dashboardOption;

    @FindBy(xpath = "//li[@data-key='reports']")
    WebElement reportsOption;

    @FindBy(xpath = "//li[@data-key='catalogs']")
    WebElement catalogOption;

    @FindBy(xpath = "//li[@data-key='campaigns']")
    WebElement campaignsOption;

    @FindBy(xpath = "//li[@data-key='loyalties']")
    WebElement loyaltiesOptions;

    @FindBy(xpath = "//li[@data-key='transaction_rules']")
    WebElement rulesOptions;

    @FindBy(xpath = "//li[@data-key='merchants']")
    WebElement merchantsOptions;

    @FindBy(xpath = "//li[@data-key='customer_management']")
    WebElement customerOptions;

    @FindBy(xpath = "//li[@data-key='bulk_actions']")
    WebElement bulkOptions;

    @FindBy(xpath = "//li[@data-key='settings']")
    WebElement settingsOptions;

    public void login(String email, String pwd) throws Exception {
        _isElementVisible(username);
        _sendKeys(username, email);
        _sendKeys(password, pwd);
        _click(loginBtn);
    }

    public String currentURL() {
        _waitForPageLoad();
        return _getCurrentURL();
    }

    public List<Boolean> pageSection(String userRole) {
        if (userRole.equalsIgnoreCase("admin")) {
            return List.of(
                    _isElementVisible(dashboardOption),
                    _isElementVisible(reportsOption),
                    _isElementVisible(rewardOption),
                    _isElementVisible(catalogOption),
                    _isElementVisible(campaignsOption),
                    _isElementVisible(loyaltiesOptions),
                    _isElementVisible(rulesOptions),
                    _isElementVisible(merchantsOptions),
                    _isElementVisible(customerOptions),
                    _isElementVisible(bulkOptions),
                    _isElementVisible(settingsOptions)
            );
        } else {
            return List.of(
                    _isElementVisible(rewardOption)
            );
        }
    }

    public int loginThroughAPI(String username, String password, String URI) {
        Map<String, String> mapValue = new HashMap<>();
        mapValue.put("email", username);
        mapValue.put("password", password);

        RestAssured.baseURI = URI;
        RequestSpecification httpResponse = RestAssured.given();
        httpResponse.contentType("application/json");
        httpResponse.body(mapValue);
        Response response = httpResponse.post("/user_sessions");
        TOKEN = response.jsonPath().get("bearer_token");
        return response.getStatusCode();
    }

    public int getCurrentResponse(String user, String URI) {
        RestAssured.baseURI = URI;
        RequestSpecification httpResponse = RestAssured.given();
        httpResponse.contentType("application/json");
        httpResponse.header("Authorization", "Bearer " + TOKEN);
        Response response;
        if (user.equalsIgnoreCase("admin")) {
            response = httpResponse.get("/rewards");
        } else {
            response = httpResponse.get("/loyalty_programs");
        }
        return response.getStatusCode();
    }
}
