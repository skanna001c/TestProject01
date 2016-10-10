package com.comcast.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.comcast.reporting.Status;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;
import com.comcast.utils.TestSettings;

/**
 * Represents default page of the application
 * 
 */
public class LogInPage extends Page {
	private String url;
	private Page page;
	private TestSettings testSettings;

	Logger log = Logger.getLogger(LogInPage.class);

	@FindBy(xpath = "//a[@onclick='Signout()']")
	private WebElement btnSignout;

	@FindBy(id = "username")
	private WebElement txtUserName;

	@FindBy(id = "password")
	private WebElement txtPassword;

	@FindBy(id = "Login")
	private WebElement btnLogin;

	protected static String LOGIN_PAGE_TITLE = "Login | Salesforce";

	private boolean mstatus;

	public LogInPage(FrameworkContext context) {
		super(context);
	}

	@Override
	protected boolean isValidPage() {
		if (browser.getTitle().trim().equalsIgnoreCase(LOGIN_PAGE_TITLE)) {
			return true;
		}
		return false;
	}

	public boolean Signout() {
		return mstatus;
	}

	public boolean applicationLogin(String userName, String password, String domain) {
		mstatus = true;
		try {
			waitforPageLoadComplete();
			waitForElement(txtUserName);
			txtUserName.click();
			txtUserName.sendKeys(userName);
			report.reportDoneEvent("Enter username", "Entered username as-> " + userName);
			txtPassword.click();
			txtPassword.sendKeys(password);
			report.reportDoneEvent("Enter password", "Entered password as-> " + password.replaceAll(".", "*"));
			report.updateTestLog("Enter Login Details", "Login Details Entered", Status.SCREENSHOT);

			if (isElementPresent(btnLogin)) {
				iClick(btnLogin, null, "Click on Login button: Login page: LoginButton");
				report.reportDoneEvent("Click on Login", "Login Clicked successfully");
			}
			waitforPageLoadComplete();
			/*
			 * waitForElementDisappear(elementLoading); waitForElement(tabHome);
			 * if(isElementDisplayed(tabHome)){
			 * report.updateTestLog("Login to HomePage",
			 * "Logged into homepage Successfully", Status.SCREENSHOT); }else{
			 * report.reportFailEvent("Login to HomePage",
			 * "Login Unsuccessfull <Please check UN and PWD>"); }
			 */
		} catch (Exception Ex) {
			report.reportFailEvent("applicationLogin",
					"User Login NOT successful, EXCEPTION CAUGHT : " + Ex.getMessage());
			mstatus = false;
			log.info(Ex.getMessage());
		}
		return mstatus;
	}

	@Override
	protected void waitForPageLoad() {
	}

}
