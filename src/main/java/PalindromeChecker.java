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
import org.apache.commons.io.FileUtils;

public class PalindromeChecker {
    private String sResult = "";
    private String sErrorMessage = "";
    utils util;

    public PalindromeChecker(){
    }

    public static boolean isPalindrome(String str){
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        String rev = sb.toString();
        if(str.equals(rev)){
            return true;
        }else{
            return false;
        }
    }

    public void PalindromeCheckerTest(String sFilePath, String sFileReport, String sOptional) throws Exception {
        try {
            String lineString = "";
            String[] arrlineString;
            String[] arrNew;
            int row = 0;
            String NewStr = "";
            util = new utils();

            String sProjectPath = System.getProperty("user.dir");
            File f1 = new File(sProjectPath + sFilePath);
            if (!f1.exists()) {
                return;
            }

            File f2 = new File(sProjectPath + sFileReport);
            util.copyFileUsingStream(f1, f2);

            ArrayList<String> arrParamValues = util.CSVRead(sProjectPath + sFilePath);
            List<String> list = arrParamValues;
            for (int i = 0; i < list.size(); i++) {
                lineString = list.get(i).toString();
                row = i + 2;

                arrlineString = lineString.split("\t");
                if (arrlineString.length>=6 && arrlineString[1].toLowerCase().indexOf('y')!=-1) {
                    if (isPalindrome(arrlineString[6])) sResult = "Passed";
                    else sResult = "Failed";
                    util.UpdateCSV(sProjectPath + sFileReport, row, lineString, sResult, sErrorMessage, "null");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
