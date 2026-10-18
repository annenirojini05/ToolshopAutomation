package listeners;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.ScreenshotUtils;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(
            ITestResult result
    ) {

        System.out.println(
                "TEST FAILED: "
                        + result.getName()
        );

        Object testInstance =
                result.getInstance();

        if (testInstance instanceof BaseTest) {

            BaseTest baseTest =
                    (BaseTest) testInstance;

            WebDriver driver =
                    baseTest.getDriver();

            ScreenshotUtils.captureScreenshot(
                    driver,
                    result.getName()
            );
        }
    }


    @Override
    public void onTestSuccess(
            ITestResult result
    ) {

        System.out.println(
                "TEST PASSED: "
                        + result.getName()
        );
    }


    @Override
    public void onTestSkipped(
            ITestResult result
    ) {

        System.out.println(
                "TEST SKIPPED: "
                        + result.getName()
        );
    }
}