package com.comcast.century.cso.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;





public class LoadCPEConfigsTaskPage extends Page {

	public LoadCPEConfigsTaskPage(FrameworkContext context) {
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
	Logger log = Logger.getLogger(DisconnectTaskPage.class);
	
	@FindBy(xpath = "//*[.='Task Details']") 
	private WebElement tabTaskDetails;
	
	@FindBy(id = "configGenerateDate")
	private WebElement  txtconfigGenerateDate;
	
	@FindBy(id = "loadCPEConfigDate")
	private WebElement  txtloadCPEConfigDate;

	private boolean mstatus=true;
	
	public boolean LoadCPEConfigs(){
		try{
			waitforPageLoadComplete();
			if(waitForElement(tabTaskDetails)){
				tabTaskDetails.click();
			if(waitForElement(txtconfigGenerateDate)){
				txtconfigGenerateDate.sendKeys(getCurrentDate());
				txtloadCPEConfigDate.sendKeys(getCurrentDate());
			}
				
			}
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}

	
	
}
