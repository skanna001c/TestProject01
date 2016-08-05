package com.comcast.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

public class TestUtils {
	
	/**
	 * Get Current time stamp
	 * @return
	 */
	public static String TestName; // added by harsh on 8/4
	
	public static String getTimeStamp() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy_MMM_dd_HH_mm_ss");
		return format.format(date);
	}

	/**
	 * Get Current time stamp
	 * @return
	 */
	public static String getTime(String time) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("time");
		return format.format(date);
	}
	/**
	 * Get relative path of the framework
	 * @return
	 */
	public static String getRelativePath() {
		String relativePath = new File(System.getProperty("user.dir"))
				.getAbsolutePath();
		if (relativePath.endsWith("bin")) {
			relativePath = new File(System.getProperty("user.dir")).getParent();
		}
		return relativePath;
	}
	
	
	/**
	 * Sets Environment Variable at runtime
	 * @return
	 */
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
	
}
