package NuvanoPackage.locators;

import org.openqa.selenium.By;

public class LoginPageLocators {

    // Login Fields
    public static final By EMAIL = By.name("email");
    public static final By PASSWORD = By.xpath("//input[@placeholder='Password']");
    public static final By LOGIN_BUTTON = By.xpath("//span[contains(text(),'Sign In')]");

    // Error Messages
    public static final By EMPTY_EMAIL_ERROR = By.xpath("//div[text()='Email is required']");
    public static final By EMPTY_PASSWORD_ERROR = By.xpath("//div[text()='Password is required']");
    public static final By INVALID_CREDENTIALS_ERROR = By.xpath("//span[text() = 'Invalid credentials']");
    public static final By INVALID_EMAIL_ERROR = By.xpath("//div[text() = 'email must be a valid email']");
    public static final By SHORT_PASSWORD_ERROR = By.xpath("//div[text()= 'Password must be at least 8 characters long']");
    public static final By PASSWORD_FORMAT_ERROR = By.xpath("//div[text()= 'Password must contain at least one uppercase letter and one special character']");

    // Page verification
    public static final By DASHBOARD_HEADER = By.xpath("//h1[text()='Smart Resource Scheduling']");

    //Try Different Email
    public static final By TRY_DIFFERENT_EMAIL = By.xpath("//button[text() = 'Try a different email']");


}

