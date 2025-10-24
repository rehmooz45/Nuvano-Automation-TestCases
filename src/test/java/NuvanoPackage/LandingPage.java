package NuvanoPackage;

import org.openqa.selenium.WebDriver;
import NuvanoPackage.locators.LandingPageLocators;
public class LandingPage extends Base{
   //landing page locators

    public LandingPage(WebDriver driver)
    {
        super(driver);
    }

    public void clickGetStarted(){

        waitForClickable(LandingPageLocators.GET_STARTED).click();
    }
    public void clickSignIn(){
        waitForClickable(LandingPageLocators.SIGN_IN).click();
    }

    public void clickSignUp(){
        waitForClickable(LandingPageLocators.SIGN_UP).click();
    }
}

