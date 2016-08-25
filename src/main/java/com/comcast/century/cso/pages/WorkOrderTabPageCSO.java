package com.comcast.century.cso.pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.comcast.utils.DataTable;
import com.comcast.century.cm.pages.Page;
import com.comcast.reporting.Status;
import com.comcast.utils.SeleniumReport;

public class WorkOrderTabPageCSO extends Page {

	public WorkOrderTabPageCSO(WebDriver browser, SeleniumReport report) {
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
	
	@FindBy(xpath = "//*[@id='RightFrame']")
	private WebElement frameRight;
	
	@FindBy(xpath = "//*[@id='codition' and contains(@src,'getWorklists.exc')]")
	private WebElement frameCodition;
	
	@FindBy(xpath = "//img[@class[contains(.,'expand-right')]]")
	private WebElement btnExpand ;

	@FindBy(xpath = "//div[text()='Order Search']/../../following-sibling::*/child::*")
	private WebElement btnExpandOrderSearch ;
	
	@FindBy(xpath = "//div[text()='Worklist']/../../following-sibling::*/child::*")
	private WebElement btnExpandWorklist ;
	
	@FindBy(xpath = "//span[@id='serviceorder']/a")
	private WebElement LinkServiceOrder ;
	
	@FindBy(xpath = "//*[@id='Presalesworklist']/a")
	private WebElement LinkPreSalesWorklist ;
	
	@FindBy(xpath = "//*[@id='Postsalesworklist']/a")
	private WebElement LinkPostSalesWorklist ;
	
	//*[@id='serviceRequestID']
	
	@FindBy(xpath = "//input[@id='serviceRequestID']")
	private WebElement txtSrId ;
	
	@FindBy(xpath = "//input[@id='serv_req_id']")
	private WebElement txtPostSalesSrId ;
	
	@FindBy(xpath = "//*[@id='surveyId']")
	private WebElement txtSurveyId ;
	
	//span[text()='Search']
	
	@FindBy(xpath = "//span[text()='Search']/following-sibling::*")
	private WebElement btnSearch ;
	
	@FindBy(xpath = "//a[text()='Advanced Search']")
	private WebElement linkAdvancedSearch ;
	
	@FindBy(xpath = "//*[@id='advance_search_WL']")
	private WebElement linkAdvancedSearchWL ;
	
	@FindBy(xpath = "//iframe[@id='soadvcodition']")
	private WebElement frameSOAdvancedSearch;
	
	@FindBy(xpath = "//iframe[@id='wlAdvSearch']")
	private WebElement frameWLAdvancedSearch;
	
	@FindBy(xpath = "//input[@value='Search']")
	private WebElement btnSearchAdvancedSearch;
	
	@FindBy(xpath = "//div[.='EDI']/../../descendant::a")
	private WebElement linkEDIFlow ;
	
	@FindBy(xpath = "//div[.='EPL']/../../descendant::a")
	private WebElement linkEPLFlow ;
	
	//@FindBy(xpath = "//*[.='Site']/../preceding-sibling::td[1]/child::*/child::*")
	//div[.='Site']/../../descendant::a
	@FindBy(xpath = "//div[.='Site']/../../descendant::a")
	private List<WebElement> linkSiteFlow ;
	
	@FindBy(xpath = "//div[.='Equipment Fee']/../../descendant::a")
	private WebElement linkEquipmentFeeFlow ;
	
	@FindBy(xpath = "//div[.='BGP']/../../descendant::a")
	private WebElement linkBGPFlow ;
	
	@FindBy(xpath = "//div[.='Trunk PRI']/../../descendant::a")
	private WebElement linkTrunkPRIFlow ;
	
	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading ;
	
	@FindBy(xpath = "//*[@value='othersTask']")
	private WebElement radioButtonAllTask ;
	
	@FindBy(xpath = "//*[@value='userGroupTasks']")
	private WebElement radioButtonUserGroupTask ;
	
	@FindBy(xpath = "//*[@value='myTasks']")
	private WebElement radioButtonMyTask ;

	@FindBy(xpath = "//*[@value='---More Actions---']")
	private WebElement ddtxtMoreActions ;
	
	@FindBy(xpath = "//*[@value='---More Actions---']/../following-sibling::td/child::div")
	private WebElement ddArrwMoreActions ;
	
	@FindBy(xpath = "//li[text()='Complete']")
	private WebElement ddValueComplete ;
	
	@FindBy(xpath = "//li[text()='Transfer']")
	private WebElement ddValueTransfer ;
	
	@FindBy(xpath = "//*[@value='Go']")
	private WebElement btnGo ;
	
	@FindBy(xpath = "//*[@id='self']")
	private WebElement radioButtonSelfTransfer ;
	
	@FindBy(xpath = "//*[@id='transferTask']")
	private WebElement ButtonTransfer ;
	
	@FindBy(xpath = "//*[@value='Ok']")
	private WebElement ButtonOk ;
	
	private boolean mstatus=true;

	public boolean SearchForOrderInSO(String SRID){
		try{
			//WaitandSwitchToFrame(fra)
			waitforPageLoadComplete();
		    waitForElement(btnExpand);
			btnExpand.click();
			btnExpandOrderSearch.click();
			LinkServiceOrder.click();
			waitforPageLoadComplete();
			WaitandSwitchToFrame(frameRight);
			waitForElementDisappear(elementLoading);
			if(waitForElement(txtSrId)){
				System.out.println("Search box present");
				txtSrId.sendKeys(SRID);		
			}
			if(waitForElement(linkAdvancedSearch)){
				linkAdvancedSearch.click();
				waitforPageLoadComplete();
				WaitandSwitchToFrame(frameSOAdvancedSearch);
				waitForElement(btnSearchAdvancedSearch);
				btnSearchAdvancedSearch.click();
				browser.switchTo().defaultContent();
				waitForElementDisappear(elementLoading);
				report.updateTestLog("Search for Order", "Order Searched Successfully", Status.SCREENSHOT);
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
 }
	
	public boolean SearchForOrderInPostSales(String SRID){
		try{
			waitforPageLoadComplete();
			waitForElement(btnExpandWorklist);
			btnExpandWorklist.click();
			LinkPostSalesWorklist.click();
			waitforPageLoadComplete();
			WaitandSwitchToFrame(frameRight);
			waitForElementDisappear(elementLoading);
			if(waitForElement(txtPostSalesSrId)){
				txtPostSalesSrId.sendKeys(SRID);
			}
			if(waitForElement(linkAdvancedSearchWL)){
				linkAdvancedSearchWL.click();
				waitforPageLoadComplete();
				WaitandSwitchToFrame(frameWLAdvancedSearch);
				waitForElement(btnSearchAdvancedSearch);
				btnSearchAdvancedSearch.click();
				browser.switchTo().defaultContent();
				waitForElementDisappear(elementLoading);
				report.updateTestLog("Search for Order", "Order Searched Successfully", Status.SCREENSHOT);
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
			
	public boolean ClickFirstSiteFlow(){
		try{
			WaitandSwitchToFrame(frameRight);
			if(waitForElement(linkSiteFlow.get(0))){
				linkSiteFlow.get(0).click();
				waitforPageLoadComplete();
				report.reportDoneEvent("Click on Site level flow", "Site level flow Clicked");
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	
	public boolean ClickSecondSiteFlow(){
		try{
			if(waitForElement(linkSiteFlow.get(1))){
				linkSiteFlow.get(0).sendKeys(Keys.chord(Keys.CONTROL,Keys.END ));
				clickndRelease(linkSiteFlow.get(1));
				waitforPageLoadComplete();
				report.reportDoneEvent("Click on Site level flow", "Site level flow Clicked");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			mstatus = false;
		}
		return mstatus;
			
	}
	
	public boolean ClickEDIFlow() throws InterruptedException{
		try{
			if(waitForElement(linkEDIFlow)){
				//linkEDIFlow.click();
				clickndRelease(linkEDIFlow);
				waitforPageLoadComplete();
				report.reportDoneEvent("Click on Service level flow", "Service level flow Clicked");
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	
	public boolean ClickEPLFlow() throws InterruptedException{
		try{
			if(waitForElement(linkEPLFlow)){
				//linkEDIFlow.click();
				clickndRelease(linkEPLFlow);
				waitforPageLoadComplete();
				report.reportDoneEvent("Click on Service level flow", "Service level flow Clicked");
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean ClickEquipmentFeeFlow() throws InterruptedException{
		try{
			if(waitForElement(linkEquipmentFeeFlow)){
				clickndRelease(linkEquipmentFeeFlow);
				//linkEquipmentFeeFlow.click();
				waitforPageLoadComplete();
				report.reportDoneEvent("Click on Equipment Fee flow", "Equipment Fee flow Clicked");
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	
	public boolean ClickBGPFlow() throws InterruptedException{
		try{
			if(waitForElement(linkBGPFlow)){
				clickndRelease(linkBGPFlow);
				//linkEquipmentFeeFlow.click();
				waitforPageLoadComplete();
				report.reportDoneEvent("Click on BGP flow", "BGP flow Clicked");
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean ClickTrunkPRIFlow() throws InterruptedException{
		try{
			if(waitForElement(linkTrunkPRIFlow)){
				clickndRelease(linkTrunkPRIFlow);
				//linkEquipmentFeeFlow.click();
				waitforPageLoadComplete();
				report.reportDoneEvent("Click on PRI flow", "PRI flow Clicked");
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	
	
	public boolean SearchForSurveyInPreSales(String SurveyID){
		try{
		    waitforPageLoadComplete();
		    waitForElement(btnExpand);
			btnExpand.click();
			waitForElement(LinkPreSalesWorklist);
			LinkPreSalesWorklist.click();
			waitforPageLoadComplete();
			WaitandSwitchToFrame(frameRight);
			waitForElementDisappear(elementLoading);
			if(waitForElement(txtSurveyId)){
				txtSurveyId.sendKeys(SurveyID);
			}
			if(waitForElement(linkAdvancedSearch)){
				linkAdvancedSearch.click();
				waitforPageLoadComplete();
				WaitandSwitchToFrame(frameWLAdvancedSearch);
				waitForElement(btnSearchAdvancedSearch);
				btnSearchAdvancedSearch.click();
				browser.switchTo().defaultContent();
				waitForElementDisappear(elementLoading);
				report.updateTestLog("Search for Order", "Order Searched Successfully", Status.SCREENSHOT);
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
		}
	
	public boolean ClickAllTask(){
		try{
			WaitandSwitchToFrame(frameRight);
			waitForElement(radioButtonAllTask);
			radioButtonAllTask.click();
			waitForElementDisappear(elementLoading);
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
}
