package com.comcast.century.cm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.data.CustomerInfo;
import com.comcast.reporting.Status;
import com.comcast.utils.PerfTransaction;
import com.comcast.utils.SeleniumReport;
import com.comcast.utils.TestSettings;

public class CustomerTabPageCM extends Page {

	private TestSettings testSettings;

	protected static String HOME_PAGE_TITLE_QA = "Einstein 360°";

	protected static String HOME_PAGE_TITLE_STG = "Comcast Business | My Online Account";

	protected static String HOME_PAGE_TITLE_SCH = "Search";

	public CustomerTabPageCM(WebDriver browser, SeleniumReport report) {
		super(browser, report);
	}
	@FindBy(css = "span#Customer")
	private WebElement tabCustomer;
	
	@FindBy(xpath = "//*[@id='mainFrame']")
	private WebElement frameMain;

	@FindBy(xpath = "//*[@id='CustomerFrame']")
	private WebElement frameCustomer;
	
	@FindBy(xpath = "//input[@id='busiCustBean.businessName']")
	private WebElement txtCustomerName;
	
	@FindBy(xpath = "//*[@id='busiCustBean.busiWorkPhone1']")
	private WebElement txtworkPhone;
	
	@FindBy(xpath = "//*[@id='sfAccountID']")
	private WebElement txtSalesForceID;	

	@FindBy(xpath = "//*[@id='addrBean.addrGDBean.addrsLine1']")
	private WebElement txtAddressLine1;
	
	@FindBy(xpath = "//*[@id='addrBean.addrGDBean.addrsLine2']")
	private WebElement txtAddressLine2;

	@FindBy(xpath = "//div[@id='ext-gen1031']")
	private WebElement ddArrwState;
	
	@FindBy(xpath = "//input[@id='CmbGDstate-inputEl']")
	private WebElement ddtxtState;
	
	@FindBy(xpath = "//li[text()='Alaska']")
	private WebElement ddValueState;

	@FindBy(xpath = "//div[@id='ext-gen1036']")
	private WebElement ddArrwCity;
	
	@FindBy(xpath = "//input[@id='CmbGDcity-inputEl']")
	private WebElement ddtxtCity;
	
	@FindBy(xpath = "//li[text()='Akiak']")
	private WebElement ddValueCity;

	@FindBy(xpath = "//div[@id='ext-gen1041']")
	private WebElement ddArrwZipCode;
	
	@FindBy(xpath = "//input[@id='CmbGDzipcode-inputEl']")
    private WebElement ddtxtZipCode;
		
	@FindBy(xpath = "//li[text()='99552']")
	private WebElement ddValueZipCode;
	
	@FindBy(xpath = "//img[@id='zipcode_imgSearch']")
	private WebElement imgZipcodeSearch;
	
	@FindBy(xpath = "//span[text()='City']")
	private WebElement txtCity;
	
	@FindBy(xpath = "//*[@id='closeLookup']")
	private WebElement btnOk;

	@FindBy(xpath = "//input[@id='CmbGDdivision-inputEl']")
    private WebElement ddtxtDivision;
		
	@FindBy(xpath = "//li[text()='West']")
	private WebElement ddValueDivision;
	
	@FindBy(xpath = "//input[@id='CmbGDmarket-inputEl']")
    private WebElement ddtxtMarket;
		
	@FindBy(xpath = "//li[text()='Seattle']")
	private WebElement ddValueMarket;
	
	@FindBy(xpath = "//input[@id='CmbGDregion-inputEl']")
    private WebElement ddtxtRegion;
		
	@FindBy(xpath = "//li[text()='Seattle'][2]")
	private WebElement ddValueRegion;
	
	@FindBy(xpath = "//input[@value='Validate']")
	private WebElement btnValidate;
	
	@FindBy(xpath = "//input[@value='Create']")
	private WebElement btnCreate;
	
	@FindBy(xpath = "//*[@id='0']")
	private WebElement btnSelectValidSite;
	
	@FindBy(xpath = "//input[@value='More']")
	private WebElement btnMore;

	@FindBy(xpath = "//input[@id='diclaimercheck']")
	private WebElement chkDisclaimer;

	@FindBy(xpath = "//*[@id='codition']")
	private WebElement frameCondition;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement btnContinue;
	
	
	
	
	@Override
	protected boolean isValidPage() {
		if (browser.getTitle().trim().equalsIgnoreCase(HOME_PAGE_TITLE_QA)
				|| (browser.getTitle().trim().equalsIgnoreCase(HOME_PAGE_TITLE_STG))
				|| (browser.getTitle().trim().equalsIgnoreCase(HOME_PAGE_TITLE_SCH))) {
			return true;
		}
		return false;
	}

	@Override
	protected void waitForPageLoad() {
		
		
	}
	
