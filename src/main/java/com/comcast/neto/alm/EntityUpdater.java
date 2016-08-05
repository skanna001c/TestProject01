package com.comcast.neto.alm;

/**
*
*/
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.comcast.neto.alm.RestConnector;

public class EntityUpdater {
	String domain;
	String project;
	RestConnector con;
	AuthenticateLoginLogout auth;
	
	public EntityUpdater(String almDomain, String almProject){
		this.domain = almDomain.trim();
		this.project = almProject.trim();
		this.con =  RestConnector.getInstance().init(
	                    new HashMap<String, String>(),
	                    Constants.HOST,
	                    Constants.PORT,
	                    this.domain,
	                    this.project);
	//	System.out.println(this.con);
		this.auth = new AuthenticateLoginLogout();
	}
	
	/**The Method is to Login to ALM via REST calls.
	 * Should be invoked to establish the connection/session, before calling any other method in the class to update the data in ALM.
	 * @throws Exception
	 */
	public void loginCall() throws Exception{
		
		boolean isLoggedIn = auth.login(Constants.USERNAME, Constants.PASSWORD);
//		System.out.println("testing");
		if(!isLoggedIn)System.out.println("ALM Error: Unable to Login.");
	}

	/**The Method is to Logout from ALM via REST calls.
	 * @throws Exception
	 */
	public void logoutCall() throws Exception{
	
		auth.logout();
	}
	
	/**The method sends REST calls to update the test case corresponding to the given ID. 
	 * First, it sends a Lock request to lock the test case to avoid parallel update from other channel.
	 * Then the actual updated call is sent with the data (field and value) represented as XML String.
	 * At last, it sends a request to unlock the test case(Delete the lock)
	 * @param testCaseId
	 * @param field - the field/column of the test case in ALM which has to be updated.
	 * @param value - the actual value.
	 * @throws Exception
	 */
	private synchronized void doUpdate(String testCaseId,String field,String value) throws Exception{

	
    String newEntityToUpdateUrl = con.buildEntityUrl("test-instance",testCaseId);
  
    if (Constants.VERSIONED) {
   // if (true) {	
            String checkout = checkout(newEntityToUpdateUrl, "REST Test Checkout", -1);
            //System.out.println("entity checked out: " + checkout.trim());
    }
    else {
            String lock = lock(newEntityToUpdateUrl);
            //System.out.println("entity locked: " + lock.trim());
    }
    
    //Create update string
    String entityUpdateXml = generateUpdateXml(field, value);
    
    //Create entity. (We could have instantiated the entity and used methods to set the new values.)
    Entity e = EntityMarshallingUtils.marshal(Entity.class, entityUpdateXml);

    //Do update operation
    String updateResponseEntityXml = update(newEntityToUpdateUrl,EntityMarshallingUtils.unmarshal(Entity.class, e)).toString();
            
    //checkin
    if (Constants.VERSIONED) {
            boolean checkin = checkin(newEntityToUpdateUrl);
            //System.out.println("entity checkin: " + checkin);
    }
    else {
            boolean unlock = unlock(newEntityToUpdateUrl);
            //System.out.println("entity unlocked: " + unlock);
    }
    
    }
	
	
	
	
	private void CreateTestSet(String testCaseId,String field,String value) throws Exception{

		
	    String newEntityToUpdateUrl = con.buildEntityUrl("test-instance",testCaseId);
	  
	    if (Constants.VERSIONED) {
	            String checkout = checkout(newEntityToUpdateUrl, "REST Test Checkout", -1);
	            //System.out.println("entity checked out: " + checkout.trim());
	    }
	    else {
	            String lock = lock(newEntityToUpdateUrl);
	            //System.out.println("entity locked: " + lock.trim());
	    }
	    
	    //Create update string
	    String entityUpdateXml = generateUpdateXml(field, value);
	    
	    //Create entity. (We could have instantiated the entity and used methods to set the new values.)
	    Entity e = EntityMarshallingUtils.marshal(Entity.class, entityUpdateXml);

	    //Do update operation
	    String updateResponseEntityXml = update(newEntityToUpdateUrl,EntityMarshallingUtils.unmarshal(Entity.class, e)).toString();
	            
	    //checkin
	    if (Constants.VERSIONED) {
	            boolean checkin = checkin(newEntityToUpdateUrl);
	            //System.out.println("entity checkin: " + checkin);
	    }
	    else {
	            boolean unlock = unlock(newEntityToUpdateUrl);
	            //System.out.println("entity unlocked: " + unlock);
	    }
	    
	    }
	    
    
	
