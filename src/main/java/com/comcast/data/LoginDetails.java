package com.comcast.data;

import com.comcast.utils.DataTable;

/**
 * User Login Details 
 *
 */
public class LoginDetails {
	public static final String userID = null;
	public String userName;
	public String password;
	public String domain;

	public LoginDetails(String userName,String password,String domain){
		this.userName = userName;
		this.password = password;
		this.domain=domain;
		
		
	}
	
	public static LoginDetails loadFromDatatable(DataTable dataTable){
		System.out.println("username : "+dataTable.getValue("userName") +", password : "+dataTable.getValue("password") );
		return new LoginDetails(dataTable.getValue("userName"),
								dataTable.getValue("password"),
								dataTable.getValue("domain"));
								
	}	
}

