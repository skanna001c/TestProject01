package com.comcast.neto.alm;


import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Field;



/*import org.apache.jasper.tagplugins.jstl.core.Set ;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;*/
public class ALMTestInformation 
{
	static String almDomain=Constants.DOMAIN;
	static String almProject=Constants.PROJECT;
    
	public static void main(String[] args) throws Exception 
	{
		   XmlLibraries xL=new XmlLibraries();
		   String l=xL.getsysTimeLog(); 
		   
		  // for(int i=0;i<tc.length;i++)
		  // {
		        ///updateRunStatus(args[0],args[1],args[2]);
		   /*ALMTestResultUpdater a = new ALMTestResultUpdater();
		  //a.updateRunStatus("58421","259313","Failed");
		   a.updateRunStatus("58421","381502","Passed");*/
		    
		   //}
		  //insertAttachment("58421","259313","C:/ALMConfig/ConfigFiles/Results.zip");
		   
		  // GetTestID("validate_VerifyTheWordChangeForMissingEmailIndicatorForVideoOnlyCustomer_300661");
		/*   ALMTestResultUpdater a = new ALMTestResultUpdater();
		   //List<String> testIDs =a.getNonPassedfromTestSet("61832");
		   List<String> testIDs =a.getNORUNfromTestSet("61832");
		   List<String> testNames = a.GetTestNames(testIDs);
		   System.out.println(testIDs);
		   System.out.println(testNames);*/
		   
		/*   HashMap<String, String> myMap = new HashMap<String, String>();
		   myMap.put("test","result");
		   a.setEnv(myMap);
		   System.out.println(myMap.get("test"));*/
		   
		   
		   /*String pname =getPackageName("setTestSetFolderPath");
		   System.out.println(pname);*/
		 /* ArrayList<Class> classes = new ArrayList<Class>();
		   classes = getClasses("com.comcast.einstein.usefulTools");
		   String classNames = classes.toString();
		   String classNames2 = classes.get(0).getName();
		   for (Method method : classes.get(0).getDeclaredMethods()) {	
			    String name = method.getName();
			    System.out.println(name);
			}*/
		   
		   //getPackageName("");
		   //String path = "C:\\Users\\vvinay00c\\workspace\\Einstein_New\\Results";
		   ALMTestInformation b = new ALMTestInformation();
		   /*b.updateRunStatus("58421","381502","Failed");
		   b.writeDuration("58421","381502", "88");
		   */
		   System.out.println(b.GetTestSetName("58421"));
		   
		   
		/*   String resultpath=lastFileModified(path);
		   String status = readresult(resultpath+"\\result.txt");
		   System.out.println("Status"+resultpath+" : " +status);     */              
	}
	
	public synchronized  void updateRunStatus(String cycleid,String testid, String status) throws Exception 
	{
        
        XmlLibraries xL=new XmlLibraries();
        String lastupdateTime=xL.getsysTime();
		EntityUpdater rest = new EntityUpdater(almDomain, almProject);						
		rest.loginCall();
		Response instanceXML=rest.getInstanceId(cycleid,testid);
		//Response instanceXML=rest.getInstanceIdFromRun(cycleid,testid);		
		String outXML=instanceXML.toString();	    
	    String XML_wf= xL.RemoveNameSpaces(outXML);
	    //will have alternate with XML node function if confusion with X Path
	    String Instanceid=xL.GetValueByXPath(XML_wf,"/Entities/Entity/Fields/Field/Value");
	    //System.out.println("Test Instanceid is ::"+Instanceid);
		rest.updateTestCase(Instanceid, "status", status);
		rest.logoutCall();
   }
	
	public void insertAttachment(String cycleid,String testid,String attachmentPath) throws Exception 
	{
        
        XmlLibraries xL=new XmlLibraries();
        Attachments at=new Attachments();
       
        String lastupdateTime=xL.getsysTimeLog();
		EntityUpdater rest = new EntityUpdater(almDomain, almProject);						
		rest.loginCall();
		Response instanceXML=rest.getInstanceIdFromRun(cycleid,testid);
		String outXML=instanceXML.toString();	    
	    String XML_wf= xL.RemoveNameSpaces(outXML);
	    //System.out.println(XML_wf);
	    String count=xL.nodeFromKey(XML_wf, "TotalResults=\"", "\"><Entity Type=\"run\">");
	    //System.out.println("No:of Runs are::"+count);
	    //will have alternate with XML node function if confusion with X Path
	    String RunsID=xL.GetValueByXPath(XML_wf,"/Entities/Entity["+count+"]/Fields/Field[4]/Value");
	    //System.out.println("RunsID is ::"+RunsID);
	    byte[] filedata;
	    if(attachmentPath.contains(".txt"))
	    {
	    filedata= at.convertTobytes(attachmentPath);
	    }
	    else
	    {
	     String attachmentPathTemp=attachmentPath;
		 filedata= at.convertTobytes(attachmentPathTemp);
	    }
		rest.CreateTCAttachment(RunsID, filedata, "Log_"+lastupdateTime+".zip");
		rest.logoutCall();
   }
	

	
	
