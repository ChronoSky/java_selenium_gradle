package ru.stqa.treining.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class Task_9 extends TestBase {

    @Test
    public void task_9() {
        String countryName;
        Integer countryZones;
        String lastCountryName = "";

        String zoneName;
        String lastZoneName = "";

        // part 1
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        // проверка что открылась правильная страница
        isElementPresent(By.xpath("//form[@name='countries_form']"));

        List<WebElement> tblRows = driver.findElements(By.xpath("//form[@name='countries_form']//tr[@class='row']"));

        System.out.println("Список содержит  <"+ tblRows.size() + "> строк");
        for (int i=0; i < tblRows.size(); i++ ){
            countryName = tblRows.get(i).findElement(By.xpath("./td[5]")).getText();
            countryZones = Integer.parseInt(tblRows.get(i).findElement(By.xpath("./td[6]")).getText());
            if (i==0){
                lastCountryName = countryName;
            } else {
                if (!isGreaterThan(countryName,lastCountryName)){Assert.assertTrue("Алфавитный порядок нарушен. Страна '"+ countryName +"' не должна стоять после страны '"+ lastCountryName +"'",false);}
            }
           if (countryZones > 0){
               tblRows.get(i).findElement(By.xpath("./td[5]/a")).click();
               if (!isElementPresent(By.id("table-zones"))){Assert.assertTrue("Не видна таблица зон страны",false);}
               List<WebElement> zonesList = driver.findElements(By.xpath("//table[@id='table-zones']//tr"));
               for (int z=1; z < zonesList.size()-1;z++){
                   zoneName = zonesList.get(z).findElement(By.xpath("./td[3]")).getText();
                   if (z==1) {
                       lastZoneName = zoneName;
                   } else {
                       if (!isGreaterThan(zoneName,lastZoneName)){Assert.assertTrue("Алфавитный порядок нарушен. Зона '"+ zoneName +"' не должна стоять после зоны '"+ lastZoneName +"'",false);}
                   }
               }
               driver.findElement(By.name("cancel")).click();
               isElementPresent(By.xpath("//form[@name='countries_form']"));
               tblRows = driver.findElements(By.xpath("//form[@name='countries_form']//tr[@class='row']"));
           }
        }

        // part 2
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        isElementPresent(By.xpath("//form[@name='geo_zones_form']"));
        List<WebElement> countryRows = driver.findElements(By.xpath("//form[@name='geo_zones_form']//tr[@class='row']"));
        for (int i=0; i < countryRows.size(); i++ ){
            //System.out.println("Проверили страну :" + tblRows.get(i).findElement(By.xpath("./td[3]/a")).getText());
            countryRows.get(i).findElement(By.xpath("./td[3]/a")).click();
            if (!isElementPresent(By.id("table-zones"))){Assert.assertTrue("Не видна таблица зон страны",false);}
            List<WebElement> zonesList = driver.findElements(By.xpath("//table[@id='table-zones']//tr"));
            for (int z=1; z < zonesList.size()-1;z++){
                zoneName = zonesList.get(z).findElement(By.xpath("./td[3]/select/option[@selected='selected']")).getText();
                if (z==1) {
                    lastZoneName = zoneName;
                } else {
                    if (!isGreaterThan(zoneName,lastZoneName)){
                        Assert.assertTrue("Алфавитный порядок нарушен. Зона '"+ zoneName +"' не должна стоять после зоны '"+ lastZoneName +"'",false);
                    }/*else {
                        System.out.println("Проверили зоны :" + zoneName + "и"+ lastZoneName);
                    }*/
                }
            }
            driver.findElement(By.name("cancel")).click();
            isElementPresent(By.xpath("//form[@name='geo_zones_form']"));
            countryRows = driver.findElements(By.xpath("//form[@name='geo_zones_form']//tr[@class='row']"));
        }


    }

    public static boolean isGreaterThan(String a, String b) {
        return a.compareTo(b) > 0;
    }
}

