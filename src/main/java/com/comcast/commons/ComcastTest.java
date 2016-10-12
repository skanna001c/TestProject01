package com.comcast.commons;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.comcast.utils.ComcastTestMain;

/**
 * Base class for all Comcast Test
 *
 */
/**
 * @author hbolak01c
 *
 */
public class ComcastTest extends ComcastTestMain {

	Logger log = Logger.getLogger(ComcastTest.class);
	Application application;
	public XSSFWorkbook datawb;
	public String uname, pwd;
	// added a new beforeTest method - Harsh

	@BeforeTest
	public void beforeTestMainComcast(ITestContext context) {
		application = new Application(frameworkContext);
		log.info("inside before test");

	}

	// updated the logic to check if datatable is null, then instantiate it
	@BeforeMethod
	public synchronized void setupDataApplication(Method testName) {
		log.debug("In ComcastTest method :" + testName.getName());
		
		application.beforeMethodGetUserndURL(testName);

	}

	@AfterMethod
	public synchronized void tearDownApplication(ITestResult result) {

		String methodStatus;

		// update teststatustable
		if (result.getStatus() == 1) {
			methodStatus = "PASS";
		} else {
			methodStatus = "FAIL";
		}

		dataDump.setValue(result.getMethod().getMethodName() + "_status", methodStatus);
		if (methodStatus.equalsIgnoreCase("fail")) {
			// Add code for test clean up

		}
	}

	@AfterTest(alwaysRun = true)
	public synchronized void afterTestMainApplication() {
		if (settings.getUpdateALM().equalsIgnoreCase("true"))
			almRestUpdateStatus();
		browser.quit();
		// Add code for after test executions
	}
	
	
	
	public static Properties setpropertyfile() throws IOException {
		Properties proo = new Properties();
		proo.load(new FileInputStream("or.properties"));
		System.out.println("property class loaded");
		return proo;

	}
	
	public String returnnameoftestuserprofile(String username) throws IOException
	{
		ArrayList<String> nameofuser = new ArrayList<String>();
		nameofuser=selectuserandlogin(username);
		String name = nameofuser.get(2);
		//System.out.println("name of the userprofile is"+name);
		//
		return name;
	}
	
	public ArrayList<String> selectuserandlogin(String profile) throws IOException

	{
		ArrayList<String> userdetails = new ArrayList<String>();
		String Profileused = profile;
		FileInputStream fis = new FileInputStream("data.xlsx"); //TODO change this location
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

	}

}
