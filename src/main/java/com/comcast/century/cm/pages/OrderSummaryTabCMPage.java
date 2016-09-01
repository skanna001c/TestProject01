package com.comcast.century.cm.pages;

import java.awt.AWTException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.comcast.century.data.AccountInfo;
import com.comcast.century.data.OrderSummaryInfo;
import com.comcast.reporting.Status;
import com.comcast.utils.SeleniumReport;

public class OrderSummaryTabCMPage extends Page {

	public OrderSummaryTabCMPage(WebDriver browser, SeleniumReport report) {
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

	@FindBy(xpath = "//*[@id='mainFrame' and contains(@src,'loadServOrderManagementPanel.exc')]")
	private WebElement frameMain;

	// select[@id='taxJurisdiction']

	@FindBy(xpath = "//select[@id='taxJurisdiction']")
	private WebElement ddtaxJurisdiction;

	// *[@id='saleschannel']

	@FindBy(xpath = "//select[@id='saleschannel']")
	private WebElement ddsaleschannel;

	// *[@id='soldRegion']

	@FindBy(xpath = "//select[@id='soldRegion']")
	private WebElement ddsoldRegion;

	// *[@id='salesOrderNumber']

	@FindBy(xpath = "//input[@id='salesOrderNumber']")
	private WebElement txtsalesOrderNumber;

	// *[@id='salesforceopportunityid']

	@FindBy(xpath = "//input[@id='salesforceopportunityid']")
	private WebElement txtsalesforceopportunityid;

	// *[@id='submitOrder']

	@FindBy(xpath = "//input[@id='submitOrder']")
	private WebElement btnsubmitOrder;

	@FindBy(xpath = "//input[@id='customerOrderSignatureDate-inputEl']/../following-sibling::*/child::*")
	private WebElement dtCustomerOrderSig;

	@FindBy(xpath = "//input[@id='salesOrderAcceptanceDate-inputEl']/../following-sibling::*/child::*")
	private WebElement dtSalesOrderAcceptance;

	@FindBy(xpath = "//input[@id='salesOrderSubmitDate-inputEl']/../following-sibling::*/child::*")
	private WebElement dtSalesOrderSubmitted;

	@FindBy(xpath = "//span[text()='Today']/following-sibling::*")
	private List<WebElement> btnToday;

	@FindBy(xpath = "//*[@id='saleoderId']/img")
	private WebElement btnSalesOrderId;

	@FindBy(xpath = "//*[text()='Installation Charge']/../following-sibling::td[1]/child::div")
	private List<WebElement> txtNRC;

	@FindBy(xpath = "//*[text()='Monthly']/../following-sibling::td[1]/child::div")
	private List<WebElement> txtMRC;

	@FindBy(xpath = "//*[text()='Equipment Fee Configuration']/../following-sibling::td[11]/child::div")
	private List<WebElement> mrcEqFee;

	@FindBy(xpath = "//*[text()='UNI']/../following-sibling::td[11]/child::div")
	private List<WebElement> mrcUNI;

	@FindBy(xpath = "//*[text()='UNI']/../following-sibling::td[9]/child::div")
	private List<WebElement> nrcUNI;

	@FindBy(xpath = "//*[text()='Basic CoS Bandwidth']/../following-sibling::td[11]/child::div")
	private List<WebElement> mrcBCosBW;

	@FindBy(xpath = "//input[contains(@id,'numberfield')]")
	private List<WebElement> txtValue;

	@FindBy(xpath = "//input[@name='nrc']")
	private WebElement txtValueNRC;

	@FindBy(xpath = "//input[@name='rc']")
	private WebElement txtValueMRC;

	@FindBy(xpath = "//span[text()='Yes']/following-sibling::*")
	private WebElement btnYes;

	@FindBy(xpath = "//select[@id='applicationName']")
	private WebElement ddNavigateToCSO;

	@FindBy(xpath = "//button[text()='OK']")
	private WebElement btnOK;

	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading;

	@FindBy(xpath = "//img[@id='manageattach-toolEl']")
	private WebElement linkAttachments;
	
	@FindBy(css = "img[id*='managenotes']")
	private WebElement linkNotes;

	@FindBy(xpath = "//*[@id='notesframe']")
	private WebElement frameNotes;

	@FindBy(xpath = "//select[@id='attachMode']")
	private WebElement ddAttachmentRepository;

	@FindBy(xpath = "//span[text()='Browse...']")
	private WebElement btnBrowse;

	@FindBy(xpath = "//select[@id='attachmentType0']")
	private WebElement ddAttachmentType;

	@FindBy(xpath = "//*[@id='btncreate']")
	private WebElement btnAdd;

	@FindBy(xpath = "//span[text()='ATTACHMENTS']/../following-sibling::div[3]/child::img")
	private WebElement closeAttachmentWindow;
	
	@FindBy(xpath = "//span[text()='NOTES']/../following-sibling::div[3]/child::img")
	private WebElement closeNotesWindow;

	@FindBy(xpath = "//*[@id='cmbcomboworkListActions-inputEl']")
	private WebElement ddTextMoreActions;

	@FindBy(xpath = "//li[text()='Assign Label']")
	private WebElement ddValueMoreActions;

	@FindBy(xpath = "//*[@value='GO']")
	private WebElement btnGo;

	@FindBy(xpath = "//*[@id='codition']")
	private WebElement frameCondition;

	@FindBy(xpath = "//input[@placeholder='---Select Label---']")
	private WebElement txtSelectLabel;

	@FindBy(xpath = "//div[text()='CAT Test Orders']/../preceding-sibling::td/child::div/child::div")
	private WebElement chkboxCatTestOrders;

	@FindBy(xpath = "//*[@id='ApplyTo']")
	private WebElement btnApply;

	@FindBy(xpath = "//span[text()='OK']/following-sibling::span")
	private WebElement btnOk;

	@FindBy(xpath = "//div[.='Related Order ID'][contains(@id,'legendTitle')]/preceding-sibling::*")
	private WebElement btnExpandRelatedOrderID;

	@FindBy(xpath = "//*[@id='relatedOrderID']")

	private WebElement txtRelatedOrderID ;
	
	@FindBy(xpath = "//div[.='Label(s) applied successfully']")
	private WebElement msgLabelAppliedSuccessfully ;
	
	@FindBy(xpath = "//div[contains(.,'OrderManagementBAT1 has assigned CATTest_Label')]")
	private WebElement msgLabelNotes ;
	
	
	
	private boolean mstatus=true;

	private String relatedOrderIDValue;
	
	
	public boolean submitOrder(OrderSummaryInfo orderSummaryInfo,String eRate){
		try{

			this.enterOrderDetails(orderSummaryInfo);
			this.mrcNrc_Value(orderSummaryInfo);
			if(eRate.equalsIgnoreCase("Yes")){
				this.Attachments(orderSummaryInfo);
			}
			this.ClickSubmitOrderButton();
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	

	public String verifyRelatedOrderIDAttribute() {
		try {
			ShortWaitandSwitchToFrame(frameMain);
			waitForElement(btnExpandRelatedOrderID);
			iClick(btnExpandRelatedOrderID);
			waitForElementDisappear(elementLoading);
			if (waitForElement(txtRelatedOrderID)) {
				report.reportPassEvent("Verify Related Order ID attribute", " Related Order ID attribute present");
				report.updateTestLog(" Related Order ID attribute", "Related Order ID attribute present",
						Status.SCREENSHOT);
			} else {
				report.reportFailEvent("Verify Related Order ID attribute", "Related Order ID attribute not present");
			}
			relatedOrderIDValue = "456258965412589aswedfrthg@#$%t";
			txtRelatedOrderID.sendKeys(relatedOrderIDValue);
			if (txtRelatedOrderID.getAttribute("maxlength").equalsIgnoreCase("30")) {
				report.reportPassEvent("Verify Related Order ID attribute takes max of 30 char ",
						"Verified successfully");
			} else {
				report.reportFailEvent("Verify Related Order ID attribute takes max of 30 char", "Verification fails");
			}
			scrollUp();
			browser.switchTo().defaultContent();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			mstatus = false;
		}
		return relatedOrderIDValue;
	}

	
	
	public boolean assignLabelCM(String labelName){

		String xpathLabel="//div[text()='"+labelName+"']/../preceding-sibling::td/child::div/child::div";
		mstatus = true;
		try {
			ShortWaitandSwitchToFrame(frameMain);
			waitForElement(ddTextMoreActions);
			ddValueSelect(ddTextMoreActions, ddValueMoreActions, "Assign Label");
			btnGo.click();
			waitforPageLoadComplete();
			WaitandSwitchToFrame(frameCondition);
			waitForElement(txtSelectLabel);
			txtSelectLabel.sendKeys(labelName);
			waitUntilElementPresent(By.xpath(xpathLabel), 10);
			WebElement chkboxLabelName= browser.findElement(By.xpath(xpathLabel));
			chkboxLabelName.click();
			btnApply.click();
			if(waitForElement(msgLabelAppliedSuccessfully)){
				report.reportPassEvent("Verify status", "label applied successfully");
				report.updateTestLog("Verify status", "label applied successfully", Status.SCREENSHOT);
			}else{
				report.reportFailEvent("Verify status", "label not applied");
			}
			waitForElement(btnOk);
			btnOk.click();
			browser.switchTo().defaultContent();

		}catch(Exception e){

			System.out.println(e.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean verifyNotesForLabels(){
		mstatus=true;
		try{
			WaitandSwitchToFrame(frameMain);
			waitForElement(linkNotes);
			clickndRelease(linkNotes);
			waitforPageLoadComplete();
			WaitandSwitchToFrame(frameNotes);
			while(!waitForElement(msgLabelNotes)){}
			scrollDown();
			if(isElementDisplayed(msgLabelNotes)){
				report.updateTestLog("Verify if notes generated for label assigned", "Notes verified succesfully",Status.SCREENSHOT);
			}else report.reportFailEvent("Verify if notes generated for label assigned", "Notes verification failed");
			browser.switchTo().defaultContent();
			WaitandSwitchToFrame(frameMain);
			waitForElement(closeNotesWindow);
			clickndRelease(closeNotesWindow);
			browser.switchTo().defaultContent();
		}catch(Exception e){
			mstatus=false;
		}
		return mstatus;
	}

	public boolean enterOrderDetails(OrderSummaryInfo orderSummaryInfo) {
		mstatus = true;
		try {
			WaitandSwitchToFrame(frameMain);
			waitForElement(dtCustomerOrderSig);
			dtCustomerOrderSig.click();
			btnToday.get(0).click();
			waitForElement(ddtaxJurisdiction);
			new Select(ddtaxJurisdiction).selectByVisibleText(orderSummaryInfo.taxJurisdiction);
			report.reportDoneEvent("Select Tax Jurisdiction",
					"Select Tax Jurisdiction as->" + orderSummaryInfo.taxJurisdiction);
			new Select(ddsaleschannel).selectByVisibleText(orderSummaryInfo.salesChannel);
			report.reportDoneEvent("Select Sales Channel",
					"Selected Sales Channel as->" + orderSummaryInfo.salesChannel);
			new Select(ddsoldRegion).selectByVisibleText(orderSummaryInfo.soldRegion);
			report.reportDoneEvent("Select Sold Region", "Selected Sold Region as->" + orderSummaryInfo.soldRegion);
			dtSalesOrderAcceptance.click();
			btnToday.get(1).click();
			dtSalesOrderSubmitted.click();
			btnToday.get(2).click();
			waitForElement(txtsalesOrderNumber);
			String salesOrderNumber = randomNumber(5);
			txtsalesOrderNumber.sendKeys(salesOrderNumber);
			report.reportDoneEvent("Enter Sales Order Number", "Entered Sales Order Number as->" + salesOrderNumber);
			waitForElement(txtsalesforceopportunityid);
			txtsalesforceopportunityid.sendKeys(orderSummaryInfo.opportunityId);
			report.reportDoneEvent("Enter Opportunity ID",
					"Entered Opportunity ID as->" + orderSummaryInfo.opportunityId);
			waitForElement(btnSalesOrderId);
			btnSalesOrderId.click();
		} catch (Exception e) {
			mstatus = false;
		}
		return mstatus;
	}

	public boolean Attachments(OrderSummaryInfo orderSummaryInfo) throws AWTException, InterruptedException {
		mstatus = true;
		try {
			waitForElement(linkAttachments);
			clickndRelease(linkAttachments);
			waitforPageLoadComplete();
			WaitandSwitchToFrame(frameNotes);
			waitForElement(ddAttachmentRepository);
			new Select(ddAttachmentRepository).selectByVisibleText(orderSummaryInfo.attachmentRepository);
			waitForElement(btnBrowse);
			clickndRelease(btnBrowse);
			Thread.sleep(5000);
			uploadAttachments(System.getProperty("user.dir") + "\\src\\test\\resources\\attachements.txt");
			waitForElement(ddAttachmentType);
			new Select(ddAttachmentType).selectByVisibleText(orderSummaryInfo.attachmentType);
			waitForElement(btnAdd);
			clickndRelease(btnAdd);
			Thread.sleep(15000);
			browser.switchTo().defaultContent();
			WaitandSwitchToFrame(frameMain);
			waitForElement(closeAttachmentWindow);
			clickndRelease(closeAttachmentWindow);
		} catch (Exception e) {
			mstatus = false;
		}
		return mstatus;
	}

	public boolean mrcNrc_Value(OrderSummaryInfo orderSummaryInfo) throws InterruptedException {
		mstatus = true;
		try {
			enterValue(nrcUNI, txtValueNRC,
					orderSummaryInfo.valueNRC); /* Enter UNI NRC Value */
			enterValue(mrcUNI, txtValueMRC,
					orderSummaryInfo.valueMRC); /* Enter UNI MRC Value */
			enterValue(mrcBCosBW, txtValueMRC,
					orderSummaryInfo.valueMRC); /* Enter BCosW MRC value Enter */
			enterValue(mrcEqFee, txtValueMRC,
					orderSummaryInfo.valueEqFeeMRC); /* Enter MRC for Equipment Fee */
		} catch (Exception e) {
			mstatus = false;
		}
		return mstatus;
	}

	public boolean ClickSubmitOrderButton() {
		mstatus = true;
		try {
			// ShortWaitandSwitchToFrame(frameMain);
			if (waitForElement(btnsubmitOrder)) {
				btnsubmitOrder.click();
				waitforPageLoadComplete();
			}
			browser.switchTo().defaultContent();
			waitForElementDisappear(elementLoading);
			waitforPageLoadComplete();
			report.updateTestLog("Click Sumbit Order", "Order Submitted Successfully", Status.SCREENSHOT);
		} catch (Exception e) {
			mstatus = false;
		}
		return mstatus;
	}

	public boolean NavigateToCSO(OrderSummaryInfo orderSummaryInfo) {
		mstatus = true;
		try {
			waitforPageLoadComplete();
			waitForElement(ddNavigateToCSO);
			new Select(ddNavigateToCSO).selectByVisibleText("Century Service Orchestrator");
			report.reportDoneEvent("Navigate to " + orderSummaryInfo.goToApplication,
					"Navigated to " + orderSummaryInfo.goToApplication);
			waitForElement(btnOK);
			btnOK.click();
			waitforPageLoadComplete();
		} catch (Exception e) {
			mstatus = false;
		}
		return mstatus;

	}

	public boolean cancelSupSubmitOrder() {
		mstatus = true;
		try {
			WaitandSwitchToFrame(frameMain);
			waitForElement(dtCustomerOrderSig);
			dtCustomerOrderSig.click();
			btnToday.get(0).click();
			dtSalesOrderAcceptance.click();
			btnToday.get(1).click();
			dtSalesOrderSubmitted.click();
			btnToday.get(2).click();
			waitForElement(btnsubmitOrder);
			iClick(btnsubmitOrder);
			waitforPageLoadComplete();
			browser.switchTo().defaultContent();
		} catch (Exception e) {
			mstatus = false;
		}

		return mstatus;
	}

}
