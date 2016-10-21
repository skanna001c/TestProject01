package com.comcast.commons;

import java.io.File;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;
import com.comcast.utils.TestSettings;
import com.comcast.utils.TestUtils;

public class SFRPage extends Page {

	public static int debug_level = 10;
	public static ObjRepoLocator pageORLocator = null;

	// TODO configurable wait time when interactive .....
	long iwaitTime = 33;

	Properties pgWebEleRepoProp = null;

	static Logger log = Logger.getLogger(SFRPage.class.getName());

	protected SFRPage(FrameworkContext context) {
		super(context);
	}

	protected SFRPage(FrameworkContext context, Properties pro) {
		super(context);
		// this.BROWSER = context.getDriver();
		this.pgWebEleRepoProp = pro;

	}

	protected SFRPage(FrameworkContext context, String className) {
		super(context);
		// this.BROWSER = context.getDriver();
		log.debug("Done calling super Page - 'Page' ");
		log.debug("Now Load locators for [" + className + "]");
		loadPageOrLocator(className);
		log.debug("Locators loaded.\n\n");

	}

	@Override
	protected boolean isValidPage() {
		return false;
	}

	@Override
	protected void waitForPageLoad() {
	}

	public WebElement getLWE(String locid) {

		WebElement we = null;
		sleep(2000);
		try {
			we = browser.findElement(By.linkText(pgWebEleRepoProp.getProperty(locid)));

		} catch (Exception e) {
			String emsg = "Unable to find [" + locid + "],  locator=[" + pgWebEleRepoProp.getProperty(locid) + "] "
					+ e.getMessage();
			log.error(emsg);
			throw new RuntimeException(emsg);
		}

		log.info("Found [" + locid + "],  we=" + we);
		return (we);
	}

	public WebElement getXWE(String locid) {

		WebElement we = null;
		sleep(2000);
		try {
			we = browser.findElement(By.xpath(pgWebEleRepoProp.getProperty(locid)));

		} catch (Exception e) {
			String emsg = "Unable to find [" + locid + "],  locator=[" + pgWebEleRepoProp.getProperty(locid) + "] "
					+ e.getMessage();
			log.error(emsg);
			throw new RuntimeException(emsg);
		}

		log.info("Found [" + locid + "],  we=" + we);
		return (we);
	}

	public boolean iXclick(String locid) {
		WebElement we = null;
		try {
			we = getXWE_Retry(locid);
			if (we == null) {
				log.info("Asked to pass the click - must have done manully :) ...");
				return (true);
			}
		} catch (Exception e) {
			String e1 = e.getMessage();
			String emsg = "Unable to find WebElement for [" + locid + "],  locator=["
					+ pgWebEleRepoProp.getProperty(locid) + "] " + e1;
			throw new RuntimeException(emsg);
		}

		try {
			we.click();
		} catch (Exception e) {
			String emsg = "Unable to click [" + locid + "],  locator=[" + pgWebEleRepoProp.getProperty(locid)
					+ "], we=[" + we + ",]" + e.getMessage();
			log.error(emsg);
			throw new RuntimeException(emsg);
		}

		log.info("Clicked [" + locid + "],  we=" + we);
		return (true);
	}

	public boolean iLclick(String locid) {
		WebElement we = null;
		try {
			we = getLWE(locid);
		} catch (Exception e) {
			String emsg = e.getMessage();
			String popupMsg = "Unable to find WebElement for [" + locid + "],\n  locator=["
					+ pgWebEleRepoProp.getProperty(locid) + "] ";
			int ansCont = TestUtils.ynPopUp(popupMsg, emsg);
			if (ansCont == JOptionPane.YES_OPTION) {
				return (true);
			}
			throw new RuntimeException(emsg);
		}

		try {
			we.click();
		} catch (Exception e) {
			String emsg = "Unable to click [" + locid + "],  locator=[" + pgWebEleRepoProp.getProperty(locid)
					+ "], we=[" + we + ",]" + e.getMessage();
			log.error(emsg);
			throw new RuntimeException(emsg);
		}

		log.info("Clicked [" + locid + "],  we=" + we);
		return (true);
	}

