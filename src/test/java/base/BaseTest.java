package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

        driver =
                new ChromeDriver();

        driver.manage()
                .window()
                .maximize();

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