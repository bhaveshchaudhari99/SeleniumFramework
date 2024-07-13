package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

public class BaseTest {
	
	public WebDriver driver;

	public WebDriver initBrowser() throws IOException
	{
		FileInputStream fis = new FileInputStream("D:\\Selenium_practise\\SeleniumFrameworkbySSquareIT\\src\\main\\java\\resources\\Properties.properties");
		Properties prop = new Properties();
		prop.load(fis);	
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));//Implicit wait
		String url = prop.getProperty("baseUrl");
		driver.get(url);
		return driver;
	}
	
	public static String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		 if (driver == null) {
			 System.out.println("driver null");
	            throw new IllegalArgumentException("Driver is not initialized!");
	        }
		TakesScreenshot ts = (TakesScreenshot)driver;
		String path = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";	
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(path);
		FileHandler.copy(source, destination);		
		return path;
	}
	
	public void tearDown()
	{
		driver.close();
	}
}