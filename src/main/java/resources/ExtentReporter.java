package resources;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {

	//ExtentSparkReport //ExtentReport
	
	public static ExtentReports ExtentReporting()
	{
		String path = System.getProperty("user.dir") + "//reports//index.html";
		ExtentSparkReporter reporter =  new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Test Report");
		reporter.config().setReportName("Automation Test Report");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Bhavesh"); 
		return extent;
	}
}