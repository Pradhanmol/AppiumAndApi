package mobile.testCases;

import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fos.mobile.config.MobileBase;
import org.fos.mobile.pageobjects.LoginPage;
import org.fos.mobile.utils.ScreenShotsUtility;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class LoginTest extends MobileBase {
    public static Logger logger;



    @BeforeMethod
    public void setUp() throws MalformedURLException, InterruptedException {
        // Start Appium server
        logger = LogManager.getLogger("FosAutomationProject");
        logger.info("=========App Login Test started========");
        startAppiumServer();
        if (isServerRunning()) {
            logger.info("Server is running");
            System.out.println("Appium server is running!");
        } else {
            System.out.println("Failed to start Appium server.");
            stopAppiumServer();
            throw new RuntimeException("Appium server is not running. Test setup failed.");
        }

        // Start Emulator
        startEmulator("MyLightEmulator");
        logger.info("Emulator started successfully.");
        System.out.println("========================================================");
        System.out.println("Emulator started successfully.");

        // Initialize Appium Driver
        AppiumDriver driver = intializeDriver(); // Driver initialization
        logger.info("Driver started successfully.");
        if (driver == null) {
            throw new RuntimeException("Driver initialization failed.");
        }
        Thread.sleep(30000); // Wait for 30 seconds for the emulator to fully initialize
        System.out.println("Setup completed.");
    }
    @Test
    public void testLoginPageByEmail() {
        try {
            // Initialize LoginPage object
            LoginPage loginPage = new LoginPage(driver);
            logger.info("Login page loaded");
            // Perform initial actions
            loginPage.enterEmail("agentcheck2@yopmail.com");
            logger.info("Email entered");
            takeScreenShot();
            logger.info("Screen shot loaded");
            takeScreenRecording();
            loginPage.enterPassword("User@000");
            logger.info("Password entered");
            takeScreenShot();
            logger.info("Screen shot loaded");
            takeScreenRecording();
            loginPage.clickLoginButton();
            logger.info("Login page clicked");
            takeScreenShot();
            logger.info("Screen shot loaded");
            takeScreenRecording();
            Thread.sleep(3000);

            System.out.println("Login completed.");
            logger.info("Login completed");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @AfterMethod
    public void tearDown() {
        quitDriver();
        System.out.println("Appium driver quit successfully.");
        stopEmulator("MyLightEmulator");
        System.out.println("Emulator stopped successfully.");
        stopAppiumServer();
        System.out.println("Appium server stopped successfully.");
        System.out.println("Test execution completed. Resources cleaned up.");
    }


}