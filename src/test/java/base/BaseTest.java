package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.ConfigReader;

public class BaseTest {

    protected WebDriver driver;


    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        String baseUrl =
                ConfigReader.getProperty(
                        "baseUrl"
                );

        boolean headless =
                Boolean.parseBoolean(
                        System.getProperty(
                                "headless",
                                "false"
                        )
                );

        ChromeOptions options =
                new ChromeOptions();

        if (headless) {

            options.addArguments(
                    "--headless=new"
            );

            options.addArguments(
                    "--no-sandbox"
            );

            options.addArguments(
                    "--disable-dev-shm-usage"
            );

            options.addArguments(
                    "--window-size=1366,768"
            );
        }

        driver =
                new ChromeDriver(
                        options
                );

        if (!headless) {

            driver.manage()
                    .window()
                    .maximize();
        }

        driver.get(
                baseUrl
        );
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        if (driver != null) {

            driver.quit();
        }
    }


    public WebDriver getDriver() {

        return driver;
    }
}