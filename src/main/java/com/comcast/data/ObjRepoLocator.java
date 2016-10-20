package com.comcast.data;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// import com.comcast.template.Mod1_PriorityCases;
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
	long _fileModTime = -1;
	int _rcount = -1;

	static Logger log = Logger.getLogger(ObjRepoLocator.class.getName());

	// HashMap<String, HashMap<String, String>> loc_hash_hash;
	HashMap<String, Map<String, String>> csv_hash_hash;

	HashMap<String, x1Locator> xloc_hash;

	public ObjRepoLocator(WebDriver wd, String objRepoFileName) {
		_objRepoFileName = objRepoFileName;

		// load loc_hash_hash from objRepoFileName
		csv_hash_hash = new HashMap<String, Map<String, String>>();
		_rcount = loadObjRepoFromCSVFile(objRepoFileName,  csv_hash_hash) ;
		
		File orFile = new File(_objRepoFileName) ;
		_fileModTime = orFile.lastModified() ;	
	}
	
	public int reloadIfModified() {
		int rcount = -1 ;
		
		File orFile = new File(_objRepoFileName) ;
		long fmodt = orFile.lastModified() ;
		if (fmodt<=_fileModTime) {
			log.info("No reload, file not modified [" +  _objRepoFileName + "] t=" + fmodt );
			return(0) ;
		}
		
		log.info("File modfied,  reload [" +  _objRepoFileName + "] tdiff=" + (fmodt-_fileModTime) );
		HashMap<String, Map<String, String>> new_csv_hash_hash = new HashMap<String, Map<String, String>>();
		rcount = loadObjRepoFromCSVFile(_objRepoFileName,  new_csv_hash_hash) ;
		csv_hash_hash = new_csv_hash_hash ;	
		_fileModTime = fmodt ;
		_rcount = rcount ;
		return(rcount) ;
	}

	public int loadObjRepoFromCSVFile(String objRepoFileName, HashMap<String, Map<String, String>> csv_hsh_hash) {
		int rcount = -1;

		xloc_hash = new HashMap<String, x1Locator>();
		rcount = CsvFileReader.readCsvFileToMap(objRepoFileName, "", csv_hash_hash);
		if (rcount < 1) {
			String eMsg = "Unable to read obj repository records from [" + objRepoFileName + "], rcount=" + rcount;
			log.error(eMsg);
			throw new RuntimeException(eMsg);
		}
		log.info("Read objRepoFileName=[" + objRepoFileName + "], rcount=" + rcount);

		// for (Map.Entry<String, HashMap<String, String>> entry : loc_hash_hash
		for (Map.Entry<String, Map<String, String>> entry : csv_hash_hash
				.entrySet()) {
			String locid = entry.getKey();
			Map<String, String> loc_entry = entry.getValue();
			if (debug_level > 2) {
				log.trace("locid=[" + entry.getKey() + "],  loc_entry=" + loc_entry);
			}
			xloc_hash.put(locid, new x1Locator(locid, loc_entry));
		}

		return (rcount);
	}

	public ObjRepoLocator(HashMap<String, Map<String, String>> csv_hash_hash) {
		// load loc_hash_hash from csv_hash_hash
		csv_hash_hash = new HashMap<String, Map<String, String>>();
		xloc_hash = new HashMap<String, x1Locator>();

		// for (Map.Entry<String, HashMap<String, String>> entry : loc_hash_hash
		for (Map.Entry<String, Map<String, String>> entry : csv_hash_hash
				.entrySet()) {
			String locid = entry.getKey();
			Map<String, String> loc_entry = entry.getValue();
			if (debug_level > 0) {
				log.debug("locid=[" + entry.getKey() + "],  value=" + loc_entry);
			}
			xloc_hash.put(locid, new x1Locator(locid, loc_entry));
		}
	}

	public void add_x1locator(String locid, HashMap<String, String> loc_hash) {
		xloc_hash.put(locid, new x1Locator(locid, loc_hash));
	}

	public void add_min_x1locator(String locid, String loctype, String locstr,
			String wetype) {
		HashMap<String, String> loc_hash = new HashMap<String, String>();
		loc_hash.put(kLocId, locid);
		loc_hash.put(kLocType, loctype);
		loc_hash.put(kLocStr, locstr);
		loc_hash.put(kWeType, wetype);
		xloc_hash.put(locid, new x1Locator(locid, loc_hash));
	}

	public void print_Xlocator() {
		// TODO ......
	}

	// ====================================================================================================
	//
	// ====================================================================================================
	
	public boolean existsObjRepoLocator(String locid) {
		x1Locator xl = xloc_hash.get(locid);
		if (xl == null) {
			return(false) ;
		}
		return (true);
	}
	
	public x1Locator getObjRepoLocator(String locid) {
		x1Locator xl = xloc_hash.get(locid);
		if (xl == null) {
			String emsg = "Can not find locator entry for locid=[" + locid + "]";
			log.error(emsg);
			throw new RuntimeException(emsg);
		}
		return (xl);
	}

	public By getObjRepoLocatorBy(String locid) {
		x1Locator xl = getObjRepoLocator(locid);
		return (xl.lby);
	}

	public WebElement waitLocatorVisible(WebDriver wd, String locid, long tSec) {
		WebElement we = null;
		By xby = getObjRepoLocatorBy(locid) ;
		
		WebDriverWait wdwt = new WebDriverWait(wd, tSec);
		if (debug_level > 1) {
			log.debug("Wait to be clickable locid=[" + locid + "], by="
					+ xby.toString() + ", tSec=" + tSec);
		}
		we = wdwt.until(ExpectedConditions.visibilityOfElementLocated(xby));
		if (we == null) {
			String emsg = "Timed out waiting for locid=[" + locid + "], by="
					+ xby.toString();
			log.error(emsg);
			throw new RuntimeException(emsg);
		}

		return (we);
	}
	
	
	
	public WebElement waitLocatorClickable(WebDriver wd, String locid, long tSec) {
		WebElement we = null;
		By xby = getObjRepoLocatorBy(locid) ;
		
		WebDriverWait wdwt = new WebDriverWait(wd, tSec);
		if (debug_level > 1) {
			log.debug("Wait to be clickable locid=[" + locid + "], by="
					+ xby.toString()+ ", tSec=" + tSec);
		}
		we = wdwt.until(ExpectedConditions.elementToBeClickable(xby));
		if (we == null) {
			String emsg = "Timed out waiting for locid=[" + locid + "], by="
					+ xby.toString();
			log.error(emsg);
			throw new RuntimeException(emsg);
		}

		return (we);
	}
	
	

	public WebElement getLocatorWE(WebDriver wd, String locid) {
		By xby = getObjRepoLocatorBy(locid) ;
			
		WebElement we = wd.findElement(xby);
		if (debug_level > 1) {
			if (we == null) {
				log.error("null WE found for locid=[" + locid + "]");
			} else {
				if (debug_level > 5) {
					log.debug("WE found for locid=[" + locid + "], we="
							+ we.toString());
				}
			}
		}
		return (we);
	}

	public List<WebElement> getLocatorWEList(WebDriver wd, String locid) {
		By xby = getObjRepoLocatorBy(locid) ;
		List<WebElement> we_list = wd.findElements(xby);
		return (we_list);
	}

	// ===============================================================================================================
	// ===============================================================================================================

	public class x1Locator {
		public Map<String, String> loc_hash;
		String _locId;
		String locType = "";
		String locStr;
		int tabind = 0;
		WebElement we = null;
		List<WebElement> we_list;
		By lby;

		public x1Locator(String locId, Map<String, String> loc_hash) {
			// HashMap<String, String> loc_hash) {
			_locId = locId;
			locType = loc_hash.get(kLocType);
			locStr = loc_hash.get(kLocStr);
			String emsg;

			if ((locType == null) || (locStr == null)) {
				emsg = "Null value/s corresponding to locId [" + locId
						+ "],  loc_hash=" + loc_hash.toString();
				log.error(emsg);
				throw new RuntimeException(emsg);
			}
			lby = getByForLolcator(locType, locStr);
			if (lby == null) {
				emsg = "Failed to find by for locType[=" + locType
						+ "], locStr=[" + locStr + "],  loc_hash="
						+ loc_hash.toString();
				log.error(emsg);
				throw new RuntimeException(emsg);
			}
			this.loc_hash = loc_hash;
			if (debug_level > 1) {
				log.trace("added by [" + lby.toString() + "]");
			}
		}

		/**
		 * Return By object corresponding to the locator TODO - enhance for
		 * custom types e.g. "innertext" - change to xpath ??
		 * 
		 * @param locType
		 * @param locStr
		 * @return
		 */
		// TODO change to JAVA 1.8 .. switch statement
		public By getByForLolcator(String locType, String locStr) {
			By by;
			String lc_ltype = locType.toLowerCase();
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
				String emsg = "Undefined locator type [" + locType + "] - locStr=[" + locStr + "]";
				log.error(emsg);
				return (null); 
			}
			return (by);
		}

	}

}
