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

public class ADITaskPage extends Page {

	public ADITaskPage(FrameworkContext context) {
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
	
	Logger log = Logger.getLogger(ADITaskPage.class);
	
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

	@FindBy(id = "serviceId")
	private WebElement txtServiceID ;

	@FindBy(id = "evccID")
	private WebElement txtEVCid ;

	@FindBy(id = "projectName")
	private WebElement txtProjectName ;
	
	@FindBy(xpath = "//b[contains(normalize-space(text()),normalize-space(concat(\"Retrieve Circuit \",\"ID's\")))]")
	private WebElement linkRetrieveCircuitID ;
	
	@FindBy(id = "oitab")
	private WebElement tabOrderInfo ;
	
	@FindBy(xpath = "//*[.='Enterprise']/preceding-sibling::td[3]/child::*")
	private WebElement resourceComponentID ;
	
	@FindBy(xpath = "//*[.='Enterprise']/following-sibling::td[1]/child::*")
	private WebElement EVCNo ;
	
	private boolean mstatus = true;
	
	
public String getResourceComponentID(){
		
		String RCID=null;
		
		try{
			waitForElement(tabOrderInfo);
			iClick(tabOrderInfo);
			waitForElementDisappear(elementLoading);
			RCID=resourceComponentID.getText();
		}catch(Exception e){
		   log.info(e.getMessage());
		   
		}
		return RCID;
	}
	

public boolean ADITask(ServiceInfo serviceInfo){
	String evc;
	if (serviceInfo.testSetName.equalsIgnoreCase("SV Integration"))
	{
		evc=serviceInfo.EVC;
		
	}
	else{
		evc="30.VLXM."+ randomNumber(6) + "..CBCL..";
		
	}
	try{
		String request = null;			
		String RCID = this.getResourceComponentID();
				request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://www.excelacom.com/century/cramer/beans/ServiceDesignNotification\">"
						+ "<soapenv:Header/>" 
				        + "<soapenv:Body>" 
						+ "<ser:serviceDesignNotification>"
						+ "<ser:resourceComponent resourceComponentId=\"" + RCID + "\">"
						+ "<ser:service evcNumber=\"" + EVCNo.getText() + "\" evcID=\""+evc+ "\" serviceID=\""+randomNumber(7)+"\"/>"
						+ "</ser:resourceComponent>" 
						+ "</ser:serviceDesignNotification>" 
						+ "</soapenv:Body>"
						+ "</soapenv:Envelope>";			
		(new SoapTest()).webServicesTask(request, "ADI",testSettings);
		iClick(btnBack,null, "ADI task complete:ADI task complete page: BackButton ");		
		waitUntilElementPresent(By.xpath("//*[text()='Assign Design Information' and contains(@onclick, 'COMPLETED')]")
				, 60);

		
	}catch(Exception e){
		log.info(e.getMessage());
		mstatus=false;
	}
	return mstatus;
}


}
