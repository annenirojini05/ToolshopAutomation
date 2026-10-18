package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import testdata.TestData;

public class LoginTest extends BaseTest {

    @Test(dependsOnMethods = "tests.RegistrationTest.validCustomerRegistrationTest" , groups = {"regression"})
    public void validLoginTest() {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.openLoginPage();

        loginPage.login(
                TestData.EMAIL,
                TestData.PASSWORD
        );

        Assert.assertTrue(
                loginPage.isLoginSuccessful(),
                "Valid customer login failed"
        );
    }
}