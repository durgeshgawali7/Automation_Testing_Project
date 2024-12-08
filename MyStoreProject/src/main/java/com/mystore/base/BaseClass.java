package com.mystore.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.mystore.utility.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static Properties prop;

	// Declare ThreadLocal Driver
	//	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

	public static WebDriver driver;

	//loadConfig method is to load the configuration
	@BeforeSuite(groups = { "Smoke", "Sanity", "Regression" })
	public void loadConfig() {
		ExtentManager.setExtent();
		DOMConfigurator.configure("log4j.xml");

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "\\Configuration\\config.properties");
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//	public static WebDriver getDriver() {
	//		// Get Driver from threadLocalmap
	//		return driver.get();
	//	}

	public void launchApp(String browserName) {

		// String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("Chrome")) {

			System.setProperty("webdriver.chrome.driver", "D:\\Testing\\Projects\\MyProject\\MyStoreProject\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("FireFox")) {

			System.setProperty("webdriver.chrome.driver", "D:\\Testing\\Projects\\MyProject\\MyStoreProject\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("Edge")) {

			System.setProperty("webdriver.chrome.driver", "D:\\Testing\\Projects\\MyProject\\MyStoreProject\\Drivers\\msedgedriver.exe");
			driver = new InternetExplorerDriver();

		}
		//Maximize the screen
		driver.manage().window().maximize();
		//Delete all the cookies
		driver.manage().deleteAllCookies();
		//Implicit TimeOuts
		driver.manage().timeouts().implicitlyWait
		(Integer.parseInt(prop.getProperty("implicitWait")),TimeUnit.SECONDS);
		//PageLoad TimeOuts
		driver.manage().timeouts().pageLoadTimeout
		(Integer.parseInt(prop.getProperty("pageLoadTimeOut")),TimeUnit.SECONDS);
		//Launching the URL
		driver.get(prop.getProperty("url"));
	}

	@AfterSuite(groups = { "Smoke", "Regression","Sanity" })
	public void afterSuite() {
		ExtentManager.endReport();
	}

}
