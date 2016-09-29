package com.comcast.century.techsup;

import org.testng.annotations.Test;



public class Tech_Supp_Add_BGP_to_EDI_PRI_Service extends Tech_Sup_ME_EDI_Tech_Sup_Add_BGP {

	@Test(priority = 30000)
	public void addBGPToEDI_PRI() throws InterruptedException {
		addBGPToEDI();
	}
}
