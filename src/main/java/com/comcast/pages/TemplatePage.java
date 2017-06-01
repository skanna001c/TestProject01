package com.comcast.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.comcast.commons.ApplicationPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.DataTable;

/**
 * @author ohegde001c
 * 
 *         Skeleton page for all application specfic pages
 */
public class TemplatePage extends ApplicationPage {

	Logger log = Logger.getLogger(TemplatePage.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	// Needed only for intellisense completion and avoiding spelling errors and
	// use with reflection for audit ....
	/**
	 * @author ohegde001c
	 *
	 *         Page element declaration class. Value of static strings should
	 *         match with web elements defined in TemplatePage.csv in
	 *         resources/locators folder
	 */
	public static class Locators {
		public static final String txtSearch = "txtSearch";
		public static final String btnSubmit = "btnSubmit";
		public static final String XXbtnSubmit = "XXbtnSubmit";
		public static final String XXtxtSearch = "XXtxtSearch";
	}

	/**
	 * @param context
	 * 
	 */
	public TemplatePage(FrameworkContext context) {
		super(context, "TemplatePage"); // TODO: get class name programmatically
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
	 * Method to check the Default cursor position once the Search Page is
	 * loaded
	 * 
	 * Following standards must be followed while writing the page methods:
	 * 
	 * 1. Should return boolean value of page method status
	 * 2. Should have log.trace("Start") in the beginning 
	 * 3. Should have log.trace("End") at the end
	 * 4. Should catch any runtime Exceptions and subsequently throw them to test method with appropriate message(Eg. eMsg)
	 * 5. Typical page method should not exceed 30 lines. Use helper methods to add re-usability
	 * 6. Use appropriate log.trace method to log contextual information in conditionals and iterations
	 * 7. Make use of recommended Page.java methods for web element interactions (To see a list all available Page methods, Press Ctrl + Space)
	 * 8. Commonly used methods across all pages must be moved to ApplicationPage.java
     *
	 * @return status
	 */
	public boolean templatePageMethod() {
		log.trace("Start");
		boolean isActiveElement = false;
		try {
			// Dummy method
			WebElement we = testLocatorVisible(Locators.txtSearch);
			isActiveElement = we.equals(browser.switchTo().activeElement());
			iReport(Locators.txtSearch, "Default cursor position on Search Screen",
					"Verifying default cursor position is on Universal search bar", isActiveElement);
		} catch (Exception e) {
			String eMsg = "templatePageMethod() Error: --- " + e.getMessage();
			log.error(eMsg);
			throw new RuntimeException(eMsg);
		}
		log.trace("End");
		
		return isActiveElement;
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
	public void launch() {
		String gurl = "https://google.com";
		String titleCheckStr = "google";
		int tmOutSec = 10;

		try {
			go(gurl, titleCheckStr, tmOutSec);
		} catch (Exception e) {
			String eMsg = "launch Failed: " + e.getMessage();
			log.error(eMsg);
			throw new RuntimeException(eMsg);
		}
	}

	public void googleSearch() {
		String srchStr = dataSet.getValue("Search_String");

		try {
			iEnterText(Locators.txtSearch, srchStr, "Enter Search Text");
			iClick(Locators.btnSubmit, null, "Search Button");
			report.reportPassEvent("clickSearchButton", "Google search was succesfull");
		} catch (Exception e) {
			String eMsg = "Search Failed: " + e.getMessage();
			log.error(eMsg);
			report.reportSoftFailEvent("clickSearchButton", "Google search was not succesfull");
			throw new RuntimeException(eMsg);
		}
	}

	public void googleSearchError() {
		String srchStr = dataSet.getValue("Search_String");

		try {
			iEnterText(Locators.XXtxtSearch, srchStr, "Enter Search Text");
			iClick(Locators.XXbtnSubmit, null, "Search Button");
			report.reportPassEvent("clickSearchButton", "Google search was successful");
		} catch (Exception e) {
			String eMsg = "Search Failed: " + e.getMessage();
			log.error(eMsg);
			report.reportSoftFailEvent("clickSearchButton", "Google search was not successful");
			throw new RuntimeException(eMsg);
		}
		sleep(5000);
	}

	/**
	 * 
	 */
	public boolean minimalViablelaunch() {
		boolean methodStatus = false;
		String gurl = "https://google.com";
		String titleCheckStr = "google";
		int tmOutSec = 10;
		methodStatus = go(gurl, titleCheckStr, tmOutSec);	
		return methodStatus;
	}

}
