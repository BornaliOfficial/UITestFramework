package com.kosmos.core;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestListener extends FileHandler implements ITestListener {
	protected static PageObjectInit driver;
	protected static ExtentReports reports;
	protected static ExtentTest test;
	static Logger logger = null;
	private static PropertyReader configFile = new PropertyReader("config.properties");
	private static String testReportDir = configFile.getPropertyValue("TEST_REPORT_DIR");
	private static String testReportName = configFile.getPropertyValue("TEST_REPORT_NAME");

	public void onTestStart(ITestResult result) {
		logger.info(result.getMethod().getMethodName() + " =====> started");
		test = reports.startTest(result.getMethod().getMethodName());
		test.log(LogStatus.INFO, result.getMethod().getMethodName() + " is started");
	}

	public void onTestSuccess(ITestResult result) {
		logger.info(result.getMethod().getMethodName() + " =====> passed");
		test.log(LogStatus.PASS, result.getMethod().getMethodName() + "test is passed");
	}

	public void onTestFailure(ITestResult result) {
		logger.info(result.getMethod().getMethodName() + " =====> failed");
		test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed");
	}

	public void onTestSkipped(ITestResult result) {
		test.log(LogStatus.SKIP, result.getMethod().getMethodName() + "test  is skipped");
		logger.info(result.getMethod().getMethodName() + " =====> skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onStart(ITestContext context) {
		logger = Logger.getLogger(TestListener.class.getName());
		logger.setLevel(Level.INFO);
		logger.info("Initializing and starting test automation...");
		reports = new ExtentReports(
				FileHandler.formFilePath(testReportDir + File.separator + testReportName + ".html"));
	}

	public void onFinish(ITestContext context) {
		// closing the browser
		PageObjectInit.getWebBrowser().close();
		// quitting the browser
		PageObjectInit.getWebBrowser().quit();
		logger.info("Teardown...");
		logger.info("On finish");
		// flushing logs into the html report
		reports.flush();
	}
}