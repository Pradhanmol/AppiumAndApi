# FOS API & UI Automation Guide

## Appium & Selenium Setup Guide

This repository allows you to automate both API and App UI testing using the respective technologies. Below are the steps and prerequisites required to successfully run the tests in this repository.

## Technologies Used

- **Rest Assured** for API Automation
- **Appium & Selenium** for Mobile UI Automation
    - **Why Selenium is required in Appium?**
        - Appium relies on Selenium WebDriver for mobile automation because Selenium provides the essential APIs to interact with the mobile applications (iOS/Android). Appium builds on top of Selenium WebDriver and extends its functionalities to automate mobile apps on real devices and emulators/simulators.

## Repository Structure

- **API Automation**: Located under the `api` package in `src/main` and `src/test`.
- **App UI Automation**: Located under the `mobile` package.
- **Excel Data Provider**: Used to read test data from Excel files for both API and App UI tests. The relevant Excel files are stored under the `utils` directory in the respective packages.

## Prerequisites

1. **Java 11** must be installed.
2. **Appium Version 2.12.1** must be installed.
3. **Android SDK**: Ensure that the SDK is installed and updated.
4. **Appium Server**: Appium server should be working, and the server can be started manually from the terminal.
5. **Virtual Device**: Make sure a virtual device (AVD) is created and started.

## Setup Steps

1. **Clone the Repository**:
    ```
    git clone <repository_url>
    cd <repository_name>
    ```

2. **Install Prerequisites**:
    - Install **Java 11** and set up the `JAVA_HOME` environment variable.
    - Install **Appium** version `2.12.1` via npm:
      ```
      npm install -g appium@2.12.1
      ```
    - Install **Android SDK** and set up the `ANDROID_HOME` environment variable.

3. **Install Appium Dependencies**:
    ```
    npm install -g appium-doctor
    appium-doctor
    ```

4. **Start the Appium Server**:
    - You can start the Appium server manually by running the following command:
      ```
      appium
      ```

5. **Install Android Emulator**:
    - Create and start a virtual device (AVD) using the Android SDK:
        - To create an AVD, use the following command:
          ```
          sdkmanager --list
          avdmanager create avd -n <avd_name> -k "system-images;android-<api_level>;google_apis;x86"
          ```
        - To start the AVD:
          ```
          emulator -avd <avd_name>
          ```

6. **Check for Updates**:
    - Make sure all your packages are updated:
      ```
      sdkmanager --update
      ```

7. **Verify Appium Server & Virtual Device**:
    - Ensure Appium server is running by verifying through the terminal.
    - Also, make sure the virtual device is running and accessible.


## Configuration in `.zshrc` or `.bashrc`

In order to make the environment setup smoother, add the following configurations to your `.zshrc` (if you're using Zsh) or `.bashrc` (if you're using Bash). This ensures your environment variables are correctly set up, particularly for Java, Maven, and Appium.

### Example for `.bashrc` or `.zshrc`:
```bash
# Set JAVA_HOME for Java 11
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH

# Set Maven Home (adjust the path according to your system)
export MAVEN_HOME=/usr/share/maven
export PATH=$MAVEN_HOME/bin:$PATH

# Set Appium Home (if using global npm installation)
export APPIUM_HOME=/usr/local/lib/node_modules/appium
export PATH=$APPIUM_HOME:$PATH

# Set Node.js (for Appium)
export PATH=/usr/local/bin:$PATH
```

After adding this to your `.bashrc` or `.zshrc`, don’t forget to source it:

```bash
source ~/.bashrc  # or source ~/.zshrc for Zsh
```

## Running the Tests
Once you've installed the required libraries and set up the environment:

1. **Build the project** using Maven:
   ```bash
   mvn clean install
   ```

2. **Run the tests**:
   ```bash
   mvn test
   ```

Make sure you have Appium running if you're testing mobile apps, using:
```bash
appium
```


## Running Tests

1. **Navigate to the test directory** in your cloned repo:
    ```
    cd <path_to_test_directory>
    ```

2. **Run TestNG tests**:
    - To run the tests, simply run any `TestNG` file (such as `APITest.java` or `MobileTest.java`) via your IDE or command line.
    - Make sure that the versions used in the repo match the versions installed on your local machine.
    - Example command:
      ```
      mvn clean test
      ```

3. **Ensure Test Data**:
    - Excel files for the data provider are located under `src/test/resources` and `utils`.
    - API tests read data from `api/utils/excel` and Mobile UI tests from `mobile/utils/excel`.

## Additional Notes

- **API Tests**: All the related files for API automation are under the `api` package.
- **Mobile UI Tests**: All the related files for mobile automation (using Appium and Selenium) are under the `mobile` package.
- **Excel Handling**: Excel files are used as data providers for both API and App UI tests. They are located in the `utils` subdirectories within `api` and `mobile`.

## Appium & Selenium Compatibility

- **Selenium** is a required component for Appium as it provides the WebDriver interface to interact with mobile applications in a similar way it interacts with web browsers. Appium uses Selenium to simulate user interactions (taps, clicks, text entry, etc.) on the mobile applications.
