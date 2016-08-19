package com.comcast.century.cm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.utils.SeleniumReport;

public class SurveyTabPageCM extends Page {

	public SurveyTabPageCM(WebDriver browser, SeleniumReport report) {
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
	
	@FindBy(xpath = "//a[.='Survey']")
	private WebElement tabSurvey;
	
	//*[@id='initiateSurveyDet']
	
	@FindBy(xpath = "//input[@id='initiateSurveyDet']")
	private WebElement btnRadioInitiateSurvey;
	
	@FindBy(xpath = "//*[@id='mainFrame']")
	private WebElement frameMain;
	
	@FindBy(xpath = "//*[@id='codition']")
	private WebElement frameCondition;
	
	//*[@id='siteAddress.addrSiteBean.siteName']
	
	@FindBy(xpath = "//input[@id='siteAddress.addrSiteBean.siteName']")
	private WebElement txtSiteName;
	
	//*[@id='siteAddress.addrSiteBean.address1']
	
	@FindBy(xpath = "//input[@id='siteAddress.addrSiteBean.address1']")
	private WebElement txtAddressLine1;
	
	@FindBy(xpath = "//img[@id='ext-gen33']")
	private WebElement ddArrwTT;

	@FindBy(xpath = "//input[@id='transportType']/following-sibling::input")
	private WebElement ddtextTT;
	
	@FindBy(xpath = "//div[text()='Coax']")
	private WebElement ddValueTT;
	
	@FindBy(xpath = "//input[@id='comboSiteZip']")
	private WebElement ddtxtZipCode;
	
	@FindBy(xpath = "//img[@id='zipcode_imgSearch']")
	private WebElement imgZipcodeSearch;
	
	@FindBy(xpath = "//input[@id='0']")
	private WebElement btnRadioSelectZip;
	
	@FindBy(xpath = "//input[@id='closeLookup']")
	private WebElement btnOk;
	
	@FindBy(xpath = "//input[@value='Validate']")
	private WebElement btnValidate;
	
	@FindBy(xpath = "//input[@value='More']")
	private WebElement btnMore;
	
	@FindBy(xpath = "//input[@id='diclaimercheck']")
	private WebElement chkDisclaimer;
	
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement btnContinue;
	
	//*[@id='comboSurveyActions']
	
	@FindBy(xpath = "//input[@id='comboSurveyActions']")
	private WebElement ddtxtSurveyAction;
	
	//*[@id='ext-gen212']
	
	@FindBy(xpath = "//img[@id='ext-gen212']")
	private WebElement ddArrwSurveyAction;
	
	//div[text()='Submit Request']
	
	@FindBy(xpath = "//div[text()='Submit Request']")
	private WebElement ddValueSubmitRequest;
	
	//div[text()='Create Survey']
	
	@FindBy(xpath = "//div[text()='Create Survey']")
	private WebElement ddValueCreateSurvey;
	
	//div[contains(text(),'Add Contact')]
	//*[@id='ext-gen71']
	
	@FindBy(xpath = ".//div[@id='ext-gen71']")
	private WebElement BtnAddContact;
	
	//input[@id='go']
	
	@FindBy(xpath = "//input[@id='go']")
	private WebElement BtnGO;
	
	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading ;
	
	@FindBy(xpath = "//div[text()='Presale']/../following-sibling::*[position()=1]/child::*")
	private WebElement txtSurveyID;
	
	private boolean mstatus;
	
	public boolean CreateSiteForSurvey() throws InterruptedException{
		mstatus = true;
		try {
			waitforPageLoadComplete();
			if(waitForElement(tabSurvey)){
			tabSurvey.click();
			    }
			waitforPageLoadComplete();
			waitForElementDisappear(elementLoading);	
			if(WaitandSwitchToFrame(frameMain)){
				waitForElement(btnRadioInitiateSurvey);
				btnRadioInitiateSurvey.click();
				waitforPageLoadComplete();
				waitForElement(txtSiteName);
				txtSiteName.sendKeys("Site"+getTimestamp());
				ddValueSelect(ddtextTT,ddValueTT,"Coax");
				 txtAddressLine1.sendKeys("Address"+getTimestamp());
				 waitForElement(ddtxtZipCode);
				 ddtxtZipCode.clear();
				 ddtxtZipCode.sendKeys("30309");
				 waitForElement(imgZipcodeSearch);
				 imgZipcodeSearch.click();
				 WaitandSwitchToFrame(frameCondition);
				 waitForElement(btnRadioSelectZip);
				 btnRadioSelectZip.click();
				 waitForElement(btnOk);
				 btnOk.click();
				 browser.switchTo().defaultContent();
				 WaitandSwitchToFrame(frameMain);
				
				 waitForElement(btnValidate);
				 btnValidate.click();
				 waitforPageLoadComplete();
				 waitForElement(btnMore);
				 btnMore.click();
				 if (WaitandSwitchToFrame(frameCondition)){
					 waitForElement(chkDisclaimer);
					 chkDisclaimer.click();
					 waitForElement(btnContinue);
					 btnContinue.click();
					 waitforPageLoadComplete();
					 browser.switchTo().defaultContent();	 
				 }
				 WaitandSwitchToFrame(frameMain);
				 waitForElement(BtnAddContact);	 
				 BtnAddContact.click();
				 waitforPageLoadComplete();
			}
		} catch (Exception e) {
			mstatus = true;
		}
		return mstatus;
	}
	
	public String SubmitSurveyRequest() throws InterruptedException{
		waitForElementDisappear(elementLoading);
		if(WaitandSwitchToFrame(frameMain)){
			waitForElement(ddtxtSurveyAction);
			ddValueSelect(ddtxtSurveyAction,ddValueSubmitRequest,"Submit Request");
			waitForElement(BtnGO);
			BtnGO.click();
			waitforPageLoadComplete();
			waitForElementDisappear(elementLoading);
			Thread.sleep(10*1000);
		}
		
		String SurveyID;
		SurveyID = txtSurveyID.getText();
		browser.switchTo().defaultContent();
		return SurveyID;
		
	}

}
