package SeleniumFramework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.BasePageClass;

public class ProductPage extends BasePageClass {
	
	WebDriver driver;

	@FindBy(css = "div.product-grid")
	private List<WebElement> products;
	
	By productLocation = By.cssSelector("div.product-layout h4");
	By addToCart = By.cssSelector("a");
	
	public ProductPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public List<WebElement> getProductsList()//wait apply because here loading problem is created because if more than elements there
	{
		visibilityOfElementLocated(productLocation);
		return products;
	} 
	
	public WebElement getProductbyName(String prodName)
	{
		WebElement prodCamera = null;
		
		List<WebElement> productList = getProductsList();
		
		for(WebElement product : productList)
		{
			WebElement targetProduct = product.findElement(productLocation);
			String productName = targetProduct.getText();
			System.out.println(productName);
			if(productName.equalsIgnoreCase(prodName))
			{	
				prodCamera = targetProduct.findElement(addToCart); 
				return prodCamera;
			}
		}
		return prodCamera;
	}	
	
	public ProductDetails addProductToCart(String prodName)
	{
		WebElement prod = getProductbyName(prodName);
		prod.click();
		ProductDetails pd = new ProductDetails(driver);
		return pd;
	}
}