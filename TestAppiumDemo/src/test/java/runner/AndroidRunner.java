package runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * TestNG-based Cucumber runner for executing Android test scenarios.
 * Configures Cucumber options for feature files, step definitions, and reporting.
 *
 * <p>
 * Example usage:
 * <pre>
 * mvn clean test -Dcucumber.options="--tags @payment"
 * </pre>
 * </p>
 */

@CucumberOptions(
        tags = "@payment",
        features = {"src/test/resources/Features/"},
        glue = {"stepDefination", "utils"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json"
        }, monochrome = true)

public class AndroidRunner extends AbstractTestNGCucumberTests {
    // Inherits test execution logic from AbstractTestNGCucumberTests
}
