package main.java;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.FileReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


public class PageCommon {
    WebDriver driver;
    WebDriver None;
    private String sSQLConnection ="";

    public PageCommon(WebDriver driver) throws Exception {
        this.driver = driver;
    }

    /**
     * @Description: Launch browser to URL
     * @param: Name(sURL), Description(URL will be open), Default(http://locahost:8080)
     */
    public void LaunchBrowser(String sURL) throws Exception
    {
        try {
            driver.manage().window().maximize();
            driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
            driver.manage().deleteAllCookies();
            driver.get(sURL);

            String sWindowHandle = driver.getWindowHandle();
            if(driver instanceof InternetExplorerDriver){
                ((JavascriptExecutor)driver).executeScript("window.focus()");
            }else if(driver instanceof EdgeDriver){
                ((JavascriptExecutor)driver).executeScript("window.focus()");
            }else if(driver instanceof ChromeDriver){
                ((JavascriptExecutor)this.driver).executeScript("alert('Test')");
                this.driver.switchTo().alert().accept();
            }

            driver.switchTo().window(sWindowHandle);
            Thread.sleep(6000);

        } catch (Exception ex) {

        }
    }

    /**
     * @Description: Close browser
     */
    public void CloseBrowser() throws Exception
    {
        try {
            driver.quit();

            Runtime rt = Runtime.getRuntime();
            if (System.getProperty("os.name").toLowerCase().indexOf("windows") > -1)
                rt.exec("taskkill chromedriver.exe" );
            else
                rt.exec("kill -9 chromedriver.exe");

        } catch (Exception ex) {
        }
    }

    public WebDriver setUp() throws Exception {
        try {
            String sPath = System.getProperty("user.dir");
            System.setProperty("webdriver.chrome.driver", sPath + "\\src\\webdriver\\chromedriver.exe");

            Map<String, Object> plugin_Chrome = new HashMap<String, Object>();
            plugin_Chrome.put("enabled", false);
            plugin_Chrome.put("name", "Chrome PDF Viewer");
            Map<String, Object> prefs_Chrome = new HashMap<String, Object>();
            prefs_Chrome.put("download.default_directory", sPath + "\\Download");
            prefs_Chrome.put("profile.default_content_settings.popups", 0);
            prefs_Chrome.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
            prefs_Chrome.put("plugins.plugins_list", Arrays.asList(plugin_Chrome));
            prefs_Chrome.put("credentials_enable_service", false);
            prefs_Chrome.put("profile.password_manager_enabled", false);
            DesiredCapabilities caps_Chrome = DesiredCapabilities.chrome();
            ChromeOptions options_Chrome = new ChromeOptions();
            options_Chrome.setExperimentalOption("prefs", prefs_Chrome);
            options_Chrome.addArguments("start-maximized");
            options_Chrome.addArguments("disable-infobars");
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("profile.default_content_setting_values.notifications", 2);
            options_Chrome.setExperimentalOption("prefs", prefs);

            caps_Chrome.setCapability(ChromeOptions.CAPABILITY, options_Chrome);
            caps_Chrome.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            driver = new ChromeDriver(caps_Chrome);
            return driver;

        } catch (Exception ex) {
            return null;
        }
    }
}