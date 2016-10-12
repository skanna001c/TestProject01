package com.comcast.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.comcast.utils.ComcastTestMain;
import com.comcast.utils.TestUtils;

/**
 * Base class for all Comcast Test
 *
 */
/**
 * @author hbolak01c
 *
 */
public class ComcastTest extends ComcastTestMain {

	Application application;
	public XSSFWorkbook datawb;
	public String uname, pwd;
	protected String testcaseName;
	public static Properties obj_repo_prop;
	static Logger log = Logger.getLogger(ComcastTest.class);

	@BeforeClass
	public void beforeTest() {
		testcaseName = frameworkContext.getTestCaseName();
	}

	@BeforeTest
	public void beforeTestMainComcast(ITestContext context) {
		log.debug("@BeforeTest beforeTestMainComcast()");
		application = new Application(frameworkContext);
		obj_repo_prop = loadObjRepo();
	}

	// updated the logic to check if datatable is null, then instantiate it
	@BeforeMethod
	public synchronized void setupDataApplication(Method testName) {
		log.debug("In ComcastTest method :" + testName.getName());
		application.beforeMethodGetUserndURL(testName);
	}

	@AfterMethod
	public synchronized void tearDownApplication(ITestResult result) {
		log.debug("@AfterMethod tearDownApplication()");

		String methodStatus;

		if (result.getStatus() == 1) {
			methodStatus = "PASS";
		} else {
			methodStatus = "FAIL";
		}
	}

	@AfterTest(alwaysRun = true)
	public synchronized void afterTestMainApplication() {
		log.debug("@AfterTest afterTestMainApplication()");

		if (settings.getUpdateALM().equalsIgnoreCase("true"))
			almRestUpdateStatus();
		browser.quit();
	}

	public static Properties loadObjRepo() {
		Properties prop = new Properties();
		String path = TestUtils.getRelativePath() + "//src//test//resources" + File.separator + "obj_repo.properties"; // TODO remove hardcoded value
		log.debug("Loading obj_repo from: " + path);
		try {
			prop.load(new FileInputStream(path));
		} catch (Exception e) {
			log.error("Unable to load obj_repo from:" + path + "---" + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException ("Unable to load obj_repo from:" + path + "---" + e.getMessage());
		} 
		return prop;
	}

	// To be removed
	
	/*public String returnNameOfTestUserProfile(String username) throws IOException {
		ArrayList<String> userNameList = new ArrayList<String>();
		userNameList = selectuserandlogin(username);
		String name = userNameList.get(2);
		return name;
	}

	public ArrayList<String> selectuserandlogin(String profile) throws IOException

	{
		ArrayList<String> userdetails = new ArrayList<String>();
		String Profileused = profile;
		FileInputStream fis = new FileInputStream("data.xlsx"); // TODO change
																// this location
		XSSFWorkbook datawb = new XSSFWorkbook(fis);

		XSSFSheet datasheet = datawb.getSheet("user");
		int rows_total = datasheet.getLastRowNum();
		System.out.println(rows_total);
		for (int i = 1; i <= rows_total; i++) {
			XSSFRow datarow = datasheet.getRow(i);
			XSSFCell datacell1 = datarow.getCell(0);

			XSSFCell datacell2 = datarow.getCell(1);

			XSSFCell datacell3 = datarow.getCell(2);
			XSSFCell datacell4 = datarow.getCell(3);
			System.out.println("profile" + datacell1.getStringCellValue());

			if (Profileused.equals(datacell1.getStringCellValue())) {
				uname = datacell2.getStringCellValue();
				pwd = datacell3.getStringCellValue();
				String name = datacell4.getStringCellValue();
				System.out.println("user name is" + uname);

				userdetails.add(uname);
				userdetails.add(pwd);
				userdetails.add(name);
				break;
			}
		}
		return userdetails;

	}*/

}
