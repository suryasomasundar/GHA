package browserstack;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.BSConfigHelper;

import java.net.URL;

/**
 * Plain Vanilla E2E test flow for Android and it's working ;-)
 * Updated to use environment variables for BrowserStack credentials.
 *
 * @author Somu
 * @since 15 Mar, 2025
 */

public class androidTestFlow {

    private AppiumDriver driver;
    private static final Logger log = LoggerFactory.getLogger(androidTestFlow.class);


    @BeforeClass
    public void setUp() throws Exception {

        log.info("🔐 Setting up BrowserStack credentials...");
        String username = BSConfigHelper.getUsername();
        String accessKey = BSConfigHelper.getAccessKey();
        BSConfigHelper.validateCreds(username, accessKey);

        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("device", "Google Pixel 6");
        caps.setCapability("os_version", "12.0");
        caps.setCapability("deviceName", "Google Pixel 6");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("app", "bs://e9e7e19a0331e9920b162afc2f06b8844c71e8b3");
        caps.setCapability("browserstack.user", username);
        caps.setCapability("browserstack.key", accessKey);
        caps.setCapability("project", "Android E2E");
        caps.setCapability("build", "Android Checkout Flow");
        caps.setCapability("name", "BS Android Test");
        caps.setCapability("browserstack.debug", "true");
        caps.setCapability("browserstack.networkLogs", "true");

        log.info("🚀 Launching Appium driver on BrowserStack...");
        driver = new AndroidDriver(new URL(BSConfigHelper.getHubUrl(username, accessKey)), caps);
        log.info("✅ Appium session started.");
    }

    @Test
    public void testCheckoutFlow() throws InterruptedException {
        log.info("🧪 Starting test: testCheckoutFlow");
        System.out.println("Testing Started..");

        Thread.sleep(1000);

        log.info("➡️ Selecting first product...");
        WebElement firstProduct = driver.findElement(By.xpath("//android.widget.TextView[@text='Sauce Labs Backpack']"));
        firstProduct.click();
        Thread.sleep(1000);

        log.info("➕ Increasing quantity...");
        WebElement quantityIncreaseButton = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='counter plus button']"));
        quantityIncreaseButton.click();
        Thread.sleep(1000);

        log.info("🛒 Adding product to cart...");
        WebElement addToCartButton = driver.findElement(By.xpath("//android.widget.TextView[@text='Add To Cart']"));
        addToCartButton.click();
        Thread.sleep(2000);

        log.info("🧺 Opening cart...");
        WebElement cartIcon = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='cart badge']"));
        cartIcon.click();
        Thread.sleep(2000);

        log.info("➡️ Proceeding to checkout...");
        WebElement proceedCheckout = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Proceed To Checkout button']"));
        proceedCheckout.click();
        Thread.sleep(2000);

        log.info("🔐 Entering login details...");
        WebElement emailField = driver.findElement(By.xpath("//android.widget.EditText[@content-desc= 'Username input field']"));
        WebElement passwordField = driver.findElement(By.xpath("//android.widget.EditText[@content-desc='Password input field']"));
        WebElement confirmLoginButton = driver.findElement(By.xpath("(//android.widget.TextView[@text='Login'])[2]"));

        emailField.sendKeys("bob@example.com");
        passwordField.sendKeys("10203040");
        confirmLoginButton.click();
        Thread.sleep(2000);

        log.info("🏠 Filling address details...");
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

        log.info("✅ Verifying payment screen...");
        WebElement paymentMethodText = driver.findElement(By.xpath("//android.widget.TextView[@text='Enter a payment method']"));
        WebElement reviewOrderButton = driver.findElement(By.xpath("//android.widget.TextView[@text='Review Order']"));

        Assert.assertTrue(paymentMethodText.isDisplayed(), "Text 'Enter a payment method' is not shown!");
        Assert.assertTrue(reviewOrderButton.isDisplayed(), "Review order button is not shown!");

        Thread.sleep(2000);
        log.info("✅ Test passed: Checkout flow completed successfully.");
    }

    @AfterClass
    public void tearDown() {
        log.info("🛑 Tearing down Appium session...");
        if (driver != null) {
            driver.quit();
            log.info("✅ Driver quit successfully.");
        }
    }
}
