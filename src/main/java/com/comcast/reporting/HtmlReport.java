package com.comcast.reporting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;


/**
 * Class to encapsulate the HTML report generation functions
 */
public class HtmlReport implements ReportType
{
	private String testLogPath, resultSummaryPath;
	private ReportSettings reportSettings;
	private ReportTheme reportTheme;
	
	private Boolean isTestLogHeaderTableCreated = false;
	private Boolean isTestLogMainTableCreated = false;
	private Boolean isResultSummaryHeaderTableCreated = false;
	private Boolean isResultSummaryMainTableCreated = false;
	
	private String currentSection = "";
	private String currentSubSection = "";
	private int currentContentNumber = 1;
	
	
	/**
	 * Constructor to initialize the HTML report
	 * @param reportSettings The {@link ReportSettings} object
	 * @param reportTheme The {@link ReportTheme} object
	 */
	public HtmlReport(ReportSettings reportSettings, ReportTheme reportTheme)
	{
		this.reportSettings = reportSettings;
		this.reportTheme = reportTheme;
		
		testLogPath = reportSettings.getReportPath() + Util.getFileSeparator() + "HTML Results"
							+ Util.getFileSeparator() + reportSettings.getReportName() + ".html";
		
		resultSummaryPath = reportSettings.getReportPath() + Util.getFileSeparator() +
								"HTML Results" + Util.getFileSeparator() + "Summary" + ".html";
	}
	
	private String getThemeCss()
	{
		String themeCss =	"\t\t <style type='text/css'> \n" +
								"\t\t\t body { \n" +
									"\t\t\t\t background-color: " + reportTheme.getContentForeColor() +"; \n" +
									"\t\t\t\t font-family: Trebuchet MS, Helvetica, sans-serif; \n" +
									"\t\t\t\t text-align: center; \n" +
								"\t\t\t } \n\n" +
								
								"\t\t\t small { \n" +
									"\t\t\t\t font-size: 0.7em; \n" +
								"\t\t\t } \n\n" +
								
								"\t\t\t table { \n" +
									"\t\t\t\t border: 1px solid #4D7C7B; \n" +
									"\t\t\t\t border-collapse: collapse; \n" +
									"\t\t\t\t border-spacing: 0px; \n" +
									"\t\t\t\t width: 1000px; \n" +
									"\t\t\t\t margin-left: auto; \n" +
									"\t\t\t\t margin-right: auto; \n" +
								"\t\t\t } \n\n" +
								
								"\t\t\t tr.heading { \n" +
									"\t\t\t\t background-color: " + reportTheme.getHeadingBackColor() + "; \n" +
									"\t\t\t\t color: #000000; \n" +
									"\t\t\t\t font-size: 0.9em; \n" +
									"\t\t\t\t font-weight: bold; \n" +
								"\t\t\t } \n\n" +
								
								"\t\t\t tr.subheading { \n" +
									"\t\t\t\t background-color: " + reportTheme.getHeadingForeColor() + "; \n" +
									"\t\t\t\t color: #FFFFFF ; \n" +
									"\t\t\t\t font-weight: bold; \n" +
									"\t\t\t\t font-size: 0.9em; \n" +
									"\t\t\t\t text-align: center; \n" +
								"\t\t\t } \n\n" +
								
								"\t\t\t tr.section { \n" +
									"\t\t\t\t background-color: " + reportTheme.getSectionBackColor() + "; \n" +
									"\t\t\t\t color: " + reportTheme.getSectionForeColor() + "; \n" +
									"\t\t\t\t cursor: pointer; \n" +
									"\t\t\t\t font-weight: bold; \n" +
									"\t\t\t\t font-size: 0.9em; \n" +
									"\t\t\t\t text-align: justify; \n" +
								"\t\t\t } \n\n" +
								
								"\t\t\t tr.subsection { \n" +
									"\t\t\t\t cursor: pointer; \n" +
								"\t\t\t } \n\n" +
								
								"\t\t\t tr.content { \n" +
									"\t\t\t\t background-color: " + reportTheme.getContentBackColor() + "; \n" +
									"\t\t\t\t color: " + reportTheme.getContentForeColor() + "; \n" +
									"\t\t\t\t font-size: 0.8em; \n" +
									"\t\t\t\t display: table-row; \n" +
								"\t\t\t } \n\n" +
								
								"\t\t\t td, th { \n" +
									"\t\t\t\t padding: 4px; \n" +
									"\t\t\t\t text-align: inherit\\0/; \n" +
								"\t\t\t } \n\n" +
									
								"\t\t\t th { \n" +
								"\t\t\t\t font-family: 'Comic Sans MS', cursive, sans-serif; \n" +
								"\t\t\t } \n\n" +
								
								"\t\t\t td { \n" +
								"\t\t\t\t border: 1px solid #4D7C7B; \n" +
								"\t\t\t\t border-spacing: 1px; \n" +
								"\t\t\t } \n\n" +

								
								"\t\t\t td.justified { \n" +
									"\t\t\t\t text-align: center; \n" +
								"\t\t\t } \n\n" +
								
								"\t\t\t td.pass { \n" +
									"\t\t\t\t font-weight: bold; \n" +
									"\t\t\t\t color: green; \n" +
								"\t\t\t } \n\n" +
								
								"\t\t\t td.fail { \n" +
									"\t\t\t\t font-weight: bold; \n" +
									"\t\t\t\t color: red; \n" +
								"\t\t\t } \n\n" +
								
								"\t\t\t td.done, td.screenshot { \n" +
									"\t\t\t\t font-weight: bold; \n" +
									"\t\t\t\t color: black; \n" +
								"\t\t\t } \n\n" +
								
								"\t\t\t td.debug { \n" +
									"\t\t\t\t font-weight: bold; \n" +
									"\t\t\t\t color: blue; \n" +
								"\t\t\t } \n\n" +
								
								"\t\t\t td.warning { \n" +
									"\t\t\t\t font-weight: bold; \n" +
									"\t\t\t\t color: orange; \n" +
								"\t\t\t } \n" +
							 "\t\t </style> \n\n";
		
		return themeCss;
	}
	
