package com.comcast.century.common;

import java.awt.AWTException;
import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.comcast.century.cm.pages.AccountTabPageCM;
import com.comcast.century.cm.pages.AddressTabPageCM;
import com.comcast.century.cm.pages.ContactTabPageCM;
import com.comcast.century.cm.pages.CustomerTabPageCM;
import com.comcast.century.cm.pages.FeatureTabPageCM;
import com.comcast.century.cm.pages.OrderSummaryTabCMPage;
import com.comcast.century.cm.pages.ProcessTabPageCM;
import com.comcast.century.cm.pages.ServiceTabPageCM;
import com.comcast.century.cso.pages.ADITaskPage;
import com.comcast.century.cso.pages.ActivateServiceTaskPage;
import com.comcast.century.cso.pages.BULBATaskPage;
import com.comcast.century.cso.pages.BuildHouseAccountTaskPage;
import com.comcast.century.cso.pages.CAETaskPage;
import com.comcast.century.cso.pages.CCATTaskPage;
import com.comcast.century.cso.pages.CompleteCoaxBuildTaskPage;
import com.comcast.century.cso.pages.CompleteFiberPlantBuildTaskPage;
import com.comcast.century.cso.pages.CompleteSiteBuildCoaxTaskPage;
import com.comcast.century.cso.pages.CompleteSiteBuildTaskPage;
import com.comcast.century.cso.pages.CompleteWavelengthReservationTaskPage;
import com.comcast.century.cso.pages.ConductCoaxSurveyTaskPage;
import com.comcast.century.cso.pages.ConductFiberPlantSurveyTaskPage;
import com.comcast.century.cso.pages.ConductSiteSurveyCoaxTaskPage;
import com.comcast.century.cso.pages.ConductSiteSurveyTaskPage;
import com.comcast.century.cso.pages.ContactCustomerTaskPage;
import com.comcast.century.cso.pages.CreateOrderBillingPackageTaskPage;
import com.comcast.century.cso.pages.DaysOfConfigsTaskPage;
import com.comcast.century.cso.pages.EqFeeFlowTasks;
import com.comcast.century.cso.pages.EqFeeStartBillingTaskPage;
import com.comcast.century.cso.pages.GenerateCPEConfigsTaskPage;
import com.comcast.century.cso.pages.GenerateCoreConfigsTaskPage;
import com.comcast.century.cso.pages.InstallCPETaskPage;
import com.comcast.century.cso.pages.InstallCPE_CoaxTaskPage;
import com.comcast.century.cso.pages.LoadCoreConfigsTaskPage;
import com.comcast.century.cso.pages.NotifyCustomerofServiceInstallationTaskPage;
import com.comcast.century.cso.pages.ObtainCoaxPermitsTaskPage;
import com.comcast.century.cso.pages.ObtainFiberPlantPermitsTaskPage;
import com.comcast.century.cso.pages.ObtainSiteAgreementTaskPage;
import com.comcast.century.cso.pages.ServiceLevelTasks;
import com.comcast.century.cso.pages.SetCriticalDatesTaskPage;
import com.comcast.century.cso.pages.ShipCPETaskPage;
import com.comcast.century.cso.pages.SiteLevelTasks;
import com.comcast.century.cso.pages.StartBillingTaskPage;
import com.comcast.century.cso.pages.UpdateDesignTaskPage;
import com.comcast.century.cso.pages.WorkOrderTabPageCSO;
import com.comcast.century.data.AccountInfo;
import com.comcast.century.data.ContactInfo;
import com.comcast.century.data.CustomerInfo;
import com.comcast.century.data.OrderSummaryInfo;
import com.comcast.century.data.ProcessInfo;
import com.comcast.century.data.ServiceInfo;
import com.comcast.century.data.ServiceLevelTaskInfo;
import com.comcast.century.data.SiteInfo;
import com.comcast.century.data.SiteLevelTaskInfo;
import com.comcast.century.data.SupplementInfo;
import com.comcast.utils.ComcastTest;
import com.comcast.utils.DataDump;
import com.comcast.utils.IDataDump;
import com.comcast.utils.PerfTransaction;

