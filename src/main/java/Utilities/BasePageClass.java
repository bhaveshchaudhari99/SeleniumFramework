package utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageClass {
	
	static WebDriver driver;
	
	@FindBy(id = "cart-total")
	private static WebElement cartBtn;
	
	public BasePageClass(WebDriver driver)
	{
		BasePageClass.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public static void visibilityOfElementLocated(By findBy)
	{
		WebDriverWait wb = new WebDriverWait(driver, Duration.ofSeconds(10));
		wb.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	//here write this method because it same all tabs
	public static void clickOnCart()
	{
		cartBtn.click();
	}
}