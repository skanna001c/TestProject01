package com.comcast.century.openordertechsup;

import org.testng.annotations.Test;

import com.comcast.century.techsup.Tech_Sup_ME_EDI_Tech_Sup_Add_BGP;

public class TechSup_Add_BGP_ME_EDI_CAE_In_progress extends Tech_Sup_ME_EDI_Tech_Sup_Add_BGP {
           
	@Test(priority = 30000)
	public void addBGP() throws InterruptedException {
		addBGPToEDI();
	}
	
}
