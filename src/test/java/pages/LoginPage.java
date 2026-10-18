package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.WaitUtils;

public class LoginPage {

    private final WebDriver driver;
    private final WaitUtils waitUtils;

    private final By signInLink =
            By.cssSelector("[data-test='nav-sign-in']");

    private final By emailField =
            By.cssSelector("[data-test='email']");

    private final By passwordField =
            By.cssSelector("[data-test='password']");

    private final By loginButton =
            By.cssSelector("[data-test='login-submit']");

    private final By loginError =
            By.cssSelector("[data-test='login-error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void openLoginPage() {
        waitUtils.waitForClickable(signInLink);
        driver.findElement(signInLink).click();

        waitUtils.waitForVisible(emailField);
    }

    public void enterEmail(String email) {
        waitUtils.waitForVisible(emailField);
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        waitUtils.waitForVisible(passwordField);
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        waitUtils.waitForClickable(loginButton);
        driver.findElement(loginButton).click();
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }

    public boolean isEmailFieldDisplayed() {
        waitUtils.waitForVisible(emailField);
        return driver.findElement(emailField).isDisplayed();
    }

    public boolean isLoginSuccessful() {
        return waitUtils.waitForUrlContains("/account");
    }

    public boolean isLoginErrorDisplayed() {
        waitUtils.waitForVisible(loginError);
        return driver.findElement(loginError).isDisplayed();
    }
}