package com.comcast.commons;

import java.sql.Connection;
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

	public void iSelectValue(String locId) throws InterruptedException {
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
	
}
