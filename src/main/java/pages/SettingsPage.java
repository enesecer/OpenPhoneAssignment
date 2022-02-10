package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SettingsPage {

    public SettingsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//div[text()='Settings']")
    public WebElement settings;

    @FindBy(xpath = "//input[@style='margin-right: 16px;']")
    public WebElement firstName;

    @FindBy(xpath = "//span[text()='Save']")
    public WebElement saveButton;

    @FindBy(xpath = "//div[text()='Profile updated!']")
    public WebElement profileUpdateText;
}
