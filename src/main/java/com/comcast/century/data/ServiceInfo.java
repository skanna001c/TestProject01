package com.comcast.century.data;

import com.comcast.utils.DataTable;

public class ServiceInfo {

	public String equipmentFee;
	public String serviceName;
	
	
	
	public ServiceInfo(String equipmentFee, String serviceName ){
		this.equipmentFee = equipmentFee;
		this.serviceName = serviceName;
		
	}
	
	public static ServiceInfo loadFromDatatable(DataTable dataTable){
		return new ServiceInfo(dataTable.getValue("equipmentFee"),
				   dataTable.getValue("serviceName"));
	}
	
}
