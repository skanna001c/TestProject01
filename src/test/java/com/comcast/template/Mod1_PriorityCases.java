package com.comcast.template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.pages.WorkPage;

public class Mod1_PriorityCases extends ComcastTest {
	public WebDriver drivermain;
	public static Properties pro;
	public static Workcommonpages workobject;
	public Old_commonfunctions common;

	static Logger log = Logger.getLogger(Prioritycases.class.getName());

	@BeforeTest
	public void test() throws Exception

	{		
		pro = setpropertyfile();

	}


	@Test(dataProvider = "coachrelationship_tc004")
	public void tc004_vcerifycoachingrelationshipfordirectprofiles(
			String coacher, String personcoached) throws Exception {
		
		drivermain = frameworkContext.getDriver();
		workobject = new Workcommonpages(drivermain, pro);
  		WorkPage work = new WorkPage(this.frameworkContext, drivermain, pro);
         	work.setCoach(coacher, personcoached);

		drivermain.quit();

	}

	
	@DataProvider(name = "coachrelationship_tc004")
	public Object[][] getcoachrelationship() {

		return new Object[][] {
			{ "SDMwork", "XSPwork" }, 
			{ "ASMwork", "SDMwork" },					
			{ "ASMwork", "XSPwork1" },
		};
	}

	@DataProvider(name = "coach")
	public Object[][] getcoach() {

		return new Object[][] {

		{ "XSPwork", "SDMwork" }, { "SDMwork", "ASMwork" },
				{ "XSPwork1", "ASMwork" },

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

	@Test
	public void unitLevel1() {
		System.out.println("testClass: Unit level1 testing");
	}

	@Test
	public void unitLevel2() {
		System.out.println("testClass: Unit level2 testing");
	}

	@BeforeMethod
	public void before2Method() {
		System.out.println("testClass: before method2");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("testClass: before class");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("testClass: after class");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("testClass: after test");
	}
	
	public void login(String Profileuse, WebDriver driver) throws IOException{
		String prof = Profileuse;
		System.out.println(prof);
		ArrayList<String> userdetail = new ArrayList<String>();
		userdetail = selectuserandlogin(prof);
		String uname = userdetail.get(0);
		System.out.println("uname" + uname);
		String pwd = userdetail.get(1);
		driver.get("https://test.salesforce.com");
		WebElement username = driver.findElement(By.id("username"));

		username.sendKeys(uname);

		WebElement passwrd = driver.findElement(By.id("password"));
		passwrd.sendKeys(pwd);

		WebElement submit = driver.findElement(By.xpath("//*[@id='Login']"));
		submit.click();
	}

}	
