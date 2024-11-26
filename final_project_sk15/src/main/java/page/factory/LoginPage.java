package page.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4300/users/login";
    private final WebDriver driver;
    @FindBy(id = "sign-in-button")
    private WebElement signInButton;
    @FindBy(id = "defaultLoginFormPassword")
    private WebElement passwordField;
    @FindBy(id = "defaultLoginFormUsername")
    private WebElement emailField;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateTo() {
        this.driver.get(PAGE_URL);
    }

    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlToBe(LoginPage.PAGE_URL));
    }

    public void logIn(String email, String password) {
        populateEmail(email);
        populatePassword(password);
        clickSignIn();
    }

    public void clickSignIn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
    }

    public void populatePassword(String password) {
        passwordField.sendKeys(password);
    }

    public void populateEmail(String email) {
        emailField.sendKeys(email);
    }
}
