import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.object.Header;
import page.object.HomePage;
import page.object.LoginPage;
import page.object.ProfilePage;

public class UserInteractionTests extends TestObjects{
    @Test(dataProvider = "registeredUsers")
    public void testOpenUserProfile(String email, String password, String user) {
        WebDriver driver = getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded(), "The URL is not correct");

        loginPage.logIn(email, password);
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isUrlLoaded());

        Header header = new Header(driver);
        Assert.assertTrue(header.checkSearchFieldVisibility(), "The element is not visible on the page!");
        header.sendTextToTheSearchField("test1");

        Assert.assertTrue(header.isDropDownVisible());
        header.clickOnUser();

        ProfilePage userPage = new ProfilePage(driver);
        Assert.assertTrue(userPage.isUrlLoaded());

    }

    @Test(dataProvider = "combinedProvider")
    public void testClickOnFollow(String email, String password, String existingUser) {
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

        header.clickOnFollowButton();
    }
}