	private String getJavascriptFunctions()
	{
		String javascriptFunctions =	"\t\t <script> \n" +
											"\t\t\t function toggleMenu(objID) { \n" +
												"\t\t\t\t if (!document.getElementById) return; \n" +
												"\t\t\t\t var ob = document.getElementById(objID).style; \n" +
												"\t\t\t\t if(ob.display === 'none') { \n" +
													"\t\t\t\t\t try { \n" +
														"\t\t\t\t\t\t ob.display='table-row-group'; \n" +
													"\t\t\t\t\t } catch(ex) { \n" +
														"\t\t\t\t\t\t ob.display='block'; \n" +
													"\t\t\t\t\t } \n" +
												"\t\t\t\t } \n" +
												"\t\t\t\t else { \n" +
													"\t\t\t\t\t ob.display='none'; \n" +
												"\t\t\t\t } \n" +
											"\t\t\t } \n" +
											
											"\t\t\t function toggleSubMenu(objId) { \n" +
												"\t\t\t\t for(i=1; i<10000; i++) { \n" +
													"\t\t\t\t\t var ob = document.getElementById(objId.concat(i)); \n" +
													"\t\t\t\t\t if(ob === null) { \n" +
														"\t\t\t\t\t\t break; \n" +
													"\t\t\t\t\t } \n" +
													"\t\t\t\t\t if(ob.style.display === 'none') { \n" +
														"\t\t\t\t\t\t try { \n" +
															"\t\t\t\t\t\t\t ob.style.display='table-row'; \n" +
														"\t\t\t\t\t\t } catch(ex) { \n" +
															"\t\t\t\t\t\t\t ob.style.display='block'; \n" +
														"\t\t\t\t\t\t } \n" +
													"\t\t\t\t\t } \n" +
													"\t\t\t\t\t else { \n" +
														"\t\t\t\t\t\t ob.style.display='none'; \n" +
													"\t\t\t\t\t } \n" +
												"\t\t\t\t } \n" +
											"\t\t\t } \n" +
										 "\t\t </script> \n";
		
		return javascriptFunctions;
	}
	
	
	/* TEST LOG FUNCTIONS*/
	
