package ru.stqa.treining.seleniumPageObject.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BasketPage extends HelperBase {

    public BasketPage(WebDriver wd) {
        super(wd);
    }

    // возврат на главную страницу
    public void returnToAdminMainPage() {
        click(By.linkText("<< Back"));
        isElementPresent(By.id("box-most-popular"));
    }

    public void clearBasket() {
        List<WebElement> basketTableRows = wd.findElements(By.xpath("//div[@id='order_confirmation-wrapper']//tr"));

        // исключаем из расчетов строки не содержащие информацию о товарах
        int tableRows = basketTableRows.size()-5;

        while(tableRows>0){
            // удаляем товар
            click(By.name("remove_cart_item"));

            // проверяем что из таблицы пропала 1 строка
            ApplicationManager.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='order_confirmation-wrapper']//tr["+ Integer.toString(basketTableRows.size()) +"]")));

            // обновляем данные по таблице
            basketTableRows = wd.findElements(By.xpath("//div[@id='order_confirmation-wrapper']//tr"));
            tableRows = basketTableRows.size()-5;
        }
    }


}
