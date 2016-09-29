package com.comcast.century.cso.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.utils.ComcastTest.FrameworkContext;
import com.comcast.utils.Page;
import com.comcast.utils.SeleniumReport;

public class NotifyCustomerofServiceDisconnectionTaskPage extends Page {

	public NotifyCustomerofServiceDisconnectionTaskPage(FrameworkContext context) {
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
	
	@FindBy(xpath = "//*[.=' Disconnect Contact Information ']")
	private WebElement tabDisconnect_Contact_Information;
	
	
	@FindBy(id = "collsContactName")
	private WebElement  txtContactName;
	
	@FindBy(id = "collsContactPN")
	private WebElement  txtPhoneNumber;
	
	
	@FindBy(id = "customerResponseCmb-inputEl")
	private WebElement  ddTextCustomerResponse;
	
	
	@FindBy(xpath = "//li[.='Service Hard Disconnect']")
	private WebElement  ddValueCustomerResponse;
	
	
	private boolean mstatus=true;
	
	public boolean Notify_Customer_of_Service_Disconnection(){
		try{
			waitforPageLoadComplete();
			if(waitForElement(tabDisconnect_Contact_Information)){
				tabDisconnect_Contact_Information.click();
				txtContactName.sendKeys("ContactName"+randomNumber(3));
				txtPhoneNumber.sendKeys("98"+randomNumber(8));
				ddValueSelect(ddTextCustomerResponse, ddValueCustomerResponse, "Service Hard Disconnect");
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}

	
	
}
