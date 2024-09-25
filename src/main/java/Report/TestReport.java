package Report;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestReport {

    static String reportFolder = ".\\Report\\";
    public static ExtentReports createTestReport() {
        System.out.println("===createTestReport===");
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(reportFolder);
        extent.attachReporter(spark);
        System.out.println("===createTestReport finished===");
        return extent;
    }

    public static void captureScreenshot(WebDriver driver, String filename) {
        File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshotFile = new File(reportFolder + filename);
        try {
            FileUtils.copyFile(image, screenshotFile);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static String getTime() {
        String time = new SimpleDateFormat("yyyyMMdd-HHmmss").
                format(Calendar.getInstance().getTime()) + ".png";
        return time;
    }

    public static String getNameImage(String testName) {
        String nameImage = testName + "_" + TestReport.getTime();
        return nameImage;
    }

}

