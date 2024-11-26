package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.refreshed;

public class Header {
    public final WebDriver driver;


    public Header(WebDriver driver) {
        this.driver = driver;
    }

    public boolean checkSearchFieldVisibility() {
        WebElement searchField = driver.findElement(By.id("search-bar"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(searchField));
        return searchField.isDisplayed();
    }

    public void sendTextToTheSearchField(String text) {
        WebElement searchField = driver.findElement(By.id("search-bar"));
        searchField.clear();
        searchField.sendKeys(text);
    }

    public String getInputInTheSearchField() {
        WebElement searchField = driver.findElement(By.id("search-bar"));
        String searchText = searchField.getAttribute("value");
        return searchText;
    }

    public boolean isTextPresent() {
        WebElement searchField = driver.findElement(By.id("search-bar"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(searchField));
        String text = searchField.getAttribute("value");
        boolean isTextPresent = false;
        if (!text.isEmpty()) {
            isTextPresent = true;
        }
        return isTextPresent;
    }

    public boolean isDropDownVisible() {
        WebElement dropDownMenu = driver.findElement(By.className("dropdown-container"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        ExpectedCondition<WebElement> dropDown = ExpectedConditions.visibilityOf(dropDownMenu);
        wait.until(refreshed(dropDown));
        return dropDownMenu.isDisplayed();
    }

    public ArrayList<String> getUsersSuggestions() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        ExpectedCondition<List<WebElement>> condition = ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".dropdown-user .post-user"));
        List<WebElement> suggestions = wait.until(refreshed(condition));
        ArrayList<String> userNames = new ArrayList<>();
        for (WebElement suggestion : suggestions) {
            String userName = suggestion.getText();
            userNames.add(userName);
        }
        return userNames;
    }

    public void clickOnUser() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        ExpectedCondition<List<WebElement>> condition = ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".dropdown-user .post-user"));
        List<WebElement> suggestions = wait.until(refreshed(condition));
        suggestions.getFirst().click();
    }

    public void clickOnFollowButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
        ExpectedCondition<WebElement> button = ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".btn-primary")));
        WebElement followButton = wait.until(refreshed(button));
        followButton.click();
    }

}
