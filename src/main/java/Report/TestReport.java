package Report;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestReport {


    public static ExtentReports createTestReport(){
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("./Report");
        extent.attachReporter(spark);
        return extent;
//        ExtentTest testReport = extent.createTest(testName);
//        return testReport;
    }
}

