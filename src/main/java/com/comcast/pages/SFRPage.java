package com.comcast.pages;

import java.io.File;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.comcast.data.ObjRepoLocator;
import com.comcast.template.ComcastTest;
import com.comcast.template.Mod1_PriorityCases;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;
import com.comcast.utils.TestSettings;
import com.comcast.utils.TestUtils;

public class SFRPage extends Page {

	WebDriver driver;
	
	public static int debug_level = 10;
	
	public static ObjRepoLocator pageWebElemLocator = null;
	
	Properties pgWebEleRepoProp;	
	
	static Logger log = Logger.getLogger(Mod1_PriorityCases.class.getName());

	protected SFRPage(FrameworkContext context) {
		super(context);		
	}
	
	protected SFRPage(FrameworkContext context, Properties pro) {
		super(context);
		this.driver = context.getDriver();
		this.pgWebEleRepoProp = pro;		
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
			we = driver.findElement(By.linkText(pgWebEleRepoProp.getProperty(locid)));

		} catch (Exception e) {
			String emsg = "Unable to find [" + locid + "],  locator=["
					+ pgWebEleRepoProp.getProperty(locid) + "] " + e.getMessage();
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
			we = driver.findElement(By.xpath(pgWebEleRepoProp.getProperty(locid)));

		} catch (Exception e) {
			String emsg = "Unable to find [" + locid + "],  locator=["
					+ pgWebEleRepoProp.getProperty(locid) + "] " + e.getMessage();
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
			if (we==null) {
				System.out.println("Asked to pass the click - must have done manully :) ...");
				return (true) ;
			}
		} catch (Exception e) {
			String e1 = e.getMessage();
			String emsg = "Unable to find WebElement for [" + locid + "],  locator=[" + pgWebEleRepoProp.getProperty(locid) + "] "  + e1;
			throw new RuntimeException(emsg);
		}

		try {
			we.click();
		} catch (Exception e) {
			String emsg = "Unable to click [" + locid + "],  locator=["
					+ pgWebEleRepoProp.getProperty(locid) + "], we=[" + we + ",]"
					+ e.getMessage();
			System.err.println(emsg);
			throw new RuntimeException(emsg);
		}

		System.out.println("Clicked [" + locid + "],  we=" + we);
		return (true);
	}
	
	public boolean iLclick(String locid) {
		WebElement we = null;
		try {
			we = getLWE(locid);
		} catch (Exception e) {
			String emsg = e.getMessage();
			String popupMsg = "Unable to find WebElement for [" + locid
					+ "],\n  locator=[" + pgWebEleRepoProp.getProperty(locid) + "] ";
			int ansCont = TestUtils.ynPopUp(popupMsg, emsg);
			if (ansCont == JOptionPane.YES_OPTION) {
				return (true);
			}
			throw new RuntimeException(emsg);
		}

		try {
			we.click();
		} catch (Exception e) {
			String emsg = "Unable to click [" + locid + "],  locator=["
					+ pgWebEleRepoProp.getProperty(locid) + "], we=[" + we + ",]"
					+ e.getMessage();
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
			String popupMsg = "Unable to find WebElement for [" + locid
					+ "],\n  locator=[" + pgWebEleRepoProp.getProperty(locid) + "] ";
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
			String emsg = "Unable to send text [" + locid + "],  locator=["
					+ pgWebEleRepoProp.getProperty(locid) + "], we=" + we;
			log.error(emsg);
			throw new RuntimeException(emsg);
		}

		log.info("[" + locid + "] enter text [" + stext + "] we="
				+ we);
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
				if (tryCount>0) {
					System.out.println("Retried times=" + tryCount);
					// Add Message here for  new locator ......
				}
				return (we);
			} catch (Exception e) {
				String emsg = e.getMessage();
				String popupMsg = "Unable to find WebElement for [" + locID
						+ "],\n  locator=[" + pgWebEleRepoProp.getProperty(locID) + "] ";

				int jopResp = TestUtils.passLoadRetryPopUp(popupMsg, emsg);

				if (jopResp == JOptionPane.YES_OPTION) {
					System.out.println("1. button clicked - Pass");
					System.out.println("Retry .......");
                    return null;    
				} else if (jopResp == JOptionPane.NO_OPTION) {
					System.out.println("2.  button clicked  - Retry");
				} else if (jopResp == JOptionPane.CANCEL_OPTION) {
					System.out.println("3.  button  clicked - Load/Retry");
					ComcastTest.reloadObjRepo();
					pgWebEleRepoProp = ComcastTest.loadObjRepo();
				}else if (jopResp == JOptionPane.CLOSED_OPTION) {
					System.out.println("JOptionPane closed - FAIL and contiue");
					throw new RuntimeException(emsg);
				} else {
					System.out.println("Unknown Option?  ERROR .........");
					throw new RuntimeException(emsg);
				}
			}
		}
		throw new RuntimeException("Unable to find we for locator " + locID);
	}
	
	public static boolean click(String locid) {
		// TODO should it be waitWE - check for visibility ??
		WebElement we = pageWebElemLocator.getWE(locid);
		if (we == null) {
			String emsg = "Unable to find web element for locid=[" + locid + "]";
			// throw exception ?
			throw new RuntimeException(emsg);
			// return(false) ;
		}
		we.click();
		if (debug_level > 1) {
			System.out.println("Clicked for locid=[" + locid + "], we=" + we.toString());
		}
		return (true);
	}

	public static boolean enterText(String locid, String stext) {
		WebElement we = pageWebElemLocator.getWE(locid);
		if (we == null) {
			String emsg = "Unable to find web element for locid=[" + locid + "]";
			// throw exception ?
			throw new RuntimeException(emsg);
			// return(false) ;
		}
		we.click();
		we.clear();
		we.sendKeys(stext);
		if (debug_level > 1) {
			System.out.println("Entered text [" + stext + "] for locid=[" + locid + "], we=" + we.toString());
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
	
	public boolean get_xLocator(String pageName) {
		String locf_dir = System.getenv("LOCATOR_FILES_DIR");
		if (locf_dir == null) {
			locf_dir = TestSettings.getResourcesDir() ;
		}
		System.out.println("Load Locators");
		String objRepoFileName = locf_dir + File.separator + pageName + "_objrepo.csv";
		System.out.println("Object repo file for page=[" + pageName + "]=[" + objRepoFileName + "]");
		File cfile = new File(objRepoFileName) ;
		if (cfile.exists()) {
			if (debug_level>1)
			    System.out.println("objRepoFileName=[" + objRepoFileName + "]" );
		} else {
			System.err.println("Can not find objRepoFileName=[" + objRepoFileName + "]" );
			System.exit(1);
		}		
		pageWebElemLocator = new ObjRepoLocator(driver, objRepoFileName);

		System.out.println("Locators loaded");
		return (true);
	}
	
	public boolean go(String url, String pgVlidationText, int pgLoadTimeLimit) {
		driver.get(url);
		waitForPageLoad();
		// logic for validation for navigating to correct page ....

		return (true); // raise exception on failure .....
	}
}
