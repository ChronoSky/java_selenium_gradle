package ru.stqa.treining.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class Task_8 extends TestBase {

    @Test
    public void task_8() {

        driver.get("http://localhost/litecart");
        isElementPresent(By.xpath("//div/a/img[@title='My Store']"));
        List<WebElement> pictList = driver.findElements(By.xpath("//li[@class='product column shadow hover-light']"));

        System.out.println("Найдено картинок : "+ pictList.size());
        for (int i=0; i < pictList.size(); i++ ){
            if (pictList.get(i).findElements(By.xpath(".//div[contains(@class,'sticker')]")).size()!=1) Assert.assertTrue(false);
        }
    }
}

