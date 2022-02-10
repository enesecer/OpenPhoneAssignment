package step_definitions;

import io.cucumber.java.en.Given;
import org.junit.Assert;
import pages.LoginPage;
import pages.SettingsPage;
import utilities.BrowserUtil;
import utilities.ConfigurationReader;
import utilities.Driver;
import utilities.EmailUtility;

public class loginApp {

    LoginPage loginPage = new LoginPage();
    BrowserUtil util = new BrowserUtil();
    SettingsPage settingsPage = new SettingsPage();

    @Given("user logged in application via numeric code")
    public void user_logged_in_application() throws InterruptedException {
        enterUsernameAndPassword(ConfigurationReader.getProperty("loginUser"));
        checkHomePageLanding();
        System.out.println("Application login successful");
    }

    public void enterUsernameAndPassword(String username) throws InterruptedException {
        util.waitForVisibility(loginPage.email, 10);
        if (loginPage.email.isDisplayed() && loginPage.email.isEnabled()) {
            loginPage.email.sendKeys(username);
            loginPage.continueButton.click();
        } else {
            Assert.fail("Login credentials not displayed");
        }
        util.wait(10);
        String password = EmailUtility.check();
        if (password != null) {
            loginPage.numericCode.sendKeys(password);
            loginPage.submit.click();
        } else {
            util.getScreenshot("Failure");
            Assert.fail("Did not receive the verification code");
        }

    }

    public void checkHomePageLanding() {

        if (util.isClickable(settingsPage.settings, 10)) {
            String title = Driver.getDriver().getTitle();
            Assert.assertTrue(title.contains("Primary | OpenPhone"));
        }


    }
}
