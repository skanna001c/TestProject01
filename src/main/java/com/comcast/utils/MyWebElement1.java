package com.comcast.utils;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

/**
 * Base class for all Comcast Test
 *
 */
public   class MyWebElement1  implements WebElement {
	private WebElement element;
	protected  TestSettings settings;
	
	public MyWebElement1()
	{
		settings=new TestSettings();
	}
	@Override
	public void click() {
		// TODO Auto-generated method stub
		if(settings.getBrowser().equalsIgnoreCase("iexplore"))
		{
			element.sendKeys(Keys.ENTER);
		}
		else
		{
			element.click();
		}
		
	}
	@Override
	public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void submit() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void sendKeys(CharSequence... keysToSend) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getTagName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getAttribute(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<WebElement> findElements(By by) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public WebElement findElement(By by) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Point getLocation() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Dimension getSize() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getCssValue(String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	 
}
