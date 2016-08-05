package com.comcast.century.cm.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.utils.SeleniumReport;

public class EquipmentTabPageCM extends Page {

	public EquipmentTabPageCM(WebDriver browser, SeleniumReport report) {
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
	
	@FindBy(xpath = "//b[contains(.,'Device')]/../../../following-sibling::tr[1]/child::td[2]/child::*/child::input[1]")
	private WebElement txtPolycomDevice  ;
	
	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading ;
	
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement btnContinue;
	
	
	public void equipmentConfiguration(){
		waitForElementDisappear(elementLoading);
		enterQuantity(txtPolycomDevice,"5");
		scrollDown();
		waitForElement(btnContinue);
		waitForElement(btnContinue);
		btnContinue.sendKeys(Keys.ENTER);
	    waitForElementDisappear(elementLoading);
	}
	
	

}
