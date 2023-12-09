package com.qa.demo.base;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.demo.factory.DriverFactory;
import com.qa.demo.pages.DashBoardPage;
import com.qa.demo.pages.LoginPage;

public class BaseTest {

	protected WebDriver driver;
	protected LoginPage loginPage;
	protected DashBoardPage dbPage;
	protected DriverFactory df;
	protected Properties prop;
	protected SoftAssert softAssert;

	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
