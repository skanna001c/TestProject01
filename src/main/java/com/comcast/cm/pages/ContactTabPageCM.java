package com.comcast.cm.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.data.ContactInfo;
import com.comcast.reporting.Status;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;
import com.comcast.utils.TestSettings;


public class ContactTabPageCM extends Page {

	private TestSettings testSettings;

	protected static String HOME_PAGE_TITLE_QA = "Einstein 360°";

	protected static String HOME_PAGE_TITLE_STG = "Comcast Business | My Online Account";

	protected static String HOME_PAGE_TITLE_SCH = "Search";
	
	Logger log = Logger.getLogger(ContactTabPageCM.class);

	public ContactTabPageCM(FrameworkContext context) {
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

	@FindBy(xpath = "//*[@id='AccountFrame' and @src='account.exc']")
	private WebElement frameAccount;

	@FindBy(id ="addcontact-toolEl")
	private WebElement BtnAddContact;

	@FindBy(id ="mainFrame")
	private WebElement frameMain;

	@FindBy(id = "codition")
	private WebElement frameCondition;

	@FindBy(id = "cmb-comboConTypeId-inputEl")
	private WebElement ddTextContactType;

	@FindBy(xpath = "//li[text()='Account Primary']")
	private WebElement ddValueContactType1;

	@FindBy(xpath = "//li[text()='Site Technical Contact']")
	private WebElement ddValueContactType2;

	@FindBy(xpath = "//li[text()='Shipping']")
	private WebElement ddValueContactType3;

	@FindBy(xpath = "//li[text()='Billing']")
	private WebElement ddValueContactType4;

	@FindBy(id = "firstName")
	private WebElement txtFirstName;

	@FindBy(id = "lastName")
	private WebElement txtLastName;

	@FindBy(id = "emailId")
	private WebElement txtEmailId;

	@FindBy(id = "officePhone")
	private WebElement txtPrimaryPhone;

	@FindBy(id = "addrBean.addrGDBean.addrsLine1")
	private WebElement txtAddressLine1;

	@FindBy(id = "CmbGDcity-inputEl")
	private WebElement ddtxtCity;

	@FindBy(xpath = "//li[text()='Akiak']")
	private WebElement ddValueCity;

	@FindBy(id = "CmbGDzipcode-inputEl")
	private WebElement ddtxtZipCode;

	@FindBy(id ="zipcode_imgSearch")
	private WebElement imgZipcodeSearch;

	@FindBy(id = "0")
	private WebElement btnRadioSelectZip;

	@FindBy(id = "closeLookup")
	private WebElement btnOk;

	@FindBy(xpath = "//li[text()='99552']")
	private WebElement ddValueZipCode;

	@FindBy(id = "CmbGDregion-inputEl")
	private WebElement ddtxtRegion;

	@FindBy(xpath = "//li[text()='Seattle'][2]")
	private WebElement ddValueRegion;

	@FindBy(id = "CmbGDdivision-inputEl")
	private WebElement ddtxtDivision;

	@FindBy(xpath = "//li[text()='West']")
	private WebElement ddValueDivision;

	@FindBy(id = "CmbGDmarket-inputEl")
	private WebElement ddtxtMarket;

	@FindBy(xpath = "//li[text()='Seattle']")
	private WebElement ddValueMarket;

	@FindBy(id = "CmbGDstate-inputEl")
	private WebElement ddtxtState;

	@FindBy(xpath = "//li[text()='Alaska']")
	private WebElement ddValueState;

	@FindBy(id = "_eventId_saveContact")
	private WebElement btnCreate;

	@FindBy(xpath = "//input[@value='Back']")
	private WebElement btnBack;

	@FindBy(xpath = "//input[@value='Add New Contact']")
	private WebElement btnAddNewContact;

	@FindBy(id = "cmb-comboCustomerTitle-inputEl")
	private WebElement ddTextNameSuffix;

	@FindBy(xpath = "//li[text()='Mr']")
	private WebElement ddValueNameSuffix;

	private boolean mstatus;

	public boolean CreateAccountPrimaryContact(ContactInfo contactInfo) throws InterruptedException {
		mstatus = true;

		try {

			while (!WaitandSwitchToFrame(frameMain, 1)) {
			}
			ddValueSelect(ddTextContactType, ddValueContactType1, contactInfo.contactType1);
			report.reportDoneEvent("Select Contact Type", "Selected Contact Type as->" + contactInfo.contactType1);

			txtFirstName.sendKeys(contactInfo.firstName);
			report.reportDoneEvent("Enter First Name", "Entered First Name as->" + contactInfo.firstName);
			String lastName = contactInfo.lastName + randomNumber(5);
			txtLastName.sendKeys(lastName);
			report.reportDoneEvent("Enter Last Name", "Entered Last Name as->" + lastName);
			waitForElement(ddTextNameSuffix);
			ddValueSelect(ddTextNameSuffix, ddValueNameSuffix, contactInfo.nameSuffix);
			txtEmailId.sendKeys(contactInfo.emailId);
			report.reportDoneEvent("Enter EmailID", "Entered EmailID as->" + contactInfo.emailId);
			txtPrimaryPhone.sendKeys(contactInfo.primaryPhoneNum);
			report.reportDoneEvent("Enter Primary Phone Number",
					"Entered Primary Phone Number as->" + contactInfo.primaryPhoneNum);
			waitForElement(btnCreate);
			iClick(btnCreate, null, "Create Primary Contact: Contact Page: CreateButton");
			waitforPageLoadComplete();
			report.updateTestLog("Create Account Primary Contact", "Account Primary Contact Created Successfully",
					Status.SCREENSHOT);
			browser.switchTo().defaultContent();
		}

		catch (Exception ex) {
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}

	public boolean CreateBillingContact(ContactInfo contactInfo) throws InterruptedException {
		mstatus = true;
		try {

			while (!WaitandSwitchToFrame(frameMain, 1)) {
			}
			ddValueSelect(ddTextContactType, ddValueContactType4, "Billing");
			report.reportDoneEvent("Select Contact Type", "Selected Contact Type as->" + contactInfo.contactType1);
			txtFirstName.sendKeys(contactInfo.firstName);
			report.reportDoneEvent("Enter First Name", "Entered First Name as->" + contactInfo.firstName);
			String lastName = contactInfo.lastName + randomNumber(5);
			txtLastName.sendKeys(lastName);
			report.reportDoneEvent("Enter Last Name", "Entered Last Name as->" + lastName);
			waitForElement(ddTextNameSuffix);
			ddValueSelect(ddTextNameSuffix, ddValueNameSuffix, contactInfo.nameSuffix);
			txtEmailId.sendKeys(contactInfo.emailId);
			report.reportDoneEvent("Enter EmailID", "Entered EmailID as->" + contactInfo.emailId);
			txtPrimaryPhone.sendKeys(contactInfo.primaryPhoneNum);
			report.reportDoneEvent("Enter Primary Phone Number",
					"Entered Primary Phone Number as->" + contactInfo.primaryPhoneNum);
			iClick(btnCreate, null, "Create Billing Contact: Contact Page: CreateButton");
			waitforPageLoadComplete();
			report.updateTestLog("Create Billing Contact", "Billing Contact Created Successfully", Status.SCREENSHOT);
			browser.switchTo().defaultContent();
		}

		catch (Exception ex) {
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}

	/**
	 * Check if the element is present in the page
	 * 
	 * @param Frames
	 *            frameMain,default content
	 * @return
	 */
	public boolean CreateSiteTechnicalContact(ContactInfo contactInfo) throws InterruptedException {
		mstatus = true;
		try {
			if (WaitandSwitchToFrame(frameMain)) {
				ddValueSelect(ddTextContactType, ddValueContactType2, contactInfo.contactType2);
				report.reportDoneEvent("Select Contact Type", "Selected Contact Type as->" + contactInfo.contactType2);
				txtFirstName.sendKeys(contactInfo.firstName);
				report.reportDoneEvent("Enter First Name", "Entered First Name as->" + contactInfo.firstName);
				String lastName = contactInfo.lastName + randomNumber(5);
				txtLastName.sendKeys(lastName);
				report.reportDoneEvent("Enter Last Name", "Entered Last Name as->" + lastName);
				waitForElement(ddTextNameSuffix);
				ddValueSelect(ddTextNameSuffix, ddValueNameSuffix, contactInfo.nameSuffix);
				txtEmailId.sendKeys(contactInfo.emailId);
				report.reportDoneEvent("Enter EmailID", "Entered EmailID as->" + contactInfo.emailId);
				txtPrimaryPhone.sendKeys(contactInfo.primaryPhoneNum);
				report.reportDoneEvent("Enter Primary Phone Number",
						"Entered Primary Phone Number as->" + contactInfo.primaryPhoneNum);
				waitForElement(btnCreate);
				iClick(btnCreate, null, "Create site technical Contact: Contact Page: CreateButton");
				waitforPageLoadComplete();
				report.updateTestLog("Create Site Technical Contact", "Site Technical Contact Created Successfully",
						Status.SCREENSHOT);
				browser.switchTo().defaultContent();
			}
		} catch (Exception ex) {
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;

	}

	public boolean CreateShippingContact(ContactInfo contactInfo) throws InterruptedException {
		mstatus = true;
		try {
			if (WaitandSwitchToFrame(frameMain)) {
				waitForElement(btnAddNewContact);
				iClick(btnAddNewContact, ddTextContactType, "Create Shipping Contact: Contact Page: CreateButton");
				waitForElement(ddTextContactType);
				ddValueSelect(ddTextContactType, ddValueContactType3, contactInfo.contactType3);
				report.reportDoneEvent("Select Contact Type", "Selected Contact Type as->" + contactInfo.contactType2);
				String firstName = contactInfo.firstName;
				txtFirstName.sendKeys(firstName);
				report.reportDoneEvent("Enter First Name", "Entered First Name as->" + firstName);
				String lastName = contactInfo.lastName + randomNumber(5);
				txtLastName.sendKeys(lastName);
				report.reportDoneEvent("Enter Last Name", "Entered Last Name as->" + lastName);
				txtEmailId.sendKeys(contactInfo.emailId);
				report.reportDoneEvent("Enter EmailID", "Entered EmailID as->" + contactInfo.emailId);
				txtPrimaryPhone.sendKeys(contactInfo.primaryPhoneNum);
				report.reportDoneEvent("Enter Primary Phone Number",
						"Entered Primary Phone Number as->" + contactInfo.primaryPhoneNum);
				waitForElement(btnCreate);
				iClick(btnCreate, null, "Create Shipping Contact: Contact Page: CreateButton");
				waitforPageLoadComplete();
				report.updateTestLog("Create Shipping Contact", "Shipping Contact Created Successfully",
						Status.SCREENSHOT);
				browser.switchTo().defaultContent();
			}
		} catch (Exception ex) {
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;

	}

	public boolean ClickOnBackBtn() {
		scrollDown();
		mstatus = true;
		try {
			while (!WaitandSwitchToFrame(frameMain, 1)) {
			}
			while (!waitForElement(btnBack, 2)) {
			}
			iClick(btnBack, null, "Click on back button: Contact Page: BackButton");
			report.reportDoneEvent("Click on Back Button", "Back Button Clicked");
			browser.switchTo().defaultContent();
		} catch (Exception ex) {
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
}
