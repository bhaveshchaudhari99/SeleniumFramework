package SeleniumFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;

	@FindBy(xpath = "//input[@id='input-email']")
	private WebElement username;
	
	@FindBy(xpath = "//input[@id='input-password']")
	private WebElement password;
	
	@FindBy(xpath = "//input[@class='btn btn-primary']")
	private WebElement submitButton;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public HomePage LoginAction(String email,String passrd)
	{
		username.sendKeys(email);
		password.sendKeys(passrd);
		submitButton.click();
		HomePage hp = new HomePage(driver);
		return hp;
	}
}