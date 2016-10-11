package com.comcast.template;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Workcommonpages {

	Properties pro;
	
	By chatter;
	By groups;
	By sdmautomationgrp;
	By more;
	By teamhuddle;
	By salesprocess;
	By Huddleforumname;
	By Subject;
	By userhud;
	By duedate;	
	
	Workcommonpages(WebDriver driver, Properties pro) throws IOException
	  {
	        this.driver = driver;
	    	this.pro = pro;
	    	chatter=By.linkText(pro.getProperty("work.chatter"));
	    	groups=By.xpath(pro.getProperty("work.groups"));
	    	sdmautomationgrp=By.xpath(pro.getProperty("work.automationgroups"));
	    	more=By.xpath(pro.getProperty("work.more"));
	    	teamhuddle=By.xpath(pro.getProperty("work.teamhuddle"));
	    	salesprocess=By.xpath(pro.getProperty("work.salesprocess"));
	    	Huddleforumname=By.xpath(pro.getProperty("work.huddleforumname"));
	    	Subject=By.xpath(pro.getProperty("work.huddlesubject"));
	    	userhud=By.xpath(pro.getProperty("work.username"));
	    	duedate=By.xpath(pro.getProperty("work.huddleduedate"));
      }
	
		WebDriver driver; 
		
	  public void clickchatter()
	  {
		  driver.findElement(chatter).click();
	  }
	  
	  
	  public void clickgroups()
	  {
		  driver.findElement(groups).click();
	  }
	  
	  public void sdmcselectgrp()
	  {
		  driver.findElement(sdmautomationgrp).click();
	  }
	
	  
	  public void clickmore()
	  {
		  driver.findElement(more).click();
	  }
	  
	  
	  public void selectsalesprocess()
	  {
		  Select salespro= new Select(driver.findElement(Huddleforumname));
		  salespro.selectByIndex(1);
	  }
	  
	  
	  public void selectteamhuddleforum()
	  {
		  Select huddleforum = new Select(driver.findElement(Huddleforumname));
		  huddleforum.selectByIndex(1);
		  
	  }
	  
	  public void logout()
	  {
		  WebElement logoutarrow=driver.findElement(By.xpath(pro.getProperty("logout.arrow")));
		  logoutarrow.click();
		  
		  WebElement logout=driver.findElement(By.xpath(pro.getProperty("logout.button")));
		  logout.click();
		  
		  driver.close();
          
	  }
	 
}
