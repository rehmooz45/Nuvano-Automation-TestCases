package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory
{
    // Thread-safe WebDriver instance for parallel execution
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static WebDriver initDriver(String browser) {
        WebDriver localDriver;

        if (browser.equalsIgnoreCase("chrome")) {
            localDriver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            localDriver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
                localDriver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Invalid browser: " + browser);
        }

        driver.set(localDriver);
        getDriver().manage().window().maximize();
        return getDriver();
    }
    public static WebDriver getDriver() {
        return driver.get();
}
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}