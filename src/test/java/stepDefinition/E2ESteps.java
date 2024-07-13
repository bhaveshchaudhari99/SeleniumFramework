package stepDefinition;

import java.io.IOException;

import org.testng.Assert;

import TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seleniumFramework.CartPage;
import seleniumFramework.CheckoutPage;
import seleniumFramework.HomePage;
import seleniumFramework.LandingPage;
import seleniumFramework.LoginPage;
import seleniumFramework.ProductDetails;
import seleniumFramework.ProductPage;

public class E2ESteps extends BaseTest {
	
	LoginPage login;
	HomePage hp;
	ProductPage pp;
	ProductDetails pd;
	CheckoutPage checkoutpage;

	@Given("Navigate to baseUrl")
	public void Navigate_to_baseUrl() throws IOException//always give same name as written in Given annotation
	{
		initBrowser();
		LandingPage lp = new LandingPage(driver);
		login = lp.navigateToLoginPage();
	}
	
	@Given("^Login with username (.+) and password (.+)$")
	public void Login_with_username_and_password(String username, String password)
	{
		hp = login.LoginAction(username, password);
	}
	
	@When("^added product (.+) to cart and checkout$")
	public void Added_product_to_cart_and_checkout(String productName)
	{
		pp = hp.clickOnCameraTab();
		System.out.println("clickOnCameraTab");
		pd = pp.addProductToCart(productName);	
		System.out.println("addProductToCart");
		String price = pd.getPrice();	
		Assert.assertEquals(price, "$98.00");
		System.out.println("price");
		CartPage cartpage = pd.setQtyandClick("2");	
		checkoutpage = cartpage.goToCheckotPage();
		checkoutpage.clickToCheckout();
	}
	
	@Then("verify the details {string}")
	public void verify_the_details(String string)
	{
		checkoutpage.closeErrorPopup();
		String mark = checkoutpage.getSuccessMessage();
		boolean isSuccess = mark.equalsIgnoreCase(string);
		Assert.assertTrue(isSuccess); 
		tearDown();
	}
}