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
	
	
	public boolean CAETask(ServiceInfo serviceInfo, ServiceLevelTaskInfo serviceLevelTaskInfo, DataDump dataDump){
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
				(new SoapTest()).webServicesTask(request, "CAE");
			case "EPL" :
				request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sit=\"http://www.excelacom.com/century/cramer/beans/siteDesignNotification\">"
						+ "<soapenv:Header/>" + "<soapenv:Body>" + "<sit:siteDesignNotification>"
						+ "<sit:resourceComponent resourceComponentId=\"" + RCID + "\">"
						+ "<sit:site uniNumber=\"" + dataDump.getValue("UNINo1_RT") + "\" uniID=\"" + serviceLevelTaskInfo.UNI1 + "\" siteCLLI=\"" + serviceLevelTaskInfo.siteCili+ "\"/>" 
						+ "<sit:site uniNumber=\"" + dataDump.getValue("UNINo2_RT") + "\" uniID=\"" + serviceLevelTaskInfo.UNI2 + "\" siteCLLI=\"" + serviceLevelTaskInfo.siteCili+ "\"/>" 
						+ "</sit:resourceComponent>" + "</sit:siteDesignNotification>" + "</soapenv:Body>"
						+ "</soapenv:Envelope>";
				(new SoapTest()).webServicesTask(request, "CAE");
			
			}
			iClick(btnBack,null, "CAE task complete:CAE task complete page: BackButton ");
			waitForElement(browser.findElement(By.xpath("//*[text()='Create Account and Equipment' and contains(@onclick, 'COMPLETED')]")));

			
		}catch(Exception e){
			e.printStackTrace();
			mstatus=false;
		}
		return mstatus;
	}
	
}
