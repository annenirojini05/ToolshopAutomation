package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import testdata.TestData;

public class RegistrationTest extends BaseTest {

    @Test(groups = {"regression"})
    public void validCustomerRegistrationTest() {

        RegistrationPage registrationPage =
                new RegistrationPage(driver);

        registrationPage.openRegistrationPage();

        registrationPage.registerCustomer(
                "Anne",
                "Test",
                "1995-05-15",
                "10 Test Street",
                "3000",
                "10",
                "Melbourne",
                "Victoria",
                "Australia",
                "0412345678",
                TestData.EMAIL,
                TestData.PASSWORD
        );

        Assert.assertTrue(
                registrationPage.isRegistrationSuccessful(),
                "Customer registration failed"
        );

        System.out.println(
                "Registered email: " + TestData.EMAIL
        );
    }
}