package com.comcast.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
//DB Validation
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.log4testng.Logger;
import org.apache.log4j.Logger;

import com.comcast.century.cm.pages.AddressTabPageCM;
import com.comcast.century.cm.pages.ContactTabPageCM;
import com.comcast.century.cm.pages.CustomerTabPageCM;
import com.comcast.century.cm.pages.LogInPage;
import com.comcast.logging.logtransactions.LoggerMain;
import com.comcast.reporting.Status;
import com.comcast.utils.ComcastTest.FrameworkContext;


/**
 * Base class for all the pages.
 *
 */
public abstract class Page {
	protected WebDriver browser;
	protected SeleniumReport report;
	//public CenturyApplication einsteinApplication;
	public LogInPage logInPage;
	protected DataTable dataTable;
	protected TestSettings testSettings;
	protected Properties properties;
	protected IDataDump dataDump;
	private String env;
	private String browserVersion;
	protected String title;
	private LoggerMain tLogger;
	protected String testName;
	protected ContactTabPageCM contactTab;
	protected AddressTabPageCM addressTab;
	protected FrameworkContext frameworkContext;
	

	protected abstract boolean isValidPage();

	protected abstract void waitForPageLoad();
	
	static Logger log = Logger.getLogger(Page.class);

	/**
	 * Constructor for Page class
	 * 
	 * @param browser
	 * @param report
	 */
	protected Page(WebDriver browser, SeleniumReport report) {
		//log.info("create page instance");
		this.browser = browser;
		this.report = report;
		PageFactory.initElements(browser, this);
		// waitForPageLoad();
		verifyApplicationInCorrectPage();
		//added by harsh on 9/9 to get the browserversion
		/*browserVersion = (String) ((JavascriptExecutor) browser).executeScript("return navigator.userAgent;");
		testSettings = new TestSettings();
		properties = testSettings.getProperties();
		properties.put("browser_version", browserVersion);*/
		//System.out.println(properties);
		
	}
	
	/**
	 * @param context
	 */
	protected Page (FrameworkContext context){
		this.browser = context.getDriver();
		this.report = context.getReport();
		this.tLogger = context.getTransactionLogger();
		this.testSettings = context.getSettings();
		this.testName = context.getTestCaseName();
		this.dataDump=context.getDataDump();
		//this.contactTab = context.getContactTabPageCM();
		//this.addressTab = context.getAddressTabPageCM();
		
		PageFactory.initElements(browser, this);
		// waitForPageLoad();
		verifyApplicationInCorrectPage();
	}

	/**
	 * Verify Application in Correct Page.
	 * 
	 * @param Nil
	 * @return Nil
	 */

	private void verifyApplicationInCorrectPage() {
		if (!isValidPage()) {
			String stepName = "Navigation to Page";
			String message = "The application is not in the expected page , current page: " + browser.getTitle()
					+ " Page.";
			// report.reportFailEvent(stepName, message);
		}
	}

	/**
	 * Verify link inside an HTML element.
	 * 
	 * @param element
	 *            HTML element in which link need to search
	 * @return linkText text of the link need to verify
	 */
	protected void verifyLinksInSection(WebElement element, String linkText, String stepName) {
		try {
			Assert.assertTrue(element.findElement(By.linkText(linkText)).isDisplayed());
			report.reportPassEvent(stepName, "Verify Link with link text: " + linkText);
		} catch (RuntimeException ex) {
			report.reportFailEvent(stepName, "Verify Link with link text: " + linkText);
		}
	}

	/**
	 * Check if the element is present in the page
	 * 
	 * @param element
	 *            WebElement need to check
	 * @return True if present
	 */
	protected boolean isElementPresent(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (NoSuchElementException ex) {
			return false;
		} catch (StaleElementReferenceException ex2) {
			return false;
		}
	}

	/**
	 * Check if the element is present in the page by size
	 * 
	 * @param element
	 *            WebElement need to check
	 * @return True if present
	 */

