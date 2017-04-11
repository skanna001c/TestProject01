package com.comcast.commons;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.DatabaseDAO;
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
	
	public String genCompanyName() {

		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("MM_dd_HH_mm_ss");
		String tmstmp = format.format(date);

		String addressVal;
		String companyName;

		addressVal = fwcontext.ctx.get("APT");
		if (addressVal != null) {
			log.info("For company name - APT=" + addressVal);
			companyName = "APT_" + addressVal + "_" + tmstmp;
		} else {
			log.info("For company name - APT - addressVal null - use default");
			companyName = getTestData("company") + "_" + tmstmp;
		}

		return (companyName);

	}
	
	public int updateOpportunityNameAndFlagInDB(String oppurtunityName,
			String oppurtunityFlag) {

		log.info("\n updateOpportunityNameAndFlagInDB \n");
		int retValue = 0;
		Statement stmt = null;

		log.info("updateOpportunityNameAndFlagInDB oppurtunityName ="
				+ oppurtunityName);
		log.info("updateOpportunityNameAndFlagInDB oppurtunityFlag ="
				+ oppurtunityFlag);

		String dbURL = fwcontext.ctx.get("DBURL");
		String dbUser = fwcontext.ctx.get("DBUSER");
		String dbPswd = fwcontext.ctx.get("DBPASSWORD");
		String tableName = fwcontext.ctx.get("addr_table_name");

		log.info("updateOpportunityNameAndFlagInDB dbURL..." + dbURL);
		log.info("updateOpportunityNameAndFlagInDB dbUser..." + dbUser);
		log.info("updateOpportunityNameAndFlagInDB dbPswd..." + dbPswd);
		log.info("updateOpportunityNameAndFlagInDB tableName..." + tableName);

		if ((dbURL == null) || (tableName == null)) {
			log.info("updateAddressUsedFlag Could NOT find dbURL Or Tablename.");
			return 0;
		}

		try {

			String sAddr1 = fwcontext.ctx.get("Address1");
			String sAddr2 = fwcontext.ctx.get("Address2");
			String sZip = fwcontext.ctx.get("zip");

			DatabaseDAO dao = new DatabaseDAO();
			Connection conn = null;

			if ((dbURL == null) || (dbUser == null) || (dbPswd == null)) {
				String eMsg = "Missing DB settings ... please check ....";
				throw new RuntimeException(eMsg);
			}

			conn = dao.getConnection(dbURL, dbUser, dbPswd, "MySql");
			log.info("\n updateOpportunityNameAndFlagInDB connection object is.. "
					+ conn + "..\n");

			String query = "update " + tableName + " set " + "oppr_used = '"
					+ oppurtunityFlag + "' , oppr_name='" + oppurtunityName
					+ "' where address1 = '" + sAddr1 + "' AND address2 = '"
					+ sAddr2 + "' AND zip5 = " + sZip;

			log.info("Query!!!!!!!!" + query);

			retValue = dao.updateMySqlDB(conn, query);
			if (retValue == 0) {
				log.info("updateOpportunityNameAndFlagInDB - Could NOT find row for specified address in DB.");
			}
		} catch (SQLException ex) {
			log.info("SQLException: " + ex.getMessage());
			log.info("SQLState: " + ex.getSQLState());
			log.info("VendorError: " + ex.getErrorCode());
		} catch (Exception e) {
			String eMsg = "updateOpportunityNameAndFlagInDB method failed --- "
					+ e.getMessage();
			log.info(eMsg);
			log.info("EndFail :\n\n\n");
			throw new RuntimeException(eMsg);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore

				stmt = null;
			}
		}

		return retValue;
	}
	
	@Override
	public void iSelectValue(String locId, String strVal) {
		if (pInteractive && (highlightUI > 0)) {
			WebElement we = this.verifyLocatorVisible(locId);
			if (we != null) {
				highlightGreenWE(we);
				sleep(1000);
			}
		}
		super.iSelectValue(locId, strVal);
	}

	public void iSelectValue(String locId) {
		if (pInteractive && (highlightUI > 0)) {
			WebElement we = this.verifyLocatorVisible(locId);
			if (we != null) {
				highlightGreenWE(we);
				sleep(1000);
			}
		}
		super.iSelectValue(locId, getTestData(locId));
	}
	

	@Override
	protected boolean isValidPage() {
		return false;
	}


	@Override
	protected void waitForPageLoad() {
	}
	
	public void getNewAddressFromDB() {

		log.info("ApplicationPage getNewAddressFromDB...");

		String tableName = null;

		getDBDetailsFromFramework();

		String dbURL = fwcontext.ctx.get("DBURL");
		String dbUser = fwcontext.ctx.get("DBUSER");
		String dbPswd = fwcontext.ctx.get("DBPASSWORD");

		Connection connObj = getMySqlConnection(dbURL, dbUser, dbPswd);

		if (connObj == null) {
			log.error("getNewAddressFromDB ..Could not get MySql Connection . Exiting get New Address from DB process. \n");
			return;
		}

		DatabaseDAO dao = new DatabaseDAO();
		tableName = getTableName();
		if (tableName == null) {
			log.error("getNewAddressFromDB ..Invalid Environment is selected in framework.properties file. Exiting get New Address from DB process. \n");
			return;
		} else if (tableName != null && tableName.contains("PROD")) {
			log.error("getNewAddressFromDB..Environment is selected as PROD. Cannot get new address from DB in this scenario.");
			return;
		} else {
			ResultSet rs = null;
			try {
				String query = "select address1, address2, city, state, zip5 from "
						+ tableName
						+ " where addr_used = 'N' and (oppr_name is null  or '')";

				log.debug(query);
				rs = dao.queryDBTable(connObj, query);
				if (rs != null && rs.next()) {
					log.info("getNewAddressFromDB ..rs = " + rs);
					setAddressParams(rs);
					String addrUsed = "Y";
					String updQuery = "update " + tableName + " set "
							+ "addr_used = '" + addrUsed
							+ "' where address1 = '" + rs.getString("address1")
							+ "' AND address2 = '" + rs.getString("address2")
							+ "' AND zip5 = " + rs.getString("zip5");
					log.info(updQuery);
					int updRetValue = dao.updateMySqlDB(connObj, updQuery);
					if (updRetValue == 0) {
						log.info("getNewAddressFromDB.. Could NOT update the address used flag for specified address in DB.");
					}

					fwcontext.ctx.put("DBADDR_STATUS", "FETCHED");

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("getNewAddressFromDB..Unable to run SQl query :"
						+ e.getMessage());

			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException sqlEx) {
					} // ignore

					rs = null;
				}
			}

		}

	}

	public void getDBDetailsFromFramework() {
		String dbURL = null;
		String dbUser = null;
		String dbPswd = null;

		dbURL = fwcontext.getAppEnvParamValue("DBURL");
		dbUser = fwcontext.getAppEnvParamValue("DBUSER");
		dbPswd = fwcontext.getAppEnvParamValue("DBPASSWORD");

		log.info("getDBDetailsFromFramework...dbURL = " + dbURL);
		log.info("getDBDetailsFromFramework...dbUser = " + dbUser);
		log.info("getDBDetailsFromFramework...dbPswd = " + dbPswd);

		if (dbURL == null || dbUser == null || dbPswd == null) {
			log.info("DBURL or DBUSER or DBPASSWORD not set - not using DB to load not used address for the test");
			return;
		}

		fwcontext.ctx.put("DBURL", dbURL);
		fwcontext.ctx.put("DBUSER", dbUser);
		fwcontext.ctx.put("DBPASSWORD", dbPswd);

	}

	public Connection getMySqlConnection(String dbUrl, String dbUser,
			String dbPaswd) {

		log.debug("\n getMySQLConnection dbURL is.. " + dbUrl + "..\n");

		if (dbUrl == null) {
			log.error("DBURL is null in framework.properties.Exiting..");
			return null;
		}

		DatabaseDAO dao = new DatabaseDAO();
		Connection conn = null;
		try {
			conn = dao.getConnection(dbUrl, dbUser, dbPaswd, "MySql");
			log.trace("\n getMySQLConnection connection object is.. " + conn
					+ "..\n");
		} catch (Exception e) {
			e.printStackTrace();
			String emsg = "Unable to connect to DB - " + dbUrl;
			log.error(emsg);
			return (null);
		}
		return (conn);

	}

	public String getTableName() {
		String tableName = null;
		String appEnvt = null;
		String envt = null;
		appEnvt = fwcontext.getParamValue("APP_ENV");
		envt = fwcontext.getParamValue("ENV");
		log.debug("\n getTableName APP_ENV is.. " + appEnvt + "..\n");
		log.debug("\n getTableName ENV is.. " + envt + "..\n");

		// We first look for APP_ENV key in framework properties file
		// If not present, then we look for ENV key. If both are not present
		// throw
		// RuntimeException
		if (appEnvt != null
				&& (appEnvt.trim().equalsIgnoreCase("QA2")
						|| appEnvt.trim().equalsIgnoreCase("STG") || appEnvt
						.trim().equalsIgnoreCase("PROD"))) {
			tableName = "bsdtestdata.frictionless_address_" + appEnvt;
		} else if (envt != null
				&& (envt.trim().equalsIgnoreCase("QA2")
						|| envt.trim().equalsIgnoreCase("STG") || envt.trim()
						.equalsIgnoreCase("PROD"))) {
			tableName = "bsdtestdata.frictionless_address_" + envt;
		} else {
			String eMsg = "Missing or incorrect environment key/value in framework properties file. please check ....";
			log.error(eMsg);
			throw new RuntimeException(eMsg);
		}
		fwcontext.ctx.put("addr_table_name", tableName);
		return tableName;
	}

	public void setAddressParams(ResultSet rs) {
		String addrType = null;
		String addrValue = null;
		log.info("setAddressParams...Set address values in dataTable ");
		try {
			if (rs.getString("address2") != null
					&& rs.getString("address2").length() > 0) {
				String[] part = rs.getString("address2").split(
						"(?<=\\D)(?=\\d)");
				log.debug(part[0]);
				log.debug(part[1]);

				if (part[0] != null && part[0].length() > 0)
					addrType = part[0].trim();
				if (part[1] != null && part[0].length() > 0)
					addrValue = part[1].trim();

			}

			dataTable.getDataTable().put("Address1", rs.getString("address1"));
			dataTable.getDataTable().put("addr2", rs.getString("address2"));
			dataTable.getDataTable().put("city", rs.getString("city"));
			dataTable.getDataTable().put("state", rs.getString("state"));
			dataTable.getDataTable().put("zip", rs.getString("zip5"));

			dataTable.getDataTable().put("AddressType", addrType);
			dataTable.getDataTable().put("AddressValue", addrValue);

			fwcontext.ctx.put("Address1", rs.getString("address1"));
			fwcontext.ctx.put("Address2", rs.getString("address2"));
			fwcontext.ctx.put("zip", rs.getString("zip5"));

			log.debug("setAddressParams..Address1 replaced with DB value .."
					+ rs.getString("address1"));
			log.debug("setAddressParams..addr2 replaced with DB value .."
					+ rs.getString("address2"));
		} catch (Exception e) {
			String eMsg = e.getMessage() + " - Unable to fill required fields";
			log.error(eMsg);
			throw new RuntimeException(eMsg);
		}

	}

	
}
