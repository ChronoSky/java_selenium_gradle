package ru.stqa.treining.selenium;


import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Task_13 extends TestBase {

    @Test
    public void task_13() throws Exception {


        driver.get("http://localhost/litecart/");

        // проверка что открылась главная страница
        isElementPresent(By.id("main"));

        // добавляем продукты в корзину
        addProductsFromMainPage(3);

        // удаляем продукты из корзины
        deleteAllProductsFromBasket();

    }


    public void deleteAllProductsFromBasket(){
        // открываем корзину
        click(By.linkText("Checkout »"));

        List<WebElement> basketTableRows = driver.findElements(By.xpath("//div[@id='order_confirmation-wrapper']//tr"));

        // исключаем из расчетов строки не содержащие информацию о товарах
        int tableRows = basketTableRows.size()-5;
        wait = new WebDriverWait(driver, 5);

        while(tableRows>0){
            // удаляем товар
            click(By.name("remove_cart_item"));

            // проверяем что из таблицы пропала 1 строка
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='order_confirmation-wrapper']//tr["+ Integer.toString(basketTableRows.size()) +"]")));

            // обновляем данные по таблице
            basketTableRows = driver.findElements(By.xpath("//div[@id='order_confirmation-wrapper']//tr"));
            tableRows = basketTableRows.size()-5;
        }

        // возврат на главную страницу
        click(By.linkText("<< Back"));
        isElementPresent(By.id("box-most-popular"));
    }

    public void addProductsFromMainPage(int addCount) {
        int curCount = Integer.parseInt( getElementText(By.xpath("//div[@id='cart']//span[@class='quantity']")));
        wait = new WebDriverWait(driver, 5);

        while (addCount > curCount){
            // выбриаем на шлавной странице товар
            click(By.xpath("//div[@id='box-most-popular']//li[1]"));

            // проверяем что открылась форма товара
            isElementPresent(By.id("box-product"));

            // если открылся товар с выбором размера
            if (driver.findElement(By.xpath("//div[@id='box-product']//h1")).getText().equals("Yellow Duck")){
                ComboBoxSelectByVisibleText(By.name("options[Size]"), "Small");
            }

            // добавляем товар в корзину
            click(By.name("add_cart_product"));

            // ожидаем изменения счетчика корзины на +1
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='cart']//span[@class='quantity' and text()='"+ Integer.toString(curCount + 1) +"']")));

            // получаем текущее кол-во товаров в корзине
            curCount = Integer.parseInt( getElementText(By.xpath("//div[@id='cart']//span[@class='quantity']")));

            // возврат на главную страницу
            click(By.xpath("//img[@title='My Store']"));
            isElementPresent(By.id("box-most-popular"));

        }
    }

}

