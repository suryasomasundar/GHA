package utils;

import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Setting Desired Capabilities for iOS platform
 *
 * @author Somu
 * @since 14 Mar, 2025
 */

public class AppiumCapabilitiesIos {

    public static DesiredCapabilities getIOSCapabilities(String appPath) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", ConfigReader.getProperty("ios.platformName"));
        capabilities.setCapability("platformVersion", ConfigReader.getProperty("ios.platformVersion"));
        capabilities.setCapability("deviceName", ConfigReader.getProperty("ios.deviceName"));
        capabilities.setCapability("automationName", ConfigReader.getProperty("ios.automationName"));
        capabilities.setCapability("app", appPath);
        return capabilities;
    }
}
