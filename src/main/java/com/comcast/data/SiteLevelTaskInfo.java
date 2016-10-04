package com.comcast.data;

import com.comcast.utils.DataTable;

public class SiteLevelTaskInfo {
	public String agreementName;
	public String localBillerHouseAccountNumber;
	public String localBillerName;
	public String wdmType;
	public String waveLength;
  
	  
	

	public SiteLevelTaskInfo(String agreementName, String localBillerHouseAccountNumber,String localBillerName, String wdmType, String waveLength) {
		this.agreementName = agreementName;
		this.localBillerHouseAccountNumber = localBillerHouseAccountNumber;
		this.localBillerName = localBillerName;
		this.wdmType = wdmType;
		this.waveLength = waveLength;
	
}
	
	public static SiteLevelTaskInfo loadFromDatatable(DataTable dataTable){
		return new SiteLevelTaskInfo(dataTable.getValue("agreementName"),
                dataTable.getValue("localBillerHouseAccountNumber"),
                dataTable.getValue("localBillerName"),
                dataTable.getValue("wdmType"),
                dataTable.getValue("waveLength"));
                
      }
}
