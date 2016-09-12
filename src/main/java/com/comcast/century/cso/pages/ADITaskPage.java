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

public class ADITaskPage extends Page {

	public ADITaskPage(WebDriver browser, SeleniumReport report) {
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

	@FindBy(xpath = "//div[text()='Circuit Mapping']")
	private WebElement tabCircuitMapping;
	
	@FindBy(xpath = "//div[text()='EDI']")
	private WebElement elementEDI ;
	
	@FindBy(xpath = "//*[text()='EVPL']")
	private List<WebElement> elementEVPL ;
	
	@FindBy(xpath = "//*[text()='-']")
	private List<WebElement> elementDash ;

	@FindBy(xpath = "//input[@id='serviceId']")
	private WebElement txtServiceID ;

	@FindBy(xpath = "//input[@id='evccID']")
	private WebElement txtEVCid ;

	@FindBy(xpath = "//input[@id='projectName']")
	private WebElement txtProjectName ;
	
	@FindBy(xpath = "//b[contains(normalize-space(text()),normalize-space(concat(\"Retrieve Circuit \",\"ID's\")))]")
	private WebElement linkRetrieveCircuitID ;
	
	@FindBy(xpath = "//*[@id='oitab']")
	private WebElement tabOrderInfo ;
	
	@FindBy(xpath = "//*[.='Enterprise']/preceding-sibling::td[3]/child::*")
	private WebElement resourceComponentID ;
	
	@FindBy(xpath = "//*[.='Enterprise']/following-sibling::td[1]/child::*")
	private WebElement EVCNo ;
	
	private boolean mstatus;
	
	
public String getResourceComponentID(){
		
		String RCID=null;
		
		try{
			waitForElement(tabOrderInfo);
			iClick(tabOrderInfo);
			waitForElementDisappear(elementLoading);
			RCID=resourceComponentID.getText();
		}catch(Exception e){
		   System.out.println(e.getMessage());
		   
		}
		return RCID;
	}
	

public boolean ADITask(ServiceInfo serviceInfo){
	try{
		String request = null;			
		String RCID = this.getResourceComponentID();
		switch(serviceInfo.serviceName){			
		case "EDI" :
		case "EDI-BGP" :
		case "EPL" :
				request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://www.excelacom.com/century/cramer/beans/ServiceDesignNotification\">"
						+ "<soapenv:Header/>" 
				        + "<soapenv:Body>" 
						+ "<ser:serviceDesignNotification>"
						+ "<ser:resourceComponent resourceComponentId=\"" + RCID + "\">"
						+ "<ser:service evcNumber=\"" + EVCNo.getText() + "\" evcID=\"30.VLXM."+ randomNumber(6) + "..CBCL.."+ "\" serviceID=\""+randomNumber(7)+"\"/>"
						+ "</ser:resourceComponent>" 
						+ "</ser:serviceDesignNotification>" 
						+ "</soapenv:Body>"
						+ "</soapenv:Envelope>";			
			(new SoapTest()).webServicesTask(request, "ADI");
			break;
		
		case "EVPL" :
			  request =  "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://www.excelacom.com/century/cramer/beans/ServiceDesignNotification\">"
						+ "<soapenv:Header/>" 
				        + "<soapenv:Body>" 
						+ "<ser:serviceDesignNotification>"
						+ "<ser:resourceComponent resourceComponentId=\"" + RCID + "\">"
						+ "<ser:service evcNumber=\"" +EVCNo.getText() + "\" evcID=\"30.VLXM."+ randomNumber(6) +"..CBCL.."+ "\" serviceID=\""+randomNumber(7)+"\"/>"
						+ "</ser:resourceComponent>" 
						+ "</ser:serviceDesignNotification>" 
						+ "</soapenv:Body>"
						+ "</soapenv:Envelope>";
			  (new SoapTest()).webServicesTask(request, "ADI");
				break;
		}
		iClick(btnBack,null, "ADI task complete:ADI task complete page: BackButton ");
		waitForElement(browser.findElement(By.xpath("//*[text()='Assign Design Information' and contains(@onclick, 'COMPLETED')]")));

		
	}catch(Exception e){
		e.printStackTrace();
		mstatus=false;
	}
	return mstatus;
}


}
