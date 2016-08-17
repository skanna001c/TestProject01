package com.comcast.utils;

import java.util.ArrayList;
import java.util.List;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

public class TestFilter implements IMethodInterceptor {

	@Override
	public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("inside method interceptor");
		List<IMethodInstance> finalMethods = new ArrayList<IMethodInstance>();

		String testName = context.getCurrentXmlTest().getName();
		TestSettings settings = new TestSettings();
		IDataDump dataDump = new DataDump(testName);

		if(settings.getPERerunStatus().equalsIgnoreCase("true")){
			dataDump.loadData();
			
			for(IMethodInstance method: methods){

				if(dataDump.getValue(method.getMethod().getMethodName()+"_status").equalsIgnoreCase("pass")){
					System.out.println("Test method: "+method.getMethod().getMethodName()+" already passed and not required to be rerun");
				}else{
					finalMethods.add(method);
				}
			}
			return finalMethods;
		}else{
			return methods;
		}
	
	}

}
