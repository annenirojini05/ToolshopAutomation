package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductDetailsPage;
import pages.ProductPage;

public class ProductDetailsTest extends BaseTest {

    @Test(groups = {"regression"})
    public void verifyProductDetailsTest() {

        ProductPage productPage =
                new ProductPage(driver);

        productPage.searchProduct("Hammer");

        Assert.assertTrue(
                productPage.isProductDisplayed("Hammer"),
                "Hammer product was not displayed"
        );

        productPage.clickProductByName("Hammer");

        ProductDetailsPage detailsPage =
                new ProductDetailsPage(driver);

        Assert.assertTrue(
                detailsPage.getProductName()
                        .toLowerCase()
                        .contains("hammer"),
                "Incorrect product name displayed"
        );

        Assert.assertFalse(
                detailsPage.getProductPrice().isEmpty(),
                "Product price was not displayed"
        );

        Assert.assertTrue(
                detailsPage.isProductDescriptionDisplayed(),
                "Product description was not displayed"
        );
    }
}