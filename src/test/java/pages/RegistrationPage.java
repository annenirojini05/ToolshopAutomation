package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.WaitUtils;

public class RegistrationPage {

    private final WebDriver driver;
    private final WaitUtils waitUtils;

    // Locators
    private final By signInLink =
            By.cssSelector("[data-test='nav-sign-in']");

    private final By registerLink =
            By.cssSelector("[data-test='register-link']");

    private final By firstNameField =
            By.cssSelector("[data-test='first-name']");

    private final By lastNameField =
            By.cssSelector("[data-test='last-name']");

    private final By dateOfBirthField =
            By.cssSelector("[data-test='dob']");

    private final By streetField =
            By.cssSelector("[data-test='street']");

    private final By postalCodeField =
            By.cssSelector("[data-test='postal_code']");

    private final By cityField =
            By.cssSelector("[data-test='city']");

    private final By stateField =
            By.cssSelector("[data-test='state']");

    private final By countryDropdown =
            By.cssSelector("[data-test='country']");

    private final By phoneField =
            By.cssSelector("[data-test='phone']");

    private final By emailField =
            By.cssSelector("[data-test='email']");

    private final By passwordField =
            By.cssSelector("[data-test='password']");

    private final By registerButton =
            By.cssSelector("[data-test='register-submit']");


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }


    public void openRegistrationPage() {

        waitUtils.waitForClickable(signInLink);
        driver.findElement(signInLink).click();

        waitUtils.waitForClickable(registerLink);
        driver.findElement(registerLink).click();

        waitUtils.waitForVisible(firstNameField);
    }


    public void enterFirstName(String firstName) {
        waitUtils.waitForVisible(firstNameField);
        driver.findElement(firstNameField).sendKeys(firstName);
    }


    public void enterLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }


    public void enterDateOfBirth(String dob) {
        driver.findElement(dateOfBirthField).sendKeys(dob);
    }


    public void enterStreet(String street) {
        driver.findElement(streetField).sendKeys(street);
    }


    public void enterPostalCode(String postalCode) {
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }


    public void enterCity(String city) {
        driver.findElement(cityField).sendKeys(city);
    }


    public void enterState(String state) {
        driver.findElement(stateField).sendKeys(state);
    }


    public void selectCountry(String countryVisibleText) {

        org.openqa.selenium.support.ui.Select select =
                new org.openqa.selenium.support.ui.Select(
                        driver.findElement(countryDropdown)
                );

        select.selectByVisibleText(countryVisibleText);
    }


    public void enterPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }


    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }


    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }


    public void clickRegister() {

        waitUtils.waitForClickable(registerButton);
        driver.findElement(registerButton).click();
    }


    public void registerCustomer(
            String firstName,
            String lastName,
            String dob,
            String street,
            String postalCode,
            String houseNumber,
            String city,
            String state,
            String country,
            String phone,
            String email,
            String password
    ) {

        enterFirstName(firstName);
        enterLastName(lastName);
        enterDateOfBirth(dob);
        selectCountry(country);
        enterPostalCode(postalCode);
        enterHouseNumber(houseNumber);
        enterStreet(street);
        enterCity(city);
        enterState(state);
        enterPhone(phone);
        enterEmail(email);
        enterPassword(password);
        clickRegister();
    }

    public boolean isRegistrationSuccessful() {
        return waitUtils.waitForUrlContains("/auth/login");
    }

    private final By houseNumberField =
            By.cssSelector("[data-test='house_number']");

    public void enterHouseNumber(String houseNumber) {
        waitUtils.waitForVisible(houseNumberField);
        driver.findElement(houseNumberField).clear();
        driver.findElement(houseNumberField).sendKeys(houseNumber);
    }
}