package com.comcast.template;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.utils.ComcastTestMain;

public class Prioritycases extends ComcastTestMain{
	public WebDriver drivermain;
	public static Properties pro;
	public static Workcommonpages workobject;
	public commonfunctions common;

	static Logger log = Logger.getLogger(Prioritycases.class.getName());

	@BeforeTest
	public void test() throws Exception

	{
		pro = setpropertyfile();
	}

	@Test(dataProvider = "observation")
	public void tc002_observation(String Manager, String reportee) throws InterruptedException, IOException {

		commonfunctions tc002 = new commonfunctions();
		drivermain = tc002.setdriver(Manager);
		workobject = new Workcommonpages(drivermain);

		drivermain.manage().window().maximize();

		WebElement Observation = drivermain.findElement(By.linkText(pro.getProperty("work.observartion.xpath")));
		Observation.click();

		Thread.sleep(2000);

		Select xsp = new Select(drivermain.findElement(By.xpath(pro.getProperty("work.obseravtion.selectxsp"))));
		if (Manager == "SDMwork" && reportee == "XSPwork") {
			xsp.selectByIndex(1);
		} else if (Manager == "ASMwork" && reportee == "XSPwork1") {
			xsp.selectByIndex(7);
		}

		Thread.sleep(6000);
		WebElement startobservation = drivermain.findElement(By.xpath(pro.getProperty("work.observation.startnew")));
		startobservation.click();

		Thread.sleep(4000);

		Select areaoffocus = new Select(
				drivermain.findElement(By.xpath(pro.getProperty("work.observation.areaoffocus"))));
		areaoffocus.selectByIndex(1);

		WebElement overallnotes = drivermain.findElement(By.xpath(pro.getProperty("work.observation.overallnotes")));
		overallnotes.sendKeys("test automation");

		Select knocksobserved = new Select(
				drivermain.findElement(By.xpath(pro.getProperty("work.obseravtion.knocksobserved"))));
		knocksobserved.selectByIndex(1);

		Select contact = new Select(drivermain.findElement(By.xpath(pro.getProperty("work.obserravtion.contacts"))));
		contact.selectByIndex(2);

		Thread.sleep(4000);
		Select start = new Select(drivermain.findElement(By.xpath(pro.getProperty("work.observation.starts"))));
		start.selectByIndex(2);

		WebElement acheivedexpectations = drivermain
				.findElement(By.xpath(pro.getProperty("work.observation.acheivedexpectations")));
		acheivedexpectations.click();

		Thread.sleep(4000);

		WebElement engage = drivermain.findElement(By.xpath(pro.getProperty("work.observation.engage")));
		engage.click();

		acheivedexpectations.click();

		Thread.sleep(4000);

		WebElement submit = drivermain.findElement(By.xpath(pro.getProperty("work.observation.submit")));
		submit.click();

	}

	@DataProvider(name = "coachrelationship_tc004")

	public Object[][] getcoachrelationship() {

		return new Object[][] {

				{ "SDMwork", "XSPwork" }, { "ASMwork", "SDMwork" }, { "ASMwork", "XSPwork1" },

		};
	}

	@DataProvider(name = "coach")

	public Object[][] getcoach() {

		return new Object[][] {

				{ "XSPwork", "SDMwork" }, { "SDMwork", "ASMwork" }, { "XSPwork1", "ASMwork" },

		};
	}

	@DataProvider(name = "huddleusers")

	public Object[][] getDataFromDataprovider() {

		return new Object[][] {

				{ "SDMwork" },

				{ "ASMwork" },

		};

	}

	@DataProvider(name = "observation")

	public Object[][] getDataforobservation() {

		return new Object[][] {

				{ "SDMwork", "XSPwork" },

				{ "ASMwork", "XSPwork1" },

		};

	}

	@AfterMethod
	public void getresult(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			log.info("test poc passed" + result.getName());
		} else {
			log.info("test poc failed" + result.getName());
		}
	}
	
	public static Properties setpropertyfile() throws IOException {
		Properties proo = new Properties();
		proo.load(new FileInputStream("or.properties"));
		System.out.println("property class loaded");
		return proo;

	}

}
