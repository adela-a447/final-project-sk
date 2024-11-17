package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Header {
    public final WebDriver driver;


    public Header(WebDriver driver) {
        this.driver = driver;
    }


    public boolean checkSearchFieldVisibility() {
        WebElement searchField = driver.findElement(By.id("search-bar"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOf(searchField)).isDisplayed();
    }

    public void sendTextToTheSearchField(String text) {
        WebElement searchField = driver.findElement(By.id("search-bar"));
        searchField.clear();
        searchField.sendKeys(text);
    }

    public String getInputInTheSearchField() {
        WebElement searchField = driver.findElement(By.id("search-bar"));
        return searchField.getText();
    }

    public boolean isTextPresent(String input) {
        WebElement searchField = driver.findElement(By.id("search-bar"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.textToBePresentInElement(searchField,input));
    }

    public boolean isDropDownVisible() {
        WebElement dropDownMenu = driver.findElement(By.className("dropdown-container"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOf(dropDownMenu)).isDisplayed();
    }

    public String getUsersSuggestions() {
        WebElement userSuggestion = driver.findElement(By.className("post-user"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        return userSuggestion.getText();
    }

}
