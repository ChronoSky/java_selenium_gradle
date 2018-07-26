package ru.stqa.treining.seleniumPageObject.tests;

import org.junit.Test;

public class AddProductsToBasketAndClearBasket extends TestBase{

    @Test
    public void addProductsToBasketAndClearBasketTest(){
        app.LoadAdminPage();
        int basCount = app.adminPage().getBasketCount();
        while (basCount < 3){
            app.adminPage().openFirstProduct();
            app.productPage().addProductToBasket();
            basCount = app.productPage().getBasketCount();
            app.productPage().returnToAdminMainPage();
        }

        app.adminPage().openBasket();
        app.basketPage().clearBasket();
        app.basketPage().returnToAdminMainPage();
        app.quit();
    }

}
