package Weather;

import io.restassured.RestAssured;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class OpenWeatherMap_Base 
{
	
	protected static ExtentReports extent;
    public static ExtentSparkReporter htmlReporter;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    
	//public static ExtentTest test;
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
		ExtentSparkReporter spark = new ExtentSparkReporter("./OpenWeatherMap/test-output/ExtentReports_Both1.html");
		//ExtentSparkReporter spark1=new extent
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
		if(result.getStatus() == ITestResult.FAILURE)
        {
            test.get().log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
            test.get().fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
            test.get().log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
        }
        else
        {
            test.get().log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
            test.get().skip(result.getThrowable());
        }
		extent.flush();
	    }

	@AfterSuite
	    public void teardown() {
		extent.flush();
	    }
	
	}

