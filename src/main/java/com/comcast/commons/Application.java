package com.comcast.commons;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;

import com.comcast.pages.LogInPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.IUserDetails;
import com.comcast.utils.TestSettings;

public class Application {
	private SeleniumReport report;
	private String password;
	private String domain;
	private TestSettings settings;
	private IUserDetails userDetails;
	private FrameworkContext frameworkContext;
	private WebDriver browser;
	private String appUrl;

	public Application(FrameworkContext context) {
		this.frameworkContext = context;
		context.getDriver();
		this.settings = context.getSettings();
		this.userDetails = context.getUserDetail();
		this.report = context.getReport();
		this.browser = context.getDriver();
		settings.getEnvironmentToTest();
		this.appUrl = settings.getApplicationURL();
		context.getDataDump();

	}
	
	public void beforeMethodGetUserndURL(Method testName) {
		String userName = null;
		// check for rerun and the status of the method
		if (userDetails.containsTestName(testName.getName().trim())) {
			userName = userDetails.getUserName(testName.getName());
		}

		if (userName == null || userName == "") {
			userName = userDetails.getPassword("default");
		}

		browser.get(appUrl);
		
		this.password = userDetails.getPassword(userName);
		this.domain = settings.getAPPDOMAIN();
		(new LogInPage(frameworkContext)).applicationLogin(userName, this.password, this.domain);
		report.updateTestLog("Application launch", "Application has been launched", Status.SCREENSHOT);

	}

}
