package test;

import Report.TestReport;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;

public abstract class BaseTest {
    public static WebDriver driver;
    public void BaseTest(WebDriver driver){
        this.driver = driver;
    }
    public void testInit(){
        driver = new ChromeDriver();
        driver.get("https://openweathermap.org/");
        driver.manage().window().maximize();
    }
    public static ExtentReports  report = TestReport.createTestReport();// create report file
    public static ExtentTest testCaseReport;

//    public void captureScreenshot(String filename) {
//        File image = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
//        File screenshotFile = new File(filename);
//        try {
//            FileUtils.copyFile(image, screenshotFile);
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
}
