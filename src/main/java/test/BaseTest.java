package test;

import Report.TestReport;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class BaseTest {
    WebDriver driver;
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
}