	public boolean enterXText(String locid, String stext) {
		WebElement we = null;
		try {
			we = getXWE(locid);
		} catch (Exception e) {
			String emsg = e.getMessage();
			String popupMsg = "Unable to find WebElement for [" + locid + "],\n  locator=["
					+ pgWebEleRepoProp.getProperty(locid) + "] ";
			int ansCont = TestUtils.ynPopUp(popupMsg, emsg);
			if (ansCont == JOptionPane.YES_OPTION) {
				return (true);
			}
			// TODO throw e ;
			throw new RuntimeException(emsg);
		}

		try {
			we.sendKeys(stext);
		} catch (Exception e) {
			String emsg = "Unable to send text [" + locid + "],  locator=[" + pgWebEleRepoProp.getProperty(locid)
					+ "], we=" + we;
			log.error(emsg);
			throw new RuntimeException(emsg);
		}

		log.info("[" + locid + "] enter text [" + stext + "] we=" + we);
		return (true);
	}

	public boolean selectXByIndex(String locid, int index) {
		WebElement we = getXWE(locid);

		Select dd = new Select(we);
		dd.selectByIndex(index);

		return (true);
	}

	/**
	 * Gets Web Element with options to retry and retry after loading properties
	 * 
	 * @param locID
	 * @return
	 */
	public WebElement getXWE_Retry(String locID) {
		WebElement we = null;
		boolean edone = false;
		int tryCount = 0;
		while (!edone) {
			try {
				we = getXWE(locID);
				if (tryCount > 0) {
					log.error("Retried times=" + tryCount);
					// Add Message here for new locator ......
				}
				return (we);
			} catch (Exception e) {
				String emsg = e.getMessage();
				String popupMsg = "Unable to find WebElement for [" + locID + "],\n  locator=["
						+ pgWebEleRepoProp.getProperty(locID) + "] ";

				int jopResp = TestUtils.passLoadRetryPopUp(popupMsg, emsg);

				if (jopResp == JOptionPane.YES_OPTION) {
					log.info("1. button clicked - Pass");
					log.info("Retry .......");
					return null;
				} else if (jopResp == JOptionPane.NO_OPTION) {
					log.info("2.  button clicked  - Retry");
				} else if (jopResp == JOptionPane.CANCEL_OPTION) {
					log.info("3.  button  clicked - Load/Retry");
					ComcastTest.reloadObjRepo();
					pgWebEleRepoProp = ComcastTest.loadObjRepo();
				} else if (jopResp == JOptionPane.CLOSED_OPTION) {
					log.info("JOptionPane closed - FAIL and contiue");
					throw new RuntimeException(emsg);
				} else {
					log.info("Unknown Option?  ERROR .........");
					throw new RuntimeException(emsg);
				}
			}
		}
		throw new RuntimeException("Unable to find we for locator " + locID);
	}

	public boolean click(String locId) {
		String iMsg = "";
		try {
			iMsg = "Check/Wait for WebElement for locid  to be clickable ";
			WebElement we = pageORLocator.waitLocatorClickable(browser, locId, iwaitTime);
			we.click();
			if (debug_level > 1) {
				log.debug("Clicked for locid=[" + locId + "], we=" + we);
			}

		} catch (Exception e) {
			String eMsg = "locId=" + locId + ", failed to sendkeys ---" + e.getMessage();
			log.error(eMsg);
			throw new RuntimeException(eMsg);
		}
		return (true);
	}

