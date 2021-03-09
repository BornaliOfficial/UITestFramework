package com.kosmos.core;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.kosmos.core.BrowserClientFactory;
import com.kosmos.core.PageObjectInit;
import com.kosmos.core.PropertyReader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageObjectInit {
	String appURL = null;
	protected static WebDriver driver;
	String browserName = null;
	static boolean cnsCalled = false;
	PropertyReader configFile = null;

	public PageObjectInit() {
		if (!cnsCalled) {
			configFile = new PropertyReader("config.properties");
			appURL = configFile.getPropertyValue("APP_URL");
			browserName = configFile.getPropertyValue("BROWSER_NAME");
			if (driver == null) {
				if (browserName == null) {
					PageObjectInit.driver = BrowserClientFactory.getChromeBrowser();
					System.out.println("Defaulting Browser to Chrome Browser since no browser name is added");
				} else if (browserName.equalsIgnoreCase("ChromeDriver"))
					PageObjectInit.driver = BrowserClientFactory.getChromeBrowser();
				else if (browserName.equalsIgnoreCase("FirefoxDriver"))
					PageObjectInit.driver = BrowserClientFactory.getFirefoxBrowser();
			}
			setApplicationURL(appURL);
			maximize();
			cnsCalled = true;
		}
	}

	// set application URL into browser
	public void setApplicationURL(String baseURL) {
		driver.get(baseURL);
	}

	// method to apply default implicit wait of 10 seconds
	public void invokeImplicitWait() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	// method to apply customized implicit wait
	public void invokeImplicitWait(int timeInSeconds) {
		driver.manage().timeouts().implicitlyWait(timeInSeconds, TimeUnit.SECONDS);
	}

	// method to maximize a browser window
	public void maximize() {
		driver.manage().window().maximize();
	}

	// method to get driver
	public static WebDriver getWebBrowser() {
		return driver;
	}

	// method to get list of elements
	public List<WebElement> getElementList(By locator) {
		return getWebBrowser().findElements(locator);
	}

	// method to apply sleep
	public void sleep(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {

		}
	}
	
	// method to get test case name
		public String getTCName() {
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
			StackTraceElement e = stacktrace[2];//maybe this number needs to be changed		
			String methodName = e.getMethodName();
			return methodName;		
		}
//	public static void main(String[] args) throws Exception {
//		PageObjectInit obj = new PageObjectInit();
//	}
}
