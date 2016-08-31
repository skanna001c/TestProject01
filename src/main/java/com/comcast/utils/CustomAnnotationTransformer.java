package com.comcast.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IInvokedMethod;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.ITestAnnotation;

public class CustomAnnotationTransformer  implements IAnnotationTransformer{
	
	TestSettings settings ;
	IDataDump dumpTable ;
	
	public CustomAnnotationTransformer(){
		this.settings = new TestSettings();
		//this.dumpTable = new DataDump(TestUtils.TestName);
		//dumpTable.loadData(TestUtils.TestName);
	}

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		// TODO Auto-generated method stub
		
//		if("invoke".equals(testMethod.getName())){
//			annotation.setEnabled(false);
//		}
		
	
		
		System.out.println("inside transform method: "+testMethod.getName() + " inside: " + annotation.getTestName());
		this.dumpTable = new DataDump(TestUtils.TestName,settings.getDumpLocation());
		dumpTable.loadData();

			if(settings.getPERerunStatus().equalsIgnoreCase("true")){
	
				if(dumpTable.getValue(testMethod.getName()+"_status").equalsIgnoreCase("pass")){
					//method.getTestMethod().setInvocationCount(0);
					annotation.setEnabled(false);
					System.out.println("Test method: "+testMethod.getName()+" already passed and not required to be rerun");
					//throw new SkipException("Test method: "+testName.getName()+" already passed and not required to be rerun");
				}
			
			}
		

	}

}
