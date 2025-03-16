package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

/**
 * Appium Driver Manager to initialize Appium driver for Android or iOS
 *
 * @author Somu
 * @since 14 Mar, 2025
 */
public class AppiumDriverManager {

    private AppiumDriver driver;

    public AppiumDriver getDriver() {
        return driver;
    }

    public void initializeDriver(String platform) throws Exception {
        String appUrl;
        String appFileName;
        File appFile;

        // Local directory to save the downloaded and extracted files
        File appDir = new File("src/test/resources/apps");
        if (!appDir.exists()) {
            appDir.mkdirs(); // Create the directory if it doesn't exist
        }

        // Platform-specific logic
        if (platform.equalsIgnoreCase("android")) {
            // Android configuration
            appUrl = "https://github.com/saucelabs/my-demo-app-rn/releases/download/v1.3.0/Android-MyDemoAppRN.1.3.0.build-244.apk";
            appFileName = "MyRNDemoApp.apk";

            // Download the APK file
            appFile = FileDownloader.downloadFile(appUrl, appDir, appFileName);
            System.out.println("APK downloaded successfully: " + appFile.getAbsolutePath());

            // Set up Android capabilities
            DesiredCapabilities capabilities = AppiumCapabilitiesAndroid.getAndroidCapabilities(appFile.getAbsolutePath());

            // Initialize the Android driver
            URL url = new URL("http://localhost:4723");
            driver = new AndroidDriver(url, capabilities);
        } else if (platform.equalsIgnoreCase("ios")) {
            // iOS configuration
            appUrl = "https://github.com/saucelabs/my-demo-app-rn/releases/download/v1.3.0/iOS-Simulator-MyRNDemoApp.1.3.0-162.zip";
            appFileName = "MyRNDemoApp.app.zip";

            // Download the ZIP file
            File zipFile = FileDownloader.downloadFile(appUrl, appDir, appFileName);

            // Extract the ZIP file
            System.out.println("Extracting zip file...");
            appFile = ZipExtractor.extractZipFile(zipFile, appDir);
            System.out.println("App directory extracted successfully: " + appFile.getAbsolutePath());

            // Clean up the ZIP file
            System.out.println("Deleting zip file...");
            zipFile.delete();

            // Set up iOS capabilities
            DesiredCapabilities capabilities = AppiumCapabilitiesIos.getIOSCapabilities(appFile.getAbsolutePath());

            // Initialize the iOS driver
            URL url = new URL("http://localhost:4723");
            driver = new IOSDriver(url, capabilities);
        } else {
            throw new IllegalArgumentException("Unsupported platform: " + platform);
        }

        System.out.println("Appium driver initialized successfully for " + platform.toUpperCase());
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void main(String[] args) {
        AppiumDriverManager manager = new AppiumDriverManager();
        try {
            // Specify the platform (android or ios)
            String platform = "android"; // Change to "ios" for iOS tests

            // Initialize the driver (download and extract the app file)
            manager.initializeDriver(platform);

            // Verify the driver is initialized
            if (manager.getDriver() != null) {
                System.out.println("Driver initialization successful for " + platform.toUpperCase() + "!");
            } else {
                System.out.println("Driver initialization failed.");
            }

            // Add a small delay to keep the driver session active for a few seconds
            Thread.sleep(5000); // 5 seconds delay
        } catch (Exception e) {
            System.err.println("Error during initialization: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Tear down the driver
            manager.tearDown();
        }
    }
}
