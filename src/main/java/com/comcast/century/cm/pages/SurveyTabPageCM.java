package com.comcast.century.cm.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;





public class SurveyTabPageCM extends Page {

	
	public SurveyTabPageCM(FrameworkContext context){
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
	
	Logger log = Logger.getLogger(SurveyTabPageCM.class);
	
	@FindBy(xpath = "//a[.='Survey']")
	private WebElement tabSurvey;
	
	//*[@id='initiateSurveyDet']
	
	@FindBy(id = "initiateSurveyDet")
	private WebElement btnRadioInitiateSurvey;
	
	@FindBy(id = "mainFrame")
	private WebElement frameMain;
	
	@FindBy(id = "codition")
	private WebElement frameCondition;
	
	@FindBy(id = "siteAddress.addrSiteBean.siteName")
	private WebElement txtSiteName;
	
	@FindBy(id = "siteAddress.addrSiteBean.address1")
	private WebElement txtAddressLine1;
	
	@FindBy(id = "ext-gen33")
	private WebElement ddArrwTT;

	@FindBy(xpath = "//input[@id='transportType']/following-sibling::input")
	private WebElement ddtextTT;
	
	@FindBy(xpath = "//div[text()='Coax']")
	private WebElement ddValueTT;
	
	@FindBy(id = "comboSiteZip")
	private WebElement ddtxtZipCode;
	
	@FindBy(id = "ipcode_imgSearch")
	private WebElement imgZipcodeSearch;
	
	@FindBy(id = "0")
	private WebElement btnRadioSelectZip;
	
	@FindBy(id = "loseLookup")
	private WebElement btnOk;
	
	@FindBy(xpath = "//input[@value='Validate']")
	private WebElement btnValidate;
	
	@FindBy(xpath = "//input[@value='More']")
	private WebElement btnMore;
	
	@FindBy(id = "diclaimercheck")
	private WebElement chkDisclaimer;
	
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement btnContinue;
	
	@FindBy(id = "comboSurveyActions")
	private WebElement ddtxtSurveyAction;
	
	@FindBy(id = "ext-gen212")
	private WebElement ddArrwSurveyAction;
	
	@FindBy(xpath = "//div[text()='Submit Request']")
	private WebElement ddValueSubmitRequest;
	
	@FindBy(xpath = "//div[text()='Create Survey']")
	private WebElement ddValueCreateSurvey;
	
	@FindBy(id = "ext-gen71")
	private WebElement BtnAddContact;
	
	@FindBy(id ="go")
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
				 iClick(BtnAddContact, null, "Add Contact: Contact page: ContactButton");				 
				 waitforPageLoadComplete();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
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
