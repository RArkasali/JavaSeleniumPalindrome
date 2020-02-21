package main.java;

import java.awt.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import com.google.common.base.Stopwatch;
import org.openqa.selenium.NoSuchElementException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

public class utils {
    public utils()
    {
    }
    /** Wait for seconds
     * @return no value
     * @param sec: second
     */

    public static void Wait(int sec) throws Exception{
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception(ex.getClass() + "|" + ex.getMessage());
        }
    }

    public static String now(String sFormat) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(sFormat);
        return sdf.format(cal.getTime());
    }

    public static void createDirectory(String sPath)
    {
        File file = new File(sPath);

        if (!(new File(file.getParent())).exists()) {
            createDirectory(file.getParent());
        }
        file.mkdir();
    }

    /**
     * @Description: CCSVRead a file
     * @param: Name(sCSVFile), Description(Source File path), Default(null)
     */
    public static ArrayList<String> CSVRead(String sCSVFile) throws Exception
    {
        ArrayList<String> data = new ArrayList<String>();
        int isFirst =0;

        File fileDir = new File(sCSVFile);
        BufferedReader in = new BufferedReader(new InputStreamReader(
                        new FileInputStream(fileDir), "UTF8"));

        String str;

        while ((str = in.readLine()) != null) {
            if (isFirst==1 && str!= null)
            {
                String[] arr = str.split("\t");
                data.add(str);
            }
            isFirst = 1;
            System.out.println(str);
        }

        in.close();

        return data;
    }

    public void UpdateReport(String sFile, int rowIndex, String sNewString, String sOptional) throws Exception
    {
        try {
            String fileNameWithoutExt = "";
            String lineStrTmp = "";
            int j =1;

            Thread.sleep(2000);
            String sProjectPath = System.getProperty("user.dir");
            int index = sFile.lastIndexOf("\\");
            String fileName = sFile.substring(index + 1);
            String nativeDir = sFile.substring(0, sFile.lastIndexOf(File.separator));
            int pos = fileName.lastIndexOf(".");
            if (pos > 0) fileNameWithoutExt = fileName.substring(0, pos);

            String newCSVFilePath = nativeDir + "\\" + fileNameWithoutExt + "_2.txt";
            File f1 = new File(sFile);
            File f2 = new File(newCSVFilePath);
            copyFileUsingStream(f1, f2);
            FileWriter fw = new FileWriter(newCSVFilePath);
            Thread.sleep(2000);
            BufferedWriter bw = new BufferedWriter(fw);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    new FileInputStream(sFile), "UTF8"));

            String str;
            while ((str = in.readLine()) != null) {
                lineStrTmp = str;
                if (j==rowIndex) {
                    lineStrTmp = sNewString;
                }
                bw.write(lineStrTmp);
                bw.newLine();
                j++;
            }
            bw.close();

            Thread.sleep(2000);
            PrintWriter writer = new PrintWriter(sFile);
            writer.print("");
            writer.close();
            String source = newCSVFilePath;
            String dest = sFile;
            File fin = new File(source);
            FileInputStream fis = new FileInputStream(fin);
            in = new BufferedReader(new InputStreamReader(fis));

            FileWriter fstream = new FileWriter(dest, true);
            BufferedWriter out = new BufferedWriter(fstream);
            Thread.sleep(2000);
            String aLine = null;
            while ((aLine = in.readLine()) != null) {
                out.write(aLine);
                out.newLine();
            }
            in.close();
            out.close();
            fin.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UpdateCSV(String sReportPath, int row, String lineString, String sResult, String sErrorMessage, String sOptional) throws Exception
    {
        try {
            String[] arrlineString;
            String NewStr = "";

            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            Date date = new Date();
            String sDate = dateFormat.format(date);

            arrlineString = lineString.split("\t");

            if (sResult.indexOf("Passed")!=-1) NewStr = arrlineString[0] + "\t" + arrlineString[1]  + "\t" + sDate + "\t" + "Passed" + "," + "";
            else if (sResult.indexOf("Failed")!=-1) NewStr = arrlineString[0] + "\t" + arrlineString[1]  + "\t" + sDate + "\t" + "Failed" + "\t" + sErrorMessage;
            else NewStr = arrlineString[0] + "\t" + arrlineString[1]  + "\t" + arrlineString[2] + "\t" + arrlineString[3]  + "\t" + arrlineString[4];

            for (int j = 5; j < arrlineString.length; j++) {// go to each line
                NewStr = NewStr + "\t" + arrlineString[j];
            }

            UpdateReport(sReportPath, row, NewStr, "null");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void copyFile(File sourceFile, File destFile) throws IOException {
        try {
            FileUtils.copyDirectory(sourceFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isElementPresent(WebDriver driver, String sObject, int TimeOut) throws Exception{
        try {
            for(int i=1; i<=TimeOut; i++)
            {
                if(driver.findElements(By.xpath(sObject)).size() > 0){
                    return true;
                }
                TimeUnit.SECONDS.sleep(1);
            }

            return false;
        }catch(InvalidSelectorException ex){
            sObject = sObject.replace("='","=\"");
            sObject = sObject.replace(",'",",\"");
            sObject = sObject.replace("')]","\")]");
            for(int i=1;i<=TimeOut;i++)
            {
                if(driver.findElements(By.xpath(sObject)).size() > 0){
                    return true;
                }
                TimeUnit.SECONDS.sleep(1);
            }

            return false;
        }
    }

    public void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }
}
