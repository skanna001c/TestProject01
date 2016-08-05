package com.comcast.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ReportPath {
	private static ReportPath instance = null;
	private String reportPath;
	protected TestUtils testutils;

	@SuppressWarnings("unused")
	protected ReportPath() {
		String finalfolder = "Result_" + TestUtils.getTimeStamp();
		testutils = new TestUtils();
		
		reportPath=System.getenv("RESULTPATH");
		String BVT=System.getenv("BVT");
		System.out.println("BVT:"+BVT);
		boolean success; 
		if(reportPath==null || reportPath == ""){
			reportPath = TestUtils.getRelativePath() + File.separator
					+ FrameworkConstants.RESULT_FOLDER + File.separator + finalfolder;
			 success = (new File(reportPath)).mkdirs();
		}
		else
		{
			success=(new File(reportPath)).mkdirs();
			
		}	
			
		System.out.println("HTML Report Path: " +reportPath);
		/*rpath = rpath + File.separator + finalfolder;
		HashMap<String, String> Resultpath = new HashMap<String, String>();
		Resultpath.put("RESULTPATH",rpath);
		testutils.setEnv(Resultpath);
		System.out.println(Resultpath.get("RESULTPATH"));
		System.out.println(System.getenv("RESULTPATH"));*/
		
	}

	/**
	 * Get the current report folder
	 * @return
	 */
	public String getReportPath(){
		return reportPath;
	}
	
	public static synchronized ReportPath getInstance() {
		if (instance == null) {
			instance = new ReportPath();
		}
		return instance;
	}
}
