package com.y2tek.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.y2tek.frameworkexception.FrameWorkException;

public class DriverFactory {

	WebDriver driver;
	OptionsManager optionsManager;
	public static String highlightElement;
	static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser").trim();
		// browser will be supplied from cmd
		// String browserName = System.getProperty("browser");
		System.out.println("Browser name : " + browserName);

		highlightElement = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);

		switch (browserName.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(driver);
			break;
		case "edge":
			driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(driver);
			break;
		case "firefox":
			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(driver);
			break;
		default:
			System.out.println("Please pass the right browser -----" + browserName);
			throw new FrameWorkException("NOBROWSEREXCEPTION");
		}

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProp() {

		Properties prop = new Properties();
		FileInputStream ip = null;
		try {
			ip = new FileInputStream("./src/main/resources/config/config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

//	public Properties initProp() {
//
//		// mvn clean install
//		// mvn clean install -Denv="qa"
//
//		Properties prop = new Properties();
//		FileInputStream ip = null;
//
//		String envName = System.getProperty("env");
//		System.out.println("Environment name is:" + envName);
//		try {
//			if (envName == null) {
//				System.out.println("No env is given .....hence running it on QA env...");
//				ip = new FileInputStream("./src/main/resources/config/config.properties");
//			} else {
//				System.out.println("Running test cases on environment: " + envName);
//				switch (envName.toLowerCase().trim()) {
//				case "qa":
//					ip = new FileInputStream("./src/main/resources/config/qa.config.properties");
//					break;
//				case "dev":
//					ip = new FileInputStream("./src/main/resources/config/dev.config.properties");
//					break;
//				case "stage":
//					ip = new FileInputStream("./src/main/resources/config/stage.config.properties");
//					break;
//				case "uat":
//
//					ip = new FileInputStream("./src/main/resources/config/uat.config.properties");
//					break;
//
//				default:
//					System.out.println("Plz pass the right env name : " + envName);
//					throw new FrameWorkException("NOVALIDENVGIVEN");
//				}
//			}
//		}
//
//		catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		try {
//			prop.load(ip);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return prop;
//	}

	// take screenshot
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}