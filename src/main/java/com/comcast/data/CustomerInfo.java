package com.comcast.data;

import com.comcast.utils.DataTable;

public class CustomerInfo {
	public String customerName;
	public String workPhone;
	public String salesForceAccId;
	public String addressLine1;
	public String addressLine2 ;
	public String zipCode;
    public String city;
    public String TestSetName;

	
	
	public CustomerInfo(String customerName, String workPhone, String salesForceAccId, String addressLine1,String addressLine2, String zipCode,String city,String TestSetName) {
		this.customerName = customerName;
		this.workPhone = workPhone;
		this.salesForceAccId = salesForceAccId;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.zipCode = zipCode;	
		this.city = city;
		this.TestSetName = TestSetName;

	}
	
	public static CustomerInfo loadFromDatatable(DataTable dataTable){
		return new CustomerInfo(dataTable.getValue("customerName"),
                dataTable.getValue("workPhone"),
                dataTable.getValue("salesForceAccId"),
                dataTable.getValue("addressLine1"),
                dataTable.getValue("addressLine2"),
                dataTable.getValue("zipCode"),
                dataTable.getValue("city"),
                dataTable.getValue("TestSetName"));
	
	
      }
}
