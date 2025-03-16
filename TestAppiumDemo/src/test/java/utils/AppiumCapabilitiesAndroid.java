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

        // ✅ Prevent hidden API adb shell failure
        capabilities.setCapability("ignoreHiddenApiPolicyError", true);
        capabilities.setCapability("uiautomator2ServerInstallTimeout", 60000);
        capabilities.setCapability("disableWindowAnimation", true);
        capabilities.setCapability("adbExecTimeout", 60000); // wait longer for adb commands
        capabilities.setCapability("newCommandTimeout", 300); // keep session alive for longer
        capabilities.setCapability("showChromedriverLog", true);         // if WebView testing
        capabilities.setCapability("autoGrantPermissions", true);        // avoid manual permission popups



        
        return capabilities;
    }
}