import bsh.org.objectweb.asm.Label;

public class NewConnectTest extends ComcastTest {
	protected String testcaseName;
	protected CustomerInfo customerInfo;
	protected AccountInfo accountInfo;
	protected ContactInfo contactInfo;
	protected SiteInfo siteInfo;
	protected ServiceInfo serviceInfo;
	protected ProcessInfo processInfo;
	protected OrderSummaryInfo orderSummaryInfo;
	protected SiteLevelTaskInfo siteLevelTaskInfo;
	protected ServiceLevelTaskInfo serviceLevelTaskInfo;
	protected SupplementInfo supplementInfo;


	String SRID;
	String SurveyID;
	int retryCount = 0;
	boolean status=true;

	@BeforeClass
	public void beforeTest() {
		loadDataNewConnect();
		testcaseName = frameworkContext.getTestCaseName();
	}
	
	
	@Test(priority = 500)
	@PerfTransaction(name = "CreateCustomer")
	public void createCustomer() {
		String customerName;
		try {
			customerName = (new CustomerTabPageCM(frameworkContext)).createCustomer(customerInfo);
			getDataDump().setValue("CustomerName_RT", customerName);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	@Test(priority = 1000)
	@PerfTransaction(name = "CreateServiceAccount")
	public void createServiceAccount() throws InterruptedException {
		if ((new AccountTabPageCM(frameworkContext)).CreateServiceAccount(accountInfo)) {
			if ((new AccountTabPageCM(frameworkContext)).clickOnAddContact()) {
				if ((new ContactTabPageCM(frameworkContext)).CreateAccountPrimaryContact(contactInfo)) {
					
					if ((new ContactTabPageCM(frameworkContext)).ClickOnBackBtn()) {
					} 
				} else
					Assert.fail("Create account primary contact failed");
			} else
				Assert.fail("Click on add contact failed");
		} else
			Assert.fail("Create service account failed");
	}
	
	
	@Test(priority = 1500)
	@PerfTransaction(name = "CreateBillingAccount")
	public void createBillingAccount() throws InterruptedException {
		if ((new AccountTabPageCM(frameworkContext)).CreateBillingAccount(accountInfo)) {
			if ((new AccountTabPageCM(frameworkContext)).clickOnAddContact()) {
				if ((new ContactTabPageCM(frameworkContext)).CreateBillingContact(contactInfo)) {
					if ((new ContactTabPageCM(frameworkContext)).ClickOnBackBtn()) {
					} 
				} else
					Assert.fail("Create billing contact failed");
			} else
				Assert.fail("Click on add contact failed");
		} else
			Assert.fail("Create billing account failed");
	}

	
	@Test(priority = 2000)
	public void createAddress() throws InterruptedException {
		
		String Site;
	    (new AddressTabPageCM(frameworkContext)).ClickAddressTab();
		for (int i = 1; i <= Integer.parseInt(siteInfo.noOfSites); i++) {
			if (i > 1) {
				new AddressTabPageCM(frameworkContext).CreateNewAddress();
			}
			Site = (new AddressTabPageCM(frameworkContext)).EnterSiteDetailsValid(siteInfo);
			getDataDump().setValue("SITE" + i + "_RT", Site);
			new ContactTabPageCM(frameworkContext).CreateSiteTechnicalContact(contactInfo);
			if(i!=Integer.parseInt(siteInfo.noOfSites)){
				new ContactTabPageCM(frameworkContext).ClickOnBackBtn();
			}
		}
	}
	   
	
	@Test(priority = 3500)
	@PerfTransaction(name = "SelectService")
	public void selectService() throws InterruptedException {
		
		if((new ServiceTabPageCM(frameworkContext)).selectServices(serviceInfo)){
		}
	
	}
	
	@Test(priority = 4000)
	@PerfTransaction(name = "ConfigureService")
	public void configureService() throws InterruptedException {
		if (getDataDump().getValue("configureService_status").equalsIgnoreCase("FAIL")) {
			selectService();
		}
		if((new FeatureTabPageCM(frameworkContext)).configureServices(serviceInfo)){
		}
	
	}
	
	
	@Test(priority = 4500)
	@PerfTransaction(name = "ProcessService")
	public void processService() throws InterruptedException {
		if (getDataDump().getValue("processService_status").equalsIgnoreCase("FAIL")) {
			selectService();
			configureService();
		}
		SRID = (new ProcessTabPageCM(frameworkContext)).ProcessConfiguration(processInfo);
		getDataDump().setValue("SRID_RT", SRID);
		IDataDump dataDump=(new ProcessTabPageCM(frameworkContext)).processServices(serviceInfo,processInfo,getDataDump());
		if( dataDump!= null){
			setDataDump(dataDump);
		}else Assert.fail("Process Services Failed");
	
	}
	
	
	@Test(priority = 5000)
	@PerfTransaction(name = "SubmitOrder")
	public void submitOrder() throws InterruptedException {
		if (getDataDump().getValue("submitOrder_status").equalsIgnoreCase("FAIL")) {
			selectService();
			configureService();
			processService();
		}
		if((new OrderSummaryTabCMPage(frameworkContext)).submitOrder(orderSummaryInfo,accountInfo.eRate)){
			startCSO();
		}else Assert.fail("Submit Order Failed");
		
		
	}
	
	
	// Fiber site flow tasks
	@Test(priority = 5500)
	public void Conduct_Site_Survey() throws InterruptedException {
		for(int i=1; i <= Integer.parseInt(getDataDump().getValue("Fibercount_RT")); i++){
			SearchOrderndLaunchFiberSiteFlow(getDataDump().getValue("FiberSite" + i + "_RT"));		
			(new SiteLevelTasks(frameworkContext)).ConductSiteSurvey();
			(new ConductSiteSurveyTaskPage(frameworkContext)).ConductSiteSurvey(siteLevelTaskInfo);
		}
	}
	
	@Test(priority = 6000)
	public void Obtain_Site_Agreement() throws InterruptedException {
		for(int i=1; i <= Integer.parseInt(getDataDump().getValue("Fibercount_RT")); i++){
			SearchOrderndLaunchFiberSiteFlow(getDataDump().getValue("FiberSite" + i + "_RT"));
			(new SiteLevelTasks(frameworkContext)).ObtainSiteAgreement();
			(new ObtainSiteAgreementTaskPage(frameworkContext)).ObtainSiteAgreement(siteLevelTaskInfo);
		}
	}
	
	
	@Test(priority = 6500)
	public void Conduct_Fiber_Plant_Survey() throws InterruptedException, AWTException {
		for(int i=1; i <= Integer.parseInt(getDataDump().getValue("Fibercount_RT")); i++){
			SearchOrderndLaunchFiberSiteFlow(getDataDump().getValue("FiberSite" + i + "_RT"));
			(new SiteLevelTasks(frameworkContext)).ConductFiberPlantSurvey();
			(new ConductFiberPlantSurveyTaskPage(frameworkContext)).ConductFiberPlantSurvey(siteLevelTaskInfo);
		}
	}
	
	@Test(priority = 7000)
	public void Build_House_Account() throws InterruptedException, AWTException {
		for(int i=1; i <= Integer.parseInt(getDataDump().getValue("Fibercount_RT")); i++){
			SearchOrderndLaunchFiberSiteFlow(getDataDump().getValue("FiberSite" + i + "_RT"));
			if ((new SiteLevelTasks(frameworkContext)).BuildHouseAccount()) {
				(new BuildHouseAccountTaskPage(frameworkContext)).BuildHouseAccount(siteLevelTaskInfo);
			}
		}
	}

	
	@Test(priority = 7500)
	public void Complete_Wavelength_Reservation() throws InterruptedException, AWTException {
		for(int i=1; i <= Integer.parseInt(getDataDump().getValue("Fibercount_RT")); i++){
			SearchOrderndLaunchFiberSiteFlow(getDataDump().getValue("FiberSite" + i + "_RT"));
			(new SiteLevelTasks(frameworkContext)).CompleteWavelengthReservation();
			(new CompleteWavelengthReservationTaskPage(frameworkContext)).CompleteWavelengthReservation(siteLevelTaskInfo);
		}

	}

	
	@Test(priority = 8000)
	public void Complete_Site_Build() throws InterruptedException, AWTException {
		for(int i=1; i <= Integer.parseInt(getDataDump().getValue("Fibercount_RT")); i++){
			SearchOrderndLaunchFiberSiteFlow(getDataDump().getValue("FiberSite" + i + "_RT"));
			(new SiteLevelTasks(frameworkContext)).CompleteSiteBuild();
			(new CompleteSiteBuildTaskPage(frameworkContext)).ClickCompleteButton();
			(new CompleteSiteBuildTaskPage(frameworkContext)).closePopup();
		}

	}
	
	
	@Test(priority = 8500)
	public void Obtain_Fiber_Plant_Permit() throws InterruptedException, AWTException {
		for(int i=1; i <= Integer.parseInt(getDataDump().getValue("Fibercount_RT")); i++){
			SearchOrderndLaunchFiberSiteFlow(getDataDump().getValue("FiberSite" + i + "_RT"));
			(new SiteLevelTasks(frameworkContext)).ObtainFiberPlantPermits();
			(new ObtainFiberPlantPermitsTaskPage(frameworkContext)).ObtainFiberPlantPermits();
		}

	}
	
	@Test(priority = 9000)
	public void Complete_Fiber_Plant_Build() throws InterruptedException, AWTException {
		for(int i=1; i <= Integer.parseInt(getDataDump().getValue("Fibercount_RT")); i++){
			SearchOrderndLaunchFiberSiteFlow(getDataDump().getValue("FiberSite" + i + "_RT"));
			(new SiteLevelTasks(frameworkContext)).CompleteFiberPlantBuild();
			(new CompleteFiberPlantBuildTaskPage(frameworkContext)).ClickCompleteButton();
			if (new CompleteFiberPlantBuildTaskPage(frameworkContext).closePopup()) {
				UpdateRespectiveFiberSiteFlows();
			}
			
		} if(testcaseName.equalsIgnoreCase("Tech_Sup_ME-EDI-Tech_Sup-Add_BGP")){
			startCM();
		}
	}
	
	
	
	
	// Service Request flow tasks
	@Test(priority = 13000)
	public void Contact_Customer() throws InterruptedException, AWTException {
		SearchOrderndLaunchServiceRequest();
		(new ServiceLevelTasks(frameworkContext)).ContactCustomer();
		(new ContactCustomerTaskPage(frameworkContext)).ContactCustomer();

	}

	@Test(priority = 13500)
	public void Update_Design() throws InterruptedException, AWTException {
		SearchOrderndLaunchServiceRequest();
		(new ServiceLevelTasks(frameworkContext)).UpdateDesign();
		(new UpdateDesignTaskPage(frameworkContext)).UpdateDesign();		
	}
	
	// Metro E flow tasks
	@Test(priority = 14000)
	public void Build_Update_Local_Biller_Account() throws InterruptedException {
		for(int i=0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++){
			SearchOrderndLaunchServiceFlow(i);
			int j=0;
			while(browser.findElements(By.xpath("//*[text()='Build Update Local Biller Account' and contains(@onclick, 'INPROGRESS')]")).size() != 0)
				{
				(new ServiceLevelTasks(frameworkContext)).BULBA(j);
				(new BULBATaskPage(frameworkContext)).BULBA(serviceLevelTaskInfo);
				j++;
			}
		} 
	}
	

	@Test(priority = 14500)
	public void Ship_CPE() throws InterruptedException {
		for (int i = 0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++) {
			SearchOrderndLaunchServiceFlow(i);
			(new ServiceLevelTasks(frameworkContext)).ShipCPE();
			(new ShipCPETaskPage(frameworkContext)).ShipCPE(serviceLevelTaskInfo);
		}
	}

	@Test(priority = 15000)
	public void Create_Account_and_Equipment() throws Exception {
		for (int i = 0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++) {
			SearchOrderndLaunchServiceFlow(i);
			(new ServiceLevelTasks(frameworkContext)).CAE();
			(new CAETaskPage(frameworkContext)).CAETask(serviceInfo);
		}
	}
		

	@Test(priority = 15500)
	public void Assign_Design_Info() throws InterruptedException {
		for (int i = 0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++) {
			SearchOrderndLaunchServiceFlow(i);
			(new ServiceLevelTasks(frameworkContext)).ADI();
			(new ADITaskPage(frameworkContext)).ADITask();
		}
		if(testcaseName.matches("Tech_Supp_ Add_BGP_to_EDI-PRI_Service|Tech_Supp_Upgrade_EVC_for_EDI_Erate_Service|Tech_Sup_Add_Trunk-PRI_to_In-Flight_Metro-E_order")){
			startCM();
		}
	}	


	@Test(priority = 16000)
	public void Generate_Core_Config() throws InterruptedException {
		for (int i = 0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++) {
			SearchOrderndLaunchServiceFlow(i);
			(new ServiceLevelTasks(frameworkContext)).GenerateCoreConfigs();
			(new GenerateCoreConfigsTaskPage(frameworkContext)).ClickCompleteButton();
		}

	}

	@Test(priority = 16500)
	public void Generate_CPE_Config() throws InterruptedException {
		for (int i = 0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++) {
			SearchOrderndLaunchServiceFlow(i);
			(new ServiceLevelTasks(frameworkContext)).GenerateCPEConfigs();
			(new GenerateCPEConfigsTaskPage(frameworkContext)).ClickCompleteButton();
		}
	}

	@Test(priority = 17000)
	public void Load_Core_Config() throws InterruptedException {
		for (int i = 0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++) {
			SearchOrderndLaunchServiceFlow(i);
			(new ServiceLevelTasks(frameworkContext)).LoadCoreConfigs();
			(new LoadCoreConfigsTaskPage(frameworkContext)).ClickCompleteButton();
		}
	}

	@Test(priority = 17500)
	public void Install_CPE() throws InterruptedException {
		for(int i=0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++){
			SearchOrderndLaunchServiceFlow(i);
			int j=0;
			while(browser.findElements(By.xpath("//*[text()='Install CPE' and contains(@onclick, 'INPROGRESS')]")).size() != 0)
			{
				(new ServiceLevelTasks(frameworkContext)).InstallCPE(j);
				(new InstallCPETaskPage(frameworkContext)).InstallCPE(serviceInfo);
				j++;
			}
		}
		
	}

	@Test(priority = 18000)
	@PerfTransaction(name = "Install_CPE_Coax")
	public void Install_CPE_Coax() throws InterruptedException, AWTException {
		for(int i=0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++){
			SearchOrderndLaunchServiceFlow(i);
			int j=0;
			while(browser.findElements(By.xpath("//*[text()='Install CPE (Coax)' and contains(@onclick, 'INPROGRESS')]")).size() != 0)
			{
				(new ServiceLevelTasks(frameworkContext)).InstallCPECoax(j);
				(new InstallCPE_CoaxTaskPage(frameworkContext)).InstallCPECoax();
			}
		}
	}	

	@Test(priority = 18500)
	public void Set_Critical_Dates() throws InterruptedException {
		for (int i = 0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++) {
			SearchOrderndLaunchServiceFlow(i);
			(new ServiceLevelTasks(frameworkContext)).SetCriticalDates();
			(new SetCriticalDatesTaskPage(frameworkContext)).SetCriticalDates();
		}
	}

	@Test(priority = 19000)
	public void Day_of_Configs() throws InterruptedException {
		for (int i = 0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++) {
			SearchOrderndLaunchServiceFlow(i);
			(new ServiceLevelTasks(frameworkContext)).DayofConfigs();
			(new DaysOfConfigsTaskPage(frameworkContext)).ClickCompleteButton();
		}
	}

	@Test(priority = 19500)
	public void Activate_Service() throws InterruptedException {
		for (int i = 0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++) {
			SearchOrderndLaunchServiceFlow(i);
			(new ServiceLevelTasks(frameworkContext)).ActivateService();
			(new ActivateServiceTaskPage(frameworkContext)).activateService(serviceInfo, serviceLevelTaskInfo);
		}
	}


	@Test(priority = 20000)
	public void Notify_Customer_of_Service_Installation() throws InterruptedException {
		for(int i=0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++){
			 SearchOrderndLaunchServiceFlow(i);
			(new ServiceLevelTasks(frameworkContext)).NotifyCustomerofServiceInstallation();
			(new NotifyCustomerofServiceInstallationTaskPage(frameworkContext)).NotifyCustomerofServiceInstallation();
		}
	}

	
	@Test(priority = 20500)
	public void Complete_Customer_Acceptance_Testing(Method method) throws InterruptedException {
		for(int i=0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++){
			SearchOrderndLaunchServiceFlow(i);
			(new ServiceLevelTasks(frameworkContext)).CCAT();
			(new CCATTaskPage(frameworkContext)).ClickCompleteButton();
		}
	}

	@Test(priority = 21000)	
	public void Create_Order_Billing_Package() throws AWTException, InterruptedException {
		for(int i=0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++){
			SearchOrderndLaunchServiceFlow(i);
			(new ServiceLevelTasks(frameworkContext)).CreateOrderBillingPackage();
			(new CreateOrderBillingPackageTaskPage(frameworkContext)).CreateOrderBillingPackage();
		}
	}


	@Test(priority = 21500)
	public void Start_Billing() throws InterruptedException {
		for(int i=0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++){
			SearchOrderndLaunchServiceFlow(i);
			(new ServiceLevelTasks(frameworkContext)).StartBilling();
			(new StartBillingTaskPage(frameworkContext)).verifyNotes();
			(new ServiceLevelTasks(frameworkContext)).ClickBackButton();
		}
	}

	
	@Test(priority = 22000)
	public void EqFeeStartBilling() throws InterruptedException {
		for(int i=0; i < Integer.parseInt(serviceInfo.equipmentFee); i++){
			SearchOrderndLauncheEquipmentFeeFlow(i);			
			(new EqFeeFlowTasks(frameworkContext)).EqFeeStartBilling();
			(new EqFeeStartBillingTaskPage(frameworkContext)).EqFeeStartBilling();			
		}
	}	
	
	@AfterMethod
	public void afterMethod() {
		
	}

	@AfterTest
	public void afterTest() {
	}

	public void loadDataNewConnect() {
		accountInfo = AccountInfo.loadFromDatatable(dataTable);
		siteInfo = SiteInfo.loadFromDatatable(dataTable);
		customerInfo = CustomerInfo.loadFromDatatable(dataTable);
		contactInfo = ContactInfo.loadFromDatatable(dataTable);
		serviceInfo = ServiceInfo.loadFromDatatable(dataTable);
		processInfo = ProcessInfo.loadFromDatatable(dataTable);
		orderSummaryInfo = OrderSummaryInfo.loadFromDatatable(dataTable);
		siteLevelTaskInfo = SiteLevelTaskInfo.loadFromDatatable(dataTable);
		serviceLevelTaskInfo = ServiceLevelTaskInfo.loadFromDatatable(dataTable);
		supplementInfo = SupplementInfo.loadFromDatatable(dataTable);
	}
	
	
	public void SearchOrderndLaunchFiberSiteFlow(String site) {
		    	retryCount = 1;
		    	do{
		    		(new WorkOrderTabPageCSO(frameworkContext)).SearchForOrderInSO(getDataDump().getValue("SRID_RT"), retryCount);
		    		status = (new WorkOrderTabPageCSO(frameworkContext)).ClickFiberSiteFlow(site);
		    		if(!status)
		    		{
		    			retryCount++;
		    		}
		    	}while(!status && retryCount <= 5);           
				
	}
	
	public void SearchOrderndLaunchCoaxSiteFlow(String site) {		
				retryCount = 1;
				do{
					(new WorkOrderTabPageCSO(frameworkContext)).SearchForOrderInSO(getDataDump().getValue("SRID_RT"), retryCount);
					status = (new WorkOrderTabPageCSO(frameworkContext)).ClickCoaxSiteFlow(site);
					if(!status)
		    		{
		    			retryCount++;
		    		}
				}while(!status && retryCount <= 5);
			
		}
	
	
	private void UpdateRespectiveFiberSiteFlows() {
		for (int i = 1; i <= Integer.parseInt(settings.getValue("MAXNOOFFIBERSITES")); i++) {
			if (!(getDataDump().getValue("FiberSiteFlow" + i).equalsIgnoreCase("pass"))) {
				getDataDump().setValue("FiberSiteFlow" + i, "PASS");
				break;
			}
		}
	}

	
	
	public void SearchOrderndLaunchServiceFlow(int i) throws InterruptedException {
    	retryCount = 1;
    	do{
    		(new WorkOrderTabPageCSO(frameworkContext)).SearchForOrderInSO(getDataDump().getValue("SRID_RT"), retryCount);
    		status = (new WorkOrderTabPageCSO(frameworkContext)).ClickServiceFlow(serviceInfo,i);
    		if(!status)
    		{
    			retryCount++;
    		}
    	}while(!status && retryCount <= 5);
	
	}
	
	
	public void SearchOrderndLaunchPRIFlow() throws InterruptedException {
    	retryCount = 1;
		do{
			(new WorkOrderTabPageCSO(frameworkContext)).SearchForOrderInSO(getDataDump().getValue("SRID_RT"), retryCount);
			status =(new WorkOrderTabPageCSO(frameworkContext)).ClickTrunkPRIFlow();
			if(!status)
			{ 
				retryCount++;
			}
		}while(!status && retryCount <= 5);
	}
	
	
	public void SearchOrderndLaunchServiceRequest() {
    	retryCount = 1;
    	do{
    		(new WorkOrderTabPageCSO(frameworkContext)).SearchForOrderInSO(getDataDump().getValue("SRID_RT"),retryCount);
    		status = (new WorkOrderTabPageCSO(frameworkContext)).ClickFirstSiteFlow();
    		if(!status)
    		{
    			retryCount++;
    		}
    	}while(!status && retryCount <= 5);	
	}
	
	
	public void SearchOrderndLauncheEquipmentFeeFlow(int i) throws InterruptedException {
    	retryCount = 1;
    	do{
    		(new WorkOrderTabPageCSO(frameworkContext)).SearchForOrderInSO(getDataDump().getValue("SRID_RT"), retryCount);
    		status = (new WorkOrderTabPageCSO(frameworkContext)).ClickEquipmentFeeFlow(i);
    		if(!status)
    		{
    			retryCount++;
    		}
    	}while(!status && retryCount <= 5);
	}
	
	@Test(priority = 15501)
	public void startCM() {
		
		getDataDump().setValue("CM_Status", "FAIL");
		getDataDump().setValue("CMLoggedIN", "FAIL");
	
	}
	
	public void startCSO() {
		getDataDump().setValue("CM_Status", "PASS");
		getDataDump().setValue("CSOLoggedIN", "FAIL");
	}
}


