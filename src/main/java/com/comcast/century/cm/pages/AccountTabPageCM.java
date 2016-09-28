package com.comcast.century.cm.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.common.NewConnectTest;
import com.comcast.century.data.AccountInfo;
import com.comcast.reporting.Status;
import com.comcast.utils.SeleniumReport;
import com.comcast.utils.TestSettings;
import com.comcast.utils.ComcastTest.FrameworkContext;

import mx4j.log.Logger;

public class AccountTabPageCM extends Page {
	
	
	public AccountTabPageCM(FrameworkContext context){
		super(context);
	}
	
	

	//@FindBy(xpath = "//a[.='Account']") // @FindBy(xpath = "//a[.='Account']")
	@FindBy(linkText="Account")
	private WebElement tabAccount;

	@FindBy(xpath = "//*[@id='mainFrame' and contains(@src,'loadCustomerPanel.exc')]")
	private WebElement frameMain;

	@FindBy(xpath = "//*[@id='AccountFrame' and @src='account.exc']")
	private WebElement frameAccount;


	@FindBy(xpath = "//input[@id='cmb-comboAccountType-inputEl']")
	private WebElement ddTextAccountType;

	@FindBy(xpath = "//li[text()='Service']")
	private WebElement ddValueAccountTypeService;

	@FindBy(xpath = "//li[text()='Billing']")
	private WebElement ddValueAccountTypeBilling;

	@FindBy(xpath = "//input[@id='entityCombo-inputEl']")
	private WebElement ddtxtServiceAcc;

	// input[contains(@value,'AcctNam')]
	@FindBy(xpath = "//input[contains(@value,'CENTURY_PROD_TEST') or contains(@value,'CENTURY_CAT_TEST')]")
	private WebElement chkServiceAcc;

	@FindBy(xpath = "//*[@id='assocToCombo-inputEl']")
	private WebElement ddtxtAssocTo;

	@FindBy(xpath = "//li[text()='Site']")
	private WebElement ddValueAssocTo;

	@FindBy(xpath = "//input[contains(@value,'Site')]")
	private WebElement chkSite;

	// img[@title='Get BAN']
	@FindBy(xpath = "//img[@title='Get BAN']")
	private WebElement imgGetBAN;

	// txtBillingAccnName
	@FindBy(xpath = "//input[@id='billingAccountName']")
	private WebElement txtBillingAccnName;

	@FindBy(id = "ext-gen1347")
	private WebElement ddArrwAssocTo;

	@FindBy(xpath = "//input[@id='assocToCombo-inputEl']")
	private WebElement ddTextAssocTo;

	@FindBy(id = "accName")
	private WebElement txtAccnName;

	@FindBy(id = "ext-gen1223")
	private WebElement ddArrwLegalEntity;

	@FindBy(xpath = "//input[@id='cmb-comboComcastLegalEntity-inputEl']")
	private WebElement ddTextLegalEntity;

	@FindBy(xpath = "//li[text()='Comcast Business Communications, LLC']")
	private WebElement ddValueLegalEntity;

	@FindBy(id = "ext-gen1228")
	private WebElement ddArrwLOB;

	@FindBy(xpath = "//input[@id='cmb-comboLineOfBusiness-inputEl']")
	private WebElement ddTextLOB;

	@FindBy(xpath = "//li[text()='Metro E']")
	private WebElement ddValueLOB;

	@FindBy(xpath = "//*[@id='cmb-comboBillERate-inputEl']")
	private WebElement ddTextErate;

	@FindBy(xpath = "//li[text()='Yes']")
	private WebElement ddValueErate;

	@FindBy(id = "ext-gen1208")
	private WebElement ddArrwVMarket;

	@FindBy(xpath = "//input[@id='cmb-comboVMarket-inputEl']")
	private WebElement ddTextVMarket;

	@FindBy(xpath = "//li[text()='Cable Operators']")
	private WebElement ddValueVMarket;

	@FindBy(xpath = "//*[@id='cmb-comboErateMarket-inputEl']")
	private WebElement ddTextVMarketErate;

	@FindBy(xpath = "//li[text()='Rural Healthcare']")
	private WebElement ddValueVMarketErate;

	@FindBy(id = "useCustomerAddress")
	private WebElement useCustomerAddress;

	///@FindBy(xpath = "//*[@id='_eventId_checkAccAddAvailability']")
	@FindBy(id = "_eventId_checkAccAddAvailability")
	private WebElement ValidateBtn;

	@FindBy(xpath = "//*[@id='_eventId_createAccount']")
	private WebElement createAccountBtn;

	@FindBy(xpath = "//input[@value='Create New Account']")
	private WebElement btnCreateNewAcc;

	// *[@id='addcontact-toolEl']

	@FindBy(xpath = "//*[@id='addcontact-toolEl']")
	private WebElement BtnAddContact;

	private boolean mstatus;

	@Override
	protected boolean isValidPage() {
		
		return false;
	}

	@Override
	protected void waitForPageLoad() {

	}

