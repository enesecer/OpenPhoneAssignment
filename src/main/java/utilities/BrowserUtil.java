package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Date;

public class BrowserUtil {


    public boolean isClickable(WebElement element, int timeOut) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeOut);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));

        } catch (Exception e) {
            return false;
        }
        return true;

    }

    public static String getScreenshot(String name) {
        String time = new SimpleDateFormat("_yyyy_MM_dd_hh:mm:ss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) Driver.getDriver();
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String target = System.getProperty("user.dir") + "/test-outputFailures/screenshots/" + name + time + ".png";
        File finalDestination = new File(target);

        try {
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException io) {
            io.printStackTrace();
        }

        return target;
    }

    public WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeToWaitInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void wait(int secs) {
        try {
            Thread.sleep(1000 * secs);
            System.out.println("Now waiting for " +secs + " seconds to receive email");
        } catch (InterruptedException e) {
        }
    }


    public String timeStamps() {

        return new SimpleDateFormat("yy.MM.dd.HH.mm").format(new Date());
    }

}
