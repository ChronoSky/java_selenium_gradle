package ru.stqa.treining.selenium;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;

import java.util.List;
import java.util.Set;

public class Task_17 extends TestBaseLogs {

    @Test
    public void task_17() throws Exception {


        // part 1
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        sendKeys(By.name("username"), "admin");
        sendKeys(By.name("password"), "admin");
        click(By.name("login"));

        isElementPresent(By.xpath("//td[@id='content']//h1[text()=' Catalog']"));

        OpenProductPages();

    }

        public void OpenProductPages(){

            List<WebElement> listCatalogs = driver.findElements(By.xpath("(//form[@name='catalog_form']/table//tr[@class='row']/td[3])/img/../a"));
            String productName;
            List<LogEntry> logList;
            for (int i=0 ; i< listCatalogs.size(); i++){
                productName = listCatalogs.get(i).getText();
                listCatalogs.get(i).click();
                isElementPresent(By.xpath("//td[@id='content']//h1[text()=' Edit Product: "+ productName +"']"));
                logList = driver.manage().logs().get("browser").getAll();
                Assert.assertTrue("Страница 'Edit Product: "+ productName +"' содержит сообщения в логах", logList.size()==0);
                click(By.name("cancel"));
                isElementPresent(By.xpath("//td[@id='content']//h1[text()=' Catalog']"));
                listCatalogs = driver.findElements(By.xpath("(//form[@name='catalog_form']/table//tr[@class='row']/td[3])/img/../a"));
            }

        }


}

