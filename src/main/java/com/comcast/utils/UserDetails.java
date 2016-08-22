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
public class UserDetails implements IUserDetails {
	
	private Properties properties;
	private String testName;
	protected TestSettings settings;
	
	public UserDetails(){
		
		properties = new Properties();		
	//	this.testName = testName;
		settings=new TestSettings();
	}
	
	/* (non-Javadoc)
	 * @see com.comcast.utils.IUserDetails#setValue(java.lang.String, java.lang.String)
	 */
	
	/* (non-Javadoc)
	 * @see com.comcast.utils.IUserDetails#getValue(java.lang.String)
	 */
	@Override
	public String getPassword(String key){
		if(properties.containsKey(key)){
			return properties.getProperty(key);
		}else
			return "";
	}
	
	/* (non-Javadoc)
	 * @see com.comcast.utils.IUserDetails#dumpData(java.util.Hashtable)
	 */
	
	/* (non-Javadoc)
	 * @see com.comcast.utils.IUserDetails#loadData()
	 */
	@Override
	public Properties loadData(){
		
		try {
		//	System.out.println("UserDetails_"+settings.getEnvironmentToTest()+".properties");
			String relativePath=TestUtils.getRelativePath();
			properties.load(new FileInputStream(relativePath + "//src//test//resources"+ File.separator +"UserDetails_"+settings.getEnvironmentToTest()+".properties"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//dumpMap = new HashMap<Object,Object>(properties);
		return properties;
	}

}
