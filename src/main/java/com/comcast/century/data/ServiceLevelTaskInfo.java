package com.comcast.century.data;

import com.comcast.utils.DataTable;

public class ServiceLevelTaskInfo {
	public String localBillerCustomerAccountNumber;
	public String shipCPEsiteAddress;
	public String status;
	
  
	  
	

	public ServiceLevelTaskInfo( String localBillerCustomerAccountNumber,String shipCPEsiteAddress,String status) {
		this.localBillerCustomerAccountNumber = localBillerCustomerAccountNumber;
		this.shipCPEsiteAddress = shipCPEsiteAddress;
		this.status = status;
	
}
	
	public static ServiceLevelTaskInfo loadFromDatatable(DataTable dataTable){
		return new ServiceLevelTaskInfo(dataTable.getValue("localBillerCustomerAccountNumber"),
                dataTable.getValue("shipCPEsiteAddress"),
                dataTable.getValue("status"));
                
      }
}
