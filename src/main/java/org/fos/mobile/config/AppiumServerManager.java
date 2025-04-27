package org.fos.mobile.config;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;

public class AppiumServerManager {
    private AppiumDriverLocalService service;
    private final URL appiumServerUrl;

    public AppiumServerManager() {
        try {
            this.appiumServerUrl = new URL("http://localhost:4723");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Appium server URL!", e);
        }
    }

    private boolean isPortAvailable(int port) {
        try (ServerSocket socket = new ServerSocket(port)) {
            return true; // Port is available
        } catch (IOException e) {
            return false; // Port is in use
        }
    }

    public void startServer() {
        if (!isPortAvailable(4723)) {
            throw new RuntimeException("Port 4723 is already in use. Please free the port and try again.");
        }

        try {
            service = new AppiumServiceBuilder()
                    .usingDriverExecutable(new File("/usr/local/bin/node"))
                    .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                    .withIPAddress("127.0.0.1")
                    .usingPort(4723)
                    .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                    .withArgument(GeneralServerFlag.LOG_LEVEL, "debug") // Enable detailed logs
                    .build();
            service.start();
            System.out.println("Appium server started successfully!");
        } catch (Exception e) {
            System.err.println("Failed to start Appium server: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void stopServer() {
        if (service != null && service.isRunning()) {
            service.stop();
            service = null; // Avoid lingering reference
            System.out.println("Appium server stopped successfully!");
        }
    }

    public boolean isServerRunning() {
        if (service != null && service.isRunning()) {
            try {
                HttpURLConnection connection = (HttpURLConnection) appiumServerUrl.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                return connection.getResponseCode() == 200;
            } catch (IOException e) {
                return false;
            }
        }
        return false;
    }
}