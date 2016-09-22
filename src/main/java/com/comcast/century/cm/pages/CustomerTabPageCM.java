package com.comcast.century.cm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.ClickAndHoldAction;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.data.CustomerInfo;
import com.comcast.century.data.SupplementInfo;
import com.comcast.reporting.Status;
import com.comcast.utils.ComcastTest.FrameworkContext;
import com.comcast.utils.PerfTransaction;
import com.comcast.utils.SeleniumReport;
import com.comcast.utils.TestSettings;

import junit.framework.Assert;

public class CustomerTabPageCM extends Page {

	private TestSettings testSettings;

	protected static String HOME_PAGE_TITLE_QA = "Einstein 360°";

	protected static String HOME_PAGE_TITLE_STG = "Comcast Business | My Online Account";

	protected static String HOME_PAGE_TITLE_SCH = "Search";

	public CustomerTabPageCM(WebDriver browser, SeleniumReport report) {
		super(browser, report);
	}
	
	public CustomerTabPageCM(FrameworkContext context){
		super(context);
	}
	
	private String customerName;
	
	@FindBy(css = "#Customer")
	private WebElement tabCustomer;	
	
	@FindBy(xpath=".//*[@class='x-tab-strip-text' and .='Customer']")
	private WebElement subtabCustomer;
	
	@FindBy(xpath = "//*[@id='mainFrame']")
	private WebElement frameMain;
	
	@FindBy(xpath = "//*[@id='leftFrame']")
	private WebElement frameLeft;
	
	@FindBy(xpath = "//*[@class[contains(.,'expand')]]")
	private WebElement btnExpand ;

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
	
	@FindBy(xpath = "//span[.='Order']")
	private WebElement tabOrder;
	
	@FindBy(xpath = "//tr[contains(@title,'In-Progress')]/child::td[@class='standartTreeImage']/child::img[contains(@src,'images/csh_bluebooks/plus')]")
	private WebElement plusButtonInProgress;
	
	@FindBy(xpath = "//tr[contains(@title,'Completed')]/child::td[@class='standartTreeImage']/child::img[contains(@src,'images/csh_bluebooks/plus')]")
	private WebElement plusButtonCompleted;
	
	@FindBy(xpath = "//span[contains(.,'SR ID')]")
	private WebElement linkSRID;
	
	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading ;
	
	
	
	private boolean mstatus;	
	
	
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
	
	  public String customerInformation(CustomerInfo customerInfo){		  
		  waitforPageLoadComplete();
		  /*waitForElement(tabCustomer);
		  //tabCustomer.click();
		   * doubleClick(tabCustomer);*/
		  do{
			  browser.switchTo().defaultContent();
			  if(waitForElement(tabCustomer,1)){
				  doubleClick(tabCustomer);
				  waitforPageLoadComplete();
			  }
			  WaitandSwitchToFrame(frameMain);
		  }while(!WaitandSwitchToFrame(frameCustomer,1));
		  
		  
		 /* if(WaitandSwitchToFrame(frameMain)){
			 // WaitandSwitchToFrame(frameCustomer);
			  
			  do{
				  if(waitForElement(tabCustomer,1)){
					  doubleClick(tabCustomer);
				  }
			  }while(!WaitandSwitchToFrame(frameCustomer,1));
		  }*/
		  
		  report.reportDoneEvent("Click on Customer Tab", "Customer Tab is present");
		  waitforPageLoadComplete();
		  waitForElement(txtCustomerName);
		 //while(!waitForElement(txtCustomerName,1)){};
		 customerName = customerInfo.customerName + getTimestamp();
		 do 
		 {    txtCustomerName.click();
			  txtCustomerName.clear();
			  //updated by harsh to store runtime data into datadump - 8/5/2016
			  
			  txtCustomerName.sendKeys(customerName);
		 }
		 	while(txtCustomerName.getAttribute("value").length()<1);
		 
		 System.out.println("txtCustomerName.getAttribute"+txtCustomerName.getAttribute("value"));
		  //ComcastTest.getDataDump().setValue("CustomerName_RT", customerName); //commented by harsh on 8/16 - need to find a way to push data to datadump
		  		  
		  waitforPageLoadComplete();
		  report.reportDoneEvent("Enter Customer Name", "Entered Customer Name as ->" + customerName);
		  if(waitForElement(txtworkPhone)){
			  txtworkPhone.click();
		  }
		  txtworkPhone.sendKeys(customerInfo.workPhone);
		  report.reportDoneEvent("Enter Work Phone", "Work Phone Entered as ->" +customerInfo.workPhone);
		  String salesForceID = customerInfo.salesForceAccId+randomNumber(5);
		  txtSalesForceID.sendKeys(salesForceID);
		  report.reportDoneEvent("Enter Salesforce Account ID", "Salesforce Account ID Entered as ->" + salesForceID);  
		  return customerName;

	  }
	  
