package test;

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
}
