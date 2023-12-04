package com.y2tek.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.y2tek.pages.DashBoardPage;
import com.y2tek.utils.AppConstants;
import com.y2tek.utils.ElementUtil;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	// 2. private By locators:
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");

	public String getLoginPageTitle() {
		return eleUtil.waitForTitleIsAndCapture(AppConstants.LOGIN_PAGE_TITLE_VALUE, AppConstants.SHORT_DEFAULT_WAIT);
	}

	public DashBoardPage doLogin(String userName, String pwd) {
		System.out.println("correct creds are : " + userName + ":" + pwd);
		eleUtil.waitForElementVisible(emailId, AppConstants.MEDIUM_DEFAULT_WAIT);
		eleUtil.doSendKeys(emailId, userName);
		eleUtil.doSendKeys(password, pwd);
		// eleUtil.doClick(loginBtn);
		// return the next landing page -- AccountsPage -- page chaining model
		return new DashBoardPage();
	}

}