	public void writeDuration(String cycleid,String testid,String TimeTaken) throws Exception 
	{
        
        XmlLibraries xL=new XmlLibraries();
        Attachments at=new Attachments();
       
        String lastupdateTime=xL.getsysTimeLog();
		EntityUpdater rest = new EntityUpdater(almDomain, almProject);						
		rest.loginCall();
		Response instanceXML=rest.getInstanceIdFromRun(cycleid,testid);
		String outXML=instanceXML.toString();	    
	    String XML_wf= xL.RemoveNameSpaces(outXML);
	    //will have alternate with XML node function if confusion with X Path
	    String RunsID=xL.GetValueByXPath(XML_wf,"/Entities/Entity/Fields/Field[5]/Value");
	    //System.out.println("RunsID is ::"+RunsID);
		byte[] filedata= TimeTaken.getBytes();
 
		rest.RunDuration(RunsID, filedata);
	 
		
		rest.logoutCall();
   }
	
	public static String[] TestCaseGroup(String TestCasedata)
	{
	   
	    String[] TestCase = TestCasedata.split(",");
	    for (int i = 0; i < TestCase.length; ++i)
	    {
	     System.out.println(i+" TestCaseID::" + TestCase[i]);
	    }
	    
		return TestCase;
	}

	public  String  GetTestID(String testName) throws Exception 
	{
        
        XmlLibraries xL=new XmlLibraries();

		EntityUpdater rest = new EntityUpdater(almDomain, almProject);						
		rest.loginCall();
		Response instanceXML=rest.getTestID(testName);
		String outXML=instanceXML.toString();	    
	    String XML_wf= xL.RemoveNameSpaces(outXML);
	    //will have alternate with XML node function if confusion with X Path
	    String testID=xL.GetValueByXPath(XML_wf,"/Entities/Entity/Fields/Field/Value");
	    //System.out.println("Test Instanceid is ::"+testID);
		rest.logoutCall();
		return testID;
   }
	
	public  String  GetTestSetName(String testSetID) throws Exception 
	{
        
        XmlLibraries xL=new XmlLibraries();

		EntityUpdater rest = new EntityUpdater(almDomain, almProject);						
		rest.loginCall();
		Response instanceXML=rest.getTestSetName(testSetID);
		String outXML=instanceXML.toString();	    
	    String XML_wf= xL.RemoveNameSpaces(outXML);
	    //will have alternate with XML node function if confusion with X Path
	    String testSetName=xL.GetValueByXPath(XML_wf,"/Entities/Entity/Fields/Field[2]/Value");
	    //System.out.println("Test Instanceid is ::"+testID);
		rest.logoutCall();
		return testSetName;
   }

	
	
	
	public  List<String>  GetTestIDsFromTestSet(String testSetID) throws Exception 
	{
        
        XmlLibraries xL=new XmlLibraries();

		EntityUpdater rest = new EntityUpdater(almDomain, almProject);						
		rest.loginCall();
		Response instanceXML=rest.getTestIDsfromTestSet(testSetID);
		String outXML=instanceXML.toString();	    
	    String XML_wf= xL.RemoveNameSpaces(outXML);
	//    System.out.println(XML_wf);
	//    System.out.println(XML_wf);
	    List<String> node1 = xL.GetNodeListByXPath(XML_wf,"/Entities/Entity/Fields/Field[2]");
	    System.out.println("TestIDs to be executed::"+node1);
		rest.logoutCall();
		return node1;
   }
	
	public  List<String>  getNonPassedfromTestSet(String testSetID) throws Exception 
	{
        
        XmlLibraries xL=new XmlLibraries();

		EntityUpdater rest = new EntityUpdater(almDomain, almProject);						
		rest.loginCall();
		Response instanceXML=rest.getNonPassedTestIDsfromTestSet(testSetID);
		String outXML=instanceXML.toString();	    
	    String XML_wf= xL.RemoveNameSpaces(outXML);
	    List<String> node1 = xL.GetNodeListByXPath(XML_wf,"/Entities/Entity/Fields/Field[2]");
	    System.out.println("TestIDs to be executed::"+node1);
		rest.logoutCall();
		return node1;
   }
	
	
	public  List<String>  getNORUNfromTestSet(String testSetID) throws Exception 
	{
        
        XmlLibraries xL=new XmlLibraries();

		EntityUpdater rest = new EntityUpdater(almDomain, almProject);						
		rest.loginCall();
		Response instanceXML=rest.getNORUNTestIDsfromTestSet(testSetID);
		String outXML=instanceXML.toString();	    
	    String XML_wf= xL.RemoveNameSpaces(outXML);
	    List<String> node1 = xL.GetNodeListByXPath(XML_wf,"/Entities/Entity/Fields/Field[2]");
	    System.out.println("TestIDs to be executed::"+node1);
		rest.logoutCall();
		return node1;
   }
	
	

