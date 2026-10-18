package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.WaitUtils;

public class ProductDetailsPage {

    private final WebDriver driver;
    private final WaitUtils waitUtils;

    private final By productName =
            By.cssSelector("[data-test='product-name']");

    private final By productPrice =
            By.cssSelector("[data-test='unit-price']");

    private final By productDescription =
            By.cssSelector("[data-test='product-description']");

    private final By quantityField =
            By.cssSelector("[data-test='quantity']");

    private final By addToCartButton =
            By.cssSelector("[data-test='add-to-cart']");

    private final By cartLink =
            By.cssSelector("[data-test='nav-cart']");

    private final By successToast =
            By.cssSelector(".toast-success");

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public String getProductName() {
        waitUtils.waitForVisible(productName);
        return driver.findElement(productName).getText().trim();
    }

    public String getProductPrice() {
        waitUtils.waitForVisible(productPrice);
        return driver.findElement(productPrice).getText().trim();
    }

    public boolean isProductDescriptionDisplayed() {
        waitUtils.waitForVisible(productDescription);
        return driver.findElement(productDescription).isDisplayed();
    }

    public void setQuantity(String quantity) {
        waitUtils.waitForVisible(quantityField);

        driver.findElement(quantityField).clear();
        driver.findElement(quantityField).sendKeys(quantity);
    }

    public void clickAddToCart() {

        waitUtils.waitForClickable(addToCartButton);

        driver.findElement(addToCartButton).click();

        waitUtils.waitForVisible(successToast);
    }

    public void openCart() {

        waitUtils.waitForInvisible(successToast);

        waitUtils.waitForClickable(cartLink);

        driver.findElement(cartLink).click();
    }
}