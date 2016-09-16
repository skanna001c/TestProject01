package com.comcast.century.cm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.utils.SeleniumReport;
import com.comcast.utils.ComcastTest.FrameworkContext;

public class SupplementPageServiceTabCM extends Page {

	protected SupplementPageServiceTabCM(WebDriver browser, SeleniumReport report) {
		super(browser, report);
		// TODO Auto-generated constructor stub
	}
	public SupplementPageServiceTabCM(FrameworkContext context){
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

	private boolean mstatus;	
	
	@FindBy(xpath = "//*[@id='mainFrame']")
	private WebElement frameMain;
	
	@FindBy(xpath = "//*[@id='leftFrame']")
	private WebElement frameLeft;
	
	@FindBy(xpath = "//*[@placeholder='---More Actions---']")
	private WebElement ddValueMoreActions;
	
	@FindBy(xpath = "//li[.='Tech Sup']")
	private WebElement ddValueTechSup;
	
	@FindBy(xpath = "//li[.='Cancel Sup']")
	private WebElement ddValueCancelSup;
	
	@FindBy(xpath = "//li[.='Admin Sup']")
	private WebElement ddValueAdminSup;
	
	@FindBy(xpath = "//input[@servicename='EPL']")
	private WebElement chkBoxEPL;
	
	@FindBy(xpath = "//*[text()='Go ']/../following-sibling::*")
	private WebElement btnGo;
	
	public boolean cancelSupEPL(){
		mstatus = true;
		try{
			 WaitandSwitchToFrame(frameMain);
			 waitForElement(chkBoxEPL);
			 iClick(chkBoxEPL);
			 waitForElement(ddValueMoreActions);
			 ddValueSelect(ddValueMoreActions,ddValueCancelSup,"Cancel Sup");
			 waitForElement(btnGo);
			 iClick(btnGo);
			 waitforPageLoadComplete();
			 browser.switchTo().defaultContent();
		}catch (Exception e) {
			mstatus = true;
			e.printStackTrace();
			
	}
		return mstatus;

}
	
}
