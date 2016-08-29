package com.comcast.century.cso.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.cm.pages.Page;
import com.comcast.reporting.Status;
import com.comcast.utils.SeleniumReport;

public class ServiceLevelTasks extends Page {

	public ServiceLevelTasks(WebDriver browser, SeleniumReport report) {
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
	
	@FindBy(xpath = "//div[@class[contains(.,'refresh')]]")
	private WebElement btnRefresh;
	
	@FindBy(xpath = "//*[text()='Build Update Local Biller Account']")
	private List<WebElement> taskBULBA;
	
	@FindBy(css = "a[onclick*='BuildUpdateLocalBillerAccount'][onClick*='INPROGRESS']")
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
	
	@FindBy(xpath = "//a[text()='Pick up CPE']")
	private WebElement taskPickupCPE;
	
	@FindBy(xpath = "//a[text()='Reclamation of Physical Equipment']")
	private WebElement taskReclamationofPhysicalEq;
	
	@FindBy(xpath = "//a[text()='Submit ASR']")
	private WebElement taskSubmitASR;
	
	
	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading ;
	
	/*@FindBy(xpath = "//*[text()='Start Billing']")
	private WebElement taskEqFeeStartBilling;*/
	
	private boolean mstatus=true;
	
	
	public boolean ClickRefreshButton() throws InterruptedException{
		try{
			if(waitForElement(btnRefresh)){
				clickndRelease(btnRefresh);
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean BULBA() throws InterruptedException{
		try{
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
				//taskShipCPE.click();
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean CAE() throws InterruptedException{
		try{
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
		return mstatus;
	}
	
	public boolean ADI() throws InterruptedException{
		try{
			if(waitForElement(taskADI)){
				if(checkifStatusChanged(taskADI,btnRefresh,"INPROGRESS")){
					waitForElement(taskADI);
					jsClick(taskADI);
					report.reportDoneEvent("Click ADI Task", " ADI Task Clicked");
				//taskADI.click();
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean GenerateCoreConfigs() throws InterruptedException{
		try{
			if(waitForElement(taskGenerateCoreConfigs)){
				if(checkifStatusChanged(taskGenerateCoreConfigs,btnRefresh,"INPROGRESS")){
					waitForElement(taskGenerateCoreConfigs);
					jsClick(taskGenerateCoreConfigs);
					report.reportDoneEvent("Click GenerateCoreConfigs Task", " GenerateCoreConfigs Task Clicked");
				//taskGenerateCoreConfigs.click();
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean GenerateCPEConfigs() throws InterruptedException{
		try{
			if(waitForElement(taskGenerateCPEConfigs)){
				if(checkifStatusChanged(taskGenerateCPEConfigs,btnRefresh,"INPROGRESS")){
					waitForElement(taskGenerateCPEConfigs);
					jsClick(taskGenerateCPEConfigs);
					report.reportDoneEvent("Click GenerateCPEConfigs Task", " GenerateCPEConfigs Task Clicked");
				//taskGenerateCPEConfigs.click();
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
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
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean InstallCPE() throws InterruptedException{
		try{
			while(!isElementPresent(taskInstallCPE)){
				Thread.sleep(1000);
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
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean InstallCPECoax() throws InterruptedException{
		try{
			while(!isElementPresent(taskInstallCPECoax)){
				Thread.sleep(1000);
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
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean StartBilling() throws InterruptedException{
		try{
			if(waitForElement(taskStartBilling)){
				if(checkifStatusChanged(taskStartBilling,btnRefresh,"COMPLETED")){
					waitForElement(taskStartBilling);
					jsClick(taskStartBilling);
					report.reportDoneEvent("Complete StartBilling Task", " StartBilling Task auto completed");
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
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
		try{
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
		return mstatus;
	}
	
	public boolean ContactCustomer() throws InterruptedException{
		try{
			if(waitForElement(taskContactCustomer)){
				if(checkifStatusChanged(taskContactCustomer,btnRefresh,"INPROGRESS")){
				taskContactCustomer.click();
				report.reportDoneEvent("Click ContactCustomer Task", " ContactCustomer Task Clicked");
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean updateLocalBiller() throws InterruptedException{
		try{
			if(waitForElement(taskUpdateLocalBiller)){
				if(checkifStatusChanged(taskUpdateLocalBiller,btnRefresh,"INPROGRESS")){
					taskUpdateLocalBiller.click();
				report.reportDoneEvent("Click UpdateLocalBiller Task", "UpdateLocalBiller Task Clicked");
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean removeCoreConfigs() throws InterruptedException{
		try{
			if(waitForElement(taskRemoveCoreConfigs)){
				if(checkifStatusChanged(taskRemoveCoreConfigs,btnRefresh,"INPROGRESS")){
					taskRemoveCoreConfigs.click();
				report.reportDoneEvent("Click RemoveCoreConfigs Task", "RemoveCoreConfigs Task Clicked");
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean scheduleCPEPickup() throws InterruptedException{
		try{
			if(waitForElement(taskScheduleCPEPickup)){
				if(checkifStatusChanged(taskScheduleCPEPickup,btnRefresh,"INPROGRESS")){
					taskScheduleCPEPickup.click();
				report.reportDoneEvent("Click ScheduleCPEPickup Task", "ScheduleCPEPickup Task Clicked");
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean pickupCPE() throws InterruptedException{
		try{
			if(waitForElement(taskPickupCPE)){
				if(checkifStatusChanged(taskPickupCPE,btnRefresh,"INPROGRESS")){
					taskPickupCPE.click();
				report.reportDoneEvent("Click PickupCPE Task", "PickupCPE Task Clicked");
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
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
			mstatus = false;
		}
		return mstatus;
	}

}
