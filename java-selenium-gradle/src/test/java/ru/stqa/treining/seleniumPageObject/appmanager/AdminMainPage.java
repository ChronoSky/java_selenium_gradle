package ru.stqa.treining.seleniumPageObject.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class AdminMainPage extends HelperBase{

    private int basketCount;

    public AdminMainPage(WebDriver wd) {
        super(wd);
    }

    public int getBasketCount() {
        return Integer.parseInt( getElementText(By.xpath("//div[@id='cart']//span[@class='quantity']")));
    }

    public void openFirstProduct() {
        // выбриаем на шлавной странице товар
        click(By.xpath("//div[@id='box-most-popular']//li[1]"));

        // проверяем что открылась форма товара
        isElementPresent(By.id("box-product"));
    }

    public void openBasket() {
        // открываем корзину
        click(By.linkText("Checkout »"));
    }
}
