package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductDetailsPage;
import pages.ProductPage;

public class ToolshopE2ETest extends BaseTest {

    @Test(groups = {"regression"})
    public void productPurchaseFlowTest() {

        ProductPage productPage =
                new ProductPage(driver);

        // Step 1 - Search
        productPage.searchProduct("Hammer");

        Assert.assertTrue(
                productPage.isProductDisplayed("Hammer"),
                "Expected product was not displayed"
        );


        // Step 2 - Select product
        productPage.clickProductByName("Hammer");

        ProductDetailsPage detailsPage =
                new ProductDetailsPage(driver);

        String selectedProduct =
                detailsPage.getProductName();

        String selectedPrice =
                detailsPage.getProductPrice();


        // Step 3 - Validate details
        Assert.assertTrue(
                selectedProduct.toLowerCase().contains("hammer"),
                "Incorrect product details displayed"
        );

        Assert.assertFalse(
                selectedPrice.isEmpty(),
                "Product price was not displayed"
        );


        // Step 4 - Add to cart
        detailsPage.setQuantity("1");

        detailsPage.clickAddToCart();

        detailsPage.openCart();


        // Step 5 - Validate cart
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
    }
}