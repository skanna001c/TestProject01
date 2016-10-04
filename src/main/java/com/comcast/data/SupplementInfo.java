package com.comcast.data;

import com.comcast.utils.DataTable;

public class SupplementInfo {

	public String supplementType;
	public String supplementOn;

	public SupplementInfo(String supplementType, String supplementOn  ){
		this.supplementType = supplementType;
		this.supplementOn = supplementOn;
	}
	
	public static SupplementInfo  loadFromDatatable(DataTable dataTable){
		return new SupplementInfo(dataTable.getValue("supplementType"),
                dataTable.getValue("supplementOn"));
	}
	
}
