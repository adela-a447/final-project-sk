package page.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.refreshed;

public class Header {
    public final WebDriver driver;
    @FindBy(id = "search-bar")
    private WebElement searchField;
    @FindBy(className = "dropdown-container")
    private WebElement dropDownMenu;
    @FindBy(css = ".dropdown-user .post-user")
    private WebElement suggestedUsers;
    @FindBy(css = ".btn-primary")
    private WebElement followButton;


    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public boolean checkSearchFieldVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(searchField));
        return searchField.isDisplayed();
    }

    public void sendTextToTheSearchField(String text) {
        searchField.clear();
        searchField.sendKeys(text);
    }

    public String getInputInTheSearchField() {
        String searchText = searchField.getAttribute("value");
        return searchText;
    }

    public boolean isTextPresent() {
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        ExpectedCondition<WebElement> dropDown = ExpectedConditions.visibilityOf(dropDownMenu);
        wait.until(refreshed(dropDown));
        return dropDownMenu.isDisplayed();
    }

    public ArrayList<String> getUsersSuggestions() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        ExpectedCondition<List<WebElement>> condition = ExpectedConditions.visibilityOfAllElements(suggestedUsers);
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
        ExpectedCondition<List<WebElement>> condition = ExpectedConditions.visibilityOfAllElements(suggestedUsers);
        List<WebElement> suggestions = wait.until(refreshed(condition));
        suggestions.getFirst().click();
    }

    public void clickOnFollowButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
        ExpectedCondition<WebElement> button = ExpectedConditions.elementToBeClickable(followButton);
        followButton = wait.until(refreshed(button));
        followButton.click();
    }
}
