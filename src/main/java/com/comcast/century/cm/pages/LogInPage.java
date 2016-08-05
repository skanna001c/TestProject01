package com.comcast.century.cm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.comcast.reporting.*;
import com.comcast.century.data.LoginDetails;
import com.comcast.utils.PerfTransaction;
import com.comcast.utils.SeleniumReport;

import com.comcast.utils.TestSettings;
import com.comcast.utils.TestUtils;

/**
 * Represents default page of the application
 * 
 */
public class LogInPage extends Page {
	private String url;
	private Page page;
	private TestSettings testSettings;

		
	//@FindBy(xpath="//*[@id='username']")
	@FindBy(xpath="//input[@id='username']")
	private WebElement txtUserName;
	
	//@FindBy(xpath="//*[@id='password']")
	@FindBy(xpath="//input[@id='password']")
	private WebElement txtPassword;
	
	//select[@id='domainList']
	@FindBy(xpath="//select[@id='domainList']")
	private WebElement lstDomain;	
	
	
	@FindBy(xpath="//input[@id='warn']")
	//@FindBy(xpath="//*[@type='button']")
	private WebElement chkBxWarning;	
	
	@FindBy(xpath="//input[@id='logButton']")
	private WebElement btnLogin;	
	
	//a[.='Home']
	@FindBy(xpath="//a[.='Home']")
	private WebElement tabHome;
	
	
	@FindBy(xpath="//span[@id='workorder']")
	private WebElement tabWorkOrder;
	/*@FindBy(xpath="//*[@class='style39']/input")
	private WebElement btnSubmit;	
	*/
	@FindBy(id="lblUserName")
	private WebElement userHomePage;
	
	
	@FindBy(id="txtAccNo") //TextField
	private WebElement accountNumber;
	
	@FindBy(id = "autocomplete")
	private WebElement searchBox;
	
	protected static String LOGIN_PAGE_TITLE = "Login Page";
		
	public LogInPage(WebDriver browser, SeleniumReport report) {
		super(browser, report);		
	}
		
	@Override
	protected boolean isValidPage() {
		if (browser.getTitle().trim().equalsIgnoreCase(LOGIN_PAGE_TITLE)) {
			return true;
		}
		return false;
	}
	
