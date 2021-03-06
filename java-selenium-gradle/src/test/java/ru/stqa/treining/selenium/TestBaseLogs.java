package ru.stqa.treining.selenium;

import com.google.common.io.Files;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestBaseLogs {

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public WebDriver driver;
    public WebDriverWait wait;

    public boolean isElementPresent(By locator){
        try{
            wait.until((WebDriver d) -> d.findElement(locator));
            return true;
        } catch (NoSuchElementException ex){
            return false;
        }
    }
/*
    public boolean isElementPresent(By locator){
        try{
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex){
            return false;
        }
    }
    */

    public boolean areElementsPresent(By locator){
        return driver.findElements(locator).size() > 0;
    }



    public static class MyListener extends AbstractWebDriverEventListener {
        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by +  " found");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            System.out.println(throwable);
        }
    }

    @Before
    public void start() {
        if (tlDriver.get() != null) {
            driver = tlDriver.get();
            wait = new WebDriverWait(driver, 10);
            return;
        }

    // IE
        //driver = new InternetExplorerDriver();

    //FireFox
        //driver = new FirefoxDriver();

        //FirefoxOptions options = new FirefoxOptions();
        //options.setBinary(new FirefoxBinary(new File("C:\\Program Files\\Firefox Nightly\\firefox.exe")));
        //driver = new FirefoxDriver(options);

    // Chrome
        driver = new EventFiringWebDriver(new ChromeDriver());

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        tlDriver.set(driver);
        //System.out.println(((HasCapabilities) driver).getCapabilities());
        wait = new WebDriverWait(driver, 10);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {driver.quit();driver = null;}));
    }

    @After
    public void stop(){
        // driver.quit();
        // driver = null;
    }

    public String getElementText(By locator) {
        return driver.findElement(locator).getText();
    }

    public void click(By locator){
        driver.findElement(locator).click();
    }

    public void clearAndSendKeys(By locator, String value){
        driver.findElement(locator).clear();
        sendKeys(locator, value);
    }

    public void sendKeys(By locator, String value){
        driver.findElement(locator).sendKeys(value);
    }

    public void adminPageSelectMenu(String menuElemPath, String pageHeaderName){

        String[] aMenu = menuElemPath.split(";");

        List<WebElement> listMenu = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li"));
        Assert.assertTrue("Не указан елемент для выбора в меню" , aMenu.length!=0);

        for (int i=0; i<listMenu.size(); i++ ){

            if (listMenu.get(i).getText().equals(aMenu[0])){
                listMenu.get(i).click();

                if (aMenu.length==2){
                    // получаем список подменю
                    WebElement lm = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li")).get(i);
                    List<WebElement> listSubMenu = lm.findElements(By.xpath("./ul/li/a/span"));

                    for (int j=0; j<listSubMenu.size(); j++ ) {
                        if (listSubMenu.get(j).getText().equals(aMenu[1])){
                            listSubMenu.get(j).click();
                            Assert.assertTrue("Открыт не верный раздел" , driver.findElement(By.tagName("h1")).getText().equals(pageHeaderName));
                            return;
                        }
                    }
                }

            }

        }
    }

    public void setCheckBox(By locator){
        WebElement el = driver.findElement(locator);
        if (el.getAttribute("checked")==null) {
            el.click();
            Assert.assertTrue("Не удалось выбрать указанный CheckBox" , el.getAttribute("checked").equals("true"));
        }
    }

    public void setRadioButton(By locator){
        WebElement el = driver.findElement(locator);
        if (el.getAttribute("checked")==null) {
            el.click();
            Assert.assertTrue("Не удалось выбрать указанный RadioButton" , el.getAttribute("checked").equals("true"));
        }
    }

    public void ComboBoxSelectByVisibleText(By locator, String value){
        WebElement el = driver.findElement(locator);
        Select sel = new Select(el);
        sel.selectByVisibleText(value);
    }

    public void ComboBoxSelectByIndex(By locator, int iValue){
        WebElement el = driver.findElement(locator);
        Select sel = new Select(el);
        sel.selectByIndex(iValue);
    }

    public ExpectedCondition<String> thereIsWindowOtherThan(Set<String> winSet){
        return new ExpectedCondition<String>(){
            public String apply(WebDriver driver){
                Set<String> curWinSet = driver.getWindowHandles();
                curWinSet.removeAll(winSet);
                return curWinSet.size() > 0 ? curWinSet.iterator().next() : null;
            }

        };
    }

    public void Admin_SelectMenu(String menuPath, String headerName){
        String[] aMenuPath = menuPath.split(";");
        Assert.assertTrue("Не корректно указан путь по меню",aMenuPath.length <= 2 && aMenuPath.length > 0);

        List<WebElement> listMenu = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li"));

        for (int i=0; i<listMenu.size(); i++ ){
            if (listMenu.get(i).getText().equals(aMenuPath[0])){
                listMenu.get(i).click();

                if (aMenuPath.length==2){
                    WebElement lm = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li")).get(i);
                    List<WebElement> listSubMenu = lm.findElements(By.xpath("./ul/li/a/span"));

                    for (int j=0; j<listSubMenu.size(); j++ ) {
                        if (listSubMenu.get(j).getText().equals(aMenuPath[1])) {
                            listSubMenu.get(j).click();
                            break;
                        }
                    }
                }
                break;
            }

        }
        isElementPresent(By.tagName("h1"));
        Assert.assertTrue(String.format("Название открытого раздела <%s> не соответсвует ожидаемому <%s>",getElementText(By.tagName("h1")),headerName), getElementText(By.tagName("h1")).equals(headerName));

    }


}
