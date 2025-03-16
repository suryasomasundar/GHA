package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Class to implement retry mechanism
 *
 * @author Somu
 * @since 14 Mar, 2025
 */

public class RetryUtil {

    public static void enterEmailWithRetry(AppiumDriver driver, String email, By locator, int retries) {
        boolean success = false;

        for (int i = 0; i < retries; i++) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(locator));

                emailField.clear();
                for (char c : email.toCharArray()) {
                    emailField.sendKeys(String.valueOf(c));
                    Thread.sleep(100);
                }

                String enteredText = emailField.getAttribute("value");
                if (!enteredText.equals(email)) {
                    emailField.clear();
                    emailField.sendKeys(email);
                }

                success = true;
                break;
            } catch (Exception e) {
                if (i == retries - 1) {
                    throw new RuntimeException("Failed to set email after " + retries + " retries.");
                }
            }
        }
    }
}
