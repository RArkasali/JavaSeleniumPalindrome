package main.java;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import main.java.utils;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.support.ui.Select;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TheInternetPage {
    WebDriver driver;
    protected WebDriverWait wait;
    private String sResult = "";
    private String sErrorMessage = "";
    utils util;
    PageCommon page;

    public TheInternetPage(WebDriver driver) throws Exception {
        this.driver = driver;
    }

    public void TheInternetPageTest() throws Exception {
        try {
            util = new utils();
            page = new PageCommon(driver);

            page.LaunchBrowser("http://the-internet.herokuapp.com/");

            String xPathBtnJavascriptAlerts = "//a[@href='/javascript_alerts']";
            String xPathBtnJSConfirm = "//button[contains(text(), 'Click for JS Confirm')]";

            if (util.isElementPresent(driver, xPathBtnJavascriptAlerts, 3)) {
                WebElement WEbtnSearch = driver.findElement(By.xpath(xPathBtnJavascriptAlerts));
                WEbtnSearch.click();
                Thread.sleep(3000);
            }
            if (util.isElementPresent(driver, xPathBtnJSConfirm, 3)) {
                WebElement WEbtnJSConfirm = driver.findElement(By.xpath(xPathBtnJSConfirm));
                WEbtnJSConfirm.click();
                Thread.sleep(3000);

                driver.switchTo().alert().accept(); //click OK
                //driver.switchTo().alert().dismiss(); //click Cancel

                Thread.sleep(6000);
                page.CloseBrowser();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

