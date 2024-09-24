package Report;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestReport {


    public static ExtentReports createTestReport(){
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("./GeneraReport/Report");
        extent.attachReporter(spark);
        return extent;
    }

    public static void captureScreenshot(WebDriver driver, String filename) {
        File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshotFile = new File(filename);
        try {
            FileUtils.copyFile(image, screenshotFile);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

//    public static void createTime(String[]args){
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        Date date = new Date();
//        System.out.println(formatter.format(date));
//    }
public static String getTime(){
    String time = new SimpleDateFormat("yyyyMMdd-HHmmss").
            format(Calendar.getInstance().getTime())+".png";
    return time;
}

}

