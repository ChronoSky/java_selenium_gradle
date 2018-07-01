package ru.stqa.treining.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Task_4 {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){

        System.setProperty("webdriver.chrome.driver", "C:\\Java\\Tools\\chromedriver.exe");
        driver = new ChromeDriver();
        //driver = new InternetExplorerDriver();
        //driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void task_4(){
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