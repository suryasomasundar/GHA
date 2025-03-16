package browserstack;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.BSConfigHelper;

import java.net.URL;
import java.time.Duration;

/**
 * Plain Vanilla E2E test flow for iOS and it's working ;-)
 * Updated to use environment variables for BrowserStack credentials.
 *
 * @author Somu
 * @since 15 Mar, 2025
 */

public class iOSTestFlow {

    private AppiumDriver driver;
    private static final Logger log = LoggerFactory.getLogger(iOSTestFlow.class);

    @BeforeClass
    public void setUp() throws Exception {
        log.info("🔐 Setting up BrowserStack credentials...");
        String username = BSConfigHelper.getUsername();
        String accessKey = BSConfigHelper.getAccessKey();
        BSConfigHelper.validateCreds(username, accessKey);

        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("device", "iPhone 14");
        caps.setCapability("os_version", "16");
        caps.setCapability("platformVersion", "16.0");
        caps.setCapability("deviceName", "iPhone 14");
        caps.setCapability("automationName", "XCUITest");
        caps.setCapability("app", "bs://07a684db2885271dbfbc4d039410661de28515c3");
        caps.setCapability("browserstack.user", username);
        caps.setCapability("browserstack.key", accessKey);
        caps.setCapability("project", "iOS E2E");
        caps.setCapability("build", "iOS Checkout Flow");
        caps.setCapability("name", "BS iOS Test");
        caps.setCapability("browserstack.debug", "true");
        caps.setCapability("browserstack.networkLogs", "true");

        String buildName = "CI Build #" + System.currentTimeMillis();
        caps.setCapability("build", buildName);
        log.info("📦 BrowserStack Build: {}", buildName);

        log.info("🚀 Launching iOS Appium session on BrowserStack...");
        driver = new IOSDriver(new URL(BSConfigHelper.getHubUrl(username, accessKey)), caps);
        log.info("✅ iOS Appium session started.");

        System.out.println("iOS Appium session started on BrowserStack.");
    }

    @Test()
    public void testLaunchApp() throws InterruptedException {
        log.info("🧪 Starting test: testLaunchApp");
        // Log the app launch confirmation
        System.out.println("Successfully Launched App on Simulator");

        // WebDriverWait to handle waiting for elements dynamically
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Step 1: Open app (already handled by setup)
        System.out.println("Testing Started ..");


        // Step 2: Navigate to the first product on the list
        log.info("➡️ Selecting first product...");
        WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//XCUIElementTypeStaticText[@label='Sauce Labs Backpack']")));
        firstProduct.click();
        Thread.sleep(1000); // Small Delay for visual quality check

        // Step 3: Increase quantity to 2
        log.info("➕ Increasing quantity...");
        WebElement quantityIncreaseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//XCUIElementTypeOther[@name='counter plus button']")));
        quantityIncreaseButton.click(); // Increase quantity to 2
        Thread.sleep(1000);

        // Step 4: Add product to the cart
        log.info("🛒 Adding to cart...");
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//XCUIElementTypeOther[@name='Add To Cart button']")));
        addToCartButton.click();
        Thread.sleep(1000);

        // Step 5: Go to Cart
        log.info("🧺 Opening cart...");
        WebElement cartIcon = driver.findElement(By.xpath("//XCUIElementTypeButton[@name='tab bar option cart']"));
        cartIcon.click();
        Thread.sleep(1000);

        // Step 6: Proceed to checkout
        log.info("➡️ Proceeding to checkout...");
        WebElement proceedCheckout = driver.findElement(By.xpath("//XCUIElementTypeOther[@name='Proceed To Checkout button']"));
        proceedCheckout.click();
        Thread.sleep(1000);


        // Step 7: Complete account creation
        log.info("🔐 Logging in...");
        enterEmail("bob@example.com", "//XCUIElementTypeTextField[@name='Username input field']", 3);

        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//XCUIElementTypeSecureTextField[@name= 'Password input field']")));
        WebElement confirmLoginButton = driver.findElement(By.xpath("//XCUIElementTypeOther[@name= 'Login button']"));

        passwordField.clear();
        passwordField.click();
        passwordField.sendKeys("10203040");
        confirmLoginButton.click();
        Thread.sleep(1000);


        // Step 8: Enter address details
        log.info("🏠 Filling address form...");
        WebElement fullNameField = driver.findElement(By.xpath("//XCUIElementTypeTextField[@name= 'Full Name* input field']"));
        WebElement addressLine1Field = driver.findElement(By.xpath("//XCUIElementTypeTextField[@name= 'Address Line 1* input field']"));
        WebElement cityField = driver.findElement(By.xpath("//XCUIElementTypeTextField[@name= 'City* input field']"));
        WebElement zipCodeField = driver.findElement(By.xpath("//XCUIElementTypeTextField[@name= 'Zip Code* input field']"));
        WebElement countryField = driver.findElement(By.xpath("//XCUIElementTypeTextField[@name= 'Country* input field']"));
        WebElement paymentButton = driver.findElement(By.xpath("//XCUIElementTypeOther[@name= 'To Payment button']"));

        fullNameField.sendKeys("My Name");
        addressLine1Field.sendKeys("Street 123");
        cityField.sendKeys("New York");
        zipCodeField.sendKeys("10001");
        countryField.sendKeys("United States");
        countryField.sendKeys(Keys.RETURN); // Tap the Return key to dismiss the keyboard
        paymentButton.click();
        Thread.sleep(2000);

        // Step 9: Verify /Assert - payment method and review order buttons
        log.info("✅ Asserting payment method and review button...");
        WebElement paymentMethodText = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name= 'Enter a payment method']"));
        WebElement reviewOrderButton = driver.findElement(By.xpath("//XCUIElementTypeOther[@name= 'Review Order button']"));

        Assert.assertTrue(paymentMethodText.isDisplayed(), "Text 'Enter a payment method' is not shown!");
        Assert.assertTrue(reviewOrderButton.isDisplayed(), "Review order button is not shown!");

        Thread.sleep(2000);
        log.info("✅ Test passed: Checkout flow completed.");
    }

    @AfterClass
    public void tearDown() {
        log.info("🧹 Tearing down session...");
        if (driver != null) {
            driver.quit();
            log.info("✅ Driver quit successfully.");
        }
    }

    public void enterEmail(String email, String locator, int retries) {
        boolean success = false;

        // Retry logic for entering email to ensure it is entered correctly

        for (int i = 0; i < retries; i++) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));

                // Clear the field
                emailField.clear();

                // Enter the email character by character
                for (char c : email.toCharArray()) {
                    emailField.sendKeys(String.valueOf(c));
                    Thread.sleep(100); // Add a small delay between characters
                }

                // Verify the input
                String enteredText = emailField.getAttribute("value");
                if (!enteredText.equals(email)) {
                    System.out.println("Incorrect value entered: " + enteredText);
                    emailField.clear();
                    emailField.sendKeys(email);
                }

                System.out.println("Value set successfully using sendKeys.");
                success = true;
                break; // Exit the loop if successful
            } catch (Exception e) {
                System.out.println("Attempt " + (i + 1) + " failed: " + e.getMessage());
                if (i == retries - 1) {
                    System.out.println("All retry attempts failed.");
                    throw new RuntimeException("Failed to set email after " + retries + " retries.");
                }
            }
        }
    }
}
