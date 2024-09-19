package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.DashboardPage;
import page.LoginPage;
import page.TopMenu;

import java.time.Duration;

public class RunTest extends BaseTest {

    @BeforeMethod
    public void testSetUp(){
        super.testInit();
    }

    @Test
    @DataProvider(name = "test1")
    public static Object[][] accountLogin(){
        return new Object[][]{{"minionmatbua@gmail","123456"},{"minionmatbua@gmail.com","12345"},{"minionmatbua@gmail.com","12345678"}};
    }
    @Test(dataProvider = "test1")
    public void test1(String email,String password){
        String messageLoginFailed = "Invalid Email or password.";
        String messageLoginSuccessfully = "Signed in successfully.";
        SoftAssert softAssert = new SoftAssert();
        TopMenu topMenu = new TopMenu(driver);
        LoginPage loginPage = topMenu.clickSignInButton();
        loginPage.login(email,password);
        String messageResultLogin = loginPage.verifyLogin();
        softAssert.assertEquals(messageResultLogin,messageLoginFailed);
        softAssert.assertEquals(messageResultLogin,messageLoginSuccessfully);

        driver.quit();
    }

    @Test
    @DataProvider(name = "test2")
    public static Object[][] accountLoginForDashboard(){
        return new Object[][]{{"minionmatbua@gmail","123456"},{"minionmatbua@gmail.com","12345"},{"minionmatbua@gmail.com","12345678"}};
    }

    @Test(dataProvider = "test2")
    public void test2(String email,String password) throws InterruptedException{
        TopMenu topMenu = new TopMenu(driver);
        DashboardPage dashboardPage = topMenu.clickDashboard();
        Object[] windowHandle = driver.getWindowHandles().toArray();
        driver.switchTo().window(windowHandle[1].toString());
        driver.findElement(By.linkText("Sign in")).click();
       Thread.sleep(2000);
        dashboardPage.loginInDashboard(email,password);

        driver.quit();
    }



}
