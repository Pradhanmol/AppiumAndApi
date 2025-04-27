package org.fos.mobile.pageobjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class LoginPage {
    private final AppiumDriver driver;

    // Constructor
    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
    }

    // Methods to perform actions
    public void enterEmail(String email) {
        // Find the first EditText field (Email)
        List<WebElement> editTextFields = driver.findElements(By.className("android.widget.EditText"));
        if (editTextFields.isEmpty() || editTextFields.size() < 2) {
            throw new RuntimeException("Email or password fields not found!");
        }
        editTextFields.get(0).sendKeys(email);
    }

    public void enterPassword(String password) {
        // Find the second EditText field (Password)
        List<WebElement> editTextFields = driver.findElements(By.className("android.widget.EditText"));
        if (editTextFields.isEmpty() || editTextFields.size() < 2) {
            throw new RuntimeException("Email or password fields not found!");
        }
        editTextFields.get(1).sendKeys(password);
    }

    public void clickLoginButton() {
        // Find the login button
        WebElement loginButton = driver.findElement(By.className("android.widget.Button"));
        loginButton.click();
    }
}