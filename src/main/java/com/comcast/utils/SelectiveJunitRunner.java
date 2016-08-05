package com.comcast.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;

import com.comcast.neto.alm.ALMTestInformation;


public class SelectiveJunitRunner extends BlockJUnit4ClassRunner {

    public SelectiveJunitRunner(Class<?> klass) throws Throwable {
        super(klass);
    }
    
    protected TestSettings settings;
    
    @Override
    protected List<FrameworkMethod> computeTestMethods() {
        
    	settings=new TestSettings();
    	 List<FrameworkMethod> filteredList = null;
    	
    	//String testSetID=settings.getTestSetID();
    	String testSetID=settings.getTestSetID();
    	
    	if (testSetID == null || testSetID =="")
    	{	
		    	
    		
    		 List<String> ignoreList = Arrays.asList("");
    	        List<FrameworkMethod> computeTestMethods = super.computeTestMethods();
    	        filteredList = new ArrayList<>();
    	        for (FrameworkMethod frameworkMethod : computeTestMethods) {
    	            if (!ignoreList.contains(frameworkMethod.getName()))
    	                filteredList.add(frameworkMethod);
    	        }
    		
    	}
    	
    	else 
    	{
    		
    		
    		List<String> testNames = null;
			//System.out.println(testSetID);
			 ALMTestInformation a= new ALMTestInformation();
			 List<String> testIDs;
			 
			 
			 
			try {
				
				String rerunstatus = settings.getrerunstatus();
				if(rerunstatus.equals("false"))
				{	
					testIDs = a.GetTestIDsFromTestSet(testSetID);
					
				}
				
				else if(rerunstatus.equals("NORUN"))
				{	
					testIDs = a.getNORUNfromTestSet(testSetID);
					
				}
				
				else
				{
					testIDs = a.getNonPassedfromTestSet(testSetID);
				}
				if (testIDs.size()==0)
				{	
					List<String> tempList;
					tempList = new ArrayList<String>();
					tempList.add("testMethod");
					testNames = tempList;
				}
				else
				{	
				testNames = a.GetTestNames(testIDs);
				testNames.add("testMethod");
				}
					
					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	    	//Inject runList List
	        List<String> runList = testNames;
	        List<FrameworkMethod> computeTestMethods = super.computeTestMethods();
	        filteredList = new ArrayList<>();
	        for (FrameworkMethod frameworkMethod : computeTestMethods) {
	            if (runList.contains(frameworkMethod.getName()))
	                filteredList.add(frameworkMethod);
	        }
    		
    		
    		
    	}
    	
        return filteredList;
    }



}