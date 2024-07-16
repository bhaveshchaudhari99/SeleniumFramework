package SeleniumFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;

	@FindBy(xpath = "//a[text()='Cameras']")
	private WebElement cameraTab;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public ProductPage clickOnCameraTab()
	{
		cameraTab.click();
		ProductPage pp = new ProductPage(driver);
		return pp;
	}
}