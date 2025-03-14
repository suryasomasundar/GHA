package org.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.CapabilityType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {

//        // Load properties file
//        Properties properties = new Properties();
//        properties.load(new FileInputStream("src/main/resources/config.properties"));
//
//        // Get APK path from the properties file
//        String apkPath = properties.getProperty("app.apk.path");
//
//        if (apkPath == null || apkPath.isEmpty()) {
//            System.out.println("APK path is missing in the config.properties file.");
//            return;
//        }
//
//        System.out.println("Using APK path: " + apkPath);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "emulator-5554"); // or use actual device name if not using emulator
        capabilities.setCapability("app", "/Users/somu/Downloads/Android-MyDemoAppRN.1.3.0.build-244.apk");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("noReset", true); // Keeps the app data intact between sessions


        try {
            // Initialize the AndroidDriver with the given capabilities
            AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
            System.out.println("Appium driver started successfully.");
            // Additional test code here (e.g., interacting with the app)

            // Close the driver after the test
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Hello, World!");
    }
}