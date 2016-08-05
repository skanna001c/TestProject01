package com.comcast.century.data;

import com.comcast.utils.DataTable;

public class ServiceLevelTaskInfo {
	public String localBillerCustomerAccountNumber;
	public String shipCPEsiteAddress;
	public String UNI1;
	public String UNI2;
	public String UNI3;
	public String siteCili;
	public String projectName;
	public String EVC1;
	public String EVC2;
	public String EVC3;
	public String status;
	
  
	  
	

	public ServiceLevelTaskInfo( String localBillerCustomerAccountNumber,String shipCPEsiteAddress, String UNI1,String UNI2,String UNI3, String siteCili, String projectName,String EVC1,String EVC2,String EVC3, String status) {
		this.localBillerCustomerAccountNumber = localBillerCustomerAccountNumber;
		this.shipCPEsiteAddress = shipCPEsiteAddress;
		this.UNI1 = UNI1;
		this.UNI2 = UNI2;
		this.UNI3 = UNI3;
		this.siteCili = siteCili;
		this.projectName = projectName;
		this.EVC1 = EVC1;
		this.EVC2 = EVC2;
		this.EVC3 = EVC3;
		this.status = status;
	
}
	
	public static ServiceLevelTaskInfo loadFromDatatable(DataTable dataTable){
		return new ServiceLevelTaskInfo(dataTable.getValue("localBillerCustomerAccountNumber"),
                dataTable.getValue("shipCPEsiteAddress"),
                dataTable.getValue("UNI1"),
                dataTable.getValue("UNI2"),
                dataTable.getValue("UNI3"),
                dataTable.getValue("siteCili"),
                dataTable.getValue("projectName"),
                dataTable.getValue("EVC1"),
                dataTable.getValue("EVC2"),
                dataTable.getValue("EVC3"),
                dataTable.getValue("status"));
                
      }
}
