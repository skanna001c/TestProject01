package com.comcast.neto.alm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Attachments 
{
	RestConnector con;
	/*public static void main(String[] args) throws Exception 
	{
		
		               	
		//updateRunStatus("62997","163290","Passed");
		convertTobytes();
		                     
	}*/
 
	
	public byte[] convertTobytes(String attachPath)
	{
		  File file = new File(attachPath);

	         byte[] b = new byte[(int) file.length()];
	         try 
	         {
	               FileInputStream fileInputStream = new FileInputStream(file);
	               fileInputStream.read(b);
	             /*  for (int i = 0; i < b.length; i++) 
	               {
	                           System.out.print((char)b[i]);
	               }*/
	           } 
	          catch (FileNotFoundException e) 
	          {
	                      System.out.println("File Not Found.");
	                      e.printStackTrace();
	          }
	          catch (IOException e1) 
	          {
	                    System.out.println("Error Reading The File.");
	                    e1.printStackTrace();
	          }
	         
	         return b;

	}
	
	  public String attachWithOctetStream(String Instanceid, byte[] fileData, String filename)
	            throws Exception {
		  
		    String entityUrl="http://"+Constants.HOST+":"+Constants.PORT+"/qcbin/rest/domains/"+Constants.DOMAIN+"/projects/"+Constants.PROJECT+"/runs/"+Instanceid;
	        Map<String, String> requestHeaders = new HashMap<String, String>();
	        requestHeaders.put("Slug", filename);	        
	        requestHeaders.put("Content-Type", "application/octet-stream");
	        requestHeaders.put("Accept", "application/xml");
	        Response response = con.httpPost(entityUrl + "/attachments"+ filename, fileData, requestHeaders);
	        return response.getResponseHeaders().get("Location").iterator().next();
	    }
	  
	  public String updateAttachmentData(String Instanceid, byte[] bytes, String attachmentFileName)
		        throws Exception {
		  
		    String entityUrl="http://"+Constants.HOST+":"+Constants.PORT+"/qcbin/rest/domains/"+Constants.DOMAIN+"/projects/"+Constants.PROJECT+"/runs/"+Instanceid;
		    Map<String, String> requestHeaders = new HashMap<String, String>();
		    //This line makes the update be on data and not properties such as description.
		    requestHeaders.put("Content-Type", "application/octet-stream");
		    requestHeaders.put("Accept", "application/xml");
		    byte[] ret =
		            con.httpPut(entityUrl + "/attachments/" + attachmentFileName, bytes, requestHeaders).getResponseData();
		    return new String(ret);
		}

}
