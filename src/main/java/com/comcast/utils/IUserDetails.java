package com.comcast.utils;

import java.util.Hashtable;

public interface IUserDetails {

	
	String getValue(String key);

	Hashtable loadData();

}