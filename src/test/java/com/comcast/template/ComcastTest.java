package com.comcast.template;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import com.comcast.utils.ComcastTestMain;

public class ComcastTest extends ComcastTestMain {
	
	public XSSFWorkbook datawb;
	public String uname, pwd;
	public static WebDriver driver;
	
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