  public String getRunStatus(String testSetID, String testID) throws Exception {
		
	  		XmlLibraries xL=new XmlLibraries();
	  		EntityUpdater rest = new EntityUpdater(almDomain, almProject);						
			rest.loginCall();
			Response instanceXML=rest.getRunStatus(testSetID, testID);
			String outXML=instanceXML.toString();	    
		    String XML_wf= xL.RemoveNameSpaces(outXML);
		    //will have alternate with XML node function if confusion with X Path
		    String testStatus=xL.GetValueByXPath(XML_wf,"/Entities/Entity/Fields/Field[2]/Value");
		    //System.out.println("Test Instanceid is ::"+testID);
			rest.logoutCall();
			return testStatus;
	}
	
	public  List<String>  GetTestNames(List<String>testIDs) throws Exception 
	{
        
        XmlLibraries xL=new XmlLibraries();

		EntityUpdater rest = new EntityUpdater(almDomain, almProject);						
		rest.loginCall();
		
		
		Response instanceXML=rest.getTestNamesfromTestIDs(testIDs);
		String outXML=instanceXML.toString();	    
		String XML_wf= xL.RemoveNameSpaces(outXML);
	
	    List<String> node1 = xL.GetNodeListByXPath(XML_wf,"/Entities/Entity/Fields/Field[2]");
	    System.out.println("List of Test Cases::"+node1);
		rest.logoutCall();
		return node1;
   }
	
	
	public String lastFileModified(String dir) {
		File f = new File(dir); // current directory

		FileFilter directoryFilter = new FileFilter() {
			public boolean accept(File file) {
				return file.isDirectory();
			}
		};

		File[] files = f.listFiles(directoryFilter);
		File LatestFolder = null;
		long latestModified = 0;
		for (File file : files) {
			if (file.isDirectory() && latestModified < file.lastModified()) {
				latestModified = file.lastModified();
				LatestFolder = file;
				
			} 
			
		}
		return LatestFolder.getAbsolutePath();
	};
	
	
	
	public String readresult(String FileName) throws IOException
	{
		 BufferedReader br = new BufferedReader(new FileReader(FileName));
		    String strLine;
		    //Read File Line By Line
		  strLine = br.readLine();
		  br.close();  
		  return strLine;
		
	}
	
	
	public void createRUN() throws Exception 
	{
        
        XmlLibraries xL=new XmlLibraries();
        Attachments at=new Attachments();
       
        String lastupdateTime=xL.getsysTimeLog();
		EntityUpdater rest = new EntityUpdater(almDomain, almProject);						
		rest.loginCall();
		Response instanceXML=rest.createTestRUN();
		/*String outXML=instanceXML.toString();	    
	    String XML_wf= xL.RemoveNameSpaces(outXML);
	    //System.out.println(XML_wf);
	    String count=xL.nodeFromKey(XML_wf, "TotalResults=\"", "\"><Entity Type=\"run\">");
	    //System.out.println("No:of Runs are::"+count);
	    //will have alternate with XML node function if confusion with X Path
	    String RunsID=xL.GetValueByXPath(XML_wf,"/Entities/Entity["+count+"]/Fields/Field[4]/Value");
	    //System.out.println("RunsID is ::"+RunsID);
	    byte[] filedata;
	    if(attachmentPath.contains(".txt"))
	    {
	    filedata= at.convertTobytes(attachmentPath);
	    }
	    else
	    {
	     String attachmentPathTemp=attachmentPath;
		 filedata= at.convertTobytes(attachmentPathTemp);
	    }
		rest.CreateTCAttachment(RunsID, filedata, "Log_"+lastupdateTime+".zip");*/
		rest.logoutCall();
   }
	
	
	public static String  getPackageName(String testname) throws Exception 
	{
        
		final Package[] packages = Package.getPackages();
	    final String className = testname;
	    String pName = null;
	    
	    //ArrayList<String> pckgnames = new ArrayList<String>();
	    
	    String [] pckgnames = new String[] {"com.comcast.einstein.accountBanner","com.comcast.einstein.accountHealth","com.comcast.einstein.accountIndicators","com.comcast.einstein.continousIntegration","com.comcast.einstein.ctiIntegration","com.comcast.einstein.darkTheme","com.comcast.einstein.dbtest","com.comcast.einstein.desktopItgs","com.comcast.einstein.diagnosticsSection","com.comcast.einstein.externalWidget","com.comcast.einstein.infoManager","com.comcast.einstein.itg","com.comcast.einstein.km","com.comcast.einstein.rcScripts","com.comcast.einstein.searchWidget","com.comcast.einstein.smartConnect","com.comcast.einstein.STGValidations","com.comcast.einstein.ticketing","com.comcast.einstein.toolkits","com.comcast.einstein.usefulTools","com.comcast.einstein.xCalibur"};
	   // pckgnames.add("");
	    
	    
	    for (final String pack : pckgnames) {
	        System.out.println(pack);
	        	ArrayList<Class> classes = new ArrayList<Class>();
	        	classes = getClasses(pack);
	        		for (final Class c : classes) {
	        			for (Method method : c.getDeclaredMethods()) {	
	        			    String name = method.getName();
	        			    System.out.println(name);
	        			}
	        		}
	        pName = pack;
	       
	        
	    }
	    
	    return pName;
   }
	
