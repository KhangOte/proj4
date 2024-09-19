package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
    WebDriver driver;

    @FindBy (id = "username")
    WebElement userEmail;

    @FindBy (id = "password")
    WebElement userPassword;

    public DashboardPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public void loginInDashboard(String email, String password) {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        driver.findElement(By.name("action")).click();
    }
}