	public boolean enterText(String locId, String sText) {
		String iMsg = "";
		try {
			iMsg = "Check/Wait for WebElement for locid  to be clickable ";
			WebElement we = pageORLocator.waitLocatorClickable(browser, locId, iwaitTime);

			we.click();
			we.clear();
			we.sendKeys(sText);
			if (debug_level > 1) {
				log.debug("Entered text [" + sText + "] for locid=[" + locId + "], we=" + we.toString());
			}
		} catch (Exception e) {
			String eMsg = "locId=" + locId + ", failed to sendkeys ---" + e.getMessage();
			log.error(eMsg);
			throw new RuntimeException(eMsg);
		}
		return (true);
	}

	public static boolean setCheckBox(String chkId, int checkFlag) {
		return (true);
	}

	public static boolean clickRadioButton(String rbtnId) {
		return (true);
	}

	public static boolean selectDropDown(String ddId, String ddSelection) {
		return (true);
	}

	public static boolean enterCombo(String comboId, String entryValue) {
		return (true);
	}

	public boolean loadPageOrLocator(String pageName) {
		String locf_dir = System.getenv("LOCATOR_FILES_DIR");
		if (locf_dir == null) {
			locf_dir = TestSettings.getResourcesDir();
		}

		String objRepoFileName = locf_dir + File.separator + pageName + "_objrepo.csv";
		log.info("Load locator objRepo for page=[" + pageName + "],  repo file=[" + objRepoFileName + "]");
		File cfile = new File(objRepoFileName);
		if (cfile.exists()) {
			if (debug_level > 1)
				log.info("objRepoFileName=[" + objRepoFileName + "]");
		} else {
			String eMsg = "Error Loading pageORLocator: Can not find objRepoFileName=[" + objRepoFileName + "]";
			log.error(eMsg);
			throw new RuntimeException(eMsg);

		}
		pageORLocator = new ObjRepoLocator(browser, objRepoFileName);
		log.info("Locator loaded: objRepo for page=[" + pageName + "],  repo file=[" + objRepoFileName + "]");

		return (true);
	}

	/**
	 * @param url
	 * @param pgVlidationText
	 * @param pgLoadTimeLimit
	 * @return
	 */
	public boolean go(String url, String pgVlidationText, int pgLoadTimeLimit) {
		log.debug("get URL=" + url);
		browser.get(url);
		waitForPageLoad();
		// logic for validation for navigating to correct page ....

		log.debug("Done get URL=" + url);

		return (true); // TODO raise exception on failure .....
	}

	protected WebElement L_testElementClickable(String locId) {
		WebElement we = pageORLocator.waitLocatorClickable(browser, locId, iwaitTime);
		return (we);
	}

	protected WebElement L_testElementVisible(String locId) {
		WebElement we = pageORLocator.waitLocatorVisible(browser, locId, iwaitTime);
		return (we);
	}

