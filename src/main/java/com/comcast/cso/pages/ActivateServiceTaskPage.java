package com.comcast.cso.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.comcast.cm.pages.SurveyTabPageCM;
import com.comcast.data.ServiceInfo;
import com.comcast.data.ServiceLevelTaskInfo;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;

public class ActivateServiceTaskPage extends Page {

	public ActivateServiceTaskPage(FrameworkContext context) {
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

	Logger log = Logger.getLogger(ActivateServiceTaskPage.class);
	
	@FindBy(xpath = "//img[@title='Back']")
	private WebElement btnBack;

	@FindBy(xpath = "//input[@value='Complete']")
	private WebElement btnComplete;

	@FindBy(xpath = "//input[@value='Save']")
	private WebElement btnSave;

	@FindBy(xpath = "//input[@value='Reset']")
	private WebElement btnReset;

	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading;

	@FindBy(id = "edpCompletionString")
	private WebElement edpCompletionDate;

	@FindBy(id = "internalTestingCompleteString")
	private WebElement internalTestingCompletionDate;

	@FindBy(xpath = "//span[text()='Circuit Testing Details']")
	private WebElement tabCircuitTestingDetails;

	@FindBy(id = "statusAct")
	private WebElement ddStatus;

	@FindBy(xpath = "//div[text()='EPL' or text()='EVPL']")
	private List<WebElement> elementEPLorEVPL;

	private boolean mstatus;

	public boolean activateService(ServiceInfo serviceInfo, ServiceLevelTaskInfo serviceLevelTaskInfo) {
		mstatus = true;
		try {
			scrollDown();
			waitForElement(edpCompletionDate);
			edpCompletionDate.click();
			edpCompletionDate.clear();
			edpCompletionDate.sendKeys(getCurrentDate());
			waitForElement(internalTestingCompletionDate);
			internalTestingCompletionDate.click();
			internalTestingCompletionDate.clear();
			internalTestingCompletionDate.sendKeys(getCurrentDate());
			waitForElement(tabCircuitTestingDetails);
			tabCircuitTestingDetails.click();
			waitForElementDisappear(elementLoading);
			waitForElement(ddStatus);
			new Select(ddStatus).selectByValue(serviceLevelTaskInfo.status);
			if (serviceInfo.serviceName.matches("EPL|EVPL") && elementEPLorEVPL.size() > 1) {
				btnSave.click();
				waitforPageLoadComplete();
				sleep(2000);
				waitForElement(tabCircuitTestingDetails);
				tabCircuitTestingDetails.click();
				waitForElementDisappear(elementLoading);
				waitForElement(elementEPLorEVPL.get(1));
				elementEPLorEVPL.get(1).click();
				waitForElement(ddStatus);
				new Select(ddStatus).selectByValue(serviceLevelTaskInfo.status);
			}
			this.ClickCompleteButton();
			waitUntilElementPresent(By.xpath("//*[text()='Activate Service' and contains(@onclick, 'COMPLETED')]"), 60);
		} catch (Exception e) {
			log.info(e.getMessage());
			mstatus = false;
		}
		return mstatus;
	}

	public boolean ClickBackButton() {
		mstatus = true;
		try {
			if (waitForElement(btnBack)) {
				btnBack.click();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			mstatus = false;
		}
		return mstatus;
	}

	public boolean ClickSaveButton() {
		mstatus = true;
		try {
			if (waitForElement(btnSave)) {
				btnSave.click();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			mstatus = false;
		}
		return mstatus;
	}

	public boolean ClickCompleteButton() {
		mstatus = true;
		try {
			if (waitForElement(btnComplete)) {
				iClick(btnComplete, btnBack, "Complete Activate Service: Activate service task page: CompleteButton");
				btnComplete.click();
				waitforPageLoadComplete();
				report.reportDoneEvent("Complete ActivateService Task", " ActivateService Task Completed");
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			mstatus = false;
		}
		return mstatus;
	}

}
