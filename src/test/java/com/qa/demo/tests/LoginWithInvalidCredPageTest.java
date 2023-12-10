package com.qa.demo.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.demo.base.BaseTest;
import com.qa.demo.utils.AppConstants;
import com.qa.demo.utils.ExcelUtil;

public class LoginWithInvalidCredPageTest extends BaseTest {

//	@DataProvider(name = "loginData")
//	public Object[][] loginData() {
//		return new Object[][] { { "abc", "123" }, { "def", "456" }, { "ghij", "789" } };
//
//	}

	@DataProvider(name = "loginExcelData")
	public Object[][] loginData() {
		Object[][] loginData = ExcelUtil.getTestData(AppConstants.LOGIN_SHEET_NAME);
		return  loginData;
	}

	@Test(dataProvider = "loginExcelData")
	public void loginTest(String username, String pwd) {
		dbPage = loginPage.doLogin(username, pwd);
	}

}