	/**
	 * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
	 *
	 * @param packageName The base package
	 * @return The classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public static ArrayList<Class> getClasses(String packageName)
	        throws ClassNotFoundException, IOException {
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    assert classLoader != null;
	    String path = packageName.replace('.', '/');
	    Enumeration<URL> resources = classLoader.getResources(path);
	    List<File> dirs = new ArrayList<File>();
	    while (resources.hasMoreElements()) {
	        URL resource = resources.nextElement();
	        dirs.add(new File(resource.getFile()));
	    }
	    ArrayList<Class> classes = new ArrayList<Class>();
	    for (File directory : dirs) {
	        classes.addAll(findClasses(directory, packageName));
	    }
	    return classes;
		

	}

	/**
	 * Recursive method used to find all classes in a given directory and subdirs.
	 *
	 * @param directory   The base directory
	 * @param packageName The package name for classes found inside the base directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("rawtypes")
	public static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
	    List<Class> classes = new ArrayList<Class>();
	    if (!directory.exists()) {
	        return classes;
	    }
	    File[] files = directory.listFiles();
	    for (File file : files) {
	        if (file.isDirectory()) {
	            assert !file.getName().contains(".");
	            classes.addAll(findClasses(file, packageName + "." + file.getName()));
	        } else if (file.getName().endsWith(".class")) {
	            classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
	        }
	    }
	    return classes;
	}	 
	
	protected static void setEnv(Map<String, String> newenv)
	{
	  try
	    {
	        Class<?> processEnvironmentClass = Class.forName("java.lang.ProcessEnvironment");
	        Field theEnvironmentField = processEnvironmentClass.getDeclaredField("theEnvironment");
	        theEnvironmentField.setAccessible(true);
	        Map<String, String> env = (Map<String, String>) theEnvironmentField.get(null);
	        env.putAll(newenv);
	        Field theCaseInsensitiveEnvironmentField = processEnvironmentClass.getDeclaredField("theCaseInsensitiveEnvironment");
	        theCaseInsensitiveEnvironmentField.setAccessible(true);
	        Map<String, String> cienv = (Map<String, String>)     theCaseInsensitiveEnvironmentField.get(null);
	        cienv.putAll(newenv);
	    }
	    catch (NoSuchFieldException e)
	    {
	      try {
	        Class[] classes = Collections.class.getDeclaredClasses();
	        Map<String, String> env = System.getenv();
	        for(Class cl : classes) {
	            if("java.util.Collections$UnmodifiableMap".equals(cl.getName())) {
	                Field field = cl.getDeclaredField("m");
	                field.setAccessible(true);
	                Object obj = field.get(env);
	                Map<String, String> map = (Map<String, String>) obj;
	                map.clear();
	                map.putAll(newenv);
	            }
	        }
	      } catch (Exception e2) {
	        e2.printStackTrace();
	      }
	    } catch (Exception e1) {
	        e1.printStackTrace();
	    } 
	}
	
	public String getTestComponent(String testID) throws Exception {
		XmlLibraries xL=new XmlLibraries();
  		EntityUpdater rest = new EntityUpdater(almDomain, almProject);						
		rest.loginCall();
		Response instanceXML=rest.getTestComponent(testID);
		String outXML=instanceXML.toString();	    
	    String XML_wf= xL.RemoveNameSpaces(outXML);
	    //will have alternate with XML node function if confusion with X Path
	    String testComponent=xL.GetValueByXPath(XML_wf,"/Entities/Entity/Fields/Field[2]/Value");
	    //System.out.println("Test Instanceid is ::"+testID);
		rest.logoutCall();
		return testComponent;
	}

	
	
}