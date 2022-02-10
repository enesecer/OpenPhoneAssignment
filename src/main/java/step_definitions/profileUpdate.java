package step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.SettingsPage;
import utilities.BrowserUtil;

public class profileUpdate {


    SettingsPage settingsPage = new SettingsPage();
    BrowserUtil util = new BrowserUtil();

    @When("user navigates settings page")
    public void user_navigate_settings_page() {
        util.waitForVisibility(settingsPage.settings, 10);
        settingsPage.settings.click();
    }

    @And("user change the first name to {string}")
    public void userChangeTheFirstNameTo(String name) {
        String append = util.timeStamps();
        util.waitForVisibility(settingsPage.firstName, 5);
        if (settingsPage.firstName.isDisplayed()) {
            settingsPage.firstName.clear();
            settingsPage.firstName.sendKeys(name + append);
        }

    }

    @Then("user validate the profile updated notification")
    public void userValidateTheProfileUpdatedNotification() {

        if (util.isClickable(settingsPage.saveButton, 5)) {
            settingsPage.saveButton.click();
        } else {
            Assert.fail("Can't change the first name - Save button disabled");
        }
        String text = settingsPage.profileUpdateText.getText();
        Assert.assertTrue(text.contains("Profile updated!"));
    }

}
