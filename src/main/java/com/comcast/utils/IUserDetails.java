package com.comcast.utils;

import java.util.Hashtable;

public interface IUserDetails {

	
	String getPassword(String key);

	Hashtable loadData();

}