package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductDetailsPage;
import pages.ProductPage;

public class CartTest extends BaseTest {

    @Test(groups = {"smoke", "ui-smoke","regression"})
    public void addProductToCartTest() {

        ProductPage productPage =
                new ProductPage(driver);

        productPage.searchProduct("Hammer");

        Assert.assertTrue(
                productPage.isProductDisplayed("Hammer"),
                "Expected product was not displayed"
        );

        productPage.clickProductByName("Hammer");

        ProductDetailsPage detailsPage =
                new ProductDetailsPage(driver);

        String selectedProduct =
                detailsPage.getProductName();

        String selectedPrice =
                detailsPage.getProductPrice();

        detailsPage.setQuantity("1");

        detailsPage.clickAddToCart();

        detailsPage.openCart();

        CartPage cartPage =
                new CartPage(driver);

        Assert.assertTrue(
                cartPage.isProductDisplayedInCart(selectedProduct),
                "Selected product was not found in the cart"
        );

        Assert.assertEquals(
                cartPage.getCartQuantity(),
                "1",
                "Incorrect cart quantity"
        );

        Assert.assertFalse(
                cartPage.getCartPrice().isEmpty(),
                "Cart price was not displayed"
        );

        System.out.println(
                "Selected Product: " + selectedProduct
        );

        System.out.println(
                "Selected Price: " + selectedPrice
        );
    }
}