package test;



import Report.TestReport;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v126.page.model.Screenshot;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import page.DashboardPage;
import page.LoginPage;
import page.TopMenu;

import java.lang.reflect.Method;
import java.time.Duration;

import static Report.TestReport.captureScreenshot;


public class RunTest extends BaseTest {
    private String testName;

    @BeforeMethod
    public void testSetUp(Method testMethod){
        super.testInit();
        testName = testMethod.getName();
        testCaseReport = report.createTest(testName);
        System.out.println("BeforeMethod");
    }

    @BeforeTest
    public void startTest(){
        System.out.println("BeforeTest");
    }

    @Test
    @DataProvider(name = "test1")
    public static Object[][] accountLogin(){
        return new Object[][]{{"minionmatbua@gmail","123456"},{"minionmatbua@gmail.com","12345"},{"minionmatbua@gmail.com","12345678"}};
//        return new Object[][]{{"minionmatbua@gmail.com","12345678"}};
    }

    @Test(dataProvider = "test1")
    public void test1(String email,String password) {
        String messageLoginFailed = "Invalid Email or password.";
        String messageLoginSuccessfully = "Signed in successfully.";
        SoftAssert softAssert = new SoftAssert();
        TopMenu topMenu = new TopMenu(driver);
        LoginPage loginPage = topMenu.clickSignInButton();
        loginPage.login(email, password);

        captureScreenshot(driver,"./login-tab2.png");
        testCaseReport.addScreenCaptureFromPath("./login-tab2.png").log(Status.INFO,"Screenshot for login tab");
        String messageResultLogin = loginPage.verifyLogin();
        try {
//            Assert.assertEquals(messageResultLogin, messageLoginFailed);
            Assert.assertEquals(messageResultLogin, messageLoginSuccessfully);
            testCaseReport.log(Status.PASS, "Login successfully");
        } catch (AssertionError assertionError){
            testCaseReport.log(Status.FAIL, "Login failed");
        }
//        ITestListener
    }

    @Test
    @DataProvider(name = "test2")
    public static Object[][] accountLoginForDashboard(){
//        return new Object[][]{{"minionmatbua@gmail","123456"},{"minionmatbua@gmail.com","12345"},{"minionmatbua@gmail.com","12345678"}};
        return new Object[][]{{"minionmatbua@gmail","123456"}};

    }

    @Test(dataProvider = "test2")
    public void test2(String email,String password) throws InterruptedException{

//        String CODE1 = "{\n    \"theme\": \"standard\",\n    \"encoding\": \"utf-8\n}";
//        String CODE2 = "{\n    \"protocol\": \"HTTPS\",\n    \"timelineEnabled\": false\n}";

        TopMenu topMenu = new TopMenu(driver);

        testCaseReport.log(Status.INFO, "Click dask board");
        DashboardPage dashboardPage = topMenu.clickDashboard();

        Object[] windowHandle = driver.getWindowHandles().toArray();
        driver.switchTo().window(windowHandle[1].toString());

        testCaseReport.log(Status.INFO, "switch to new tab");
        driver.findElement(By.linkText("Sign in")).click();
        Thread.sleep(2000);

        dashboardPage.loginInDashboard(email,password);
        testCaseReport.log(Status.PASS, "Verify LOGIN success ");
//
//        ExtentReports extent = new ExtentReports();
//        ExtentSparkReporter spark = new ExtentSparkReporter("./Report");
//        extent.attachReporter(spark);
//
//        extent.createTest("ScreenCapture")
//                .addScreenCaptureFromPath("extent.png")
//                .pass(MediaEntityBuilder.createScreenCaptureFromPath("extent.png").build());
//
//        extent.createTest("LogLevels")
//                .info("info")
//                .pass("pass")
//                .warning("warn")
//                .skip("skip")
//                .fail("fail");
//
//        extent.createTest("CodeBlock").generateLog(
//                Status.PASS,
//                MarkupHelper.createCodeBlock(CODE1, CODE2));
//
//        extent.createTest("ParentWithChild")
//                .createNode("Child")
//                .pass("This test is created as a toggle as part of a child test of 'ParentWithChild'");
//
//        extent.createTest("Tags")
//                .assignCategory("MyTag")
//                .pass("The test 'Tags' was assigned by the tag <span class='badge badge-primary'>MyTag</span>");
//
//        extent.createTest("Authors")
//                .assignAuthor("TheAuthor")
//                .pass("This test 'Authors' was assigned by a special kind of author tag.");
//
//        extent.createTest("Devices")
//                .assignDevice("TheDevice")
//                .pass("This test 'Devices' was assigned by a special kind of devices tag.");
//
//        extent.createTest("Exception! <i class='fa fa-frown-o'></i>")
//                .fail(new RuntimeException("A runtime exception occurred!"));

//        extent.createTest(testName)
//                .log(Status.PASS, "This is a logging event for FirstTestReport, and it passed!");
//        extent.flush();

    }

    @AfterMethod
    private void extracted() {
        System.out.println("AfterMethod");
        report.flush();
        driver.quit();
    }


}
