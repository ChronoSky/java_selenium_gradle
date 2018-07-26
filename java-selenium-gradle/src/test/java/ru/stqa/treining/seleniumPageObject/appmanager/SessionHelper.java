package ru.stqa.treining.seleniumPageObject.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase{

    public SessionHelper(WebDriver wd){
        super(wd);
    }

    public void openAdminPage(){
        wd.get("http://localhost/litecart/");
        isElementPresent(By.id("main"));
    }
}
