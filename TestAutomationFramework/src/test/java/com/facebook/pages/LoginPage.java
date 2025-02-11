package com.facebook.pages;

import com.facebook.genericPage.CommonMethods;

public class LoginPage extends CommonMethods {

	public LoginPage() throws Exception {
		super();

	}

	// Click on email field
	public void clickEmail() {
		clickWebElement("EmailOrPhone");
		handleLogger("LoginPage", "Clicked Email Field");
	}

	// Enter email
	public void enterEmail() {
		enterData("EmailOrPhone", "TestData1");
		handleLogger("LoginPage", "entered EmailOrPhone");
	}

	// Clear an email field
	public void clearEmail() {
		clearData("EmailorPhone");
		handleLogger("LoginPage", "Cleared Email Field");
	}

	// Click on password field
	public void clickPassword() {
		clickWebElement("Password");
		handleLogger("LoginPage", "Clicked Password Field");
	}

	// Enter password
	public void enterPassword() {
		enterData("Password", "TestData2");
		handleLogger("LoginPage", "Entered Password");
	}

	// Clear password field
	public void clearPassword() {
		clearData("Password");
		handleLogger("LoginPage", "Cleared Password Field");
	}

	// get Facebook text
	public void getFacebookText() {
		getWebElementText("FacebookText");
		handleLogger("LoginPage", "Fetched Facebook Text");
	}

	// click Login button
	public void clickLoginButton() {
		clickWebElement("LoginButton");
		handleLogger("LoginPage", "Clicked Login Button");
	}

}