	protected boolean isElementDisplayedBySize(By element) {
		try {
			if ((browser.findElement(element).getSize().height) > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception Ex) {
			return false;
		}
	}

	/**
	 * Method to check Element id displayed in the page by its id value
	 * 
	 * @param ElementName
	 */

	protected boolean isElementDisplayed(String ElementName) {
		try {
			new WebDriverWait(browser, 50).until(ExpectedConditions.elementToBeClickable(By.id(ElementName)));

		} catch (RuntimeException ex) {
			return false;
		}
		return true;
	}

	/**
	 * Check if the element is present in the page
	 * 
	 * @param element
	 *            WebElement need to check
	 * @return True if present
	 */
	public boolean isElementPresent(By by) {
		try {
			return browser.findElement(by).isDisplayed();
		} catch (NoSuchElementException ex) {
			return false;
		} catch (StaleElementReferenceException ex2) {
			return false;
		}
	}

	/*
	 * public void authenticateHelp(LoginDetails loginInfo) throws IOException {
	 * 
	 * if(einsteinApplication.env.equals("UAT")){
	 * logInPage.applicationLogin(loginInfo); }else{
	 * einsteinApplication.openQAApplication(); } }
	 */

	/**
	 * Check if the element is present in the page and report error
	 * 
	 * @param element
	 * @param errorMsg
	 *            error message need to report if the element not present
	 */
	protected void isElementPresent(WebElement element, String stepName, String errorMsg) {
		if (!isElementPresent(element)) {
			report.reportFailEvent(stepName, errorMsg);
		}
	}

	/**
	 * Check if the element is present in the page and report error
	 * 
	 * @param element
	 * @param errorMsg
	 *            error message need to report if the element not present
	 */
	protected void checkElementPresent(WebElement element, String value) {
		if (isElementPresent(element)) {
			report.reportPassEvent("VerifyPresenceof: " + value, "Element is present");
			report.updateTestLog("VerifyPresenceof: " + value, "Element is present", Status.SCREENSHOT);
		} else {
			report.reportFailEvent("VerifyPresenceof: " + value, "Element is not present");
		}
	}

	/**
	 * Check if the element is present in the page and report error
	 * 
	 * @param element
	 * @param errorMsg
	 *            error message need to report if the element not present
	 */
	protected void checkElementPresentBVT(WebElement element, String value) {
		if (isElementPresent(element)) {
			report.reportPassEvent("HomePage : VerifyPresenceof: " + value, "Element is present");
			// report.updateTestLog("HomePage : VerifyPresenceof: "+value,
			// "Element is present",Status.SCREENSHOT);
		} else {
			report.reportFailEvent("VerifyPresenceof: " + value, "Element is not present");
		}
	}
	// *****************************************************************************************************************//
	// Button
	// *****************************************************************************************************************//

	/***
	 * Method to click on a link(WebElement button)
	 * 
	 * @param :
	 *            Element Name
	 ***/
	public void clickOnButton(WebElement button) {
		String btn = button.getText();
		try {
			waitForElement(button);

			if (isElementPresent(button)) {
				button.click();
				report.reportDoneEvent("ClickOn" + btn, "Successfully clicked on button " + btn);
			}
		} catch (RuntimeException ex) {
			report.reportDoneEvent("Click" + btn,
					btn + " is NOT clicked successfully, EXCEPTION CAUGHT : " + ex.getMessage());
		}
	}

	/**
	 * Method to click on a button(WebElement button)
	 * 
	 * @param :
	 *            Element Name
	 */
	protected void jsClickButton(WebElement button) {
		String btnText = button.getText();
		try {
			if ((new TestSettings()).getBrowser().equalsIgnoreCase("iexplore")) {
				((JavascriptExecutor) browser).executeScript("arguments[0].click();", button);
				report.reportDoneEvent("ClickOnButton" + btnText, "Successfully clicked on button " + btnText);
			} else {
				button.click();
			}
		} catch (RuntimeException ex) {
			report.reportDoneEvent("Click" + btnText,
					btnText + " is NOT clicked successfully, EXCEPTION CAUGHT : " + ex.getMessage());
		}
	}

	// *****************************************************************************************************************//
	// TextBox
	// *****************************************************************************************************************//
	/***
	 * Method to enter text in a textbox
	 * 
	 * @param :
	 *            Element Name
	 * @return :
	 * @author : Shilpa (sshank001c)
	 ***/
	public void enterText(WebElement element, String text) {
		try {
			waitForElement(element);
			if (isElementPresent(element)) {
				element.clear();
				element.sendKeys(text);
				report.reportDoneEvent("Enter :" + text, "Successfully entered " + text);
			}
		} catch (RuntimeException ex) {
			report.updateTestLog("Enter :" + text, "NOT able to enter text ", Status.SCREENSHOT);
			report.updateTestLog("Enter :" + text, "NOT able to enter text, EXCEPTION CAUGHT : " + ex.getMessage(),
					Status.FAIL);
		}
	}

	/***
	 * Method to clear text in a textbox
	 * 
	 * @param :
	 *            Element Name
	 * @return :
	 * @author : Shilpa (sshank001c)
	 ***/
	public void clearText(WebElement element) {
		try {
			waitForElement(element);
			if (isElementPresent(element)) {
				element.clear();
				report.reportDoneEvent("Clear" + element.getText().trim(),
						"Successfully cleared text in " + element.getText().trim());
			}
		} catch (RuntimeException ex) {
			report.updateTestLog("UnableToClearText", "Unable to clear text in " + element.getText().trim(),
					Status.SCREENSHOT);
			report.updateTestLog("UnableToClearText", "Unable to clear text, EXCEPTION CAUGHT : " + ex.getMessage(),
					Status.FAIL);
		}
	}

	/***
	 * Method to verify textbox is displayed
	 * 
	 * @param :
	 *            Element Name
	 * @return :
	 ***/
	public void verifyTextBox(WebElement element) {
		try {
			waitForElement(element);
			if (isElementPresent(element)) {
				report.reportPassEvent("VerifyTextBoxPresence", "TextBox is displayed");
				report.reportDoneEvent("VerifyTextBoxPresence", "TextBox is displayed");
			}
		} catch (RuntimeException ex) {
			report.updateTestLog("VerifyTextBoxPresence", "TextBox is not displayed", Status.SCREENSHOT);
			report.updateTestLog("VerifyTextBoxPresence", "TextBox is not displayed" + ex.getMessage(), Status.FAIL);
		}
	}

	// *****************************************************************************************************************//
	// Links
	// *****************************************************************************************************************//
	/***
	 * Method to click on a link(WebElement link)
	 * 
	 * @param :
	 *            Element Name
	 * @author : Shilpa (sshank001c)
	 ***/
	public void clickOnLink(WebElement link) {
		try {
			waitForElement(link);
			String linkText = link.getText();
			if (isElementPresent(link)) {
				// link.click();
				jsClick(link);
				report.reportDoneEvent("ClickOn" + linkText, "Successfully clicked on link " + linkText);
			}
		} catch (Exception ex) {
			report.reportFailEvent("ClickÖnLink", "Link is not displayed, EXCEPTION CAUGHT : " + ex.getMessage());
		}
	}

	/**
	 * Method to click on a link(WebElement link)
	 * 
	 * @param :
	 *            Element Name
	 * @author : Shilpa (sshank001c)
	 */
	protected void jsClickLink(WebElement link) {
		String linkText = link.getText();
		try {
			if ((new TestSettings()).getBrowser().equalsIgnoreCase("iexplore")) {
				((JavascriptExecutor) browser).executeScript("arguments[0].click();", link);
				report.reportDoneEvent("ClickOn" + linkText, "Successfully clicked on link " + link.getText().trim());
			} else {
				link.click();
			}
		} catch (RuntimeException ex) {
			report.reportDoneEvent("Click" + linkText,
					linkText + " is NOT clicked successfully, EXCEPTION CAUGHT : " + ex.getMessage());
		}
	}

	public void clickOnElement(WebElement element, String reportStep) {

		try {

			element.click();

			report.reportDoneEvent(reportStep, "Clicked on element successfully");

		} catch (RuntimeException ex) {

			report.reportFailEvent(reportStep,
					"Element is NOT clicked successfully, EXCEPTION CAUGHT : " + ex.getMessage());

		}

	}

	/**
	 * Method to click on a link(WebElement link)
	 * 
	 * @param :
	 *            Element Name
	 */
	protected void jsClick(WebElement link) {
		try {
			sleep(3000);
			String linkText = link.getText().trim();
			System.out.println("linkText: " + linkText);
			if ((new TestSettings()).getBrowser().equalsIgnoreCase("iexplore")) {
				((JavascriptExecutor) browser).executeScript("arguments[0].click();", link);
				// report.reportDoneEvent("ClickOn"+linkText, "Successfully
				// clicked on link "+linkText);
			} else if ((new TestSettings()).getBrowser().equalsIgnoreCase("chrome")) {
				((JavascriptExecutor) browser).executeScript("arguments[0].scrollIntoView(true);", link);
				sleep(500);
				((JavascriptExecutor) browser).executeScript("arguments[0].click();", link);

			} else {
				link.click();

			}
		} catch (RuntimeException ex) {
			report.reportFailEvent("ClickOnLink", "Unable to click on Link ->" + ex.getMessage());
		}
	}

	/**
	 * Method to sroll down to find element
	 * 
	 * @param :
	 *            Element Name
	 */

	public Object scrollElementIntoView(WebElement element) {
		return ((JavascriptExecutor) browser).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	// *****************************************************************************************************************//
	// Windows or WebPages
	// *****************************************************************************************************************//
	/**
	 * Method to validate the URL
	 * 
	 * @param pageName
	 */
	public Page navigatedUrlValidation(String pageName) {
		try {
			String navigatedUrl = browser.getCurrentUrl().trim();
			if (navigatedUrl.contains(pageName)) {
				report.reportPassEvent("navigatedUrlValidation ",
						browser.getTitle().trim() + "Page is Displayed Successfully");
			}

		} catch (RuntimeException ex) {
			report.reportFailEvent("navigatedUrlValidation", browser.getTitle().trim()
					+ " is NOT Displayed Successfully, EXCEPTION CAUGHT : " + ex.getMessage());
		}
		return this;
	}

	public void pageError() {
		sleep(1000);
		if (isElementPresent(By.xpath("//*[contains(@class,'jbar-bottom')]"))) {
			browser.findElement(By.xpath("//*[@class='jbar-cross']")).click();
			// report.reportFailEvent("validate", "Page error present");
			sleep(2000);
		}
	}

	/***
	 * Method to switch to child window
	 * 
	 * @param :
	 *            parentWindow
	 ***/
	public void navigatoToWindow(String parentWindow, String pageName) {
		try {
			sleep(5000);
			Set<String> handles = browser.getWindowHandles();
			sleep(2000);
			for (String windowHandle : handles) {
				if (!windowHandle.equals(parentWindow)) {
					browser.switchTo().window(windowHandle);
					title = browser.getTitle();
					browser.manage().window().maximize();
					sleep(5000);
					report.updateTestLog("NavigateTo" + pageName, "Successfully navigated to " + pageName + " page",
							Status.SCREENSHOT);
					break;
				}
			}
		} catch (RuntimeException ex) {
			report.updateTestLog("NavigateTo" + pageName,
					"Not able to navigate to " + pageName + " Exception caught :" + ex.getMessage(), Status.FAIL);
		}
	}

	/***
	 * Method to switch to child window
	 * 
	 * @param :
	 *            parentWindow
	 ***/
	public String navigatoToWindowReturnPageTitle(String parentWindow, String pageName) {
		String pageTitle = null;
		try {
			sleep(5000);
			Set<String> handles = browser.getWindowHandles();
			sleep(2000);
			for (String windowHandle : handles) {
				if (!windowHandle.equals(parentWindow)) {
					browser.switchTo().window(windowHandle);
					pageTitle = browser.getTitle();
					browser.manage().window().maximize();
					sleep(5000);
					report.updateTestLog("NavigateTo" + pageName, "Successfully navigated to " + pageName + " page",
							Status.SCREENSHOT);
					break;
				}
			}
		} catch (RuntimeException ex) {
			report.updateTestLog("NavigateTo" + pageName,
					"Not able to navigate to " + pageName + " Exception caught :" + ex.getMessage(), Status.FAIL);
		}
		return pageTitle;
	}

	/***
	 * Method to switch to child window
	 * 
	 * @param :
	 *            parentWindow
	 ***/
	public void closeParentWindowNavigateToChildWindow(String parentWindow, String pageName) {
		try {
			sleep(5000);
			Set<String> handles = browser.getWindowHandles();
			browser.close();
			sleep(2000);
			for (String windowHandle : handles) {
				if (!windowHandle.equals(parentWindow)) {
					browser.switchTo().window(windowHandle);
					browser.manage().window().maximize();
					sleep(5000);
					report.updateTestLog("NavigateTo" + pageName, "Successfully navigated to " + pageName + " page",
							Status.PASS);
					report.updateTestLog("NavigateTo" + pageName, "Successfully navigated to " + pageName + " page",
							Status.SCREENSHOT);
					break;
				}
			}
		} catch (RuntimeException ex) {
			report.updateTestLog("NavigateTo" + pageName,
					"Not able to navigate to " + pageName + " Exception caught :" + ex.getMessage(), Status.FAIL);
		}
	}

	/***
	 * Method to switch to parent window
	 * 
	 * @param :
	 *            parentWindow
	 ***/
	public void navigatoToParentWindow(String parentWindow) {
		browser.switchTo().window(parentWindow);
	}

	/***
	 * Method to close a webpage
	 * 
	 * @return :
	 ***/
	public void closeCurrentPage() {
		String str = browser.getTitle();
		try {
			browser.close();
			Set<String> windows = browser.getWindowHandles();
			for (String window : windows) {

				browser.switchTo().window(window);
			}
			sleep(5000);
			report.reportDoneEvent("ClosingThe" + str + "Page", str + "page is closed");
		} catch (Exception e) {
			report.reportFailEvent("ClosingThe" + str + "Page", str + "page is not closed: " + e);
		}

	}

	/***
	 * Method to navigate to default content
	 * 
	 * @param :
	 * @return :
	 * @author : Shabana (sshaik002c) Modified By :
	 ***/
	public void navigateToDefaultContent() {
		browser.switchTo().defaultContent();
	}

	// *****************************************************************************************************************//
	// Checkbox
	// *****************************************************************************************************************//

	// *****************************************************************************************************************//
	// Validations
	// *****************************************************************************************************************//

	// *****************************************************************************************************************//
	// Images
	// *****************************************************************************************************************//

	// *****************************************************************************************************************//
	// Selection from tabs or submenus
	// *****************************************************************************************************************//
	/**
	 * Method to click sub Header navigation Menu Items
	 * 
	 * @param menuHeaderItem
	 */
	protected void clickMainMenuItem(WebElement menuHeaderItem) {
		try {
			isElementPresent(menuHeaderItem,
					"verify if Main menu item " + menuHeaderItem.getText().trim() + " is displayed",
					menuHeaderItem.getText().trim() + " item is NOT displayed");
			menuHeaderItem.click();
			report.reportDoneEvent("clickMainMenuItem", menuHeaderItem.getText().trim() + " clicked successfully.");
		} catch (RuntimeException ex) {
			report.reportFailEvent("clickMainMenuItem", menuHeaderItem.getText().trim()
					+ " is NOT clicked successfully, EXCEPTION CAUGHT : " + ex.getMessage());
		}
	}

	/**
	 * Method to click sub Header navigation drop down items displayed after
	 * clicking Menu Items
	 * 
	 * @param subMenuItem
	 */
	protected void clickSubMenuItem(WebElement subMenuItem) {
		try {
			isElementPresent(subMenuItem, "verify if sub menu item " + subMenuItem.getText().trim() + " is displayed",
					subMenuItem.getText().trim() + " item is NOT displayed");
			subMenuItem.click();
			report.reportDoneEvent("clickSubMenuItem", subMenuItem.getText().trim() + " clicked successfully.");
		} catch (RuntimeException ex) {
			report.reportFailEvent("clickSubMenuItem", subMenuItem.getText().trim()
					+ " is NOT clicked successfully, EXCEPTION CAUGHT : " + ex.getMessage());
		}
	}

	/**
	 * Method to navigate to products or Bundles & Deals pages
	 * 
	 * @param menuHeaderItem
	 * @param subMenuItem
	 */
	protected void navigateTo(WebElement menuHeaderItem, WebElement subMenuItem) {
		if ((new TestSettings()).getBrowser().equalsIgnoreCase("iexplore")) {
			Actions builder = new Actions(browser);
			builder.moveToElement(menuHeaderItem).build().perform();
			((JavascriptExecutor) browser).executeScript("arguments[0].click();", subMenuItem);
		} else {
			clickMainMenuItem(menuHeaderItem);
			sleep(1000);
			clickSubMenuItem(subMenuItem);
		}
	}

	/**
	 * Method to navigate to products or Bundles & Deals pages
	 * 
	 * @param menuHeaderItem
	 * @param subMenuItem
	 */
	protected void doubleClick(WebElement element) {
		try {
			if ((new TestSettings()).getBrowser().equalsIgnoreCase("iexplore")) {
				Actions builder = new Actions(browser);
				builder.doubleClick(element).build().perform();

				report.reportDoneEvent("ClickOn" + element.getText().trim(),
						"Successfully clicked on " + element.getText().trim());
			} else {
				// element.click();
				Actions builder = new Actions(browser);
				builder.doubleClick(element).build().perform();
				report.reportDoneEvent("ClickOn" + element.getText().trim(),
						"Successfully clicked on " + element.getText().trim());
			}
		}

		catch (RuntimeException ex) {
			report.reportDoneEvent("Click" + element.getText().trim(),
					element.getText().trim() + " is NOT clicked successfully, EXCEPTION CAUGHT : " + ex.getMessage());
		}
	}

	// *****************************************************************************************************************//
	// Alert pop ups
	// *****************************************************************************************************************//
	/***
	 * Method to accept and close alert and return the text within the alert
	 * 
	 * @param :
	 * @return :
	 * @author : Shravanth (sarago001c) Modified By :
	 ***/
	public String closeAlertAndReturnText() {
		String alertMessage = null;
		try {
			WebDriverWait wait = new WebDriverWait(browser, 40);
			wait.until(ExpectedConditions.alertIsPresent());
			System.out.print("\nAlert is displayed...");
			Alert alert = browser.switchTo().alert();
			alertMessage = alert.getText();
			System.out.print("\nMessage: " + alertMessage);
			alert.accept();
			report.reportPassEvent("alertMessage", "alertMessage displayed is->" + alertMessage);
		} catch (Exception Ex) {
			report.reportFailEvent("Exception Caught", "Message is->" + Ex.getMessage());
		}
		return alertMessage;
	}

	/***
	 * Method to accept and close alert and return the text within the alert
	 * 
	 * @param :
	 * @return :
	 * @author : Shravanth (sarago001c) Modified By :
	 ***/
	public String closeAlertAndReturnTextshort() {
		String alertMessage = null;
		try {
			WebDriverWait wait = new WebDriverWait(browser, 5);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = browser.switchTo().alert();
			alertMessage = alert.getText();
			report.reportPassEvent("alertMessage", "alertMessage displayed is->" + alertMessage);
			alert.accept();
		} catch (Exception Ex) {

		}
		return alertMessage;
	}

	/***
	 * Method to accept and close alert and return the text within the alert
	 * 
	 * @param :
	 * @return :
	 * @author : Shravanth (sarago001c) Modified By :
	 ***/
	public void closeAlert() {

		try {
			WebDriverWait wait = new WebDriverWait(browser, 40);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = browser.switchTo().alert();
			alert.accept();
		} catch (Exception Ex) {
			// do nothing
		}
	}

	/***
	 * Method to accept and close alert and compare returned text within the
	 * alert
	 * 
	 * @param :
	 * @return :
	 * @author : Shilpa(266022) Modified By :
	 ***/
	public String closeAlertAndCompareReturnText(String expectedValue) {
		String alertMessage = null;
		WebDriverWait wait = new WebDriverWait(browser, 20);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = browser.switchTo().alert();
		alertMessage = alert.getText();
		if (expectedValue.contains(alertMessage)) {
			report.reportPassEvent("alertMessage", "alertMessage is displayed as expected :" + alertMessage);
		} else {
			report.reportFailEvent("alertMessage", "alertMessage is not displayed as expected :" + alertMessage);
		}
		alert.accept();
		return alertMessage;
	}

	// *****************************************************************************************************************//
	// waits
	// *****************************************************************************************************************//

	/**
	 * Method to wait for element to load in the page
	 * 
	 * @param WebElement
	 */
	protected Boolean waitForElement(By by) {
		try {
			new WebDriverWait(browser, 90).until(ExpectedConditions.presenceOfElementLocated(by));

		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	protected Boolean shortWaitForElement(WebElement we) {
		try {
			new WebDriverWait(browser, 10).until(ExpectedConditions.visibilityOf(we));

		} catch (RuntimeException ex) {
			return false;
		}
		return true;
	}

	protected Boolean waitForElement(WebElement we, int sec) {
		try {
			new WebDriverWait(browser, sec).until(ExpectedConditions.visibilityOf(we));

		} catch (RuntimeException ex) {
			return false;
		}
		return true;
	}

	protected Boolean miniWaitForElement(WebElement we) {
		try {
			new WebDriverWait(browser, 5).until(ExpectedConditions.visibilityOf(we));

		} catch (RuntimeException ex) {
			return false;
		}
		return true;
	}

	protected Boolean WaitandSwitchToFrame(WebElement we) {
		try {
			new WebDriverWait(browser, 60).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(we));

		} catch (RuntimeException ex) {
			return false;
		}
		return true;
	}

	protected Boolean WaitandSwitchToFrame(WebElement we, int sec) {
		try {
			new WebDriverWait(browser, sec).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(we));

		} catch (RuntimeException ex) {
			return false;
		}
		return true;
	}

	protected Boolean ShortWaitandSwitchToFrame(WebElement we) {
		try {
			new WebDriverWait(browser, 5).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(we));

		} catch (RuntimeException ex) {
			return false;
		}
		return true;
	}

	// frameToBeAvailableAndSwitchToIt
	/**
	 * Method to wait for element to load in the page
	 * 
	 * @param WebElement
	 */
	protected Boolean normalWaitForElement(By by) {
		try {
			new WebDriverWait(browser, 40).until(ExpectedConditions.presenceOfElementLocated(by));

		} catch (RuntimeException ex) {
			return false;
		}
		return true;
	}

	/**
	 * Method to wait for element to load in the page
	 * 
	 * @param WebElement
	 */
	protected Boolean shortWaitForElement(By by) {
		try {
			new WebDriverWait(browser, 20).until(ExpectedConditions.presenceOfElementLocated(by));

		} catch (RuntimeException ex) {
			return false;
		}
		return true;
	}

	protected void waitForNewSecondWindow() {
		try {
			int count = 0;
			Set<String> windows = browser.getWindowHandles();
			while (count < 40) {
				windows = browser.getWindowHandles();
				if (windows.size() > 1) {
					report.reportPassEvent("VerifyNewWindowOpened", "New Window opened successfully");
					break;
				} else {
					sleep(2000);
					count++;
				}
			}
			if (count >= 40) {
				report.reportFailEvent("VerifyNewWindowOpened", "New Window not opened");
			}
		} catch (Exception Ex) {
			report.reportFailEvent("VerifyNewWindowOpened", "New Window not opened");
		}
	}

	/**
	 * Method to wait for element to load in the page
	 * 
	 * @param WebElement
	 */

	protected Boolean waitForElement(WebElement we) {
		try {
			new WebDriverWait(browser, 60).until(ExpectedConditions.elementToBeClickable(we));
			// new WebDriverWait(browser, 60).until(ExpectedConditions.fram
			return true;
		} catch (RuntimeException ex) {
			return false;
		}
		
	}

	protected Boolean waitForVisibilityOfElement(WebElement we) {
		try {
			new WebDriverWait(browser, 60).until(ExpectedConditions.visibilityOfElementLocated((By) we));
			// new WebDriverWait(browser, 60).until(ExpectedConditions.fram
			return true;
		} catch (RuntimeException ex) {
			return false;
		}
	}

	protected void jsClickWE(WebElement we) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) browser;
			executor.executeScript("arguments[0].click();", we);
		} catch (RuntimeException ex) {
			report.reportDoneEvent("DropDownClicked", "Failed");
		}
	}

	/***
	 * Method to search customer with the invalid Combinations
	 * 
	 * @param :
	 * 			@return : @author : Kumar (kpokha001c) @throws
	 ***/
	protected void isPresent(WebElement we) {
		WebDriverWait wait = new WebDriverWait(browser, 10);
		try {
			wait.until(ExpectedConditions.visibilityOf(we));
			report.reportPassEvent("HomePage::" + we.getAttribute("innerHTML") + " Object Verification",
					"The object " + we.getAttribute("innerHTML") + " is present.");
		} catch (NoSuchElementException nse) {
			report.reportDoneEvent("HomePage::" + we.getAttribute("innerHTML") + " Object Verification",
					"The object " + we.getAttribute("innerHTML") + " is NOT present.");
			// System.out.println("" + we + " No such element");
		} catch (TimeoutException toe) {
			report.reportDoneEvent("HomePage::" + we.getAttribute("innerHTML") + " Object Verification",
					"The object " + we.getAttribute("innerHTML") + " is NOT present.");
			// System.out.println("" + we + " TimeOut");
		}

	}

	/**
	 * Method to wait for element to load in the page
	 * 
	 * @param WebElement
	 * @author Kumar (kpokha001c)
	 */

	protected void isElementClickable(WebElement we) {
		WebDriverWait wait = new WebDriverWait(browser, 10);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(we));
			report.reportPassEvent("HomePage::" + we.toString() + " Object Verification",
					"The object " + we.getAttribute("innerHTML") + " is present.");
		} catch (NoSuchElementException nse) {
			report.reportDoneEvent("HomePage::" + we.getAttribute("innerHTML") + " Object Verification",
					"The object " + we.getAttribute("innerHTML") + " is NOT present.");
		} catch (TimeoutException toe) {
			report.reportDoneEvent("HomePage::" + we.getAttribute("innerHTML") + " Object Verification",
					"The object " + we.getAttribute("innerHTML") + " is NOT present.");
		}
	}

	protected Boolean longwaitForElement(By by) {
		try {
			int count = 0;
			while (count < 25) {
				new WebDriverWait(browser, 50).until(ExpectedConditions.presenceOfElementLocated(by));
				if (isElementPresent(by)) {
					break;
				}
			}
			return true;
		} catch (UnhandledAlertException ex) {
			report.reportFailEvent("AlertPresent", "Message is->" + ex.getAlertText());
			return false;
		} catch (Exception Ex) {
			return false;
		}
	}

	/**
	 * Method to wait for Alert present in the page
	 * 
	 * @param
	 */

	protected Boolean waitForAlert() {
		try {
			new WebDriverWait(browser, 60).until(ExpectedConditions.alertIsPresent());
			return true;
		} catch (Exception Ex) {
			return false;
		}
	}

	/**
	 * Method to check timed out exception
	 * 
	 * @param Nil
	 */

	protected void waitForApplicationSessionTimeOut() {
		try {
			sleep(1800);
			new WebDriverWait(browser, 300).until(ExpectedConditions.elementToBeClickable(By.cssSelector(
					"#overlay-service-switch > div.overlay-body > div.overlay-content > div.overlay-content-bottom")));
			report.reportDoneEvent("waitForApplicationSessionTimeOut", "SessionTimeOut Overlay displayed successfully");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to wait for the page to load
	 * 
	 * @param nil
	 */
	protected void waitForApplicationToProcess() {
		try {
			(new WebDriverWait(browser, 180))
					.until(ExpectedConditions.invisibilityOfElementLocated(By.id("overlay-waiting")));
			waitForPageLoad();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to check Element id displayed in the page
	 * 
	 * @param ElementName
	 */

	protected boolean isElementDisplayed(WebElement we) {
		try {
			new WebDriverWait(browser, 50).until(ExpectedConditions.elementToBeClickable(we));

		} catch (RuntimeException ex) {
			return false;
		}
		return true;
	}
	
	protected boolean isElementDisplayed(WebElement we, int sec) {
		try {
			new WebDriverWait(browser, sec).until(ExpectedConditions.elementToBeClickable(we));

		} catch (RuntimeException ex) {
			return false;
		}
		return true;
	}

	// *****************************************************************************************************************//
	// General
	// *****************************************************************************************************************//
	/***
	 * Method to check if element is enabled(element_name)
	 * 
	 * @param :
	 *            Element Name
	 * @return :
	 * @author : Shabana (sshaik002c)
	 ***/
	public void isElementEnabled(WebElement element) {
		try {
			if (isElementPresent(element)) {
				if (element.isEnabled()) {
					report.updateTestLog("IsElementEnabled", element.getText() + "is enabled", Status.SCREENSHOT);
					report.reportPassEvent("IsElementEnabled", element.getText() + "is enabled");
				} else {
					report.updateTestLog("IsElementEnabled", element.getText() + "is not enabled", Status.SCREENSHOT);
					report.updateTestLog("IsElementEnabled", element.getText() + "is not enabled", Status.FAIL);
				}

			}
		} catch (RuntimeException ex) {

			report.updateTestLog("ElementIsNotAvailable", element.getText() + "is not available", Status.FAIL);
		}
	}

	/***
	 * Method to check if element is enabled(element_name) with a boolean return
	 * 
	 * @param :
	 *            Element Name
	 * @return : Boolean
	 * @author : Shabana (sshaik002c) Parity for E360: Indu Murthy (imurth002c)
	 ***/
	public boolean isElementEnabledwithBooleanReturn(WebElement element) {
		waitForElement(element);
		if (element.isEnabled()) {
			report.reportDoneEvent("ElementIsEnabled", element.getText() + "is enabled");
			return true;
		} else {
			report.reportDoneEvent("ElementIsNotEnabled", element.getText() + "is not enabled");
			return false;
		}

	}

	/***
	 * Method to check if element is enabled(element_name)
	 * 
	 * @param :
	 *            Element Name
	 * @return :
	 * @author : Shabana (sshaik002c) Modified By :
	 ***/
	public void isTextBoxEnabled(WebElement element, String elementName) {
		try {
			if (isElementPresent(element)) {
				if (element.isEnabled()) {
					report.updateTestLog("IsElementEnabled", elementName + "is enabled", Status.SCREENSHOT);
					report.reportPassEvent("IsElementEnabled", elementName + "is enabled");
				} else {
					report.updateTestLog("IsElementEnabled", elementName + "is not enabled", Status.SCREENSHOT);
					report.updateTestLog("IsElementEnabled", elementName + "is not enabled", Status.FAIL);
				}

			}
		} catch (RuntimeException ex) {

			report.updateTestLog("ElementIsNotAvailable", elementName + "is not available", Status.FAIL);
		}
	}

	/***
	 * Method to check if element is disabled(element_name)
	 * 
	 * @param :
	 *            Element Name
	 * @return :
	 * @author : Shabana (sshaik002c) Modified By :
	 ***/
	public void isElementDisabled(WebElement element) {
		try {
			if (isElementPresent(element)) {
				if (element.isEnabled()) {
					report.updateTestLog("isElementDisabled", element.getText() + "is not disabled", Status.SCREENSHOT);
					report.updateTestLog("isElementDisabled", element.getText() + "is not disabled", Status.FAIL);
				} else {
					report.updateTestLog("isElementDisabled", element.getText() + "is disabled", Status.SCREENSHOT);
					report.reportPassEvent("isElementDisabled", element.getText() + "is disabled");
				}

			}
		} catch (RuntimeException ex) {
			report.updateTestLog("ElementIsNotAvailable", element.getText() + "is not available", Status.FAIL);
		}
	}

	/***
	 * Method to check if textbox is disabled(element_name)
	 * 
	 * @param :
	 *            Element Name
	 * @return :
	 * @author : Shabana (sshaik002c) Modified By :
	 ***/
	public void isTextBoxDisabled(WebElement element, String elementName) {
		try {
			if (isElementPresent(element)) {
				if (element.isEnabled()) {
					report.updateTestLog("isElementDisabled", elementName + "is not disabled", Status.SCREENSHOT);
					report.updateTestLog("isElementDisabled", elementName + "is not disabled", Status.FAIL);
				} else {
					report.updateTestLog("isElementDisabled", elementName + "is disabled", Status.SCREENSHOT);
					report.reportPassEvent("isElementDisabled", elementName + "is disabled");
				}

			}
		} catch (RuntimeException ex) {
			report.updateTestLog("ElementIsNotAvailable", elementName + "is not available", Status.FAIL);
		}
	}

	// *****************************************************************************************************************//
	// *****************************************************************************************************************//
	// *****************************************************************************************************************//
	// *****************************************************************************************************************//

	/**
	 * verify if section Headers are displayed in the Footer
	 * 
	 * @param actualHeaderName
	 * @param expectedHeaderName
	 * @param stepName
	 */
	protected void verifySectionHeader(String actualHeaderName, String expectedHeaderName, String stepName) {
		try {
			Assert.assertTrue(actualHeaderName.equalsIgnoreCase(expectedHeaderName));
			report.reportPassEvent(stepName, expectedHeaderName + " is displayed as expected");
		} catch (RuntimeException ex) {
			report.reportFailEvent(stepName, expectedHeaderName + " is NOT displayed as expected" + ex.getMessage());
		}
	}

	/**
	 * Method to Check if a particular WebElement is displayed in the Page and
	 * return true if displayed or False if not
	 * 
	 * @param element
	 * @return boolean
	 * @author : Shilpa
	 */
	protected boolean isElementDisplayedInThePage(WebElement element) {
		try {
			new WebDriverWait(browser, 20).until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		} catch (Exception se) {
			return false;
		}
	}

	/**
	 * Wait for all the elements to be present under a given ID value
	 * 
	 * @param idvalue
	 */
	protected void waitForElementsLoadById(String idvalue) {
		(new WebDriverWait(browser, 180)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(idvalue)));
	}

	/**
	 * Wait for a clickable element to be present under a given className
	 * 
	 * @param className
	 */
	protected void waitForElementToBeClickableByclassName(String className) {
		new WebDriverWait(browser, 180).until(ExpectedConditions.elementToBeClickable(By.className(className)));
	}

	/**
	 * method to make a thread sleep for customized time in milliseconds
	 * 
	 * @param milliseconds
	 * @author : Shilpa
	 */
	protected void sleep(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * method to wait till the element displayed
	 * 
	 * @param by
	 * @author : Shilpa
	 */

	protected Boolean waitForElementDisplay(By by) {
		int i = 0;
		while (true) {
			if (isElementPresent(by)) {
				return true;
			} else {
				i = i + 1;
				sleep(3000);
			}
			if (i == 20) {
				return false;
			}
		}
	}

	/***
	 * Method to click on all pdfLinks in a page
	 * 
	 * @param :
	 *            Element Name
	 * @return :
	 * @author : Shravanth Modified By :Shabana (sshaik002c)
	 ***/
	public void checkLinks(WebElement element) throws Exception {
		List<WebElement> all_links_webpage = element.findElements(By.tagName("a"));

		List<String> actualExternalLinks = new ArrayList<String>();
		List<String> actualFailedExternalLinks = new ArrayList<String>();
		for (int i = 0; i < all_links_webpage.size(); i++) {

			actualExternalLinks.add(all_links_webpage.get(i).getText());

		}

		if (actualExternalLinks.isEmpty()) {
			report.reportPassEvent("Clicking on external links ", "No external links found");
		}

		else {
			for (int i = 0; i < actualExternalLinks.size(); i++) {
				System.out.println("clicking link number: " + (i + 1) + " with text: " + actualExternalLinks.get(i));
				// searchBox.click();

				browser.findElement(By.linkText(actualExternalLinks.get(i))).click();
				// System.out.println("clicking on links");
				sleep(5000);
				String parentWindow = browser.getWindowHandle();
				Set<String> handles = browser.getWindowHandles();
				if (handles.size() == 1) {
					actualFailedExternalLinks.add(actualExternalLinks.get(i));
					// System.out.println("Link failed to open with link text
					// "+actualExternalLinks.get(i));
					report.updateTestLog("Check External Link",
							"Link not working fine with Link Text:" + actualExternalLinks.get(i), Status.SCREENSHOT);
					browser.switchTo().window(parentWindow);
					continue;
				} else {
					for (String windowHandle : handles) {
						if (!windowHandle.equals(parentWindow)) {
							browser.switchTo().window(windowHandle);
							browser.manage().window().maximize();
							// boolean result = checkTitle();
							// if (result) {

							System.out.println("Link working correctly");
							report.updateTestLog("Click external link", "link number " + (i + 1) + "with link text "
									+ actualExternalLinks.get(i) + " working successfully", Status.PASS);
							browser.close();
							browser.switchTo().window(parentWindow);

						}

						/*
						 * } else {
						 * actualFailedExternalLinks.add(actualExternalLinks.get
						 * (i)); report.updateTestLog("Check External Link",
						 * "Link not working fine with Link Text:"
						 * +actualExternalLinks.get(i), Status.SCREENSHOT);
						 * //System.out.println("Link not working");
						 * browser.close();
						 * browser.switchTo().window(parentWindow); continue; }
						 */
					}

				}

			}

		}

		String FailedLinks = "";
		if (actualFailedExternalLinks.size() > 0) {
			for (int i = 0; i < actualFailedExternalLinks.size(); i++) {
				FailedLinks = FailedLinks + actualFailedExternalLinks.get(i) + ";";
			}
			report.reportFailEvent("External Links failed", "Links Failed are:" + FailedLinks);
		}

		// return new CustomerPage(browser,report);

	}

	// This method is used check if a specified button is available or not.
	// <WebElement> may be user defined element or webelement path derieved from
	// browser. <Element> is actual name of the button.

	/***
	 * Method to Alter Today's date to specified date
	 * 
	 * @param :
	 *            Element Name
	 * @return :
	 * @author : Omkar (oraghu001c) Modified By :
	 ***/
	public Date getAlteredDate(Integer NoOfDays) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, NoOfDays);
		return cal.getTime();
	}

	/***
	 * Method to get current time in minutes
	 * 
	 * @param :
	 *            Element Name
	 * @return :
	 * @author : Shabana Modified By :
	 ***/
	public int getCurrentTimeInMin() {
		String time = new SimpleDateFormat("HH:mm").format(new Date());
		String[] splitTime = time.split(":");
		int hr = Integer.parseInt(splitTime[0]);
		int mn = Integer.parseInt(splitTime[1].substring(0, 2));
		if (hr > 12) {
			hr = hr - 12;
		}
		int timStamp = (hr * 60) + mn;
		return timStamp;
	}

	/***
	 * Method to get current time in minutes
	 * 
	 * @param :
	 *            Element Name
	 * @return :
	 * @author : Shabana Modified By :
	 ***/
	public int getTimeInMin(String time) {
		// String time=new SimpleDateFormat("HH:mm").format(new Date());
		String[] splitTime = time.split(":");
		int hr = Integer.parseInt(splitTime[0]);
		int mn = Integer.parseInt(splitTime[1].substring(0, 2));
		if (hr > 12) {
			hr = hr - 12;
		}
		int timStamp = (hr * 60) + mn;
		return timStamp;
	}

	/***
	 * Method to get current time in minutes
	 * 
	 * @param :
	 *            Element Name
	 * @return :
	 * @author : Shabana Modified By :
	 ***/
	public String getTimeIntwelveHrFormat(String time) {
		// String time=new SimpleDateFormat("HH:mm").format(new Date());
		String[] splitTime = time.split(":");
		int hr = Integer.parseInt(splitTime[0]);
		int mn = Integer.parseInt(splitTime[1].substring(0, 2));
		if (hr > 12) {
			hr = hr - 12;
		}
		String timStamp = Integer.toString(hr) + ":" + Integer.toString(mn);
		return timStamp;
	}

	/***
	 * Method to Switch window and return URL of window
	 * 
	 * @param :
	 *            Element Name
	 * @return : Browser URL
	 * @author : Omkar (oraghu001c) Modified By :
	 ***/
	public String switchtowindow(String window) {
		browser.switchTo().window(window);
		return browser.getCurrentUrl();
	}

	/***
	 * Method to hover on a link(WebElement link)
	 * 
	 * @param :
	 *            Element Name
	 * @author : Shilpa (sshank001c)
	 ***/
	public void hooverOnLink(WebElement link) {
		String linkText = link.getText();
		try {
			waitForElement(link);
			if (isElementPresent(link)) {
				Actions action = new Actions(browser);
				action.moveToElement(link);
				action.perform();
				this.sleep(2);
				report.updateTestLog("HoverOn" + linkText, "Successfully hovered on link " + link.getText().trim(),
						Status.PASS);
				report.updateTestLog("HoverOn" + linkText, "Successfully hovered on link " + link.getText().trim(),
						Status.SCREENSHOT);
			}
		} catch (RuntimeException ex) {
			report.reportDoneEvent("HoverOn" + linkText,
					linkText + " is NOT hovered successfully, EXCEPTION CAUGHT : " + ex.getMessage());
		}
	}

	/***
	 * Method to scroll (WebElement link)
	 * 
	 * @param :
	 *            Element Name
	 * @author : Shilpa (sshank001c)
	 ***/

	public void scroll(WebElement link, int height, int width) {
		JavascriptExecutor js = (JavascriptExecutor) browser;
		// Ideally give (250,750)
		js.executeScript("javascript:window.scrollBy(height,width)", link);
	}

	public void scrollToElementandclick(WebElement we) {
		Actions builder = new Actions(browser);
		builder.moveToElement(we).click().perform();
		((JavascriptExecutor) browser).executeScript("arguments[0].scrollIntoView(true);", we);
	}

	private void beforePageLoad() {
		JavascriptExecutor js = (JavascriptExecutor) browser;
		js.executeScript("document.mpPageReloaded='notYet';");
	}

	public void waitforPageLoadComplete() {
		WebDriverWait wait = new WebDriverWait(browser, 60);

		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver wdriver) {
				return ((JavascriptExecutor) browser).executeScript("return document.readyState").equals("complete");
			}
		});
	}
	// Actions builder = new Actions(browser);
	// builder.moveToElement(we).click().perform();
	/*
	 * new WebDriverWait(browser, 30).until(((JavascriptExecutor)
	 * browser).executeScript("return document.readyState").equals("complete"));
	 * //<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript(
	 * "return document.readyState").equals("complete")); //new
	 * WebDriverWait(browser,
	 * 30).until(ExpectedConditions.elementToBeClickable(by));
	 * ((JavascriptExecutor)
	 * browser).executeScript("arguments[0].scrollIntoView(true);", we);
	 */

	/**
	 * Check if the element is present in the page
	 * 
	 * @param element
	 *            WebElement need to check
	 * @return True if present
	 * @author : Shilpa (sshank001c)
	 */
	protected void isElementPresent_Report(WebElement element) {
		try {
			sleep(5000);
			waitForElement(element);
			element.isDisplayed();
			report.reportPassEvent("CheckElementPresent", element.getText() + ": is present");
		} catch (Exception ex) {
			report.reportFailEvent("" + ex.getMessage(), "Element is not present");
		}
	}

	/***
	 * Method to getSizeSelectCombo
	 * 
	 * @param :
	 *            Details
	 * @return :
	 * @author : Shilpa (sshank001c) Modified By :
	 * @throws InterruptedException
	 ***/

	public int getSizeSelectCombo(WebElement selObj) {

		Select sel = new Select(selObj);
		List<WebElement> selSize = sel.getOptions();
		int size = selSize.size();
		return size;
	}

	/***
	 * Method to Verify the selectComboOptions
	 * 
	 * @param :
	 * @return :
	 * @author : Shilpa (sshank001c) Modified By :
	 * @throws InterruptedException
	 ***/

	public void selectComboOptions(WebElement selObj, String[] expOption) {
		try {
			Select sel = new Select(selObj);
			String Flag = null;
			Flag = "true";
			for (int i = 0; i < sel.getOptions().size(); i++) {
				System.out.println(sel.getOptions().get(i).getText());
				if (sel.getOptions().get(i).getText().trim().equalsIgnoreCase(expOption[i])) {
					report.updateTestLog("VerifyDropdown :" + expOption[i], "Dropdown have the value ->" + expOption[i],
							Status.PASS);
					Flag = "true";
				} else {
					Flag = "False";
					report.reportDoneEvent("VerifyDropdown :" + expOption[i],
							"Dropdown does not have the value ->" + expOption[i]);
				}
			}

			if (Flag == "False") {
				report.reportDoneEvent("selectComboOptions :", "Verification Failed some values missing");
			}
		} catch (NoSuchElementException ex) {
			report.reportFailEvent("selectComboOptions :", ex.getMessage());

		}
	}

	/**
	 * To get the warning message and compare it with the actual message and
	 * close the warning
	 * 
	 * @param warningMsg
	 *            WebElement need to check and compare with String expectedMsg
	 * @return True if present
	 * @author : Namitha (Nnaray003c)
	 */
	protected void closeWarningAndCompareReturnText(WebElement warningMsg, String expectedMsg,
			WebElement warningMsgClose) {
		try {
			String actualWarningMsg = null;
			waitForElement(warningMsg);
			warningMsg.isDisplayed();
			actualWarningMsg = warningMsg.getText();
			if (actualWarningMsg.contains(expectedMsg)) {
				report.reportPassEvent("WarningMsgDisplayedAsExpected",
						warningMsg.getText() + "Msg Displayed As Expected");
				report.updateTestLog("WarningMsgDisplayedAsExpected",
						warningMsg.getText() + "Msg Displayed As Expected", Status.SCREENSHOT);
			} else {
				report.updateTestLog("WarningMsgNotDisplayedAsExpected",
						warningMsg.getText() + "Msg Not Displayed As Expected", Status.FAIL);
			}
			warningMsgClose.click();

		} catch (NoSuchElementException ex) {
			report.reportFailEvent("WarningMsg_NotDisplayed",
					warningMsg.getText() + ": is not present" + ex.getMessage());

		}
	}

	/***
	 * Method to close tool kit
	 * 
	 * @param :
	 * @return :
	 * @author : Shabana Shaik(sshaik002c) Modified By :
	 ***/
	public CustomerTabPageCM closeToolkit() {
		try {
			String parentWindow = browser.getWindowHandle();
			Set<String> handles = browser.getWindowHandles();
			browser.close();
			for (String windowHandle : handles) {
				if (!windowHandle.equals(parentWindow)) {
					browser.switchTo().window(windowHandle);
					browser.manage().window().maximize();
					sleep(4000);
					report.updateTestLog("NavigateToCustomerPage", "Successfully navigated to customer page",
							Status.SCREENSHOT);
					break;
				}
			}
		} catch (RuntimeException ex) {
			report.updateTestLog("NavigateToCustomerPage",
					"Not able to navigate to customer page, exception caught :" + ex.getMessage(), Status.FAIL);
		}
		return new CustomerTabPageCM(browser, report);
	}

	/***
	 * Method to close back end service name
	 * 
	 * @param :
	 * @return :
	 * @author : Shabana Shaik(sshaik002c) Modified By :
	 ***/
	public String closeBackEndServiceFrame() {
		String message = null;
		if (isElementPresent(By.tagName("iframe"))) {
			String frameName = browser.findElement(By.tagName("iframe")).getAttribute("name");
			System.out.println(frameName);
			browser.switchTo().frame(frameName);
			waitForElement(By.id("lblMessage"));
			message = browser.findElement(By.id("lblMessage")).getText();
			System.out.println(message);
			do {
				browser.findElement(By.id("Button2")).sendKeys(Keys.ENTER);
			} while (isElementPresent(By.xpath("//iframe[@name='" + frameName + "']")));

			// browser.switchTo().frame(browser.findElement(By.xpath("//iframe[contains(@name,'RadWindowManager')]")));
			// browser.findElement(By.xpath("//*[@name='CloseBtn']")).click();
			report.reportDoneEvent("CloseBackEndServicePop", "Successfully closed back end service pop up");
			browser.switchTo().defaultContent();
		}

		return message;
	}

	public void closeMessageFromBackEndServiceFrame() {
		String message = null;
		if (isElementPresent(By.tagName("iframe"))) {
			String frameName = browser.findElement(By.tagName("iframe")).getAttribute("name");
			System.out.println(frameName);
			browser.switchTo().frame(frameName);
			sleep(2000);
			// jsClick(browser.findElement(By.xpath("//*[contains(@id,'RadWindowManager')]/table/tbody/tr[1]/td[2]/table/tbody/tr/td[3]/ul/li/a")));
			browser.findElement(By.xpath("//*[text()='Close']")).click();
		}
	}

	/***
	 * Method to close frame with a message and check the message
	 * 
	 * @param :
	 * @return :
	 * @author : Shravanth(sarago001) Modified By :
	 ***/
	public void closeBackEndServiceFrameAndCheckMessage(String expMessage) {
		String message = null;
		String frameName = browser.findElement(By.tagName("iframe")).getAttribute("name");
		System.out.println(frameName);
		browser.switchTo().frame(frameName);
		waitForElement(By.id("lblMessage"));
		message = browser.findElement(By.id("lblMessage")).getText();
		System.out.println(message);
		if (message.contains(expMessage)) {
			report.reportPassEvent("Check Message", "Messsage is as expected: actual Message:" + message);
			report.updateTestLog("Check Message", "Messsage is as expected: actual Message:" + message,
					Status.SCREENSHOT);
		} else {
			report.reportFailEvent("Check Message", "Messsage is not as expected: actual Message:" + message);
		}
		do {
			jsClick(browser.findElement(By.id("Button2")));
		} while (isElementPresent(By.xpath("//iframe[@name='" + frameName + "']")));

		// browser.switchTo().frame(browser.findElement(By.xpath("//iframe[contains(@name,'RadWindowManager')]")));
		// browser.switchTo().frame(0);
		// browser.findElement(By.xpath("//*[@name='rwPopupButton']")).click();
		report.reportDoneEvent("CloseBackEndServicePop", "Successfully closed back end service pop up");
		browser.switchTo().defaultContent();

	}

	/***
	 * Method to close frame with a message and check the message
	 * 
	 * @param :
	 * @return :
	 * @author : Shravanth(sarago001) Modified By :
	 ***/
	public void closeBackEndServiceFrameAndCheckMessage1(String expMessage) {
		String message = null;
		String frameName = browser.findElement(By.tagName("iframe")).getAttribute("name");
		System.out.println(frameName);
		browser.switchTo().frame(frameName);
		waitForElement(By.id("lblMessagePanel"));
		message = browser.findElement(By.id("lblMessagePanel")).getText();
		System.out.println(message);
		if (message.contains(expMessage)) {
			report.reportPassEvent("Check Message", "Messsage is as expected: actual Message:" + message);
			report.updateTestLog("Check Message", "Messsage is as expected: actual Message:" + message,
					Status.SCREENSHOT);
		} else {
			report.reportFailEvent("Check Message", "Messsage is not as expected: actual Message:" + message);
		}
		do {
			jsClick(browser.findElement(By.id("Button2")));
		} while (isElementPresent(By.xpath("//iframe[@name='" + frameName + "']")));

		// browser.switchTo().frame(browser.findElement(By.xpath("//iframe[contains(@name,'RadWindowManager')]")));
		// browser.switchTo().frame(0);
		// browser.findElement(By.xpath("//*[@name='rwPopupButton']")).click();
		report.reportDoneEvent("CloseBackEndServicePop", "Successfully closed back end service pop up");
		browser.switchTo().defaultContent();

	}

	/***
	 * Method to Enter text in the field specified
	 * 
	 * @param :
	 * @return :
	 * @author : Shravanth PAV (sarago001) Modified By :
	 ***/
	public void sendKeys(WebElement we, String fieldName, String textToEnter) {
		try {
			waitForElement(we);
		} catch (RuntimeException re) {
			report.reportFailEvent("Wait For Element " + fieldName, "Element failed to Load" + re.getMessage());
		}
		try {
			we.sendKeys(textToEnter);
			report.reportDoneEvent("Enter data to field:" + fieldName, "Data entered:" + textToEnter);
			report.updateTestLog("Enter data to field:" + fieldName, "Data entered:" + textToEnter, Status.SCREENSHOT);
		} catch (RuntimeException re) {
			report.reportFailEvent("Enter data to field:" + fieldName, "Failed entering data." + re.getMessage());
		}

	}

	/***
	 * Method to check if element is disabled(element_name)
	 * 
	 * @param :
	 *            Element Name
	 * @return :
	 * @author : Namitha (nnaray003c) Modified By :
	 ***/
	public void isElementDisabledNew(WebElement element) {
		try {
			if (isElementPresent(element)) {
				if (element.isEnabled()) {
					report.updateTestLog("isElementDisabled", element.getText() + "is not disabled", Status.FAIL);
				} else {
					report.reportPassEvent("isElementDisabled", element.getText() + "is disabled");
				}

			}
		} catch (RuntimeException ex) {
			report.updateTestLog("ElementIsNotAvailable", element.getText() + "is not available", Status.FAIL);
		}
	}

	/***
	 * Method to check if element is present(element_name)
	 * 
	 * @param :
	 *            Element Name
	 * @return :
	 * @author : Shravanth (sarago001c) Modified By :
	 ***/
	public boolean checkElementPresent(By by) {
		try {
			waitForElement(by);
			return true;
		} catch (RuntimeException ex) {
			return false;
		}
	}

	/***
	 * Method to click an element to open another element
	 * 
	 * @param :
	 *            Element Name
	 * @return :
	 * @author : Shravanth (sarago001c) Modified By :
	 ***/
	public void clickToOpen(WebElement element, By elementToBeVisible) {
		try {
			waitForElement(element);
			int count = 0;
			do {
				if (count > 50)
					break;
				if (count % 2 == 0) {
					element.click();
					sleep(2500);
				} else {
					jsClick(element);
					sleep(2500);
				}
				count = count + 1;
			} while (!isElementPresent(elementToBeVisible));
		} catch (RuntimeException ex) {
			report.reportFailEvent("Click on element", "Cannot click on element");
		}
	}

	/***
	 * Method to click and close an element
	 * 
	 * @param :
	 *            Element Name
	 * @return :
	 * @author : Shravanth (sarago001c) Modified By :
	 ***/
	public void clickToClose(WebElement element) {
		try {
			waitForElement(element);
			int count = 0;
			while (isElementPresent(element)) {
				if (count > 50) {
					break;
				} else {
					if (count % 2 == 0) {
						element.click();
						sleep(500);
					} else {
						jsClickButton(element);
						sleep(500);
					}
				}
				count = count + 1;
			}

		} catch (RuntimeException ex) {
			System.out.println("Cannot click on element");
		}
	}

	/***
	 * Method to wait for an element to disappear
	 * 
	 * @param :
	 *            Element Name
	 * @return :
	 * @author : Prakash (pshett001c) Modified By :
	 ***/

	public boolean waitForElementDisappear(WebElement we) {
		try {
			for (int i = 0; i < 120; i++) {
				if (isElementPresent(we)) {
					sleep(1000);
				} else {
					break;
				}
			}
			return !isElementPresent(we);
		} catch (Exception Ex) {
			return true;
		}
	}

	/***
	 * Method to check for an alert for 5 seconds
	 * 
	 * @param :
	 *            Element Name
	 * @return :
	 * @author : Shravanth (sarago001c) Modified By :
	 ***/

	public boolean isAlertPresent() {
		try {
			WebDriverWait wait = new WebDriverWait(browser, 15);
			wait.until(ExpectedConditions.alertIsPresent());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/***
	 * Method to check for an Wifi Router for 5 seconds
	 * 
	 * @param :
	 *            Element Name
	 * @return :
	 * @author : Sneha (skeeli001c) Modified By :
	 ***/

	public boolean isWifiRouterPresent() {
		try {
			WebDriverWait wait = new WebDriverWait(browser, 5);
			waitForElement(By.xpath("//*[@id='divAccountIndicators']/div[1]/span[2]"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getXPath(WebElement webElement) {
		String jscript = "function getXPath(node) {" + "var comp, comps = [];" + "var parent = null;"
				+ "var xpath = '';" + "var getPos = function(node) {" + "var position = 1, curNode;"
				+ "if (node.nodeType == Node.ATTRIBUTE_NODE) {" + "return null;" + "}"
				+ "for (curNode = node.previousSibling; curNode; curNode = curNode.previousSibling) {"
				+ "if (curNode.nodeName == node.nodeName) {" + "++position;" + "}" + "}" + "return position;" + "}"
				+ "if (node instanceof Document) {" + "return '/';" + "}"
				+ "for (; node && !(node instanceof Document); node = node.nodeType == Node.ATTRIBUTE_NODE ? node.ownerElement : node.parentNode) {"
				+ "comp = comps[comps.length] = {};" + "switch (node.nodeType) {" + "case Node.TEXT_NODE:"
				+ "comp.name = 'text()';" + "break;" + "case Node.ATTRIBUTE_NODE:" + "comp.name = '@' + node.nodeName;"
				+ "break;" + "case Node.PROCESSING_INSTRUCTION_NODE:" + "comp.name = 'processing-instruction()';"
				+ "break;" + "case Node.COMMENT_NODE:" + "comp.name = 'comment()';" + "break;"
				+ "case Node.ELEMENT_NODE:" + "comp.name = node.nodeName;" + "break;" + "}"
				+ "comp.position = getPos(node);" + "}" + "for (var i = comps.length - 1; i >= 0; i--) {"
				+ "comp = comps[i];" + "xpath += '/' + comp.name;" + "if (comp.position != null) {"
				+ "xpath += '[' + comp.position + ']';" + "}" + "}" + "return xpath;}";
		return (String) ((JavascriptExecutor) browser).executeScript(jscript, webElement);
	}

	public void getPageElementDetails() {

		List<WebElement> inputs = browser.findElements(By.tagName("input"));
		List<WebElement> links = browser.findElements(By.tagName("a"));
		ArrayList<String[]> list = new ArrayList<String[]>();
		for (int i = 0; i < inputs.size(); i++) {
			// String[]
			// array={inputs.get(i).getAttribute("id"),inputs.get(i).getAttribute("name"),inputs.get(i).getAttribute("class"),inputs.get(i).getAttribute("type"),(inputs.get(i).getLocation().getX()+","+inputs.get(i).getLocation().getY())};
			String[] array = { inputs.get(i).getAttribute("id"), inputs.get(i).getAttribute("name"),
					inputs.get(i).getAttribute("class"), inputs.get(i).getAttribute("type"),
					inputs.get(i).getLocation().toString() };
			list.add(array);
		}
		Iterator<String[]> itr = list.iterator();
		while (itr.hasNext()) {
			String[] array = itr.next();
			for (int i = 0; i < array.length; i++) {
				System.out.print(array[i] + "\t");
			}
			System.out.println();
		}
	}

	/***
	 * Method to wait for the any of 2 elements to be displayed
	 * 
	 * @param :
	 *            we1,we2
	 * @return :
	 * @author : Prakash Shetty(pshett001c) Modified By :
	 ***/

	public boolean waitForAnyElement(WebElement we1, WebElement we2) {
		try {
			for (int i = 0; i < 60; i++) {
				if (isElementPresent(we1) || isElementPresent(we2)) {
					break;
				} else {
					sleep(1000);
				}
			}
			return true;
		} catch (Exception Ex) {
			return false;
		}
	}

	/***
	 * Method to wait for the any of 2 elements to be displayed
	 * 
	 * @param :
	 *            we1,we2
	 * @return :
	 * @author : Prakash Shetty(pshett001c) Modified By :
	 ***/

	public boolean waitForAnyElement(By we1, By we2) {
		try {
			for (int i = 0; i < 80; i++) {
				if (isElementPresent(we1) || isElementPresent(we2)) {
					break;
				} else {
					sleep(1000);
				}
			}
			return true;
		} catch (Exception Ex) {
			return false;
		}
	}

	/***
	 * Method to wait for the a element to be clickable
	 * 
	 * @param :
	 *            we1,we2
	 * @return :
	 * @author : Shravanth PAV(sarago001c) Modified By :
	 ***/

	public void waitForElementToBeClickable(By by) {
		try {
			new WebDriverWait(browser, 30).until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception Ex) {
			report.reportFailEvent("Wait For Element", "Error in waiting for an element:" + Ex.getMessage());
		}
	}

	/***
	 * Method to wait for the a element to be clickable
	 * 
	 * @param :
	 *            we1,we2
	 * @return :
	 * @author : kpokha001c Modified By :
	 ***/

	public boolean isClickable(WebElement element) {
		try {
			new WebDriverWait(browser, 30).until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (Exception Ex) {
			report.reportFailEvent("Wait For Element", "Error in waiting for an element:" + Ex.getMessage());
		}
		return false;
	}

	public void mousePointToCentre() {
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		robot.mouseMove(650, 650);

	}

	public boolean checkUncheck(WebElement element) {
		waitForElement(element);
		if (element.getAttribute("class").contains("Checked")) {
			report.reportDoneEvent("ElementIsChecked", element.getText() + "is Checked");
			clickOnButton(element);
			report.reportDoneEvent("IsElementUnchecked", element.getText() + "is Unchecked");
			return false;
		} else {
			report.reportDoneEvent("IsElementUnchecked", element.getText() + "is Unchecked");
			clickOnButton(element);
			report.reportDoneEvent("ElementIsChecked", element.getText() + "is Checked");
			return true;
		}

	}

	/**
	 * Click On Help Button an any Page and verify its Content
	 * 
	 * @param HelpButton
	 *            to click, ContentVerification to get a Text form HelpPage,
	 *            ContentText to verify if text matches with text displayed in
	 *            HelpPage
	 * @return : void
	 * @author : Omkar (oraghu001c)
	 */

	/*
	 * public void clickonHelpButtonAndVerify(WebElement HelpButton, String
	 * expectedtitle) { try { HelpPageLoginData helpPageLoginData = new
	 * HelpPageLoginData(); TestSettings testSettings = new TestSettings();
	 * String[] filepath = new String[] { TestUtils.getRelativePath() +
	 * "\\src\\main\\resources\\AutoIt\\QALoginAll.exe",
	 * testSettings.getBrowser(), helpPageLoginData.username,
	 * helpPageLoginData.password }; try { Runtime.getRuntime().exec(filepath);
	 * 
	 * } catch (IOException e) { System.out.println("Unable to close"); }
	 * waitForElement(HelpButton); //HelpButton.click(); jsClick(HelpButton);
	 * report.reportDoneEvent("ClickHelpButton", "Help button in is clicked");
	 * 
	 * //sleep(10000); String strHelp = null; Set<String> windows =
	 * browser.getWindowHandles(); for (String window : windows) {
	 * browser.switchTo().window(window); try { strHelp =
	 * browser.getCurrentUrl(); if (strHelp.contains("Help")) { break; } } catch
	 * (UnhandledAlertException Ex) { sleep(10000); strHelp =
	 * browser.getCurrentUrl(); if (strHelp.contains("Help")) { break; } } } try
	 * { new WebDriverWait(browser,
	 * 50).until(ExpectedConditions.presenceOfElementLocated(By.id("username")))
	 * ; } catch (UnhandledAlertException Ex) { try { Alert alert =
	 * browser.switchTo().alert(); alert.dismiss(); sleep(3000); } catch
	 * (Exception ex) { // do nothing } } catch (Exception Ex) { // do nothing }
	 * if (isElementPresent(By.id("username"))) {
	 * browser.findElement(By.id("username")).sendKeys(helpPageLoginData.
	 * username);
	 * browser.findElement(By.id("password")).sendKeys(helpPageLoginData.
	 * password); browser.findElement(By.id("SubmitCreds")).click();
	 * System.out.println(browser.getTitle().toLowerCase());
	 * System.out.println(expectedtitle.toLowerCase()); } sleep(5000);
	 * waitForElement(By.xpath("//div[@class='cdclvAssistTitle']"));
	 * report.reportPassEvent("CheckHelpLink", "The window with URL: " +
	 * browser.getCurrentUrl() + " is opened");
	 * report.updateTestLog("CheckHelpLink", "The window with URL: " +
	 * browser.getCurrentUrl() + " is opened", Status.SCREENSHOT); if
	 * (browser.getTitle().toLowerCase().contains(expectedtitle.toLowerCase()))
	 * { report.reportPassEvent("Check title of Help Page",
	 * " Help page loaded with title" + browser.getTitle());
	 * report.updateTestLog("Check title of Help Page",
	 * " Help page loaded with title" + browser.getTitle(), Status.SCREENSHOT);
	 * } else { report.reportFailEvent("Check title of Help Page",
	 * " Title of help page : actual Title:" + browser.getTitle()); }
	 * 
	 * } catch (UnhandledAlertException Ex) { try { Alert alert =
	 * browser.switchTo().alert(); alert.dismiss(); sleep(3000); } catch
	 * (Exception ex) { // do nothing } } }
	 */

	/***
	 * Method to close frame with a message and check the message
	 * 
	 * @param :
	 * @return :
	 * @author : Shravanth(sarago001) Modified By :
	 ***/
	public void closeFrame() {

		String frameName = browser.findElement(By.tagName("iframe")).getAttribute("name");
		System.out.println(frameName);
		browser.switchTo().frame(frameName);
		do {
			jsClick(browser.findElement(By.id("CloseBtn")));
		} while (isElementPresent(By.xpath("//iframe[@name='" + frameName + "']")));

		// browser.switchTo().frame(browser.findElement(By.xpath("//iframe[contains(@name,'RadWindowManager')]")));
		// browser.findElement(By.xpath("//*[@name='CloseBtn']")).click();
		report.reportDoneEvent("CloseBackEndServicePop", "Successfully closed back end service pop up");
		browser.switchTo().defaultContent();

	}

	public void CloseBtn() {
		jsClick(browser.findElement(By.id("CloseBtn")));
	}

	/***
	 * Method to close back end service name- For different
	 * 
	 * @param :
	 * @return :
	 * @author : Shravanth(sarago001c) Modified By :
	 ***/
	public String closeBackEndServiceFrame2() {
		String message = null;
		if (isElementPresent(By.tagName("iframe"))) {
			String frameName = browser.findElement(By.tagName("iframe")).getAttribute("name");
			System.out.println(frameName);
			browser.switchTo().frame(frameName);
			waitForElement(By.id("MessageLabel"));
			message = browser.findElement(By.id("MessageLabel")).getText();
			System.out.println(message);
			do {
				browser.findElement(By.id("CloseBtn")).sendKeys(Keys.ENTER);
			} while (isElementPresent(By.xpath("//iframe[@name='" + frameName + "']")));

			// browser.switchTo().frame(browser.findElement(By.xpath("//iframe[contains(@name,'RadWindowManager')]")));
			// browser.findElement(By.xpath("//*[@name='CloseBtn']")).click();
			report.reportDoneEvent("CloseBackEndServicePop", "Successfully closed back end service pop up");
			browser.switchTo().defaultContent();
		}

		return message;
	}

	/***
	 * Method to close back end service name and check message
	 * 
	 * @param :
	 * @return :
	 * @author : Mahesh Hatti (mhatti002c) Modified By :
	 ***/
	public void closeServiceFrameandCheckMessage(String msg) {
		String message = null;
		sleep(8000);
		waitForElement(By.id("MessageLabel"));
		// waitForElement(By.id("lblMessage"));
		if (isElementPresent(By.tagName("iframe"))) {
			String frameName = browser.findElement(By.tagName("iframe")).getAttribute("name");
			System.out.println(frameName);
			browser.switchTo().frame(frameName);
			waitForElement(By.id("MessageLabel"));
			message = browser.findElement(By.id("MessageLabel")).getText();
			System.out.println("message=" + message);
			if (message.contains(msg) || message.contains("UID")) {
				report.reportPassEvent("BadUsername", "Bad user name Error message is" + message);
				report.updateTestLog("BadUsername", "Bad user name Error message is as expected", Status.SCREENSHOT);
			} else
				report.reportFailEvent("BadUsername", "Bad user name Error message is" + message);
			do {
				browser.findElement(By.id("CloseBtn")).sendKeys(Keys.ENTER);
				// browser.findElement(By.id("Button2")).sendKeys(Keys.ENTER);
			} while (isElementPresent(By.xpath("//iframe[@name='" + frameName + "']")));

			// browser.switchTo().frame(browser.findElement(By.xpath("//iframe[contains(@name,'RadWindowManager')]")));
			// browser.findElement(By.xpath("//*[@name='CloseBtn']")).click();
			report.reportDoneEvent("CloseBackEndServicePop", "Successfully closed back end service pop up");
			browser.switchTo().defaultContent();
		}

	}

	/***
	 * Method to close back end service name and check message
	 * 
	 * @param :
	 * @return :
	 * @author : Mahesh Hatti (mhatti002c) Modified By :
	 ***/
	public void closeServiceFrameandCheckMessage2(String msg) {
		String message = null;
		sleep(8000);
		// waitForElement(By.id("MessageLabel"));
		waitForElement(By.id("lblMessage"));
		if (isElementPresent(By.tagName("iframe"))) {
			String frameName = browser.findElement(By.tagName("iframe")).getAttribute("name");
			System.out.println(frameName);
			browser.switchTo().frame(frameName);
			waitForElement(By.id("lblMessage"));
			message = browser.findElement(By.id("lblMessage")).getText();
			System.out.println("message=" + message);
			if (message.contains(msg)) {
				report.reportPassEvent("BadUsername", "Bad user name Error message is" + message);
				report.updateTestLog("BadUsername", "Bad user name Error message is as expected", Status.SCREENSHOT);
			} else
				report.reportFailEvent("BadUsername", "Bad user name Error message is" + message);
			do {
				// browser.findElement(By.id("CloseBtn")).sendKeys(Keys.ENTER);
				browser.findElement(By.id("Button2")).sendKeys(Keys.ENTER);
			} while (isElementPresent(By.xpath("//iframe[@name='" + frameName + "']")));

			// browser.switchTo().frame(browser.findElement(By.xpath("//iframe[contains(@name,'RadWindowManager')]")));
			// browser.findElement(By.xpath("//*[@name='CloseBtn']")).click();
			report.reportDoneEvent("CloseBackEndServicePop", "Successfully closed back end service pop up");
			browser.switchTo().defaultContent();
		}

	}

	/***
	 * Method to handle multiple windows
	 * 
	 * @param :
	 * @return :
	 * @author : Shravanth(sarago001c) Modified By :
	 ***/
	public void handleMultipleWindowsNew(String windowTitle) {
		sleep(5000);
		Set<String> windows = browser.getWindowHandles();
		for (String window : windows) {
			browser.switchTo().window(window);
			if (browser.getTitle().contains(windowTitle)) {
				sleep(5000);
				System.out.print("\nWindow switched to " + browser.getTitle());
				report.reportPassEvent("Switch window", "Window switched to " + browser.getTitle());
				break;
			} // else report.reportFailEvent("Switch window",
				// "Problem opening new window");
		}
	}

	/***
	 * Method to compare arrays
	 * 
	 * @param :
	 * @return :
	 * @author : Facundo (ftolos001c) Modified By :
	 ***/

	public void compareArrays(List<String> A, List<String> B) {
		try {
			Assert.assertNotNull(A);
			Assert.assertNotNull(B);
			Assert.assertTrue(A.containsAll(B));
			Assert.assertTrue(A.size() == B.size());
			report.reportPassEvent("Compare elements", "All elements are correct");
		} catch (Throwable e) {
			report.reportFailEvent("Error Comparing the elements",
					"Error comparing elements: " + e + "Expected: " + B + " Actual: " + A);
		}

	}

	/***
	 * Method to compare arrays
	 * 
	 * @param :
	 * @return :
	 * @author : Facundo (ftolos001c) Modified By :
	 ***/

	public boolean compareStrings(String actual, String expected) {
		boolean result = false;
		try {
			if (actual.equalsIgnoreCase(expected)) {
				result = true;
			} else
				report.reportFailEvent("Compare text",
						"Text displayed is not the expected. Expected: " + expected + " Actual: " + actual);
		} catch (RuntimeException e) {
			report.reportFailEvent("Problem during execution", "Exception found: " + e);
		}
		return result;
	}

	/***
	 * Method to Validate DataBase
	 * 
	 * @param :
	 *            Environmnet,query,Column name and expected string
	 * @return : Nill
	 * @author : Gopal (gtikar00c) Modified By :
	 * @throws Sql
	 *             exception
	 * 
	 ***/

	public void dbValidation(String que, String colName, String expectedvalue)
			throws ClassNotFoundException, SQLException {
		try {
			// Declare the JDBC objects.
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;

			String query = que;
			String connectionUrl;
			boolean flag = false;
			// String query="SELECT TOP 1000
			// [NTLogin],[CSGOperatorID],[DDPOperatorID],[LastUpdated]FROM
			// [CDT20_qa].[dbo].[OperatorIDMapping]where NTLogin='gsglobalqa'";
			// String query="SELECT TOP 1000
			// [NTLogin],[CSGOperatorID],[DDPOperatorID],[LastUpdated]FROM
			// [CDT20_qa].[dbo].[OperatorIDMapping]where NTLogin='gsglobalqa'";
			if (env.equalsIgnoreCase("QA") || env.equalsIgnoreCase("INT")) {
				// QA URL
				connectionUrl = "jdbc:sqlserver://PACDCGSDEVD01.cable.comcast.com;"
						+ "user=CDT20_Admin;password=m@estro@dmin";
			} else {
				// UAT URL
				connectionUrl = "jdbc:sqlserver://PACDCGSINTR01.cable.comcast.com;"
						+ "user=CDT20_Admin;password=m@estro@dmin";
			}

			String dbClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			Class.forName(dbClass);
			con = DriverManager.getConnection(connectionUrl);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				String var = rs.getString(colName).trim();
				if (var.equalsIgnoreCase(expectedvalue)) {
					report.reportPassEvent("dbValidation",
							"Ecpected value : " + expectedvalue + " matches the actual value : " + var);
					flag = true;
					break;
				}
			}

			if (flag == false) {
				report.reportFailEvent("dbValidation", "Ecpected value fails to match the actual value");
			}

			con.close();

		} catch (SQLException ex) {
			report.reportFailEvent("", ex.getMessage());
		} catch (ClassNotFoundException e) {
			report.reportFailEvent("", e.getMessage());
		}

	}

	/***
	 * Method to Update DataBase Query
	 * 
	 * @param :
	 *            query
	 * @return : Nill
	 * @author : Gopal (gtikar00c) Modified By :
	 * @throws Sql
	 *             exception
	 * 
	 ***/

	public void dbValidationUpdateQuery(String que) throws ClassNotFoundException, SQLException {
		try {
			// Declare the JDBC objects.
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;

			String query = que;
			String connectionUrl;
			boolean flag = false;
			// String query="SELECT TOP 1000
			// [NTLogin],[CSGOperatorID],[DDPOperatorID],[LastUpdated]FROM
			// [CDT20_qa].[dbo].[OperatorIDMapping]where NTLogin='gsglobalqa'";
			// String query="SELECT TOP 1000
			// [NTLogin],[CSGOperatorID],[DDPOperatorID],[LastUpdated]FROM
			// [CDT20_qa].[dbo].[OperatorIDMapping]where NTLogin='gsglobalqa'";
			if (env.equalsIgnoreCase("QA") || env.equalsIgnoreCase("INT")) {
				// QA URL
				connectionUrl = "jdbc:sqlserver://PACDCGSDEVD01.cable.comcast.com;"
						+ "user=CDT20_Admin;password=m@estro@dmin";
			} else {
				// UAT URL
				connectionUrl = "jdbc:sqlserver://PACDCGSINTR01.cable.comcast.com;"
						+ "user=CDT20_Admin;password=m@estro@dmin";
			}

			String dbClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			Class.forName(dbClass);
			con = DriverManager.getConnection(connectionUrl);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			// while(rs.next()){
			// String var=rs.getString(colName).trim();
			// if(var.equalsIgnoreCase(expectedvalue)){
			// report.reportPassEvent("dbValidation", "Ecpected value :
			// "+expectedvalue+" matches the actual value : "+var);
			// flag=true;
			// break;
			// }
			// }
			//
			// if(flag==false){
			// report.reportFailEvent("dbValidation", "Ecpected value fails to
			// match the actual value");
			// }

			con.close();

		} catch (SQLException ex) {
			report.reportFailEvent("", ex.getMessage());
		} catch (ClassNotFoundException e) {
			report.reportFailEvent("", e.getMessage());
		}

	}

	public void clickonLinkForNewPopUp(WebElement link) {
		int i = 0;
		while (i < 6) {
			jsClick(link);
			sleep(5000);
			Set<String> windows = browser.getWindowHandles();
			if (windows.size() > 1) {
				break;
			}
		}

	}

	public String getCurrentTimeStamp() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}

	/***
	 * Method to get Current Date
	 * 
	 * @param :
	 * @return : Nill
	 * @author : kpokha001c Modified By :
	 * 
	 ***/
	public String getCurrentDate() {
		try {
			SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/YYYY");
			Date now = new Date();
			String strDate = sdfDate.format(now);
			return strDate;
		} catch (Exception ex) {
			report.reportFailEvent("getCurrentDate", "Probelm during execution" + ex.getMessage());
		}
		return null;
	}
	
	public String getCurrentDateTime() {
		try {
			SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/YYYY hh:mm a");
			Date now = new Date();
			String strDate = sdfDate.format(now);
			return strDate;
		} catch (Exception ex) {
			report.reportFailEvent("getCurrentDate", "Probelm during execution" + ex.getMessage());
		}
		return null;
	}

	/***
	 * Method to get Previous Date
	 * 
	 * @param :
	 *            days to deduct from current date
	 * @return : Nill
	 * @author : kpokha001c Modified By :
	 * 
	 ***/
	public String getPreviousDaysDate(int minusDays) {
		try {
			SimpleDateFormat sdfDate = new SimpleDateFormat("MMM dd, yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, Integer.parseInt("-" + minusDays));
			String strDate = sdfDate.format(cal.getTime());
			return strDate;
		} catch (Exception ex) {
			report.reportFailEvent("getCurrentDate", "Probelm during execution" + ex.getMessage());
		}
		return null;
	}

	/***
	 * Method to get Previous Date
	 * 
	 * @param :
	 *            days to deduct from current date
	 * @return : Nill
	 * @author : kpokha001c Modified By :
	 * 
	 ***/
	public String getPreviousDaysDate(String date, int minusDays) {
		try {
			SimpleDateFormat sdfDate = new SimpleDateFormat("MMM dd, yyyy");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdfDate.parse(date));
			cal.add(cal.get(Calendar.DAY_OF_WEEK), Integer.parseInt("-" + minusDays));
			String strDate = sdfDate.format(cal.getTime());
			return strDate;
		} catch (Exception ex) {
			report.reportFailEvent("getCurrentDate", "Probelm during execution" + ex.getMessage());
		}
		return null;
	}

	/***
	 * Method to enterCredentialsThruRobotClass
	 * 
	 * @param :toolKitName
	 * @return :
	 * @author : kpokha001c
	 * @throws AWTException
	 ***/
	/*public void enterCredentialsThruRobotClass(LoginDetails loginInfo) throws AWTException {
		try {
			// wait
			sleep(15000);
			Robot rb = new Robot();

			// Enter user name by ctrl-v
			StringSelection username = new StringSelection(loginInfo.userName);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(username, null);
			rb.keyPress(KeyEvent.VK_CONTROL);
			rb.keyPress(KeyEvent.VK_V);
			rb.keyRelease(KeyEvent.VK_V);
			rb.keyRelease(KeyEvent.VK_CONTROL);

			// tab to password entry field
			rb.keyPress(KeyEvent.VK_TAB);
			rb.keyRelease(KeyEvent.VK_TAB);
			sleep(2000);

			// Enter password by ctrl-v
			StringSelection pwd = new StringSelection(loginInfo.password);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(pwd, null);
			rb.keyPress(KeyEvent.VK_CONTROL);
			rb.keyPress(KeyEvent.VK_V);
			rb.keyRelease(KeyEvent.VK_V);
			rb.keyRelease(KeyEvent.VK_CONTROL);

			// press enter
			rb.keyPress(KeyEvent.VK_ENTER);
			rb.keyRelease(KeyEvent.VK_ENTER);

			// wait
			sleep(5000);
		} catch (RuntimeException ex) {
			report.updateTestLog("Open Application", ex.getMessage(), Status.FAIL);
			throw ex;
		}

	}
*/
	/***
	 * Method to uploadAttachments
	 * 
	 * @param :toolKitName
	 * @return :
	 * @author : jluthr001c
	 * @throws AWTException
	 ***/

	public void uploadAttachments(String filePath) throws AWTException {
		try {
			// wait
			sleep(15000);
			Robot rb = new Robot();

			// Enter user name by ctrl-v
			StringSelection fpath = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(fpath, null);
			rb.keyPress(KeyEvent.VK_CONTROL);
			rb.keyPress(KeyEvent.VK_V);
			rb.keyRelease(KeyEvent.VK_V);
			rb.keyRelease(KeyEvent.VK_CONTROL);
			rb.keyPress(KeyEvent.VK_TAB);
			rb.keyRelease(KeyEvent.VK_TAB);
			sleep(2000);
			rb.keyPress(KeyEvent.VK_TAB);
			rb.keyRelease(KeyEvent.VK_TAB);
			sleep(2000);
			rb.keyPress(KeyEvent.VK_ENTER);
			rb.keyRelease(KeyEvent.VK_ENTER);
			sleep(5000);
		} catch (RuntimeException ex) {
			report.updateTestLog("Upload failed", ex.getMessage(), Status.FAIL);
			throw ex;
		}

	}

	public void keyPress(int key, int counter) throws AWTException {
		try {
			Robot rb = new Robot();

			for (int i = 0; i < counter; i++) {
				rb.keyPress(key);
				rb.keyRelease(key);
			}

		} catch (RuntimeException ex) {
			report.updateTestLog("Failed", ex.getMessage(), Status.FAIL);
			throw ex;
		}

	}

	/***
	 * Method to pass Random Value
	 * 
	 * @return
	 */
	protected String getTimestamp() {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
		int day = now.get(Calendar.DAY_OF_MONTH);
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int minute = now.get(Calendar.MINUTE);
		int second = now.get(Calendar.SECOND);
		// int millis = now.get(Calendar.MILLISECOND);
		return Integer.toString(year) + Integer.toString(month) + Integer.toString(day) + Integer.toString(hour)
				+ Integer.toString(minute) + Integer.toString(second);
	}

	protected String randomNumber(int limit) {
		return Double.toString(Math.random()).substring(2, 2 + limit);
	}

	/****
	 * Method to handle drop downs
	 * 
	 * @param ddTxtWE
	 * @param ddValueWE
	 * @param Str
	 * @throws InterruptedException
	 */

	protected void ddValueSelect(WebElement ddTxtWE, WebElement ddValueWE, String Str) throws InterruptedException {
		do {
			waitForElement(ddTxtWE);
			ddTxtWE.clear();
			ddTxtWE.sendKeys(Str);
			ddTxtWE.sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(1000);
		} while (!waitForElement(ddValueWE));
		ddValueWE.click();
	}

	protected void ddValueSelect(WebElement ddTxtWE, By Identifer, String Str) throws InterruptedException {
		do {
			waitForElement(ddTxtWE);
			ddTxtWE.clear();
			ddTxtWE.sendKeys(Str);
			ddTxtWE.sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(1000);
		} while (!waitUntilElementPresent(Identifer, 5));
		if (isElementPresent(Identifer))
		{
			browser.findElement(Identifer).click();
		}
	}

	
	public void ddValue(WebElement ddTxtWE, String Str) throws InterruptedException {
		waitForElement(ddTxtWE);
		ddTxtWE.clear();
		ddTxtWE.sendKeys(Str);
		ddTxtWE.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
	}

	/***
	 * Method to close the pop ups
	 * 
	 * @param windowHandle
	 * @param we
	 * @throws InterruptedException
	 */

	protected void ClosePopUpndSwitchtoFrame(String windowHandle, WebElement we) throws InterruptedException {
		String homePage, currentWindowId;
		homePage = windowHandle;
		Thread.sleep(5 * 1000);
		Set<String> windows = browser.getWindowHandles();
		System.out.println("size " + windows.size());
		// while size<=1
		// Thread.sleep(1)
		// Set<String> windows = browser.getWindowHandles();

		Iterator<String> iterator = windows.iterator();
		while (iterator.hasNext()) {

			currentWindowId = iterator.next().toString();
			System.out.println("currentWindowId " + currentWindowId);
			if (!currentWindowId.equals(homePage)) {
				browser.switchTo().window(currentWindowId);
				browser.close();
			}
		}

		browser.switchTo().window(homePage);
		WaitandSwitchToFrame(we);
	}

	protected void RobotClick(WebElement we) throws AWTException {
		Point coordinates = we.getLocation();
		Robot robot = new Robot();
		/*
		 * WebElement markNews = we; markNews.click();
		 */
		robot.mouseMove(coordinates.x, coordinates.y + 80);
		robot.mousePress(InputEvent.BUTTON1_MASK); // Left Mouse click - Press
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		// robot.
	}

	/***
	 * Method to wait for a task to become in progress & click
	 * 
	 * @param we1
	 * @param we2
	 * @param status
	 * @return
	 * @throws InterruptedException
	 */

	protected boolean checkifStatusChanged(WebElement we1, WebElement we2, String status) throws InterruptedException

	{
		boolean fn_status = false;
		System.out.println("Task ::" + we1.getText());
		int counter = 0;
		int reqminutes = 7;

		if ((!(we1.getAttribute("onclick").contains("COMPLETED")))
				&& (!(we1.getAttribute("onclick").contains("DEFERRED")))
						&&(!(we1.getAttribute("onclick").contains("CANCELLED")))) {
			while (!(browser.findElement(By.xpath("//*[text()='" + we1.getText() + "']")).getAttribute("onclick")
					.contains(status)) && counter < reqminutes * 6) {
				Thread.sleep(10 * 1000);
				we2.click();
				System.out.println("inside counter " + counter);
				counter++;
			}

			if (we1.getAttribute("onclick").contains(status)) {
				waitForElement(we1);
				fn_status = true;
			} else {
				report.reportFailEvent(we1.getText() + " clicked failed",
						we1.getText() + "task not coming to in progress after 7 mins");

			}
		}

		return fn_status;

	}

	public void clickndRelease(WebElement link) throws InterruptedException {
		try {
			waitForElement(link);
			if (isElementPresent(link)) {
				Actions action = new Actions(browser);
				action.clickAndHold(link);
				Thread.sleep(250);
				action.release();
				action.perform();

			}
		} catch (RuntimeException ex) {

		}
	}

	public void closeAllOtherWindows(String openWindowHandle) {

		Set<String> allWindowHandles = browser.getWindowHandles();
		for (String currentWindowHandle : allWindowHandles) {
			if (!currentWindowHandle.equals(openWindowHandle)) {
				browser.switchTo().window(currentWindowHandle);
				browser.close();
			}
		}

		browser.switchTo().window(openWindowHandle);
	}

	/***
	 * Method to scroll up down to page
	 * 
	 */

	protected void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) browser;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	protected void scrollUp() {
		JavascriptExecutor js = (JavascriptExecutor) browser;
		js.executeScript("scroll(0, -250);");
	}

	protected void jsSendKeys(WebElement we, String Str) {
		String id;
		JavascriptExecutor executor = (JavascriptExecutor) browser;
		id = we.getAttribute("id");
		executor.executeScript("document.getElementById('" + id + "').value='" + Str + "'");
	}

	public WebElement iterateThroughFrames(WebElement we) {

		List<WebElement> frameList = browser.findElements(By.tagName("iframe"));
		for (WebElement frame : frameList) {
			if (ShortWaitandSwitchToFrame(frame)) {
				if (miniWaitForElement(we)) {
					return frame;
				}
			}
		}
		return null;
	}

	public WebElement iterateThroughFramesinsideFrame(WebElement we, WebElement parentframe) {
		System.out.println("Inside iterateThroughFramesinsideFrame");
		List<WebElement> frameList = null;
		int counter = 0;
		System.out.println("parentframe " + parentframe.getAttribute("id"));
		browser.switchTo().defaultContent();
		do {
			parentframe = browser.findElement(By.xpath("//*[@id='mainFrame']"));
			if (ShortWaitandSwitchToFrame(parentframe)) {
				System.out.println("main frame found");
				frameList = browser.findElements(By.tagName("iframe"));
				counter++;
			}

		} while (frameList.size() == 0 && counter <= 5);

		if (frameList.size() != 0) {

			System.out.println("No. of frames " + frameList.size());
			for (WebElement frame : frameList) {
				if (ShortWaitandSwitchToFrame(frame)) {
					System.out.println("inside frame ");
					if (miniWaitForElement(we)) {
						browser.switchTo().defaultContent();
						browser.switchTo().frame(parentframe);
						return frame;
					}
					browser.switchTo().defaultContent();
					browser.switchTo().frame(parentframe);
				}
			}
		}
		return null;
	}

	/*
	 * Method to enter MRC , NRC Value on order summary page
	 * 
	 * 
	 * 
	 */

	public void enterValue(List<WebElement> we, WebElement we1, String value) throws InterruptedException {

		for (int i = 0; i < we.size(); i++) {
			waitForElement(we.get(i));
			doubleClick(we.get(i));
			Thread.sleep(1500);
			waitForElement(we1);
			we1.click();
			we1.clear();
			we1.sendKeys(value);
			we1.sendKeys(Keys.ENTER);
			Thread.sleep(2500);
		}

	}

	protected void enterQuantity(WebElement we, String value) {
		if (waitForElement(we)) {
			we.click();
			we.clear();
			we.sendKeys(value);
			we.sendKeys(Keys.TAB);
		}
	}

	public boolean iterateThroughtableAndSelectCity(String city) throws InterruptedException {
		int rowId = -1;
		String cityXpath = "//*[.='State']/ancestor-or-self::form/descendant::div[.='" + city + "']";
		waitforPageLoadComplete();
		Thread.sleep(5000);
		while (!isElementPresent(browser.findElement(By.xpath(cityXpath)))) {
			Thread.sleep(2000);
		}
		/*
		 * while(!isElementPresent(browser.findElement(By.tagName("tbody")))){
		 * Thread.sleep(2000); }
		 * 
		 * 
		 * while(!isElementPresent(table.findElements(By.tagName("tr")).get(0)))
		 * { Thread.sleep(2000); }
		 */
		WebElement table = browser.findElement(By.tagName("tbody"));
		List<WebElement> allRows = table.findElements(By.tagName("tr"));

		for (int i = 0; i < allRows.size(); i++) {

			List<WebElement> cells = allRows.get(i).findElements(By.tagName("td"));

			if (city.equalsIgnoreCase(cells.get(2).getText())) {
				rowId = i;
				break;
			}
		}
		if (rowId > -1) {

			while (!isElementPresent(browser.findElement(By.id(Integer.toString(rowId))))) {
				Thread.sleep(2000);
			}
			browser.findElement(By.id(Integer.toString(rowId))).click();

		} else {
			report.reportFailEvent("City selection", "City not found");
		}

		return (rowId > -1);
	}

	public void iClick(WebElement we) {
		if ((new TestSettings()).getBrowser().equalsIgnoreCase("iexplore")
				|| (new TestSettings()).getBrowser().equalsIgnoreCase("ie")) {
			we.sendKeys(Keys.ENTER);
		} else
			we.click();

	}

	/**
	 * Method to click any Web Element on the page
	 * 
	 * @param we
	 *            The element in which we want to perform the action
	 * @param waitForElement
	 *            [Optional] Synchronization point in which we want to sync
	 *            after the button click
	 * @param description
	 *            Description about the action performed on the UI.
	 */
	public void iClick(WebElement we, WebElement waitForElement, String description) {
		//System.out.println("inside iclick");
		//added by harsh to monitor perf transactions
		//log.error("inside iclick");
		log.info("inside iClick");
		if(tLogger!=null){
			
			tLogger.startTransaction(testName);
		}
		//System.out.println(TestSettingsSingleton.getInstance().getProperties());
		//String testName = report.getReportSettings().getReportName(); // added by harsh on 9/6/2016 to get the test name
		if (testSettings.getBrowser().equalsIgnoreCase("iexplore")
				|| testSettings.getBrowser().equalsIgnoreCase("ie")) {
			log.info("Webelement: " + we.getText() + "clicked Description: " + description);
			we.sendKeys(Keys.ENTER);
			
		} else{
			log.info("Webelement: " + we.getText() + "clicked Description: " + description);
			we.click();
			
		}
		waitforPageLoadComplete();
		if (!(waitForElement == null)) {		
			isElementClickable(waitForElement);
		}
		//added by harsh to monitor perf transactions
		if(tLogger!=null){
			tLogger.endTransaction(description, testSettings.getProperties());
		}
		report.reportPassEvent("Button Click", description);
		

	}

	public boolean waitUntilElementPresent(By by, int sec) {
		int counter = 0;
		while ((!isElementPresent(browser.findElement(by))) && counter <= sec) {
			sleep(1000);
			counter++;
		}
		return isElementPresent(browser.findElement(by));
	}

	// #############################################################################################
	// CustomMethods
	public void iSendKeys(WebElement we, String text) {
		do {
			waitForElement(we);
			we.click();
			we.clear();
			we.sendKeys(text);
		} while (!we.getAttribute("value").equalsIgnoreCase(text));

	}

	public String getValue(WebElement we) {
		return we.getAttribute("value");
	}

	public void iSubmit(WebElement we) {
		// TODO Auto-generated method stub
		we.submit();
	}

	/*
	 * public void iclear(WebElement we) { // TODO Auto-generated method stub
	 * 
	 * }
	 * 
	 * public String igetTagName(WebElement we) { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * public String igetAttribute(String iname) { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * public boolean isSelected(WebElement we) { // TODO Auto-generated method
	 * stub return false; }
	 * 
	 * public boolean isEnabled(WebElement we) { // TODO Auto-generated method
	 * stub return false; }
	 * 
	 * public String igetText(WebElement we) { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * public List<WebElement> findElements(By by) { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * public WebElement findElement(By by) { // TODO Auto-generated method stub
	 * return null; }
	 * 
	 * public boolean isDisplayed(WebElement we) { // TODO Auto-generated method
	 * stub return false; }
	 * 
	 * public Point getLocation(WebElement we) { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * /*public Dimension getSize(WebElement we) { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * public Rectangle getRect(WebElement we) { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * public String igetCssValue(String ipropertyName) { // TODO Auto-generated
	 * method stub return null; }
	 */
	// ################################################

}