	@Override
	public void initializeTestLog()
	{
		File testLogFile = new File(testLogPath);
		try {
			testLogFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrameworkException("Error while creating HTML test log file");
		}
		
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(testLogFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new FrameworkException("Cannot find HTML test log file");
		}
		PrintStream printStream = new PrintStream(outputStream);
		
		String testLogHeadSection;
		testLogHeadSection = 	"<!DOCTYPE html> \n" +
								"<html> \n" +
								"\t <head> \n" +
									"\t\t <meta charset='UTF-8'> \n" +
									"\t\t <title>" +
										reportSettings.getProjectName() +
										" - " +	reportSettings.getReportName() +
										" Automation Execution Results" +
									"</title> \n\n" +
									getThemeCss() +
									getJavascriptFunctions() +
								"\t </head> \n";
		
        printStream.println(testLogHeadSection);
        printStream.close();
	}
	
	private void createTestLogHeaderTable()
	{
		BufferedWriter bufferedWriter;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(testLogPath, true));
			
		    String testLogHeaderTable =	"\t <body> \n" +
											"\t\t <table id='header'> \n" +
												"\t\t\t <colgroup> \n" +
													"\t\t\t\t <col style='width: 10%' /> \n" +
													"\t\t\t\t <col style='width: 30%' /> \n" +
													"\t\t\t\t <col style='width: 10%' /> \n" +
													"\t\t\t\t <col style='width: 50%' /> \n" +
												"\t\t\t </colgroup> \n\n" +
												 
