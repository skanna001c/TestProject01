package com.comcast.utils;

import java.util.Hashtable;

public interface IUserDetails {

	
	String getPassword(String key);
	String getUserName(String key);
	boolean containsTestName(String key);

	Hashtable loadData();

}