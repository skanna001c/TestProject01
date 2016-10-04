package com.comcast.cm.pages;

import java.awt.AWTException;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.comcast.commons.ComcastTest;
import com.comcast.data.OrderSummaryInfo;
import com.comcast.reporting.Status;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;


public class OrderSummaryTabCMPage extends Page {

	public OrderSummaryTabCMPage(FrameworkContext context){
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
	
	@FindBy(xpath = "//*[@id='mainFrame' and contains(@src,'loadServOrderManagementPanel.exc')]")
	private WebElement frameMain;

	@FindBy(id ="taxJurisdiction")
	private WebElement ddtaxJurisdiction;

	@FindBy(id = "saleschannel")
	private WebElement ddsaleschannel;

	@FindBy(id = "soldRegion")
	private WebElement ddsoldRegion;

	@FindBy(id = "salesOrderNumber")																				
	private WebElement txtsalesOrderNumber;

	@FindBy(id = "salesforceopportunityid")
	private WebElement txtsalesforceopportunityid;

	@FindBy(id = "submitOrder")
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

	@FindBy(xpath = "//*[text()='BGP']/../following-sibling::td[11]/child::div")
	private List<WebElement> mrcBGP;
	
	@FindBy(xpath = "//*[text()='BGP']/../following-sibling::td[9]/child::div")
	private List<WebElement> nrcBGP;
	
	@FindBy(xpath = "//*[text()='UNI']/../following-sibling::td[9]/child::div")
	private List<WebElement> nrcUNI;

	@FindBy(xpath = "//*[.='Basic CoS Bandwidth' or .='Premium CoS Bandwidth']/../following-sibling::td[11]/child::div")
	private List<WebElement> mrcCosBW;

	@FindBy(xpath = "//input[contains(@id,'numberfield')]")
	private List<WebElement> txtValue;

	@FindBy(name ="nrc")
	private WebElement txtValueNRC;

	@FindBy(name ="rc")
	private WebElement txtValueMRC;

	@FindBy(xpath = "//span[text()='Yes']/following-sibling::*")
	private WebElement btnYes;

	@FindBy(id = "applicationName")
	private WebElement ddNavigateToCSO;

	@FindBy(xpath = "//button[text()='OK']")
	private WebElement btnOK;

	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading;

	@FindBy(id = "manageattach-toolEl")
	private WebElement linkAttachments;
	
	@FindBy(css = "img[id*='managenotes']")
	private WebElement linkNotes;

	@FindBy(id = "notesframe")
	private WebElement frameNotes;

	@FindBy(id = "attachMode")
	private WebElement ddAttachmentRepository;

	@FindBy(xpath = "//span[text()='Browse...']")
	private WebElement btnBrowse;

	@FindBy(id = "attachmentType0")
	private WebElement ddAttachmentType;

	@FindBy(id = "btncreate")
	private WebElement btnAdd;

	@FindBy(xpath = "//span[text()='ATTACHMENTS']/../following-sibling::div[3]/child::img")
	private WebElement closeAttachmentWindow;
	
	@FindBy(xpath = "//span[text()='NOTES']/../following-sibling::div[3]/child::img")
	private WebElement closeNotesWindow;

	@FindBy(id = "cmbcomboworkListActions-inputEl")
	private WebElement ddTextMoreActions;

	@FindBy(xpath = "//li[text()='Assign Label']")
	private WebElement ddValueMoreActions;

	@FindBy(xpath = "//*[@value='GO']")
	private WebElement btnGo;

	@FindBy(id = "codition")
	private WebElement frameCondition;

	@FindBy(xpath = "//input[@placeholder='---Select Label---']")
	private WebElement txtSelectLabel;

	@FindBy(xpath = "//div[text()='CAT Test Orders']/../preceding-sibling::td/child::div/child::div")
	private WebElement chkboxCatTestOrders;

	@FindBy(id = "ApplyTo")
	private WebElement btnApply;

	@FindBy(xpath = "//span[text()='OK']/following-sibling::span")
	private WebElement btnOk;

	@FindBy(xpath = "//div[.='Related Order ID'][contains(@id,'legendTitle')]/preceding-sibling::*")
	private WebElement btnExpandRelatedOrderID;

	@FindBy(id = "relatedOrderID")
	private WebElement txtRelatedOrderID ;
	
	@FindBy(xpath = "//div[.='Label(s) applied successfully']")
	private WebElement msgLabelAppliedSuccessfully ;
	
	@FindBy(xpath = "//div[contains(.,'OrderManagementBAT1 has assigned CATTest_Label')]")
	private WebElement msgLabelNotes ;
	
	@FindBy(xpath = "//b[.='Activity Type']/../following-sibling::td[1]")
	private WebElement activityType ;
	
	@FindBy(xpath = "//input[@id='successMessage']/..")
	private WebElement OrdersubmittedMessage ;

	@FindBy(xpath = "//b[.='Service Request ID']/../following-sibling::td[1]")
	private WebElement SRID ;
	
	//############################### Disconnect '##########################
	
	@FindBy(xpath = "//td[contains(.,'Supp/MACD has been auto generated for the following Service Request Id(s)')]/following-sibling::td")
	private WebElement EquipmentSupSRID ;
	
	//#########################################################
	
	private boolean mstatus=true;
	
	Logger log = Logger.getLogger(OrderSummaryTabCMPage.class);

	private String relatedOrderIDValue;
	
	
	public String submitOrder(OrderSummaryInfo orderSummaryInfo, String eRate) {
		String SRID = null;
		try {			
			 if(testSettings.getEnvironmentToTest().equalsIgnoreCase("PROD")){
				 this.assignLabelCM("CAT Test Orders");
				 report.updateTestLog("Label Assign", "Label Assigned",
							Status.SCREENSHOT);
			 }
			this.enterOrderDetails(orderSummaryInfo);
			if ((activityType.getText().trim()).equalsIgnoreCase("New Connect")) {
				this.mrcNrc_Value(orderSummaryInfo);
				if (eRate.equalsIgnoreCase("Yes")) {
					this.Attachments(orderSummaryInfo);
				}
			}
			SRID = this.ClickSubmitOrderButton();
		} catch (Exception e) {
			log.info(e.getMessage());
			SRID = null;
		}
		return SRID;
	}
	
	public String FectchEDIEquipmentSupSRID(OrderSummaryInfo orderSummaryInfo)
	{	String eqSupSRID;
		if(orderSummaryInfo.supplementType.equalsIgnoreCase("disconnect")){
			WaitandSwitchToFrame(frameMain);
			waitforPageLoadComplete();
			if(waitForElement(EquipmentSupSRID))
			{
				report.updateTestLog("Supp/MACD has been auto generated for the following Service Request Id", "Verified",
						Status.SCREENSHOT);
				eqSupSRID=EquipmentSupSRID.getText().trim();
				log.info("Disconnect Equipment SR ID: " + eqSupSRID);
				browser.switchTo().defaultContent();
				return eqSupSRID;
				
			}
			else
				return null;
		}
		else return null; 
	}
	
	public String submitOrder_OnlyMRCNoNRC(OrderSummaryInfo orderSummaryInfo, String eRate) {
		String SRID = null;
		try {			
			 if(testSettings.getEnvironmentToTest().equalsIgnoreCase("PROD")){
				 this.assignLabelCM("CAT Test Orders");
				 report.updateTestLog("Label Assign", "Label Assigned",
							Status.SCREENSHOT);
			 }
			this.enterOrderDetails(orderSummaryInfo);
			if ((activityType.getText().trim()).equalsIgnoreCase("New Connect")) {
				this.onlyMRC_NONRC_Value((orderSummaryInfo));
				if (eRate.equalsIgnoreCase("Yes")) {
					this.Attachments(orderSummaryInfo);
				}
			}
			SRID = this.ClickSubmitOrderButton();
		} catch (Exception e) {
			log.info(e.getMessage());
			SRID = null;
		}
		return SRID;
	}

	public String verifyRelatedOrderIDAttribute() {
		try {
			ShortWaitandSwitchToFrame(frameMain);
			waitForElement(btnExpandRelatedOrderID);
			iClick(btnExpandRelatedOrderID, null, "Expand related order ID: Order summary page: ExpandRelatedOrderIDButton");
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
			log.info(e.getMessage());
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
			iClick(btnGo, null, "Click on Assign label GO button: Order summary page: GoButton");			
			waitforPageLoadComplete();
			WaitandSwitchToFrame(frameCondition);
			waitForElement(txtSelectLabel);
			txtSelectLabel.sendKeys(labelName);
			waitUntilElementPresent(By.xpath(xpathLabel), 10);
			WebElement chkboxLabelName= browser.findElement(By.xpath(xpathLabel));
			chkboxLabelName.click();
			iClick(btnApply, msgLabelAppliedSuccessfully, "Click on Apply button in assign label: Order summary page: ApplyButton");			
			if(waitForElement(msgLabelAppliedSuccessfully)){
				report.reportPassEvent("Verify status", "label applied successfully");
				report.updateTestLog("Verify status", "label applied successfully", Status.SCREENSHOT);
			}else{
				report.reportFailEvent("Verify status", "label not applied");
			}
			waitForElement(btnOk);
			iClick(btnOk, null, "Click on OK button in assign label: Order summary page: OKButton");
			browser.switchTo().defaultContent();

		}catch(Exception e){
			log.info(e.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean verifyNotesForLabels(){
		mstatus=true;
		try{
			WaitandSwitchToFrame(frameMain);
			waitForElement(linkNotes);
			iClick(linkNotes, null, "Click on Notes Link: Order summary page: NotesLink");			
			waitforPageLoadComplete();
			WaitandSwitchToFrame(frameNotes);
			while(!waitForElement(msgLabelNotes)){}
			msgLabelNotes.sendKeys(Keys.ARROW_DOWN);
			if(isElementDisplayed(msgLabelNotes)){
				report.updateTestLog("Verify if notes generated for label assigned", "Notes verified succesfully",Status.SCREENSHOT);
			}else report.reportFailEvent("Verify if notes generated for label assigned", "Notes verification failed");
			browser.switchTo().defaultContent();
			WaitandSwitchToFrame(frameMain);
			waitForElement(closeNotesWindow);
			clickndRelease(closeNotesWindow);
			browser.switchTo().defaultContent();
		}catch(Exception e){
			log.info(e.getMessage());
			mstatus=false;
		}
		return mstatus;
	}

	public boolean enterOrderDetails(OrderSummaryInfo orderSummaryInfo) {
		mstatus = true;
		try {
			WaitandSwitchToFrame(frameMain,8);
			waitForElement(dtCustomerOrderSig);
			dtCustomerOrderSig.click();
			btnToday.get(0).click();
			new Select(ddtaxJurisdiction).selectByVisibleText(orderSummaryInfo.taxJurisdiction);
			report.reportDoneEvent("Select Tax Jurisdiction",
					"Select Tax Jurisdiction as->" + orderSummaryInfo.taxJurisdiction);
			new Select(ddsaleschannel).selectByVisibleText(orderSummaryInfo.salesChannel);
			report.reportDoneEvent("Select Sales Channel",
					"Selected Sales Channel as->" + orderSummaryInfo.salesChannel);
			new Select(ddsoldRegion).selectByVisibleText(orderSummaryInfo.soldRegion);
			report.reportDoneEvent("Select Sold Region", "Selected Sold Region as->" + orderSummaryInfo.soldRegion);
			waitForElement(dtSalesOrderAcceptance);
			dtSalesOrderAcceptance.click();
			btnToday.get(1).click();
			dtSalesOrderSubmitted.click();
			btnToday.get(2).click();
			//if (activityType.getText().equalsIgnoreCase("New Connect")){
				iSendKeys(txtsalesOrderNumber, randomNumber(5));
			    iSendKeys(txtsalesforceopportunityid,orderSummaryInfo.opportunityId );
			    report.reportDoneEvent("Enter Opportunity ID",
						"Entered Opportunity ID as->" + orderSummaryInfo.opportunityId);
				btnSalesOrderId.click();
			//}
			
		} catch (Exception e) {
			log.info(e.getMessage());
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
			new Select(ddAttachmentRepository).selectByVisibleText("Century");
			waitForElement(btnBrowse);
			clickndRelease(btnBrowse);
			Thread.sleep(5000);
			if(testSettings.getGRIDstatus().equalsIgnoreCase("true"))
			{
				new FileOutputStream("C:\\Users\\!centurybsacats\\attachements.txt", false).close();
				uploadAttachments("C:\\Users\\!centurybsacats\\attachements.txt");
			}
			else
			{
				uploadAttachments(System.getProperty("user.dir") + "\\src\\test\\resources\\attachements.txt");
			}
			waitForElement(ddAttachmentType);
			new Select(ddAttachmentType).selectByVisibleText("Tax Exemption Form");
			waitForElement(btnAdd);
			clickndRelease(btnAdd);
			Thread.sleep(15000);
			browser.switchTo().defaultContent();
			WaitandSwitchToFrame(frameMain);
			waitForElement(closeAttachmentWindow);
			clickndRelease(closeAttachmentWindow);
		} catch (Exception e) {
			log.info(e.getMessage());
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
			enterValue(mrcCosBW, txtValueMRC,
					orderSummaryInfo.valueMRC); /* Enter BCosW MRC value Enter */
			enterValue(mrcEqFee, txtValueMRC,
					orderSummaryInfo.valueEqFeeMRC); /* Enter MRC for Equipment Fee */
		} catch (Exception e) {
			log.info(e.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	
	public boolean mrcNrc_Supplements(OrderSummaryInfo orderSummaryInfo) throws InterruptedException {
		mstatus = true;
		try {
			enterValue(nrcBGP,txtValueNRC,orderSummaryInfo.valueNRC);   /*Enter BGP NRC*/
			enterValue(mrcBGP,txtValueMRC,orderSummaryInfo.valueMRC);   /*ENTER BGP MRC*/
		} catch (Exception e) {
			log.info(e.getMessage());
			mstatus = false;
		}
		return mstatus;
	}

	public boolean onlyMRC_NONRC_Value(OrderSummaryInfo orderSummaryInfo) throws InterruptedException {
		mstatus = true;
		try {
			/*enterValue(nrcUNI, txtValueNRC,
					orderSummaryInfo.valueNRC);  Enter UNI NRC Value */
			enterValue(mrcUNI, txtValueMRC,
					orderSummaryInfo.valueMRC); /* Enter UNI MRC Value */
			enterValue(mrcCosBW, txtValueMRC,
					orderSummaryInfo.valueMRC); /* Enter BCosW MRC value Enter */
			enterValue(mrcEqFee, txtValueMRC,
					orderSummaryInfo.valueEqFeeMRC); /* Enter MRC for Equipment Fee */
		} catch (Exception e) {
			log.info(e.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	public String ClickSubmitOrderButton() {
		String SRID_RT = null;
		try {
			if (waitForElement(btnsubmitOrder)) {
				iClick(btnsubmitOrder, null, "Submit Order: Order Summary Page: SubmitButton");
				if(waitForElement(btnYes,5)){
					btnYes.click();
				}
				waitforPageLoadComplete();
				waitForElementDisappear(elementLoading);
				if(OrdersubmittedMessage.getText().contains("Order submitted successfully"))
				{
					report.updateTestLog("Click Sumbit Order", "Order Submitted Successfully", Status.SCREENSHOT);
					report.reportPassEvent("Click Sumbit Order", "Order Submitted Successfully");
					SRID_RT = SRID.getText();
					log.info("SR ID is: " + SRID_RT);
				}
				else
				{
					report.updateTestLog("Click Sumbit Order", "Order Submission Failed", Status.FAIL);
					report.reportFailEvent("Click Sumbit Order", "Order Submission Failed");
				}
			}
			browser.switchTo().defaultContent();
			waitforPageLoadComplete();
		} catch (Exception e) {
			log.info(e.getMessage());
			SRID_RT = null;
		}
		return SRID_RT;
	}

	/*public boolean NavigateToCSO(OrderSummaryInfo orderSummaryInfo) {
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

	}*/

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
			iClick(btnsubmitOrder, null, "Submit Order: Order Summary Page: SubmitButton");
			waitforPageLoadComplete();
			browser.switchTo().defaultContent();
		} catch (Exception e) {
			log.info(e.getMessage());
			mstatus = false;
		}

		return mstatus;
	}

}
