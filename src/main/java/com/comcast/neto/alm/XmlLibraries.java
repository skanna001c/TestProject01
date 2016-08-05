package com.comcast.neto.alm;

import org.testng.annotations.Test;
import java.io.IOException;
import java.io.StringReader;






import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;







import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.*;

public class XmlLibraries 
{
	public String RemoveNameSpaces(String sIpStr) 
	{
        String sRet = sIpStr;
        Pattern pP = Pattern.compile("<[a-zA-Z]*:");
        Matcher mM = pP.matcher(sRet);
        sRet = mM.replaceAll("<");

        pP = Pattern.compile("</[a-zA-Z]*:");
        mM = pP.matcher(sRet);
        sRet = mM.replaceAll("</");
        
        pP = Pattern.compile(" xmlns.*?(\"|\').*?(\"|\')");
        mM = pP.matcher(sRet);
        sRet = mM.replaceAll(" "); 

        pP = Pattern.compile(" xsi:");
        mM = pP.matcher(sRet);
        sRet = mM.replaceAll(" ");
       // bfcopcom
        pP = Pattern.compile("sik:");
        mM = pP.matcher(sRet);
        sRet = mM.replaceAll(" ");
        
        pP = Pattern.compile("bfcopcom:");
        mM = pP.matcher(sRet);
        sRet = mM.replaceAll(" ");
        
        pP = Pattern.compile("comt:");
        mM = pP.matcher(sRet);
        sRet = mM.replaceAll(" ");
        
               
        pP = Pattern.compile("SignatureRequired=\"FALSE\"");
        mM = pP.matcher(sRet);
        sRet = mM.replaceAll(" ");
        
        pP = Pattern.compile("type=\" SingleComponentProduct\"");
        mM = pP.matcher(sRet);
        sRet = mM.replaceAll(" ");
        
        return sRet;
   }

	public String getsysTime()
	{
		 Date todayDate = new Date();
	     TimeZone timeZone = TimeZone.getTimeZone("UTC");
	     DateFormat todayDateFormat = new SimpleDateFormat("YYYY-MM-dd H:m:s");
	     todayDateFormat.setTimeZone(timeZone);
	     String strTodayDate = todayDateFormat.format(todayDate);
	     //System.out.println("Todays Date as per EST is::"+strTodayDate); 
	     return strTodayDate;
	}
	
	public String getsysTimeLog()
	{
		 Date todayDate = new Date();
	     TimeZone timeZone = TimeZone.getTimeZone("UTC");
	     DateFormat todayDateFormat = new SimpleDateFormat("YYYY-MM-dd");
	     DateFormat hrs=new SimpleDateFormat("H");
	     DateFormat min=new SimpleDateFormat("m");
	     DateFormat sec=new SimpleDateFormat("s");
	     hrs.setTimeZone(timeZone);
	     min.setTimeZone(timeZone);
	     sec.setTimeZone(timeZone);
	     
	     
	     todayDateFormat.setTimeZone(timeZone);
	     String strTodayDate = todayDateFormat.format(todayDate);
	     String LogDate=strTodayDate+"_"+hrs.format(todayDate)+"hrs"+min.format(todayDate)+"min"+sec.format(todayDate)+"sec";
	    
	     //System.out.println("Todays Date as per EST is::"+LogDate); 
	     return LogDate;
	     
	  
	}
	
