package com.comcast.century.data;

import com.comcast.utils.DataTable;

public class ProcessInfo {
	public String terms;
	public String UNITransportType1;
	public String UNITransportType2;
	public String UNITransportType3;
	public String surCILI1;
	public String surCILI2;
	public String surCILI3;
	public String surCILIPRI;
	public String basicCosBandwidth;
    public String ipAddressAllocation;
    public String evcAreaType;
    public String customerVLANInfo;
	  
	

	public ProcessInfo(String terms, String UNITransportType1,String UNITransportType2, String UNITransportType3, String surCILI1,String surCILI2,String surCILI3,String surCILIPRI, String basicCosBandwidth, String ipAddressAllocation,String evcAreaType, String customerVLANInfo  ) {
		this.terms = terms;
		this.UNITransportType1 = UNITransportType1;
		this.UNITransportType2 = UNITransportType2;
		this.UNITransportType3 = UNITransportType3;
		this.surCILI1 = surCILI1;
		this.surCILI2 = surCILI2;
		this.surCILI3 = surCILI3;
		this.surCILIPRI = surCILIPRI;
		this.basicCosBandwidth = basicCosBandwidth;
		this.ipAddressAllocation = ipAddressAllocation;
		this.evcAreaType = evcAreaType;
		this.customerVLANInfo = customerVLANInfo;
	
}
	
	public static ProcessInfo loadFromDatatable(DataTable dataTable){
		return new ProcessInfo(dataTable.getValue("terms"),
				dataTable.getValue("UNITransportType1"),
				dataTable.getValue("UNITransportType2"),
				dataTable.getValue("UNITransportType3"),
                dataTable.getValue("surCILI1"),
                dataTable.getValue("surCILI2"),
                dataTable.getValue("surCILI3"),
                dataTable.getValue("surCILIPRI"),
                dataTable.getValue("basicCosBandwidth"),
                dataTable.getValue("ipAddressAllocation"),
                dataTable.getValue("evcAreaType"),
                dataTable.getValue("customerVLANInfo"));
      }
}
