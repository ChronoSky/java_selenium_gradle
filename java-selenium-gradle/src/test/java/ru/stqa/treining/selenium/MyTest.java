package ru.stqa.treining.selenium;


import org.junit.Test;
import org.openqa.selenium.By;

public class MyTest extends TestBase{

    @Test
    public void test(){
        driver.get("http://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("webdriver");
    }

}
