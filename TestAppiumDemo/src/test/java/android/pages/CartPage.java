package android.pages;

import common.pages.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

/**
 * Defined Methods to interact with Cart Page
 *
 * @author Somu
 * @since 15 Mar, 2025
 */
public class CartPage extends BasePage {

    static By Backpack = By.xpath("//android.widget.TextView[@content-desc='store item text' and @text='Sauce Labs Backpack']");
    static By plusImage = By.xpath("//android.view.ViewGroup[@content-desc='counter plus button']/android.widget.ImageView");
    static By addToCart = By.xpath("//android.view.ViewGroup[@content-desc='Add To Cart button']");
    static By cartImage = By.xpath("//android.view.ViewGroup[@content-desc='cart badge']/android.widget.ImageView");
    static By proceedToCart = By.xpath("//android.view.ViewGroup[@content-desc='Proceed To Checkout button']");

    public CartPage(AppiumDriver driver) {
        super(driver);
    }

    public void selectProduct() {
        clickElement(Backpack);
    }

    public void addQuantity() {

        clickElement(plusImage);
        clickElement(addToCart);
    }

    public void goCart() {

        clickElement(cartImage);
        clickElement(proceedToCart);
    }
}
