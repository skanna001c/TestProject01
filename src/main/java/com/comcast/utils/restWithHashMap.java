package com.comcast.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.jexl2.internal.AbstractExecutor.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.comcast.reporting.Report;
import com.comcast.utils.SeleniumReport;

/**
 * 
 * @author 534948 Suhas
 *
 */

public class restWithHashMap {

	public static void main(String[] args) throws IOException, ParseException {
		restWithHashMap rest = new restWithHashMap();
		HashMap hm = new HashMap();
		String GET_URL = "http://einstein.sys.comcast.net/einstein/rest/timeline/account/8499100770040524/eventtype/billpay_billpay/event/dotcom_2015-11-03_05:00:00_billpay?allFields=True";
		ArrayList<String> listOfKeys = new ArrayList<String>();
		String consumerId = "ODZCN0NGQjMtRkQwQi00NDUzLUFFMUYtRDJCQUMyNzcwNEUy";

		String USER_AGENT = "Mozilla/5.0";

		String POST_URL = "http://gseapp-po-1u.cable.comcast.com/CXP/Customer/getSession";

		String POST_PARAMS = "{\"accountNumber\":\"8499100840116007\",\"affiliate\":\"EINSTEIN_CARE\", \"channel\":\"WEB\",\"agent\":\"mike\"}";
		ArrayList<Object> listOfValues = new ArrayList<Object>();
		listOfKeys.add("amountPaid");
		listOfKeys.add("mobileDevice");
		listOfKeys.add("mobileDeviceType");
	
		int tries = 0;
		while (rest.responseCode == 0 && tries < 10) {
			hm = rest.sendGET(GET_URL, listOfKeys, consumerId);
			tries++;
		}
System.out.println("Map : "+hm);
		java.util.Set set = hm.entrySet();
		// Get an iterator
		Iterator i = set.iterator();
		while (i.hasNext()) {
			Map.Entry me = (Map.Entry) i.next();
			
			switch (me.getKey().toString()) {
			case "amountPaid":
				System.out.print("key hm : " + me.getKey() + " , ");
				System.out.println("value hm : " + me.getValue());
				break;

			case "mobileDevice":
				System.out.print("key hm : " + me.getKey() + " , ");
				System.out.println("value hm : " + me.getValue());
				break;
				
			case "mobileDeviceType":
				System.out.print("key hm : " + me.getKey() + " , ");
				System.out.println("value hm : " + me.getValue());
				break;	
				
			default:
				break;
			}

		}
		// listOfValues=
		// rest.sendPOST(listOfKeys,USER_AGENT,POST_URL,POST_PARAMS,consumerId);

	}

	public int responseCode = 0;

	public HashMap sendGET(String GET_URL, ArrayList<String> listOfKeys, String consumerId) throws IOException,
			ParseException {
		String USER_AGENT = "Mozilla/5.0";
		HashMap hm = new HashMap();
		// GET_URL = "http://www.google.com/search?q=mkyong";
		ArrayList<Object> jsonKeyValuePair = new ArrayList<Object>();

		URL obj = new URL(GET_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		if (consumerId != null) {
			con.setRequestProperty("x-consumer-id", consumerId);
		}
		con.setRequestProperty("Content-Type", "application/json");

		int tries = 0;
		try {
			while (responseCode != 200 && tries < 3) {
				responseCode = con.getResponseCode();
				tries++;

			}
		} catch (ConnectException ex) {
			// report.reportFailEvent("sendGET",
			// "Exception"+ex.getMessage().split("/n")[0]);
			System.out.println("Exception: " + ex.getMessage().split("/n")[0]);
		}
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
			JSONParser parser = new JSONParser();
			Object object = parser.parse(response.toString());
			JSONObject json = new JSONObject();

			json = (JSONObject) object;
			hm = printJsonObject(json, listOfKeys);
			
			// java.util.Set set = hm.entrySet();
			// // Get an iterator
			// Iterator i = set.iterator();
			// while(i.hasNext()) {
			// Map.Entry me = (Map.Entry)i.next();
			// System.out.print("key hm : "+me.getKey());
			// System.out.println("value hm : "+me.getValue());
			// }
		} else {
			System.out.println("GET request not worked");
		}

		return hm;
	}

	public HashMap sendPOST(ArrayList<String> listOfKeys, String USER_AGENT, String POST_URL, String POST_PARAMS,
			String consumerId) throws IOException, ParseException {
		ArrayList<Object> jsonKeyValuePair = new ArrayList<Object>();
		HashMap hm = new HashMap();
		URL obj = new URL(POST_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("x-consumer-id", consumerId);
		con.setRequestProperty("Content-Type", "application/json");

		// For POST only - START

		OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
		wr.write(POST_PARAMS.toString());
		wr.flush();
		// For POST only - END

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			java.util.Set set = hm.entrySet();
			// Get an iterator
			Iterator i = set.iterator();
			// Display elements

			// print result
			System.out.println(response.toString());
			JSONParser parser = new JSONParser();
			Object object = parser.parse(response.toString());
			JSONObject json = new JSONObject();

			json = (JSONObject) object;
			hm = printJsonObject(json, listOfKeys);
			System.out.println("-- : -- " + jsonKeyValuePair);
			// while(i.hasNext()) {
			// Map.Entry me = (Map.Entry)i.next();
			// System.out.print("key hm : "+me.getKey());
			// System.out.println("value hm : "+me.getValue());
			// }

		} else {
			System.out.println("POST request not worked");
		}

		return hm;
	}

	public static ArrayList<Object> keyValues = new ArrayList<Object>();

	public static HashMap printJsonObject(JSONObject jsonObj, ArrayList<String> listOfKeys) {
		HashMap hm = new HashMap();
		for (Object key : jsonObj.keySet()) {
			// based on you key types
			String keyStr = (String) key;

			Object keyvalue = jsonObj.get(keyStr);

			// Print key and value
			// System.out.println("key: "+ keyStr + " value: " + keyvalue);
			// System.out.println("account number :
			// "+jsonObj.get("accountNumber"));

			// for nested objects iteration if required
			if (keyvalue instanceof JSONObject)
				printJsonObject((JSONObject) keyvalue, listOfKeys);

			else if (keyvalue instanceof JSONArray) {
				JSONArray jsonArray = new JSONArray();
				jsonArray = (JSONArray) keyvalue;
				for (int i = 0; i < jsonArray.size(); i++) {

					if (jsonArray.get(i) instanceof JSONObject) {
						printJsonObject((JSONObject) jsonArray.get(i), listOfKeys);
					}

				}
			}

			for (int j = 0; j < listOfKeys.size(); j++) {
				if (keyStr.equals(listOfKeys.get(j))) {
					System.out.println("key: " + keyStr + " value: " + keyvalue);
					keyValues.add(keyvalue);
					hm.put(keyStr, keyvalue);
					
				}
			}
			
		}
		System.out.println("map in : "+hm);
		return hm;

	}

}
