package com.comcast.data;

// TODO change  System.out.println to log4j log .....

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.comcast.utils.CsvFileReader;

public class ObjRepoLocator {
	public static int debug_level = 10;

	public static final String kLocId = "locId";
	public static final String kLocType = "locType";
	public static final String kLocStr = "locStr";
	public static final String kWeType = "weType";

	public static final String locTypeId = "id";
	public static final String locTypeName = "name";
	public static final String locTypeCSS = "css";
	public static final String locTypeXPATH = "xpath";
	public static final String locTypeClass = "class";
	public static final String locTypeTag = "tag";
	public static final String locTypeLink = "link";
	public static final String locTypePLink = "partiallink";

	String _objRepoFileName = "Not_Set";

	// HashMap<String, HashMap<String, String>> loc_hash_hash;
	HashMap<String, Map<String, String>> csv_hash_hash;

	HashMap<String, x1Locator> xloc_hash;
	WebDriver driver;

	// TODO - add browser type to handle special handling for IE etc .... ????
	public ObjRepoLocator(WebDriver wd, String objRepoFileName) {
		driver = wd;
		_objRepoFileName = objRepoFileName;
		int rcount = -1;

		// load loc_hash_hash from objRepoFileName
		csv_hash_hash = new HashMap<String, Map<String, String>>();
		xloc_hash = new HashMap<String, x1Locator>();
		if (debug_level > 0) {
			System.out.println("Read csv obj repo file=[" + objRepoFileName + "]");
		}
		rcount = CsvFileReader.readCsvFileToMap(objRepoFileName, "", csv_hash_hash);

		if (rcount < 1) {
			System.out
					.println("Unable to read obj repository records from [" + objRepoFileName + "], rcount=" + rcount);
			System.exit(1);
			// TODO raise error .....
		}

		// for (Map.Entry<String, HashMap<String, String>> entry : loc_hash_hash
		for (Map.Entry<String, Map<String, String>> entry : csv_hash_hash.entrySet()) {
			String locid = entry.getKey();
			Map<String, String> loc_entry = entry.getValue();
			if (debug_level > 0) {
				System.out.println("locid=[" + entry.getKey() + "],  value=" + loc_entry);
			}
			xloc_hash.put(locid, new x1Locator(wd, locid, loc_entry));
		}
	}
	
	public ObjRepoLocator(WebDriver wd, HashMap<String, Map<String, String>> csv_hash_hash) {
		driver = wd;

		// load loc_hash_hash from objRepoFileName
		this.csv_hash_hash = new HashMap<String, Map<String, String>>();
		xloc_hash = new HashMap<String, x1Locator>();

		// for (Map.Entry<String, HashMap<String, String>> entry : loc_hash_hash
		for (Map.Entry<String, Map<String, String>> entry : csv_hash_hash.entrySet()) {
			String locid = entry.getKey();
			Map<String, String> loc_entry = entry.getValue();
			if (debug_level > 0) {
				System.out.println("locid=[" + entry.getKey() + "],  value=" + loc_entry);
			}
			xloc_hash.put(locid, new x1Locator(wd, locid, loc_entry));
		}
	}
	

	public void add_x1locator(String locid, HashMap<String, String> loc_hash) {
		xloc_hash.put(locid, new x1Locator(driver, locid, loc_hash));
	}

	public void add_min_x1locator(String locid, String loctype, String locstr, String wetype) {
		HashMap<String, String> loc_hash = new HashMap<String, String>();
		loc_hash.put(kLocId, locid);
		loc_hash.put(kLocType, loctype);
		loc_hash.put(kLocStr, locstr);
		loc_hash.put(kWeType, wetype);
		xloc_hash.put(locid, new x1Locator(driver, locid, loc_hash));
	}

	public void print_Xlocator() {
		// TODO ......
	}

	//====================================================================================================
	//
	//====================================================================================================
	public x1Locator get_x1Locator(String locid) {
		x1Locator xl = xloc_hash.get(locid);
		if (xl == null) {
			String emsg = "Can not find locator entry for locid=[" + locid + "]";
			System.out.println(emsg);
			throw new RuntimeException(emsg);
			// return null; // raise error ....
		}
		return (xl);
	}

