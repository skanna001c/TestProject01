package com.comcast.pages;

import java.util.Properties;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;

public class SFRPage extends Page {

	WebDriver driver;
	
	Properties pro;	
	
	protected SFRPage(FrameworkContext context) {
		super(context);
		
	}
	
	protected SFRPage(FrameworkContext context, WebDriver driver, Properties pro) {
		super(context);
		this.driver = driver;
		this.pro = pro;		
	}
	
	

	@Override
	protected boolean isValidPage() {
		
		return false;
	}

	@Override
	protected void waitForPageLoad() {
	}
	
	public WebElement getLWE(String locid) {

		WebElement we = null;
		sleep(2000);
		try {
			we = driver.findElement(By.linkText(pro.getProperty(locid)));

		} catch (Exception e) {
			String emsg = "Unable to find [" + locid + "],  locator=["
					+ pro.getProperty(locid) + "] " + e.getMessage();
			System.err.println(emsg);
			throw new RuntimeException(emsg);
		}

		System.out.println("Found [" + locid + "],  we=" + we);
		return (we);
	}	
	
	
	public WebElement getXWE(String locid) {

		WebElement we = null;
		sleep(2000);
		try {
			we = driver.findElement(By.xpath(pro.getProperty(locid)));

		} catch (Exception e) {
			String emsg = "Unable to find [" + locid + "],  locator=["
					+ pro.getProperty(locid) + "] " + e.getMessage();
			System.err.println(emsg);
			throw new RuntimeException(emsg);
		}

		System.out.println("Found [" + locid + "],  we=" + we);
		return (we);
	}
	
	public boolean iLclick(String locid) {
		WebElement we = null;
		try {
			we = getLWE(locid);
		} catch (Exception e) {
			String emsg = e.getMessage();
			String popupMsg = "Unable to find WebElement for [" + locid
					+ "],\n  locator=[" + pro.getProperty(locid) + "] ";
			int ansCont = qPopup(popupMsg, emsg);
			if (ansCont > 0) {
				return (true);

			}
			
			// TODO throw e ;
			throw new RuntimeException(emsg);
		}

		try {
			we.click();
		} catch (Exception e) {
			String emsg = "Unable to click [" + locid + "],  locator=["
					+ pro.getProperty(locid) + "], we=[" + we + ",]"
					+ e.getMessage();
			System.err.println(emsg);
			throw new RuntimeException(emsg);
		}

		System.out.println("Clicked [" + locid + "],  we=" + we);
		return (true);
	}
	

	public boolean iXclick(String locid) {
		WebElement we = null;
		try {
			we = getXWE(locid);
		} catch (Exception e) {
			String emsg = e.getMessage();
			String popupMsg = "Unable to find WebElement for [" + locid
					+ "],\n  locator=[" + pro.getProperty(locid) + "] ";
			int ansCont = qPopup(popupMsg, emsg);
			if (ansCont > 0) {
				return (true);

			}
			
			// TODO throw e ;
			throw new RuntimeException(emsg);
		}

		try {
			we.click();
		} catch (Exception e) {
			String emsg = "Unable to click [" + locid + "],  locator=["
					+ pro.getProperty(locid) + "], we=[" + we + ",]"
					+ e.getMessage();
			System.err.println(emsg);
			throw new RuntimeException(emsg);
		}

		System.out.println("Clicked [" + locid + "],  we=" + we);
		return (true);
	}

	public boolean enterXText(String locid, String stext) {
		WebElement we = null;
		try {
			we = getXWE(locid);
		} catch (Exception e) {
			String emsg = e.getMessage();
			String popupMsg = "Unable to find WebElement for [" + locid
					+ "],\n  locator=[" + pro.getProperty(locid) + "] ";
			int ansCont = qPopup(popupMsg, emsg);
			if (ansCont > 0) {
				return (true);

			}
			// TODO throw e ;
			throw new RuntimeException(emsg);
		}

		try {
			we.sendKeys(stext);
		} catch (Exception e) {
			String emsg = "Unable to send text [" + locid + "],  locator=["
					+ pro.getProperty(locid) + "], we=" + we;
			System.err.println(emsg);
			throw new RuntimeException(emsg);
		}

		System.out.println("[" + locid + "] enter text [" + stext + "] we="
				+ we);
		return (true);
	}

	public boolean selectXByIndex(String locid, int index) {
		WebElement we = getXWE(locid);

		Select dd = new Select(we);
		dd.selectByIndex(index);

		return (true);
	}
	
	public static int qPopup(String msg, String emsg) {
		JDialog.setDefaultLookAndFeelDecorated(true);
		// TODO Improve the GUI to show the exception message also + means to
		// copy the error message ....
		// TODO + beautify

		System.err.println("\n\nqPopup:");
		System.err.println(emsg);
		System.err.println("\n\n" + msg + "\n\n");
		int response = JOptionPane.showConfirmDialog(null, msg
				+ "\n Do you want to continue?\n", "Confirm",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.NO_OPTION) {
			System.out.println("No button clicked");
			return (-1);
		} else if (response == JOptionPane.YES_OPTION) {
			System.out.println("Yes button clicked");
			return (1);
		} else if (response == JOptionPane.CLOSED_OPTION) {
			System.out.println("JOptionPane closed");
			return (0);
		}

		return (-1001);
	}

	@Override
	public void sleep(int tmilisec) {
		try {
			Thread.sleep(tmilisec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
