package ios.pages;

import common.pages.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

/**
 * Defined Methods to interact with Cart Page
 *
 * @author Somu
 * @since 14 Mar, 2025
 */


public class CartPage extends BasePage {

    public CartPage(AppiumDriver driver) {
        super(driver);
    }

    By cartIcon = By.xpath("//XCUIElementTypeButton[@name='tab bar option cart']");
    By proceedCheckoutButton = By.xpath("//XCUIElementTypeOther[@name='Proceed To Checkout button']");

    public void goToCart() {
        waitForElementToBeClickable(cartIcon).click();
    }

    public void proceedToCheckout() {
        waitForElementToBeClickable(proceedCheckoutButton).click();
    }
}
