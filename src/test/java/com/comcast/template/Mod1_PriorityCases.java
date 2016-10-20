package com.comcast.template;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.pages.ComcastTest;
import com.comcast.pages.WorkPage;

public class Mod1_PriorityCases extends ComcastTest {

	static Logger log = Logger.getLogger(Mod1_PriorityCases.class.getName());

	@Test(dataProvider = "coachrelationship_tc004")
	public void tc004_verifyCoachingRelationshipForDirectProfiles(String coacher, String personcoached)
			throws Exception {

		WorkPage workPage = new WorkPage(this.frameworkContext, obj_repo_prop);
		workPage.setCoach(coacher, personcoached);
	}

	@DataProvider(name = "coachrelationship_tc004")
	public Object[][] getcoachrelationship() {

		return new Object[][] { { "SDMwork", "XSPwork" }, { "ASMwork", "SDMwork" }, { "ASMwork", "XSPwork1" }, };
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

	@Test
	public void unitLevel1() {
		log.debug("testClass: Unit level1 testing");
	}

	@Test
	public void unitLevel2() {
		log.debug("testClass: Unit level2 testing");
	}

	@BeforeMethod
	public void before2Method() {
		log.debug("testClass: before method2");
	}

	@BeforeClass
	public void beforeClass() {
		log.debug("testClass: before class");
	}

	@AfterClass
	public void afterClass() {
		log.debug("testClass: after class");
	}

	@AfterTest
	public void afterTest() {
		log.debug("testClass: after test");
	}

	// To be removed

	/*
	 * public void login(String Profileuse, WebDriver driver) throws IOException
	 * { String prof = Profileuse; log.debug(prof); ArrayList<String>
	 * userdetail = new ArrayList<String>(); userdetail =
	 * selectuserandlogin(prof); String uname = userdetail.get(0);
	 * log.debug("uname" + uname); String pwd = userdetail.get(1);
	 * driver.get("https://test.salesforce.com"); WebElement username =
	 * driver.findElement(By.id("username"));
	 * 
	 * username.sendKeys(uname);
	 * 
	 * WebElement passwrd = driver.findElement(By.id("password"));
	 * passwrd.sendKeys(pwd);
	 * 
	 * WebElement submit = driver.findElement(By.xpath("//*[@id='Login']"));
	 * submit.click(); }
	 */

}
