package com.comcast.century.cso.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.comcast.utils.DataTable;
import com.comcast.century.cm.pages.Page;
import com.comcast.century.data.ServiceInfo;
import com.comcast.reporting.Status;
import com.comcast.utils.SeleniumReport;
import com.comcast.utils.ComcastTest.FrameworkContext;

public class WorkOrderTabPageCSO extends Page {

	public WorkOrderTabPageCSO(FrameworkContext context) {
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

	@FindBy(css = "span#workorder")
	private WebElement tabWorkorder;

	@FindBy(xpath = "//*[@id='RightFrame']")
	private WebElement frameRight;

	@FindBy(xpath = "//*[@id='codition' and contains(@src,'getWorklists.exc')]")
	private WebElement frameCodition;

	@FindBy(xpath = "//img[@class[contains(.,'expand-right')]]")
	private WebElement btnExpand;

	@FindBy(xpath = "//div[text()='Order Search']/../../following-sibling::*/child::*")
	private WebElement btnExpandOrderSearch;

	@FindBy(xpath = "//div[text()='Worklist']/../../following-sibling::*/child::*")
	private WebElement btnExpandWorklist;

	@FindBy(xpath = "//span[@id='serviceorder']/a")
	private WebElement LinkServiceOrder;

	@FindBy(xpath = "//*[@id='servicerequest']/a")
	private WebElement LinkServiceRequest;

	@FindBy(xpath = "//*[@id='Presalesworklist']/a")
	private WebElement LinkPreSalesWorklist;

	@FindBy(xpath = "//*[@id='Postsalesworklist']/a")
	private WebElement LinkPostSalesWorklist;

	// *[@id='serviceRequestID']

	@FindBy(xpath = "//input[@id='serviceRequestID']")
	private WebElement txtSrId;

	@FindBy(xpath = "//*[@id='servicereqId']")
	private WebElement txtServiceReqId;

	@FindBy(xpath = "//input[@id='serv_req_id']")
	private WebElement txtPostSalesSrId;

	@FindBy(xpath = "//img[@title='Back']")
	private WebElement btnBack;

	@FindBy(xpath = "//*[@id='surveyId']")
	private WebElement txtSurveyId;

	// span[text()='Search']

	/*
	 * @FindBy(xpath = "//span[text()='Search']/following-sibling::*") private
	 * WebElement btnSearch ;
	 */

	@FindBy(xpath = "//a[text()='Advanced Search']")
	private WebElement linkAdvancedSearch;

	@FindBy(xpath = "//*[@id='advance_search_WL']")
	private WebElement linkAdvancedSearchWL;

	@FindBy(xpath = "//iframe[@id='soadvcodition']")
	private WebElement frameSOAdvancedSearch;

	@FindBy(xpath = "//iframe[@id='wlAdvSearch']")
	private WebElement frameWLAdvancedSearch;

	@FindBy(xpath = "//input[@value='Search']")
	private WebElement btnSearchAdvancedSearch;

	// //div[.='EVPL']/../../descendant::a[contains(@onclick,'SOTaskView')][contains(@onclick,'SOTaskview')]
	@FindBy(xpath = "//div[.='EDI']/../../descendant::img[contains(@src,'progress') or contains(@src,'activated') or contains(@src,'completed')]/../../../descendant::a[contains(@onclick,'SOTaskView')]")
	private List<WebElement> linkEDIFlow;

	@FindBy(xpath = "//div[.='EPL']/../../descendant::img[contains(@src,'progress') or contains(@src,'activated') or contains(@src,'completed')]/../../../descendant::a[contains(@onclick,'SOTaskView')]")
	private List<WebElement> linkEPLFlow;

	@FindBy(xpath = "//div[.='EVPL']/../../descendant::img[contains(@src,'progress') or contains(@src,'activated') or contains(@src,'completed')]/../../../descendant::a[contains(@onclick,'SOTaskView')]")
	private List<WebElement> linkEVPLFlow;

	@FindBy(xpath = "//div[.='ENS']/../../descendant::img[contains(@src,'progress') or contains(@src,'activated') or contains(@src,'completed')]/../../../descendant::a[contains(@onclick,'SOTaskView')]")
	private List<WebElement> linkENSFlow;

	// @FindBy(xpath =
	// "//*[.='Site']/../preceding-sibling::td[1]/child::*/child::*")
	// div[.='Site']/../../descendant::a[contains(@onclick,'SOTaskView')]
	@FindBy(xpath = "//div[.='Site']/../../descendant::a[contains(@onclick,'SOTaskView')]")
	private List<WebElement> linkSiteFlow;

	@FindBy(xpath = "//div[.='Equipment Fee']/../../descendant::a[contains(@onclick,'SOTaskView')]")
	private List<WebElement> linkEquipmentFeeFlow;

	@FindBy(xpath = "//div[.='BGP']/../../descendant::a[contains(@onclick,'SOTaskView')]")
	private WebElement linkBGPFlow;

	@FindBy(xpath = "//div[.='Trunk PRI']/../../descendant::a[contains(@onclick,'SOTaskView')]")
	private WebElement linkTrunkPRIFlow;

	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading;

	@FindBy(xpath = "//*[@value='othersTask']")
	private WebElement radioButtonAllTask;

	@FindBy(xpath = "//*[@value='userGroupTasks']")
	private WebElement radioButtonUserGroupTask;

	@FindBy(xpath = "//*[@value='myTasks']")
	private WebElement radioButtonMyTask;

	@FindBy(xpath = "//*[@value='---More Actions---']")
	private WebElement ddtxtMoreActions;

	@FindBy(xpath = "//*[@value='---More Actions---']/../following-sibling::td/child::div")
	private WebElement ddArrwMoreActions;

	@FindBy(xpath = "//li[text()='Complete']")
	private WebElement ddValueComplete;

	@FindBy(xpath = "//li[text()='Transfer']")
	private WebElement ddValueTransfer;

	@FindBy(xpath = "//*[@value='Go']")
	private WebElement btnGo;

	@FindBy(xpath = "//*[@id='self']")
	private WebElement radioButtonSelfTransfer;

	@FindBy(xpath = "//*[@id='transferTask']")
	private WebElement ButtonTransfer;

	@FindBy(xpath = "//*[@value='Ok']")
	private WebElement ButtonOk;

	@FindBy(xpath = "a[onclick*='callServOrder']")
	private WebElement linkSOCount;

	@FindBy(xpath = "//*[@id='dynaDiv']/child::div[contains(.,'CATTest_Label')]")
	private WebElement labelDisplay;

	@FindBy(xpath = "//span[.='Search']/following-sibling::*")
	private WebElement btnSearch;

	private boolean mstatus = true;

	public boolean verifySOCount(String SRID) {
		try {
			waitforPageLoadComplete();
			waitForElement(btnExpand);
			btnExpand.click();
			btnExpandOrderSearch.click();
			LinkServiceRequest.click();
			waitforPageLoadComplete();
			WaitandSwitchToFrame(frameRight);
			waitForElementDisappear(elementLoading);
			waitForElement(txtServiceReqId);
			txtServiceReqId.sendKeys(SRID);
			waitForElement(btnSearch);
			iClick(btnSearch);
			waitForElementDisappear(elementLoading);
			waitForElement(linkSOCount);
			iClick(linkSOCount);
			waitforPageLoadComplete();
			browser.switchTo().defaultContent();
		} catch (Exception ex) {
			mstatus = false;
		}
		return mstatus;
	}

	public boolean SearchForOrderInSO(String SRID, int retryCount) {
		try {
			if (!ClickBackButton(3)) {
				waitforPageLoadComplete();
				if (waitForElement(btnExpand, 5)) {
					btnExpand.click();
					btnExpandOrderSearch.click();
					LinkServiceOrder.click();
					WaitandSwitchToFrame(frameRight, 5);
				}
			}
			waitforPageLoadComplete();
			waitForElementDisappear(elementLoading);
			if (waitForElement(txtSrId)) {
				System.out.println("Search box present");
				if (!SRID.equalsIgnoreCase(txtSrId.getAttribute("value")) || retryCount > 1) {
					txtSrId.clear();
					iSendKeys(txtSrId, SRID);
					iClick(btnSearch, null, "Search SRID:Service Order Page:Search button");

				}

				browser.switchTo().defaultContent();
			}

		} catch (Exception ex) {
			mstatus = false;
		}
		return mstatus;
	}

	public boolean SearchForOrderInPostSales(String SRID) {
		try {
			waitforPageLoadComplete();
			waitForElement(btnExpandWorklist);
			btnExpandWorklist.click();
			LinkPostSalesWorklist.click();
			waitforPageLoadComplete();
			WaitandSwitchToFrame(frameRight);
			waitForElementDisappear(elementLoading);
			if (waitForElement(txtPostSalesSrId)) {
				txtPostSalesSrId.sendKeys(SRID);
			}
			if (waitForElement(linkAdvancedSearchWL)) {
				linkAdvancedSearchWL.click();
				waitforPageLoadComplete();
				WaitandSwitchToFrame(frameWLAdvancedSearch);
				waitForElement(btnSearchAdvancedSearch);
				btnSearchAdvancedSearch.click();
				browser.switchTo().defaultContent();
				waitForElementDisappear(elementLoading);
				report.updateTestLog("Search for Order", "Order Searched Successfully", Status.SCREENSHOT);
			}
		} catch (Exception ex) {
			mstatus = false;
		}
		return mstatus;
	}

	public boolean ClickFirstSiteFlow() {
		try {
			WaitandSwitchToFrame(frameRight);
			if (waitForElement(linkSiteFlow.get(0))) {
				linkSiteFlow.get(0).sendKeys(Keys.chord(Keys.CONTROL, Keys.ARROW_DOWN));
				linkSiteFlow.get(0).click();
				waitforPageLoadComplete();
				report.reportDoneEvent("Click on Site level flow", "Site level flow Clicked");
			} else {
				mstatus = false;
			}
		} catch (Exception ex) {
			mstatus = false;
		}
		return mstatus;
	}

	public boolean ClickCoaxSiteFlow(String coaxSite1) {

		try {
			WaitandSwitchToFrame(frameRight);
			if (!(coaxSite1.equalsIgnoreCase(""))) {
				String xpath = "//div[.='Site']/../../descendant::" + "div[.='" + coaxSite1 + "']/../../"
						+ "descendant::a[contains(@onclick,'SOTaskView')]"; // "CoaxSite1_RT"

				if (waitUntilElementPresent(By.xpath(xpath), 30)) {

					if (waitForElement(browser.findElement(By.xpath(xpath)), 1)) {
						browser.findElement(By.xpath(xpath)).sendKeys(Keys.chord(Keys.CONTROL, Keys.END));
						browser.findElement(By.xpath(xpath)).click();
						waitforPageLoadComplete();
						report.reportDoneEvent("Clicked on Coax Site level flow", "Site level flow Clicked");
					}
				} else {
					mstatus = false;
				}

			}

		} catch (Exception ex) {
			mstatus = false;
		}
		return mstatus;
	}

	public boolean ClickFiberSiteFlow(String fiberSite1) {
		try {
			WaitandSwitchToFrame(frameRight);

			if (!(fiberSite1.equalsIgnoreCase(""))) {
				String xpath = "//div[.='Site']/../../descendant::" + "div[.='" + fiberSite1 + "']/../../"
						+ "descendant::a[contains(@onclick,'SOTaskView')]"; // "FiberSite1_RT"

				if (waitUntilElementPresent(By.xpath(xpath), 30)) {

					if (waitForElement(browser.findElement(By.xpath(xpath)), 1)) {
						browser.findElement(By.xpath(xpath)).sendKeys(Keys.chord(Keys.CONTROL, Keys.END));
						browser.findElement(By.xpath(xpath)).click();
						waitforPageLoadComplete();
						report.reportDoneEvent("Click on Fiber Site level flow", "Site level flow Clicked");
					}
				} else {
					mstatus = false;
				}
			}
		} catch (Exception ex) {
			mstatus = false;
		}
		return mstatus;
	}

	public boolean ClickSecondSiteFlow() {

		try {
			if (waitForElement(linkSiteFlow.get(1))) {
				linkSiteFlow.get(0).sendKeys(Keys.chord(Keys.CONTROL, Keys.END));
				clickndRelease(linkSiteFlow.get(1));
				waitforPageLoadComplete();
				report.reportDoneEvent("Click on Site level flow", "Site level flow Clicked");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			mstatus = false;
		}
		return mstatus;

	}

	public boolean ClickServiceFlow(ServiceInfo serviceInfo, int i)
			throws InterruptedException, IndexOutOfBoundsException {

		try {
			WebElement elementToClick = null;
			WaitandSwitchToFrame(frameRight);
			switch (serviceInfo.serviceName) {

			case "EDI":
			case "EDI-BGP":
			case "EDI-ToF":
				try {
					if (waitForElement(linkEDIFlow.get(i), 10)) {
						elementToClick = linkEDIFlow.get(i);
					} else
						mstatus = false;
				} catch (Exception e) {
					mstatus = false;
				}
				break;
			case "EPL":
				try {
					if (waitForElement(linkEPLFlow.get(i), 10)) {
						elementToClick = linkEPLFlow.get(i);
					} else
						mstatus = false;
				} catch (Exception e) {
					mstatus = false;
				}
				break;
			case "EVPL":
				try {
					if (waitForElement(linkEVPLFlow.get(i), 10)) {
						elementToClick = linkEVPLFlow.get(i);
					} else
						mstatus = false;
				} catch (Exception e) {
					mstatus = false;
				}
				break;
			case "ENS":
			case "ENS-PRI":
				try {
					if (waitForElement(linkENSFlow.get(i), 10)) {
						elementToClick = linkENSFlow.get(i);
					} else
						mstatus = false;
				} catch (Exception e) {
					mstatus = false;
				}
				break;
			}

			elementToClick.sendKeys(Keys.chord(Keys.CONTROL, Keys.ARROW_DOWN));
			clickndRelease(elementToClick);
			waitforPageLoadComplete();
			report.reportDoneEvent("Click on Service level flow", "Service level flow Clicked");
		} catch (Exception ex) {
			mstatus = false;
		}
		return mstatus;
	}

	public boolean ClickEPLFlow() throws InterruptedException {
		try {
			if (waitForElement(linkEPLFlow.get(0))) {
				clickndRelease(linkEPLFlow.get(0));
				waitforPageLoadComplete();
				report.reportDoneEvent("Click on Service level flow", "Service level flow Clicked");
			}
		} catch (Exception ex) {
			mstatus = false;
		}
		return mstatus;
	}

	public boolean ClickEquipmentFeeFlow(int i) throws InterruptedException {
		try {
			WaitandSwitchToFrame(frameRight);
			if (waitForElement(linkEquipmentFeeFlow.get(i))) {
				clickndRelease(linkEquipmentFeeFlow.get(i)); //
				waitforPageLoadComplete();
				report.reportDoneEvent("Click on Equipment Fee flow", "Equipment Fee flow Clicked");
			}
		} catch (Exception ex) {
			mstatus = false;
		}
		return mstatus;
	}

	public boolean ClickBGPFlow() throws InterruptedException {
		try {
			if (waitForElement(linkBGPFlow)) {
				clickndRelease(linkBGPFlow);
				// linkEquipmentFeeFlow.click();
				waitforPageLoadComplete();
				report.reportDoneEvent("Click on BGP flow", "BGP flow Clicked");
			}
		} catch (Exception ex) {
			mstatus = false;
		}
		return mstatus;
	}

	public boolean ClickTrunkPRIFlow() throws InterruptedException {
		try {
			WaitandSwitchToFrame(frameRight);
			if (waitForElement(linkTrunkPRIFlow)) {
				linkTrunkPRIFlow.sendKeys(Keys.chord(Keys.CONTROL, Keys.ARROW_DOWN));
				clickndRelease(linkTrunkPRIFlow);
				waitforPageLoadComplete();
				report.reportDoneEvent("Click on PRI flow", "PRI flow Clicked");
			}
		} catch (Exception ex) {
			mstatus = false;
		}
		return mstatus;
	}

	public boolean SearchForSurveyInPreSales(String SurveyID) {
		try {
			waitforPageLoadComplete();
			waitForElement(btnExpand);
			btnExpand.click();
			waitForElement(LinkPreSalesWorklist);
			LinkPreSalesWorklist.click();
			waitforPageLoadComplete();
			WaitandSwitchToFrame(frameRight);
			waitForElementDisappear(elementLoading);
			if (waitForElement(txtSurveyId)) {
				txtSurveyId.sendKeys(SurveyID);
			}
			if (waitForElement(linkAdvancedSearch)) {
				linkAdvancedSearch.click();
				waitforPageLoadComplete();
				WaitandSwitchToFrame(frameWLAdvancedSearch);
				waitForElement(btnSearchAdvancedSearch);
				btnSearchAdvancedSearch.click();
				browser.switchTo().defaultContent();
				waitForElementDisappear(elementLoading);
				report.updateTestLog("Search for Order", "Order Searched Successfully", Status.SCREENSHOT);
			}
		} catch (Exception ex) {
			mstatus = false;
		}
		return mstatus;
	}

	public boolean ClickAllTask() {
		try {
			WaitandSwitchToFrame(frameRight);
			waitForElement(radioButtonAllTask);
			radioButtonAllTask.click();
			waitForElementDisappear(elementLoading);
		} catch (Exception ex) {
			mstatus = false;
		}
		return mstatus;
	}

	public boolean ClickBackButton(int waitForSecs) {
		mstatus = false;
		try {
			if (waitForElement(btnBack, waitForSecs)) {
				btnBack.click();
				mstatus = true;
			}
		} catch (Exception ex) {
			mstatus = false;
		}
		return mstatus;
	}

	public boolean verifyDisplayOfLabelName(String labelName, String fiberSite1) {
		mstatus = true;
		try {

			this.ClickFiberSiteFlow(fiberSite1);
			waitForElement(labelDisplay);
			if (labelDisplay.getText().equalsIgnoreCase(labelName)) {
				report.updateTestLog("Verify label display", "Display label Verified", Status.SCREENSHOT);
			} else
				report.reportFailEvent("Verify label display", "Display label Verified");

		} catch (Exception e) {
			mstatus = false;
		}
		return mstatus;

	}

}
