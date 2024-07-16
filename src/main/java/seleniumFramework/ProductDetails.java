package seleniumFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.BasePageClass;

public class ProductDetails extends BasePageClass{
	
	WebDriver driver;

	//Advanced xpath concept
	// "/.." is used for back to parent tag 
	@FindBy(xpath = "//h1[text()='Nikon D300']/..//following-sibling::ul[@class='list-unstyled'][2]//h2")
	private WebElement price;
	
	@FindBy(id = "cart-total")
	private WebElement cartTotal;
	
	@FindBy(xpath = "//button[@title='Remove']")
	private WebElement removeBtn;
	
	@FindBy(xpath = "//input[@name='quantity']")
	private WebElement quantity;
	
	@FindBy(id = "button-cart")
	private WebElement cartBtn;
	

	public ProductDetails(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getPrice()
	{
		return price.getText();
	}
	
	public void resetPrice()
	{
		cartTotal.click();
		removeBtn.click();
	}
	
	public CartPage setQtyandClick(String qty)
	{
		quantity.clear();
		quantity.sendKeys(qty);
		cartBtn.click();
		clickOnCart();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
}