	@Test
	public String testDuration(String strtTime, String endTime) throws ParseException
	{
		
		 String durationTime = null;
	     DateFormat todayDateFormat = new SimpleDateFormat("YYYY-MM-dd H:m:s");
	     Date date1 = todayDateFormat.parse(strtTime);
	     Date date2 = todayDateFormat.parse(endTime);
	     long diff = date2.getTime() - date1.getTime();
	     
			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			System.out.print(diffDays + " days, ");
			System.out.print(diffHours + " hours, ");
			System.out.print(diffMinutes + " minutes, ");
			System.out.print(diffSeconds + " seconds.");
	     durationTime= String.valueOf(diffSeconds);
	    
	     return durationTime;
	}

	
 public String GetValueByXPath(String sXml, String sxpath) throws SAXException, IOException, XPathExpressionException
 {
	 String val=null;
	 try
	 {
		 //sXml.replaceAll(" xsi:", " ");
	     InputSource source = new InputSource(new StringReader(sXml));
	     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	     factory.setNamespaceAware(false);    
	     factory.setIgnoringElementContentWhitespace(false);
      
	     DocumentBuilder builder;        
	     Document doc = null;
	 
		 builder = factory.newDocumentBuilder();  
		 doc = builder.parse(source);
		 XPathFactory xpathFactory = XPathFactory.newInstance();            
	     XPath xpath = xpathFactory.newXPath(); 
	     XPathExpression expr = xpath.compile(sxpath);
	     Node oNode= (Node) expr.evaluate(doc,XPathConstants.NODE);
	     val=oNode.getTextContent();
	   
	     xpath=null; expr=null; oNode=null; doc=null; builder=null;
	     
	 }
	 catch (ParserConfigurationException | SAXException | IOException e) 
	 {           
		 e.printStackTrace();       
	 }
	 
	 return val;
	 
 }
 
 
 public List<String> GetNodeListByXPath(String sXml, String sxpath) throws SAXException, IOException, XPathExpressionException
 {
	 String val=null;
	 List<String> actualAttributes=new ArrayList<String>();
	 try
	 {
		 //sXml.replaceAll(" xsi:", " ");
	     InputSource source = new InputSource(new StringReader(sXml));
	     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	     factory.setNamespaceAware(false);    
	     factory.setIgnoringElementContentWhitespace(false);
      
	     DocumentBuilder builder;        
	     Document doc = null;
	 
		 builder = factory.newDocumentBuilder();  
		 doc = builder.parse(source);
		 XPathFactory xpathFactory = XPathFactory.newInstance();            
	     XPath xpath = xpathFactory.newXPath(); 
	     XPathExpression expr = xpath.compile(sxpath);
	     
	 	Object result1 = expr.evaluate(doc, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result1;
	     
	 	for(int m=0 ;m<nodes.getLength();m++){
			Node x= nodes.item(m);
			//		            System.out.println(  x.getTextContent() );
			NodeList nodes1  = x.getChildNodes();

			for (int j = 0; j < nodes1.getLength(); j++) {
				Node childNode = nodes1.item(j);
				if (childNode.getNodeType() == Node.ELEMENT_NODE) {
			    //System.out.println("Nodedetails","\nNodeName: "+childNode.getNodeName()+" NodeText: "+childNode.getTextContent());
				//if(childNode.getNodeName().equals("test-id")){
					actualAttributes.add(nodes1.item(j).getTextContent());
					
				//		}				
				}}
				
		
		}
	   
	     xpath=null; expr=null; nodes=null; doc=null; builder=null;
	     
	 }
	 catch (ParserConfigurationException | SAXException | IOException e) 
	 {           
		 e.printStackTrace();       
	 }
	 
	 return actualAttributes;
	 
 }
 
 public String nodeFromKey(String xmlStr,String str1,String str2) 
	{
		int startPosition=0;
		int endPosition=0;
		startPosition = xmlStr.indexOf(str1) + str1.length();
		if(startPosition==-1)
		{
			System.out.printf("No Value found for ::%s\n",str1);
         return(null);
      
		}
		
		else if(xmlStr.indexOf(str1)==-1)
		{
			System.out.printf("No Value found for ::%s\n",str1);
         return(null);
      
		}
		
		endPosition = xmlStr.indexOf(str2, startPosition); 
	    if(endPosition==-1)
		{
			System.out.printf("No Value found for ::%s\n",str2);
         return(null);
      
		}
		//System.out.printf("endPosition value is :: %d\n",endPosition);
		String resultval = xmlStr.substring(startPosition, endPosition); 
	    System.out.printf("Result value is :: %s\n",resultval);
	    return (resultval);
	}
}
