package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TopMenu {
    WebDriver driver;


    public TopMenu(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public LoginPage clickSignInButton(){
        driver.findElement(By.linkText("Sign in")).click();
        return new LoginPage(driver);
    }

    public DashboardPage clickDashboard(){
        driver.findElement(By.linkText("Dashboard")).click();
        return new DashboardPage(driver);
    }
}
