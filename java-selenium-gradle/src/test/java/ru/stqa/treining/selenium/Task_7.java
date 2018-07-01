package ru.stqa.treining.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Task_7 extends TestBase {

    @Test
    public void task_7() {

        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        isElementPresent(By.xpath("//ul[@id='box-apps-menu']"));
        List<WebElement> listMenu = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li"));

        for (int i=0; i<listMenu.size(); i++ ){
            listMenu.get(i).click();
            WebElement lm = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li")).get(i);
            List<WebElement> listSubMenu = lm.findElements(By.xpath("./ul/li/a/span"));

            for (int j=0; j<listSubMenu.size(); j++ ) {
                listSubMenu.get(j).click();
                // обновляем список элементов подменю
                listSubMenu = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li/ul/li/a/span"));
                isElementPresent(By.tagName("h1"));
            }
            // если раздел не содержит подразделов
            if (listMenu.size()==0) isElementPresent(By.tagName("h1"));
            // обновляем список элементов меню
            listMenu = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li"));
        }

    }
}

