package com.comcast.century.data;

import com.comcast.utils.DataTable;

public class ContactInfo {
	public String contactType1;
	public String contactType2;
	public String contactType3;
	public String firstName;
	public String lastName;
	public String nameSuffix;
	public String emailId;
	public String primaryPhoneNum;
	  
	

	public ContactInfo(String contactType1, String firstName, String lastName,String nameSuffix, String emailId,String primaryPhoneNum, String contactType2,String contactType3  ) {
		this.contactType1 = contactType1;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nameSuffix = nameSuffix;
		this.emailId = emailId;
		this.primaryPhoneNum = primaryPhoneNum;
		this.contactType2 = contactType2;
		this.contactType3 = contactType3;
	
}
	
	public static ContactInfo loadFromDatatable(DataTable dataTable){
		return new ContactInfo(dataTable.getValue("contactType1"),
                dataTable.getValue("firstName"),
                dataTable.getValue("lastName"),
                dataTable.getValue("nameSuffix"),
                dataTable.getValue("emailId"),
                dataTable.getValue("primaryPhoneNum"),
				dataTable.getValue("contactType2"),
				dataTable.getValue("contactType3"));
	
	
      }
}
