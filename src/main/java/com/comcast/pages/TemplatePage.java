package com.comcast.pages;

import org.apache.log4j.Logger;

import com.comcast.commons.ApplicationPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.transactions.MetricsCollector;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.DataTable;

/**
 * @author ohegde001c
 * 
 * Skeleton page for all application specfic pages
 */
public class TemplatePage extends ApplicationPage {

	Logger log = Logger.getLogger(TemplatePage.class);
	SeleniumReport report; 
	FrameworkContext context;
	DataTable dataSet;


	// Needed only for intellisense completion and avoiding spelling errors and use with reflection for audit ....
	/**
	 * @author ohegde001c
	 *
	 * Page element declaration class. Value of static strings should match with web elements
	 * defined in TemplatePage.csv in resources/locators folder 
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
	 * Your page methods start here. Add all required methods for this page.
	 * 
	 * Method names should represent the business process executed.
	 * 
	 * All business process methods called from test methods
	 * should throw exceptions on non recoverable errors.
	 *    
	 * Following methods are example page methods. 	
	 */
	public void launch() {
		String gurl = "https://google.com";
		String titleCheckStr = "google";
		int tmOutSec = 10;
		MetricsCollector mc = new MetricsCollector();

		try {
			mc.clearResourceTiming(browser);
			go(gurl, titleCheckStr, tmOutSec);
			mc.collectPerformanceData("Google test transaction", browser);
		} catch (Exception e) {
			String eMsg = "launch Failed: " + e.getMessage();
			log.error(eMsg);
			throw new RuntimeException(eMsg);
		}
	}

	public void googleSearch() {
		String srchStr = dataSet.getValue("Search_String");

		try {
		enterText(Locators.txtSearch, srchStr, "Enter Search Text");
			iClick(Locators.btnSubmit, null, "Search Button");
			report.reportPassEvent("clickSearchButton", "Google search was succesfull");
		} catch (Exception e) {
			String eMsg = "Search Failed: " + e.getMessage();
			log.error(eMsg);
			report.reportFailEvent("clickSearchButton", "Google search was not succesfull");
			throw new RuntimeException(eMsg);
		}
	}

	public void googleSearchError() {
		String srchStr = dataSet.getValue("Search_String");

		try {
			enterText(Locators.XXtxtSearch, srchStr,  "Enter Search Text");
			iClick(Locators.XXbtnSubmit, null, "Search Button");
			report.reportPassEvent("clickSearchButton", "Google search was successful");
		} catch (Exception e) {
			String eMsg = "Search Failed: " + e.getMessage();
			log.error(eMsg);
			report.reportFailEvent("clickSearchButton", "Google search was not successful");
			throw new RuntimeException(eMsg);
		}
		sleep(5000);
	}

}
