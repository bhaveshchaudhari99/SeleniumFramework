package seleniumFramework;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import seleniumFramework.HomePage;
import seleniumFramework.LandingPage;
import seleniumFramework.LoginPage;
import testComponents.BaseTest;

public class VerifyInvalidLogin extends BaseTest{

	@Test
	public void verifyLoginWithInvalidCreds() throws IOException
	{
		WebDriver driver = initBrowser();
		LandingPage lp = new LandingPage(driver);
		LoginPage login = lp.navigateToLoginPage();
		HomePage hp = login.LoginAction("yogesh@gmail.com", "Yogesh@1234");
		
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "https://tutorialsninja.com/demo/index.php?route=account/account";
		Assert.assertEquals(actualUrl, expectedUrl);
		tearDown();
	}
}