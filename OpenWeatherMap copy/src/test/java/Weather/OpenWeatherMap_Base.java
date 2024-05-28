package Weather;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

//import Report.ExtentManager;
//import Report.ExtentReportListener;
import io.restassured.RestAssured;

public class OpenWeatherMap_Base 
{
	
	protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    /*
	@BeforeSuite
	  public void beforeSuite() throws IOException {
	    ExtentManager.setExtent();
	  }

	  @AfterSuite
	  public void afterSuite() {
	    //service.shutdown();
	    ExtentManager.endReport();
	  }
	 */ 
	@BeforeClass
	public static void setup() 
	{
		RestAssured.baseURI="https://api.openweathermap.org/data/2.5";
		ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReports1.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
	}
	
	/*
	@AfterClass
	public static void teardown()
	{
	//	service.shutdown();
	    ExtentManager.endReport();
	

	}
	*/
	/*
	@org.testng.annotations.AfterTest
	public void AfterTest() {

		ExtentManager.endReport();

	}
	*/
	/*
	@AfterMethod
	public void AfterMethod(ITestResult result) {
		ExtentReportListener obj=new ExtentReportListener();

	    if (result.getStatus() == ITestResult.FAILURE) {
	    	obj.onTestFailure(result);
	        
	    } else if (result.getStatus() == ITestResult.SUCCESS) {
	        obj.onTestSuccess(result);
	    } else {
	        obj.onTestSkipped(result);
	    }
	}
	*/ 
	
	@AfterMethod
	 public void getResult(ITestResult result) {
	        if (result.getStatus() == ITestResult.FAILURE) {
	            test.get().fail(result.getThrowable());
	        } else if (result.getStatus() == ITestResult.SKIP) {
	            test.get().skip(result.getThrowable());
	        } else {
	            test.get().pass("Test passed");
	        }
	        extent.flush();
	    }

	    @AfterClass
	    public void teardown() {
	        extent.flush();
	    }
	
	}

