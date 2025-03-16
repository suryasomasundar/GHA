package vanillaFlow;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;

/**
 * Plain Vanilla E2E test flow for iOS  and it's working ;-)
 *
 * @author Somu
 * @since 14 Mar, 2025
 */

public class iOSDemoTest {

    private AppiumDriver driver;

    @BeforeClass
    public void setUp() throws Exception {
        // Set up desired capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "18.3");
        capabilities.setCapability("deviceName", "iPhone 16");
        capabilities.setCapability("app", "/Users/somu/Downloads/MyRNDemoApp.app");
        capabilities.setCapability("automationName", "XCUITest");


        // URL for Appium server
        URL url = new URL("http://localhost:4723");

        // Initialize the driver for iOS Simulator
        driver = new IOSDriver(url, capabilities);
    }

    @Test(enabled = false)
    public void testLaunchApp() throws InterruptedException {
        // Log the app launch confirmation
        System.out.println("Successfully Launched App on Simulator");

        // WebDriverWait to handle waiting for elements dynamically
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Step 1: Open app (already handled by setup)
        System.out.println("Testing Started ..");


        // Step 2: Navigate to the first product on the list
        WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//XCUIElementTypeStaticText[@label='Sauce Labs Backpack']")));
        firstProduct.click();
        Thread.sleep(1000); // Small Delay for visual quality check

        // Step 3: Increase quantity to 2
        WebElement quantityIncreaseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//XCUIElementTypeOther[@name='counter plus button']")));
        quantityIncreaseButton.click(); // Increase quantity to 2
        Thread.sleep(1000);

        // Step 4: Add product to the cart
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//XCUIElementTypeOther[@name='Add To Cart button']")));
        addToCartButton.click();
        Thread.sleep(1000);

        // Step 5: Go to Cart
        WebElement cartIcon = driver.findElement(By.xpath("//XCUIElementTypeButton[@name='tab bar option cart']"));
        cartIcon.click();
        Thread.sleep(1000);

        // Step 6: Proceed to checkout
        WebElement proceedCheckout = driver.findElement(By.xpath("//XCUIElementTypeOther[@name='Proceed To Checkout button']"));
        proceedCheckout.click();
        Thread.sleep(1000);


        // Step 7: Complete account creation
        enterEmail("bob@example.com", "//XCUIElementTypeTextField[@name='Username input field']", 3);

        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//XCUIElementTypeSecureTextField[@name= 'Password input field']")));
        WebElement confirmLoginButton = driver.findElement(By.xpath("//XCUIElementTypeOther[@name= 'Login button']"));

        passwordField.clear();
        passwordField.click();
        passwordField.sendKeys("10203040");
        confirmLoginButton.click();
        Thread.sleep(1000);


        // Step 8: Enter address details
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
        WebElement paymentMethodText = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name= 'Enter a payment method']"));
        WebElement reviewOrderButton = driver.findElement(By.xpath("//XCUIElementTypeOther[@name= 'Review Order button']"));

        Assert.assertTrue(paymentMethodText.isDisplayed(), "Text 'Enter a payment method' is not shown!");
        Assert.assertTrue(reviewOrderButton.isDisplayed(), "Review order button is not shown!");

        Thread.sleep(2000);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
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
