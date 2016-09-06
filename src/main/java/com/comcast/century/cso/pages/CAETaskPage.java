package com.comcast.century.cso.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.cm.pages.Page;
import com.comcast.century.data.ServiceInfo;
import com.comcast.century.data.ServiceLevelTaskInfo;
import com.comcast.utils.DataDump;
import com.comcast.utils.SeleniumReport;
import com.comcast.utils.SoapTest;

public class CAETaskPage extends Page {

	public CAETaskPage(WebDriver browser, SeleniumReport report) {
		super(browser, report);
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
	
	@FindBy(xpath = "//img[@title='Back']")
	private WebElement btnBack;
	
	@FindBy(xpath = "//input[@value='Complete']")
	private WebElement btnComplete;
	
	@FindBy(xpath = "//input[@value='Save']")
	private WebElement btnSave;
	
	@FindBy(xpath = "//input[@value='Reset']")
	private WebElement btnReset;
	
	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading ;
	
	@FindBy(xpath = "//span[text()='On-net Service Component(s) Information']")
	private WebElement tabOnnetServiceCI;
	
	@FindBy(xpath = "//div[text()='EDI']")
	private WebElement elementEDI ;
	
	@FindBy(xpath = "//div[text()='EPL']")
	private WebElement elementEPL ;
	
	@FindBy(xpath = "//div[text()='EVPL']")
	private WebElement elementEVPL ;
	
	@FindBy(xpath = "//div[text()='Trunk-PRI']")
	private WebElement elementPRI ;
	
	@FindBy(xpath = "//input[@id='uniId']")
	private WebElement txtUNIid ;
	
	@FindBy(xpath = "//input[@id='siteCili']")
	private WebElement txtSiteCili ;
	
	@FindBy(xpath = "//b[contains(normalize-space(text()),normalize-space(concat(\"Retrieve Circuit \",\"ID's\")))]")
	private WebElement linkRetrieveCircuitID ;
	
	@FindBy(xpath = "//input[@id='projectName']")
	private WebElement txtProjectName ;
	
	@FindBy(xpath = "//span[.='Order Services']")
	private WebElement tabOrderServices ;
	
	@FindBy(xpath = "//div[.='New']/../preceding-sibling::td[1]/div")
	private WebElement resourceComponentID ;
	
	
	
	
	
	private boolean mstatus = true;
	
	public String getResourceComponentID(){
		
		String RCID=null;
		
		try{
			waitForElement(tabOrderServices);
			iClick(tabOrderServices);
			waitForElementDisappear(elementLoading);
			RCID=resourceComponentID.getText();
		}catch(Exception e){
		   System.out.println(e.getMessage());
		   
		}
		return RCID;
	}
	
	
	public boolean cAETask(ServiceInfo serviceInfo, ServiceLevelTaskInfo serviceLevelTaskInfo, DataDump dataDump){
		try{
			String request = null;			
			String RCID = this.getResourceComponentID();
			switch(serviceInfo.serviceName){			
			case "EDI" :
				request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sit=\"http://www.excelacom.com/century/cramer/beans/siteDesignNotification\">"
						+ "<soapenv:Header/>" + "<soapenv:Body>" + "<sit:siteDesignNotification>"
						+ "<sit:resourceComponent resourceComponentId=\"" + RCID + "\">"
						+ "<sit:site uniNumber=\"" + dataDump.getValue("UNINo1_RT") + "\" uniID=\"" + serviceLevelTaskInfo.UNI1 + "\" siteCLLI=\"" + serviceLevelTaskInfo.siteCili+ "\"/>" 
						+"</sit:resourceComponent>" + "</sit:siteDesignNotification>" + "</soapenv:Body>"
						+ "</soapenv:Envelope>";				
				(new SoapTest()).soapCAETask(request);
			case "EPL" :
				request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sit=\"http://www.excelacom.com/century/cramer/beans/siteDesignNotification\">"
						+ "<soapenv:Header/>" + "<soapenv:Body>" + "<sit:siteDesignNotification>"
						+ "<sit:resourceComponent resourceComponentId=\"" + RCID + "\">"
						+ "<sit:site uniNumber=\"" + dataDump.getValue("UNINo1_RT") + "\" uniID=\"" + serviceLevelTaskInfo.UNI1 + "\" siteCLLI=\"" + serviceLevelTaskInfo.siteCili+ "\"/>" 
						+ "<sit:site uniNumber=\"" + dataDump.getValue("UNINo2_RT") + "\" uniID=\"" + serviceLevelTaskInfo.UNI2 + "\" siteCLLI=\"" + serviceLevelTaskInfo.siteCili+ "\"/>" 
						+ "</sit:resourceComponent>" + "</sit:siteDesignNotification>" + "</soapenv:Body>"
						+ "</soapenv:Envelope>";
				(new SoapTest()).soapCAETask(request);
			
			}
			this.ClickCompleteButton();
			
			
		}catch(Exception e){
			e.printStackTrace();
			mstatus=false;
		}
		return mstatus;
	}
	
	
	public boolean CAE(ServiceLevelTaskInfo serviceLevelTaskInfo) throws InterruptedException{
		try{
			if(waitForElement(tabOnnetServiceCI)){
				tabOnnetServiceCI.click();
				Thread.sleep(2*1000);
				scrollToElementandclick(elementEDI);
				waitForElement(txtUNIid);
				txtUNIid.sendKeys(serviceLevelTaskInfo.UNI1);
				txtSiteCili.sendKeys(serviceLevelTaskInfo.siteCili);
				this.ClickSaveButton();
				waitForElement(linkRetrieveCircuitID);
				linkRetrieveCircuitID.click();
				Thread.sleep(5*1000);
				txtProjectName.clear();
				txtProjectName.sendKeys(serviceLevelTaskInfo.projectName);								
				this.ClickCompleteButton();
				if(waitForElement(btnComplete,5))
				{
					this.ClickCompleteButton();
				}
				waitForElement(browser.findElement(By.xpath("//*[text()='Create Account and Equipment' and contains(@onclick, 'COMPLETED')]")));
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			mstatus = false;			
		}
		return mstatus;
	}
	
	public boolean CAE_EPL(ServiceLevelTaskInfo serviceLevelTaskInfo) throws InterruptedException{
		try{
			if(waitForElement(tabOnnetServiceCI)){
				tabOnnetServiceCI.click();
				Thread.sleep(2*1000);
				scrollToElementandclick(elementEPL);
				waitForElement(txtUNIid);
				txtUNIid.sendKeys(serviceLevelTaskInfo.UNI1);
				txtSiteCili.sendKeys(serviceLevelTaskInfo.siteCili);
				txtProjectName.clear();
				txtProjectName.sendKeys(serviceLevelTaskInfo.projectName);
				btnSave.click();
				waitforPageLoadComplete();
				Thread.sleep(2*1000);
				linkRetrieveCircuitID.click();
				Thread.sleep(5*1000);				
				waitForElement(tabOnnetServiceCI);
				tabOnnetServiceCI.click();
				Thread.sleep(2*1000);
				scrollToElementandclick(elementEPL);
				waitForElement(txtUNIid);
				txtUNIid.sendKeys(serviceLevelTaskInfo.UNI2);
				txtSiteCili.sendKeys(serviceLevelTaskInfo.siteCili);
				txtProjectName.clear();
				txtProjectName.sendKeys(serviceLevelTaskInfo.projectName);
				btnSave.click();
				linkRetrieveCircuitID.click();
				Thread.sleep(5*1000);
				waitforPageLoadComplete();
				Thread.sleep(2*1000);
				this.ClickCompleteButton();
				waitForElement(browser.findElement(By.xpath("//*[text()='Create Account and Equipment' and contains(@onclick, 'COMPLETED')]")));
			}
		}	
		catch(Exception e){
			System.out.println(e.getMessage());
			mstatus = false;			
		}
		return mstatus;
}
	
	public boolean CAE_EVPL(ServiceLevelTaskInfo serviceLevelTaskInfo) throws InterruptedException{
		try{
			if(waitForElement(tabOnnetServiceCI)){
				tabOnnetServiceCI.click();
				Thread.sleep(2*1000);
				scrollToElementandclick(elementEVPL);
				waitForElement(txtUNIid);
				txtUNIid.sendKeys(serviceLevelTaskInfo.UNI1);
				txtSiteCili.sendKeys(serviceLevelTaskInfo.siteCili);
				txtProjectName.clear();
				txtProjectName.sendKeys(serviceLevelTaskInfo.projectName);
				btnSave.click();
				waitforPageLoadComplete();
				linkRetrieveCircuitID.click();
				Thread.sleep(5*1000);				
				Thread.sleep(2*1000);
				waitForElement(tabOnnetServiceCI);
				tabOnnetServiceCI.click();
				Thread.sleep(2*1000);
				scrollToElementandclick(elementEVPL);
				waitForElement(txtUNIid);
				txtUNIid.sendKeys(serviceLevelTaskInfo.UNI2);
				txtSiteCili.sendKeys(serviceLevelTaskInfo.siteCili);
				txtProjectName.clear();
				txtProjectName.sendKeys(serviceLevelTaskInfo.projectName);
				btnSave.click();
				waitforPageLoadComplete();
				linkRetrieveCircuitID.click();
				Thread.sleep(5*1000);				
				this.ClickCompleteButton();
				waitForElement(browser.findElement(By.xpath("//*[text()='Create Account and Equipment' and contains(@onclick, 'COMPLETED')]")));
			}
		}	
		catch(Exception e){
			System.out.println(e.getMessage());
			mstatus = false;			
		}
		return mstatus;
}
	
	public boolean cAE_PRI(ServiceLevelTaskInfo serviceLevelTaskInfo) throws InterruptedException{
		try{
			if(waitForElement(tabOnnetServiceCI)){
				tabOnnetServiceCI.click();
				Thread.sleep(2*1000);
				scrollToElementandclick(elementPRI);
				waitForElement(txtUNIid);
				txtUNIid.sendKeys(serviceLevelTaskInfo.UNI2);
				txtSiteCili.sendKeys(serviceLevelTaskInfo.siteCili);
				linkRetrieveCircuitID.click();
				Thread.sleep(5*1000);
				txtProjectName.clear();
				txtProjectName.sendKeys(serviceLevelTaskInfo.projectName);
				this.ClickCompleteButton();
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			mstatus = false;			
		}
		return mstatus;
		
	}
	
	
	public boolean ClickSaveButton(){
		try{
			if(waitForElement(btnSave)){
				btnSave.click();
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			mstatus = false;			
		}
		return mstatus;
	}
	
	public boolean ClickCompleteButton(){
		try{
			if(waitForElement(btnComplete)){
				iClick(btnComplete, btnBack,"Complete CAE Task: Complete CAE Task page: CompleteButton");
				waitforPageLoadComplete();
				report.reportDoneEvent("Complete CAE Task", " CAE Task Completed");
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			mstatus = false;			
		}
		return mstatus;
		
	}

}
