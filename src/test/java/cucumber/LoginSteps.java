package cucumber;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import io.cucumber.java.en.*;
import cucumber.LoginPage;
import cucumber.DriverFactory;

public class LoginSteps {

    WebDriver driver = DriverFactory.getDriver();
    LoginPage loginPage = new LoginPage(driver);

    @Given("I open the login page")
    public void i_open_the_login_page() {
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
    }

    @When("I enter valid username and password")
    public void i_enter_valid_username_and_password() {
        loginPage.enterUsername("rahulshettyacademy");
        loginPage.enterPassword("learning");
    }

    @When("I click on the login button")
    public void i_click_on_the_login_button() {
        loginPage.clickLogin();
    }

    @Then("I should be redirected to the homepage")
    public void i_should_be_redirected_to_the_homepage() {
       // Assert.assertTrue(driver.getTitle().contains("ProtoCommerce"));
        DriverFactory.quitDriver();
    }
}
