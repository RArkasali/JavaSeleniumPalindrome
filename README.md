Please follow the below steps, for the setup and execution, and kindly refer to the Readme_Elaborated.docx(attached above) for detailed reference:

Step 1) Download & set up Java JDK 8

Step 2) Download apache-maven-3.5.4 package and save in the folder path D:\TheInternet\apache-maven-3.5.4

Step 3) Download Rapid Environment Editor & then create variables for JAVA_PATH, MAVEN_PATH

Step 4) Download IntelliJ "Community" Version, example it will save as in the path D:\ideaIC-2019.3.2.win

Step 5) Double click on the application idea64 to open Intellij IDE

Step 6) Click Import Project

Step 7) Click OK

Step 8) Click Create project from existing sources, then click Next

Step 9) Click Next

Step 10) Click Yes 

Step 11) Click Next 

Step 12) Click Next 

Step 13) Click Next 

Step 14) Click Overwrite

Step 15) Add Java JDK 8 as image 

C:\Program Files\Java\jdk1.8.0_241
 
Step 16) Click Finish

Step 17) It will open the project as image, remember click ‘Add as Maven project’

Step 18) It will take 15 minutes for auto downloading maven dependencies which will be keep as default as: C:\Users\hieptc\.m2\repository

Step 19) There are 4 main folders: main, test, testdata, report
 
Main: keep java files which operate UI operations such as open webform, click button, send keys into a text fields
Test: keep test cases which be execute
Testdata: create test data file in csv file and save to this folder 
Report: will auto generate after running a test
 
Running a test: 
Run PalindromeCheckerTest: Click on PalindromeCheckerTest.java file in the folder test, then right click and click menu Run PalindromeCheckerTest, then it will run all test data which defined in the D:\TheInternet\src\testdata\ PalindromeTestData.txt

When running it looks like: 
 
After running, it will display green result in the bottom panel 
 
Then go to D:\TheInternet\src\report\ PalindromeTestData.txt to review result
 
Run TheInternetTest: Click on TheInternetTest.java file in the folder test, then right click and click menu Run TheInternetTest, then it will run the function TheInternetTest()

Notes:
You can double click the runtest.bat (D:\TheInternet\runtest.bat) to run all test suites without using Intellij IDE  
File D:\TheInternet\runtest.bat
cd D:\TheInternet\
mvn package -DskipTests=false
Remember to edit cd D:\TheInternet\ by the project TheInternet path you save in your machine

Or you can also run by using TestNG, by right click-> testng.xml, and running it as a TestNG suite
