package org.fos.mobile.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MobileActionUtils {
    private AppiumDriver driver;

    public MobileActionUtils(AppiumDriver driver) {
        this.driver = driver;
    }

    public void clickElement(WebElement element) {
        element.click();
    }

//    public void waitForElement(WebElement element, int timeoutInSeconds) {
//        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
//        wait.until(ExpectedConditions.visibilityOf(element));
//    }
}
