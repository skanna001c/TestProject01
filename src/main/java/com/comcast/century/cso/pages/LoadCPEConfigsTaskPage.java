package com.comcast.century.cso.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.cm.pages.Page;
import com.comcast.utils.ComcastTest.FrameworkContext;
import com.comcast.utils.SeleniumReport;

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
	
	/*
	
	
	@FindBy(id = "collsContactName")
	private WebElement  txtContactName;
	
	@FindBy(id = "collsContactPN")
	private WebElement  txtPhoneNumber;
	
	
	@FindBy(id = "customerResponseCmb-inputEl")
	private WebElement  ddTextCustomerResponse;
	
	
	@FindBy(xpath = "//li[.='Service Hard Disconnect']")
	private WebElement  ddValueCustomerResponse;
	*/
	
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
				txtconfigGenerateDate.sendKeys(getCurrentDate());
				txtconfigGenerateDate.sendKeys(getCurrentDate());
				
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}

	
	
}
