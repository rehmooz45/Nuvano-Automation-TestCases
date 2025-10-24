package NuvanoPackage;

import Utilities.DriverFactory;
import Utilities.configutilities;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.time.Duration;
import java.util.Properties;

public class Base {
    protected WebDriver driver;
    protected WebDriverWait defaultWait;
    protected WebDriverWait maxWait;
    protected Properties Prop;


    public Base(WebDriver driver) {
        this.driver = driver;
        this.defaultWait = new WebDriverWait(driver, Duration.ofSeconds(10)); // default = 10s
        this.maxWait = new WebDriverWait(driver, Duration.ofSeconds(30));     // max = 30s
        this.Prop = configutilities.getProps("Config");

    }

    public void openUrl(String url) {
        if (url == null || url.isEmpty()) {
            driver.get(Prop.getProperty("URL"));
        } else {
            driver.get("https://d3lk7kpaz67l3s.cloudfront.net/");
        }
    }

    public void RefreshPage() {

        driver.navigate().refresh();
    }


    // Default Waits
    public WebElement waitForVisibility(By locator) {
        return defaultWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickable(By locator) {
        return defaultWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean waitForTextPresent(By locator, String text) {
        return defaultWait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public void waitForUrlContains(String fraction) {
        defaultWait.until(ExpectedConditions.urlContains(fraction));
    }

    //  Max Waits (use only for slow elements/pages)
    public WebElement waitForVisibilityLong(By locator) {
        return maxWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickableLong(By locator) {
        return maxWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // ✅ New Method: Conditional Wait (for optional or flaky elements)
    public WebElement waitForClickableIfVisible(By locator) {
        try {
            return defaultWait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            System.out.println("Element not visible/clickable within timeout: " + locator);
            return null;
        }
    }

    // ✅ New Method: Check if element is visible without throwing exception
    public boolean isElementVisible(By locator) {
        try {
            defaultWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }

    }
    public WebDriver getDriver() {
        return driver;
    }


}