												"\t\t\t <thead> \n";
		    bufferedWriter.write(testLogHeaderTable);
		    bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrameworkException("Error while adding header table to HTML test log");
		}
	}
	
	@Override
	public void addTestLogHeading(String heading)
	{
		if(!isTestLogHeaderTableCreated) {
			createTestLogHeaderTable();
			isTestLogHeaderTableCreated = true;
		}
		
		addTestLogHeadingTQM("TQM Century Automation Report");
		
		BufferedWriter bufferedWriter;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(testLogPath, true));
			
		    String testLogHeading =	"\t\t\t\t <tr class='heading'> \n" +
										"\t\t\t\t\t <th colspan='4' style='font-family:Palatino Linotype, Book Antiqua, Palatino, serif; font-size:1.4em;'> \n" + 
											"\t\t\t\t\t\t " + heading + " \n" +
										"\t\t\t\t\t </th> \n" +
									"\t\t\t\t </tr> \n";
		    bufferedWriter.write(testLogHeading);
		    bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrameworkException("Error while adding heading to HTML test log");
		}
	}
	
	public void addTestLogHeadingTQM(String heading)
	{
		if(!isTestLogHeaderTableCreated) {
			createTestLogHeaderTable();
			isTestLogHeaderTableCreated = true;
		}
		
		BufferedWriter bufferedWriter;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(testLogPath, true));
			
		    String testLogHeading =	"\t\t\t\t <tr class='heading'> \n" +
										"\t\t\t\t\t <th colspan='4' style='font-family:Palatino Linotype,Book Antiqua, Palatino, serif; font-size:2em;'> \n" + 
											"\t\t\t\t\t\t " + heading + " \n" +
										"\t\t\t\t\t </th> \n" +
									"\t\t\t\t </tr> \n";
		    bufferedWriter.write(testLogHeading);
		    bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrameworkException("Error while adding heading to HTML test log");
		}
	}
	
	@Override
	public void addTestLogSubHeading(String subHeading1, String subHeading2,
										String subHeading3, String subHeading4)
	{
		BufferedWriter bufferedWriter;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(testLogPath, true));
			
		    String testLogSubHeading =	"\t\t\t\t <tr class='subheading'> \n" +
											"\t\t\t\t\t <th>&nbsp;" + subHeading1.replace(" ", "&nbsp;") + "</th> \n" +
											"\t\t\t\t\t <th>&nbsp;" + subHeading2.replace(" ", "&nbsp;") + "</th> \n" +
											"\t\t\t\t\t <th>&nbsp;" + subHeading3.replace(" ", "&nbsp;") + "</th> \n" +
											"\t\t\t\t\t <th>&nbsp;" + subHeading4.replace(" ", "&nbsp;") + "</th> \n" +
										"\t\t\t\t </tr> \n";
		    bufferedWriter.write(testLogSubHeading);
		    bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrameworkException("Error while adding sub-heading to HTML test log");
		}
	}
	
	private void createTestLogMainTable()
	{
		BufferedWriter bufferedWriter;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(testLogPath, true));
			
		    String testLogMainTable =		"\t\t\t </thead> \n" +
										 "\t\t </table> \n\n" +
										 
										 "\t\t <table id='main'> \n" + 
											"\t\t\t <colgroup> \n" +
												"\t\t\t\t <col style='width: 8%' /> \n" +
												"\t\t\t\t <col style='width: 20%' /> \n" +
												"\t\t\t\t <col style='width: 54%' /> \n" +
												"\t\t\t\t <col style='width: 8%' /> \n" +
												"\t\t\t\t <col style='width: 10%' /> \n" +
											 "\t\t\t </colgroup> \n\n";
		    bufferedWriter.write(testLogMainTable);
		    bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrameworkException("Error while adding main table to HTML test log");
		}
	}
	
	@Override
	public void addTestLogTableHeadings()
	{
		if(!isTestLogMainTableCreated) {
			createTestLogMainTable();
			isTestLogMainTableCreated = true;
		}
		
		BufferedWriter bufferedWriter;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(testLogPath, true));
			
		    String testLogTableHeading =	"\t\t\t <thead> \n" +
												"\t\t\t\t <tr class='heading'> \n" + 
													"\t\t\t\t\t <th>Step#</th> \n" +
													"\t\t\t\t\t <th>Action</th> \n" +
													"\t\t\t\t\t <th>Actual Result</th> \n" +
													"\t\t\t\t\t <th>Status</th> \n" +
													"\t\t\t\t\t <th>Time</th> \n" +
												"\t\t\t\t </tr> \n" +
											"\t\t\t </thead> \n\n";
		    bufferedWriter.write(testLogTableHeading);
		    bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrameworkException("Error while adding main table headings to HTML test log");
		}
	}
	
	@Override
	public void addTestLogSection(String section)
	{
		String testLogSection = "";
		if (!currentSection.equals("")) {
			testLogSection = "\t\t\t </tbody>";
		}
		
		currentSection = section.replaceAll("[^a-zA-Z0-9]", "");
		
		BufferedWriter bufferedWriter;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(testLogPath, true));
			
		    testLogSection +=	"\t\t\t <tbody> \n" +
										"\t\t\t\t <tr class='section'> \n" +
											"\t\t\t\t\t <td colspan='5' onclick=\"toggleMenu('" + currentSection + "')\">+ " +
												section + "</td> \n" +
										"\t\t\t\t </tr> \n" +
									"\t\t\t </tbody> \n" +
									"\t\t\t <tbody id='" + currentSection + "' style='display:table-row-group'> \n";
		    bufferedWriter.write(testLogSection);
		    bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrameworkException("Error while adding section to HTML test log");
		}
	}
	
	@Override
	public void addTestLogSubSection(String subSection)
	{
		currentSubSection = subSection.replaceAll("[^a-zA-Z0-9]", "");
		currentContentNumber = 1;
		
		BufferedWriter bufferedWriter;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(testLogPath, true));
			
		    String testLogSubSection =	"\t\t\t\t <tr class='subheading subsection'> \n" +
											"\t\t\t\t\t <td colspan='5' onclick=\"toggleSubMenu('" + currentSection + currentSubSection + "')\">&nbsp;+ " +
												subSection + "</td> \n" +
										"\t\t\t\t </tr> \n";
		    bufferedWriter.write(testLogSubSection);
		    bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrameworkException("Error while adding sub-section to HTML test log");
		}
	}
	
	@Override
	public void updateTestLog(String stepNumber, String stepName, String stepDescription, Status stepStatus, String screenShotName)
	{
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(testLogPath, true));
			
			String BVTSSURL=System.getenv("SCREENSHOTURL");
			//System.out.println(BVTSSURL);
			String screenShotPathJenkins = "<a href='"+BVTSSURL;
			String screenShotPathNormal= "<a href='..\\Screenshots\\";
			String BVT=System.getenv("BVT");
			String sspath="<a href='..\\Screenshots\\"; 
			
			if (BVT==null)
			{	
				sspath = screenShotPathNormal;
			}
			
			else
			{
				
				if(BVT.equalsIgnoreCase("YES"))
				{
					
					sspath = screenShotPathJenkins;
				}
			}
			
			
			String testStepRow = "\t\t\t\t <tr class='content' id='" + currentSection + currentSubSection + currentContentNumber + "'> \n" +
									"\t\t\t\t\t <td>" + stepNumber + "</td> \n" +
									"\t\t\t\t\t <td class='justified'>" + stepName + "</td> \n" +
									"\t\t\t\t\t <td class='justified'>" + stepDescription + "</td> \n";
			currentContentNumber++;
			
			if(stepStatus.equals(Status.FAIL) || stepStatus.equals(Status.WARNING)) {
				if(reportSettings.takeScreenshotFailedStep) {
					 testStepRow += "\t\t\t\t\t <td class='" + stepStatus.toString().toLowerCase() + "'>" +
							 											   sspath + screenShotName + "'>" +
							 											   					   stepStatus +
							 											   					   	   "</a>" +
							 											   					   	"</td> \n";
				}
				else {
					testStepRow += 	"\t\t\t\t\t <td class='" + stepStatus.toString().toLowerCase() + "'>" +
													stepStatus +
									"</td> \n";
				}
			}
			else if(stepStatus.equals(Status.PASS)) {
				if(reportSettings.takeScreenshotPassedStep) {
					testStepRow += 	"\t\t\t\t\t <td class='" + stepStatus.toString().toLowerCase() + "'>" +
							sspath + screenShotName + "'>" +
														stepStatus +
													"</a>" +
									"</td> \n";
				}
				else {
					testStepRow += 	"\t\t\t\t\t <td class='" + stepStatus.toString().toLowerCase() + "'>" +
													stepStatus +
									"</td> \n";	
				}
			}
			else if(stepStatus.equals(Status.SCREENSHOT))
			{
				testStepRow += 	"\t\t\t\t\t <td class='" + stepStatus.toString().toLowerCase() + "'>" +
						sspath + screenShotName + "'>" +
													stepStatus +
												"</a>" +
								"</td> \n";
			}
	        else {
	       		 testStepRow += "\t\t\t\t\t <td class='" + stepStatus.toString().toLowerCase() + "'>" +
       		 									stepStatus +
       		 					"</td> \n";
	       	}
			
	       	testStepRow += 		"\t\t\t\t\t <td>" +
       											"<small>" + Util.getCurrentFormattedTime(reportSettings.getDateFormatString()) + "</small>" +
       							"</td> \n" +
       						"\t\t\t\t </tr> \n";
	       	
		    bufferedWriter.write(testStepRow);
		    bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrameworkException("Error while updating HTML test log");
		}
	}
	
	@Override
	public void addTestLogFooter(String executionTime, int nStepsPassed, int nStepsFailed)
	{
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(testLogPath, true));
			
			String BVTVPURL=System.getenv("VIEWPAGEURL");
			String BVT=System.getenv("BVT");
			String viewPagePath = null;
			
			
			if (BVT==null)
			{	
				viewPagePath = "clearView.html";
			}
			
			else
			{
				
				if(BVT.equalsIgnoreCase("YES"))
				{
					
					viewPagePath = BVTVPURL+"\\clearView.html";
				}
			}
			
			//VIEWPAGEURL
		    
			String testLogFooter =	"\t\t\t </tbody> \n" +
								"\t\t </table> \n\n" +
								
								"\t\t <table id='footer'> \n" +
									"\t\t\t <colgroup> \n" +
										"\t\t\t\t <col style='width: 25%' /> \n" +
										"\t\t\t\t <col style='width: 25%' /> \n" +
										"\t\t\t\t <col style='width: 25%' /> \n" +
										"\t\t\t\t <col style='width: 25%' /> \n" +
									"\t\t\t </colgroup> \n\n" +
									
									"\t\t\t <tfoot> \n" +
										"\t\t\t\t <tr class='heading'> \n" + 
											"\t\t\t\t\t <th colspan='4'>Execution Duration: " + executionTime + "</th> \n" + 
										"\t\t\t\t </tr> \n" +
										"\t\t\t\t <tr class='subheading'> \n" + 
											"\t\t\t\t\t <td class='pass'>&nbsp;Steps passed</td> \n" + 
											"\t\t\t\t\t <td class='pass'>&nbsp;: " + nStepsPassed + "</td> \n" +
											"\t\t\t\t\t <td class='fail'>&nbsp;Steps failed</td> \n" + 
											"\t\t\t\t\t <td class='fail'>&nbsp;: " + nStepsFailed + "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" + "<a href='"+viewPagePath+"'>ViewPageErrors</a>"+"</td> \n" +
										"\t\t\t\t </tr> \n" +
									"\t\t\t </tfoot> \n" +
								"\t\t </table> \n" +
							"\t </body> \n" +
						"</html>";
		    
		    bufferedWriter.write(testLogFooter);
		    bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrameworkException("Error while adding footer to HTML test log");
		}
	}
	
	
	/* RESULT SUMMARY FUNCTIONS*/
	
	@Override
	public void initializeResultSummary()
	{
		File resultSummaryFile = new File(resultSummaryPath);
		
		try {
			resultSummaryFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrameworkException("Error while creating HTML result summary file");
		}
		
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(resultSummaryFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new FrameworkException("Cannot find HTML result summary file");
		}
		PrintStream printStream = new PrintStream(outputStream);
		
		String resultSummaryHeader;
		resultSummaryHeader = 	"<!DOCTYPE html> \n" +
								"<html> \n" +
								"\t <head> \n" +
									"\t\t <meta charset='UTF-8'> \n" +
									"\t\t <title>" +
										reportSettings.getProjectName() +
										" - Automation Execution Results Summary" +
									"</title> \n\n" +
									getThemeCss() +
									getJavascriptFunctions() +
								"\t </head> \n";
		
		printStream.println (resultSummaryHeader);
        printStream.close();
	}
	
	private void createResultSummaryHeaderTable()
	{
		BufferedWriter bufferedWriter;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(resultSummaryPath, true));
			
		    String resultSummaryHeaderTable =	"\t <body> \n" +
													"\t\t <table id='header'> \n" +
														"\t\t\t <colgroup> \n" +
															"\t\t\t\t <col style='width: 20%' /> \n" +
															"\t\t\t\t <col style='width: 50%' /> \n" +
															"\t\t\t\t <col style='width: 15%' /> \n" +
															"\t\t\t\t <col style='width: 15%' /> \n" +
														"\t\t\t </colgroup> \n\n" +
														 
														"\t\t\t <thead> \n";
		    bufferedWriter.write(resultSummaryHeaderTable);
		    bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrameworkException("Error while adding header table to HTML result summary");
		}
	}
	
	@Override
	public void addResultSummaryHeading(String heading)
	{
		if(!isResultSummaryHeaderTableCreated) {
			createResultSummaryHeaderTable();
			isResultSummaryHeaderTableCreated = true;
		}
		
		BufferedWriter bufferedWriter;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(resultSummaryPath, true));
			
		    String resultSummaryHeading =	"\t\t\t\t <tr class='heading'> \n" +
												"\t\t\t\t\t <th colspan='4' style='font-family:Copperplate Gothic Bold; font-size:1.4em;'> \n" + 
													"\t\t\t\t\t\t " + heading + " \n" +
												"\t\t\t\t\t </th> \n" +
											"\t\t\t\t </tr> \n";
		    bufferedWriter.write(resultSummaryHeading);
		    bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrameworkException("Error while adding heading to HTML result summary");
		}
	}
	
	@Override
	public void addResultSummarySubHeading(String subHeading1, String subHeading2,
											String subHeading3, String subHeading4)
	{
		BufferedWriter bufferedWriter;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(resultSummaryPath, true));
			
		    String resultSummarySubHeading =	"\t\t\t\t <tr class='subheading'> \n" +
													"\t\t\t\t\t <th>&nbsp;" + subHeading1.replace(" ", "&nbsp;") + "</th> \n" +
													"\t\t\t\t\t <th>&nbsp;" + subHeading2.replace(" ", "&nbsp;") + "</th> \n" +
													"\t\t\t\t\t <th>&nbsp;" + subHeading3.replace(" ", "&nbsp;") + "</th> \n" +
													"\t\t\t\t\t <th>&nbsp;" + subHeading4.replace(" ", "&nbsp;") + "</th> \n" +
												"\t\t\t\t </tr> \n";
		    bufferedWriter.write(resultSummarySubHeading);
		    bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrameworkException("Error while adding sub-heading to HTML result summary");
		}
	}
	
	private void createResultSummaryMainTable()
	{
		BufferedWriter bufferedWriter;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(resultSummaryPath, true));
			
		    String resultSummaryMainTable =	"\t\t\t </thead> \n" +
										 "\t\t </table> \n\n" +
										 
										 "\t\t <table id='main'> \n" + 
											"\t\t\t <colgroup> \n" +
												"\t\t\t\t <col style='width: 10%' /> \n" +
												"\t\t\t\t <col style='width: 10%' /> \n" +
												"\t\t\t\t <col style='width: 50%' /> \n" +
												"\t\t\t\t <col style='width: 20%' /> \n" +
												"\t\t\t\t <col style='width: 10%' /> \n" +
											 "\t\t\t </colgroup> \n\n";
		    bufferedWriter.write(resultSummaryMainTable);
		    bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrameworkException("Error while adding main table to HTML result summary");
		}
	}
	
	@Override
	public void addResultSummaryTableHeadings()
	{
		if(!isResultSummaryMainTableCreated) {
			createResultSummaryMainTable();
			isResultSummaryMainTableCreated = true;
		}
		
		BufferedWriter bufferedWriter;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(resultSummaryPath, true));
			
		    String resultSummaryTableHeading =	"\t\t\t <thead> \n" +
												"\t\t\t\t <tr class='heading'> \n" + 
													"\t\t\t\t\t <th>Test_Scenario</th> \n" +
													"\t\t\t\t\t <th>Test_Case</th> \n" +
													"\t\t\t\t\t <th>Test_Description</th> \n" +
													"\t\t\t\t\t <th>Execution_Time</th> \n" +
													"\t\t\t\t\t <th>Test_Status</th> \n" +
												"\t\t\t\t </tr> \n" +
											"\t\t\t </thead> \n\n";
		    bufferedWriter.write(resultSummaryTableHeading);
		    bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrameworkException("Error while adding main table headings to HTML result summary");
		}
	}
	
	@Override
	public void updateResultSummary(String scenarioName, String testcaseName, String executionTime, String testStatus)
	{
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultSummaryPath, true));
			
			String BVTVPURL=System.getenv("VIEWPAGEURL");
			String BVT=System.getenv("BVT");
			String testcaseNamewithPath = null;
			
			
			if (BVT==null)
			{	
				testcaseNamewithPath = testcaseName;
			}
			
			else
			{
				
				if(BVT.equalsIgnoreCase("YES"))
				{
					
					testcaseNamewithPath = BVTVPURL+testcaseName;
				}
			}
			
		    
			String testcaseRow = "\t\t\t\t <tr class='content' > \n" +
									"\t\t\t\t\t <td class='justified'>" + scenarioName + "</td> \n" +
									"\t\t\t\t\t <td align='left'><a href='" + testcaseNamewithPath + ".html' " +
														">" + testcaseName + "</a>" +
												"</td> \n" +
									"\t\t\t\t\t <td>" + executionTime + "</td> \n";
		    
			if(testStatus.equalsIgnoreCase("passed")) {
				testcaseRow += 		"\t\t\t\t\t <td class='pass'>" + testStatus + "</td> \n" +
								"\t\t\t\t </tr> \n";
			}
			else {
				testcaseRow += 		"\t\t\t\t\t <td class='fail'>" + testStatus + "</td> \n" +
								"\t\t\t\t </tr> \n";
			}
			
		    bufferedWriter.write(testcaseRow);
		   	bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrameworkException("Error while updating HTML result summary");
		}
	}
	
	@Override
	public void addResultSummaryFooter(String totalExecutionTime,
										int nTestsPassed, int nTestsFailed)
	{
		try {
			BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(resultSummaryPath, true));
			
		    String resultSummaryFooter =	"\t\t\t </tbody> \n" +
										"\t\t </table> \n\n" +
										
										"\t\t <table id='footer'> \n" +
											"\t\t\t <colgroup> \n" +
												"\t\t\t\t <col style='width: 25%' /> \n" +
												"\t\t\t\t <col style='width: 25%' /> \n" +
												"\t\t\t\t <col style='width: 25%' /> \n" +
												"\t\t\t\t <col style='width: 25%' /> \n" +
											"\t\t\t </colgroup> \n\n" +
											
											"\t\t\t <tfoot> \n" +
												"\t\t\t\t <tr class='heading'> \n" + 
													"\t\t\t\t\t <th colspan='4'>Total Duration: " + totalExecutionTime + "</th> \n" + 
												"\t\t\t\t </tr> \n" +
												"\t\t\t\t <tr class='subheading'> \n" + 
													"\t\t\t\t\t <td class='pass'>&nbsp;Tests passed</td> \n" + 
													"\t\t\t\t\t <td class='pass'>&nbsp;: " + nTestsPassed + "</td> \n" +
													"\t\t\t\t\t <td class='fail'>&nbsp;Tests failed</td> \n" + 
													"\t\t\t\t\t <td class='fail'>&nbsp;: " + nTestsFailed + "</td> \n" +
												"\t\t\t\t </tr> \n" +
											"\t\t\t </tfoot> \n" +
										"\t\t </table> \n" +
									"\t </body> \n" +
								"</html>";
		    
		    bufferWriter.write(resultSummaryFooter);
			bufferWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrameworkException("Error while adding footer to HTML result summary");
		}
	}


}
