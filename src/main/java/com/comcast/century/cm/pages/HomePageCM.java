package com.comcast.century.cm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.reporting.Status;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;


public class HomePageCM extends Page {

	protected static String HOME_PAGE_TITLE_CM = "Customer Manager";

	@Override
	protected boolean isValidPage() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void waitForPageLoad() {
		// TODO Auto-generated method stub

	}

	public HomePageCM(FrameworkContext context) {
		super(context);
	}

	@FindBy(xpath = "//a[.='Home']")
	private WebElement tabHome;

	@FindBy(xpath = "//a[.='Customer']")
	private WebElement tabCustomer;

	@FindBy(xpath = "//*[@id='mainFrame']")
	private WebElement frameMain;

	@FindBy(xpath = "//*[@id='leftFrame']")
	private WebElement frameLeft;

	// *[@id='customerNameCombo-inputEl']

	@FindBy(xpath = "//input[@id='customerNameCombo-inputEl']")
	private WebElement txtCustomerName;

	@FindBy(xpath = "//*[@id='SEARCH_SERVICEORDERID']")
	private WebElement txtSRID;

	// *[@id='splitbutton-1011-btnIconEl']

	@FindBy(xpath = "//span[text()='Search']/following-sibling::span")
	private WebElement btnSearch;

	// span[contains(.,'10955195')]

	@FindBy(xpath = "//span[contains(.,'3900473')]")
	private WebElement clickSRid;

	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading;

	// span[text()='ServiceAcc024143152016']

	@FindBy(xpath = "//span[contains(.,'ServiceAcc')]")
	private WebElement clickServiceAcc;

	@FindBy(xpath = "//*[@id='CustomerFrame']")
	private WebElement frameCustomer;

	@FindBy(css = "select#SEARCH_SERVICESTATUS")
	private WebElement ddOrderStatus;

	@FindBy(css = "a[onClick*='callLabel']")
	private WebElement labelCount;

	@FindBy(css = "img[src*='progress']")
	private WebElement orderStatus;

	private boolean mstatus;

	public boolean clickOnHomeTab() {
		mstatus = true;
		try {
			waitForElement(tabHome);
			iClick(tabHome);
			waitforPageLoadComplete();
		} catch (Exception e) {
			e.printStackTrace();
			mstatus = false;
		}
		return mstatus;
	}

	public boolean searchCustomer(String custName) {
		mstatus = true;
		try {
			waitforPageLoadComplete();
			WaitandSwitchToFrame(frameMain);
			txtCustomerName.sendKeys(custName);
			iClick(btnSearch, null, "Search Customer: Home page: SearchButton");
			waitforPageLoadComplete();
			waitForElementDisappear(elementLoading);
			waitUntilElementPresent(By.xpath("//span[contains(.,'" + custName + "')]"), 120);
			browser.findElement(By.xpath("//span[contains(.,'" + custName + "')]")).click();
			waitforPageLoadComplete();
			browser.switchTo().defaultContent();
		} catch (Exception e) {
			e.printStackTrace();
			mstatus = false;
		}
		return mstatus;
	}

	public boolean searchOrder(String SRID) {
		mstatus = true;

		try {
			WaitandSwitchToFrame(frameMain);
			txtSRID.sendKeys(SRID);
			iClick(btnSearch, null, "Search SR ID: Home page: SearchButton");
			waitforPageLoadComplete();
			waitForElementDisappear(elementLoading);
			browser.switchTo().defaultContent();
		} catch (Exception e) {
			e.printStackTrace();
			mstatus = false;
		}

		return mstatus;
	}

	public boolean verifyLabelCountAndOrderStatus() {
		mstatus = true;
		try {

			WaitandSwitchToFrame(frameMain);
			waitForElement(labelCount);
			if (labelCount.getText().equalsIgnoreCase("1")) {
				report.reportDoneEvent("Verify label count", "label count verified");
			} else
				report.reportFailEvent("Verify label count", "label count not verified");
			if (isElementDisplayed(orderStatus)) {
				report.updateTestLog("Verify order status as INPROGRESS", "Status verified", Status.SCREENSHOT);
			} else
				report.reportFailEvent("Verify order status as INPROGRESS", "Status not verified");
			browser.switchTo().defaultContent();

		} catch (Exception e) {
			e.printStackTrace();
			mstatus = false;
		}
		return mstatus;
	}

}





	
	
	
	




