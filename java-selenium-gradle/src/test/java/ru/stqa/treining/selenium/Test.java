package ru.stqa.treining.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
/*
        System.setProperty("webdriver.chrome.driver", "C:\\Java\\Tools\\chromedriver.exe");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("unexpectedAlertBehaviour", "dismiss");
*/
        //driver = new ChromeDriver();
        //driver = new InternetExplorerDriver();
        driver = new FirefoxDriver();

        //driver = new ChromeDriver(caps);
        //driver = new InternetExplorerDriver(caps);
        //driver = new FirefoxDriver(caps);
        //System.out.println(((HasCapabilities) driver).getCapabilities());
        wait = new WebDriverWait(driver, 10);
    }

    @org.junit.Test
    public void test(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