	public boolean CreateServiceAccount(AccountInfo accountInfo) throws InterruptedException {
		mstatus = true;
		try {
			waitforPageLoadComplete();
			while (!WaitandSwitchToFrame(frameMain)) {
			}
			while (!waitForElement(tabAccount)) {
			}
			do {
				tabAccount.click();
				jsClick(tabAccount);
			} while (!WaitandSwitchToFrame(frameAccount, 1));
			report.reportDoneEvent("Click on Account tab", "Account tab clicked");
			while (!waitForElement(txtAccnName)) {
			}

			do {
				txtAccnName.click();
				txtAccnName.clear();
				txtAccnName.sendKeys(accountInfo.serviceAccName);
			} while (txtAccnName.getAttribute("value").length() < 1);

			report.reportDoneEvent("Enter Service Account Name",
					"Entered Service Account Name as->" + accountInfo.serviceAccName);
			waitforPageLoadComplete();
			ddValueSelect(ddTextLegalEntity, ddValueLegalEntity, accountInfo.legalEntity);
			report.reportDoneEvent("Select Legal Entity", "Selected Legal Entity as->" + accountInfo.legalEntity);
			ddValueSelect(ddTextLOB, ddValueLOB, accountInfo.lineOfBusiness);
			report.reportDoneEvent("Select Line of Business",
					"Selected Line of Business as->" + accountInfo.lineOfBusiness);
			if ((accountInfo.eRate).equalsIgnoreCase("Yes")) {
				ddValueSelect(ddTextErate, ddValueErate, "Yes");
				report.reportDoneEvent("Select Erate dropdown", "Dropdown Selected");
				ddValueSelect(ddTextVMarketErate, ddValueVMarketErate, accountInfo.verticalMarket);
			} else {
				ddValueSelect(ddTextVMarket, ddValueVMarket, accountInfo.verticalMarket);
			}

			report.reportDoneEvent("Select Vertical Market",
					"Selected Vertical Market as->" + accountInfo.verticalMarket);
			waitforPageLoadComplete();
			waitForElement(useCustomerAddress);
			useCustomerAddress.click();
			report.reportDoneEvent("Check Use Customer Address", "Use Customer Address Checked");
			waitforPageLoadComplete();
			waitForElement(createAccountBtn);
			iClick(createAccountBtn, null, "Create Service Account: CustomerPage: CreateButton");
			waitforPageLoadComplete();
			report.updateTestLog("Create Service Account", "Service Account Created Successfully", Status.SCREENSHOT);
			browser.switchTo().defaultContent();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			mstatus = false;
		}
		return mstatus;
	}

	public boolean clickOnAddContact() {
		mstatus = true;
		try {
			while (!WaitandSwitchToFrame(frameMain)) {
			}
			while (!WaitandSwitchToFrame(frameAccount)) {
			}
			while (!waitForElement(BtnAddContact)) {

			}
			scrollDown();
			BtnAddContact.click();
			/**
			  * Here Keys.ENTER is not working in IE. So we are using normal click for all the browsers.
			  * Updated by Kesavan on 06th Sep 2016			  
			    //iClick(BtnAddContact, null, "Add Contact: ContactPage: AddContactButton");
			 */
			
			waitforPageLoadComplete();
			// browser.switchTo().defaultContent();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			mstatus = false;
		}
		return mstatus;
	}

	public boolean CreateErateServiceAccount(AccountInfo accountInfo) throws InterruptedException {
		mstatus = true;
		try {
			waitforPageLoadComplete();
			WaitandSwitchToFrame(frameMain);
			waitForElement(tabAccount);
			tabAccount.click();
			jsClick(tabAccount);
			report.reportDoneEvent("Click on Account tab", "Account tab clicked");
			WaitandSwitchToFrame(frameAccount);
			txtAccnName.click();
			txtAccnName.clear();
			String serviceAccName = accountInfo.serviceAccName + getTimestamp();
			txtAccnName.sendKeys(serviceAccName);
			report.reportDoneEvent("Enter Service Account Name", "Entered Service Account Name as->" + serviceAccName);
			waitforPageLoadComplete();
			ddValueSelect(ddTextLegalEntity, ddValueLegalEntity, accountInfo.legalEntity);
			report.reportDoneEvent("Select Legal Entity", "Selected Legal Entity as->" + accountInfo.legalEntity);
			ddValueSelect(ddTextLOB, ddValueLOB, accountInfo.lineOfBusiness);
			report.reportDoneEvent("Select Line of Business",
					"Selected Line of Business as->" + accountInfo.lineOfBusiness);
			ddValueSelect(ddTextErate, ddValueErate, "Yes");
			report.reportDoneEvent("Select Erate dropdown", "Dropdown Selected");
			ddValueSelect(ddTextVMarketErate, ddValueVMarketErate, accountInfo.verticalMarket);
			waitforPageLoadComplete();
			waitForElement(useCustomerAddress);
			useCustomerAddress.click();
			report.reportDoneEvent("Check Use Customer Address", "Use Customer Address Checked");
			waitforPageLoadComplete();
			waitForElement(createAccountBtn);
			iClick(createAccountBtn, null, "Create ERate Service Account: CustomerPage: CreateButton");
			waitforPageLoadComplete();
			report.updateTestLog("Create Service Account", "Service Account Created Successfully", Status.SCREENSHOT);
			browser.switchTo().defaultContent();
		} catch (Exception ex) {
			mstatus = false;
		}

		return mstatus;
	}

