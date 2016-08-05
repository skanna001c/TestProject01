package com.comcast.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;

public interface IDataDump {

	void setValue(String key, String val);

	String getValue(String key);

	void dumpData(Hashtable<String, String> dataTable) throws FileNotFoundException, IOException;

	Hashtable loadData();

}