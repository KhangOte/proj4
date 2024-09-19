package Report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
    private static final ExtentReports extentReports = new ExtentReports();
    public synchronized static ExtentReports getExtentReport(){
        ExtentSparkReporter reporter = new ExtentSparkReporter("");
        reporter.config().setReportName("Report for proj4");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("","");
        extentReports.setSystemInfo("","");
        return extentReports;
    }
}
