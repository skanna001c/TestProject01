package com.comcast.pages;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class GooglePage extends SFRPage {

	Logger log = Logger.getLogger(GooglePage.class);

	public GooglePage(FrameworkContext context) {	
		super(context, "GooglePage");
		log.debug("Done calling Super SRFPage");
	}

	public void launch() {
		String googleURL = " https://google.com";
		go(googleURL, "google", 10);
	}

	@Override
	protected boolean isValidPage() {
		return true;
	}

	@Override
	protected void waitForPageLoad() {
	}

	public boolean google_search() {

		String gurl = "https://google.com";

		go(gurl, "google", 10);

		String locId = "txtSearch";
		String sText = "Seleinum webdriver";

		enterText(locId, sText, null);

		return (true);
	}

}