	  public boolean addressInformationInvalid(CustomerInfo customerInfo) throws InterruptedException{
		  mstatus = true;
		  try{
				  
			  waitForElement(txtAddressLine1);
			  String addressLine1 = customerInfo.addressLine1+getTimestamp();
			  txtAddressLine1.sendKeys(addressLine1);
			  report.reportDoneEvent("Enter Address Line 1", "Address Line 1 Entered as ->" + addressLine1);
			  ddtxtZipCode.sendKeys(customerInfo.zipCode);
			  report.reportDoneEvent("Enter Zipcode", "Zipcode Entered as ->" +customerInfo.zipCode);
			  imgZipcodeSearch.click();
			  waitforPageLoadComplete();
			  while(!WaitandSwitchToFrame(frameCondition)){}			  
			  while(!waitForElement(txtCity)){}
			  while(!iterateThroughtableAndSelectCity(customerInfo.city)){};
			  iClick(btnOk, null, "Select City and click OK: Customer Page: OKButton");			  
			  browser.switchTo().defaultContent();
			  if(WaitandSwitchToFrame(frameMain)){
				  WaitandSwitchToFrame(frameCustomer);
				  }
			  waitForElement(btnCreate);
			  iClick(btnCreate, btnMore, "Create Customer: Customer Page: CreateButton");			  
			  waitforPageLoadComplete();
			  waitForElement(btnMore);
			  iClick(btnMore, null, "Create Customer: Customer Page: MoreButton");			  
			  waitforPageLoadComplete();
			  WaitandSwitchToFrame(frameCondition);
			  waitForElement(chkDisclaimer);
			  chkDisclaimer.click();
			  waitForElement(btnContinue);
			  iClick(btnContinue, null, "Create Customer: Customer Page: ContinueButton");			  
			  browser.switchTo().defaultContent();
			  waitforPageLoadComplete();
			  Thread.sleep(3000);
			  report.updateTestLog("Create Customer", "Customer Created Successfully", Status.SCREENSHOT);
		  }
		  catch(Exception ex)
		  {
			  mstatus = false;
		  }
		  return mstatus;
			  
	  }
	  
