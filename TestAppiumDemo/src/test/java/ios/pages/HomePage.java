package ios.pages;

import common.pages.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

/**
 * selecting the first product from home page
 *
 * @author Somu
 * @since 14 Mar, 2025
 */

public class HomePage extends BasePage {

    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    By firstProduct = By.xpath("//XCUIElementTypeStaticText[@label='Sauce Labs Backpack']");

    public void selectFirstProduct() {
        waitForElementToBeClickable(firstProduct).click();
    }
}
