import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.time.Duration;

public class TestObjects {
    public static final String SCREENSHOTS_DIR = "src\\test\\resources\\screenshots\\";
    private WebDriver driver;

    @BeforeSuite
    protected final void setupTestSuite() throws IOException {
        cleanDirectory(SCREENSHOTS_DIR);
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
                {"sdfgty6777@gmail.com", "adela.Aleksova123", "AdelaAleksova123"},
                {"asd474774@abv.bg", "Asd4747", "asd4747"},
                {"Proba1234", "Proba1234", "Proba1234"}
        };
    }

    @DataProvider(name = "existingUsers")
    public Object[][] existingUsers() {
        return new Object[][]{
                {"Qwer2345"},
                {"Wert567"},
                {"TestUserUser"}
        };
    }

    @DataProvider(name = "combinedProvider")
    public Object[][] combinedProvider(Method method) {
        Object[][] dataProvider1 = registeredUsers();
        Object[][] dataProvider2 = existingUsers();

        Object[][] combined = new Object[dataProvider1.length][];
        for (int i = 0; i < dataProvider1.length; i++) {
            combined[i] = new Object[]{dataProvider1[i][0], dataProvider1[i][1], dataProvider2[i % dataProvider2.length][0]};
        }
        return combined;
    }

    @AfterSuite
    private void takeScreenshot(ITestResult testResult) throws IOException {
        if (ITestResult.FAILURE == testResult.getStatus()) {
            try {
                TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
                File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
                String testName = testResult.getName();
                FileUtils.copyFile(screenshot, new File(SCREENSHOTS_DIR.concat(testName).concat(".jpg")));
            } catch (IOException e) {
                System.out.println("Unable to create a screenshot file: " + e.getMessage());
            }
        }
    }

    private void cleanDirectory(String directoryPath) throws IOException {
        File directory = new File(directoryPath);

        Assert.assertTrue(directory.isDirectory(), "Invalid directory!");

        FileUtils.cleanDirectory(directory);
        String[] fileList = directory.list();
        if (fileList != null && fileList.length == 0) {
            System.out.printf("All files are deleted in Directory: %s%n", directoryPath);
        } else {
            System.out.printf("Unable to delete the files in Directory:%s%n", directoryPath);
        }
    }


    @AfterMethod
    protected final void tearDownTest(ITestResult testResult) throws IOException {
        takeScreenshot(testResult);
        quitDriver();
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