	  public boolean addressInformationValid(CustomerInfo customerInfo) throws InterruptedException{
		  mstatus = true;
		  String addressLine2;
		  try {
			waitForElement(txtAddressLine1);
			  txtAddressLine1.sendKeys(customerInfo.addressLine1);
			  report.reportDoneEvent("Enter Address Line 1", "Address Line 1 Entered as ->" +customerInfo.addressLine1);
			  if(testName.equalsIgnoreCase("EDI-New_connect_Local_Biller_with_Biller_type-CSG")){
				  addressLine2=customerInfo.addressLine2;
				}
				else
				{	
					addressLine2=customerInfo.addressLine2 + " " + getTimestamp();
				}
			 
			  txtAddressLine2.sendKeys(addressLine2);
			  report.reportDoneEvent("Enter Address Line 2", "Address Line 2 Entered as ->" + addressLine2);
			  ddtxtZipCode.sendKeys(customerInfo.zipCode);
			  report.reportDoneEvent("Enter Zipcode", "Zipcode Entered as ->" +customerInfo.zipCode);
			  imgZipcodeSearch.click();
			  waitforPageLoadComplete();
			  while(!WaitandSwitchToFrame(frameCondition)){}			  
			  while(!waitForElement(txtCity)){}
			  while(!iterateThroughtableAndSelectCity(customerInfo.city)){};
			  btnOk.sendKeys(Keys.ENTER);
			  browser.switchTo().defaultContent();
			  if(WaitandSwitchToFrame(frameMain)){
				  WaitandSwitchToFrame(frameCustomer);
				  }
			  waitForElement(btnCreate);
			  iClick(btnCreate,null,"CreateCustomer: Customer Page: CreateButton");//updated by harsh on 9/2/2016
			  waitforPageLoadComplete();
			  if( waitForElement(btnSelectValidSite,5)){
			  btnSelectValidSite.click();
			  waitForElement(btnContinue);
			  iClick(btnContinue,null,"CreateCustomer: Address Verification: Continue_Button"); //updated by harsh on 9/2/2016
			  waitforPageLoadComplete();
			  }
			  browser.switchTo().defaultContent();
			  report.updateTestLog("Create Customer", "Customer Created Successfully", Status.SCREENSHOT);
		} catch (Exception e) {
			mstatus = true;
			e.printStackTrace();
		}
		return mstatus;
	  }
	  
	  
	  /**
		 *Creates Customer
		 *
		 **/
	  @PerfTransaction(name="CreateCustomer")
	  public String createCustomer(CustomerInfo customerInfo) throws InterruptedException{
		  String custName = customerInformation(customerInfo);
		  this.addressInformationValid(customerInfo);
		  return custName;
		  
	  }
	  
	public boolean clickSRIDInOrderHierarchy(String custName, SupplementInfo supplementInfo) {
		mstatus = true;
		String xpath = "//tr[@title='" + custName + "']/child::td[@class='standartTreeImage']/child::"
				+ "img[contains(@src,'images/csh_bluebooks/plus')]";
		try {
			do {
				if (isElementDisplayed(btnExpand)) {
					iClick(btnExpand, null, "ExpandSOHierarchyButton: CSO Page: ExpandButton");
				}
			} while (isElementDisplayed(btnExpand, 2));
			waitforPageLoadComplete();
			WaitandSwitchToFrame(frameLeft);
			do {
				if (isElementDisplayed(tabOrder)) {
					iClick(tabOrder, null, "OrderButton: CSO Page: tabOrder");
				}
			} while (!waitUntilElementPresent(By.xpath(xpath), 2));
			waitforPageLoadComplete();
			if (waitUntilElementPresent(By.xpath(xpath), 60)) {
				iClick(browser.findElement(By.xpath(xpath)));
			}
			waitforPageLoadComplete();
			if (supplementInfo.supplementType.matches("Tech Sup|Admin Sup|Cancel Sup")) {
				waitForElement(plusButtonInProgress);
				iClick(plusButtonInProgress, linkSRID, "Click on + button: CSO Page: InProgress + button");
			} else if (supplementInfo.supplementType.matches("Change|Disconnect")) {
				waitForElement(plusButtonCompleted);
				iClick(plusButtonCompleted, linkSRID, "Click on + button: CSO Page: Completed + button");
			}
			waitforPageLoadComplete();
			waitForElement(linkSRID);
			clickndRelease(linkSRID);
			waitforPageLoadComplete();
			browser.switchTo().defaultContent();
		} catch (Exception e) {
			mstatus = true;
			e.printStackTrace();
		}

		return mstatus;
	}
	  
}


	
	

