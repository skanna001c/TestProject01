package com.comcast.century.cso.pages;

import java.awt.AWTException;
import java.io.FileOutputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.comcast.reporting.Status;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;

public class CreateOrderBillingPackageTaskPage extends Page {

	public CreateOrderBillingPackageTaskPage(FrameworkContext context) {
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
	
	@FindBy(xpath = ".//*[@id='attachImg']")
	private WebElement linkAttachments ;
	
	@FindBy(css = "img#noteImg")
	private WebElement imgNotes;
	
	@FindBy(css = "iframe#notesframe")
	private WebElement frameNotes;

	@FindBy(css = "iframe#rightFrame")
	private WebElement rightFrame;

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
				Attachments();
				this.ClickCompleteButton();
				waitForElement(browser.findElement(By.xpath("//*[text()='Create Order Billing Package' and contains(@onclick, 'COMPLETED')]")));
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
				iClick(btnComplete, btnBack, "Complete StartBilling Task: Complete StartBilling Task page: CompleteButton");
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
	public boolean Attachments() throws AWTException, InterruptedException{
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
			new Select(ddAttachmentType).selectByVisibleText("Customer Acceptance Doc");
			waitForElement(btnAdd);
			clickndRelease(btnAdd);
			Thread.sleep(15000);
			browser.switchTo().defaultContent();	
			WaitandSwitchToFrame(rightFrame);
			waitForElement(closeAttachmentWindow);
			clickndRelease(closeAttachmentWindow);
		} catch (Exception e) {
			mstatus = false;
		}
		return mstatus;
	}
	

}
