package ios.pages;

import common.pages.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

/**
 * Defined Methods to interact with Checkout Page
 *
 * @author Somu
 * @since 14 Mar, 2025
 */

public class CheckoutPage extends BasePage {

    public CheckoutPage(AppiumDriver driver) {
        super(driver);
    }

    public By emailField = By.xpath("//XCUIElementTypeTextField[@name='Username input field']");
    By passwordField = By.xpath("//XCUIElementTypeSecureTextField[@name='Password input field']");
    By loginButton = By.xpath("//XCUIElementTypeOther[@name='Login button']");
    By fullNameField = By.xpath("//XCUIElementTypeTextField[@name='Full Name* input field']");
    By addressLine1Field = By.xpath("//XCUIElementTypeTextField[@name='Address Line 1* input field']");
    By cityField = By.xpath("//XCUIElementTypeTextField[@name='City* input field']");
    By zipCodeField = By.xpath("//XCUIElementTypeTextField[@name='Zip Code* input field']");
    By countryField = By.xpath("//XCUIElementTypeTextField[@name='Country* input field']");
    By paymentButton = By.xpath("//XCUIElementTypeOther[@name='To Payment button']");
    By paymentMethodText = By.xpath("//XCUIElementTypeStaticText[@name='Enter a payment method']");
    By reviewOrderButton = By.xpath("//XCUIElementTypeOther[@name='Review Order button']");

    // Reusable method to enter text and dismiss the keyboard
    private void enterTextAndDismissKeyboard(By locator, String text) {
        enterText(locator, text);
        waitForElementToBeClickable(locator).sendKeys(Keys.RETURN);
    }

    public void enterEmail(String email) {
        enterText(emailField, email);
    }

    public void enterPassword(String password) {
        enterTextAndDismissKeyboard(passwordField, password);
    }

    public void clickLoginButton() {
        waitForElementToBeClickable(loginButton).click();
    }

    public void enterAddressDetails(String fullName, String addressLine1, String city, String zipCode, String country) {
        enterText(fullNameField, fullName);
        enterText(addressLine1Field, addressLine1);
        enterTextAndDismissKeyboard(cityField, city);
        enterText(zipCodeField, zipCode);
        enterTextAndDismissKeyboard(countryField, country);
    }

    public void clickPaymentButton() {
        waitForElementToBeClickable(paymentButton).click();
    }

    public void verifyPaymentMethodAndReviewOrder() {
        Assert.assertTrue(waitForElementToBeClickable(paymentMethodText).isDisplayed(), "Text 'Enter a payment method' is not shown!");
        Assert.assertTrue(waitForElementToBeClickable(reviewOrderButton).isDisplayed(), "Review order button is not shown!");
    }
}
