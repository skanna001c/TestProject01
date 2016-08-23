package com.comcast.century.cso.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.cm.pages.Page;
import com.comcast.reporting.Status;
import com.comcast.utils.SeleniumReport;

public class BGPTasks extends Page {

	public BGPTasks(WebDriver browser, SeleniumReport report) {
		super(browser, report);
		// TODO Auto-generated constructor stub
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
	
	@FindBy(xpath = "//*[@id='RightFrame' and contains(@src,'MyOrder.exc')]")
	private WebElement frameRight;
	
	@FindBy(xpath = "//img[@title='Back']")
	private WebElement btnBack;
	
	@FindBy(xpath = "//div[@class[contains(.,'refresh')]]")
	private WebElement btnRefresh;
	
	@FindBy(xpath = "//a[text()='Assign Design- BGP']")
	private WebElement taskAssignDesignBGP;
	
	@FindBy(xpath = "//a[text()='Generate CPE Configs']")
	private WebElement taskGenearteCPEConfigs;
	
	@FindBy(xpath = "//a[text()='Set Critical Dates']")
	private WebElement taskSetCriticalDates;
	
	@FindBy(xpath = "//a[text()='Day of Configs']")
	private WebElement taskDayOfConfigs;
	
	@FindBy(xpath = "//a[text()='Notify Customer of Service Installation']")
	private WebElement taskNotifyCustomerOfSI;
	
	@FindBy(xpath = "//a[text()='Create Order Billing Package']")
	private WebElement taskCreateOrderBillingPackage;
	
	@FindBy(xpath = "//a[text()='Start Billing']")
	private WebElement taskStartBilling;
	
	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading ;
	
	private boolean mstatus;
	
	public boolean assignDesignBGP() throws InterruptedException{
		mstatus= true;
		try{
			if(waitForElement(taskAssignDesignBGP)){
				if(checkifStatusChanged(taskAssignDesignBGP,btnRefresh,"INPROGRESS")){
				waitForElement(taskAssignDesignBGP);	
				jsClick(taskAssignDesignBGP);
				}
				waitforPageLoadComplete();
				report.reportDoneEvent("Click assignDesignBGP Task", " assignDesignBGP Task Clicked");
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	
	public boolean genearteCPEConfigs() throws InterruptedException{
		mstatus = true;
		try{				
			if(waitForElement(taskGenearteCPEConfigs)){
				if(checkifStatusChanged(taskGenearteCPEConfigs,btnRefresh,"INPROGRESS")){
				waitForElement(taskGenearteCPEConfigs);	
				jsClick(taskGenearteCPEConfigs);
				}
				waitforPageLoadComplete();
				report.reportDoneEvent("Click genearteCPEConfigs Task", " genearteCPEConfigs Task Clicked");
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	
	public boolean setCriticalDates() throws InterruptedException{
		mstatus = true;
		try{
			if(waitForElement(taskSetCriticalDates)){
				if(checkifStatusChanged(taskSetCriticalDates,btnRefresh,"INPROGRESS")){
				waitForElement(taskSetCriticalDates);	
				jsClick(taskSetCriticalDates);
				}
				waitforPageLoadComplete();
				report.reportDoneEvent("Click setCriticalDates Task", " setCriticalDates Task Clicked");
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean dayOfConfigs() throws InterruptedException{
		mstatus = true;
		try{
			if(waitForElement(taskDayOfConfigs)){
				if(checkifStatusChanged(taskDayOfConfigs,btnRefresh,"INPROGRESS")){
				waitForElement(taskDayOfConfigs);	
				jsClick(taskDayOfConfigs);
				}
				waitforPageLoadComplete();
				report.reportDoneEvent("Click dayOfConfigs Task", " dayOfConfigs Task Clicked");
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean notifyCustomerOfSI() throws InterruptedException{
		mstatus = true;
		try{
			if(waitForElement(taskNotifyCustomerOfSI)){
				if(checkifStatusChanged(taskNotifyCustomerOfSI,btnRefresh,"INPROGRESS")){
				waitForElement(taskNotifyCustomerOfSI);	
				jsClick(taskNotifyCustomerOfSI);
				}
				waitforPageLoadComplete();
				report.reportDoneEvent("Click notifyCustomerOfSI Task", " notifyCustomerOfSI Task Clicked");
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean createOrderBillingPackage() throws InterruptedException{
		mstatus = true;
		try{
			if(waitForElement(taskCreateOrderBillingPackage)){
				if(checkifStatusChanged(taskCreateOrderBillingPackage,btnRefresh,"INPROGRESS")){
				waitForElement(taskCreateOrderBillingPackage);	
				jsClick(taskCreateOrderBillingPackage);
				}
				waitforPageLoadComplete();
				report.reportDoneEvent("Click createOrderBillingPackage Task", " createOrderBillingPackage Task Clicked");
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean startBilling() throws InterruptedException{
		mstatus = true;
		try{				
			if(waitForElement(taskStartBilling)){
				if(checkifStatusChanged(taskStartBilling,btnRefresh,"COMPLETED")){
				waitForElement(taskStartBilling);	
				//jsClick(taskStartBilling);
				}
				//waitforPageLoadComplete();
				report.reportDoneEvent("Complete startBilling Task", " startBilling Task auto completed");
			}
		}	
		catch(Exception e){
			System.out.println(e.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	
	public boolean ClickBackButton() throws InterruptedException{
		mstatus = true;
		try{
			if(waitForElement(btnBack)){
				clickndRelease(btnBack);
				//btnBack.click();
				waitforPageLoadComplete();
				waitForElementDisappear(elementLoading);
				report.updateTestLog("Validate", "BGP Flow Completed", Status.SCREENSHOT);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			mstatus = false;
		}
		return mstatus;
	}

}
