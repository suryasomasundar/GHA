package vanillaFlow;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;

/**
 * Plain Vanilla E2E test flow for android  and it's working ;-)
 *
 * @author Somu
 * @since 14 Mar, 2025
 */


public class androidDemoTest {

    private AppiumDriver driver;

    @BeforeClass
    public void setUp() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setDeviceName("Google Pixel 7"); // Use a real device name from BS
        options.setPlatformVersion("13.0");       // Optional but recommended for consistency
        //options.setApp("/Users/somu/Downloads/Android-MyDemoAppRN.1.3.0.build-244.apk");
        options.setApp("bs://e9e7e19a0331e9920b162afc2f06b8844c71e8b3");  // Replace with the actual app_url
        options.setAutomationName("UiAutomator2");

        options.setCapability("browserstack.user", "somusurya_3yWHf1");
        options.setCapability("browserstack.key", "Sinb4RuhiyWhbU32Wa2K");
        options.setCapability("browserstack.local", "true");
        options.setCapability("project", "Vanilla E2E");
        options.setCapability("build", "Checkout Flow Test");
        options.setCapability("name", "BS Vanilla Flow");

        options.setCapability("browserstack.debug", "true");        // Enable visual logs (screenshots, etc.)
        options.setCapability("browserstack.networkLogs", "true");  // Capture network traffic


        // options.fullReset();

        // Initialize the driver
        //driver = new AndroidDriver(new URL("http://localhost:4723"), options);
        // ✅ Initialize driver pointing to BrowserStack hub
        driver = new AndroidDriver(new URL("http://somusurya_3yWHf1:Sinb4RuhiyWhbU32Wa2K@hub.browserstack.com/wd/hub"), options);
        System.out.println("Appium driver started successfully.");
    }

    @Test()
    public void testCheckoutFlow() throws InterruptedException {

        // Step 1: Open app (already handled by setup)
        System.out.println("Testing  Started..");

        // Step 2: Navigate to the first product on the list
        WebElement firstProduct = driver.findElement(By.xpath("//android.widget.TextView[@text='Sauce Labs Backpack']"));
        firstProduct.click();
        Thread.sleep(1000);

        // Step 3: Add quantity 2
        WebElement quantityIncreaseButton = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='counter plus button']"));
        quantityIncreaseButton.click(); // Increase quantity to 2
        Thread.sleep(1000);

        // Step 4: Add to cart , go to cart & Proceed tp Checkout
        WebElement addToCartButton = driver.findElement(By.xpath("//android.widget.TextView[@text='Add To Cart']"));
        addToCartButton.click();
        Thread.sleep(2000);

        WebElement cartIcon = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='cart badge']"));
        cartIcon.click();
        Thread.sleep(2000);

        WebElement proceedCheckout = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Proceed To Checkout button']"));
        proceedCheckout.click();
        Thread.sleep(2000);


        // Step 5: Complete account creation
        Thread.sleep(1000);
        WebElement emailField = driver.findElement(By.xpath("//android.widget.EditText[@content-desc= 'Username input field']"));
        WebElement passwordField = driver.findElement(By.xpath("//android.widget.EditText[@content-desc='Password input field']"));
        WebElement confirmLoginButton = driver.findElement(By.xpath("(//android.widget.TextView[@text='Login'])[2]"));

        emailField.sendKeys("bob@example.com");
        passwordField.sendKeys("10203040");
        confirmLoginButton.click();
        Thread.sleep(2000);


        // Step 6: Complete checkout

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

        // Step 7: Verify /Assert - payment method and review order buttons

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
