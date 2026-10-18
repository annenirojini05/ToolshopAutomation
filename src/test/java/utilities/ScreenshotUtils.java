package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenshotUtils {

    public static String captureScreenshot(
            WebDriver driver,
            String testName
    ) {

        if (driver == null) {
            System.out.println(
                    "Screenshot not captured because driver is null."
            );
            return null;
        }

        try {

            File source =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE);

            Path screenshotFolder =
                    Paths.get(
                            "target",
                            "screenshots"
                    );

            Files.createDirectories(
                    screenshotFolder
            );

            String fileName =
                    testName
                            + "_"
                            + System.currentTimeMillis()
                            + ".png";

            Path destination =
                    screenshotFolder.resolve(
                            fileName
                    );

            Files.copy(
                    source.toPath(),
                    destination
            );

            System.out.println(
                    "Screenshot saved: "
                            + destination
            );

            return destination.toString();

        } catch (IOException e) {

            System.out.println(
                    "Unable to capture screenshot: "
                            + e.getMessage()
            );

            return null;
        }
    }
}