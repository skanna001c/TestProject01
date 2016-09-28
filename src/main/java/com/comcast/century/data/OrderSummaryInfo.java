package com.comcast.century.data;

import com.comcast.utils.DataTable;

public class OrderSummaryInfo {
	public String taxJurisdiction;
	public String salesChannel;
	public String soldRegion;
	public String valueMRC;
	public String valueNRC;
	public String valueEqFeeMRC;
	public String opportunityId;
	public String NonPayDisconnect;
	public String supplementType;
	
	
	public OrderSummaryInfo(String taxJurisdiction, String salesChannel, String soldRegion,String valueMRC,
			String valueNRC,String valueEqFeeMRC, String opportunityId,String NonPayDisconnect, String supplementType ) {
		this.taxJurisdiction = taxJurisdiction;
		this.salesChannel = salesChannel;
		this.soldRegion = soldRegion;
		this.valueMRC = valueMRC;
		this.valueNRC = valueNRC;
		this.valueEqFeeMRC = valueEqFeeMRC;
		this.opportunityId = opportunityId;
		this.NonPayDisconnect= NonPayDisconnect;
		this.supplementType= supplementType;
		
}
	
	public static OrderSummaryInfo loadFromDatatable(DataTable dataTable){
		return new OrderSummaryInfo(dataTable.getValue("taxJurisdiction"),
                dataTable.getValue("salesChannel"),
                dataTable.getValue("soldRegion"),
                dataTable.getValue("valueMRC"),
                dataTable.getValue("valueNRC"),
                dataTable.getValue("valueEqFeeMRC"),
                dataTable.getValue("opportunityId"),
                dataTable.getValue("NonPayDisconnect"),
				dataTable.getValue("supplementType"));
                
      }
}
