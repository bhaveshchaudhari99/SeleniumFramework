package TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporter;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent = ExtentReporter.ExtentReporting();

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Case Passed.");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String filePath = null;
		
		test.fail(result.getThrowable());
		
		//Screenshot -> Attach to extent report
		try 
		{
			//here we catch driver from specific test case and passed them to screenshot 
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		if(driver == null)
			System.out.println("driver null");
		
		if (result.getThrowable() instanceof AssertionError) 
		{	
			try 
			{
				filePath = getScreenshot(result.getMethod().getMethodName(), driver);
			}
			catch (IOException e) 
			{	
				e.printStackTrace();
			}
		}	
		if(driver == null)
			System.out.println("driver null");
		test.addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());//give screenshot knowledge to extent report
	}

	@Override
	public void onFinish(ITestContext context) {
			extent.flush();
	}
}