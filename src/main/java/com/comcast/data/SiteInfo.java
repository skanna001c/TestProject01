package com.comcast.data;

import com.comcast.utils.DataTable;

public class SiteInfo {
	public String siteName;
	public String siteAddress1;
	public String siteAddress2;
	public String zipCode;
	public String headendName;
	public String headendCili;
	public String city;
	public String noOfSites;
	public String TestSetName;


	public SiteInfo(String siteName, String siteAddress1,String siteAddress2,  String zipCode, String headendName,String headendCili,String city,String noOfSites,String TestSetName) {
		this.siteName = siteName;
		this.siteAddress1 = siteAddress1;
		this.siteAddress2 = siteAddress2;
		this.zipCode = zipCode;
		this.headendName = headendName;
		this.headendCili = headendCili;
		this.city = city;
		this.noOfSites = noOfSites;
		this.TestSetName = TestSetName;
		
	
}
	
	public static SiteInfo loadFromDatatable(DataTable dataTable){
		return new SiteInfo(dataTable.getValue("siteName"),
                dataTable.getValue("siteAddress1"),
                dataTable.getValue("siteAddress2"),
                dataTable.getValue("zipCode"),
                dataTable.getValue("headendName"),
                dataTable.getValue("headendCili"),
                dataTable.getValue("city"),
                dataTable.getValue("noOfSites"),
                dataTable.getValue("TestSetName"));
      }
}
