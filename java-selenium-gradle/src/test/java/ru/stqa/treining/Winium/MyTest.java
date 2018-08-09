package ru.stqa.treining.Winium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class MyTest {
    WiniumDriver driver;

    @Before
    public void start() throws MalformedURLException {
        DesktopOptions option = new DesktopOptions();

        option.setApplicationPath("C:\\Windows\\system32\\calc.exe");

        driver = new WiniumDriver(new URL("http://localhost:9999") , option);
    }

    @Test
    public void myTest()  {

        driver.findElement(By.id("131")).click();
        driver.findElement(By.id("131")).click();
        driver.findElement(By.id("93")).click();
        driver.findElement(By.id("132")).click();
        driver.findElement(By.id("133")).click();
        driver.findElement(By.id("121")).click();

        Assert.assertTrue("Сумма не равноа 34", driver.findElement(By.id("150")).getAttribute("Name").equals("34"));
      }

    @After
    public void stop(){
        driver.findElement(By.id("Close")).click();
    }
}
