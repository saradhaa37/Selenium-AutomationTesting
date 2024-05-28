package Report;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

  public static ExtentSparkReporter htmlReporter;
  public static ExtentReports extent;
  public static ExtentTest test;

  public static void setExtent() throws IOException {
    htmlReporter = new ExtentSparkReporter(
        System.getProperty("user.dir") + "/test-output/ExtentReport/"
            + "TestExecutionReport"
            + ".html");
    htmlReporter.loadXMLConfig(System.getProperty("user.dir")
        + "/src/main/resources/extent-config.xml");

    extent = new ExtentReports();
    extent.attachReporter(htmlReporter);
  }

  public static void endReport() {
    extent.flush();
  }
}