	@Override
	protected void waitForPageLoad() {try{
		new WebDriverWait(browser,30).

		until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='logButton']")));	

	}catch(Exception e){
		report.reportFailEvent("Check Century Application Login Page","Login Page is not displayed");
	}
		
		
	}
	
	/*@FindBy(id = "ntid")
	private WebElement ntid;
	
	@FindBy(id = "inputPassword")
	private WebElement passwrd;
	


	@FindBy(xpath="//*[@class='style39']/input")
	//@FindBy(xpath="//*[@type='button']")

	private WebElement btnSubmit2;
	
	@FindBy(xpath="//*[@type='submit']")
	private WebElement btnSubmit3;*/

	@PerfTransaction(name="Login")
	@Test
	public void applicationLogin(LoginDetails loginInfo) {
		try{
			shortWaitForElement(txtUserName);
			txtUserName.click();
			txtUserName.sendKeys(loginInfo.userName);
			report.reportDoneEvent("Enter username","Entered username as-> " +loginInfo.userName);				
			txtPassword.click();
			txtPassword.sendKeys(loginInfo.password);
			report.reportDoneEvent("Enter password","Entered password as-> " +loginInfo.password.replaceAll(".", "*"));			
			 
			new Select(lstDomain).selectByVisibleText(loginInfo.domain);
			report.reportDoneEvent("Select Domain","Selected domain as-> "+loginInfo.domain);			
			
			if (! chkBxWarning.isSelected()){
			    chkBxWarning.click();
			}
			report.reportDoneEvent("Check on Terms and Conditions","Checked Terms and Conditions");				
			report.updateTestLog("Enter Login Details","Login Details Entered",Status.SCREENSHOT);		
			
			if(isElementPresent(btnLogin)){
				btnLogin.click();
				report.reportDoneEvent("Click on Login", "Login Clicked successfully");
			}
			/*waitForElement(userHomePage);
			if(isElementPresent(userHomePage)){
				report.updateTestLog("LogintoHomePage", "logged into the homepage Successfully", Status.SCREENSHOT);
			}else{
				report.reportFailEvent("LoginToHomePage", "Login Unsuccessfull <Please check UN and PWD>");
			}*/
			waitForElement(tabHome);			
			if(isElementPresent(tabHome)){
				report.updateTestLog("Login to HomePage", "Logged into homepage Successfully", Status.SCREENSHOT);
			}else{
				report.reportFailEvent("Login to HomePage", "Login Unsuccessfull <Please check UN and PWD>");
			}			
		}catch(Exception Ex){
			report.reportFailEvent("applicationLogin", "User Login NOT successful, EXCEPTION CAUGHT : " + Ex.getMessage());
			throw Ex;
		}	
		//return new HomePageCM(browser,report);			
	}	
	

	/*public HomePage applicationLoginUAT(LoginDetails loginInfo) {
		try{
			shortWaitForElement(txtUserName);
			txtUserName.click();
			txtUserName.sendKeys(loginInfo.userName);
			report.reportDoneEvent("Enter username","Entered username as-> "+loginInfo.userName);				
			txtPassword.click();
			txtPassword.sendKeys(loginInfo.password);
			report.reportDoneEvent("Enter password","Entered password as-> "+loginInfo.password.replaceAll(".", "*"));				
			report.updateTestLog("Enter UserName&Password","Entered UserName and Password",Status.SCREENSHOT);		
			if(isElementPresent(btnSubmit2)){
				btnSubmit2.click();
			}else btnSubmit3.click();
			report.reportDoneEvent("Click on Submit", "Clicked successfully");
			sleep(5000);
			waitForElement(userHomePage);
			if(isElementPresent(userHomePage)){
				report.updateTestLog("LogintoHomePage", "logged into the homepage Successfully", Status.SCREENSHOT);
			}else{
				report.reportFailEvent("LoginToHomePage", "Login Unsuccessfull <Please check UN and PWD>");
			}
			waitForElement(accountNumber);			
			if(isElementPresent(accountNumber)){
				report.updateTestLog("LogintoHomePage", "logged into the homepage Successfully", Status.SCREENSHOT);
			}else{
				report.reportFailEvent("LoginToHomePage", "Login Unsuccessfull <Please check UN and PWD>");
			}			
		}catch(Exception Ex){
			report.reportFailEvent("applicationLogin", "User Login NOT successful, EXCEPTION CAUGHT : " + Ex.getMessage());
			throw Ex;
		}	
		return new HomePage(browser,report);			
	}	
*/	//==========================For RC related TC Login ===================================================
	/*public HomePage applicationQARCLogin(String userid, String Password) {
		try{
			shortWaitForElement(ntid);
			ntid.click();
			ntid.sendKeys(userid);
			report.reportDoneEvent("Enter username","Entered username as-> "+userid);				
			passwrd.click();
			passwrd.sendKeys(Password);
			report.reportDoneEvent("Enter password","Entered password as-> "+Password.replaceAll(".", "*"));				
			report.updateTestLog("Enter UserName&Password","Entered UserName and Password",Status.SCREENSHOT);
			btnSubmit2.click();
			report.reportDoneEvent("Click on Submit", "Clicked successfully");
			sleep(5000);
			waitForElement(userHomePage);
			if(isElementPresent(userHomePage)){
				report.updateTestLog("LogintoHomePage", "logged into the homepage Successfully", Status.SCREENSHOT);
			}else{
				report.reportFailEvent("LoginToHomePage", "Login Unsuccessfull <Please check UN and PWD>");
			}
			waitForElement(accountNumber);			
			if(isElementPresent(accountNumber)){
				report.updateTestLog("LogintoHomePage", "logged into the homepage Successfully", Status.SCREENSHOT);
			}else{
				report.reportFailEvent("LoginToHomePage", "Login Unsuccessfull <Please check UN and PWD>");
			}			
		}catch(Exception Ex){
			report.reportFailEvent("applicationLogin", "User Login NOT successful, EXCEPTION CAUGHT : " + Ex.getMessage());
			throw Ex;
		}	
		return new HomePage(browser,report);			
	}	
	
	*//**
     *  Method to handle basic authentication in CI Login
     * @author     : Vishwas Patil (vvinay00c)
    ***//*
	
	public HomePage openRQAapplicationBasicURL(String strUserName, String strPassword) {
		
			try{
			//String strUserName = loginInfo.userID;
			//String strPassword = loginInfo.password;
			String strAuthUrl = strUserName +  ":" + strPassword + "@mcw";
			String[] arrurl = url.split("mcw");
			arrurl[0] = arrurl[0] + strAuthUrl;
			String authUrl = arrurl[0] + arrurl[1]; 			
			browser.get(authUrl);
			System.out.println(authUrl);
			handleIECertificateErrorAndSafariCertificatePopup(browser);
			browser.manage().window().maximize();
			browser.get(url);
			report.updateTestLog("Open Resiliency QA Application", "Application has been successfully launched", Status.DONE);
		}catch(Exception Ex){
			report.reportFailEvent("Exception Caught", "Message is->"+Ex.getMessage());
		}
		
		return new HomePage(browser,report);
		
	}
	

	*//**	
	 * Handle Certification error while opening HTTPS website
	 * @param browser
	 *//*
	public void handleIECertificateErrorAndSafariCertificatePopup(WebDriver browser) {
		try{
			if(page.isElementPresent(By.id("overridelink"))){
				String browserName = testSettings.getBrowser();
				String url= browser.getCurrentUrl();
				if(browserName.equalsIgnoreCase("iexplore") && url.contains("http")){
					browser.navigate().to("javascript:document.getElementById('overridelink').click()");
				}		
			}}
		catch(RuntimeException ex){
		}
	}
	public HomePage applicationLoginKM(LoginDetails loginInfo) {
		try{
			shortWaitForElement(txtEmailAddress);
			txtEmailAddress.click();
			txtEmailAddress.sendKeys(loginInfo.userName);
			report.reportDoneEvent("Enter username","Entered username as-> "+loginInfo.userName);				
			txtPassword.click();
			txtPassword.sendKeys(loginInfo.password);
			report.reportDoneEvent("Enter password","Entered password as-> "+loginInfo.password.replaceAll(".", "*"));				
			report.updateTestLog("Enter UserName&Password","Entered UserName and Password",Status.SCREENSHOT);
			btnSubmit.click();
			report.reportDoneEvent("Click on Submit", "Clicked successfully");
			sleep(5000);
			waitForElement(userHomePage);
			if(isElementPresent(userHomePage)){
				report.updateTestLog("LogintoHomePage", "logged into the homepage Successfully", Status.SCREENSHOT);
			}else{
				report.reportFailEvent("LoginToHomePage", "Login Unsuccessfull <Please check UN and PWD>");
			}
			waitForAnyElement(userHomePage, searchBox);
			sleep(3000);			
			if(isElementPresent(searchBox)||isElementPresent(userHomePage)){
				report.updateTestLog("LogintoKMHomePage", "logged into KM Application Successfully", Status.SCREENSHOT);
			}else{
				report.reportFailEvent("LoginToKMhomePage", "Login Unsuccessfull <Please check UN and PWD>");
			}			
		}catch(Exception Ex){
			report.reportFailEvent("applicationLogin", "User Login NOT successful, EXCEPTION CAUGHT : " + Ex.getMessage());
			throw Ex;
		}	
		return new HomePage(browser,report);			
	}
	
	
	
		

	

	public HomePage applicationLoginKM(IMApplicationDetails iMApplicationDetails) {
		try{
			shortWaitForElement(txtEmailAddress);
			txtEmailAddress.click();
			txtEmailAddress.sendKeys(iMApplicationDetails.userName);
			report.reportDoneEvent("Enter username","Entered username as-> "+iMApplicationDetails.userName);				
			txtPassword.click();
			txtPassword.sendKeys(iMApplicationDetails.password);
			report.reportDoneEvent("Enter password","Entered password as-> "+iMApplicationDetails.password.replaceAll(".", "*"));				
			report.updateTestLog("Enter UserName&Password","Entered UserName and Password",Status.SCREENSHOT);
			btnSubmit.click();
			report.reportDoneEvent("Click on Submit", "Clicked successfully");
			sleep(5000);
			waitForElement(userHomePage);
			if(isElementPresent(userHomePage)){
				report.updateTestLog("LogintoHomePage", "logged into the homepage Successfully", Status.SCREENSHOT);
			}else{
				report.reportFailEvent("LoginToHomePage", "Login Unsuccessfull <Please check UN and PWD>");
			}
			waitForAnyElement(userHomePage, searchBox);
			sleep(3000);
			if(isElementPresent(searchBox)){
				report.updateTestLog("LogintoKMHomePage", "logged into KM Application Successfully", Status.SCREENSHOT);
			}else{
				report.reportFailEvent("LoginToKMhomePage", "Login Unsuccessfull <Please check UN and PWD>");
			}			
		}catch(Exception Ex){
			report.reportFailEvent("applicationLogin", "User Login NOT successful, EXCEPTION CAUGHT : " + Ex.getMessage());
			throw Ex;
		}	
		return new HomePage(browser,report);			
	}

	
	
	
	//=============================RC Methods===================================
	public HomePage applicationLoginRC(String userid, String Password) {
		try{
			shortWaitForElement(txtEmailAddress);
			txtEmailAddress.click();
			txtEmailAddress.sendKeys(userid);
			report.reportDoneEvent("Enter username","Entered username as-> "+userid);				
			txtPassword.click();
			txtPassword.sendKeys(Password);
			report.reportDoneEvent("Enter password","Entered password as-> "+Password.replaceAll(".", "*"));				
			report.updateTestLog("Enter UserName&Password","Entered UserName and Password",Status.SCREENSHOT);
			btnSubmit.click();
			report.reportDoneEvent("Click on Submit", "Clicked successfully");
			sleep(5000);
			waitForElement(userHomePage);
			if(isElementPresent(userHomePage)){
				report.updateTestLog("LogintoHomePage", "logged into the homepage Successfully", Status.SCREENSHOT);
			}else{
				report.reportFailEvent("LoginToHomePage", "Login Unsuccessfull <Please check UN and PWD>");
			}
			waitForElement(accountNumber);			
			if(isElementPresent(accountNumber)){
				report.updateTestLog("LogintoHomePage", "logged into the homepage Successfully", Status.SCREENSHOT);
			}else{
				report.reportFailEvent("LoginToHomePage", "Login Unsuccessfull <Please check UN and PWD>");
			}			
		}catch(Exception Ex){
			report.reportFailEvent("applicationLogin", "User Login NOT successful, EXCEPTION CAUGHT : " + Ex.getMessage());
			throw Ex;
		}	
		return new HomePage(browser,report);			
	}	
*/

}
	
	
	