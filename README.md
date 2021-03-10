## UITestFramework
Java Project for test automation, covering UI testing. Created for testing the launch of any website and validating test cases related to the site functionalities.

## Concepts Included
1. Page Object Model
2. Excel reader
3. Test Listener
4. Property File Reader
5. Method Chaining

## Tools
1. Maven
2. TestNG
3. Selenium
4. Extent reports

## Requirements
In order to utilize this project you need to have the following installed locally:
1. Eclipse IDE or any supported IDE
2. Maven
3. TestNG
4. Java 1.8

## Usage
The project is broken into four main modules. 

The core files handling the working of the framework are present in the path `src/main/java`, inside package named as `com.kosmos.core`. 

Page Objects for each page holding the page elements and methods to test the functionality of the page is available in the `src/test/java` path, inside package named as `com.kosmos.page`.

Test case is available in the `src/test/java` path, inside package named as `com.kosmos.test`.

Test data for running the tests is fed via excel file which is placed under testdata folder available in `src/test/resources` path. The test data excel file should be named as per the test class's name. And if not already created a test data excel file with the Test class name will be generated as soon as we run the test case and a warning will be thrown to enter the data accordingly for further execution.

The drivers required to run the test cases on Chrome or Firefox are available in `src/main/resources` path.


The fields required for setting up the framework like url of the website, browser name, excel test data directory path, sheet name etc; are fed from the `config.properties` file.

To test the framework-  
- open the `testNG.xml` file under the source project
- right click on the file
-  choose `RunAs`, then `TestNG Suite`

You can also test the framework through terminal
- open the terminal
- move to the project location
- run the command `mvn clean` from terminal
- run the command `mvn test` from terminal

## Reporting
After successfull execution of the tests a folder named TestReport will be generated under the source framework (Refresh the project if the folder is not visible). Open the .html file using any browser to view the detailed reports for the execution. 

### Note
You can create test files under `com.kosmos.test` and create respective page objects under `com.kosmos.page`. As per your requirements you can design the test cases and write common methods for all the test cases in `PageObjectInit.java` class and other test specific methods for each page in the respective Page Object class. 
Execute the test cases as per your requirements either by updating the testNG.xml file or by simply running individual test files.
