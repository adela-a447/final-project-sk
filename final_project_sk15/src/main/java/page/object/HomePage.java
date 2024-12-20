package page.object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4300/posts/all";
    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        return wait.until(ExpectedConditions.urlToBe(page.object.HomePage.PAGE_URL));
    }
}
