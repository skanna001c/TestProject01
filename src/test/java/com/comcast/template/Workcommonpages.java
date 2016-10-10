package com.comcast.template;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Workcommonpages extends Prioritycases{

		WebDriver driver; 
	
	By chatter=By.linkText(super.pro.getProperty("work.chatter"));
	By groups=By.xpath(super.pro.getProperty("work.groups"));
	By sdmautomationgrp=By.xpath(super.pro.getProperty("work.automationgroups"));
	By more=By.xpath(super.pro.getProperty("work.more"));
	By teamhuddle=By.xpath(super.pro.getProperty("work.teamhuddle"));
	By salesprocess=By.xpath(super.pro.getProperty("work.salesprocess"));
	By Huddleforumname=By.xpath(super.pro.getProperty("work.huddleforumname"));
	By Subject=By.xpath(super.pro.getProperty("work.huddlesubject"));
	By userhud=By.xpath(super.pro.getProperty("work.username"));
	By duedate=By.xpath(super.pro.getProperty("work.huddleduedate"));
	
	
	Workcommonpages(WebDriver driver) throws IOException
	  {

	        this.driver = driver;
	    	
        }
	  
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
		  WebElement logoutarrow=driver.findElement(By.xpath(super.pro.getProperty("logout.arrow")));
		  logoutarrow.click();
		  
		  WebElement logout=driver.findElement(By.xpath(super.pro.getProperty("logout.button")));
		  logout.click();
		  
		  driver.close();
          
	  }
	 
}
