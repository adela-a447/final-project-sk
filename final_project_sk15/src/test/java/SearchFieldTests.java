import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.object.Header;
import page.object.HomePage;
import page.object.LoginPage;

public class SearchFieldTests extends TestObjects {
    private static final Logger log = LoggerFactory.getLogger(SearchFieldTests.class);

    @Test(dataProvider = "registeredUsers")
    public void testTypingInSearchField(String email, String password, String user) {
        WebDriver driver = getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded(), "The URL is not correct");

        loginPage.logIn(email, password);
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isUrlLoaded());

        Header header = new Header(driver);
        Assert.assertTrue(header.checkSearchFieldVisibility(), "The element is not visible on the page!");
        header.sendTextToTheSearchField("kjhfy");
        Assert.assertTrue(header.isTextPresent(header.getInputInTheSearchField()));

        quitDriver();
    }

    @Test(dataProvider = "registeredUsers")
    public void testFindExistingUser(String email, String password, String user) {
        WebDriver driver = getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded(), "The URL is not correct");

        loginPage.logIn(user, password);
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isUrlLoaded());

        Header header = new Header(driver);
        Assert.assertTrue(header.checkSearchFieldVisibility(), "The element is not visible on the page!");
        header.sendTextToTheSearchField("AdelaAleksova123");

        Assert.assertTrue(header.isDropDownVisible());
        //Assert.assertTrue(header.getUsersSuggestions().contains("AdelaAleksova123"));
        //Assert.assertEquals(header.getUsersSuggestions(), "AdelaAleksova123");

        quitDriver();
    }
}
   