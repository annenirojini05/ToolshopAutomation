package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.ScreenshotUtils;
import utilities.WaitUtils;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.List;

public class ProductPage {

    private final WebDriver driver;
    private final WaitUtils waitUtils;

    private final By searchField =
            By.cssSelector("[data-test='search-query']");

    private final By searchButton =
            By.cssSelector("[data-test='search-submit']");

    private final By productNames =
            By.cssSelector("[data-test='product-name']");

    private final By noResultsMessage =
            By.cssSelector("[data-test='no-results']");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void enterSearchTerm(String productName) {

        waitUtils.waitForPageLoad();

        System.out.println(
                "Search elements found: "
                        + driver.findElements(searchField).size()
        );

        ScreenshotUtils.captureScreenshot(
                driver,
                "before_product_search"
        );

        waitUtils.waitForVisible(searchField);

        driver.findElement(searchField).clear();

        driver.findElement(searchField)
                .sendKeys(productName);
    }

    public void clickSearch() {

        waitUtils.waitForClickable(searchButton);

        driver.findElement(searchButton).click();
    }

    public void searchProduct(String productName) {

        enterSearchTerm(productName);

        clickSearch();
    }

    public List<WebElement> getDisplayedProducts() {

        waitUtils.waitForVisible(productNames);

        return driver.findElements(productNames);
    }

    public boolean isProductDisplayed(String expectedProduct) {

        for (int attempt = 0; attempt < 3; attempt++) {

            try {
                waitUtils.waitForVisible(productNames);

                List<WebElement> products =
                        driver.findElements(productNames);

                for (WebElement product : products) {

                    if (product.getText()
                            .trim()
                            .toLowerCase()
                            .contains(expectedProduct.toLowerCase())) {

                        return true;
                    }
                }

            } catch (StaleElementReferenceException e) {

                // Retry because the product list refreshed
            }
        }

        return false;
    }

    public boolean isNoResultsMessageDisplayed() {

        waitUtils.waitForVisible(noResultsMessage);

        return driver.findElement(noResultsMessage).isDisplayed();
    }

    public void clickProductByName(String productName) {

        By product =
                By.xpath(
                        "//*[contains(@data-test,'product') " +
                                "and contains(normalize-space(),'" +
                                productName + "')]"
                );

        waitUtils.waitForClickable(product);

        driver.findElement(product).click();
    }
}