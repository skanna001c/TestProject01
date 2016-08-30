package com.comcast.century.cm.pages;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import javax.xml.xpath.XPath;

import org.jboss.netty.handler.codec.http.HttpHeaders.Values;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.comcast.reporting.*;
import com.comcast.utils.ComcastTest;
import com.comcast.utils.SeleniumReport;
import com.comcast.utils.TestSettings;
import com.comcast.utils.TestUtils;
import com.thoughtworks.selenium.webdriven.commands.WaitForPageToLoad;

import net.sourceforge.htmlunit.corejs.javascript.ast.SwitchCase;


public class HomePageCM extends Page {

	
	protected static String HOME_PAGE_TITLE_CM = "Customer Manager";
	
	@Override
	protected boolean isValidPage() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void waitForPageLoad() {
		// TODO Auto-generated method stub
		
	}
	
	public HomePageCM(WebDriver browser, SeleniumReport report) {
		super(browser, report);
		
	}

	@FindBy(xpath = "//a[.='Home']")
	private WebElement tabHome;

	@FindBy(xpath = "//a[.='Customer']")
	private WebElement tabCustomer;
	
	@FindBy(xpath = "//*[@id='mainFrame']")
	private WebElement frameMain;
	
	@FindBy(xpath = "//*[@id='leftFrame']")
	private WebElement frameLeft;
	
	//*[@id='customerNameCombo-inputEl']
	
	@FindBy(xpath = "//input[@id='customerNameCombo-inputEl']")
	private WebElement txtCustomerName;
	
	@FindBy(xpath = "//*[@id='SEARCH_SERVICEORDERID']")
	private WebElement txtSRID;
	
	//*[@id='splitbutton-1011-btnIconEl']
	
	@FindBy(xpath = "//span[text()='Search']/following-sibling::span")
	private WebElement btnSearch;
	
	//span[contains(.,'10955195')]
	
	@FindBy(xpath = "//span[contains(.,'3900473')]")
	private WebElement clickSRid;
	
	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading ;
	
	//span[text()='ServiceAcc024143152016']
	
	@FindBy(xpath = "//span[contains(.,'ServiceAcc')]")
	private WebElement clickServiceAcc;
	
	@FindBy(xpath = "//*[@id='CustomerFrame']")
	private WebElement frameCustomer;
	
	@FindBy(css = "select#SEARCH_SERVICESTATUS")
	private WebElement ddOrderStatus;
	
	private boolean mstatus;
	
	
	public boolean clickOnHomeTab(){
		try{
			waitforPageLoadComplete();
			waitForElement(tabHome);
			iClick(tabHome);
			waitforPageLoadComplete();
		}catch(Exception e){
			e.printStackTrace();
			mstatus=false;
		}
		return mstatus;
	}
	
	
	
	public boolean NavigateToServiceTab() throws InterruptedException{
		mstatus = true;
		 try {
			waitforPageLoadComplete();
			 WaitandSwitchToFrame(frameMain);
			 txtCustomerName.sendKeys("CustName123420862016");
			 btnSearch.click();
			 waitforPageLoadComplete();
			 clickSRid.click();
			 //clickServiceAcc.click();
			 waitforPageLoadComplete();
			 browser.switchTo().defaultContent();
			 Thread.sleep(10000);
			 //waitforPageLoadComplete(); 
		} catch (Exception e) {
			e.printStackTrace();
			mstatus = false;
		}
		 return mstatus;
	}
	
	//added by harsh on 8/8 to ensure continuity in packaged execution
	//commented by harsh on 8/16 as we need to find an alternate way tor ead data from data dump
	public boolean searchCustomer(String custName){
		mstatus = true;
		//String custName = ComcastTest.getDataDump().getValue("CustomerName_RT");
		//leftframe
		//itforPageLoadComplete();
		/*WaitandSwitchToFrame(frameLeft);
		WebElement elem = browser.findElement(By.xpath("//span[@class=\"standartTreeRow\" and contains(.,'"+custName+"')]"));
		if(shortWaitForElement(elem)) {
			elem.click();
			
		}else{*/
			//span[@class="standartTreeRow" and contains(.,'ProdTest_ENT_R16.07_EDI_NC56515')]
		try{
			waitforPageLoadComplete();
			WaitandSwitchToFrame(frameMain);			 
			txtCustomerName.sendKeys(custName);
			btnSearch.click();
			waitforPageLoadComplete();
			browser.findElement(By.xpath("//span[contains(.,'"+custName+"')]")).click();
			waitforPageLoadComplete();
			browser.switchTo().defaultContent();
		    }
			catch (Exception e) {
				e.printStackTrace();
				mstatus = false;
			}
			 //WaitandSwitchToFrame(frameMain);
			 //WaitandSwitchToFrame(frameCustomer);
			 //waitForElement(browser.findElement(By.xpath(".//*[@id='busiCustBean.businessName']")));
				
		 //clickServiceAcc.click();
		return mstatus;
		}
	
	public boolean searchSRID(String SRID){
		mstatus = true;
		
		try{
			waitforPageLoadComplete();
			WaitandSwitchToFrame(frameMain);
			txtSRID.sendKeys(SRID);
			btnSearch.click();
			waitforPageLoadComplete();
			browser.findElement(By.xpath("//span[contains(.,'"+SRID+"')]")).click();
			waitForElementDisappear(elementLoading);
			browser.switchTo().defaultContent();
		}catch (Exception e) {
			e.printStackTrace();
			mstatus = false;
		}
		
		
		return mstatus;
	}
	
	
	
	public String searchForHeldOrder(){
		 String SRID = null;
		try{
			
			WaitandSwitchToFrame(frameMain);
			waitForElement(ddOrderStatus);
			new Select(ddOrderStatus).selectByVisibleText("Held");
			btnSearch.click();
			
			
		}catch (Exception e) {
			e.printStackTrace();
			mstatus = false;
		}
		return SRID;
	}

	
	}





	
	
	
	




