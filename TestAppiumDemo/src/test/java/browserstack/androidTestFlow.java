package browserstack;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;

/**
 * Plain Vanilla E2E test flow for Android and it's working ;-)
 * Updated to use environment variables for BrowserStack credentials.
 *
 * @author Somu
 * @since 14 Mar, 2025
 */

public class androidTestFlow {

    private AppiumDriver driver;

    @BeforeClass
    public void setUp() throws Exception {
        String username = System.getProperty("BROWSERSTACK_USERNAME", System.getenv("BROWSERSTACK_USERNAME"));
        String accessKey = System.getProperty("BROWSERSTACK_ACCESS_KEY", System.getenv("BROWSERSTACK_ACCESS_KEY"));

        System.out.println("USERNAME: '" + username + "'");
        System.out.println("ACCESS KEY: '" + accessKey + "'");

        if (username == null || username.trim().isEmpty() ||
                accessKey == null || accessKey.trim().isEmpty()) {
            throw new IllegalArgumentException("Please set BROWSERSTACK_USERNAME and BROWSERSTACK_ACCESS_KEY.");
        }

        MutableCapabilities caps = new MutableCapabilities();

        caps.setCapability("platformName", "Android");
        caps.setCapability("device", "Google Pixel 6");
        caps.setCapability("os_version", "12.0");
        caps.setCapability("deviceName", "Google Pixel 6"); // 🔥 Required!
        caps.setCapability("app", "bs://e9e7e19a0331e9920b162afc2f06b8844c71e8b3");

        caps.setCapability("browserstack.user", username);
        caps.setCapability("browserstack.key", accessKey);
        caps.setCapability("browserstack.local", "false");

        caps.setCapability("project", "Android E2E");
        caps.setCapability("build", "Checkout Flow Test");
        caps.setCapability("name", "BS Android Test Flow");

        caps.setCapability("browserstack.debug", "true");
        caps.setCapability("browserstack.networkLogs", "true");

        String bsUrl = "http://" + username + ":" + accessKey + "@hub.browserstack.com/wd/hub";
        driver = new AndroidDriver(new URL(bsUrl), caps);

        System.out.println("Appium driver started successfully.");
    }

    @Test
    public void testCheckoutFlow() throws InterruptedException {
        System.out.println("Testing Started..");

        WebElement firstProduct = driver.findElement(By.xpath("//android.widget.TextView[@text='Sauce Labs Backpack']"));
        firstProduct.click();
        Thread.sleep(1000);

        WebElement quantityIncreaseButton = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='counter plus button']"));
        quantityIncreaseButton.click();
        Thread.sleep(1000);

        WebElement addToCartButton = driver.findElement(By.xpath("//android.widget.TextView[@text='Add To Cart']"));
        addToCartButton.click();
        Thread.sleep(2000);

        WebElement cartIcon = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='cart badge']"));
        cartIcon.click();
        Thread.sleep(2000);

        WebElement proceedCheckout = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Proceed To Checkout button']"));
        proceedCheckout.click();
        Thread.sleep(2000);

        WebElement emailField = driver.findElement(By.xpath("//android.widget.EditText[@content-desc= 'Username input field']"));
        WebElement passwordField = driver.findElement(By.xpath("//android.widget.EditText[@content-desc='Password input field']"));
        WebElement confirmLoginButton = driver.findElement(By.xpath("(//android.widget.TextView[@text='Login'])[2]"));

        emailField.sendKeys("bob@example.com");
        passwordField.sendKeys("10203040");
        confirmLoginButton.click();
        Thread.sleep(2000);

        WebElement fullNameField = driver.findElement(By.xpath("//android.widget.EditText[@content-desc='Full Name* input field']"));
        WebElement addressLine1Field = driver.findElement(By.xpath("//android.widget.EditText[@content-desc='Address Line 1* input field']"));
        WebElement cityField = driver.findElement(By.xpath("//android.widget.EditText[@content-desc='City* input field']"));
        WebElement zipCodeField = driver.findElement(By.xpath("//android.widget.EditText[@content-desc='Zip Code* input field']"));
        WebElement countryField = driver.findElement(By.xpath("//android.widget.EditText[@content-desc='Country* input field']"));
        WebElement paymentButton = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='To Payment button']"));

        fullNameField.sendKeys("My Name");
        addressLine1Field.sendKeys("Street 123");
        cityField.sendKeys("New York");
        zipCodeField.sendKeys("10001");
        countryField.sendKeys("United States");
        paymentButton.click();
        Thread.sleep(2000);

        WebElement paymentMethodText = driver.findElement(By.xpath("//android.widget.TextView[@text='Enter a payment method']"));
        WebElement reviewOrderButton = driver.findElement(By.xpath("//android.widget.TextView[@text='Review Order']"));

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
}
