package stepDefination;

import android.pages.CartPage;
import android.pages.CheckoutPage;
import android.pages.LoginPage;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Given;
import utils.AppiumDriverManager;
import utils.TestContext;

import java.time.Duration;

/**
 * Step definitions for login test scenarios
 *
 * @author Somu
 * @since 14 Mar, 2025
 */

public class Login {

    private LoginPage loginPage;

    private TestContext context;

    public Login(TestContext context) {
        this.context = context; // Inject the shared context
    }

    @Given("Complete account creation with email {string} and password {string}")
    public void complete_account_creation_with_email_and_password(String username, String password) {
        LoginPage loginPage = context.getLoginPage(); // Get LoginPage from the shared context
        loginPage.login(username, password);

    }

    @Given("Open app")
    public void open_app() {
        // Initialize the driver using AppiumDriverManager
        AppiumDriverManager driverManager = new AppiumDriverManager();

        try {
            driverManager.initializeDriver("android");
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppiumDriver driver = driverManager.getDriver(); // Get the driver instance

        // Set implicit wait (e.g., 10 seconds)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Initialize LoginPage, CartPage, and CheckoutPage with the driver
        LoginPage loginPage = new LoginPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        // Set objects in the shared context
        context.setDriver(driver);
        context.setLoginPage(loginPage);
        context.setCartPage(cartPage);
        context.setCheckoutPage(checkoutPage);

        // Log initialization
        System.out.println("Appium driver initialized successfully");
        System.out.println("LoginPage, CartPage, and CheckoutPage initialized");
    }

}
