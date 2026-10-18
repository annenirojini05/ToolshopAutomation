package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public WaitUtils(WebDriver driver) {

        this.driver = driver;

        this.wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(30)
                );
    }


    public void waitForPageLoad() {

        wait.until(
                webDriver ->
                        ((JavascriptExecutor) webDriver)
                                .executeScript(
                                        "return document.readyState"
                                )
                                .equals("complete")
        );
    }


    public void waitForVisible(By locator) {

        wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(locator)
        );
    }


    public void waitForClickable(By locator) {

        wait.until(
                ExpectedConditions
                        .elementToBeClickable(locator)
        );
    }


    public void waitForInvisible(By locator) {

        wait.until(
                ExpectedConditions
                        .invisibilityOfElementLocated(locator)
        );
    }


    public boolean waitForUrlContains(String text) {

        return wait.until(
                ExpectedConditions
                        .urlContains(text)
        );
    }
}