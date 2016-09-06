package com.comcast.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Security;

public class SoapTest {

	public void soapCAETask(String request) {
		
		  String Endpoint;
	      
	      String Response;
	      String cibaResponse=null;


	Endpoint = "http://omwebsvc-dt-as-vip.sys.comcast.net/cif/services/CramerSoapPort";

	     
	    
	System.out.println("--------------------------------------");
	System.out.println("CIBA Request : "+request);
	    System.setProperty("java.protocol.handler.pkgs",
	           "com.sun.net.ssl.internal.www.protocol");
	Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	try {

	    URL endPoint = new URL(Endpoint);
	    HttpURLConnection httpConn = (HttpURLConnection) endPoint
	                  .openConnection();
	    httpConn.setRequestProperty("Content-Type",
	                  "text/xml; charset=utf-8");
	    httpConn.setRequestProperty("SOAPAction", "http://www.excelacom.com/century/cramer/service/getSiteDesignNotification");
	    httpConn.setRequestMethod("POST");
	    httpConn.setDoOutput(true);
	    httpConn.setDoInput(true);

	    ByteArrayOutputStream bout = new ByteArrayOutputStream();
	    byte[] buffer = new byte[request.length()];
	    buffer = request.getBytes();
	    bout.write(buffer);
	    byte[] b = bout.toByteArray();
	    httpConn.setRequestProperty("Content-Length",
	                  String.valueOf(b.length));

	    // Set the appropriate HTTP parameters.
	    PrintWriter pw = new PrintWriter(httpConn.getOutputStream());
	    pw.write(request);
	    pw.flush();

	    // Ready with sending the request.

	    // Read the response.

	    httpConn.connect();

	    InputStreamReader isr = new InputStreamReader(
	                  httpConn.getInputStream());
	    BufferedReader in = new BufferedReader(isr);

	    // Write the SOAP message response to a String.
	    while ((Response = in.readLine()) != null) {
	            cibaResponse = Response;
	    }
	    //System.out.println("postCIBAResponse :" +cibaResponse);
	    //System.out.println(cibaResponse);
	} catch (Exception e) {
	    e.printStackTrace();
	}

	System.out.println(cibaResponse);

	}



}


