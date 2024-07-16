package seleniumFramework;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import seleniumFramework.CartPage;
import seleniumFramework.CheckoutPage;
import seleniumFramework.HomePage;
import seleniumFramework.LandingPage;
import seleniumFramework.LoginPage;
import seleniumFramework.ProductDetails;
import seleniumFramework.ProductPage;
import testComponents.BaseTest;

public class E2ETest2 extends BaseTest{

	@Test(dataProvider = "data")
	public void endToEndTest(String username, String password) throws IOException
	{
		WebDriver driver = initBrowser();
		
		//Landing Page
		LandingPage lp = new LandingPage(driver);
		LoginPage login = lp.navigateToLoginPage();
		System.out.println(username+" "+password);
		HomePage hp = login.LoginAction(username, password);
		
		//Home page
		ProductPage pp = hp.clickOnCameraTab();
		
		//Product Page
		ProductDetails pd = pp.addProductToCart("Nikon D300");		
		String price = pd.getPrice();	
		//First Validation
		Assert.assertEquals(price, "$98.00");
		
		//Reset cart items
		//pd.resetPrice();
		
		CartPage cartpage = pd.setQtyandClick("2");
		
		//Cart Page
		String netPrice = cartpage.getTotalPrice();
		System.out.println("Total Price:- "+netPrice);	
		//Second-price Validation
		//Assert.assertEquals(netPrice, "$196.00");
		
		CheckoutPage checkoutpage = cartpage.goToCheckotPage();
		checkoutpage.clickToCheckout();
		checkoutpage.closeErrorPopup();
		//Third Validation
		String mark = checkoutpage.getSuccessMessage();
		boolean isSuccess = mark.equalsIgnoreCase("***");
		Assert.assertTrue(isSuccess);
		 
		tearDown();
	}
	
	@DataProvider(name="data")
	public Object[][] getData()
	{
		return new Object[][] {
			{"yog@gmail.com", "Yogesh@12345"},{"lallu@gmail.com", "Lallu@12345"}
		};
	}
}