	public boolean CreateBillingAccount(AccountInfo accountInfo) throws InterruptedException {
		mstatus = true;
		try {

			waitforPageLoadComplete();
			if (WaitandSwitchToFrame(frameMain)) {
				if (WaitandSwitchToFrame(frameAccount)) {
					if (waitForElement(btnCreateNewAcc)) {
						System.out.println("Create new Acc exists");

					}

					do {
						iClick(btnCreateNewAcc, null, "CreateAccount: AccountPage: CreateNewAccountButton");
						waitforPageLoadComplete();
					} while (getValue(txtBillingAccnName).length() > 1);

					report.reportDoneEvent("Click on Create New Account", "Create New Account Clicked");
					ddValueSelect(ddTextAccountType, ddValueAccountTypeBilling, accountInfo.accountType);
					report.reportDoneEvent("Select Account Type",
							"Selected Account Type as->" + accountInfo.accountType);
					waitforPageLoadComplete();
					ddValueSelect(ddtxtServiceAcc, chkServiceAcc, accountInfo.serviceAccName);
					report.reportDoneEvent("Select Service Account",
							"Selected Service Account as->" + accountInfo.serviceAccName);
					waitforPageLoadComplete();
					txtBillingAccnName.click();
					txtBillingAccnName.sendKeys(accountInfo.billingAccName);
					report.reportDoneEvent("Enter Billing Account Name",
							"Entered Billing Account Name as->" + accountInfo.billingAccName);
					waitforPageLoadComplete();
					imgGetBAN.click();
					report.reportDoneEvent("Click to get BAN", "Clicked to get BAN");
					waitforPageLoadComplete();
					waitForElement(useCustomerAddress);
					report.reportDoneEvent("Check Use Customer Address", "Use Customer Address Checked");
					useCustomerAddress.click();
					waitforPageLoadComplete();
					waitForElement(createAccountBtn);
					iClick(createAccountBtn, null, "Create Billing Account: AccountPage: CreateAccountButton");
					waitforPageLoadComplete();
					report.updateTestLog("Create Billing Account", "Billing Account Created Successfully",
							Status.SCREENSHOT);
					browser.switchTo().defaultContent();

				}
			}
		} catch (Exception ex) {
			mstatus = false;
		}
		return mstatus;
	}

	public boolean CreateSecondBillingAccount(AccountInfo accountInfo) throws InterruptedException {
		mstatus = true;
		try {

			WaitandSwitchToFrame(frameMain);
			waitForElement(tabAccount);
			tabAccount.click();
			jsClick(tabAccount);
			waitforPageLoadComplete();
			report.reportDoneEvent("Click on Account tab", "Account tab clicked");
			WaitandSwitchToFrame(frameAccount);
			if (waitForElement(btnCreateNewAcc)) {
				System.out.println("Create new Acc exists");
				btnCreateNewAcc.click();
				jsClickWE(btnCreateNewAcc);
				report.reportDoneEvent("Click on Create New Account", "Create New Account Clicked");
				waitforPageLoadComplete();
			}
			ddValueSelect(ddTextAccountType, ddValueAccountTypeBilling, accountInfo.accountType);
			report.reportDoneEvent("Select Account Type", "Selected Account Type as->" + accountInfo.accountType);
			waitforPageLoadComplete();
			waitForElement(ddtxtAssocTo);
			ddValueSelect(ddtxtAssocTo, ddValueAssocTo, "Site");
			waitForElement(ddtxtServiceAcc);
			ddValueSelect(ddtxtServiceAcc, chkSite, "Site");
			waitforPageLoadComplete();
			txtBillingAccnName.click();
			String billingAccName = accountInfo.billingAccName + getTimestamp();
			txtBillingAccnName.sendKeys(billingAccName);
			report.reportDoneEvent("Enter Billing Account Name", "Entered Billing Account Name as->" + billingAccName);
			waitforPageLoadComplete();
			imgGetBAN.click();
			report.reportDoneEvent("Click to get BAN", "Clicked to get BAN");
			waitforPageLoadComplete();
			waitForElement(useCustomerAddress);
			report.reportDoneEvent("Check Use Customer Address", "Use Customer Address Checked");
			useCustomerAddress.click();
			waitforPageLoadComplete();
			waitForElement(createAccountBtn);
			iClick(createAccountBtn, null, "Create Billing Account: CustomerPage: CreateButton");
			waitforPageLoadComplete();
			report.updateTestLog("Create Billing Account", "Billing Account Created Successfully", Status.SCREENSHOT);
			browser.switchTo().defaultContent();
		} catch (Exception ex) {
			mstatus = false;
		}
		return mstatus;
	}
}
