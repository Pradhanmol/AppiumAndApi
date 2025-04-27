package org.fos.mobile.config;

import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.fos.mobile.utils.ScreenRecorder;
import org.fos.mobile.utils.ScreenShotsUtility;
import org.openqa.selenium.WebElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.ElementType;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MobileBase {
    public AppiumDriver driver;
    private AppiumServerManager serverManager;
    private String deviceName;
    private ScreenShotsUtility screenShotsUtility;
    private ScreenRecorder screenRecorder;
    String timestamp = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
    String screenShotName = "App-" + timestamp + ".jpeg";
    String screenRecordName = "App-recording" + timestamp + ".mp4";
    private final String filePathofScreenShot = System.getProperty("user.dir") + "//Screenshots//"+screenShotName;
    private final String filePathofScreenRecording = System.getProperty("user.dir") + "//recordings//"+screenRecordName;

/*    public void MobileBase(){
        //1. start the appium server
        //2. start the avd on systeme
        //3. connect both through capablities
        startAppiumServer();
        startEmulator(deviceName);
        intializeDriver();
    }*/
    public AppiumDriver intializeDriver(){
        try{
            MobileBase.setupAndroidEnvironment();
            DesiredCapabilities capabilities = CapabilitiesConfig.getCapabilities();
            URL appiumServerUrl = new URL("http://localhost:4723/");
            System.out.println("yha tak aa gya hai");
            System.out.println(appiumServerUrl);
            System.out.println(capabilities);
            try {
                 driver = new AppiumDriver( appiumServerUrl, capabilities);
                 return  driver;
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("=========================================");
            System.out.println("Appium driver initialized successfully!");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return driver;
    }
    public static void setupAndroidEnvironment() {
        System.setProperty("ANDROID_HOME", "/Users/admin/Library/Android/sdk");
        System.setProperty("ANDROID_SDK_ROOT", "/Users/admin/Library/Android/sdk");
    }

    public List<String> getAvailableAvds() {
        List<String> avdList = new ArrayList<>();
        try {
            // Command to list available AVDs
            String command = "emulator -list-avds";

            // Execute the command
            Process process = Runtime.getRuntime().exec(command);

            // Read the output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                avdList.add(line);
            }

            if (avdList.isEmpty()) {
                System.err.println("No AVDs found. Please create an Android Virtual Device.");
            } else {
                System.out.println("Available AVDs: " + avdList);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to get AVD list.");
        }
        return avdList;
    }


    public void startEmulator(String avdName) {
        try {
            // Command to start the AVD
            String command = "/Users/admin/Library/Android/sdk/emulator/emulator -avd " + avdName;

            // Execute the command
            Process process = Runtime.getRuntime().exec(command);

//            // Optional: Read the output of the command
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }

            System.out.println("Android Virtual Device " + avdName + " started successfully.");

            // Wait for the emulator to be ready
            Thread.sleep(10000); // Wait for 30 seconds (adjust time as necessary)

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to start Android Virtual Device: " + avdName);
        }
    }
    public void startAppiumServer() throws MalformedURLException {
        serverManager = new AppiumServerManager();
        serverManager.startServer();
    }
    public boolean isServerRunning() {
        try {
            URL url = new URL("http://127.0.0.1:4723/status"); // Updated endpoint for Appium v2
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            return responseCode == 200; // Server is running
        } catch (IOException e) {
            return false; // Server is not running
        }
    }
    public void takeScreenShot() {
        // Create an instance of ScreenShotsUtility
        ScreenShotsUtility screenShotsUtility = new ScreenShotsUtility(driver);
        // Capture the screenshot and get the path
         screenShotsUtility.captureScreenshot(filePathofScreenShot);

        if (screenShotsUtility != null) {
            System.out.println("Screenshot taken and saved at: " + filePathofScreenShot);
        } else {
            System.out.println("Screenshot capture failed.");
        }
    }
    public void takeScreenRecording(){
        screenRecorder = new ScreenRecorder(driver);
        screenRecorder.startRecording();
        screenRecorder.stopAndSaveRecording(filePathofScreenRecording);
        if (screenRecorder != null) {
            System.out.println("Screen recording taken and saved at: " + filePathofScreenRecording);
        } else {
            System.out.println("Screen recording  failed.");
        }
    }
    public void stopAppiumServer(){
        try {
            // Assuming you're running Appium server via Node.js
            ProcessBuilder processBuilder = new ProcessBuilder("pkill", "-f", "appium");
            Process process = processBuilder.start();
            process.waitFor();
            System.out.println("Appium server stopped successfully.");
        } catch (Exception e) {
            System.err.println("Failed to stop Appium server.");
            e.printStackTrace();
        }
    }
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            System.out.println("Appium driver quit successfully.");
        } else {
            System.out.println("Driver was null. Nothing to quit.");
        }
    }
    public void stopEmulator(String emulatorName) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "adb", "-s", emulatorName, "emu", "kill");
            Process process = processBuilder.start();
            process.waitFor();
            System.out.println("Emulator " + emulatorName + " stopped.");
        } catch (Exception e) {
            System.err.println("Failed to stop the emulator: " + emulatorName);
            e.printStackTrace();
        }
    }

}
