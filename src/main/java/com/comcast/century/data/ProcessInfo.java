package com.comcast.century.data;

import com.comcast.utils.DataTable;

public class ProcessInfo {
	public String terms;
	public String UNITransportType1;
	public String UNITransportType2;
	public String UNITransportType3;
	public String surCILI;
	public String basicCosBandwidth;
    public String ipAddressAllocation;
    public String evcAreaType;
    public String customerVLANInfo;
    public String NonPayDisconnect;
	  
	

	public ProcessInfo(String terms, String UNITransportType1,String UNITransportType2,
			String UNITransportType3, String surCILI,String basicCosBandwidth, 
			String ipAddressAllocation,String evcAreaType, String customerVLANInfo ,String NonPayDisconnect ) {
		this.terms = terms;
		this.UNITransportType1 = UNITransportType1;
		this.UNITransportType2 = UNITransportType2;
		this.UNITransportType3 = UNITransportType3;
		this.surCILI = surCILI;
		this.basicCosBandwidth = basicCosBandwidth;
		this.ipAddressAllocation = ipAddressAllocation;
		this.evcAreaType = evcAreaType;
		this.customerVLANInfo = customerVLANInfo;
		this.NonPayDisconnect= NonPayDisconnect;
	
}
	
	public static ProcessInfo loadFromDatatable(DataTable dataTable){
		return new ProcessInfo(dataTable.getValue("terms"),
				dataTable.getValue("UNITransportType1"),
				dataTable.getValue("UNITransportType2"),
				dataTable.getValue("UNITransportType3"),
                dataTable.getValue("surCILI"),
                dataTable.getValue("basicCosBandwidth"),
                dataTable.getValue("ipAddressAllocation"),
                dataTable.getValue("evcAreaType"),
                dataTable.getValue("customerVLANInfo"),
                dataTable.getValue("NonPayDisconnect"));
      }
}
