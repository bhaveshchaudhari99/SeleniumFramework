package seleniumFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.BasePageClass;

public class CartPage extends BasePageClass{
	
	WebDriver driver;

	@FindBy(xpath = "//strong[text()='Total']/..//following-sibling::td")
	private WebElement totalPrice;
	
	@FindBy(xpath = "//strong[text()='Checkout']")
	private WebElement checkOutBtn;
	
	By checkOutBtnTime = By.xpath("//strong[text()='Checkout']");
	By totalPriceTime = By.xpath("//strong[text()='Total']/..//following-sibling::td");
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getTotalPrice()
	{
		visibilityOfElementLocated(totalPriceTime);
		return totalPrice.getText();
	}
	
	public CheckoutPage goToCheckotPage()
	{
		visibilityOfElementLocated(checkOutBtnTime);
		checkOutBtn.click();
		CheckoutPage checkoutpage = new CheckoutPage(driver);
		return checkoutpage;
	}
}