package ru.stqa.treining.seleniumPageObject.appmanager;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.WebDriverWait;

public class ApplicationManager {

    public static WebDriverWait wait;
    private WebDriver driver;
    private SessionHelper sessionHelper;
    private AdminMainPage adminMainPage;
    private ProductPage productPage;
    private BasketPage basketPage;


    public ApplicationManager() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        sessionHelper = new SessionHelper(driver);
        adminMainPage = new AdminMainPage(driver);
        basketPage = new BasketPage(driver);
        productPage = new ProductPage(driver);

    }

    public void LoadAdminPage(){
        sessionHelper.openAdminPage();
    }

    public void quit(){
        driver.quit();
    }

    public AdminMainPage adminPage(){
        return adminMainPage;
    }

    public ProductPage productPage(){
        return productPage;
    }

    public BasketPage basketPage(){
        return basketPage;
    }
}

