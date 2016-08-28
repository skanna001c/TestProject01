package com.comcast.century.cso.pages;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.comcast.century.cm.pages.Page;
import com.comcast.century.data.OrderSummaryInfo;
import com.comcast.reporting.Status;
import com.comcast.utils.SeleniumReport;

public class CreateOrderBillingPackageTaskPage extends Page {

	public CreateOrderBillingPackageTaskPage(WebDriver browser, SeleniumReport report) {
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
	
	@FindBy(xpath = "//img[@title='Back']")
	private WebElement btnBack;
	
	@FindBy(xpath = "//input[@value='Complete']")
	private WebElement btnComplete;
	
	@FindBy(xpath = "//input[@value='Save']")
	private WebElement btnSave;
	
	@FindBy(xpath = "//input[@value='Reset']")
	private WebElement btnReset;
	
	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading ;
	
	@FindBy(xpath = "//*[@id='custAcctReceivedate']/following-sibling::img")
	private WebElement customerAcceptanceReceivedDate ;
	
	@FindBy(xpath = "//button[text()='Today']")
	private WebElement btnToday ;
	
	@FindBy(xpath = "//img[@id='.//*[@id='attachImg']")
	private WebElement linkAttachments ;
	
	@FindBy(css = "img#noteImg")
	private WebElement imgNotes;
	
	@FindBy(css = "iframe#notesframe")
	private WebElement frameNotes;

	@FindBy(xpath = "//select[@id='attachMode']")
	private WebElement ddAttachmentRepository ;
	
	@FindBy(xpath = "//span[text()='Browse...']")
	private WebElement btnBrowse ;
	
	@FindBy(xpath = "//select[@id='attachmentType0']")
	private WebElement ddAttachmentType ;
	
	@FindBy(xpath = "//*[@id='btncreate']")
	private WebElement btnAdd ;
	
	@FindBy(xpath = ".//div[contains(@class,'close')]")
	private WebElement closeAttachmentWindow ;
	
	@FindBy(xpath = "//span[.='NOTES']/preceding-sibling::div[3]")
	private WebElement closeNotes ;
	
	private boolean mstatus = true;
	
	public boolean CreateOrderBillingPackage() throws InterruptedException{
		try{
			if(waitForElement(customerAcceptanceReceivedDate)){
				clickndRelease(customerAcceptanceReceivedDate);
				clickndRelease(btnToday);
				OrderSummaryInfo orderSummaryInfo;
				orderSummaryInfo = OrderSummaryInfo.loadFromDatatable(dataTable);
				Attachments(orderSummaryInfo);
				this.ClickCompleteButton();
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	
	public boolean verifyNotes(){
		try{
			waitForElement(imgNotes);
			imgNotes.click();
			waitforPageLoadComplete();
			sleep(10000);
			report.updateTestLog("Verify Notes", "Notes generate for SV Integration", Status.SCREENSHOT);
			closeNotes.click();
			this.ClickBackButton();
		}catch(Exception e){
			System.out.println(e.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean ClickBackButton(){
		try{
			if(waitForElement(btnBack)){
				btnBack.click();
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean ClickSaveButton(){
		try{
			if(waitForElement(btnSave)){
				btnSave.click();
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean ClickCompleteButton(){
		try{
			if(waitForElement(btnComplete)){
				btnComplete.click();
				waitforPageLoadComplete();
				waitForElementDisappear(elementLoading);
				report.reportDoneEvent("Complete StartBilling Task", " StartBilling Task Completed");
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
		
	}
	public boolean Attachments(OrderSummaryInfo orderSummaryInfo) throws AWTException, InterruptedException{
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
			uploadAttachments(orderSummaryInfo.filePath);
			waitForElement(ddAttachmentType);
			new Select(ddAttachmentType).selectByVisibleText("Customer Acceptance Doc");
			waitForElement(btnAdd);
			clickndRelease(btnAdd);
			Thread.sleep(15000);
			browser.switchTo().defaultContent();	
			//WaitandSwitchToFrame(frameMain);
			waitForElement(closeAttachmentWindow);
			clickndRelease(closeAttachmentWindow);
		} catch (Exception e) {
			mstatus = false;
		}
		return mstatus;
	}
	

}
