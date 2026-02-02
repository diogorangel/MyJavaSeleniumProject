package steps;

import driver.DriverManager;
import pages.LoginPage;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import java.io.File; 
import java.io.IOException;

public class LoginSteps {
    LoginPage loginPage = new LoginPage(DriverManager.getDriver());

    @Given("I am on the SauceDemo login page")
    public void i_am_on_the_sauce_demo_login_page() throws IOException {
        DriverManager.getDriver().get("https://www.saucedemo.com/");
        DriverManager.waitSeconds(2);
        DriverManager.takeScreenshot("given_navigation");
    }

    @When("I enter valid credentials {string} and {string}")
    public void i_enter_valid_credentials(String user, String pass) throws IOException {
        loginPage.login(user, pass);
        DriverManager.waitSeconds(2);
        DriverManager.takeScreenshot("when_login");
    }

    @Then("I should be redirected to the products page with title {string}")
    public void i_should_be_redirected(String expectedTitle) throws IOException {
        Assert.assertEquals(expectedTitle, loginPage.getPageTitle());
        DriverManager.waitSeconds(2);
        DriverManager.takeScreenshot("then_redirect");
    }
}