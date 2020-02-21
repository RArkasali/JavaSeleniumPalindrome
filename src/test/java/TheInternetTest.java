package test.java;

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
import main.java.utils;
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
import org.testng.Assert;
import org.testng.annotations.Test;
import main.java.PalindromeChecker;
import main.java.PageCommon;
import main.java.*;

public class TheInternetTest {
    WebDriver driver;
    PageCommon page;

    @Test
    public void TheInternetTest() throws Exception{
        page = new PageCommon(driver);
        driver = page.setUp();

        TheInternetPage internetPage = new TheInternetPage(driver);
        internetPage.TheInternetPageTest();
    }
}
