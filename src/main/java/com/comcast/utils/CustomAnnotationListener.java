package com.comcast.utils;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener2;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;

public class CustomAnnotationListener implements IInvokedMethodListener2 {
	
	long startTime;
	long endTime;


	@Override
	public synchronized void  afterInvocation(IInvokedMethod method, ITestResult result, ITestContext context) {
		// TODO Auto-generated method stub
		//System.out.println("inside perftransaction after invoke method");
		//String methodname = method.toString();
		//System.out.println(methodname);
		PerfTransaction annotation = method.getTestMethod().getConstructorOrMethod().getMethod().getAnnotation(PerfTransaction.class);
		if(annotation==null){
			return;
		}
		endTime = System.currentTimeMillis();
		
		double duration = (endTime-startTime)/1000;
		String name = annotation.name();
		//System.out.println("The transaction with name: " + name + " started at :" + startTime);
		//System.out.println("The transaction with name: " + name + " ended at :" + endTime);
		//System.out.println("The transaction with name: " + name + " took :" + duration + " secs");
	    //ComcastTest.report.reportDoneEvent(name,"The transaction with name: " + name + " took :" + duration + " secs");
		

	}

	@Override
	public synchronized void beforeInvocation(IInvokedMethod method, ITestResult result, ITestContext context) {
		// TODO Auto-generated method stub
		//System.out.println("inside perftransaction before invoke method");
		
		
		PerfTransaction annotation = method.getTestMethod().getConstructorOrMethod().getMethod().getAnnotation(PerfTransaction.class);
		if(annotation==null){
			return;
		}
		startTime = System.currentTimeMillis();
		//System.out.println("start time of transaction is " + startTime);
		

		
		
	}
	
	@Override
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// TODO Auto-generated method stub

	}

}
