package com.comcast.utils;

import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Read Test settings from framework.propeties files
 *
 */
public class TestSettings {
	Properties properties;
	String key;
	long value;
	public TestSettings(){
		loadProperties();
	}
	/**
	 * Get property value 
	 * @param key property key
	 * @return value of the property
	 */
	public String getSettings(String key){
		return properties.getProperty(key);
	}
	
	/**
	 * Get property value 
	 * @param key property key
	 * @return 
	 * @return value of the property
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public  void setSettings(String key,long value) throws FileNotFoundException, IOException{
		this.key=key;
		this.value=value;
		properties.store(new FileOutputStream("framework.properties"), null);

	}
	/**
	 * Get the browser name in which tests needs to be run
	 */
	public String getBrowser(){
		String browser= System.getenv("browser");
		if(browser==null || browser == ""){
			browser=properties.getProperty("browser","firefox");
		}
		return browser;
	}
	
	
	/**
	 * Get the TestSetID in which will have tests to be run
	 */
	public String getTestSetID(){
		String testSetID= System.getenv("testsetID");
		if(testSetID==null || testSetID == ""){
			testSetID=properties.getProperty("testsetID","");
		}
		
		return testSetID;
	}
	
	/**
	 * Get the ALM Host for the rest API call
	 */
	public String getALMHost(){
		String HOST= System.getenv("HOST");
		if(HOST==null || HOST == ""){
			HOST=properties.getProperty("HOST","");
		}
		
		return HOST;
	}
	
	
	/**
	 * Get the ALM Port for the rest API call
	 */
	public String getPORT(){
		String PORT= System.getenv("PORT");
		if(PORT==null || PORT == ""){
			PORT=properties.getProperty("PORT","");
		}
		
		return PORT;
	}
	
	
	/**
	 * Get the ALM username for the rest API call
	 */
	public String getALM_Username(){
		String ALM_Username= System.getenv("ALM_Username");
		if(ALM_Username==null || ALM_Username == ""){
			ALM_Username=properties.getProperty("ALM_Username","");
		}
		
		return ALM_Username;
	}
	
	
	/**
	 * Get the ALM Password for the rest API call
	 */
	public String getALM_Password(){
		String ALM_Password= System.getenv("ALM_Password");
		if(ALM_Password==null || ALM_Password == ""){
			ALM_Password=properties.getProperty("ALM_Password","");
		}
		
		return ALM_Password;
	}
	
	
	/**
	 * Get the ALM DOMAIN for the rest API call
	 */
	public String getDOMAIN(){
		String DOMAIN= System.getenv("DOMAIN");
		if(DOMAIN==null || DOMAIN == ""){
			DOMAIN=properties.getProperty("DOMAIN","");
		}
		
		return DOMAIN;
	}
	

		
	
	/**
	 * Get the ALM PROJECT for the rest API call
	 */
	public String getPROJECT(){
		String PROJECT= System.getenv("PROJECT");
		if(PROJECT==null || PROJECT == ""){
			PROJECT=properties.getProperty("PROJECT","");
		}
		
		return PROJECT;
	}
	
	/**
	 * Get the TestSetID in which will have tests to be run
	 */
	@Test
	public String testSetFolderPath(){
		String testSetFolderPath= System.getenv("testSetFolderPath");
		if(testSetFolderPath==null || testSetFolderPath == ""){
			testSetFolderPath=properties.getProperty("testSetFolderPath","");
		}
		
		return testSetFolderPath;
	}
	
	/**
	 * Get the if Versioned for the rest API call
	 */
	public String getifVersioned(){
		String VERSIONED= System.getenv("VERSIONED");
		if(VERSIONED==null || VERSIONED == ""){
			VERSIONED=properties.getProperty("VERSIONED","");
		}
		
		return VERSIONED;
	}
	
	/**
	 * Get the if rerun is true or false
	 */
	public String getrerunstatus(){
		String rerunstatus= System.getenv("rerun");
		if(rerunstatus==null || rerunstatus == ""){
			rerunstatus=properties.getProperty("rerun","");
		}
		if(rerunstatus==null || rerunstatus == ""){
			rerunstatus="false";
		}
		
		return rerunstatus;
	}
	
	// added by harsh for PE_Rerun
	//83/2016
	public String getPERerunStatus(){
		String rerunstatus= System.getenv("PE_RERUN");
		if(rerunstatus==null || rerunstatus == ""){
			rerunstatus=properties.getProperty("PE_RERUN","");
		}
		if(rerunstatus==null || rerunstatus == ""){
			rerunstatus="false";
		}
		
		return rerunstatus;
	}
	
