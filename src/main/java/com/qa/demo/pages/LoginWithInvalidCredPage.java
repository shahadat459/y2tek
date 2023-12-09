package com.qa.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.demo.pages.DashBoardPage;
import com.qa.demo.utils.AppConstants;
import com.qa.demo.utils.ElementUtil;

public class LoginWithInvalidCredPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	public LoginWithInvalidCredPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	// 2. private By locators:
	private By emailId = By.name("username");
	private By password = By.name("password");
	private By loginBtn = By.xpath("//button[text()='Sign In']");
	public String getLoginPageTitle() {
		return eleUtil.waitForTitleIsAndCapture(AppConstants.LOGIN_PAGE_TITLE_VALUE, AppConstants.SHORT_DEFAULT_WAIT);
	}

	public DashBoardPage doLogin(String userName, String pwd) {
		System.out.println("correct creds are : " + userName + ":" + pwd);
		eleUtil.waitForElementVisible(emailId, AppConstants.LONG_DEFAULT_WAIT);
		eleUtil.doSendKeys(emailId, userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		// return the next landing page -- AccountsPage -- page chaining model
		return new DashBoardPage();
	}

}
