package com.comcast.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Hashtable;

import java.util.Properties;

/*className: DataDump
Created by: Harsh Bolakani
Description: Helper class that keeps track of runtime data and test status for packaged execution
*/
public class DataDump implements IDataDump {
	
	private Hashtable<String, String> dumpTable;
	private Properties properties;
	private String testName;
	
	
	public DataDump(String testName){
		
		properties = new Properties();
		dumpTable = new Hashtable<String,String>();
		this.testName = testName;
		
	}
	
	/* (non-Javadoc)
	 * @see com.comcast.utils.IDataDump#setValue(java.lang.String, java.lang.String)
	 */
	@Override
	public void setValue(String key, String val){
		dumpTable.put(key, val);
	}
	
	/* (non-Javadoc)
	 * @see com.comcast.utils.IDataDump#getValue(java.lang.String)
	 */
	@Override
	public String getValue(String key){
		if(dumpTable.containsKey(key)){
			return dumpTable.get(key);
		}else
			return "";
	}
	
	
	@Override
	public boolean deleteValue(String key){
		if(dumpTable.containsKey(key)){
			 dumpTable.remove(key);
			 return true;
		}else
			return false;
	}
	/* (non-Javadoc)
	 * @see com.comcast.utils.IDataDump#dumpData(java.util.Hashtable)
	 */
	@Override
	public void dumpData() throws FileNotFoundException, IOException{
		//properties.putAll(dataTable);
		/*for(String key: dataTable.keySet()){
			properties.put(key,dataTable.get(key));
			
		}*/		
		for(String key: dumpTable.keySet()){
			properties.put(key,dumpTable.get(key));
			
		}
		File file = new File("C:\\dump\\dump_"+ testName + ".properties");
		if(!file.exists()){
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		
		properties.store(new FileOutputStream("C:\\dump\\dump_"+ testName + ".properties"), "Dumping data to properties file");
	}
	
	/* (non-Javadoc)
	 * @see com.comcast.utils.IDataDump#loadData()
	 */
	@Override
	public Hashtable<String,String> loadData(){
		
		try {
			properties.load(new FileInputStream("C:\\dump\\dump_"+ testName + ".properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(String key: properties.stringPropertyNames()){
			dumpTable.put(key, properties.get(key).toString());			
		}
		//dumpMap = new HashMap<Object,Object>(properties);
		return dumpTable;
	}
	
	
	

}
