package utils;

import org.openqa.selenium.remote.DesiredCapabilities;
/**
 * Defined appium capabilities for android
 *
 * @author Somu
 * @since 15 Mar, 2025
 */

public class AppiumCapabilitiesAndroid {

    public static DesiredCapabilities getAndroidCapabilities(String appPath) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", ConfigReader.getProperty("android.platformName"));
        capabilities.setCapability("platformVersion", ConfigReader.getProperty("android.platformVersion"));
        capabilities.setCapability("deviceName", ConfigReader.getProperty("android.deviceName"));
        capabilities.setCapability("automationName", ConfigReader.getProperty("android.automationName"));
        capabilities.setCapability("app", appPath);
        return capabilities;
    }
}
