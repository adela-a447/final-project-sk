package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4300/users/login";
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
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
        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("sign-in-button")));
        signInButton.click();
    }

    public void populatePassword(String password) {
        WebElement passwordField = driver.findElement(By.id("defaultLoginFormPassword"));
        passwordField.sendKeys(password);
    }

    public void populateEmail(String email) {
        WebElement emailField = driver.findElement(By.id("defaultLoginFormUsername"));
        emailField.sendKeys(email);
    }
}
