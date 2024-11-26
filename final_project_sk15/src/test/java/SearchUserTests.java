import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.object.Header;
import page.object.HomePage;
import page.object.LoginPage;

public class SearchUserTests extends TestObjects{
    @Test(dataProvider = "combinedProvider")
    public void testSearchExistingUser(String email, String password, String existingUser) {
        WebDriver driver = getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded(), "The URL is not correct");

        loginPage.logIn(email, password);
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isUrlLoaded());

        Header header = new Header(driver);
        Assert.assertTrue(header.checkSearchFieldVisibility(), "The element is not visible on the page!");
        header.sendTextToTheSearchField(existingUser);

        Assert.assertTrue(header.isDropDownVisible());
        Assert.assertTrue(header.getUsersSuggestions().contains(existingUser));

    }

    @Test(dataProvider = "registeredUsers")
    public void testSearchNonExistentUser(String email, String password, String user) {
        WebDriver driver = getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded(), "The URL is not correct");

        loginPage.logIn(user, password);
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isUrlLoaded());

        Header header = new Header(driver);
        Assert.assertTrue(header.checkSearchFieldVisibility(), "The element is not visible on the page!");
        String randomText = generateRandomAlphabeticString(4, 14);
        header.sendTextToTheSearchField(randomText);

        Assert.assertTrue(!header.getUsersSuggestions().contains(randomText) || !header.isDropDownVisible());

    }

    @Test(dataProvider = "registeredUsers")
    public void testSearchYourSelf(String email, String password, String user) {
        WebDriver driver = getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded(), "The URL is not correct");

        loginPage.logIn(email, password);
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isUrlLoaded());

        Header header = new Header(driver);
        Assert.assertTrue(header.checkSearchFieldVisibility(), "The element is not visible on the page!");
        header.sendTextToTheSearchField(user);

        Assert.assertFalse(header.getUsersSuggestions().contains(user));
    }

    private String generateRandomAlphabeticString(int minLengthInclusive, int maxLengthInclusive) {
        return RandomStringUtils.randomAlphanumeric(minLengthInclusive, maxLengthInclusive);
    }
}
