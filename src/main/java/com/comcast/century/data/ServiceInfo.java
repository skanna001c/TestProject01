package com.comcast.century.data;

import com.comcast.utils.DataTable;

public class ServiceInfo {

	public String equipmentFee;
	
	
	public ServiceInfo(String equipmentFee ){
		this.equipmentFee = equipmentFee;
	}
	
	public static ServiceInfo loadFromDatatable(DataTable dataTable){
		return new ServiceInfo(dataTable.getValue("equipmentFee"));
	}
	
}
