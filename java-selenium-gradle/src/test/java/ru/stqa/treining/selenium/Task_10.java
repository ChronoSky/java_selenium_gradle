package ru.stqa.treining.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Task_10 extends TestBase {

    @Test
    public void task_10() {
        boolean mpiRegularPriceColorIsGray = false;
        boolean mpiCampaignPriceColorIsRed = false;
        boolean сRegularPriceColorIsGray = false;
        boolean сCampaignPriceColorIsRed = false;

        String[] aColor;
        // part 1
        driver.get("http://localhost/litecart/");

        // проверка что открылась главная страница
        isElementPresent(By.id("main"));

        //List<WebElement> tblRows = driver.findElements(By.xpath("//form[@name='countries_form']//tr[@class='row']"));
        WebElement сampaignsItem = driver.findElement(By.xpath("//div[@id='box-campaigns']//li[1]/a"));
        // получили название товара
        String mpiName = сampaignsItem.findElement(By.className("name")).getText();

        // получили обычную цену товара
        double mpiRegularPrice = Double.parseDouble(сampaignsItem.findElement(By.className("regular-price")).getText().replace("$",""));

        // проверка на наличие перечеркивания
        boolean mpiRegularPriceHaveLine = сampaignsItem.findElement(By.className("regular-price")).getCssValue("text-decoration").contains("line-through");

        // получили свойство Color у обычной цены
        String mpiRegularPriceColor = сampaignsItem.findElement(By.className("regular-price")).getCssValue("color");
        mpiRegularPriceColor = mpiRegularPriceColor.replace("rgba(", ""); // для Chrome
        mpiRegularPriceColor = mpiRegularPriceColor.replace("rgb(", ""); // для FF
        mpiRegularPriceColor = mpiRegularPriceColor.replace(")", "");
        mpiRegularPriceColor = mpiRegularPriceColor.replace(" ", "");
        aColor = mpiRegularPriceColor.split(",");
        if (Integer.parseInt(aColor[0]) == Integer.parseInt(aColor[1]) && Integer.parseInt(aColor[1]) == Integer.parseInt(aColor[2])) mpiRegularPriceColorIsGray = true;

        // получили размер текста обычной цены (px - для Chrome, em - для IE)
        double mpiRegularPriceTextSize = Double.parseDouble(сampaignsItem.findElement(By.className("regular-price")).getCssValue("font-size").replace("px" , "").replace("em" , ""));

        // получили акционную цену товара
        double mpiCampaignPrice =  Double.parseDouble(сampaignsItem.findElement(By.className("campaign-price")).getText().replace("$",""));

        // проверка наличия жирной выделенности текста акционной цены
        boolean mpiCampaignPriceHaveBold = Integer.parseInt(сampaignsItem.findElement(By.className("campaign-price")).getCssValue("font-weight"))>=700;

        // получили свойство Color у акционной цены
        String mpiCampaignPriceColor = сampaignsItem.findElement(By.className("campaign-price")).getCssValue("color");
        mpiCampaignPriceColor = mpiCampaignPriceColor.replace("rgba(", ""); // для Chrome
        mpiCampaignPriceColor = mpiCampaignPriceColor.replace("rgb(", ""); // для FF
        mpiCampaignPriceColor = mpiCampaignPriceColor.replace(")", "");
        mpiCampaignPriceColor = mpiCampaignPriceColor.replace(" ", "");
        aColor = mpiCampaignPriceColor.split(",");
        if (Integer.parseInt(aColor[1]) == 0 && Integer.parseInt(aColor[2]) == 0) mpiCampaignPriceColorIsRed  = true;

        // получили размер текста акционной цены (px - для Chrome, em - для IE)
        double mpiCampaignPriceTextSize = Double.parseDouble(сampaignsItem.findElement(By.className("campaign-price")).getCssValue("font-size").replace("px" , "").replace("em" , ""));

        сampaignsItem.click();
        isElementPresent(By.id("box-product"));

        WebElement itemContent = driver.findElement(By.id("box-product"));

        String сName = itemContent.findElement(By.xpath(".//h1[@itemprop='name']")).getText();
        double сRegularPrice = Double.parseDouble(itemContent.findElement(By.className("regular-price")).getText().replace("$",""));

        // проверка на наличие перечеркивания
        boolean сRegularPriceHaveLine = itemContent.findElement(By.className("regular-price")).getCssValue("text-decoration").contains("line-through");

        // получили свойство Color у обычной цены
        String сRegularPriceColor = itemContent.findElement(By.className("regular-price")).getCssValue("color");
        сRegularPriceColor = сRegularPriceColor.replace("rgba(", ""); // для Chrome
        сRegularPriceColor = сRegularPriceColor.replace("rgb(", ""); // для FF
        сRegularPriceColor = сRegularPriceColor.replace(")", "");
        сRegularPriceColor = сRegularPriceColor.replace(" ", "");
        aColor = сRegularPriceColor.split(",");
        if (Integer.parseInt(aColor[0]) == Integer.parseInt(aColor[1]) && Integer.parseInt(aColor[1]) == Integer.parseInt(aColor[2])) сRegularPriceColorIsGray = true;

        // (px - для Chrome, em - для IE)
        double сRegularPriceTextSize = Double.parseDouble(itemContent.findElement(By.className("regular-price")).getCssValue("font-size").replace("px" , "").replace("em" , ""));

        // получили акционную цену товара
        double сCampaignPrice =  Double.parseDouble(itemContent.findElement(By.className("campaign-price")).getText().replace("$",""));

        // проверка наличия жирной выделенности текста акционной цены
        boolean сCampaignPriceHaveBold = Integer.parseInt(itemContent.findElement(By.className("campaign-price")).getCssValue("font-weight"))>=700;

        // получили свойство Color у акционной цены
        String сCampaignPriceColor = itemContent.findElement(By.className("campaign-price")).getCssValue("color");
        сCampaignPriceColor = сCampaignPriceColor.replace("rgba(", ""); // для Chrome
        сCampaignPriceColor = сCampaignPriceColor.replace("rgb(", ""); // для FF
        сCampaignPriceColor = сCampaignPriceColor.replace(")", "");
        сCampaignPriceColor = сCampaignPriceColor.replace(" ", "");
        aColor = сCampaignPriceColor.split(",");
        if (Integer.parseInt(aColor[1].trim()) == 0 && Integer.parseInt(aColor[2].trim()) == 0) сCampaignPriceColorIsRed  = true;

        // получили размер текста акционной цены  (px - для Chrome, em - для IE)
        double сCampaignPriceTextSize = Double.parseDouble(itemContent.findElement(By.className("campaign-price")).getCssValue("font-size").replace("px" , "").replace("em" , ""));

        // сравнили размеры обычной цены и акционной
        if (mpiRegularPriceTextSize >= mpiCampaignPriceTextSize) Assert.assertTrue("акционная цена НЕ крупнее, чем обычная" , false);


        // а
        Assert.assertTrue("на главной странице и на странице товара НЕ совпадает текст названия товара" , mpiName.equals(сName));

        // б
        Assert.assertTrue("на главной странице и на странице товара НЕ совпадает ОБЫЧНАЯ цены" , mpiRegularPrice == сRegularPrice);
        Assert.assertTrue("на главной странице и на странице товара НЕ совпадает АКЦИОННАЯ цены" , mpiCampaignPrice == сCampaignPrice);

        // в
        Assert.assertTrue("обычная цена НЕ зачёркнутая на Главной Странице" , mpiRegularPriceHaveLine);
        Assert.assertTrue("обычная цена НЕ серая на Главной Странице" , mpiRegularPriceColorIsGray);
        Assert.assertTrue("обычная цена НЕ зачёркнутая на Странице Товара" , сRegularPriceHaveLine);
        Assert.assertTrue("обычная цена НЕ серая на Странице Товара" , сRegularPriceColorIsGray);

        // г
        Assert.assertTrue("акционная цена НЕ выделена жирным на Главной Странице" , mpiCampaignPriceHaveBold);
        Assert.assertTrue("акционная цена НЕ красная на Главной Странице" , mpiCampaignPriceColorIsRed);
        Assert.assertTrue("акционная цена НЕ выделена жирным на Странице Товара" , сCampaignPriceHaveBold);
        Assert.assertTrue("акционная цена НЕ красная на Странице Товара" , сCampaignPriceColorIsRed);

        // д
        Assert.assertTrue("акционная цена НЕ крупнее, чем обычная на Главной Странице" , mpiRegularPriceTextSize < mpiCampaignPriceTextSize);
        Assert.assertTrue("акционная цена НЕ крупнее, чем обычная на Странице Товара" , сRegularPriceTextSize < сCampaignPriceTextSize);

    }


}

