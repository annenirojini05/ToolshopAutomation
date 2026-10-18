package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.WaitUtils;

public class CartPage {

    private final WebDriver driver;
    private final WaitUtils waitUtils;

    private final By cartProductName =
            By.cssSelector("[data-test='product-title']");

    private final By cartQuantity =
            By.cssSelector("[data-test='product-quantity']");

    private final By cartPrice =
            By.cssSelector("[data-test='product-price']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public String getCartProductName() {
        waitUtils.waitForVisible(cartProductName);

        return driver.findElement(cartProductName)
                .getText()
                .trim();
    }

    public String getCartQuantity() {
        waitUtils.waitForVisible(cartQuantity);

        return driver.findElement(cartQuantity)
                .getAttribute("value");
    }

    public String getCartPrice() {
        waitUtils.waitForVisible(cartPrice);

        return driver.findElement(cartPrice)
                .getText()
                .trim();
    }

    public boolean isProductDisplayedInCart(String expectedProduct) {
        return getCartProductName()
                .toLowerCase()
                .contains(expectedProduct.toLowerCase());
    }
}