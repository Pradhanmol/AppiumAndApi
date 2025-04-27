package org.fos.mobile.utils;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class ScreenShotsUtility {

    private AppiumDriver driver;

    public ScreenShotsUtility(AppiumDriver driver) {
        this.driver = driver;
    }

    // Capture Screenshot
    public void captureScreenshot(String filePath) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            File destFile = new File(filePath);
            FileUtils.copyFile(srcFile, destFile); // Requires Apache Commons IO library
            System.out.println("Screenshot saved at: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}