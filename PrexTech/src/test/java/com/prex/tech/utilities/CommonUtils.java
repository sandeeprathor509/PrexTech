package com.prex.tech.utilities;

import com.prex.tech.configs.AppConfig;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is read only do not modify anything here
 */
@Log4j
public class CommonUtils {

    private final WebDriver driver;

    AppConfig appConfig;

    public CommonUtils(WebDriver driver) {
        this.driver = driver;
        this.appConfig = AppConfig.getBean(AppConfig.class);
    }

    public boolean _isElementVisible(WebElement element) {
        try {
            By eID = _convertElemToBy(element);
            new FluentWait<>(driver)
                    .withTimeout(Duration.ofMinutes(appConfig.getSeleniumTimeout()))
                    .pollingEvery(Duration.ofSeconds(10))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(ElementNotInteractableException.class)
                    .ignoring(ElementClickInterceptedException.class)
                    .until(ExpectedConditions.visibilityOfElementLocated(eID));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }


    public boolean _isElementVisible(By by) {
        try {
            new FluentWait<>(driver)
                    .withTimeout(Duration.ofMinutes(appConfig.getSeleniumTimeout()))
                    .pollingEvery(Duration.ofSeconds(10))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(ElementNotInteractableException.class)
                    .ignoring(ElementClickInterceptedException.class)
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    /**
     * Performs a click operation on a WebElement with explicit wait
     *
     * @param element WebElement
     */
    public void _click(WebElement element) throws Exception {
        By eID = _convertElemToBy(element);
        if (!_isElementVisible(eID))
            throw new RuntimeException("Unable to locate the element even after waiting for " + appConfig.getSeleniumTimeout() + " seconds");
        driver.findElement(eID).click();
    }


    public void _click(By element) {
        if (!_isElementVisible(element))
            throw new RuntimeException("Unable to locate the element even after waiting for " + appConfig.getSeleniumTimeout() + " seconds");
        driver.findElement(element).click();
    }

    /**
     * Explicitly waits for the element to be present and return the text
     *
     * @param element WebElement
     * @return String
     */
    public String _getText(WebElement element) throws Exception {
        By eID = _convertElemToBy(element);
        if (!_isElementVisible(eID))
            throw new RuntimeException("Unable to locate the element even after waiting for " + appConfig.getSeleniumTimeout() + " seconds");
        return driver.findElement(eID).getText();
    }


    /**
     * Performs sendKeys in an element with explicit wait
     *
     * @param element      WebElement
     * @param keysSequence Text to type-in
     */
    public void _sendKeys(WebElement element, String keysSequence) throws Exception {
        By eId = _convertElemToBy(element);
        if (!_isElementVisible(eId))
            throw new RuntimeException("Unable to locate the element even after waiting for " + appConfig.getSeleniumTimeout() + " seconds");

        driver.findElement(eId).clear();
        driver.findElement(eId).sendKeys(keysSequence);
    }

    public String _getCurrentURL(){
        return driver.getCurrentUrl();
    }

    /**
     * Performs a scroll on a web page based on the web element co-ordinates
     *
     * @param element WebElement
     */
    public void _scrollToElement(WebElement element) {
        Coordinates cor = ((Locatable) element).getCoordinates();
        cor.inViewPort();
    }

    public void _waitForPageLoad() {
        if (driver != null) {
            ExpectedCondition<Boolean> expectation = driver -> {
                assert driver != null;
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            };
            try {
                Thread.sleep(10000);
                WebDriverWait wait = new WebDriverWait(driver, appConfig.getSeleniumTimeout());
                wait.until(expectation);
            } catch (Throwable error) {
                Assert.fail("Timeout waiting for Page Load Request to complete.");
            }
        }
    }

    protected By getByElement(String locator, String element) throws Exception {
        switch (locator.toLowerCase()) {
            case "id": {
                return By.id(element);
            }
            case "name": {
                return By.name(element);
            }
            case "classname": {
                return By.className(element);
            }
            case "linktext": {
                return By.linkText(element);
            }
            case "partiallinktext": {
                return By.partialLinkText(element);
            }
            case "cssselector": {
                return By.cssSelector(element);
            }
            case "xpath": {
                return By.xpath(element);
            }
            default: {
                throw new Exception("Unknown locator type '" + locator + "'");
            }
        }
    }

    protected By _convertElemToBy(WebElement element) throws Exception {
        String locator, elem, eleString = element.toString();
        if (eleString.contains("Proxy element for: DefaultElementLocator")) {
            String elemString = eleString.replace("Proxy element for: DefaultElementLocator ", "");
            String[] trimmedText = elemString.substring(1, elemString.length() - 1).split(":");
            locator = trimmedText[0].replace("By.", "").trim();
            elem = trimmedText[1].trim();
        } else if (eleString.contains("Located by By.chained")) {
            Matcher m = Pattern.compile("\\{(.*?)\\}").matcher(eleString);
            String matchedString = "";
            while (m.find()) {
                matchedString = m.group(1);
            }
            String[] trimmedText = matchedString.split(": ");
            locator = trimmedText[0].replace("By.", "").trim();
            elem = trimmedText[1].trim();
        } else if (eleString.contains("Located by By")) {
            String elemString = eleString.replace("Located by By.", "");
            String[] trimmedText = elemString.substring(0, elemString.length() - 1).split(":");
            locator = trimmedText[0].trim();
            elem = trimmedText[1].trim();
        } else {
            String[] trimmedText = (eleString.split("->")[1]).split(":");
            locator = trimmedText[0].trim();
            elem = trimmedText[1].trim().replaceAll(".$", "");
        }
        return this.getByElement(locator, elem);
    }
}
