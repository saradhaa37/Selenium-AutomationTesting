package utils;

import io.restassured.RestAssured;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class OpenWeatherMap_Base {
    protected static ExtentReports extent;
    public static ExtentSparkReporter htmlReporter;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @BeforeClass
    public static void setup() {
        String baseUrl = ConfigManager.getProperty("base.url");
        RestAssured.baseURI = baseUrl;

        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReports_Both1.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @AfterMethod
    public void getResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.get().log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:", ExtentColor.RED));
            test.get().fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.get().log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
        } else {
            test.get().log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
            test.get().skip(result.getThrowable());
        }
        extent.flush();
    }

    @AfterSuite
    public void teardown() {
        extent.flush();
    }
}
