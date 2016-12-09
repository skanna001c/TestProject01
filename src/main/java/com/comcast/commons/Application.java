package com.comcast.commons;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.comcast.pages.TemplateLoginPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.DataTable;
import com.comcast.utils.IDataDump;
import com.comcast.utils.IUserDetails;
import com.comcast.utils.TestSettings;

/**
 * @author hbolak01c
 *
 */
public class Application {

	private WebDriver browser;
	private SeleniumReport report;

	private String password;
	private String userName;
	private String env;
	private String domain;
	private TestSettings settings;
	private IUserDetails userDetails;
	private IDataDump dataDump;
	private DataTable dataSet;
	private FrameworkContext frameworkContext;

	public Application(FrameworkContext context) {
		this.frameworkContext = context;
		this.browser = context.getDriver();
		this.settings = context.getSettings();
		this.userDetails = context.getUserDetail();
		this.report = context.getReport();
		this.env = settings.getEnvironmentToTest().trim();
		this.dataDump = context.getDataDump();
		this.dataSet = context.getDataTable();

	}

	Logger log = Logger.getLogger(Application.class);

	
	/**
	 * applicationLogin
	 * 
	 * This method is used to launch the browser, navigate to the desired URL and Login to the application
	 * This only contains some helper code and the full method should be implemented as per the project requirements
	 * There are TODO's defined at appropriate places to easily udpate code
	 */
	public void applicationLogin() {
		String eMsg = null;
		try {
			String strRole;
			eMsg = "retreiving role value from data sheet";
			strRole = dataSet.getValue(""); //TODO - update the column value for role from data sheet
			eMsg = "getting the role from userdetails file";
			//The following data is read from UserDetails files located per environment in the src/test/resouces package
			String userString = userDetails.getValue(strRole);
			String arrUserString[] = userString.split(":");
			userName = arrUserString[0].trim();
			password = arrUserString[1].trim();

			String url = settings.getApplicationURL();
			eMsg = "Navigating to the Application url: " + url;
			log.info(eMsg);

			browser.get(url);

			eMsg = "logging in from login page";

			//TODO - update and implement the login method inside the TemplateLoginPage class
			(new TemplateLoginPage(frameworkContext)).login(userName, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("error in : " + eMsg + " Exception thrown is: " + e.getMessage());
			e.printStackTrace();
		}

	}
}