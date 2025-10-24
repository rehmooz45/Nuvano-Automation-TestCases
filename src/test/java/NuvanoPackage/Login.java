package NuvanoPackage;

import Utilities.configutilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import NuvanoPackage.locators.LoginPageLocators;

public class Login extends Base {

    public Login(WebDriver driver) {
        super(driver);
    }

    // ✅ Clears both Email & Password fields before each test
    public void clearFields() {
        WebElement emailField = waitForVisibility(LoginPageLocators.EMAIL);
        WebElement passwordField = waitForVisibility(LoginPageLocators.PASSWORD);
        emailField.clear();
        passwordField.clear();
    }

    // ✅ Main login method with encrypted password handling
    public void Login(String username, String encryptedPassword) {
        String decryptedPassword = configutilities.decryptPassword(encryptedPassword);

        WebElement emailField = waitForVisibility(LoginPageLocators.EMAIL);
        emailField.clear();
        emailField.sendKeys(username);

        WebElement passwordField = waitForVisibility(LoginPageLocators.PASSWORD);
        passwordField.clear();
        passwordField.sendKeys(decryptedPassword);

        waitForClickable(LoginPageLocators.LOGIN_BUTTON).click();
    }

    // ✅ Enter email only
    public void enterEmail(String email) {
        WebElement emailField = waitForVisibility(LoginPageLocators.EMAIL);
        emailField.clear();
        emailField.sendKeys(email);
    }

    // ✅ Enter password only (decrypts before sending)
    public void enterPassword(String encryptedPassword) {
        WebElement passwordField = waitForVisibility(LoginPageLocators.PASSWORD);
        passwordField.clear();
        String decryptedPassword = configutilities.decryptPassword(encryptedPassword);
        passwordField.sendKeys(decryptedPassword);
    }

    // ✅ Click login button
    public void clickLogin() {
        waitForClickable(LoginPageLocators.LOGIN_BUTTON).click();
    }

    // ✅ Click “Try a different email” button safely
    public boolean clickDifferentEmail() {
        try {
            WebElement btn = waitForClickableIfVisible(LoginPageLocators.TRY_DIFFERENT_EMAIL);
            if (btn != null) {
                btn.click();
                return true;
            }
        } catch (Exception ignored) {}
        return false;
    }

    // ✅ Check if Dashboard is displayed
    public boolean isDashboardDisplayed() {
        return isElementVisible2(LoginPageLocators.DASHBOARD_HEADER);
    }

    // ✅ Error message verifications
    public boolean isInvalidCredentialErrorDisplayed() {
        return isElementVisible2(LoginPageLocators.INVALID_CREDENTIALS_ERROR);
    }

    public boolean isEmptyEmailErrorDisplayed() {
        return isElementVisible2(LoginPageLocators.EMPTY_EMAIL_ERROR);
    }

    public boolean isEmptyPasswordErrorDisplayed() {
        return isElementVisible2(LoginPageLocators.EMPTY_PASSWORD_ERROR);
    }

    public boolean isInvalidEmailErrorDisplayed() {
        return isElementVisible2(LoginPageLocators.INVALID_EMAIL_ERROR);
    }

    public boolean isShortPasswordErrorDisplayed() {
        return isElementVisible2(LoginPageLocators.SHORT_PASSWORD_ERROR);
    }

    public boolean isLongPasswordErrorDisplayed() {
        return isElementVisible2(LoginPageLocators.PASSWORD_FORMAT_ERROR);
    }

    // ✅ Generic reusable method for safe element visibility
    private boolean isElementVisible2(By locator) {
        try {
            return waitForVisibilityLong(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ Optional utility: wait for clickable if visible
    public WebElement waitForClickableIfVisible(By locator) {
        try {
            WebElement element = waitForVisibility(locator);
            return element.isDisplayed() ? waitForClickable(locator) : null;
        } catch (Exception e) {
            return null;
        }
    }
}
