package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private final WebDriverWait wait;

    public WaitUtils(WebDriver driver) {

        this.wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(10)
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

    public boolean waitForUrlContains(String value) {

        return wait.until(
                ExpectedConditions
                        .urlContains(value)
        );
    }

    public void waitForPresence(By locator) {

        wait.until(
                ExpectedConditions
                        .presenceOfElementLocated(locator)
        );
    }

    public void waitForNumberOfElementsMoreThan(
            By locator,
            int number
    ) {

        wait.until(
                ExpectedConditions
                        .numberOfElementsToBeMoreThan(
                                locator,
                                number
                        )
        );
    }
}