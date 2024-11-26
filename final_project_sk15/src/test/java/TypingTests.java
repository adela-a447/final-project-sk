import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.object.Header;
import page.object.HomePage;
import page.object.LoginPage;

public class TypingTests extends TestObjects {
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
        header.sendTextToTheSearchField(generateRandomAlphabeticString(4,12));
        Assert.assertTrue(header.isTextPresent());
    }

    @Test(dataProvider = "registeredUsers")
    public void testSendAVeryLongString(String email, String password, String user) {
        WebDriver driver = getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded(), "The URL is not correct");

        loginPage.logIn(email, password);
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isUrlLoaded());

        Header header = new Header(driver);
        Assert.assertTrue(header.checkSearchFieldVisibility(), "The element is not visible on the page!");
        String input = generateRandomAlphabeticString(122, 147);
        header.sendTextToTheSearchField(input);
        Assert.assertEquals(header.getInputInTheSearchField().length(), input.length());
    }

    private String generateRandomAlphabeticString(int minLengthInclusive, int maxLengthInclusive) {
        return RandomStringUtils.randomAlphanumeric(minLengthInclusive, maxLengthInclusive);
    }
}
