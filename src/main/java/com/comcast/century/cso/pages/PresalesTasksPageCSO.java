package com.comcast.century.cso.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;






public class PresalesTasksPageCSO extends Page {

	public PresalesTasksPageCSO(FrameworkContext context) {
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
	
	Logger log = Logger.getLogger(PresalesTasksPageCSO.class);
	
	@FindBy(xpath = "//*[text()='Conduct Coax Survey']")
	private WebElement taskConductCoaxSurvey;
	
	@FindBy(xpath = "//*[@id='surveyCompDate']/following-sibling::img")
	private WebElement surveyCompletionDate;
	
	@FindBy(xpath = "//button[text()='Today']")
	private WebElement btnToday;
	
	@FindBy(xpath = "//span[text()='Coax Survey Results']")
	private WebElement tabCoaxSurveyResults;
	
	@FindBy(xpath = "//*[@id='comboHomeheadEndNameZip']/following-sibling::img")
	private WebElement ddArrwHeadendName;
	
	@FindBy(xpath = "//div[text()='a1atlanta.ga']")
	private WebElement ddValueHeadendName;
	
	@FindBy(id = "nodeNumber")
	private WebElement txtNodeNumber;
	
	@FindBy(id = "serviceable")
	private WebElement ddServiceable;
	
	@FindBy(xpath = "//input[@value='Complete']")
	private WebElement btnComplete;
	
	@FindBy(xpath = "//button[text()='Yes']")
	private WebElement btnYes;
	
	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading ;
	
	private boolean mstatus=true;
	
	public boolean ConductCoaxSurvey() throws InterruptedException{
		try{
			if(waitForElement(taskConductCoaxSurvey)){
				taskConductCoaxSurvey.click();
				waitforPageLoadComplete();
				waitForElement(surveyCompletionDate);
				surveyCompletionDate.click();
				btnToday.click();
				clickndRelease(tabCoaxSurveyResults);
				waitForElement(ddArrwHeadendName);
				ddArrwHeadendName.click();
			    Thread.sleep(5*1000);
			    ddValueHeadendName.click();
				txtNodeNumber.sendKeys(randomNumber(5));
				new Select(ddServiceable).selectByValue("Yes");
				waitForElement(btnComplete);
				iClick(btnComplete, null, "Complete ConductCoaxServey Task: Complete ConductCoaxServey Task Page: CompleteButton");
				btnYes.click();
				waitforPageLoadComplete();
				btnYes.click();
				waitforPageLoadComplete();	
			}
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	
	
	
           
	

}