	// added by harsh for PE_Rerun
		//8/15/2016
		public String getGridIP(){
			String rerunstatus= System.getenv("grid_ip");
			if(rerunstatus==null || rerunstatus == ""){
				rerunstatus=properties.getProperty("grid_ip","");
			}
			if(rerunstatus==null || rerunstatus == ""){
				rerunstatus="false";
			}
			
			return rerunstatus;
		}
	
	/**
	 * Get the if rerun is true or false
	 */
	public String getGRIDstatus(){
		String GRIDstatus= System.getenv("GRID");
		if(GRIDstatus==null || GRIDstatus == ""){
			GRIDstatus=properties.getProperty("GRID","");
		}
		if(GRIDstatus==null || GRIDstatus == ""){
			GRIDstatus="false";
		}
		
		return GRIDstatus;
	}
	
	
	/**
	 * Get the getFailRunRetryCount for each test to avoid False Positives
	 */
	public int getFailRunRetryCount(){
		String runCount= System.getenv("FailRunRetryCount");
		if(runCount==null || runCount == ""){
			runCount=properties.getProperty("FailRunRetryCount","");
		}
		
		if(runCount==null || runCount == ""){
			runCount="1";
		}
		return Integer.valueOf(runCount);
	}
	
	
	
	/**
	 *	Get the environment under test 
	 */
	public String getEnvironmentToTest(){
		String test= System.getenv("test");
		if(test==null || test == ""){
			test=properties.getProperty("test","UAT");
		}
		return test;
	}
	
	public String getOracleVersion(){
		String version = System.getenv("version");
		if(version==null || version == ""){
			version=properties.getProperty("version","8.5");
		}
		return version;
		
	}
	
	
	
	
	
	/**
	 *	Get the URL of the application under test 
	 */
	public String getApplicationCMURL(){
		if(getEnvironmentToTest().equals("UAT")){			
			return getSettings("century_uat_url_cm");
		}else if(getEnvironmentToTest().equals("SOAK")){
			return getSettings("century_soak_url_cm");
		}else if(getEnvironmentToTest().equals("PROD")){
			return getSettings("century_prod_url_cm");
		}else if(getEnvironmentToTest().equals("STG")){
			return getSettings("century_stg_url_cm");
		}else if(getEnvironmentToTest().equals("INT")){
			return getSettings("century_int_url_cm");
		}else if(getEnvironmentToTest().equals("RES_QA")){
			return getSettings("century_rqa_url");	
		}else if(getEnvironmentToTest().equals("RES_UAT")){
			return getSettings("century_ruat_url_cm");	
			
		}else if(getEnvironmentToTest().equals("DEV")){
			return getSettings("century_dev_url_cm");	
		
		}else if(getEnvironmentToTest().equals("UAT_KM")) {
			return getSettings("century_uat_km");
		}else if(getEnvironmentToTest().equals("CI")) {
			return getSettings("century_ci_url_cm");
		}else{
			return getSettings("century_qa_url_cm");
		}
		
	}
	
	
	public String getApplicationCSOURL(){
		if(getEnvironmentToTest().equals("UAT")){			
			return getSettings("century_uat_url_cso");
		}else if(getEnvironmentToTest().equals("SOAK")){
			return getSettings("century_soak_url_cso");
		}else if(getEnvironmentToTest().equals("PROD")){
			return getSettings("century_prod_url_cso");
		}else if(getEnvironmentToTest().equals("STG")){
			return getSettings("century_stg_url_cso");
		}else if(getEnvironmentToTest().equals("INT")){
			return getSettings("century_int_url_cso");
		}else if(getEnvironmentToTest().equals("RES_QA")){
			return getSettings("century_rqa_url_cso");	
		}else if(getEnvironmentToTest().equals("RES_UAT")){
			return getSettings("century_ruat_url_cso");	
			
		}else if(getEnvironmentToTest().equals("DEV")){
			return getSettings("century_dev_url_cso");	
		
		}else if(getEnvironmentToTest().equals("UAT_KM")) {
			return getSettings("century_uat_km");
		}else if(getEnvironmentToTest().equals("CI")) {
			return getSettings("century_ci_url_cso");
		}else{
			return getSettings("century_qa_url_cso");
		}
		
	}
	
