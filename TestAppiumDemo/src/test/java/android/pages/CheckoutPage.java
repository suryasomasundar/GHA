package android.pages;

import common.pages.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

/**
 * Defined Methods to interact with Checkout Page
 *
 * @author Somu
 * @since 15 Mar, 2025
 */

public class CheckoutPage extends BasePage {

    static By fullname = By.xpath("//android.widget.EditText[@content-desc='Full Name* input field']");
    static By addressLine1 = By.xpath("//android.widget.EditText[@content-desc='Address Line 1* input field']");
    static By City = By.xpath("//android.widget.EditText[@content-desc='City* input field']");
    static By zip = By.xpath("//android.widget.EditText[@content-desc='Zip Code* input field']");
    static By country = By.xpath("//android.widget.EditText[@content-desc='Country* input field']");
    static By paymentButton = By.xpath("//android.view.ViewGroup[@content-desc='To Payment button']");
    static By reviewButton = By.xpath("//android.widget.TextView[@text='Review Order']");
    static By payMentText = By.xpath("//android.widget.TextView[@text='Enter a payment method']");

    public CheckoutPage(AppiumDriver driver) {
        super(driver);
    }

    public void fillAddressDetail(String firstName, String addressline1, String city, String zipCode, String Country) {
        enterText(fullname, firstName);
        enterText(addressLine1, addressline1);
        enterText(City, city);
        enterText(zip, zipCode);
        enterText(country, Country);
    }

    public void goToPayment() {
        clickElement(paymentButton);
    }

    public void verifyText() {
        findElement(payMentText);
    }

    public void verifyRevieworder() {
        findElement(reviewButton);
    }
}
