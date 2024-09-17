package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.LoginPage;
import page.TopMenu;

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



}
