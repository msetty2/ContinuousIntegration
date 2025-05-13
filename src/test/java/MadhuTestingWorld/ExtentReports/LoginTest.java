package MadhuTestingWorld.ExtentReports;

import org.testng.Assert;
import org.testng.annotations.Test;

import MadhuTestingWordl.ExtentReports.Utilities.DriverFactory;
import MadhuTestingWorld.ExtentReports.pages.LoginPage;

public class LoginTest {

	@Test(description = "Valid login with registered user credentials",  retryAnalyzer = MadhuTestingWorld.ExtentReports.listeners.RetryAnalyzer.class)
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.goToLoginPage();
        loginPage.login("rahulshettyacademy11@gmail.com", "Rahulshetty!1");
        loginPage.verifyLoginSuccess();
        //Assert.assertEquals(1, 2);
    }
}
