package com.kosmos.core;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserClientFactory {
	static File file = null;

	public static WebDriver getChromeBrowser() {
		file = new File("src//main//resources//chromedriver.exe");
		String driverPath = file.getAbsolutePath();
		System.setProperty("webdriver.chrome.driver", driverPath);
		return new ChromeDriver();
	}
	public static WebDriver getFirefoxBrowser() {
		file = new File("src//main//resources//executables//geckodriver.exe");
		String driverPath = file.getAbsolutePath();
		System.setProperty("webdriver.gecko.driver", driverPath);
		return new FirefoxDriver();
	}

}