	  public void customerInformation(CustomerInfo customerInfo){
		  
		  waitforPageLoadComplete();
		  waitForElement(tabCustomer);
		  tabCustomer.click();
		  report.reportDoneEvent("Click on Customer Tab", "Customer Tab is present");
		  waitforPageLoadComplete();
		  if(WaitandSwitchToFrame(frameMain)){
		  WaitandSwitchToFrame(frameCustomer);
		  }
		  waitForElement(txtCustomerName);
		  txtCustomerName.click();
		  txtCustomerName.clear();
		  txtCustomerName.sendKeys(customerInfo.customerName+RandomNumber());
		  waitforPageLoadComplete();
		  report.reportDoneEvent("Enter Customer Name", "Entered Customer Name as ->" +customerInfo.customerName+RandomNumber());
		  if(waitForElement(txtworkPhone)){
			  txtworkPhone.click();
		  }
		  txtworkPhone.sendKeys(customerInfo.workPhone);
		  report.reportDoneEvent("Enter Work Phone", "Work Phone Entered as ->" +customerInfo.workPhone);
		  txtSalesForceID.sendKeys(customerInfo.salesForceAccId+RandomNumber());
		  report.reportDoneEvent("Enter Salesforce Account ID", "Salesforce Account ID Entered as ->" +customerInfo.salesForceAccId+RandomNumber());  
		  
	  }
	  
	  public void addressInformationInvalid(CustomerInfo customerInfo) throws InterruptedException{
		  
		  waitForElement(txtAddressLine1);
		  txtAddressLine1.sendKeys(customerInfo.addressLine1+RandomNumber());
		  report.reportDoneEvent("Enter Address Line 1", "Address Line 1 Entered as ->" +customerInfo.addressLine1+RandomNumber());
		  ddtxtZipCode.sendKeys(customerInfo.zipCode);
		  report.reportDoneEvent("Enter Zipcode", "Zipcode Entered as ->" +customerInfo.zipCode);
		  imgZipcodeSearch.click();
		  waitforPageLoadComplete();
		  WaitandSwitchToFrame(frameCondition);
		  waitForElement(txtCity);
		  iterateThroughtableAndSelectCity(customerInfo.city);
		  btnOk.click();
		  browser.switchTo().defaultContent();
		  if(WaitandSwitchToFrame(frameMain)){
			  WaitandSwitchToFrame(frameCustomer);
			  }
		  waitForElement(btnCreate);
		  btnCreate.click();
		  waitforPageLoadComplete();
		  waitForElement(btnMore);
		  btnMore.click();
		  waitforPageLoadComplete();
		  WaitandSwitchToFrame(frameCondition);
		  waitForElement(chkDisclaimer);
		  chkDisclaimer.click();
		  waitForElement(btnContinue);
		  btnContinue.click();
		  browser.switchTo().defaultContent();
		  waitforPageLoadComplete();
		  Thread.sleep(3000);
		  report.updateTestLog("Create Customer", "Customer Created Successfully", Status.SCREENSHOT);
		  
	  }
	  
	  public void addressInformationValid(CustomerInfo customerInfo) throws InterruptedException{
		  
		  waitForElement(txtAddressLine1);
		  txtAddressLine1.sendKeys(customerInfo.addressLine1);
		  report.reportDoneEvent("Enter Address Line 1", "Address Line 1 Entered as ->" +customerInfo.addressLine1);
		  txtAddressLine2.sendKeys(customerInfo.addressLine2+" "+RandomNumber());
		  report.reportDoneEvent("Enter Address Line 2", "Address Line 2 Entered as ->" +customerInfo.addressLine2+RandomNumber());
		  ddtxtZipCode.sendKeys(customerInfo.zipCode);
		  report.reportDoneEvent("Enter Zipcode", "Zipcode Entered as ->" +customerInfo.zipCode);
		  imgZipcodeSearch.click();
		  waitforPageLoadComplete();
		  WaitandSwitchToFrame(frameCondition);
		  waitForElement(txtCity);
		  iterateThroughtableAndSelectCity(customerInfo.city);
		  btnOk.click();
		  browser.switchTo().defaultContent();
		  if(WaitandSwitchToFrame(frameMain)){
			  WaitandSwitchToFrame(frameCustomer);
			  }
		  waitForElement(btnCreate);
		  btnCreate.click();
		  waitforPageLoadComplete();
		  waitForElement(btnSelectValidSite);
		  btnSelectValidSite.click();
		  waitForElement(btnContinue);
		  btnContinue.click();
		  waitforPageLoadComplete();
		  browser.switchTo().defaultContent();
		  report.updateTestLog("Create Customer", "Customer Created Successfully", Status.SCREENSHOT); 
	  }
	  
	  @PerfTransaction(name="CreateCustomer")
	  public void createCustomer(CustomerInfo customerInfo) throws InterruptedException{
		  this.customerInformation(customerInfo);
		  this.addressInformationValid(customerInfo);
		  
	  }
	

	}

	
	

