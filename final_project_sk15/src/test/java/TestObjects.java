import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.time.Duration;

public class TestObjects {
    private WebDriver driver;

    @BeforeSuite
    protected final void setupTestSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    protected final void setUpTest() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @DataProvider(name = "registeredUsers")
    public Object[][] registeredUsers() {
        return new Object[][]{
               // {"sdfgty6777@gmail.com", "adela.Aleksova123", "AdelaAleksova123"},
                {"asd474774@abv.bg", "Asd4747", "asd4747"},
               // {"Proba1234", "Proba1234", "Proba1234"}
        };
    }

    protected void quitDriver() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
