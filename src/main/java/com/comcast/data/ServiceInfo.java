package com.comcast.data;


import com.comcast.utils.DataTable;

public class ServiceInfo  {

	public String equipmentFee;
	public String serviceName;
	public String testSetName;
	public String UNI1;
	public String UNI2;
	public String EVC;
	

	/*public ServiceInfo(String equipmentFee, String serviceName ){
		this.equipmentFee = equipmentFee;
		this.serviceName = serviceName;
		
	}*/
	
	public ServiceInfo(String equipmentFee, String serviceName ,
			String testSetName,String UNI1,String UNI2,String EVC){
		this.equipmentFee = equipmentFee;
		this.serviceName = serviceName;
		this.testSetName = testSetName;
		this.UNI1 = UNI1;
		this.UNI2 = UNI2;
		this.EVC = EVC;
		
	
	}
	
	public static ServiceInfo loadFromDatatable(DataTable dataTable){
		System.out.println("serviceName "+dataTable.getValue("serviceName"));
		return new ServiceInfo(dataTable.getValue("equipmentFee"),
				   dataTable.getValue("serviceName"), dataTable.getValue("testSetName"), 
				   dataTable.getValue("UNI1"), dataTable.getValue("UNI2"), dataTable.getValue("EVC"));
		
	}
	
}
