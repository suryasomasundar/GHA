package ios.pages;

import common.pages.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

/**
 * Defined Methods to interact with product Page
 *
 * @author Somu
 * @since 14 Mar, 2025
 */

public class ProductPage extends BasePage {

    public ProductPage(AppiumDriver driver) {
        super(driver);
    }

    By quantityIncreaseButton = By.xpath("//XCUIElementTypeOther[@name='counter plus button']");
    By addToCartButton = By.xpath("//XCUIElementTypeOther[@name='Add To Cart button']");

    public void increaseQuantity() {
        waitForElementToBeClickable(quantityIncreaseButton).click();
    }

    public void addToCart() {
        waitForElementToBeClickable(addToCartButton).click();
    }
}
