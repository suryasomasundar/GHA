package stepDefination;

import android.pages.CheckoutPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utils.TestContext;

/**
 * Step definitions for checkout test scenarios
 *
 * @author Somu
 * @since 15 Mar, 2025
 */

public class Checkout {

    private TestContext context;

    public Checkout(TestContext context) {
        this.context = context; // Inject the shared context
    }

    @Given("Complete checkout with Fullname {string} AddressLine1 {string} City {string} Zipcode {string} Country {string}")
    public void complete_checkout_with_fullname_address_line_city_zipcode_country(String fullname, String AddressLine1, String city, String zipCode, String country) {
        CheckoutPage checkoutPage = context.getCheckoutPage(); // Get CheckoutPage from the shared context
        checkoutPage.fillAddressDetail(fullname, AddressLine1, city, zipCode, country);

    }

    @Given("Click on {string}")
    public void click_on(String string) {
        CheckoutPage checkoutPage = context.getCheckoutPage();
        checkoutPage.goToPayment();
    }

    @Then("Verify Text {string} is shown")
    public void verify_text_is_shown(String string) {
        CheckoutPage checkoutPage = context.getCheckoutPage();
        checkoutPage.verifyText();

    }

    @Then("Verify {string} button is shown")
    public void verify_button_is_shown(String string) {
        CheckoutPage checkoutPage = context.getCheckoutPage();
        checkoutPage.verifyRevieworder();

    }
}
