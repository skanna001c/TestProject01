package com.comcast.pages;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.comcast.commons.SFRPage;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class GooglePage extends SFRPage {

	Logger log = Logger.getLogger(GooglePage.class);
	

	// Needed only for intellisense completion and avoiding spelling errors ....
	public static class Locators {
		public static final String txtSearch = "txtSearch";
		public static final String btnSubmit = "btnSubmit";
		public static final String XXbtnSubmit = "XXbtnSubmit";
		public static final String submit = "submit";
		//XXbtnSubmit

	}


	public GooglePage(FrameworkContext context) {	
		super(context, "GooglePage");
		log.debug("Done calling Super SRFPage");
	}


	@Override
	protected boolean isValidPage() {
		return true;
	}

	@Override
	protected void waitForPageLoad() {
	}

	
	public void launch() {
		String gurl = "https://google.com";
		String titleCheckStr  = "google" ;
		int tmOutSec = 10 ;
		

		try {
			go(gurl, titleCheckStr, tmOutSec);
		} catch (Exception e) {		
			e.printStackTrace();
			String eMsg = "launch Failed: "   + e.getMessage() ;
			log.error(eMsg);
			sleep(10000);
			throw new RuntimeException(eMsg);
		}
		// sleep(5000);
	}
	
	public void google_search() {		
		String srchStr = "Selenium web driver";
		
		try {
			
			// WebElement we = L_testElementClickable(locids.txtSearch) ;
			//highligntWE(we);		
			//WebElement btnwe = L_testElementClickable(locids.btnSubmit) ;
			//highligntWE(btnwe);			
			// sleep(10000);
			
			enterText(Locators.txtSearch, srchStr);
	
			iClick("btmSubmit", null, "Search Button");
			iClick(Locators.btnSubmit, null, "Search Button");
			
		} catch (Exception e) {		
			String eMsg = "Search Failed: "   + e.getMessage() ;
			log.error(eMsg);
			throw new RuntimeException(eMsg);
		}		
		// sleep(5000);

	}
	
	public void google_search_err() {		
		String srchStr = "Selenium web driver";
		
		try {			
			enterText(Locators.txtSearch, srchStr);
			iClick(Locators.XXbtnSubmit, null, "Search Button");
			
		} catch (Exception e) {		
			String eMsg = "Search Failed: "   + e.getMessage() ;
			log.error(eMsg);
			throw new RuntimeException(eMsg);
		}		
		sleep(5000);

	}

}
