package android.pages;

import common.pages.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

/**
 * Defined Methods to interact with Login Page
 *
 * @author Somu
 * @since 14 Mar, 2025
 */

public class LoginPage extends BasePage {

    static By username = By.xpath("//android.widget.EditText[@content-desc='Username input field']");
    static By password = By.xpath("//android.widget.EditText[@content-desc='Password input field']");
    static By loginButton = By.xpath("(//android.widget.TextView[@text='Login'])[2]");

    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    public void login(String userName, String Password) {
        enterText(username, userName);
        enterText(password, Password);
        clickElement(loginButton);
    }
}
