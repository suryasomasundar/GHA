package tests;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.*;
import ios.pages.CartPage;
import ios.pages.CheckoutPage;
import ios.pages.HomePage;
import ios.pages.ProductPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.AppiumDriverManager;
import utils.RetryUtil;
import utils.ScreenshotUtil;

/**
 * End-to-end test class for verifying the checkout flow on an iOS simulator.
 * This test covers the complete user journey from product selection to checkout.
 *
 * <p>
 * The test includes:
 * <ul>
 *     <li>Launching the app on an iOS simulator.</li>
 *     <li>Selecting a product, increasing quantity, and adding it to the cart.</li>
 *     <li>Navigating to the cart and proceeding to checkout.</li>
 *     <li>Entering user details, payment information, and verifying the review order.</li>
 * </ul>
 * </p>
 *
 * <p>
 * The test is annotated with Allure annotations for detailed reporting and includes
 * error handling with screenshots and logs for failures.
 * </p>
 *
 * @author Somu
 * @since 14 Mar, 2025
 */

public class iOSCheckoutTest {

    private AppiumDriverManager driverManager;
    private AppiumDriver driver;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeClass
    public void setUp() throws Exception {
        String platform = "iOS";

        driverManager = new AppiumDriverManager();
        driverManager.initializeDriver(platform);
        driver = driverManager.getDriver();

        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @Test()
    @Epic("Demo Checkout Flow")
    @Feature("iOS Checkout")
    @Story("User completes e2e checkout on iOS")
    @Description("This test verifies the checkout flow on an iOS simulator device.")
    @Severity(SeverityLevel.CRITICAL)
    public void testLaunchApp() throws InterruptedException {

        try {
            System.out.println("Successfully Launched App on Simulator");
            Allure.addAttachment("App Launch", "text/plain", "App launched successfully on iOS simulator.");
            System.out.println("Testing Started ..");

            homePage.selectFirstProduct();
            Thread.sleep(500);

            productPage.increaseQuantity();
            Thread.sleep(500);

            productPage.addToCart();
            Thread.sleep(500);

            cartPage.goToCart();
            Thread.sleep(500);

            cartPage.proceedToCheckout();
            Thread.sleep(500);

            RetryUtil.enterEmailWithRetry(driver, "bob@example.com", checkoutPage.emailField, 3);
            checkoutPage.enterPassword("10203040");
            checkoutPage.clickLoginButton();
            Thread.sleep(500);

            checkoutPage.enterAddressDetails("My Name", "Street 123", "New York", "10001", "United States");
            checkoutPage.clickPaymentButton();
            Thread.sleep(500);

            checkoutPage.verifyPaymentMethodAndReviewOrder();
            Thread.sleep(500);

            // Log success
            Allure.addAttachment("Checkout Flow", "text/plain", "Checkout flow completed successfully.");
        } catch (Exception e) {
            // Attach screenshot and logs on failure
            ScreenshotUtil.takeScreenshot(driver, "Checkout Flow Failed");
            Allure.addAttachment("Error Log", "text/plain", "Checkout flow failed: " + e.getMessage());
            throw e; // Re-throw the exception to mark the test as failed
        }
    }

    @AfterClass
    public void tearDown() {
        driverManager.tearDown();
    }
}
