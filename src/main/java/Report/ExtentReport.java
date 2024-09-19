package Report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;

public class ExtentReport {
    static ExtentTest test;
    static ExtentReports reports;
    WebDriver driver;

    public static void startTest(){
    reports = new ExtentReports(System.getProperty("user.dir")+"ExtentReportResults.html");
    test = reports.startTest("ExtentReport");
    }

}

