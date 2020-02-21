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

public class PalindromeTest {
    @Test
    public void PalindromeCheckerTest() throws Exception{

        PalindromeChecker checker = new PalindromeChecker();
        checker.PalindromeCheckerTest("\\src\\testdata\\PalindromeTestData.txt", "\\src\\report\\PalindromeTestData.txt", "");
    }
}

