package org.fos.mobile.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;

import java.io.IOException;

public class ScreenRecorder {

    private AppiumDriver driver;

    public ScreenRecorder(AppiumDriver driver) {
        this.driver = driver;
    }

    // Start Screen Recording
    public void startRecording() {
        if (driver instanceof CanRecordScreen) {
            ((CanRecordScreen) driver).startRecordingScreen();
            System.out.println("Screen recording started...");
        }
    }

    // Stop Recording and Save the File
    public void stopAndSaveRecording(String filePath) {
        if (driver instanceof CanRecordScreen) {
            // Stop the screen recording and get the base64 encoded video string
            String base64Video = ((CanRecordScreen) driver).stopRecordingScreen();

            try (java.io.OutputStream outputStream = new java.io.FileOutputStream(filePath)) {

                // Decode the base64 string to bytes
                byte[] videoBytes = java.util.Base64.getDecoder().decode(base64Video);

                // Write the bytes to the specified file
                outputStream.write(videoBytes);

                System.out.println("Screen recording saved successfully at: " + filePath);
            } catch (IOException e) {
                System.err.println("Error occurred while saving the screen recording: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.err.println("Driver does not support screen recording.");
        }
    }
}