	/**
	 * Method to click a WebElement for locId and optionally wait for some
	 * element to become visible. It also records as a transaction time between
	 * these two events In interactive mode it will pop up a JOptionPane with
	 * option to fix the issue
	 * 
	 * @param locId
	 *            WebElement for locId to click.
	 * @param locIdToWaitFor
	 *            Element to wait to become visible - no wait it element is null
	 * @param description
	 *            Description about the action performed on the UI - Format ...
	 *            TODO
	 */
	public void iClick_0(String locId, String locIdToWaitFor, String description) {
		log.debug("\n**********************    iClickWithPopup " + description + "***************\n");
		String iMsg = "";

		WebElement we = null;
		try {
			iMsg = "Finding clickable WebElement: ";
			we = L_testElementClickable(locId);

			iMsg = "Click WebElement: " + we.toString() + ", innerHTML" + we.getText();
			startTransaction(testName);
			browserDependentClick(we);

			iMsg = "Witing for PageLoadComplete: ";
			waitforPageLoadComplete();

			if (!(locIdToWaitFor == null)) {
				iMsg = "waitForElement: ";
				WebElement weToWaitFor = L_testElementVisible(locIdToWaitFor);
			}
			endTransaction(testName, description, null); // TODO record
															// transaction as
															// passed .....
			log.debug("Successfully clicked " + description + "\n");
			return;

		} catch (Exception e) {
			int nlevels = 4;
			String ctrace = TestUtils.getExceptionCallTrace(e, nlevels);
			String exMsg = "Click+Check Failed " + description + ": " + iMsg + e.getMessage(); // TODO
																								// beautify
																								// ......
			String popUpMsg = "\nClicking on WebElement failed!\n" + iMsg + "\n" + description + "\n" + ctrace + "\n";
			we_ErrorInteraction_PopUpCheck(popUpMsg, exMsg, description);
		}
	}
	
	
	/**
	 * @param locId
	 * @param sText
	 * @param transactionDescription
	 */
	public void enterText(String locId, String sText, String transactionDescription) {
		log.debug("\n**********************    enterText locId=[" + locId + "]  ***************\n");
		WebElement we = null;
		boolean edone = false;
		int tryCount = 0;
		String iMsg = "" ;
		String lstr = "??" ;
		By lby  ;
			
		while (!edone) {
			try {
				lby =  pageORLocator.getObjRepoLocatorBy(locId) ;
				lstr = pageORLocator.getObjRepoLocator(locId).loc_hash.get("locStr") ;
				iMsg = "Finding WebElement to enter text: locId=[" + locId + "], locStr=[" + lstr  + "]  by=" + lby ;
				log.trace(iMsg) ;
				we = L_testElementClickable(locId) ;
				
				iSendKeysWithCheck(we, sText);
				log.debug("Successfully entered text [" + sText  +  "], we=" + we) ;
				highligntWE(we);
				return ;			
			} catch (Exception e) {
				String cMsg = "Failed to success fully click [" + locId + "] and check --- " ;
				int jopResp = 0 ;
				jopResp =   locid_catchWithPopup(e, cMsg, iMsg) ;
				if (jopResp > 0) {
					return ;  // popup selection set to pass ....
				}
				tryCount++ ;
				log.error("Retry enterText #" + tryCount);
				// back to loop ..
			}
		}
	}
	
	
	
	

	/**
	 * @param locId
	 * @param locIdToWaitFor
	 * @param transactionDescription
	 */
	public void iClick(String locId, String locIdToWaitFor, String transactionDescription) {
		WebElement we = null;
		boolean edone = false;
		int tryCount = 0;
		String iMsg = "";
		String lstr = "??";
		By lby;

		while (!edone) {
			try {
				iMsg = "Finding clickable WebElement: ";
				lby = pageORLocator.getObjRepoLocatorBy(locId);
				lstr = pageORLocator.getObjRepoLocator(locId).loc_hash.get("locStr");
				iMsg = "Finding clickable WebElement: locId=[" + locId + "], locStr=[" + lstr + "]  by=" + lby;
				log.trace(iMsg);
				we = L_testElementClickable(locId);

				iMsg = "Click WebElement: " + we.toString() + ", innerHTML" + we.getText();
				// startTransaction(testName);
				browserDependentClick(we);

				iMsg = "Waiting for PageLoadComplete: ";
				log.trace(iMsg);
				waitforPageLoadComplete();

				if (!(locIdToWaitFor == null)) {
					iMsg = "waitForElement: ";
					lby = pageORLocator.getObjRepoLocatorBy(locIdToWaitFor);
					lstr = pageORLocator.getObjRepoLocator(locIdToWaitFor).loc_hash.get("locStr");
					iMsg = "Wait for element: locId=[" + locIdToWaitFor + "], locStr=[" + lstr + "]  by=" + lby;
					log.trace(iMsg);
					WebElement weToWaitFor = L_testElementVisible(locIdToWaitFor);
				}

				// endTransaction(testName, transactionDescription, null); //
				// TODO // record // transaction // as passed // .....

				log.debug("Successfully clicked " + transactionDescription + "\n\n");

				// return (true);
				return;

			} catch (Exception e) {
				String emsg = e.getMessage();
				// String popupMsg = "Unable to find WebElement for [" + locId +
				// "],\n locator=[" + lstr + "] ";
				String cMsg = "Failed to success fully click [" + locId + "] and check --- ";

				int jopResp = 0;
				// jopResp = locId_ErrorInteraction_PopUpCheck(popupMsg, emsg,
				// null);

				jopResp = locid_catchWithPopup(e, cMsg, iMsg);

				if (jopResp > 0) {
					return;
				}

				/*
				 * if (jopResp < 0) {
				 * log.info("JOptionPane closed - FAIL and contiue"); throw new
				 * RuntimeException(emsg); }
				 */

				tryCount++;
				log.error("Retry iClick #" + tryCount);
				// back to loop ..

			}
		}
		throw new RuntimeException("Unable to find/click we for locator " + locId);
	}

