package com.comcast.century.cso.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.reporting.Status;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;




public class ServiceLevelTasks extends Page {

	public ServiceLevelTasks(FrameworkContext context) {
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
	
	Logger log = Logger.getLogger(ServiceLevelTasks.class);
	
	@FindBy(xpath = "//img[@title='Back']")
	private WebElement btnBack;
	
	@FindBy(xpath = "//div[@class[contains(.,'refresh')]]")
	private WebElement btnRefresh;
	
	@FindBy(xpath = "//*[text()='Build Update Local Biller Account']")
	private List<WebElement> taskBULBA;
	
	@FindBy(xpath = "//*[text()='Build Update Local Biller Account' and contains(@onclick, 'PROGRESS')]")
	private WebElement taskBULBA2;
	
	@FindBy(xpath = "//*[text()='Ship CPE']")
	private WebElement taskShipCPE;
	
	@FindBy(xpath = "//*[text()='Create Account and Equipment']")
	private WebElement taskCAE;
	
	@FindBy(xpath = "//*[text()='Assign Design Information']")
	private WebElement taskADI;
	
	@FindBy(xpath = "//*[text()='Generate Core Configs']")
	private WebElement taskGenerateCoreConfigs;
	
	@FindBy(xpath = "//*[text()='Generate CPE Configs']")
	private WebElement taskGenerateCPEConfigs;
	
	@FindBy(xpath = "//*[text()='Load Core Configs']")
	private WebElement taskLoadCoreConfigs;
	
	@FindBy(xpath = "//*[text()='Install CPE']")
	private WebElement taskInstallCPE;
	
	@FindBy(xpath = "//*[text()='Install CPE (Coax)']")
	private WebElement taskInstallCPECoax;

	@FindBy(css = "a[onclick*='InstallCPE'][onclick*='INPROGRESS']")
	private WebElement taskInstallCPE2;
	
	@FindBy(xpath = "a[onclick*='InstallCPE (Coax)'][onclick*='INPROGRESS']")
	private WebElement taskInstallCPECoax2;
	
	@FindBy(xpath = "//*[text()='Set Critical Dates']")
	private WebElement taskSetCriticalDates;
	
	@FindBy(xpath = "//*[text()='Day of Configs']")
	private WebElement taskDayofConfigs;
	
	@FindBy(xpath = "//*[text()='Activate Service']")
	private WebElement taskActivateService;
	
	@FindBy(xpath = "//*[text()='Notify Customer of Service Installation']")
	private WebElement taskNotifyCustomerofServiceInstallation;
	
	@FindBy(xpath = "//*[text()='Complete Customer Acceptance Testing']")
	private WebElement taskCCAT;
	
	@FindBy(xpath = "//*[text()='Create Order Billing Package']")
	private WebElement taskCreateOrderBillingPackage;
	
	@FindBy(xpath = "//*[text()='Start Billing']")
	private WebElement taskStartBilling;	
	
	@FindBy(xpath = "//a[text()='Update Design']")
	private WebElement taskUpdateDesign;
	
	@FindBy(xpath = "//a[text()='Contact Customer']")
	private WebElement taskContactCustomer;
	
	@FindBy(xpath = "//a[text()='Update Local Biller']")
	private WebElement taskUpdateLocalBiller;
	
	@FindBy(xpath = "//a[text()='Remove Core Configs']")
	private WebElement taskRemoveCoreConfigs;

	@FindBy(xpath = "//a[text()='Schedule CPE Pickup']")
	private WebElement taskScheduleCPEPickup;
	
	@FindBy(xpath = "//a[text()='Schedule CPE Pickup' and contains(@onclick, 'PROGRESS')]")
	private List<WebElement> tasksScheduleCPEPickup;
	
	@FindBy(xpath = "//a[text()='Pick up CPE']")
	private WebElement taskPickupCPE;
	
	@FindBy(xpath = "//a[text()='Reclamation of Physical Equipment']")
	private WebElement taskReclamationofPhysicalEq;
	
	@FindBy(xpath = "//a[text()='Submit ASR']")
	private WebElement taskSubmitASR;
	
	@FindBy(xpath = "//a[text()='Assign Design- BGP']")
	private WebElement taskAssignDesignBGP;	
	
	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading ;	
	
	@FindBy(xpath = "//input[@value='Complete']")
	private WebElement btnComplete;
	
	
	
	
	//##########Disconnect#########################
	@FindBy(xpath = "//a[text()='Stop Billing']")
	private WebElement taskStopBilling;
	
	@FindBy(xpath = "//a[text()='Issue Soft Disconnect']")
	private WebElement taskIssue_Soft_Disconnect;
	
	@FindBy(xpath = "//a[text()='Notify Customer of Service Disconnection']")
	private WebElement taskNotify_Customer_of_Service_Disconnection;
	
	@FindBy(xpath = "//a[text()='Load CPE Configs']")
	private WebElement taskLoadCPEConfigs;
	
	@FindBy(xpath = "//a[text()='Un-Assign Design Information']")
	private WebElement taskUnAssignDesignInformation;
	
	
	@FindBy(xpath = "//a[text()='Confirm Order Complete']")
	private WebElement taskConfirmOrderComplete;
	
	//##########################	
	
	
	private boolean mstatus=true;
	
	
	public boolean ClickRefreshButton() throws InterruptedException{
		try{
			if(waitForElement(btnRefresh)){
				clickndRelease(btnRefresh);
			}
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean BULBA(int j) throws InterruptedException{
		try{
			if(j==1)
			{
				if(waitForElement(taskBULBA2)){
					taskBULBA2.click();
					waitforPageLoadComplete();
				}
			}
			if(waitForElement(taskBULBA.get(0))){
				if(checkifStatusChanged(taskBULBA.get(0),btnRefresh,"INPROGRESS")){
				taskBULBA.get(0).click();
				report.reportDoneEvent("Click BULBA Task", " BULBA Task Clicked");
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
			
		}
		return mstatus;
	}
	
	public boolean BULBA2() throws InterruptedException{
		try{
			if(waitForElement(taskBULBA2)){
				taskBULBA2.click();
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	
	public boolean ShipCPE() throws InterruptedException{
		try{
			if(waitForElement(taskShipCPE)){
				if(checkifStatusChanged(taskShipCPE,btnRefresh,"INPROGRESS")){
					waitForElement(taskShipCPE);
					jsClick(taskShipCPE);
					report.reportDoneEvent("Click ShipCPE Task", " ShipCPE Task Clicked");				
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean CAE() throws InterruptedException{
		return clicktask(taskCAE, "Create Equipment Fee");
		/*try{
			if(waitForElement(taskCAE)){
				if(checkifStatusChanged(taskCAE,btnRefresh,"INPROGRESS")){
					waitForElement(taskCAE);
					jsClick(taskCAE);
					report.reportDoneEvent("Click CAE Task", " CAE Task Clicked");
				//taskCAE.click();
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;*/
	}
	
	public boolean ADI() throws InterruptedException{
		return clicktask(taskADI,"Assign Assign Design Information");
		
	}
	
	public boolean GenerateCoreConfigs() throws InterruptedException{
		return clicktask(taskGenerateCoreConfigs, "GenerateCoreConfigs");
	}
	
	public boolean GenerateCPEConfigs() throws InterruptedException{
		try{
			if(waitForElement(taskGenerateCPEConfigs)){
				if(checkifStatusChanged(taskGenerateCPEConfigs,btnRefresh,"INPROGRESS")){
					waitForElement(taskGenerateCPEConfigs);
					jsClick(taskGenerateCPEConfigs);
					report.reportDoneEvent("Click GenerateCPEConfigs Task", " GenerateCPEConfigs Task Clicked");				
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean LoadCoreConfigs() throws InterruptedException{
		try{
			if(waitForElement(taskLoadCoreConfigs)){
				if(checkifStatusChanged(taskLoadCoreConfigs,btnRefresh,"INPROGRESS")){
					waitForElement(taskLoadCoreConfigs);
					jsClick(taskLoadCoreConfigs);
					report.reportDoneEvent("Click LoadCoreConfigs Task", " LoadCoreConfigs Task Clicked");
				//taskLoadCoreConfigs.click();
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean InstallCPE(int j) throws InterruptedException{
		try{
			while(!isElementPresent(taskInstallCPE)){
				Thread.sleep(1000);
			}
			if(j==1)
			{
				if (waitForElement(taskInstallCPE2)) {
					waitForElement(taskInstallCPE2);
					jsClick(taskInstallCPE2);
					report.reportDoneEvent("Click InstallCPE Task", " InstallCPE Task Clicked");
				}
				waitforPageLoadComplete();
			}	
			
			if(waitForElement(taskInstallCPE)){
				if(checkifStatusChanged(taskInstallCPE,btnRefresh,"INPROGRESS")){
					waitForElement(taskInstallCPE);
					jsClick(taskInstallCPE);
					report.reportDoneEvent("Click InstallCPE Task", " InstallCPE Task Clicked");
				//taskInstallCPE.click();
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean InstallCPECoax(int j) throws InterruptedException{
		try{
			while(!isElementPresent(taskInstallCPECoax)){
				Thread.sleep(1000);
			}
			
			if(j==1)
			{
				if (waitForElement(taskInstallCPECoax2)) {
					waitForElement(taskInstallCPECoax2);
					jsClick(taskInstallCPECoax2);
					report.reportDoneEvent("Click InstallCPE (Coax)  Task", " InstallCPE Coax Task Clicked");
				}
				waitforPageLoadComplete();
			}
			if(waitForElement(taskInstallCPECoax)){
				if(checkifStatusChanged(taskInstallCPECoax,btnRefresh,"INPROGRESS")){
					waitForElement(taskInstallCPECoax);
					jsClick(taskInstallCPECoax);
					report.reportDoneEvent("Click InstallCPECoax Task", " InstallCPECoax Task Clicked");
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}

	public boolean InstallCPE2() throws InterruptedException{
		try{
			if(waitForElement(taskInstallCPE2)){
				taskInstallCPE2.click();
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	
	public boolean SetCriticalDates() throws InterruptedException{
		try{
			if(waitForElement(taskSetCriticalDates)){
				if(checkifStatusChanged(taskSetCriticalDates,btnRefresh,"INPROGRESS")){
					waitForElement(taskSetCriticalDates);
					jsClick(taskSetCriticalDates);
					report.reportDoneEvent("Click SetCriticalDates Task", " SetCriticalDates Task Clicked");
				//taskSetCriticalDates.click();
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean DayofConfigs() throws InterruptedException{
		try{
			if(waitForElement(taskDayofConfigs)){
				if(checkifStatusChanged(taskDayofConfigs,btnRefresh,"INPROGRESS")){
					waitForElement(taskDayofConfigs);
					jsClick(taskDayofConfigs);
					report.reportDoneEvent("Click DayofConfigs Task", " DayofConfigs Task Clicked");
				//taskDayofConfigs.click();
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean ActivateService() throws InterruptedException{
		try{
			if(waitForElement(taskActivateService)){
				if(checkifStatusChanged(taskActivateService,btnRefresh,"INPROGRESS")){
					waitForElement(taskActivateService);
					jsClick(taskActivateService);
					report.reportDoneEvent("Click ActivateService Task", " ActivateService Task Clicked");
				//taskActivateService.click();
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean NotifyCustomerofServiceInstallation() throws InterruptedException{
		try{
			if(waitForElement(taskNotifyCustomerofServiceInstallation)){
				if(checkifStatusChanged(taskNotifyCustomerofServiceInstallation,btnRefresh,"INPROGRESS")){
					waitForElement(taskNotifyCustomerofServiceInstallation);
					jsClick(taskNotifyCustomerofServiceInstallation);
					report.reportDoneEvent("Click NotifyCustomerofServiceInstallation Task", " NotifyCustomerofServiceInstallation Task Clicked");
				//taskNotifyCustomerofServiceInstallation.click();
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean CCAT() throws InterruptedException{
		try{
			if(waitForElement(taskCCAT)){
				if(checkifStatusChanged(taskCCAT,btnRefresh,"INPROGRESS")){
					waitForElement(taskCCAT);
					jsClick(taskCCAT);
					report.reportDoneEvent("Click CCAT Task", " CCAT Task Clicked");
				//taskCCAT.click();
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean CreateOrderBillingPackage() throws InterruptedException{
		try{
			if(waitForElement(taskCreateOrderBillingPackage)){
				if(checkifStatusChanged(taskCreateOrderBillingPackage,btnRefresh,"INPROGRESS")){
					waitForElement(taskCreateOrderBillingPackage);
					jsClick(taskCreateOrderBillingPackage);
					report.reportDoneEvent("Click CreateOrderBillingPackage Task", " CreateOrderBillingPackage Task Clicked");
				//taskCreateOrderBillingPackage.click();
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean StartBilling() throws InterruptedException{
		try{
			if(waitForElement(taskStartBilling)){
				//if(checkifStatusChanged(taskStartBilling,btnRefresh,"INPROGRESS") || checkifStatusChanged(taskStartBilling,btnRefresh,"COMPLETED")){
					waitForElement(taskStartBilling);
					jsClick(taskStartBilling);
					report.reportDoneEvent("Complete StartBilling Task", " StartBilling Task auto completed");
				//}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	
	
	/*public boolean EqFeeStartBilling() throws InterruptedException{
	 * try{
			if(waitForElement(taskEqFeeStartBilling)){
				if(checkifStatusChanged(taskEqFeeStartBilling,btnRefresh,"INPROGRESS")){
					waitForElement(taskEqFeeStartBilling);
					Thread.sleep(10*1000);
					jsClick(taskEqFeeStartBilling);
					report.reportDoneEvent("Click EqFeeStartBilling Task", " EqFeeStartBilling Task Clicked");
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}*/
	
	public boolean UpdateDesign() throws InterruptedException{
		return clicktask(taskUpdateDesign, "Update Design");
		/*try{
			if(waitForElement(taskUpdateDesign)){
				if(checkifStatusChanged(taskUpdateDesign,btnRefresh,"INPROGRESS")){
				taskUpdateDesign.click();
				report.reportDoneEvent("Click UpdateDesign Task", " UpdateDesign Task Clicked");
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;*/
	}
	
	public boolean ContactCustomer() throws InterruptedException{
		mstatus=true;
		try{
			if(waitForElement(taskContactCustomer)){
				if(checkifStatusChanged(taskContactCustomer,btnRefresh,"INPROGRESS")){
				taskContactCustomer.click();
				report.reportDoneEvent("Click ContactCustomer Task", " ContactCustomer Task Clicked");
				}
				else mstatus=false;
				waitforPageLoadComplete();
			}
			else mstatus=false;
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean updateLocalBiller() throws InterruptedException{
		mstatus=true;
		try{
			if(waitForElement(taskUpdateLocalBiller)){
				if(checkifStatusChanged(taskUpdateLocalBiller,btnRefresh,"INPROGRESS")){
					taskUpdateLocalBiller.click();
					report.reportDoneEvent("Click UpdateLocalBiller Task", "UpdateLocalBiller Task Clicked");
				}
				else {mstatus=false;}
				waitforPageLoadComplete();
			}
			else mstatus=false;
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean removeCoreConfigs() throws InterruptedException{
		return clicktask(taskRemoveCoreConfigs, "Remove Core Configs");
			
	}
	
	public boolean scheduleCPEPickup(By by) throws InterruptedException{
		return clicktask(browser.findElements(by).get(0), "Schedule CPE Pickup");
		
	}
	
	public boolean pickupCPE(By by) throws InterruptedException{
		return clicktask(browser.findElements(by).get(0), "Pick up CPE");
		
	}
	
	
	public boolean Reclamation_of_Physical_Equipment(By by) throws InterruptedException{
		return clicktask(browser.findElements(by).get(0), "Pick up CPE");
		
	}
	
	public boolean pickupCPE() throws InterruptedException{
		try{
			if(waitForElement(taskPickupCPE)){
				if(checkifStatusChanged(taskPickupCPE,btnRefresh,"INPROGRESS")){
					taskPickupCPE.click();
				report.reportDoneEvent("Click PickupCPE Task", "PickupCPE Task Clicked");
				}
				else mstatus=false;
				waitforPageLoadComplete();
			}
			else mstatus=false;
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean reclamationofPhysicalEq() throws InterruptedException{
		try{
			if(waitForElement(taskReclamationofPhysicalEq)){
				if(checkifStatusChanged(taskReclamationofPhysicalEq,btnRefresh,"INPROGRESS")){
					taskReclamationofPhysicalEq.click();
				report.reportDoneEvent("Click ReclamationofPhysicalEq Task", "ReclamationofPhysicalEq Task Clicked");
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean assignDesignBGP() throws InterruptedException{
		try{
			if(waitForElement(taskAssignDesignBGP)){
				if(checkifStatusChanged(taskAssignDesignBGP,btnRefresh,"INPROGRESS")){
					waitForElement(taskAssignDesignBGP);
					jsClick(taskAssignDesignBGP);
					report.reportDoneEvent("Click AssignDesignBGP Task", "AssignDesignBGP Task Clicked");
					waitforPageLoadComplete();
				}
				
			}
		}
		catch(Exception ex)
		{	log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	
	
	public boolean ClickBackButton() throws InterruptedException{
		try{
			if(waitForElement(btnBack)){
				clickndRelease(btnBack);
				//btnBack.click();
				waitforPageLoadComplete();
				waitForElementDisappear(elementLoading);
				report.updateTestLog("Validate", "Service Level Tasks Completed", Status.SCREENSHOT);
			}
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
//##################################Disconnect#################################################
	public boolean StopBilling() throws InterruptedException{
		return clicktask(taskStopBilling, "Stop Billing");
		/*mstatus=true;
		try{
			if(waitForElement(taskStopBilling)){
				//if(checkifStatusChanged(taskStartBilling,btnRefresh,"INPROGRESS") || checkifStatusChanged(taskStartBilling,btnRefresh,"COMPLETED")){
				if(checkifStatusChanged(taskStopBilling,btnRefresh,"INPROGRESS")){
					jsClick(taskStopBilling);
					report.reportDoneEvent("Inside Stop Billing Task", "");
					waitforPageLoadComplete();
				}
				else mstatus=false;
				
			}else  mstatus=false;
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;*/
	}
	
	
	public boolean Issue_Soft_Disconnect() {
		
		return clicktask(taskIssue_Soft_Disconnect,"Issue_Soft_Disconnect");
		/*mstatus=true;
		 * try{
			if(waitForElement(taskIssue_Soft_Disconnect)){
				//if(checkifStatusChanged(taskStartBilling,btnRefresh,"INPROGRESS") || checkifStatusChanged(taskStartBilling,btnRefresh,"COMPLETED")){
				if(checkifStatusChanged(taskIssue_Soft_Disconnect,btnRefresh,"INPROGRESS")){
					jsClick(taskIssue_Soft_Disconnect);
					report.reportDoneEvent("Inside Stop Billing Task", "");
				}
				else mstatus=false;
				waitforPageLoadComplete();
			}else  mstatus=false;
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;*/
	}
	
	public boolean Notify_Customer_of_Service_Disconnection() {
		return clicktask(taskNotify_Customer_of_Service_Disconnection,"Notify_Customer_of_Service_Disconnection");
		/*mstatus=true;
		try{
			if(waitForElement(taskNotify_Customer_of_Service_Disconnection)){
				//if(checkifStatusChanged(taskStartBilling,btnRefresh,"INPROGRESS") || checkifStatusChanged(taskStartBilling,btnRefresh,"COMPLETED")){
				if(checkifStatusChanged(taskNotify_Customer_of_Service_Disconnection,btnRefresh,"INPROGRESS")){
					jsClick(taskNotify_Customer_of_Service_Disconnection);
					report.reportDoneEvent("Inside Stop Billing Task", "");
				}
				else mstatus=false;
				waitforPageLoadComplete();
			}else  mstatus=false;
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;*/
	}

	public boolean LoadCPEConfigs() {
		
		return clicktask(taskLoadCPEConfigs,"LoadCPEConfigs");
	}
	
	public boolean clicktask(WebElement task,String taskName) {
		mstatus=true;
		
		try{
			if(waitForElement(task)){
				//if(checkifStatusChanged(taskStartBilling,btnRefresh,"INPROGRESS") || checkifStatusChanged(taskStartBilling,btnRefresh,"COMPLETED")){
				if(this.checkifStatusChanged(task,btnRefresh,"INPROGRESS")){
					jsClick(task);
					report.reportDoneEvent("Inside "+taskName, "");
					waitforPageLoadComplete();
				}
				else{ mstatus=false;}
				
			}else  mstatus=false;
		}
		catch(Exception ex)
		{  
			log.info(ex.getMessage());
			mstatus = false;			
		}
		return mstatus;
	}

	public boolean Update_Local_Biller() {
		// TODO Auto-generated method stub
		return clicktask(taskUpdateLocalBiller,"Update Local Biller");
	}


	public boolean Un_Assign_Design_Information() {
		// TODO Auto-generated method stub
		return clicktask(taskUnAssignDesignInformation,"Un-Assign Design Information");
	}

	public boolean Confirm_Order_Complete() {
		// TODO Auto-generated method stub
		return clicktask(taskConfirmOrderComplete,"Confirm Order Complete");
	}
	
	public boolean ClickCompleteButton(String taskName){
		try{
			if(waitForElement(btnComplete)){
				iClick(btnComplete, btnBack, "Complete "+taskName+" Task: Complete "+taskName+" Task Page: CompleteButton");
				waitforPageLoadComplete();
				waitForElementDisappear(elementLoading);
				report.reportDoneEvent("Complete "+taskName+" Task", taskName+" Task Completed");
			}
		}
		catch(Exception ex)
		{	
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
		
	}

	
	
	protected boolean checkifStatusChanged(WebElement we1, WebElement we2, String status) throws InterruptedException {
		boolean fn_status = false;
		log.info("Task :" + we1.getText());
		int counter = 0;
		int reqminutes = 7;

		if ((!(we1.getAttribute("onclick").contains("COMPLETED")))
				&& (!(we1.getAttribute("onclick").contains("DEFERRED")))
				&& (!(we1.getAttribute("onclick").contains("CANCELLED")))) {
			
			String xpath="//*[text()='" + we1.getText() +"' and contains(@onclick, '"+status+ "')]";
			By by=By.xpath(xpath);
			String onclick = null;
			try{
				onclick=this.browser.findElement(by).getAttribute("onclick"); //checking for exception
			}
			catch(NoSuchElementException ex )
			{
				onclick=null;
			}
			
			if(onclick==null)
			{	xpath="//"+we1.getTagName()+"[text()='" + we1.getText() +"' and contains(@onclick, '"+status+ "')]";			
				by=By.xpath(xpath);		
			}
			
			while ((!(browser.findElement(by).getAttribute("onclick").contains(status))) && (counter < reqminutes * 6)) {
				Thread.sleep(10000L);
				we2.click();
				log.info("Click referesh button to come to inprogress. Counter value is: " + counter);
				++counter;
			}

			if (we1.getAttribute("onclick").contains(status)) {
				fn_status = true;
			} else {
				this.report.reportFailEvent(we1.getText() + " clicked failed",
						we1.getText() + "task not coming to in progress after 7 mins");
			}

		}

		return fn_status;
	}
	
	

}
