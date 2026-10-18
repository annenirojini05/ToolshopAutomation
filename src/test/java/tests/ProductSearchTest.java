package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductPage;

public class ProductSearchTest extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    public void validProductSearchTest() {

        ProductPage productPage =
                new ProductPage(driver);

        productPage.searchProduct("Hammer");

        Assert.assertTrue(
                productPage.isProductDisplayed("Hammer"),
                "Expected product was not displayed"
        );
    }

    @Test(groups = {"regression"})
    public void invalidProductSearchTest() {

        ProductPage productPage =
                new ProductPage(driver);

        productPage.searchProduct(
                "ProductThatDoesNotExist12345"
        );

        Assert.assertTrue(
                productPage.isNoResultsMessageDisplayed(),
                "Expected no-results message was not displayed"
        );
    }
}