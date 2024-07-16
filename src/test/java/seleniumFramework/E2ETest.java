package seleniumFramework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import testComponents.RetryTest;

public class E2ETest {

	@Test
	public void endToEndTest()
	{
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//Explicit wait
		WebDriverWait wb = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://tutorialsninja.com/demo/");
		
		//Landing Page
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Login']")).click();
		
		//Login Page
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("yog@gmail.com");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("Yogesh@12345");
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		
		//Home page
		driver.findElement(By.xpath("//a[text()='Cameras']")).click();
		
		//Product Page
		List<WebElement> products = driver.findElements(By.cssSelector("div.product-grid"));
		for(WebElement product : products)
		{
			WebElement targetProduct = product.findElement(By.cssSelector("div.product-layout h4"));
			String productName = targetProduct.getText();
			System.out.println(productName);
			if(productName.equalsIgnoreCase("Nikon D300"))
				targetProduct.findElement(By.cssSelector("a")).click(); 
		}
		
		//Product Details Page
			//Advanced xpath concept
			// /.. is used to back to parent tag 
		String price = driver
				.findElement(By.xpath("//h1[text()='Nikon D300']/..//following-sibling::ul[@class='list-unstyled'][2]//h2"))
				.getText();
		System.out.println(price);
		
		//First Validation
		Assert.assertEquals(price, "$98.00");
		
		//Reset cart items
		driver.findElement(By.id("cart-total")).click();
		driver.findElement(By.xpath("//button[@title='Remove']")).click();
		
		driver.findElement(By.xpath("//input[@name='quantity']")).clear();
		driver.findElement(By.xpath("//input[@name='quantity']")).sendKeys("2");
		driver.findElement(By.id("button-cart")).click();
		
		driver.findElement(By.id("cart-total")).click();
		String totalPrice = driver.findElement(By.xpath("//strong[text()='Total']/..//following-sibling::td")).getText();
		System.out.println("Total Price:- "+totalPrice);
		
		//Second Validation
		Assert.assertEquals(totalPrice, "$196.00");
		
		driver.findElement(By.xpath("//strong[text()='Checkout']")).click();
		
		//Checkout Page
		driver.findElement(By.xpath("//a[text()='Checkout']")).click();
		driver.findElement(By.xpath("//button[@class='close']")).click();
		
		//Third Validation
		String mark = driver.findElement(By.xpath("(//a[text()='Nikon D300'])[2]/..//following-sibling::span")).getText();
		boolean isSuccess = mark.equalsIgnoreCase("***");
		Assert.assertTrue(isSuccess);
		 
		driver.close();
	}
}