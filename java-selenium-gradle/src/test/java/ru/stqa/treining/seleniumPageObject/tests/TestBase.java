package ru.stqa.treining.seleniumPageObject.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.treining.seleniumPageObject.appmanager.ApplicationManager;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static ThreadLocal<ApplicationManager> tlDriver = new ThreadLocal<>();
    public ApplicationManager app;


    @Before
    public void start() {
        if (tlDriver.get() != null) {
            app = tlDriver.get();
            return;
        }

        app = new ApplicationManager();
        tlDriver.set(app);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {app.quit();app = null;}));

    }

}
