package org.fos.mobile.config;

import org.openqa.selenium.remote.DesiredCapabilities;

public class CapabilitiesConfig {
    public static DesiredCapabilities getCapabilities() {
        try{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        /*capabilities.setCapability("platformVersion", "11.0");*/
        capabilities.setCapability("appium:deviceName", "MyLightEmulator");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:app", "/Users/admin/Downloads/app-staging-release.apk");
        capabilities.setCapability("appium:ensureWebviewsHavePages", true);
        capabilities.setCapability("appium:nativeWebScreenshot", true);
        capabilities.setCapability("appWaitActivity", "com.credgenics.fos.*");
        capabilities.setCapability("appium:newCommandTimeout", 3600);
        capabilities.setCapability("appium:connectHardwareKeyboard", true);
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("appWaitDuration", 30000); // 30 secondscapabilities.setCapability("ignoreHiddenApiPolicyError", true);capabilities.setCapability("disableWindowAnimation", true);
            return capabilities;
    }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize Appium driver.", e);
        }

    }
}