	public int locid_catchWithPopup(Exception e, String cMsg, String iMsg) {

		int nlevels = 4;
		String ctrace = TestUtils.getExceptionCallTrace(e, nlevels);
		String exMsg = cMsg + ": " + iMsg + e.getMessage(); // TODO beautify
															// ......
		String popUpMsg = "\n " + cMsg + "\n" + iMsg + "\n\n" + ctrace + "\n";

		// we_ErrorInteraction_PopUpCheck(popUpMsg, exMsg, null);
		return (locId_ErrorInteraction_PopUpCheck(popUpMsg, exMsg, null));

	}

	public int locId_ErrorInteraction_PopUpCheck(String popUpMsg, String emsg, String transactionDescription) {
		int jopResp = TestUtils.passLoadRetryPopUp(popUpMsg, emsg);

		if (jopResp == JOptionPane.CLOSED_OPTION) {
			log.error("JOptionPane closed - FAIL and contiue");
			// endTransaction(testName, transactionDescription, null); // TODO
			// // record // transaction // as failed // .....
			throw new RuntimeException(emsg);
		}

		// endTransaction(testName, transactionDescription, null); // TODO //
		// record // transaction // as passed // .....
		if (jopResp == JOptionPane.YES_OPTION) {
			log.info("1. button clicked - Pass");
			log.info("Retry .......");
			return (1);

		} else if (jopResp == JOptionPane.NO_OPTION) {
			log.info("2.  button clicked  - Retry");
			return (0);

		} else if (jopResp == JOptionPane.CANCEL_OPTION) {
			log.info("3.  button  clicked - Load/Retry");
			// ComcastTest.reloadObjRepo();
			log.error("Try - Reload locator ...");
			pageORLocator.reloadIfModified();
			log.error("Reload locator try done");
			return (0);

		} else {
			log.info("Unknown Option?  ERROR ......... [" + jopResp + "]");
			throw new RuntimeException(emsg);
		}

	}

	public void XX_enterText(String locId, String sText, String description) {
		log.debug("\n**********************    enterText locId=[" + locId + "]  ***************\n");
		String iMsg = "";

		WebElement we = null;
		try {
			iMsg = "Finding clickable WebElement for locId=[" + locId + ": ";
			we = L_testElementClickable(locId);
			iMsg = "enterText WebElement: " + we.toString() + ", innerHTML" + we.getText();
			iSendKeysWithCheck(we, sText);
			log.debug("Successfully entered text [" + sText + "], we=" + we);
			highligntWE(we);
			return;
		} catch (Exception e) {
			int nlevels = 4;
			String ctrace = TestUtils.getExceptionCallTrace(e, nlevels);
			String exMsg = "Click+Check Failed " + description + ": " + iMsg + e.getMessage(); // TODO
																								// beautify
																								// ......
			String popUpMsg = "\nClicking on WebElement failed!\n" + iMsg + "\n" + description + "\n" + ctrace + "\n";
			we_ErrorInteraction_PopUpCheck(popUpMsg, exMsg, null);
		}
	}

	public void highligntWE(WebElement we) {
		JavascriptExecutor js = (JavascriptExecutor) browser;
		js.executeScript("arguments[0].style.border='3px dotted blue'", we);
	}

}
