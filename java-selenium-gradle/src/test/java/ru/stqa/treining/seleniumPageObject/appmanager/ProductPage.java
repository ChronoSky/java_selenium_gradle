package ru.stqa.treining.seleniumPageObject.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends HelperBase {

    public ProductPage(WebDriver wd) {
        super(wd);
    }

    public void addProductToBasket() {
        int curCount = getBasketCount();

        // если открылся товар с выбором размера
        if (getElementText(By.xpath("//div[@id='box-product']//h1")).equals("Yellow Duck")){
            ComboBoxSelectByVisibleText(By.name("options[Size]"), "Small");
        }

        // добавляем товар в корзину
        click(By.name("add_cart_product"));

        // ожидаем изменения счетчика корзины на +1
        ApplicationManager.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='cart']//span[@class='quantity' and text()='"+ Integer.toString(curCount + 1) +"']")));
    }



    public void returnToAdminMainPage() {
        // возврат на главную страницу
        click(By.xpath("//img[@title='My Store']"));
        isElementPresent(By.id("box-most-popular"));
    }

    public int getBasketCount() {
        return Integer.parseInt( getElementText(By.xpath("//div[@id='cart']//span[@class='quantity']")));
    }
}
