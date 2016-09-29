package com.comcast.century.cso.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.comcast.century.data.LoginDetails;
import com.comcast.reporting.Status;
import com.comcast.utils.ComcastTest.FrameworkContext;
import com.comcast.utils.Page;
import com.comcast.utils.SeleniumReport;
import com.comcast.utils.TestSettings;

public class LogInPage extends Page {
	
	private String url;
	private Page page;
	private TestSettings testSettings;
	
	protected static String LOGIN_PAGE_TITLE = "Login Page";

	public LogInPage(FrameworkContext context) {
		super(context);	
	}

	@Override
	protected boolean isValidPage() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void waitForPageLoad() {
		// TODO Auto-generated method stub
		
	}

	
	@FindBy(xpath="//input[@id='username']")
	private WebElement txtUserName;
		
		
	@FindBy(xpath="//input[@id='password']")
	private WebElement txtPassword;
		
		
	@FindBy(xpath="//select[@id='domainList']")
	private WebElement lstDomain;
	
	@FindBy(xpath="//input[@id='warn']")
	private WebElement chkBxWarning;	
	
	@FindBy(xpath="//input[@id='logButton']")
	private WebElement btnLogin;
	
	//*[@id='workorder']
	
	@FindBy(xpath="//span[@id='workorder']")
	private WebElement tabWorkOrder;
	
	
	
	public void applicationLogin(LoginDetails loginInfo) {
		try{
			shortWaitForElement(txtUserName);
			txtUserName.click();
			txtUserName.sendKeys(loginInfo.userName);
			report.reportDoneEvent("Enter username","Entered username as-> "+loginInfo.userName);				
			txtPassword.click();
			txtPassword.sendKeys(loginInfo.password);
			report.reportDoneEvent("Enter password","Entered password as-> "+loginInfo.password.replaceAll(".", "*"));			
			 
			new Select(lstDomain).selectByVisibleText(loginInfo.domain);
			report.reportDoneEvent("Select Domain","Selected domain as-> "+loginInfo.domain.replaceAll(".", "*"));			
			
			if (! chkBxWarning.isSelected()){
			    chkBxWarning.click();
			}
			report.reportDoneEvent("Check on Terms and Conditions","Checked Terms and Conditions");				
			report.updateTestLog("Enter Login Details","Entered Login Details",Status.SCREENSHOT);		
			
			if(isElementPresent(btnLogin)){
				btnLogin.click();
				report.reportDoneEvent("Click on Login", "Clicked successfully");
			}
			/*waitForElement(userHomePage);
			if(isElementPresent(userHomePage)){
				report.updateTestLog("LogintoHomePage", "logged into the homepage Successfully", Status.SCREENSHOT);
			}else{
				report.reportFailEvent("LoginToHomePage", "Login Unsuccessfull <Please check UN and PWD>");
			}*/
			waitForElement(tabWorkOrder);			
			if(isElementPresent(tabWorkOrder)){
				report.updateTestLog("LogintoCSO", "Logged into the CSO Successfully", Status.SCREENSHOT);
			}else{
				report.reportFailEvent("LoginToCSO", "Login Unsuccessfull <Please check UN and PWD>");
			}			
		}catch(Exception Ex){
			report.reportFailEvent("applicationLogin", "User Login NOT successful, EXCEPTION CAUGHT : " + Ex.getMessage());
			throw Ex;
		}	
		//return new HomePageCM(browser,report);			
	}	
	
	
}
