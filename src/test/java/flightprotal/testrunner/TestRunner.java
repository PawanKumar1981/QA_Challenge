package flightprotal.testrunner;


//import org.testng.annotations.DataProvider;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
//import io.cucumber.testng.AbstractTestNGCucumberTests;


public class TestRunner {
	@RunWith(Cucumber.class)
	@CucumberOptions(
			features="/src/main/resources",
			glue= "flightprotal.StepDefiniation",
					plugin = {"pretty",
							"html:target/html/",
							"json:target/json/file.json"}
			
			)
	
	public class Runner{	
		//AbstractTestNGCucumberTests
//		    @Override
//		    @DataProvider(parallel = true)
//		    public Object[][] scenarios() {
//		        return super.scenarios();
//		    }
	}

}