	private synchronized void doAttachment(String instanceId,byte[] filedata,String filename) throws Exception{

		//changed
	    String newEntityToUpdateUrl = con.buildEntityUrl("run",instanceId);
	  
	    if (Constants.VERSIONED) {
	            String checkout = checkout(newEntityToUpdateUrl, "REST Test Checkout", -1);
	            //System.out.println("entity checked out: " + checkout.trim());
	    }
	    else {
	            String lock = lock(newEntityToUpdateUrl);
	            //System.out.println("entity locked: " + lock.trim());
	    }
	    
	    //Create update string
	  //  String entityUpdateXml = generateUpdateXml("attachment", filename);
	    
	    //Create entity. (We could have instantiated the entity and used methods to set the new values.)
	   // Entity e = EntityMarshallingUtils.marshal(Entity.class, entityUpdateXml);

	    //Do update operation
	    // String updateResponseEntityXml =updateAttachmentData(instanceId, filedata, filename);
	    Response updateResponseEntityXml =attachWithOctetStream(instanceId, filedata, filename);
	    //System.out.println("updateResponseEntityXml:"+updateResponseEntityXml); 
	    
	   /* Response newMultiPartAttachmentUrl =
                attachWithMultipart(
                		instanceId,
                		filedata,
                        "text/plain",
                        "multipartFileName.txt",
                        "some random description");
*/
	    // System.out.println("newMultiPartAttachmentUrl is ::"+newMultiPartAttachmentUrl); 
	     
	    /* String newOctetStreamAttachmentUrl =
	                attachWithOctetStream(
	                		instanceId,
	                		filedata,
	                        "octetStreamFileName.txt");*/
	    //checkin
	    if (Constants.VERSIONED) {
	            boolean checkin = checkin(newEntityToUpdateUrl);
	            //System.out.println("entity checkin: " + checkin);
	    }
	    else {
	            boolean unlock = unlock(newEntityToUpdateUrl);
	            //System.out.println("entity unlocked: " + unlock);
	    }
	    
	    }
	
private void doDuration(String instanceId,byte[] filedata) throws Exception{

		
	    String newEntityToUpdateUrl = con.buildEntityUrl("runs",instanceId);
	  
	    if (Constants.VERSIONED) {
	            String checkout = checkout(newEntityToUpdateUrl, "REST Test Checkout", -1);
	            //System.out.println("entity checked out: " + checkout.trim());
	    }
	    else {
	            String lock = lock(newEntityToUpdateUrl);
	            //System.out.println("entity locked: " + lock.trim());
	    }
	    
	    //Create update string
	  //  String entityUpdateXml = generateUpdateXml("attachment", filename);
	    
	    //Create entity. (We could have instantiated the entity and used methods to set the new values.)
	   // Entity e = EntityMarshallingUtils.marshal(Entity.class, entityUpdateXml);

	    //Do update operation
	    // String updateResponseEntityXml =updateAttachmentData(instanceId, filedata, filename);
	    Response updateResponseEntityXml =updateDuration(instanceId, filedata);
	    //System.out.println("updateResponseEntityXml:"+updateResponseEntityXml); 
	    
	   /* Response newMultiPartAttachmentUrl =
                attachWithMultipart(
                		instanceId,
                		filedata,
                        "text/plain",
                        "multipartFileName.txt",
                        "some random description");
*/
	    // System.out.println("newMultiPartAttachmentUrl is ::"+newMultiPartAttachmentUrl); 
	     
	    /* String newOctetStreamAttachmentUrl =
	                attachWithOctetStream(
	                		instanceId,
	                		filedata,
	                        "octetStreamFileName.txt");*/
	    //checkin
	    if (Constants.VERSIONED) {
	            boolean checkin = checkin(newEntityToUpdateUrl);
	            //System.out.println("entity checkin: " + checkin);
	    }
	    else {
	            boolean unlock = unlock(newEntityToUpdateUrl);
	            //System.out.println("entity unlocked: " + unlock);
	    }
	    
	    }
   
