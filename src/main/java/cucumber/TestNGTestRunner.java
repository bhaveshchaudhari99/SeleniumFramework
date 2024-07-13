package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/main/java/cucumber",
		glue = "stepDefinition",
		monochrome = true,
		plugin = {"html:target/cucmber.html"}
		)
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

	
}
