package com.comcast.century.cm.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.utils.SeleniumReport;
import com.comcast.utils.ComcastTest.FrameworkContext;

public class EquipmentTabPageCM extends Page {

	public EquipmentTabPageCM(FrameworkContext context){
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
	
	@FindBy(xpath = "//b[contains(.,'Device')]/../../../following-sibling::tr[1]/child::td[2]/child::*/child::input[1]")
	private WebElement txtPolycomDevice  ;
	
	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading ;
	
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement btnContinue;
	
	private boolean mstatus;
	
	public boolean equipmentConfiguration(){
		mstatus = true;
		try {
			waitForElementDisappear(elementLoading);
			enterQuantity(txtPolycomDevice,"5");
			scrollDown();
			waitForElement(btnContinue);			
			iClick(btnContinue,null,"Click on Continue button: Equipment quantity configuration: ContinueButton");			
			waitForElementDisappear(elementLoading);
		} catch (Exception e) {
			mstatus = true;
		}
		return mstatus;
	}
	
	

}
