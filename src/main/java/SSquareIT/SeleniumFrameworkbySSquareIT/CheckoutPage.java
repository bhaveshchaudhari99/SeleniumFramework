package SSquareIT.SeleniumFrameworkbySSquareIT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

	@FindBy(xpath = "//a[text()='Checkout']")
	private WebElement checkout;
	
	@FindBy(xpath = "//button[@class='close']")
	private WebElement closeBtn;
	
	@FindBy(xpath = "(//a[text()='Nikon D300'])[2]/..//following-sibling::span")
	private WebElement successMessage;
	
	public CheckoutPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void clickToCheckout()
	{
		checkout.click();
	}
	
	public void closeErrorPopup()
	{
		closeBtn.click();
	}
	
	public String getSuccessMessage()
	{
		return successMessage.getText();
	}
}