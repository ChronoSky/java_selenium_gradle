package ru.stqa.treining.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Random;

public class Task_11 extends TestBase {

    @Test
    public void task_11() {

        Random rand = new Random();

        String email = "Test"+  rand.nextInt(10000) +"@mail.ru";
        driver.get("http://localhost/litecart/");

        // проверка что открылась главная страница
        isElementPresent(By.id("main"));

        // Шаг 1
        driver.findElement(By.linkText("New customers click here")).click();

        // проверка что открылась форма регистрации
        isElementPresent(By.id("create-account"));

        // заполенение обязательных полей формы регистрации
        driver.findElement(By.name("firstname")).sendKeys("TestFName");
        driver.findElement(By.name("lastname")).sendKeys("TestLName");
        driver.findElement(By.name("address1")).sendKeys("TestAddress");
        driver.findElement(By.name("postcode")).sendKeys("123456");
        driver.findElement(By.name("city")).sendKeys("Moscow");
        driver.findElement(By.name("address1")).sendKeys("st.TestStreet d.1");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("phone")).sendKeys("+79251234567");
        driver.findElement(By.name("password")).sendKeys("qwerty12345");
        driver.findElement(By.name("confirmed_password")).sendKeys("qwerty12345");
        driver.findElement(By.name("create_account")).click();

        // Шаг 2
        // проверка что выполнен вход под учетной записью
        Assert.assertTrue("Вход в аккаунт не выполнен" , driver.findElement(By.xpath("//div[@id='box-account']/h3")).getText().equals("Account"));

        // выход из под учетки
        driver.findElement(By.linkText("Logout")).click();

        // проверка что выполнен выход из-под учетной записи на главную страницу
        Assert.assertTrue("Выход из аккаунта не выполнен" , driver.findElement(By.xpath("//div[@id='box-account-login']/h3")).getText().equals("Login"));

        // Шаг 3
        // вход под учетной записью с главной страницы
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("qwerty12345");
        driver.findElement(By.name("login")).click();

        // проверка что выполнен вход под учетной записью
        Assert.assertTrue("Вход в аккаунт не выполнен" , driver.findElement(By.xpath("//div[@id='box-account']/h3")).getText().equals("Account"));

        // Шаг 4
        // проверка что выполнен вход под учетной записью
        Assert.assertTrue("Вход в аккаунт не выполнен" , driver.findElement(By.xpath("//div[@id='box-account']/h3")).getText().equals("Account"));

        // выход из под учетки
        driver.findElement(By.linkText("Logout")).click();

        // проверка что выполнен выход из-под учетной записи на главную страницу
        Assert.assertTrue("Выход из аккаунта не выполнен" , driver.findElement(By.xpath("//div[@id='box-account-login']/h3")).getText().equals("Login"));

    }


}

