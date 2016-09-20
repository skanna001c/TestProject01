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
  
	  
	

	public OrderSummaryInfo(String taxJurisdiction, String salesChannel, String soldRegion,String valueMRC,String valueNRC,String valueEqFeeMRC, String opportunityId ) {
		this.taxJurisdiction = taxJurisdiction;
		this.salesChannel = salesChannel;
		this.soldRegion = soldRegion;
		this.valueMRC = valueMRC;
		this.valueNRC = valueNRC;
		this.valueEqFeeMRC = valueEqFeeMRC;
		this.opportunityId = opportunityId;
	
}
	
	public static OrderSummaryInfo loadFromDatatable(DataTable dataTable){
		return new OrderSummaryInfo(dataTable.getValue("taxJurisdiction"),
                dataTable.getValue("salesChannel"),
                dataTable.getValue("soldRegion"),
                dataTable.getValue("valueMRC"),
                dataTable.getValue("valueNRC"),
                dataTable.getValue("valueEqFeeMRC"),
                dataTable.getValue("opportunityId"));
                
      }
}
