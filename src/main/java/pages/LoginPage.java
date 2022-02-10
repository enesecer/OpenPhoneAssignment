package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class LoginPage {

    public LoginPage() { PageFactory.initElements(Driver.getDriver(), this);}


    @FindBy(xpath = "//input[@placeholder='Email address...']")
    public WebElement email;

    @FindBy(xpath = "//button[contains(@class,'jss29') and @style='margin-top: 12px;']")
    public WebElement continueButton;

    @FindBy(xpath = "//input[@placeholder='6-digit code']")
    public WebElement numericCode;

    @FindBy(xpath = "//button[@style='margin-top: 20px;']/div")
    public WebElement submit;
}
