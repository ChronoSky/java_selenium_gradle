package ru.stqa.treining.selenium;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Task_14 extends TestBase {

    @Test
    public void task_14() throws Exception {


        // part 1
        driver.get("http://localhost/litecart/admin/");
        sendKeys(By.name("username"), "admin");
        sendKeys(By.name("password"), "admin");
        click(By.name("login"));

        isElementPresent(By.xpath("//ul[@id='box-apps-menu']"));

        // открыли раздел - Countries
        Admin_SelectMenu("Countries" , "Countries");

        // открыли создание новой страны
        click(By.linkText("Add New Country"));

        isElementPresent(By.xpath("//h1[text()=' Add New Country']"));

        // запоминаем текущий handle окна
        String mainWinHandle = driver.getWindowHandle();
        // запоминаем handle всех открытых в текущий момент окно(вкладок)
        Set<String> winSet = driver.getWindowHandles();

        // переход по ссылке
        click(By.xpath("(//i[@class='fa fa-external-link'])[1]"));

        // переключение на новое окно(вкладку)
        String newWindow = wait.until(thereIsWindowOtherThan(winSet));
        driver.switchTo().window(newWindow);

        // проверка отктытия нужнонго окна
        isElementPresent(By.id("firstHeading"));
        Assert.assertTrue("Открылась не верное окно(вкладка)", getElementText(By.id("firstHeading")).equals("ISO 3166-1 alpha-2"));

        // переключение на первое окно(вкладку)
        driver.close();
        driver.switchTo().window(mainWinHandle);

        // проверка открытия первого окна
        isElementPresent(By.xpath("//ul[@id='box-apps-menu']"));

    }



}

