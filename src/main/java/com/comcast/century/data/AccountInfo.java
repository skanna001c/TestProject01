package com.comcast.century.data;

import com.comcast.utils.DataTable;

public class AccountInfo {
	public String accountType;
	public String serviceAccName;
	public String billingAccName;
	public String legalEntity;
	public String lineOfBusiness;
	public String verticalMarket;
	public String eRate;
	

	public AccountInfo(String accountType, String serviceAccName, String legalEntity, String lineOfBusiness,String verticalMarket, String billingAccName, String eRate) {
		this.accountType = accountType;
		this.serviceAccName = serviceAccName;
		this.legalEntity = legalEntity;
		this.lineOfBusiness = lineOfBusiness;
		this.verticalMarket = verticalMarket;
		this.billingAccName = billingAccName;
		this.eRate = eRate;
	
}
	
	public static AccountInfo loadFromDatatable(DataTable dataTable){
		return new AccountInfo(dataTable.getValue("accountType"),
                dataTable.getValue("serviceAccName"),
                dataTable.getValue("legalEntity"),
                dataTable.getValue("lineOfBusiness"),
                dataTable.getValue("verticalMarket"),
                dataTable.getValue("billingAccName"),
                dataTable.getValue("eRate"));
	
	
      }
	
}
