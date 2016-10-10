package com.comcast.template;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class commonfunctions {
	public XSSFWorkbook datawb;
	public String uname, pwd;
	public static WebDriver driver;

	// function to fetch user name and pwd and select the particular driver

	public ArrayList<String> selectuserandlogin(String profile) throws IOException

	{
		ArrayList<String> userdetails = new ArrayList<String>();
		String Profileused = profile;
		FileInputStream fis = new FileInputStream("C:\\Users\\ohegde001c\\Desktop\\data.xlsx"); //TODO change this location
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

	public WebDriver setdriver(String Profileuse) throws IOException {

		String prof = Profileuse;
		System.out.println(prof);
		WebDriver driverr = openchrome();
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

		return driverr;
	}

	public static WebDriver openchrome() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("chrome.switches", "--disable-extensions");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ohegde001c\\Desktop\\chromedriver.exe"); //TODO change this location
		return driver = new ChromeDriver(options);

	}

}
