package utils;

import android.pages.CartPage;
import android.pages.CheckoutPage;
import android.pages.LoginPage;
import io.appium.java_client.AppiumDriver;

/**
 * TestContext class to manage shared state across test steps.
 * Holds instances of AppiumDriver, LoginPage, CartPage, and CheckoutPage
 *
 * @author Somu
 * @since 15 Mar, 2025
 */
public class TestContext {

    private AppiumDriver driver;
    private LoginPage loginPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    // Getters and Setters
    public AppiumDriver getDriver() {
        return driver;
    }

    public void setDriver(AppiumDriver driver) {
        this.driver = driver;
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    public CartPage getCartPage() {
        return cartPage;
    }

    public void setCartPage(CartPage cartPage) {
        this.cartPage = cartPage;
    }

    public CheckoutPage getCheckoutPage() {
        return checkoutPage;
    }

    public void setCheckoutPage(CheckoutPage checkoutPage) {
        this.checkoutPage = checkoutPage;
    }
}
