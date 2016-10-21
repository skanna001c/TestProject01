package com.comcast.commons;

import java.util.Properties;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;
import com.comcast.utils.TestUtils;

public class SFRPage extends Page {

	Properties pgWebEleRepoProp = null;

	long iwaitTime = 33;

	static Logger log = Logger.getLogger(SFRPage.class.getName());

	protected SFRPage(FrameworkContext context) {
		super(context);
	}

	protected SFRPage(FrameworkContext context, String className) {
		super(context, className);
	}

	protected SFRPage(FrameworkContext context, Properties pro) {
		super(context);
		// this.BROWSER = context.getDriver();
		this.pgWebEleRepoProp = pro;
	}

	// TODO improve implementation - identify which classes are designed with
	// locators

	@Override
	protected boolean isValidPage() {
		return false;
	}

	@Override
	protected void waitForPageLoad() {
	}

	// ===============================================================================================
	// Deprecated - used with old property file locator .....
	// ===============================================================================================

	@Deprecated
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

	@Deprecated
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

	@Deprecated
	public boolean iXclick(String locid) {
		WebElement we = null;
		try {
			we = getXWE_Retry(locid);
			if (we == null) {
				log.info("Asked to pass the click - must have done manually :) ...");
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

	@Deprecated
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

	@Deprecated
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

	@Deprecated
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
	@Deprecated
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

	@Deprecated
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

	@Deprecated
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

	public static boolean isetCheckBox(String chkId, int checkFlag) {
		return (true);
	}

	public static boolean iclickRadioButton(String rbtnId) {
		return (true);
	}

	public static boolean iselectDropDown(String ddId, String ddSelection) {
		return (true);
	}

	public static boolean ienterCombo(String comboId, String entryValue) {
		return (true);
	}

}
