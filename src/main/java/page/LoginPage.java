package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    @FindBy(id = "user_email")
    WebElement userEmailBox;

    @FindBy(id = "user_password")
    WebElement userPasswordBox;

    @FindBy(name = "commit")
    WebElement clickSubmitButton;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void login(String email,String password){
        userEmailBox.sendKeys(email);
        userPasswordBox.sendKeys(password);
        clickSubmitButton.click();

    }
    public String verifyLogin(){
        String resultLogin = driver.findElement(By.xpath("//div[@class = 'panel-body']")).getText();
        return resultLogin;
    }
}
