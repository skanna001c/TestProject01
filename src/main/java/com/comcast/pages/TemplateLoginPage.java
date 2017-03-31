package com.comcast.pages;

import org.apache.log4j.Logger;

import com.comcast.commons.ApplicationPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.DataTable;

/**
 * @author ohegde001c
 * 
 *         Skeleton page for all application specfic pages
 */
public class TemplateLoginPage extends ApplicationPage {

	Logger log = Logger.getLogger(TemplateLoginPage.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	/**
	 * @author ohegde001c
	 *
	 *         Page element declaration class. Value of static strings should
	 *         match with web elements defined in TemplatePage.csv in
	 *         resources/locators folder
	 */
	public static class Locators {
		public static final String txtUserName = "txtUserName";
		public static final String txtPassword = "txtPassword";
		public static final String btnSubmit = "btnSubmit";
	}

	/**
	 * @param context
	 * 
	 */
	public TemplateLoginPage(FrameworkContext context) {
		super(context, "TemplateLoginPage");
		report = context.getReport();
		this.context = context;
		this.dataSet = context.getDataTable();
	}

	/*
	 * Check to ensure that you are on the required page
	 */
	@Override
	protected boolean isValidPage() {
		return true;
	}

	/*
	 * Not used currently
	 */
	@Override
	protected void waitForPageLoad() {
	}

	/**
	 * Your page methods start here. Add all required methods for this page.
	 * 
	 * Method names should represent the business process executed.
	 * 
	 * All business process methods called from test methods should throw
	 * exceptions on non recoverable errors.
	 * 
	 * Following methods are example page methods.
	 */
	public void login(String userName, String password) {
		try {
			enterText(Locators.txtUserName, userName, "UserName entered as:" + userName);
			enterText(Locators.txtPassword, password, "Password entered as:" + password);
			iClick(Locators.btnSubmit, null, "Submit button clicked");
			// TODO: Validate if login was successful

		} catch (Exception e) {
			String eMsg = "Login Failed: " + e.getMessage();
			log.error(eMsg);
			throw new RuntimeException(eMsg);
		}
		log.debug("Login Successful");
	}
}
