package ru.stqa.treining.seleniumPageObject.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SessionHelper extends HelperBase{

    public SessionHelper(WebDriver wd){
        super(wd);
    }

    public void openAdminPage(){
        wd.get("http://localhost/litecart/");
        isElementPresent(By.id("main"));
    }
}
