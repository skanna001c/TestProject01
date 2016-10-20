package com.comcast.pages;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.comcast.commons.SFRPage;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class WorkPage extends SFRPage {

	public WorkPage(FrameworkContext context) {
		super(context);
	}

	public WorkPage(FrameworkContext context,Properties pro) {
		super(context, pro); 
	}
		
	@Override
	protected boolean isValidPage() {		
		return false;
	}

	@Override
	protected void waitForPageLoad() {
	}
	
	Logger log = Logger.getLogger(WorkPage.class);
	
	public void setCoach(
			String coacher, String personcoached) throws Exception {

		iLclick("Work.coaching");

		iXclick("Work.Coaching.go");

		iXclick("work.newcoaching");

		iXclick("work.savecoaching");

		Thread.sleep(2000);
		WebElement savecoachingerror = getXWE("work.savecoachingwithnoinputerror");
		System.out.println("error text " + savecoachingerror.getText());

		if (savecoachingerror.isDisplayed()) {
			System.out.println("proper error message displayed when mandatory fields are not selected for a coaching relationship");
		} else {
			System.out.println(" error message not displayed when mandatory fields are not selected for a coaching relationship");
		}

		int count = 1;
		if (count == 2) {

			String name = "Randomessssssssssssss";
			enterXText("work.personcoached", name) ;
			iXclick("work.savecoaching");

		} else if (count == 3) {

			String name = "Randomezzzzzzzzzzzzzzzzzzz";
			enterXText("work.personcoached", name) ;
			iXclick("work.savecoaching");
		}

		else {

			String name = "Randomelllllllllllllllllllll";
			enterXText("work.personcoached", name) ;
			iXclick("work.savecoaching");
		}
		Thread.sleep(3000);
		WebElement errormsgonexistingcoach = getXWE("work.erroemessagewhemcoachingexists") ;
		System.out.println(errormsgonexistingcoach.getText());
		if (errormsgonexistingcoach.isEnabled()) {
System.out.println("proper error message is displayed when a coaching relation ship exists btwn"
							+ coacher + "and" + personcoached);
		} else {
System.out.println(" error message is not displayed properly when a coaching relation ship exists btwn"
							+ coacher + "and" + personcoached);
		}

		count++;

		System.out.println(count);

	}
	


}
