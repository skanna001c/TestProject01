package com.comcast.utils;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import com.comcast.reporting.*;


public class SeleniumReport extends Report {
	
	
	/**
	 * Function to set the {@link WebDriver} object
	 * 
	 * @param driver
	 *            The {@link WebDriver} object
	 */
	public void setDriver(WebDriver driver) {
		this.browser = driver;
	}

	/**
	 * Constructor to initialize the Report
	 * 
	 * @param reportSettings
	 *            The {@link ReportSettings} object
	 * @param reportTheme
	 *            The {@link ReportTheme} object
	 */
	public SeleniumReport(ReportSettings reportSettings, ReportTheme reportTheme) {
		super(reportSettings, reportTheme);
	}

	/*@Override
	protected void takeScreenshot(String screenshotPath) {
		if (driver == null) {
			throw new FrameworkException("Report.driver is not initialized!");
		}
		WebDriver augmentedDriver = new Augmenter().augment(driver);
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(scrFile, new File(screenshotPath), true);
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrameworkException(
					"Error while writing screenshot to file");
		}
	}
	*/
	
	
	@Override
	protected void takeScreenshot(String screenshotPath) {
		if (browser == null) {
			throw new FrameworkException("Report.driver is not initialized!");
		}

		File scrFile = ((TakesScreenshot) browser)
				.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(scrFile, new File(screenshotPath), true);
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrameworkException(
					"Error while writing screenshot to file");
		}
	}
	
	
	/*
	 * 
	 */
	public void reportDoneEvent(String stepName,String description) {
		//this.updateTestLog(stepName, description, Status.DONE);
		this.updateTestLog(stepName, description, Status.DONE);
	}	
	
	/**
	 * Add a Passed step to the test report
	 * @param stepName
	 * @param description
	 */
	public void reportPassEvent(String stepName,String description){
		this.updateTestLog(stepName, description, Status.DONE);
	}
	
	/**
	 * Add a failed report to the test report
	 * @param stepName
	 * @param description
	 */
	public void reportFailEvent(String stepName,String description){
		this.updateTestLog(stepName, description, Status.FAIL);
		String BVT=System.getenv("BVT");
	//	String BVT = "Yes";	
		if (BVT==null)
		{	
			System.out.println("inside bvt null");
			Assert.fail("Error in Step: "+ stepName +"\n Descripton: " + description); //I have to change it back since the test were not failing even when there was a failure
			//Assert.assertFalse("Error in Step: "+ stepName +"\n Descripton: ", false);//changed by ragini
		}
		
		else
		{
			
			if(BVT.equalsIgnoreCase("YES"))
			{
				
				if(stepName=="ApplicationLaunch")
				{
					Assert.fail("Error in Step: "+stepName +"\n Descripton: " + description);
				}
				if(stepName=="AccountLoad")
				{
					System.out.println("inside accountload");
					Assert.fail("Error in Step: "+stepName +"\n Descripton: " + description);
				}
			
			}
		}
		

		}
	
	public void reportFailEvent(String stepName,String description, boolean blnExit){
		this.updateTestLog(stepName, description, Status.FAIL);
		if(blnExit){
			
		}
		
		
		
	}	
}