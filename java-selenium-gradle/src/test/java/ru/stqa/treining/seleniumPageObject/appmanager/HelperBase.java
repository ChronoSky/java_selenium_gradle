package ru.stqa.treining.seleniumPageObject.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HelperBase {

    protected WebDriver wd;

    public HelperBase(WebDriver wd){
        this.wd = wd;
    }

    protected void type(By locator, String text) {
        click(locator);
        if (text != null){
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected String getElementText(By locator) {
        return wd.findElement(locator).getText();
    }

    public void ComboBoxSelectByVisibleText(By locator, String value){
        WebElement el = wd.findElement(locator);
        Select sel = new Select(el);
        sel.selectByVisibleText(value);
    }

    public boolean isElementPresent(By locator){
        try{
            ApplicationManager.wait.until((WebDriver d) -> d.findElement(locator));
            return true;
        } catch (NoSuchElementException ex){
            return false;
        }
    }
}