	public String smartConnectUrl(){
		if(getEnvironmentToTest().equals("QA")){
			return getSettings("smart_QA");
		}else{
			return getSettings("url_dataBase");
		}
	}
	/**
	 *	Get the URL of the Information Manager application  
	 */
	public String getIMURL(){
		
		if(getEnvironmentToTest().equals("QA")||getEnvironmentToTest().equals("RES_QA")){
			return getSettings("im_qa_url");
		}
		else{
			return getSettings("im_uat_url");
		}
		
	
	}
	public String getEnvironment() {
		return getSettings("Environment");
	}
	/**
	 * Load properties from framework.properties file
	 */
	private void loadProperties() {
		properties= new Properties();
		try {
			String relativePath=TestUtils.getRelativePath();
			properties.load(new FileInputStream(relativePath + "//src//test//resources"+ File.separator +"framework.properties"));
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getSheetName() {
		if(getEnvironmentToTest().equals("UAT") ||getEnvironmentToTest().equals("UAT_KM") || (getEnvironmentToTest().equals("SOAK"))||(getEnvironmentToTest().equals("PROD"))){
			return getSettings("uat_SheetName");
		//}else if((getEnvironmentToTest().equals("SOAK"))||(getEnvironmentToTest().equals("PROD"))){
		//	return getSettings("soak_SheetName");
		}
		else if((getEnvironmentToTest().equals("STG"))){
			return getSettings("stg_SheetName");
		}
		else if((getEnvironmentToTest().equals("RES_QA"))){
			return getSettings("rqa_SheetName");
		}else if((getEnvironmentToTest().equals("RES_UAT"))){
			return getSettings("ruat_SheetName");
		}
		else if((getEnvironmentToTest().equals("RES_UAT"))){
			return getSettings("ruat_SheetName");
		}
		else if((getEnvironmentToTest().equals("DEV"))){
			return getSettings("dev_SheetName");
		}
		else{
			return getSettings("qa_SheetName");
		}
	}
	
	/**
     *             Get the URL of the Maestro Components application  
     */
	public String getMCURL(){
	                     
	     if(getEnvironmentToTest().equals("UAT")){
	                     return getSettings("maestro_uat_url");
	     }
	     else{
	                     return getSettings("maestro_qa_url");
	     }                              
	     
	}
	
	/**
	 *	Get the URL of the Diagnostics widget
	 */	
	public String getITGURL(){
		if(getEnvironmentToTest().equals("UAT")){
			return getSettings("ITG_uat_url");
		}
		else{
			return getSettings("ITG_qa_url");
		}
	}
	
	/**
	 *	Get the URL of .com ITG
	 */	
	public String getCOMITGURL(){
		if(getEnvironmentToTest().equals("UAT")){
			return getSettings("com_ITG_uat_url");
		}else if(getEnvironmentToTest().equals("SOAK")){
			return getSettings("com_ITG_soak_url");
		}		
		else{
			return getSettings("com_ITG_qa_url");
		}
	}
	
	/**
	 *	Get the URL of the HTML ITG widget
	 */	
	public String getHTMLITGURL(){
		if(getEnvironmentToTest().equals("UAT")){
			return getSettings("HTML_Mobflow_uat_url");
		}else if(getEnvironmentToTest().equals("SOAK")){
			return getSettings("HTML_Mobflow_soak_url");
		}else{
			return getSettings("HTML_Mobflow_qa_url");
		}
	}
	
	
	/**
	 *	Get the URL of the Information Manager application  
	 */
	public String getRCURL(){
		
		if(getEnvironmentToTest().equals("QA")){
			return getSettings("RC_qa_url");
		}
		else{
			return getSettings("RC_uat_url");
		}
		
	
	}
	
	/**
     *             Get the URL of the gmail  
     */
	public String getGmailURL(){
	   return getSettings("gmail_url");
	}
	
	//QC Details
	
	public String getQCUrl() {
		return getSettings("qcURL");
	}
	
	public String getQCUserName() {
		return getSettings("qcUserName");
	}
	
	public String getQCPassword() {
		return getSettings("qcPassword");
	}
	
	public String getQCDomain() {
		return getSettings("qcDomain");
	}
	public String getQCProject() {
		return getSettings("qcProject");
	}
	public String getQCTestLabPath() {
		return getSettings("qcTestLabPath");
	}
	public String getQCUpdate() {
		return getSettings("qcUpdate");
	}
	
	public String getUserPassword(String userName) {
		// TODO Auto-generated method stub
		return getSettings(userName);
	}
	
	public String getAPPDOMAIN(){
		String DOMAIN= System.getenv("APP_DOMAIN");
		if(DOMAIN==null || DOMAIN == ""){
			DOMAIN=properties.getProperty("APP_DOMAIN","");
		}
		
		return DOMAIN;
	}
}
