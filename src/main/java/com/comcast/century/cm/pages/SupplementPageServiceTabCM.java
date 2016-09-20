package com.comcast.century.cm.pages;

import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.comcast.utils.SeleniumReport;
import com.comcast.century.data.SupplementInfo;
import com.comcast.reporting.Status;
import com.comcast.utils.ComcastTest.FrameworkContext;

public class SupplementPageServiceTabCM extends Page {

	
	public SupplementPageServiceTabCM(FrameworkContext context){
		super(context);
	}

	@Override
	protected boolean isValidPage() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void waitForPageLoad() {
		// TODO Auto-generated method stub
		
	}

	private boolean mstatus=true;	
	
	@FindBy(xpath = "//*[@id='mainFrame']")
	private WebElement frameMain;
	
	@FindBy(xpath = "//*[@id='leftFrame']")
	private WebElement frameLeft;
	
	@FindBy(xpath = "//*[@placeholder='---More Actions---']")
	private WebElement ddValueMoreActions;
	
	@FindBy(xpath = "//li[.='Tech Sup']")
	private WebElement ddValueTechSup;
	
	@FindBy(xpath = "//li[.='Cancel Sup']")
	private WebElement ddValueCancelSup;
	
	@FindBy(xpath = "//li[.='Admin Sup']")
	private WebElement ddValueAdminSup;
	
	@FindBy(css = "input[servicename='EPL']")
	private List<WebElement> chkBoxEPL;
	
	@FindBy(css = "input[servicename='EDI']")
	private List<WebElement> chkBoxEDI;
	
	@FindBy(css = "input[servicename='ENS']")
	private List<WebElement> chkBoxENS;
	
	@FindBy(css = "input[servicename='EVPL']")
	private List<WebElement> chkBoxEVPL;
	
	@FindBy(css = "input[servicename='Equipment Fee']")
	private List<WebElement> chkBoxEquipmentFee;
	
	@FindBy(css = "input[servicename='Trunk-PRI']")
	private WebElement chkBoxTrunkPRI;
	
	@FindBy(xpath = "//*[text()='Go ']/../following-sibling::*")
	private WebElement btnGo;
	
	@FindBy(xpath = "//span[.='OK']/following-sibling::*")
	private WebElement btnOK;
	
	@FindBy(css = "div[id*='messagebox']")
	private WebElement equipmentFeeErrormsg;
	
	
	public boolean placeSupplements(SupplementInfo supplementInfo) throws IndexOutOfBoundsException {
		mstatus = true;
		try {
			List<WebElement> elementToClick = null;
			WaitandSwitchToFrame(frameMain);
			waitForElement(ddValueMoreActions);
			switch (supplementInfo.supplementOn) {
			case "EDI":
			case "EDI-PRI":
				elementToClick = chkBoxEDI;
				if (supplementInfo.supplementOn.contains("PRI")) {
					chkBoxTrunkPRI.click();
				}
				break;
			case "EPL":
				elementToClick = chkBoxEPL;
				break;
			case "ENS":
				elementToClick = chkBoxENS;
				break;
			case "EVPL":
				elementToClick = chkBoxEVPL;
				break;
			default:
				System.out.println("Invalid service name");
			}

			waitForElement(elementToClick.get(0));

			for (int i = 0; i < elementToClick.size(); i++) {
				elementToClick.get(i).click();
			}

			// Select all equipment fees
			for (int i = 0; i < chkBoxEquipmentFee.size(); i++) {
				waitForElement(chkBoxEquipmentFee.get(i));
				chkBoxEquipmentFee.get(i).click();
			}

			waitForElement(ddValueMoreActions);
			iSendKeys(ddValueMoreActions, supplementInfo.supplementType);
			waitForElement(btnGo);
			btnGo.click();

			// EquipmentFee error message validations
			waitForElement(equipmentFeeErrormsg);
			if (equipmentFeeErrormsg.getText().contains(
					"This Action cannot be performed for Equipment Fee service. Please select only ME Service(s) to proceed")) {
				report.updateTestLog("Verify EquipmentFee Error message", "EquipmentFee Error message verified",
						Status.SCREENSHOT);
			} else
				report.reportFailEvent("Verify EquipmentFee Error message",
						"EquipmentFee Error message verification failed");

			waitForElement(btnOK);
			btnOK.click();

			// UnSelect all equipment fees
			for (int i = 0; i < chkBoxEquipmentFee.size(); i++) {
				waitForElement(chkBoxEquipmentFee.get(i));
				chkBoxEquipmentFee.get(i).click();
			}

			// Place supplements
			waitForElement(btnGo);
			btnGo.click();
			waitforPageLoadComplete();
			browser.switchTo().defaultContent();
		} catch (Exception e) {
			mstatus = false;
			e.printStackTrace();

		}
		return mstatus;
	}
		
}
