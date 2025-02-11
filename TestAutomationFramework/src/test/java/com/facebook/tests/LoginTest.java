package com.facebook.tests;

import org.junit.AfterClass;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.facebook.genericPage.MasterPage;
import com.facebook.pages.LoginPage;

public class LoginTest {

	@Test
	public void loginTest() throws Exception {

		LoginPage lp = new LoginPage();

		lp.clickEmail();
		lp.enterEmail();
		lp.clickPassword();
		lp.enterPassword();
		lp.getFacebookText();
		lp.clearEmail();
		lp.clearPassword();
		lp.readExcelData("EmailOrPhone", 3, 1, "ExcelTestDataSheetName");
		lp.readExcelData("Password", 3, 2, "ExcelTestDataSheetName");
	}

	@AfterMethod
	public void takeScreenshot(ITestResult result2) throws Exception {
		LoginPage lp = new LoginPage();
		lp.captureScreenshot(result2);
	}

	@AfterClass
	public static void closeLoginPage() {
		MasterPage.driver.close();
	}
}
