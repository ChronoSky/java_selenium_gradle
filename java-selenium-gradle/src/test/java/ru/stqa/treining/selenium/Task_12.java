package ru.stqa.treining.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class Task_12 extends TestBase {

    @Test
    public void task_12() throws Exception {

        int from = 100000;
        int to = 999999;
        int rndNumber = from + (int)(Math.random()*to);
        String productName = "ButterFly_"+  rndNumber ;

        driver.get("http://localhost/litecart/admin/");
        sendKeys(By.name("username"), "admin");
        sendKeys(By.name("password"), "admin");
        click(By.name("login"));


        // проверка что открылась главная страница админки
        isElementPresent(By.id("box-apps-menu-wrapper"));

        adminPageSelectMenu("Catalog;Catalog", "Catalog");

        click(By.linkText("Add New Product"));

        // Navigate Tab - General
        click(By.linkText("General"));
        isElementPresent(By.id("tab-general"));

        // status
        setRadioButton(By.xpath("(//div[@id='tab-general']//input[@name='status'])[1]"));

        // Name
        sendKeys(By.name("name[en]"),productName);

        // postCode
        sendKeys(By.name("code"),Integer.toString(rndNumber));

        // Categories
        setCheckBox(By.xpath("(//div[@class='input-wrapper']//tr)[1]//input[@name='categories[]']"));
        setCheckBox(By.xpath("(//div[@class='input-wrapper']//tr)[2]//input[@name='categories[]']"));
        setCheckBox(By.xpath("(//div[@class='input-wrapper']//tr)[3]//input[@name='categories[]']"));

        // Default Category
        ComboBoxSelectByVisibleText(By.name("default_category_id"), "Rubber Ducks");
        //ComboBoxSelectByIndex(driver.findElement(By.name("default_category_id")), 2);

        // Product Groups
        setCheckBox(By.xpath("(//div[@id='tab-general']//input[@name='product_groups[]'])[1]"));
        setCheckBox(By.xpath("(//div[@id='tab-general']//input[@name='product_groups[]'])[2]"));
        setCheckBox(By.xpath("(//div[@id='tab-general']//input[@name='product_groups[]'])[3]"));

        // Quantity
        clearAndSendKeys(By.name("quantity") , "5");

        //Quantity Unit
        ComboBoxSelectByVisibleText(By.name("quantity_unit_id"), "pcs");

        //Delivery Status
        ComboBoxSelectByVisibleText(By.name("delivery_status_id"), "3-5 days");

        //Sold Out Status
        ComboBoxSelectByVisibleText(By.name("sold_out_status_id"), "Sold out");

        File imgFileName = new File("src/test/java/resources/Task_12.png");
        String fullFilePath = imgFileName.getAbsolutePath();
        try{
            FileInputStream fis = new FileInputStream(imgFileName);
            fis.close();
        } catch (FileNotFoundException e){
           throw new FileNotFoundException("Файл не содержится по указанному пути ["+ fullFilePath +"]");
        }

        // Upload Images
        sendKeys(By.name("new_images[]"),fullFilePath);

        // Date Valid From
        sendKeys(By.name("date_valid_from"),"07.07.2018");

        // Date Valid To
        sendKeys(By.name("date_valid_to"),"08.07.2018");

        // Navigate Tab - Information
        click(By.linkText("Information"));
        isElementPresent(By.id("tab-information"));

        // Manufacturer
        ComboBoxSelectByVisibleText(By.name("manufacturer_id"), "ACME Corp.");

        // Keywords
        clearAndSendKeys(By.name("keywords") , "keywords");

        // Short Description
        clearAndSendKeys(By.name("short_description[en]") , "short_description[en]");

        // Description
        clearAndSendKeys(By.xpath("//div[@class='trumbowyg-editor']") , "Description");

        // Head Title
        sendKeys(By.name("head_title[en]") , "Head Title");

        // Description
        sendKeys(By.name("meta_description[en]") , "Meta Description");

        // Navigate Tab - Prices
        click(By.linkText("Prices"));
        isElementPresent(By.id("tab-prices"));

        // Purchase Price
        clearAndSendKeys(By.name("purchase_price") , "12");
        ComboBoxSelectByVisibleText(By.name("purchase_price_currency_code"), "US Dollars");

        // Tax Class
        // no values
        //ComboBoxSelectByVisibleText(By.name("tax_class_id"), "US Dollars");

        // Price USD
        clearAndSendKeys(By.name("prices[USD]") , "12.53");

        click(By.name("save"));

        isElementPresent(By.name("catalog_form"));

        // поиск
        clearAndSendKeys(By.name("query") , productName + Keys.ENTER );

        List<WebElement> listProducts = driver.findElements(By.xpath("//form[@name='catalog_form']//tr[@class='row']"));

        Assert.assertTrue("Количество элементов поиска отличается от 1" , listProducts.size()==1 && listProducts.get(0).getText().equals(productName) );

    }


}

