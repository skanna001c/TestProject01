package com.comcast.century.change;

import java.awt.AWTException;

import org.testng.annotations.Test;

import com.comcast.century.common.Supplements;

public class CoaxTaskFlow_Change extends Supplements{
	
	@Test(priority = 60000)
	public void Conduct_Site_Survey_Coax_Change() throws InterruptedException {
		Conduct_Site_Survey_Coax();
	}
	
	@Test(priority = 60100)
	public void Obtain_Site_Agreement_Coax_Change() throws InterruptedException {
		Obtain_Site_Agreement_Coax();
	}
	
	@Test(priority = 60200)	
	public void Conduct_Coax_Survey_Change() throws InterruptedException, AWTException {
		Conduct_Coax_Survey();
	}
	
	@Test(priority = 60300)
	public void Build_House_Account_Coax_Change() throws InterruptedException, AWTException {
		Build_House_Account_Coax();

	}
	
	@Test(priority = 60400)	
	public void Obtain_Coax_Permit_Change() throws InterruptedException, AWTException {
		Obtain_Coax_Permit();
	}
	
	@Test(priority = 60500)	
	public void Complete_Site_Build_Coax_Change() throws InterruptedException, AWTException {
		Complete_Site_Build_Coax();
	}
	
	
	@Test(priority = 60600)	
	public void Complete_Coax_Build_Change() throws InterruptedException, AWTException {
		Complete_Coax_Build();
	}

}
