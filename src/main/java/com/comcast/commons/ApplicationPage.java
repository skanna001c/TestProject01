package com.comcast.commons;

import org.apache.log4j.Logger;

import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;

/**
 * @author ohegde001c
 *
 *Modify this class name to your application specific name
 *Eg: CenturyPage
 *
 */
public abstract class ApplicationPage extends Page {

	static Logger log = Logger.getLogger(ApplicationPage.class.getName());

	public ApplicationPage(FrameworkContext context) {
		super(context);
	}

	public ApplicationPage(FrameworkContext context, String className) {
		super(context, className);
	}
	
}
