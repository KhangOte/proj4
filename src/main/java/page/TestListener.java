package page;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import test.BaseTest;

import static test.BaseTest.testCaseReport;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("This is test failed: " + result.getName());
//        captureScreenshot(result.getTestName());
        testCaseReport.log(Status.FAIL, "Login failed");
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
        testCaseReport.log(Status.PASS, "Login successfully");
    }

    public void onStart(ITestContext context) {
        System.out.println("Test start");
    }


    public void onFinish(ITestContext context) {
        System.out.println("Test finish");
    }
}
