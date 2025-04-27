package org.fos.api.config;

import io.restassured.RestAssured;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Base {

    // Declaring the object of Properties class to read configuration details
    static Properties props;

    // Define the path to the properties file
    String path = System.getProperty("user.dir") + "/src/main/resources/config.properties";

    public String enviorment;

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public String getEnviorment() {
        return this.enviorment;
    }

    public String baseUrl;

    // Constructor to initialize the Properties object and read the configuration file
    public Base() {
        // Initialize the Properties object
        try {
            props = new Properties();
            // Use FileInputStream to read the properties file
            FileInputStream fis = new FileInputStream(path);
            // Load properties from the file
            props.load(fis);
        } catch (IOException e) {
            // Print stack trace if there is an issue with file reading
            e.printStackTrace();
        }
        this.enviorment = props.getProperty("baseEnv");
        setBaseUrl();
    }
    public void setBaseUrl(){
        switch (this.enviorment.toLowerCase())
        {
            case "staging" :
                this.baseUrl = "https://api.staging.credgenics.com";
                break;
            case "dev" :
                this.baseUrl = "https://p3-api.dev.credgenics.com";
                break;
            case "production" :
                this.baseUrl = "https://api.staging.credgenics.com";
            default:
                throw new RuntimeException("Unknown environment: " + this.enviorment);//fallback case

        }
    }
    public String getAuthenticationToken(){
        return props.getProperty("authenticationToken");
    }
}