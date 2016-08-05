package com.comcast.neto.alm;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.comcast.almadapter.client.ALMAdapterClient;
import com.comcast.almadapter.client.entity.Test;
import com.comcast.almadapter.client.entity.TestRun;
import com.comcast.almadapter.client.entity.TestStep;
import com.comcast.almadapter.client.exception.ALMClientException;
import com.comcast.utils.ComcastTest;
import com.comcast.utils.ReportPath;
import com.google.gson.Gson;



public class ALMUpdaterClient extends ComcastTest {
	
	
       private static ALMAdapterClient client;
       private static final String URL = "http://tqmapp-wc-1p.sys.comcast.net:8080/almadapter/";
       private static final String ALM_USER_NAME = Constants.USERNAME;
       private static final String ALM_PASSWORD = Constants.PASSWORD;

       private static final String DOMAIN = "CET_SIT";
       private static final String PROJECT = "SA_SIT";

  
       
       public synchronized  void createTestRUN(String testSetID,String testName,String testID,String status, String duration) throws Exception 
   	   {
   		
    	   
    	   client = new ALMAdapterClient(URL, ALM_USER_NAME, ALM_PASSWORD);
    	   ArrayList<TestStep> testSteps = new ArrayList<TestStep>();
    	   Date dNow = new Date();
           SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
           SimpleDateFormat ft2 = new SimpleDateFormat ("hh:mm");
           String dateString = ft.format(dNow);
           String timeString = ft2.format(dNow);
           
           
           String reportPath=ReportPath.getInstance().getReportPath();
           
           testSteps.add(new TestStep("Step1", status,dateString, timeString));
           testSteps.add(new TestStep("Step2", status,dateString, timeString));
           ArrayList<Test> tests = new ArrayList<com.comcast.almadapter.client.entity.Test>();
      
           Test test = new com.comcast.almadapter.client.entity.Test(testID, status,
           dateString, timeString, testSteps);
           test.setDuration(duration);
           tests.add(test);
      
           // params are testset name, test folder path, owner NT id, test list
           
           ALMTestInformation ALM= new ALMTestInformation();
           
           //TestRun testRun = new TestRun(ALM.GetTestSetName(testSetID), testSetPath, ALM_USER_NAME, tests);
           //Alternative Implementations with TestID instead of testSetPath and testSetName
           
           TestRun testRun = new TestRun();
           testRun.setLoginId(ALM_USER_NAME);
           testRun.setTestList(tests);
           testRun.setTestSetId(testSetID);
           
          
           try {
               String runInfo = client.createTestRun(DOMAIN, PROJECT, testRun);
               System.out.println("Returns Run Info");
               System.out.println(runInfo);
               Map<String, Object> javaRootMapObject = new Gson().fromJson(runInfo, Map.class);
               String runID =(String) ((Map)((List)((Map)(javaRootMapObject.get("CreateResultResponse"))).get("RunInfo")).get(0)).get("RunId");
               System.out.println("runID:" + runID);
               //client.createTestCaseAttachment(DOMAIN, PROJECT, testID, reportPath+"\\"+testName+".zip");
               client.createTestRunAttachment(DOMAIN, PROJECT, runID, reportPath+"\\"+testName+".zip");
               
                             
           	} catch (ALMClientException exe) {
                  System.out.println("Exception should not be thrown");
                  System.out.print(exe);
                  exe.printStackTrace();
           }
   		
   	    }
       
       
}

