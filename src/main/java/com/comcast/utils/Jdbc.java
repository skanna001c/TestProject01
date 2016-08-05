package com.comcast.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Jdbc {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static final String DB_URL = "jdbc:sqlserver://PACDCGSDEVD01.cable.comcast.com;MultipleActiveResultSets=true;";
	static final String DB_USER = "awd";
	static final String DB_PASS = "awd";

	public HashMap<String, List<String>> createConnection(String query) {
		Connection conn = null;
		//Statement stmt = null;
		ResultSet rs;
		HashMap<String, List<String>> table = new HashMap<String, List<String>>();
		List<String> rowList;
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			
			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			//Statement stmt = conn.createStatement();
			PreparedStatement stmt = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery();
			//int size=rs.getRow();
			//System.out.println("size "+size);
			ResultSetMetaData rsmd = rs.getMetaData();
			//System.out.println(rs.getObject("UserID"));	
			//System.out.println(table.get("IsEvenDate"));	
			//System.out.println(table.get("AcctContextID"));	
			//System.out.println(table.get("AcctNum"));
			for (int i = 1; i < rsmd.getColumnCount(); i++) {
				String name = rsmd.getColumnName(i);
				rowList = new ArrayList<String>();
				//System.out.println(name);
				rs.first();
				do
				 {
					rowList.add(rs.getString(name));
					//System.out.println(rs.getString(name));
				}while (rs.next());

				table.put(rsmd.getColumnName(i), rowList);
				rs.first();
			}
		
			conn.close();
			
			return table;
		} catch (SQLException se) {

			se.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
	
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return table;
	}
}