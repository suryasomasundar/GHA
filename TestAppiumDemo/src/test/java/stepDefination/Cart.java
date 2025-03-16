package stepDefination;


import android.pages.CartPage;
import io.cucumber.java.en.Given;
import utils.TestContext;

/**
 * Step definitions for cart test scenarios
 *
 * @author Somu
 * @since 15 Mar, 2025
 */
public class Cart {

    private TestContext context;

    public Cart(TestContext context) {
        this.context = context; // Inject the shared context
    }

    @Given("Navigate to the first product on the list")
    public void navigate_to_the_first_product_on_the_list() {
        CartPage cartPage = context.getCartPage();
        cartPage.selectProduct();
    }

    @Given("Add quantity {int}")
    public void add_quantity(Integer int1) {
        CartPage cartPage = context.getCartPage();
        cartPage.addQuantity();
    }

    @Given("Add to cart and go to cart")
    public void add_to_cart_and_go_to_cart() {
        CartPage cartPage = context.getCartPage();
        cartPage.goCart();
    }
}
