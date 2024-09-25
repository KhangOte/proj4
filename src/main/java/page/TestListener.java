package page;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import test.BaseTest;

import static Report.TestReport.*;
import static test.BaseTest.testCaseReport;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String screenShotName = getNameImage(result.getName());
        System.out.println("This is test failed: " + result.getName());
        captureScreenshot(((BaseTest) result.getInstance()).driver, screenShotName);
        testCaseReport
                .addScreenCaptureFromPath(screenShotName)
                .log(Status.FAIL, "Login failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("This is test skipped: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test failed: ");
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("Test success");
        String screenShotName = getNameImage(result.getName());

        captureScreenshot(((BaseTest) result.getInstance()).driver, screenShotName); //=> note in Readme File
        testCaseReport.log(Status.PASS, "Login successfully");
    }

    public void onStart(ITestContext context) {
        System.out.println("Test start");
    }


    public void onFinish(ITestContext context) {
        System.out.println("Test finish");
        BaseTest.report.flush();
        BaseTest.driver.quit();
    }
}
