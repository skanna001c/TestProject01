package com.comcast.cso.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.data.ServiceInfo;
import com.comcast.utils.Page;
import com.comcast.utils.SoapTest;

public class CAETaskPage extends Page {

	public CAETaskPage(FrameworkContext context) {
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
	
	Logger log = Logger.getLogger(CAETaskPage.class);
	
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
	
	@FindBy(id = "uniId")
	private WebElement txtUNIid ;
	
	@FindBy(id = "siteCili")
	private WebElement txtSiteCili ;
	
	@FindBy(xpath = "//b[contains(normalize-space(text()),normalize-space(concat(\"Retrieve Circuit \",\"ID's\")))]")
	private WebElement linkRetrieveCircuitID ;
	
	@FindBy(id = "projectName")
	private WebElement txtProjectName ;
	
	@FindBy(xpath = "//span[.='Order Services']")
	private WebElement tabOrderServices ;
	
	@FindBy(xpath = "//div[.='Modify' or .='New']/../preceding-sibling::td[1]/div")
	private WebElement resourceComponentID ;
	
	@FindBy(css = "div[class*='col-uniNumber']")
	private List<WebElement> UNINo ;
	
	@FindBy(css = "div[class*='col-uniNumber']")
	private WebElement UNINo1 ;
	
	private boolean mstatus = true;
	
	public String getResourceComponentID(){
		
		String RCID=null;
		
		try{
			waitForElement(tabOrderServices);
			iClick(tabOrderServices);
			waitForElementDisappear(elementLoading);
			RCID=resourceComponentID.getText();
			waitForElement(tabOnnetServiceCI);
			iClick(tabOnnetServiceCI);
			waitForElementDisappear(elementLoading);
		}catch(Exception e){
		  log.info(e.getMessage());
		   
		}
		return RCID;
	}
	
	
	public boolean CAETask(ServiceInfo serviceInfo) throws Exception{
		String uni1,uni2;
		if (serviceInfo.testSetName.equalsIgnoreCase("SV Integration"))
		{
			uni1=serviceInfo.UNI1;
			uni2=serviceInfo.UNI2;
		}
		else{
			uni1="30.KRGS." + randomNumber(6) + "..CBCL..";
			uni2="30.KRGS." + randomNumber(6) + "..CBCL..";
		}
		try{
			String request = null;			
			String RCID = this.getResourceComponentID();
			switch(serviceInfo.serviceName){			
			case "EDI" :
			case "ENS" :
			case "EDI-BGP" :
			case "EDI-PRI" :
			case "ENS-PRI" :			
				request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sit=\"http://www.excelacom.com/century/cramer/beans/siteDesignNotification\">"
						+ "<soapenv:Header/>" + "<soapenv:Body>" + "<sit:siteDesignNotification>"
						+ "<sit:resourceComponent resourceComponentId=\"" + RCID + "\">"
						+ "<sit:site uniNumber=\"" + UNINo1.getText() + "\" uniID=\""+uni1+ "\" siteCLLI=\"JNBOGAEM\"/>" 
						+"</sit:resourceComponent>" + "</sit:siteDesignNotification>" + "</soapenv:Body>"
						+ "</soapenv:Envelope>";				
				(new SoapTest()).webServicesTask(request, "CAE",testSettings);
				break;
			case "EPL" :
			case "EVPL" :
				if(UNINo.size()>1)
				{
				request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sit=\"http://www.excelacom.com/century/cramer/beans/siteDesignNotification\">"
						+ "<soapenv:Header/>" + "<soapenv:Body>" + "<sit:siteDesignNotification>"
						+ "<sit:resourceComponent resourceComponentId=\"" + RCID + "\">"
						+ "<sit:site uniNumber=\"" + UNINo.get(0).getText()  + "\" uniID=\""+uni1+ "\" siteCLLI=\"JNBOGAEM\"/>" 
						+ "<sit:site uniNumber=\"" + UNINo.get(1).getText()  + "\" uniID=\""+uni2+ "\" siteCLLI=\"JNBOGAEM\"/>" 
						+ "</sit:resourceComponent>" + "</sit:siteDesignNotification>" + "</soapenv:Body>"
						+ "</soapenv:Envelope>";
				}
				else
				{
					request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sit=\"http://www.excelacom.com/century/cramer/beans/siteDesignNotification\">"
							+ "<soapenv:Header/>" + "<soapenv:Body>" + "<sit:siteDesignNotification>"
							+ "<sit:resourceComponent resourceComponentId=\"" + RCID + "\">"
							+ "<sit:site uniNumber=\"" + UNINo1.getText() + "\" uniID=\""+uni1+ "\" siteCLLI=\"JNBOGAEM\"/>" 							 
							+ "</sit:resourceComponent>" + "</sit:siteDesignNotification>" + "</soapenv:Body>"
							+ "</soapenv:Envelope>";	
				}
				
				(new SoapTest()).webServicesTask(request, "CAE",testSettings);
				break;
			}
			iClick(btnBack,null, "CAE task complete:CAE task complete page: BackButton ");
			waitForElement(browser.findElement(By.xpath("//*[text()='Create Account and Equipment' and contains(@onclick, 'COMPLETED')]")));
			waitUntilElementPresent(By.xpath("//*[text()='Create Account and Equipment' and contains(@onclick, 'COMPLETED')]"), 60);			
		}catch(Exception e){
			log.info(e.getMessage());
			mstatus=false;
		}
		return mstatus;
	}
	
}