    /**
    * @param entityUrl
    * of the entity to checkout
    * @param comment
    * to keep on the server side of why you checked this entity out
    * @param version
    * to checkout or -1 if you want the latest
    * @return a string description of the checked out entity
    * @throws Exception
    */
    public String checkout(String entityUrl, String comment, int version) throws Exception {
    String commentXmlBit =
    ((comment != null) && !comment.isEmpty()
    ? "<Comment>" + comment + "</Comment>"
    : "");
    String versionXmlBit = (version >= 0 ? "<Version>" + version + "</Version>" : "");
    String xmlData = commentXmlBit + versionXmlBit;
    String xml =
    xmlData.isEmpty() ? "" : "<CheckOutParameters>" + xmlData + "</CheckOutParameters>";
    Map<String, String> requestHeaders = new HashMap<String, String>();
    requestHeaders.put("Content-Type", "application/xml");
    requestHeaders.put("Accept", "application/xml");
    Response response =
    con.httpPost(entityUrl + "/versions/check-out", xml.getBytes(), requestHeaders);
    return response.toString();
}
	
//public static void main(String[] args) throws Exception {
/**The method sends REST calls to update the test case corresponding to the given ID. 
 * If the update request fails/encounters any exception, the methods attempts to send a REST call to unlock the test case.
 * @param testCaseId
 * @param field - the field/column of the test case in ALM which has to be updated.
 * @param value - the actual value.
 */
public synchronized void updateTestCase(String testCaseId,String field,String value){
 
        try{
        	//doUpdate(con,example,testCaseId,args[1],args[2]);
        	doUpdate(testCaseId.trim(),field.trim(),value.trim());
        }catch(Exception e){
        	System.out.println("Exception in Update - Unlock Enitty.....");
        	String newEntityToUpdateUrl = con.buildEntityUrl("test-instance",testCaseId);
        	//System.out.println("newEntityToUpdateUrl :"+newEntityToUpdateUrl);
			try {
				boolean unlock = unlock(newEntityToUpdateUrl);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
        }
        	   
}


public void CreateTCAttachment(String instanceId,byte[] filedata,String filename){
	 
    try{
    	//doUpdate(con,example,testCaseId,args[1],args[2]);
    	doAttachment(instanceId.trim(),filedata,filename.trim());
    }catch(Exception e){
    	System.out.println("Exception in Update - Unlock Enitty.....");
    	//changed
    	String newEntityToUpdateUrl = con.buildEntityUrl("run",instanceId);
		try {
			boolean unlock = unlock(newEntityToUpdateUrl);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		e.printStackTrace();
    }
    	   
}


public void RunDuration(String instanceId,byte[] filedata){
	 
    try{
    	//doUpdate(con,example,testCaseId,args[1],args[2]);
    	doDuration(instanceId.trim(),filedata);
    }catch(Exception e){
    	//System.out.println("Exception in Update - Unlock Enitty.....");
    	String newEntityToUpdateUrl = con.buildEntityUrl("runs",instanceId);
		try {
			boolean unlock = unlock(newEntityToUpdateUrl);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		e.printStackTrace();
    }
    	   
}

/**
* @param entityUrl
* to checkin
* @return true if operation is successful
* @throws Exception
*/
public boolean checkin(String entityUrl) throws Exception {
        //Execute a post operation on the checkin resource of your entity.
        Response response = con.httpPost(entityUrl + "/versions/check-in", null, null);
        boolean ret = response.getStatusCode() == HttpURLConnection.HTTP_OK;
        return ret;
}

/**
* @param entityUrl
* to lock
* @return the locked entity xml
* @throws Exception
*/
public synchronized String  lock(String entityUrl) throws Exception {
        Map<String, String> requestHeaders = new HashMap<String, String>();
        requestHeaders.put("Accept", "application/xml");
        return con.httpPost(entityUrl + "/lock", null, requestHeaders).toString();
}

        /**
* @param entityUrl
* to unlock
* @return
* @throws Exception
*/
public boolean unlock(String entityUrl) throws Exception {
        return con.httpDelete(entityUrl + "/lock", null).getStatusCode() == HttpURLConnection.HTTP_OK;
}

/**
* @param field
* the field name to update
* @param value
* the new value to use
* @return an XML that can be used to update an entity's single given field to given value
*/
private String generateUpdateXml(String field, String value) {
        return "<Entity Type=\"test-instance\"><Fields>"
                + "<Field Name=\""
                + field
                + "\"><Value>"
                + value
                + "</Value></Field>"
                + "</Fields></Entity>";
}

/**
* @param entityUrl
* to update
* @param updatedEntityXml
* new entity descripion. only lists updated fields. unmentioned fields will not
* change.
* @return xml description of the entity on the serverside, after update.
* @throws Exception
*/
private Response update(String entityUrl, String updatedEntityXml) throws Exception {
        Map<String, String> requestHeaders = new HashMap<String, String>();
        requestHeaders.put("Content-Type", "application/xml");
        requestHeaders.put("Accept", "application/xml");
        Response put = con.httpPut(entityUrl, updatedEntityXml.getBytes(), requestHeaders);
        return put;
}


public String updateAttachmentData(String instanceId, byte[] bytes, String attachmentFileName)
        throws Exception {
	
    String entityUrl="http://"+Constants.HOST+":"+Constants.PORT+"/qcbin/rest/domains/"+Constants.DOMAIN+"/projects/"+Constants.PROJECT+"/runs/"+instanceId;

    Map<String, String> requestHeaders = new HashMap<String, String>();
    //This line makes the update be on data and not properties such as description.
    requestHeaders.put("Content-Type", "application/octet-stream");
    requestHeaders.put("Accept", "application/xml");
    byte[] ret =
            con.httpPut(entityUrl + "/attachments/" + attachmentFileName, bytes, requestHeaders).getResponseData();
    return new String(ret);
}

public Response getInstanceId(String cycleid,String testid) throws Exception {
    Map<String, String> requestHeaders = new HashMap<String, String>();
    requestHeaders.put("Content-Type", "application/xml");
    requestHeaders.put("Accept", "application/xml");  
    Response XML1 = con.httpGet("http://"+Constants.HOST+":"+Constants.PORT+"/qcbin/rest/domains/"+Constants.DOMAIN+"/projects/"+Constants.PROJECT+"/test-instances?query={cycle-id["+cycleid+"];test-id["+testid+"]}&fields=id", null, requestHeaders);
    //System.out.println("XML is ::"+XML1);
    return XML1;
}

public Response getInstanceIdFromRun(String cycleid,String testid) throws Exception {
    Map<String, String> requestHeaders = new HashMap<String, String>();
    requestHeaders.put("Content-Type", "application/xml");
    requestHeaders.put("Accept", "application/xml");  
    Response XML1 = con.httpGet("http://"+Constants.HOST+":"+Constants.PORT+"/qcbin/rest/domains/"+Constants.DOMAIN+"/projects/"+Constants.PROJECT+"/runs?query={cycle-id["+cycleid+"];test-id["+testid+"]}", null, requestHeaders);
    //System.out.println("XML is ::"+XML1);
    return XML1;
}	

public Response attachWithOctetStream(String Instanceid, byte[] fileData, String filename)
        throws Exception {
  
	//changed
    String entityUrl="http://"+Constants.HOST+":"+Constants.PORT+"/qcbin/rest/domains/"+Constants.DOMAIN+"/projects/"+Constants.PROJECT+"/runs/"+Instanceid;
    Map<String, String> requestHeaders = new HashMap<String, String>();
    requestHeaders.put("Slug", filename);
    requestHeaders.put("Content-Type", "application/octet-stream");
    requestHeaders.put("Accept", "application/xml");
    Response response = con.httpPost(entityUrl + "/attachments", fileData, requestHeaders);
    
    //Response res=response.getResponseHeaders().get("Location").iterator().next();
    
    return response;
}

public Response updateDuration(String Instanceid, byte[] fileData)
        throws Exception {
  
    String entityUrl="http://"+Constants.HOST+":"+Constants.PORT+"/qcbin/rest/domains/"+Constants.DOMAIN+"/projects/"+Constants.PROJECT+"/runs/"+Instanceid;
    Map<String, String> requestHeaders = new HashMap<String, String>();
    
    requestHeaders.put("Content-Type", "application/octet-stream");
    requestHeaders.put("Accept", "application/xml");
    Response response = con.httpPost(entityUrl + "/duration", fileData, requestHeaders);
    
    //Response res=response.getResponseHeaders().get("Location").iterator().next();
    
    return response;
}





public Response attachWithOctetStream1(String Instanceid, byte[] fileData, String filename)
        throws Exception {
  
    String entityUrl="http://"+Constants.HOST+":"+Constants.PORT+"/qcbin/rest/domains/"+Constants.DOMAIN+"/projects/"+Constants.PROJECT+"/test-instances/"+Instanceid;
    Map<String, String> requestHeaders = new HashMap<String, String>();
    requestHeaders.put("Content-Type", "application/xml");
    requestHeaders.put("Accept", "application/xml");
    Response response = con.httpPost(entityUrl + "/attachments", fileData, requestHeaders);
    String res=response.getResponseHeaders().get("Location").iterator().next();
    
    return response;
}


public Response attachWithMultipart(
        String InstanceId,
        byte[] fileData,
        String contentType,
        String filename,
        String description) throws Exception {
	
    String entityUrl="http://"+Constants.HOST+":"+Constants.PORT+"/qcbin/rest/domains/"+Constants.DOMAIN+"/projects/"+Constants.PROJECT+"/test-instances/"+InstanceId;

    /*
    headers:
    Content-Type: multipart/form-data; boundary=<boundary>
    //Template for file mime part:
    --<boundary>\r\n
    Content-Disposition: form-data; name="<fieldName>"; filename="<filename>"\r\n
    Content-Type: <mime-type>\r\n
    \r\n
    <file-data>\r\n
    <boundary>--
    //Template for post parameter mime part, such as description and/or filename:
    --<boundary>\r\n
        Content-Disposition: form-data; name="<fieldName>"\r\n
        \r\n
        <value>\r\n
    <boundary>--
    //End of parts:
    --<boundary>--
     We need three parts: filename(template for parameter), description(template for parameter) and file data(template for file).
     */
    //This can be pretty much any string - it's used to signify the different mime parts
    String boundary = "exampleboundary";
    //Template to use when sending field data (assuming non-binary data)
    String fieldTemplate =
            "--%1$s\r\n"
                    + "Content-Disposition: form-data; name=\"%2$s\" \r\n\r\n"
                    + "%3$s"
                    + "\r\n";
    //Template to use when sending file data (binary data still needs to be suffixed)
    String fileDataPrefixTemplate =
            "--%1$s\r\n"
                    + "Content-Disposition: form-data; name=\"%2$s\"; filename=\"%3$s\"\r\n"
                    + "Content-Type: %4$s\r\n\r\n";
    String filenameData = String.format(fieldTemplate, boundary, "filename", filename);
    String descriptionData = String.format(fieldTemplate, boundary, "description", description);
    String fileDataSuffix = "\r\n--" + boundary + "--";
    String fileDataPrefix =
            String.format(fileDataPrefixTemplate, boundary, "file", filename, contentType);
    //The order is extremely important: The filename and description come before file data. The name of the file in the file part and in the filename part value MUST MATCH.
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    bytes.write(filenameData.getBytes());
    bytes.write(descriptionData.getBytes());
    bytes.write(fileDataPrefix.getBytes());
    bytes.write(fileData);
    bytes.write(fileDataSuffix.getBytes());
    bytes.close();
    Map<String, String> requestHeaders = new HashMap<String, String>();
    requestHeaders.put("Content-Type", "multipart/form-data; boundary=" + boundary);
    Response response =
            con.httpPost(entityUrl + "/attachments", bytes.toByteArray(), requestHeaders);
   // return response.getResponseHeaders().get("Location").iterator().next();
    
    return response;
}



public Response getTestID(String testName) throws Exception {
	// TODO Auto-generated method stub
		Map<String, String> requestHeaders = new HashMap<String, String>();
	    requestHeaders.put("Content-Type", "application/xml");
	    requestHeaders.put("Accept", "application/xml");  
	    Response XML1 = con.httpGet("http://"+Constants.HOST+":"+Constants.PORT+"/qcbin/rest/domains/"+Constants.DOMAIN+"/projects/"+Constants.PROJECT+"/tests?query={name["+testName+"]}&fields=id", null, requestHeaders);
	    //System.out.println("XML is ::"+XML1);
	    return XML1;
}

public Response getTestSetName(String testSetID) throws Exception {
	// TODO Auto-generated method stub
		Map<String, String> requestHeaders = new HashMap<String, String>();
	    requestHeaders.put("Content-Type", "application/xml");
	    requestHeaders.put("Accept", "application/xml");  
	    Response XML1 = con.httpGet("http://"+Constants.HOST+":"+Constants.PORT+"/qcbin/rest/domains/"+Constants.DOMAIN+"/projects/"+Constants.PROJECT+"/test-sets?query={id["+testSetID+"]}&fields=name", null, requestHeaders);
	    //System.out.println("XML is ::"+XML1);
	    return XML1;
}


public Response getTestIDsfromTestSet(String testSetID) throws Exception {
	// TODO Auto-generated method stub
		Map<String, String> requestHeaders = new HashMap<String, String>();
	    requestHeaders.put("Content-Type", "application/xml");
	    requestHeaders.put("Accept", "application/xml");  
	    Response XML1 = con.httpGet("http://"+Constants.HOST+":"+Constants.PORT+"/qcbin/rest/domains/"+Constants.DOMAIN+"/projects/"+Constants.PROJECT+"/test-instances?query={cycle-id["+testSetID+"]}&fields=test-id&page-size=300", null, requestHeaders);
	    //System.out.println("XML is ::"+XML1);
	    return XML1;
}


public Response getNonPassedTestIDsfromTestSet(String testSetID) throws Exception {
	// TODO Auto-generated method stub
		Map<String, String> requestHeaders = new HashMap<String, String>();
	    requestHeaders.put("Content-Type", "application/xml");
	    requestHeaders.put("Accept", "application/xml");  
	    Response XML1 = con.httpGet("http://"+Constants.HOST+":"+Constants.PORT+"/qcbin/rest/domains/"+Constants.DOMAIN+"/projects/"+Constants.PROJECT+"/test-instances?query={cycle-id["+testSetID+"];status[not%20Passed]}&fields=test-id&page-size=300", null, requestHeaders);
	    //System.out.println("XML is ::"+XML1);
	    return XML1;
}


public Response getNORUNTestIDsfromTestSet(String testSetID) throws Exception {
	// TODO Auto-generated method stub
		Map<String, String> requestHeaders = new HashMap<String, String>();
	    requestHeaders.put("Content-Type", "application/xml");
	    requestHeaders.put("Accept", "application/xml");  
	    Response XML1 = con.httpGet("http://"+Constants.HOST+":"+Constants.PORT+"/qcbin/rest/domains/"+Constants.DOMAIN+"/projects/"+Constants.PROJECT+"/test-instances?query={cycle-id["+testSetID+"];status[%22no%20run%22]}&fields=test-id&page-size=300", null, requestHeaders);
	    //System.out.println("XML is ::"+XML1);
	    return XML1;
}


public Response getRunStatus(String testSetID,String testID) throws Exception {

		Map<String, String> requestHeaders = new HashMap<String, String>();
	    requestHeaders.put("Content-Type", "application/xml");
	    requestHeaders.put("Accept", "application/xml");  
	    Response XML1 = con.httpGet("http://"+Constants.HOST+":"+Constants.PORT+"/qcbin/rest/domains/"+Constants.DOMAIN+"/projects/"+Constants.PROJECT+"/test-instances?query={cycle-id["+testSetID+"];test-id["+testID+"]}&fields=status", null, requestHeaders);
	    return XML1;
}


public Response getTestNamesfromTestIDs(List<String>testIDs) throws Exception {
	// TODO Auto-generated method stub
	
		String queryTestIDs = testIDs.get(0);
		for(int i = 1;i<testIDs.size();i++ )
		{
			
			queryTestIDs = queryTestIDs + "%20or%20" + testIDs.get(i);
			
		}
		
		Map<String, String> requestHeaders = new HashMap<String, String>();
	    requestHeaders.put("Content-Type", "application/xml");
	    requestHeaders.put("Accept", "application/xml");  
	    Response XML1 = con.httpGet("http://"+Constants.HOST+":"+Constants.PORT+"/qcbin/rest/domains/"+Constants.DOMAIN+"/projects/"+Constants.PROJECT+"/tests?query={id["+queryTestIDs+"]}&fields=name&page-size=300", null, requestHeaders);
	    //System.out.println("XML is ::"+XML1);
	    return XML1;
}
//

public Response createTestRUN() throws Exception {
	// TODO Auto-generated method stub
	Map<String, String> requestHeaders = new HashMap<String, String>();
    requestHeaders.put("Content-Type", "application/json");
    String entityUrl="http://"+Constants.HOST+":"+Constants.PORT+"/qcbin/rest/domains/"+Constants.DOMAIN+"/projects/"+Constants.PROJECT+"/test-instances/testRun";
    String filenameData = "";
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    bytes.write(filenameData.getBytes());
    bytes.close();
    
    Response response =
            con.httpPost(entityUrl, bytes.toByteArray(), requestHeaders);
   return response;
   
}

public Response getTestComponent(String testID) throws Exception {
	Map<String, String> requestHeaders = new HashMap<String, String>();
    requestHeaders.put("Content-Type", "application/xml");
    requestHeaders.put("Accept", "application/xml");  
    Response XML1 = con.httpGet("http://"+Constants.HOST+":"+Constants.PORT+"/qcbin/rest/domains/"+Constants.DOMAIN+"/projects/"+Constants.PROJECT+"/tests?query={id["+testID+"]}&fields=user-03", null, requestHeaders);
    return XML1;
}





}

