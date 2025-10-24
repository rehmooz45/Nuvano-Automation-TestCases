package NuvanoPackage;

import Utilities.DriverFactory;
import Utilities.configutilities;
import Utilities.ExcelUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Properties;
 class Nuvanotestrun {

    private WebDriver driver;
    private Login NuvanoSignin;

    private LandingPage NuvanoLanding;
    private Properties prop;

    @Parameters("browser") // browser name will come from TestNG XML
   @BeforeMethod
   public void setup(String browser) {
       driver = DriverFactory.initDriver(browser);
        driver.manage().window().maximize();
        NuvanoSignin = new Login(driver);
        NuvanoLanding = new LandingPage(driver);
        prop = configutilities.getProps("Config");
        NuvanoSignin.openUrl(prop.getProperty("URL"));
        NuvanoLanding.clickGetStarted();
   }


//    public void setup() throws InterruptedException {
//        driver = DriverFactory.initDriver("chrome");
//        NuvanoSignin = new Login(driver);
//        NuvanoSignin.openUrl("URL");
//        driver.manage().window().maximize();
//        NuvanoLanding = new LandingPage(driver);
//        NuvanoLanding.Getstarted();
//
//        // Load Excel file once
////        ExcelUtils.loadExcel("src/test/resources/TestData.xlsx");
//    }

//    @Test(description = "SignAtom with Valid Credentials", priority = 1)
//    public void Login() throws InterruptedException {
//        NuvanoSignin = new Login(driver);
//        NuvanoSignin.openUrl(null);
//        NuvanoLanding = new LandingPage(driver);
//        NuvanoLanding.Getstarted();
//        Properties Prop = configutilities.getProps("Config");
//        NuvanoSignin.Login(Prop.getProperty("USERNAME1"), Prop.getProperty("PASSWORD1"));
//        //****Verification****
//        String text = NuvanoSignin.waitForVisibility(By.xpath("//h1[text() = 'Smart Resource Scheduling']")).getText();
//        System.out.println(text);
//        Assert.assertTrue(text.contains("Smart Resource Scheduling"));
////        Thread.sleep(1000);
////        NuvanoSignin.ChangeAccount();
////        Thread.sleep(2000);
//    }
//}
//@AfterClass
//@AfterMethod
//public void tearDown() {
//    DriverFactory.quitDriver();

     @Test(priority = 1, description = "Login with valid email and valid password")
     public void validLogin() throws InterruptedException {
         NuvanoSignin.enterEmail(prop.getProperty("USERNAME1"));
         NuvanoSignin.enterPassword(prop.getProperty("PASSWORD1"));
         NuvanoSignin.clickLogin();
         Assert.assertTrue(NuvanoSignin.isDashboardDisplayed(), "Smart Resource Scheduling");
     }
     @Test(priority = 2, description = "Login with valid email and invalid password")
     public void invalidPasswordTest() {
         NuvanoSignin.clickDifferentEmail();
         NuvanoSignin.enterEmail(prop.getProperty("USERNAME1"));
         NuvanoSignin.enterPassword(prop.getProperty("PASSWORD3")); // Encrypted invalid password
         NuvanoSignin.clickLogin();
         Assert.assertTrue(NuvanoSignin.isInvalidCredentialErrorDisplayed(), "Invalid credentials");
     }

     @Test(priority = 3, description = "Login with invalid email and valid password")
     public void InvalidEmailTest() {
        NuvanoSignin.clearFields();
        NuvanoSignin.enterEmail(prop.getProperty("USERNAME3"));
         NuvanoSignin.enterPassword(prop.getProperty("PASSWORD1")); // Encrypted invalid password
         NuvanoSignin.clickLogin();
         Assert.assertTrue(NuvanoSignin.isInvalidEmailErrorDisplayed(), "email must be a valid email");
     }
     @Test(priority = 4, description = "Login with invalid email and short password")
     public void InvalidEmailandPasswordTest() {
         NuvanoSignin.clearFields();
         NuvanoSignin.enterEmail(prop.getProperty("USERNAME3"));
         NuvanoSignin.enterPassword(prop.getProperty("PASSWORD4")); // Encrypted invalid password
         NuvanoSignin.clickLogin();
         Assert.assertTrue(NuvanoSignin.isShortPasswordErrorDisplayed(), "Password must be at least 8 characters long") ;
     }
     @Test(priority = 5, description = "Login with empty email and empty password")
     public void EmptyTest() {
         NuvanoSignin.clearFields();
         NuvanoSignin.enterEmail(prop.getProperty("USERNAME6"));
         NuvanoSignin.enterPassword(prop.getProperty("PASSWORD6")); // Encrypted invalid password
         NuvanoSignin.clickLogin();

         Assert.assertTrue(NuvanoSignin.isEmptyEmailErrorDisplayed(), "Email is required") ;
     }

     @AfterMethod
     public void tearDown() {
         DriverFactory.quitDriver();
     }
 }
