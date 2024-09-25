package test;


import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import page.DashboardPage;
import page.LoginPage;
import page.TestListener;
import page.TopMenu;

import java.lang.reflect.Method;

import static Report.TestReport.*;

@Listeners(TestListener.class)
public class RunTest extends BaseTest {
    private String testName;

    @BeforeMethod
    public void testSetUp(Method testMethod) {
        super.testInit();
        testName = testMethod.getName();
        testCaseReport = report.createTest(testName);
        System.out.println("BeforeMethod");
    }

    @BeforeTest
    public void startTest() {
        System.out.println("BeforeTest");
    }

    @Test
    @DataProvider(name = "test1")
    public static Object[][] accountLogin() {
        return new Object[][]{{"minionmatbua@gmail", "123456"}, {"minionmatbua@gmail.com", "12345"}, {"minionmatbua@gmail.com", "12345678"}};
//        return new Object[][]{{"minionmatbua@gmail.com", "12345678"}};
    }

    @Test(dataProvider = "test1")
    public void test1(String email, String password) {
        String messageLoginSuccessfully = "Signed in successfully.";
        SoftAssert softAssert = new SoftAssert();
        TopMenu topMenu = new TopMenu(driver);
        LoginPage loginPage = topMenu.clickSignInButton();
        loginPage.login(email, password);
        String nameImage = getNameImage(testName);
        System.out.println(nameImage);
        captureScreenshot(driver, nameImage);

        testCaseReport.addScreenCaptureFromPath(nameImage).log(Status.INFO, "Screenshot for login tab test");
        String messageResultLogin = loginPage.verifyLogin();
        Assert.assertEquals(messageResultLogin, messageLoginSuccessfully);

//        try {
//            Assert.assertEquals(messageResultLogin, messageLoginFailed);


//        } catch (AssertionError assertionError) {
//            testCaseReport.log(Status.FAIL, "Login failed");
//        }
//        ITestListener
    }

    @Test
    @DataProvider(name = "test2")
    public static Object[][] accountLoginForDashboard() {
//        return new Object[][]{{"minionmatbua@gmail","123456"},{"minionmatbua@gmail.com","12345"},{"minionmatbua@gmail.com","12345678"}};
        return new Object[][]{{"minionmatbua@gmail", "123456"}};

    }

    @Test(dataProvider = "test2")
    public void test2(String email, String password) throws InterruptedException {

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

        dashboardPage.loginInDashboard(email, password);
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