	public By getBy(String locid) {
		x1Locator xl = get_x1Locator(locid);
		return (xl.lby);
	}

	public WebElement waitWE(String locid, int tSec) {
		WebElement we = null;
		x1Locator xl = get_x1Locator(locid);
		WebDriverWait wdwt = new WebDriverWait(driver, tSec);
		if (debug_level > 1) {
			System.out.println("Wait to be clickable locid=[" + locid + "], by=" + xl.lby.toString());
		}
		we = wdwt.until(ExpectedConditions.elementToBeClickable(xl.lby));
		if (we == null) {
			String emsg = "Timed out waiting for locid=[" + locid + "], by=" + xl.lby.toString();
			System.out.println(emsg);
		}

		return (we);
	}

	public WebElement getWE(String locid) {
		x1Locator xl = get_x1Locator(locid);
		WebElement we = driver.findElement(xl.lby);
		if (debug_level > 1) {
			if (we == null) {
				System.out.println("null WE found for locid=[" + locid + "]");
			} else {
				if (debug_level > 5) {
					System.out.println("WE found for locid=[" + locid + "], we=" + we.toString());
				}
			}
		}
		return (we);
	}

	public List<WebElement> getWEList(String locid) {
		x1Locator xl = get_x1Locator(locid);
		List<WebElement> we_list = driver.findElements(xl.lby);
		return (we_list);
	}

	// ===============================================================================================================
	// ===============================================================================================================

	public class x1Locator {
		Map<String, String> loc_hash;
		WebDriver wd;
		String _locId;
		String locType = "";
		String locStr;
		int tabind = 0;
		WebElement we = null;
		List<WebElement> we_list;
		By lby;

		public x1Locator(WebDriver driver, String locId, Map<String, String> loc_hash) {
			// HashMap<String, String> loc_hash) {
			wd = driver;
			_locId = locId;
			locType = loc_hash.get(kLocType);
			locStr = loc_hash.get(kLocStr);
			String emsg;

			if ((locType == null) || (locStr == null)) {
				emsg = "Null value/s corresponding to locId [" + locId + "],  loc_hash=" + loc_hash.toString();
				System.out.println(emsg);
				throw new RuntimeException(emsg);
			}  
			lby = getByForLolcator(locType, locStr);
			if (lby == null) {
				emsg = "Failed to find by for locType[=" + locType + "], locStr=[" + locStr + "],  loc_hash="
						+ loc_hash.toString();
				System.out.println(emsg);
				throw new RuntimeException(emsg);
			}
			this.loc_hash = loc_hash;
			if (debug_level > 1) { System.out.println("added by [" + lby.toString() + "]");	}
		}

		/**
		 * Return By object corresponding to the locator TODO - enhance for
		 * custom types e.g. "innertext" - change to xpath ??
		 * 
		 * @param locType
		 * @param locStr
		 * @return
		 */
		// TODO  change to JAVA 1.8 .. switch statement
		public By getByForLolcator(String locType, String locStr) {
			By by;
			String lc_ltype = locType.toLowerCase() ;
			if (lc_ltype.equals("id")) {
				by = By.id(locStr);
			} else if (lc_ltype.equals("name")) {
				by = By.name(locStr);
			} else if (lc_ltype.equals("css")) {
				by = By.cssSelector(locStr);
			} else if (lc_ltype.equals("xpath")) {
				by = By.xpath(locStr);
			} else if (lc_ltype.equals("tag")) {
				by = By.tagName(locStr);
			} else if (lc_ltype.equals("link")) {
				by = By.linkText(locStr);
			} else if (lc_ltype.equals("class")) {
				by = By.className(locStr);
			} else if (lc_ltype.equals("partiallink")) {
				by = By.partialLinkText(locStr);
			} else {
				String emsg = "Undefined locator type [" + locType + "]";
				System.out.println(emsg);
				return (null); // raise exception .....
			}
			return (by);
		}

	}

}
