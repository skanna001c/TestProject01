package com.comcast.century.newConnect;

import java.awt.AWTException;
import java.lang.reflect.Method;

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
import com.comcast.utils.ComcastTest;
import com.comcast.utils.IDataDump;
import com.comcast.utils.PerfTransaction;

public class NewConnectTest extends ComcastTest {

	protected CustomerInfo customerInfo;
	protected AccountInfo accountInfo;
	protected ContactInfo contactInfo;
	protected SiteInfo siteInfo;
	protected ServiceInfo serviceInfo;
	protected ProcessInfo processInfo;
	protected OrderSummaryInfo orderSummaryInfo;
	protected SiteLevelTaskInfo siteLevelTaskInfo;
	protected ServiceLevelTaskInfo serviceLevelTaskInfo;

	String SRID;
	String SurveyID;
	String Site1;
	String Site2;
	String Site3;

	@BeforeClass
	public void beforeTest() {
		loadDataNewConnect();
	}
	
	
	@Test(priority = 500)
	@PerfTransaction(name = "CreateCustomer")
	public void createCustomer() {
		String customerName;
		try {
			customerName = (new CustomerTabPageCM(browser, report)).createCustomer(customerInfo);
			getDataDump().setValue("CustomerName_RT", customerName);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority = 1000)
	@PerfTransaction(name = "CreateServiceAccount")
	public void createServiceAccount() throws InterruptedException {
		if ((new AccountTabPageCM(browser, report)).CreateServiceAccount(accountInfo)) {
			if ((new AccountTabPageCM(browser, report)).clickOnAddContact()) {
				if ((new ContactTabPageCM(browser, report)).CreateAccountPrimaryContact(contactInfo)) {
					if ((new ContactTabPageCM(browser, report)).ClickOnBackBtn()) {
					} else
						Assert.fail("Click on back button failed");
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
		if ((new AccountTabPageCM(browser, report)).CreateBillingAccount(accountInfo)) {
			if ((new AccountTabPageCM(browser, report)).clickOnAddContact()) {
				if ((new ContactTabPageCM(browser, report)).CreateBillingContact(contactInfo)) {
					if ((new ContactTabPageCM(browser, report)).ClickOnBackBtn()) {
					} else
						Assert.fail("Click on back button failed");
				} else
					Assert.fail("Create billing contact failed");
			} else
				Assert.fail("Click on add contact failed");
		} else
			Assert.fail("Create billing account failed");
	}

	
	@Test(priority = 2000)
	@PerfTransaction(name = "CreateAddress1")
	public void createAddress1() throws InterruptedException {
		if ((new AddressTabPageCM(browser, report)).ClickAddressTab(siteInfo)) {
			Site1 = (new AddressTabPageCM(browser, report)).EnterSiteDetailsValid(siteInfo);
			getDataDump().setValue("SITE1_RT", Site1);
			if ((new ContactTabPageCM(browser, report)).CreateSiteTechnicalContact(contactInfo)) {
				if ((new ContactTabPageCM(browser, report)).ClickOnBackBtn()) {
				} else
					Assert.fail("Create site technical Contact failed");
			} else
				Assert.fail("click on address tab failed");
		} else
			Assert.fail("click on back button failed");
	}

	@Test(priority = 2500)
	@PerfTransaction(name = "CreateAddress2")
	public void createAddress2() throws InterruptedException {
		if ((new AddressTabPageCM(browser, report).CreateNewAddress())) {
			Site2 = (new AddressTabPageCM(browser, report)).EnterSiteDetailsValid(siteInfo);
			getDataDump().setValue("SITE2_RT", Site2);
			if ((new ContactTabPageCM(browser, report)).CreateSiteTechnicalContact(contactInfo)) {
				if ((new ContactTabPageCM(browser, report)).ClickOnBackBtn()) {
				} else
					Assert.fail("Create site technical Contact failed");
			} else
				Assert.fail("click on back button failed");
		} else
			Assert.fail("click on create new address failed");
	}
	
	
	@Test(priority = 3000)
	@PerfTransaction(name = "CreateAddress3")
	public void createAddress3() throws InterruptedException {
		if ((new AddressTabPageCM(browser, report)).CreateNewAddress()){
			Site3 = (new AddressTabPageCM(browser, report)).EnterSiteDetailsValid(siteInfo);
			getDataDump().setValue("SITE3_RT", Site3);
			if ((new ContactTabPageCM(browser, report)).CreateSiteTechnicalContact(contactInfo)) {
			} else
				Assert.fail("Create site technical Contact failed");
		} else
			Assert.fail("click on create new address failed");
	}
	
	
	@Test(priority = 3500)
	@PerfTransaction(name = "SelectService")
	public void selectService() throws InterruptedException {
		
		if((new ServiceTabPageCM(browser,report)).selectServices(serviceInfo)){
		}else Assert.fail("Select Services Failed");
	
	}
	
	@Test(priority = 4000)
	@PerfTransaction(name = "ConfigureService")
	public void configureService() throws InterruptedException {
		
		if((new FeatureTabPageCM(browser,report)).configureServices(serviceInfo)){
		}else Assert.fail("Configure Services Failed");
	
	}
	
	
	@Test(priority = 4500)
	@PerfTransaction(name = "ProcessService")
	public void processService() throws InterruptedException {
		if (getDataDump().getValue("processService_status").equalsIgnoreCase("FAIL")) {
			selectService();
			configureService();
		}
		SRID = (new ProcessTabPageCM(browser, report)).ProcessConfiguration(processInfo);
		getDataDump().setValue("SRID_RT", SRID);
		IDataDump dataDump=(new ProcessTabPageCM(browser,report)).processServices(serviceInfo,processInfo,getDataDump());
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
		if((new OrderSummaryTabCMPage(browser,report)).submitOrder(orderSummaryInfo,accountInfo.eRate)){
			getDataDump().setValue("CM_Status", "PASS");
		}else Assert.fail("Submit Order Failed");
		
		
	}
	
	@Test(priority = 5500)
	public void Conduct_Site_Survey() throws InterruptedException {
		SearchOrderndLaunchFiberSiteFlow();
		(new SiteLevelTasks(browser, report)).ConductSiteSurvey();
		(new ConductSiteSurveyTaskPage(browser, report)).ConductSiteSurvey(siteLevelTaskInfo);

	}
	
	@Test(priority = 6000)
	public void Obtain_Site_Agreement() throws InterruptedException {
		SearchOrderndLaunchFiberSiteFlow();
		(new SiteLevelTasks(browser, report)).ObtainSiteAgreement();
		(new ObtainSiteAgreementTaskPage(browser, report)).ObtainSiteAgreement(siteLevelTaskInfo);
	}
	
	
	@Test(priority = 6500)
	public void Conduct_Fiber_Plant_Survey() throws InterruptedException, AWTException {

		SearchOrderndLaunchFiberSiteFlow();
		(new SiteLevelTasks(browser, report)).ConductFiberPlantSurvey();
		(new ConductFiberPlantSurveyTaskPage(browser, report)).ConductFiberPlantSurvey(siteLevelTaskInfo);

	}
	
	@Test(priority = 7000)
	public void Build_House_Account() throws InterruptedException, AWTException {
		SearchOrderndLaunchFiberSiteFlow();
		if ((new SiteLevelTasks(browser, report)).BuildHouseAccount()) {
			(new BuildHouseAccountTaskPage(browser, report)).BuildHouseAccount(siteLevelTaskInfo);
		}

	}

	
	@Test(priority = 7500)
	public void Complete_Wavelength_Reservation() throws InterruptedException, AWTException {
		SearchOrderndLaunchFiberSiteFlow();
		(new SiteLevelTasks(browser, report)).CompleteWavelengthReservation();
		(new CompleteWavelengthReservationTaskPage(browser, report)).CompleteWavelengthReservation(siteLevelTaskInfo);

	}

	
	@Test(priority = 8000)
	public void Complete_Site_Build() throws InterruptedException, AWTException {
		SearchOrderndLaunchFiberSiteFlow();
		(new SiteLevelTasks(browser, report)).CompleteSiteBuild();
		(new CompleteSiteBuildTaskPage(browser, report)).ClickCompleteButton();
		(new CompleteSiteBuildTaskPage(browser, report)).closePopup();

	}
	
	
	@Test(priority = 8500)
	public void Obtain_Fiber_Plant_Permit() throws InterruptedException, AWTException {
		SearchOrderndLaunchFiberSiteFlow();
		(new SiteLevelTasks(browser, report)).ObtainFiberPlantPermits();
		(new ObtainFiberPlantPermitsTaskPage(browser, report)).ObtainFiberPlantPermits();

	}
	
	@Test(priority = 9000)
	public void Complete_Fiber_Plant_Build() throws InterruptedException, AWTException {
		SearchOrderndLaunchFiberSiteFlow();
		(new SiteLevelTasks(browser, report)).CompleteFiberPlantBuild();
		(new CompleteFiberPlantBuildTaskPage(browser, report)).ClickCompleteButton();
		if (new CompleteFiberPlantBuildTaskPage(browser, report).closePopup()) {
			UpdateRespectiveFiberSiteFlows();
		}

	}
	
	@Test(priority = 9500)
	public void Conduct_Site_Survey_Coax() throws InterruptedException {
		SearchOrderndLaunchCoaxSiteFlow();
		(new SiteLevelTasks(browser, report)).ConductSiteSurveyCoax();
		(new ConductSiteSurveyCoaxTaskPage(browser, report)).ConductSiteSurveyCoax(siteLevelTaskInfo);
	}
	
	@Test(priority = 10000)
	public void Obtain_Site_Agreement_Coax() throws InterruptedException {
		 SearchOrderndLaunchCoaxSiteFlow();
		(new SiteLevelTasks(browser, report)).ObtainSiteAgreement();
		(new ObtainSiteAgreementTaskPage(browser, report)).ObtainSiteAgreement(siteLevelTaskInfo);
	}
	
	@Test(priority = 10500)
	@PerfTransaction(name = "Conduct_Coax_Survey")
	public void Conduct_Coax_Survey() throws InterruptedException, AWTException {
		SearchOrderndLaunchCoaxSiteFlow();
		(new SiteLevelTasks(browser, report)).ConductCoaxSurvey();
		(new ConductCoaxSurveyTaskPage(browser, report)).ConductCoaxSurvey(siteLevelTaskInfo);
	}
	
	@Test(priority = 11000)
	public void Build_House_Account_Coax() throws InterruptedException, AWTException {
		SearchOrderndLaunchCoaxSiteFlow();
		if ((new SiteLevelTasks(browser, report)).BuildHouseAccount()) {
			(new BuildHouseAccountTaskPage(browser, report)).BuildHouseAccount(siteLevelTaskInfo);
		}

	}
	
	@Test(priority = 11500)
	@PerfTransaction(name = "Obtain_Coax_Permit")
	public void Obtain_Coax_Permit() throws InterruptedException, AWTException {
		SearchOrderndLaunchCoaxSiteFlow();
		(new SiteLevelTasks(browser, report)).ObtainCoaxPermits();
		(new ObtainCoaxPermitsTaskPage(browser, report)).ObtainCoaxPermits();
	}
	
	@Test(priority = 12000)
	@PerfTransaction(name = "Complete_Site_Build_Coax")
	public void Complete_Site_Build_Coax() throws InterruptedException, AWTException {
		SearchOrderndLaunchCoaxSiteFlow();
		(new SiteLevelTasks(browser, report)).CompleteSiteBuildCoax();
		(new CompleteSiteBuildCoaxTaskPage(browser, report)).ClickCompleteButton();
		(new CompleteSiteBuildCoaxTaskPage(browser, report)).closePopup();
	}
	
	
	@Test(priority = 12500)
	@PerfTransaction(name = "Complete_Coax_Build")
	public void Complete_Coax_Build() throws InterruptedException, AWTException {
		SearchOrderndLaunchCoaxSiteFlow();
		(new SiteLevelTasks(browser, report)).CompleteCoaxBuild();
		(new CompleteCoaxBuildTaskPage(browser, report)).ClickCompleteButton();
		if (new CompleteCoaxBuildTaskPage(browser, report).closePopup()) {
			UpdateRespectiveCoaxSiteFlows();
		}

	}
	/* 
	 * Coax second site flow from 12505 to 12535
	 */
	
	@Test(priority = 12505)
	public void Conduct_Site_Survey_CoaxSite2() throws InterruptedException {
		this.Conduct_Site_Survey_Coax();		
	}
	
	@Test(priority = 12510)
	public void Obtain_Site_Agreement_CoaxSite2() throws InterruptedException {
		this.Obtain_Site_Agreement_Coax();		
	}
	
	@Test(priority = 12515)	
	public void Conduct_Coax_Survey_CoaxSite2() throws InterruptedException, AWTException {
		this.Conduct_Coax_Survey();		
	}
	
	@Test(priority = 12520)
	public void Build_House_Account_CoaxSite2() throws InterruptedException, AWTException {
		this.Build_House_Account_Coax();		

	}
	
	@Test(priority = 12525)	
	public void Obtain_Coax_Permit_CoaxSite2() throws InterruptedException, AWTException {
		this.Obtain_Coax_Permit();		
	}
	
	@Test(priority = 12530)	
	public void Complete_Site_Build_CoaxSite2() throws InterruptedException, AWTException {
		this.Complete_Site_Build_Coax();
	}
	
	
	@Test(priority = 12535)	
	public void Complete_Coax_Build_CoaxSite2() throws InterruptedException, AWTException {
		this.Complete_Coax_Build();		

	}
	
	
	@Test(priority = 13000)
	public void Contact_Customer() throws InterruptedException, AWTException {
		SearchOrderndLaunchServiceRequest();
		(new ServiceLevelTasks(browser, report)).ContactCustomer();
		(new ContactCustomerTaskPage(browser, report)).ContactCustomer();

	}

	@Test(priority = 13500)
	public void Update_Design() throws InterruptedException, AWTException {
		SearchOrderndLaunchServiceRequest();
		(new ServiceLevelTasks(browser, report)).UpdateDesign();
		(new UpdateDesignTaskPage(browser, report)).UpdateDesign();
		(new SiteLevelTasks(browser, report)).ClickBackButton();
	}
	
	
	@Test(priority = 14000)
	public void Build_Update_Local_Biller_Account() throws InterruptedException {
		SearchOrderndLaunchServiceFlow();
		(new ServiceLevelTasks(browser, report)).BULBA();
		(new BULBATaskPage(browser, report)).BULBA(serviceLevelTaskInfo);

	}
	@Test(priority = 14100)
	public void Build_Update_Local_Biller_Account_2() throws InterruptedException {
		SearchOrderndLaunchServiceFlow();
		(new ServiceLevelTasks(browser, report)).BULBA2();
		(new BULBATaskPage(browser, report)).BULBA(serviceLevelTaskInfo);

	}

	@Test(priority = 14500)
	public void Ship_CPE() throws InterruptedException {
		SearchOrderndLaunchServiceFlow();
		(new ServiceLevelTasks(browser, report)).ShipCPE();
		(new ShipCPETaskPage(browser, report)).ShipCPE(serviceLevelTaskInfo);

	}

	@Test(priority = 15000)
	public void Create_Account_and_Equipment() throws InterruptedException {
		SearchOrderndLaunchServiceFlow();
		(new ServiceLevelTasks(browser, report)).CAE();
		(new CAETaskPage(browser, report)).CAE(serviceLevelTaskInfo);

	}
	
	@Test(priority = 15100)
	public void Create_Account_and_Equipment_EPL() throws InterruptedException {
		SearchOrderndLaunchServiceFlow();
		(new ServiceLevelTasks(browser, report)).CAE();
		(new CAETaskPage(browser, report)).CAE_EPL(serviceLevelTaskInfo);

	}
	
	@Test(priority = 15200)
	public void Create_Account_and_Equipment_EVPL() throws InterruptedException {
		SearchOrderndLaunchServiceFlow();
		(new ServiceLevelTasks(browser, report)).CAE();
		(new CAETaskPage(browser, report)).CAE_EVPL(serviceLevelTaskInfo);

	}

	@Test(priority = 15500)
	public void Assign_Design_Info() throws InterruptedException {
		SearchOrderndLaunchServiceFlow();
		(new ServiceLevelTasks(browser, report)).ADI();
		(new ADITaskPage(browser, report)).ADI(serviceLevelTaskInfo);

	}
	
	@Test(priority = 15550)
	public void Assign_Design_Info_EVPL() throws InterruptedException {
		SearchOrderndLaunchServiceFlow();
		(new ServiceLevelTasks(browser, report)).ADI();
		(new ADITaskPage(browser, report)).ADI_EVPL(serviceLevelTaskInfo);

	}

	@Test(priority = 16000)
	public void Generate_Core_Config() throws InterruptedException {
		SearchOrderndLaunchServiceFlow();
		(new ServiceLevelTasks(browser, report)).GenerateCoreConfigs();
		(new GenerateCoreConfigsTaskPage(browser, report)).ClickCompleteButton();

	}

	@Test(priority = 16500)
	public void Generate_CPE_Config() throws InterruptedException {
		SearchOrderndLaunchServiceFlow();
		(new ServiceLevelTasks(browser, report)).GenerateCPEConfigs();
		(new GenerateCPEConfigsTaskPage(browser, report)).ClickCompleteButton();
	}

	@Test(priority = 17000)
	public void Load_Core_Config() throws InterruptedException {
		SearchOrderndLaunchServiceFlow();
		(new ServiceLevelTasks(browser, report)).LoadCoreConfigs();
		(new LoadCoreConfigsTaskPage(browser, report)).ClickCompleteButton();
	}

	@Test(priority = 17500)
	public void Install_CPE() throws InterruptedException {
		SearchOrderndLaunchServiceFlow();
		(new ServiceLevelTasks(browser, report)).InstallCPE();
		(new InstallCPETaskPage(browser, report)).InstallCPE();
	}

	@Test(priority = 18000)
	@PerfTransaction(name = "Install_CPE_Coax")
	public void Install_CPE_Coax() throws InterruptedException, AWTException {
		SearchOrderndLaunchServiceFlow();
		(new ServiceLevelTasks(browser, report)).InstallCPECoax();
		(new InstallCPE_CoaxTaskPage(browser, report)).InstallCPECoax();

	}
	
	@Test(priority = 18100)
	@PerfTransaction(name = "Install_CPE_2")
	public void Install_CPE_2() throws InterruptedException, AWTException {
		SearchOrderndLaunchServiceFlow();
		(new ServiceLevelTasks(browser, report)).InstallCPE2();
		(new InstallCPETaskPage(browser, report)).ClickCompleteButton();

	}

	@Test(priority = 18500)
	public void Set_Critical_Dates() throws InterruptedException {
		SearchOrderndLaunchServiceFlow();
		(new ServiceLevelTasks(browser, report)).SetCriticalDates();
		(new SetCriticalDatesTaskPage(browser, report)).SetCriticalDates();
	}

	@Test(priority = 19000)
	public void Day_of_Configs() throws InterruptedException {
		SearchOrderndLaunchServiceFlow();
		(new ServiceLevelTasks(browser, report)).DayofConfigs();
		(new DaysOfConfigsTaskPage(browser, report)).ClickCompleteButton();
	}

	@Test(priority = 19500)
	public void Activate_Service() throws InterruptedException {
		SearchOrderndLaunchServiceFlow();
		(new ServiceLevelTasks(browser, report)).ActivateService();
		(new ActivateServiceTaskPage(browser, report)).ActivateService(serviceLevelTaskInfo);
	}
	
	@Test(priority = 19600)
	public void Activate_Service_EPL() throws InterruptedException {
		SearchOrderndLaunchServiceFlow();
		(new ServiceLevelTasks(browser, report)).ActivateService();
		(new ActivateServiceTaskPage(browser, report)).ActivateService_EPL(serviceLevelTaskInfo);
	}
	
	@Test(priority = 19700)
	public void Activate_Service_EVPL() throws InterruptedException {
		SearchOrderndLaunchServiceFlow();
		(new ServiceLevelTasks(browser, report)).ActivateService();
		(new ActivateServiceTaskPage(browser, report)).ActivateService_EVPL(serviceLevelTaskInfo);
	}


	@Test(priority = 20000)
	public void Notify_Customer_of_Service_Installation() throws InterruptedException {
		 SearchOrderndLaunchServiceFlow();
		(new ServiceLevelTasks(browser, report)).NotifyCustomerofServiceInstallation();
		(new NotifyCustomerofServiceInstallationTaskPage(browser, report)).NotifyCustomerofServiceInstallation();
	}

	
	@Test(priority = 20500)
	public void Complete_Customer_Acceptance_Testing(Method method) throws InterruptedException {
		SearchOrderndLaunchServiceFlow();
		(new ServiceLevelTasks(browser, report)).CCAT();
		(new CCATTaskPage(browser, report)).ClickCompleteButton();
	}

	@Test(priority = 21000)
	@PerfTransaction(name = "Create_Order_Billing_Package")
	public void Create_Order_Billing_Package() throws AWTException, InterruptedException {
		SearchOrderndLaunchServiceFlow();
		(new ServiceLevelTasks(browser, report)).CreateOrderBillingPackage();
		(new CreateOrderBillingPackageTaskPage(browser, report)).CreateOrderBillingPackage();
	}


	@Test(priority = 21500)
	public void Start_Billing() throws InterruptedException {
		SearchOrderndLaunchServiceFlow();
		(new ServiceLevelTasks(browser, report)).StartBilling();
		(new StartBillingTaskPage(browser,report)).StartBilling();
		(new StartBillingTaskPage(browser,report)).verifyNotes();
		(new ServiceLevelTasks(browser, report)).ClickBackButton();
	}


	@Test(priority = 22000)
	public void EquipmentFeeFlow() throws InterruptedException {
		SearchOrderndLaunchServiceFlow();
		(new WorkOrderTabPageCSO(browser, report)).SearchForOrderInSO(getDataDump().getValue("SRID_RT"));
		(new WorkOrderTabPageCSO(browser, report)).ClickEquipmentFeeFlow();
	}

	@Test(priority = 22500)
	public void EqFeeStartBilling() throws InterruptedException {
		SearchOrderndLaunchServiceFlow();
		(new EqFeeFlowTasks(browser, report)).EqFeeStartBilling();
		(new EqFeeStartBillingTaskPage(browser, report)).EqFeeStartBilling();
		(new EqFeeFlowTasks(browser, report)).ClickBackButton();
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
	}
	
	
	public void SearchOrderndLaunchFiberSiteFlow() {
		for (int i = 1; i <= Integer.parseInt(settings.getValue("MAXNOOFFIBERSITES")); i++) {
			if (!(getDataDump().getValue("FiberSiteFlow" + i).equalsIgnoreCase("pass"))) {
				(new WorkOrderTabPageCSO(browser, report)).SearchForOrderInSO(getDataDump().getValue("SRID_RT"));
				(new WorkOrderTabPageCSO(browser, report))
						.ClickFiberSiteFlow(getDataDump().getValue("FiberSite" + i + "_RT"));
				break;
			}
		}
	}
	
	public void SearchOrderndLaunchCoaxSiteFlow() {
		for (int i = 1; i <= Integer.parseInt(settings.getValue("MAXNOOFCOAXSITES")); i++) {
			if (!(getDataDump().getValue("CoaxSiteFlow" + i).equalsIgnoreCase("pass"))) {
				(new WorkOrderTabPageCSO(browser, report)).SearchForOrderInSO(getDataDump().getValue("SRID_RT"));
				(new WorkOrderTabPageCSO(browser, report))
						.ClickCoaxSiteFlow(getDataDump().getValue("CoaxSite" + i + "_RT"));
				break;
			}
		}
	}
	
	private void UpdateRespectiveFiberSiteFlows() {
		for (int i = 1; i <= Integer.parseInt(settings.getValue("MAXNOOFFIBERSITES")); i++) {
			if (!(getDataDump().getValue("FiberSiteFlow" + i).equalsIgnoreCase("pass"))) {
				getDataDump().setValue("FiberSiteFlow" + i, "PASS");
				break;
			}
		}
	}

	private void UpdateRespectiveCoaxSiteFlows() {
		for (int i = 1; i <= Integer.parseInt(settings.getValue("MAXNOOFCOAXSITES")); i++) {
			if (!(getDataDump().getValue("CoaxSiteFlow" + i).equalsIgnoreCase("pass"))) {
				getDataDump().setValue("CoaxSiteFlow" + i, "PASS");
				break;
			}
		}		
	}
	
	public void SearchOrderndLaunchServiceFlow() throws InterruptedException {
		(new WorkOrderTabPageCSO(browser, report)).SearchForOrderInSO(getDataDump().getValue("SRID_RT"));
		(new WorkOrderTabPageCSO(browser, report)).ClickServiceFlow();
	}
	
	public void SearchOrderndLaunchServiceRequest() {
		(new WorkOrderTabPageCSO(browser, report)).ClickBackButton(2);
		(new WorkOrderTabPageCSO(browser, report)).SearchForOrderInSO(getDataDump().getValue("SRID_RT"));
		(new WorkOrderTabPageCSO(browser, report)).ClickFirstSiteFlow();
	}
}


