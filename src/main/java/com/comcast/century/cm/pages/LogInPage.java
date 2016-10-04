package com.comcast.century.cm.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	
	@FindBy(xpath="//a[@onclick='Signout()']")
	private WebElement btnSignout;
	
	@FindBy(id = "username")
	private WebElement txtUserName;
	
	@FindBy(id = "password")
	private WebElement txtPassword;
	
	@FindBy(id = "domainList")
	private WebElement lstDomain;	
		
	@FindBy(id = "warn")
	private WebElement chkBxWarning;	
	
	@FindBy(id = "logButton")
	private WebElement btnLogin;	
	
	@FindBy(xpath="//a[.='Home']")
	private WebElement tabHome;
	
	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading ;
	
	@FindBy(id = "workorder")
	private WebElement tabWorkOrder;
	
	@FindBy(id = "lblUserName")
	private WebElement userHomePage;
	
	@FindBy(id = "txtAccNo")
	private WebElement accountNumber;
	
	@FindBy(id = "autocomplete")
	private WebElement searchBox;
	
	protected static String LOGIN_PAGE_TITLE = "Login Page";
	
	private boolean mstatus;
		
	
	public LogInPage(FrameworkContext context){
		super(context);
	}
		
	@Override
	protected boolean isValidPage() {
		if (browser.getTitle().trim().equalsIgnoreCase(LOGIN_PAGE_TITLE)) {
			return true;
		}
		return false;
	}
	
	@Override
	protected void waitForPageLoad() {
		try{
		new WebDriverWait(browser,30).

		until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='logButton']")));	

	}catch(Exception e){
		report.reportFailEvent("Check Century Application Login Page","Login Page is not displayed");
	}
		
		
	}
		
	public boolean Signout(){
		mstatus = true;
		try{
			waitforPageLoadComplete();
			browser.switchTo().defaultContent();
			if (waitForElement(btnSignout,5)){
			browser.manage().deleteAllCookies();
			iClick(btnSignout,txtUserName,"Click on signout: Home page: SignoutButton");		
			waitforPageLoadComplete();
			waitForElement(txtUserName);
			}
		}
		catch(Exception Ex){
			report.reportFailEvent("applicationLogin", "User Login NOT successful, EXCEPTION CAUGHT : " + Ex.getMessage());
			mstatus = false;
			log.info(Ex.getMessage());
		}	
		return mstatus;
	}
	public boolean applicationLoginCM(String userName, String password, String domain) {
		mstatus = true;
		try{
			waitforPageLoadComplete();			
			waitForElement(txtUserName);
			txtUserName.click();
			txtUserName.sendKeys(userName);
			report.reportDoneEvent("Enter username","Entered username as-> " +userName);				
			txtPassword.click();
			txtPassword.sendKeys(password);
			report.reportDoneEvent("Enter password","Entered password as-> " +password.replaceAll(".", "*"));			
		 
			new Select(lstDomain).selectByVisibleText(domain);
			report.reportDoneEvent("Select Domain","Selected domain as-> "+domain);			
			
			if (! chkBxWarning.isSelected()){
			    chkBxWarning.click();
			}
			report.reportDoneEvent("Check on Terms and Conditions","Checked Terms and Conditions");				
			report.updateTestLog("Enter Login Details","Login Details Entered",Status.SCREENSHOT);		
			
			if(isElementPresent(btnLogin)){
				iClick(btnLogin,tabHome,"Click on Login button: Login page: LoginButton");				
				report.reportDoneEvent("Click on Login", "Login Clicked successfully");
			}
			waitforPageLoadComplete();
			waitForElementDisappear(elementLoading);
			waitForElement(tabHome);			
			if(isElementDisplayed(tabHome)){
				report.updateTestLog("Login to HomePage", "Logged into homepage Successfully", Status.SCREENSHOT);
			}else{
				report.reportFailEvent("Login to HomePage", "Login Unsuccessfull <Please check UN and PWD>");
			}			
		}catch(Exception Ex){
			report.reportFailEvent("applicationLogin", "User Login NOT successful, EXCEPTION CAUGHT : " + Ex.getMessage());
			mstatus = false;
			log.info(Ex.getMessage());
		}	
		return mstatus;
	}	
	
	public boolean applicationLoginCSO(String userName, String password, String domain) {
		mstatus = true;
		try{
			waitforPageLoadComplete();			
			waitForElement(txtUserName);
			txtUserName.click();
			txtUserName.sendKeys(userName);
			report.reportDoneEvent("Enter username","Entered username as-> " +userName);				
			txtPassword.click();
			txtPassword.sendKeys(password);
			report.reportDoneEvent("Enter password","Entered password as-> " +password.replaceAll(".", "*"));			
			 
			new Select(lstDomain).selectByVisibleText(domain);
			report.reportDoneEvent("Select Domain","Selected domain as-> "+domain);			
			
			if (! chkBxWarning.isSelected()){
			    chkBxWarning.click();
			}
			report.reportDoneEvent("Check on Terms and Conditions","Checked Terms and Conditions");				
			report.updateTestLog("Enter Login Details","Login Details Entered",Status.SCREENSHOT);		
			
			if(isElementPresent(btnLogin)){
				iClick(btnLogin,null,"Click on Login button: Login page: LoginButton");
				waitforPageLoadComplete();
				report.reportDoneEvent("Click on Login", "Login Clicked successfully");
			}
				
		}catch(Exception Ex){
			report.reportFailEvent("applicationLogin", "User Login NOT successful, EXCEPTION CAUGHT : " + Ex.getMessage());
			mstatus = false;
			log.info(Ex.getMessage());
		}	
		return mstatus;
	}	
	

}
	
	
	