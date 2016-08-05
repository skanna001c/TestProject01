/**
 *
 */
package com.comcast.neto.alm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.comcast.utils.TestSettings;

/**
 *
 * These constants are used throughout the code to set the server to work with.
 * To execute this code, change these settings to fit
 * those of your server.
 */
public class Constants {
 private Constants() {}

 public static final boolean VERSIONED = true;
 public static String AlmConfigpath="C:/ALMConfig/ConfigFiles/AlmConfig";
 protected static TestSettings settings=new TestSettings();
 static String File="";
 
// public static final String HOST = "almapp.comcast.net";
/* public static final String HOST = nodeFromKey(readfromConfigFile(), "HOST =", "PORT").trim();
 public static final String PORT = nodeFromKey(readfromConfigFile(), "PORT =", "USERNAME").trim();
 public static final String USERNAME = nodeFromKey(readfromConfigFile(), "USERNAME=", "PASSWORD").trim();
 public static final String PASSWORD = nodeFromKey(readfromConfigFile(), "PASSWORD=", "DOMAIN").trim();
 //public static final String USERNAME = "AutoRes_Update";
 //public static final String PASSWORD = "";
 public static final String DOMAIN = nodeFromKey(readfromConfigFile(), "DOMAIN=", "PROJECT=").trim();
 public static final String PROJECT = nodeFromKey(readfromConfigFile(), "PROJECT=", "VERSIONED=").trim();
 */
 
 public static final String HOST = settings.getALMHost().trim();
 public static final String PORT = settings.getPORT().trim();
 public static final String USERNAME = settings.getALM_Username().trim();
 public static final String PASSWORD = settings.getALM_Password().trim();
 //public static final String USERNAME = "AutoRes_Update";
 //public static final String PASSWORD = "";
 public static final String DOMAIN = settings.getDOMAIN().trim();
 public static final String PROJECT = settings.getPROJECT().trim();

 
 
 public final static String readfromConfigFile()
 {
	 
	 
	 FileReader in = null;
	try 
	{
		in = new FileReader(AlmConfigpath+".txt");
	} 
	catch (FileNotFoundException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     BufferedReader br = new BufferedReader(in);
     String data;
               
     //System.out.println();
     try 
     {
		while ((data = br.readLine()) != null)   
		 {
		                                                                                 
		  File=File+data;

		 }
	}
    catch (IOException e) 
    {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    try
     {
		br.close();
	} 
    catch (IOException e) 
    {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    // System.out.println("The File is"+File);
    return File;
    
 }
 
 public static String nodeFromKey(String xmlStr,String str1,String str2) 
 {
 	int startPosition=0;
 	int endPosition=0;
 	startPosition = xmlStr.indexOf(str1) + str1.length();
 	if(startPosition==-1)
 	{
 		System.out.printf("No Value found for ::%s\n",str1);
         return(null);
      
 	}
 	
 	else if(xmlStr.indexOf(str1)==-1)
 	{
 		System.out.printf("No Value found for ::%s\n",str1);
         return(null);
      
 	}
 	
 	endPosition = xmlStr.indexOf(str2, startPosition); 
     if(endPosition==-1)
 	{
 		System.out.printf("No Value found for ::%s\n",str2);
         return(null);
      
 	}
 	//System.out.printf("endPosition value is :: %d\n",endPosition);
 	String resultval = xmlStr.substring(startPosition, endPosition); 
     //System.out.printf("Result value is :: %s\n",resultval);
     return (resultval);
 }

}