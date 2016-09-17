/*package com.comcast.century.newConnect;


import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.awt.AWTException;
import java.io.IOException;

import com.comcast.century.cm.pages.AccountTabPageCM;
import com.comcast.century.cm.pages.AddressTabPageCM;
import com.comcast.century.cm.pages.ContactTabPageCM;
import com.comcast.century.cm.pages.CustomerTabPageCM;
import com.comcast.century.cm.pages.EquipmentTabPageCM;
import com.comcast.century.cm.pages.FeatureTabPageCM;
import com.comcast.century.cm.pages.OrderSummaryTabCMPage;
import com.comcast.century.cm.pages.ProcessTabPageCM;
import com.comcast.century.cm.pages.ServiceTabPageCM;
import com.comcast.century.cm.pages.SurveyTabPageCM;
import com.comcast.century.commons.CenturyApplication;
import com.comcast.century.cso.pages.ADITaskPage;
import com.comcast.century.cso.pages.ActivateServiceTaskPage;
import com.comcast.century.cso.pages.AssignDesignBGPTaskPage;
import com.comcast.century.cso.pages.BGPTasks;
import com.comcast.century.cso.pages.BULBATaskPage;
import com.comcast.century.cso.pages.BuildHouseAccountTaskPage;
import com.comcast.century.cso.pages.CAETaskPage;
import com.comcast.century.cso.pages.CCATTaskPage;
import com.comcast.century.cso.pages.CompleteFiberPlantBuildTaskPage;
import com.comcast.century.cso.pages.CompleteSiteBuildTaskPage;
import com.comcast.century.cso.pages.CompleteWavelengthReservationTaskPage;
import com.comcast.century.cso.pages.ConductFiberPlantSurveyTaskPage;
import com.comcast.century.cso.pages.ConductSiteSurveyTaskPage;
import com.comcast.century.cso.pages.ContactCustomerTaskPage;
import com.comcast.century.cso.pages.DaysOfConfigsTaskPage;
import com.comcast.century.cso.pages.EqFeeFlowTasks;
import com.comcast.century.cso.pages.EqFeeStartBillingTaskPage;
import com.comcast.century.cso.pages.GenerateCPEConfigsTaskPage;
import com.comcast.century.cso.pages.GenerateCoreConfigsTaskPage;
import com.comcast.century.cso.pages.InstallCPETaskPage;
import com.comcast.century.cso.pages.LoadCoreConfigsTaskPage;
import com.comcast.century.cso.pages.NotifyCustomerofServiceInstallationTaskPage;
import com.comcast.century.cso.pages.ObtainFiberPlantPermitsTaskPage;
import com.comcast.century.cso.pages.ObtainSiteAgreementTaskPage;
import com.comcast.century.cso.pages.SCDBGPTaskPage;
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
import com.comcast.century.data.LoginDetails;
import com.comcast.century.data.OrderSummaryInfo;
import com.comcast.century.data.ProcessInfo;
import com.comcast.century.data.ServiceInfo;
import com.comcast.century.data.ServiceLevelTaskInfo;
import com.comcast.century.data.SiteInfo;
import com.comcast.century.data.SiteLevelTaskInfo;

import com.comcast.utils.ComcastTest;
import com.comcast.utils.SelectiveJunitRunner;

//@RunWith(SelectiveJunitRunner.class)
public class newConnect extends ComcastTest {

	private static CenturyApplication centuryApplication;
	private CustomerInfo customerInfo;
	private AccountInfo accountInfo;
	private ContactInfo contactInfo;
	private SiteInfo siteInfo;
	private ServiceInfo serviceInfo;
	private ProcessInfo processInfo;
	private OrderSummaryInfo orderSummaryInfo;
	private SiteLevelTaskInfo siteLevelTaskInfo;
	private ServiceLevelTaskInfo serviceLevelTaskInfo;
	String SRID;
	String SurveyID;

	@BeforeMethod
	public void openApplication() throws Exception {
		LoginDetails loginInfo = LoginDetails.loadFromDatatable(dataTable);
		centuryApplication = new CenturyApplication(frameworkContext);
		//centuryApplication.openCSOUrl(loginInfo);
	}



	@Test
	public void New_Connect_ME_EDI_New_connect() throws InterruptedException, AWTException {
      
	
		
		String Site1;
		customerInfo = CustomerInfo.loadFromDatatable(dataTable);
		(new CustomerTabPageCM(frameworkContext)).createCustomer(customerInfo);
		accountInfo = AccountInfo.loadFromDatatable(dataTable);
		(new AccountTabPageCM(frameworkContext)).CreateServiceAccount(accountInfo);		
		contactInfo = ContactInfo.loadFromDatatable(dataTable);
		(new ContactTabPageCM(frameworkContext)).CreateAccountPrimaryContact(contactInfo);
		(new ContactTabPageCM(frameworkContext)).ClickOnBackBtn();		
		(new AccountTabPageCM(frameworkContext)).CreateBillingAccount(accountInfo);		
		(new ContactTabPageCM(frameworkContext)).CreateBillingContact(contactInfo);		
		(new ContactTabPageCM(frameworkContext)).ClickOnBackBtn();		
		siteInfo = SiteInfo.loadFromDatatable(dataTable);		
		(new AddressTabPageCM(frameworkContext)).ClickAddressTab(siteInfo);		
		Site1 = (new AddressTabPageCM(frameworkContext)).EnterSiteDetailsValid(siteInfo);
		(new ContactTabPageCM(frameworkContext)).CreateSiteTechnicalContact(contactInfo);
		
		(new ServiceTabPageCM(frameworkContext)).ClickOnServiceTab();
		(new ServiceTabPageCM(frameworkContext)).SelectPricePlan();
		(new ServiceTabPageCM(frameworkContext)).EDI();
		(new ServiceTabPageCM(frameworkContext)).EquipmentFee(serviceInfo);
		(new ServiceTabPageCM(frameworkContext)).ClickOnContinueButton();
		(new FeatureTabPageCM(frameworkContext)).ClickOnContinueButton();
		processInfo = ProcessInfo.loadFromDatatable(dataTable);
		SRID = (new ProcessTabPageCM(frameworkContext)).ProcessConfiguration(processInfo);
		(new ProcessTabPageCM(frameworkContext)).UNIConfiguration(processInfo, Site1);
		(new ProcessTabPageCM(frameworkContext)).EVCConfiguration_EDI(processInfo);
		(new ProcessTabPageCM(frameworkContext)).EqFeeConfiguration(processInfo);
		(new ProcessTabPageCM(frameworkContext)).ClickOnContinueButton();
		orderSummaryInfo = OrderSummaryInfo.loadFromDatatable(dataTable);
		//(new OrderSummaryTabCMPage(frameworkContext)).assignLabel(orderSummaryInfo);
		(new OrderSummaryTabCMPage(frameworkContext)).enterOrderDetails(orderSummaryInfo);
		(new OrderSummaryTabCMPage(frameworkContext)).mrcNrc_Value(orderSummaryInfo);
		(new OrderSummaryTabCMPage(frameworkContext)).ClickSubmitOrderButton();
		(new OrderSummaryTabCMPage(frameworkContext)).NavigateToCSO(orderSummaryInfo);
		(new WorkOrderTabPageCSO(frameworkContext)).SearchForOrderInSO(SRID);
		(new WorkOrderTabPageCSO(frameworkContext)).ClickFirstSiteFlow();
		siteLevelTaskInfo = SiteLevelTaskInfo.loadFromDatatable(dataTable);
		
		
		(new SiteLevelTasks(frameworkContext)).ConductSiteSurvey();
		(new ConductSiteSurveyTaskPage(frameworkContext)).ConductSiteSurvey(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).ObtainSiteAgreement();
		(new ObtainSiteAgreementTaskPage(frameworkContext)).ObtainSiteAgreement(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).ConductFiberPlantSurvey();
		(new ConductFiberPlantSurveyTaskPage(frameworkContext)).ConductFiberPlantSurvey(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).BuildHouseAccount();
		(new BuildHouseAccountTaskPage(frameworkContext)).BuildHouseAccount(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).CompleteWavelengthReservation();
		(new CompleteWavelengthReservationTaskPage(frameworkContext)).CompleteWavelengthReservation(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).CompleteSiteBuild();
		(new CompleteSiteBuildTaskPage(frameworkContext)).ClickCompleteButton();
		(new CompleteSiteBuildTaskPage(frameworkContext)).closePopup();
		(new SiteLevelTasks(frameworkContext)).ObtainFiberPlantPermits();
		(new ObtainFiberPlantPermitsTaskPage(frameworkContext)).ObtainFiberPlantPermits();
		(new SiteLevelTasks(frameworkContext)).CompleteFiberPlantBuild();
		(new CompleteFiberPlantBuildTaskPage(frameworkContext)).ClickCompleteButton();
		(new CompleteFiberPlantBuildTaskPage(frameworkContext)).closePopup();
		(new ServiceLevelTasks(frameworkContext)).ContactCustomer();
		(new ContactCustomerTaskPage(frameworkContext)).ContactCustomer();
		(new ServiceLevelTasks(frameworkContext)).UpdateDesign();
		(new UpdateDesignTaskPage(frameworkContext)).UpdateDesign();
		(new SiteLevelTasks(frameworkContext)).ClickBackButton();
		
		(new WorkOrderTabPageCSO(frameworkContext)).ClickServiceFlow();
		serviceLevelTaskInfo = ServiceLevelTaskInfo.loadFromDatatable(dataTable);
		(new ServiceLevelTasks(frameworkContext)).BULBA();
		(new BULBATaskPage(frameworkContext)).BULBA(serviceLevelTaskInfo);
		(new ServiceLevelTasks(frameworkContext)).ShipCPE();
		(new ShipCPETaskPage(frameworkContext)).ShipCPE(serviceLevelTaskInfo);
		(new ServiceLevelTasks(frameworkContext)).CAE();
	//	(new CAETaskPage(frameworkContext)).CAE(serviceLevelTaskInfo);
		(new ServiceLevelTasks(frameworkContext)).ADI();
		//(new ADITaskPage(frameworkContext)).ADI(serviceLevelTaskInfo);
		(new ServiceLevelTasks(frameworkContext)).GenerateCoreConfigs();
		new GenerateCoreConfigsTaskPage(frameworkContext).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).GenerateCPEConfigs();
		(new GenerateCPEConfigsTaskPage(frameworkContext)).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).LoadCoreConfigs();
		(new LoadCoreConfigsTaskPage(frameworkContext)).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).InstallCPE();
		(new InstallCPETaskPage(frameworkContext)).InstallCPE();
		(new ServiceLevelTasks(frameworkContext)).SetCriticalDates();
		(new SetCriticalDatesTaskPage(frameworkContext)).SetCriticalDates();
		(new ServiceLevelTasks(frameworkContext)).DayofConfigs();
		(new DaysOfConfigsTaskPage(frameworkContext)).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).ActivateService();
		(new ActivateServiceTaskPage(frameworkContext)).activateService(serviceInfo,serviceLevelTaskInfo);
		(new ServiceLevelTasks(frameworkContext)).NotifyCustomerofServiceInstallation();
		(new NotifyCustomerofServiceInstallationTaskPage(frameworkContext)).NotifyCustomerofServiceInstallation();
		(new ServiceLevelTasks(frameworkContext)).CCAT();
		(new CCATTaskPage(frameworkContext)).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).StartBilling();
		(new ServiceLevelTasks(frameworkContext)).ClickBackButton();


	}

	
	@Test
	public void New_Connect_ME_EDI_BGP_New_connect() throws InterruptedException, AWTException {

		String Site1;
		customerInfo = CustomerInfo.loadFromDatatable(dataTable);
		(new CustomerTabPageCM(frameworkContext)).createCustomer(customerInfo);
		accountInfo = AccountInfo.loadFromDatatable(dataTable);
		(new AccountTabPageCM(frameworkContext)).CreateServiceAccount(accountInfo);
		contactInfo = ContactInfo.loadFromDatatable(dataTable);
		(new ContactTabPageCM(frameworkContext)).CreateAccountPrimaryContact(contactInfo);
		(new ContactTabPageCM(frameworkContext)).ClickOnBackBtn();
		(new AccountTabPageCM(frameworkContext)).CreateBillingAccount(accountInfo);
		(new ContactTabPageCM(frameworkContext)).CreateBillingContact(contactInfo);
		(new ContactTabPageCM(frameworkContext)).ClickOnBackBtn();
		siteInfo = SiteInfo.loadFromDatatable(dataTable);
		(new AddressTabPageCM(frameworkContext)).ClickAddressTab(siteInfo);
		Site1 = (new AddressTabPageCM(frameworkContext)).EnterSiteDetailsValid(siteInfo);
		(new ContactTabPageCM(frameworkContext)).CreateSiteTechnicalContact(contactInfo);
		(new ServiceTabPageCM(frameworkContext)).ClickOnServiceTab();
		(new ServiceTabPageCM(frameworkContext)).SelectPricePlan();
		(new ServiceTabPageCM(frameworkContext)).EDI();
		(new ServiceTabPageCM(frameworkContext)).EquipmentFee(serviceInfo);
		(new ServiceTabPageCM(frameworkContext)).ClickOnContinueButton();
		(new FeatureTabPageCM(frameworkContext)).BGP();
		(new FeatureTabPageCM(frameworkContext)).ClickOnContinueButton();
		processInfo = ProcessInfo.loadFromDatatable(dataTable);
		SRID = (new ProcessTabPageCM(frameworkContext)).ProcessConfiguration(processInfo);
		(new ProcessTabPageCM(frameworkContext)).UNIConfiguration(processInfo, Site1);
		(new ProcessTabPageCM(frameworkContext)).EVCConfiguration_EDI(processInfo);
		(new ProcessTabPageCM(frameworkContext)).BGPConfiguration();
		(new ProcessTabPageCM(frameworkContext)).EqFeeConfiguration(processInfo);
		(new ProcessTabPageCM(frameworkContext)).ClickOnContinueButton();
		orderSummaryInfo = OrderSummaryInfo.loadFromDatatable(dataTable);
		(new OrderSummaryTabCMPage(frameworkContext)).enterOrderDetails(orderSummaryInfo);
		(new OrderSummaryTabCMPage(frameworkContext)).mrcNrc_Value(orderSummaryInfo);
		(new OrderSummaryTabCMPage(frameworkContext)).ClickSubmitOrderButton();
		(new OrderSummaryTabCMPage(frameworkContext)).NavigateToCSO(orderSummaryInfo);
		(new WorkOrderTabPageCSO(frameworkContext)).SearchForOrderInSO(SRID);
		(new WorkOrderTabPageCSO(frameworkContext)).ClickFirstSiteFlow();
		siteLevelTaskInfo = SiteLevelTaskInfo.loadFromDatatable(dataTable);
		(new SiteLevelTasks(frameworkContext)).ConductSiteSurvey();
		(new ConductSiteSurveyTaskPage(frameworkContext)).ConductSiteSurvey(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).ObtainSiteAgreement();
		(new ObtainSiteAgreementTaskPage(frameworkContext)).ObtainSiteAgreement(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).ConductFiberPlantSurvey();
		(new ConductFiberPlantSurveyTaskPage(frameworkContext)).ConductFiberPlantSurvey(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).BuildHouseAccount();
		(new BuildHouseAccountTaskPage(frameworkContext)).BuildHouseAccount(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).CompleteWavelengthReservation();
		(new CompleteWavelengthReservationTaskPage(frameworkContext)).CompleteWavelengthReservation(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).CompleteSiteBuild();
		(new CompleteSiteBuildTaskPage(frameworkContext)).ClickCompleteButton();
		(new CompleteSiteBuildTaskPage(frameworkContext)).closePopup();
		(new SiteLevelTasks(frameworkContext)).ObtainFiberPlantPermits();
		(new ObtainFiberPlantPermitsTaskPage(frameworkContext)).ObtainFiberPlantPermits();
		(new SiteLevelTasks(frameworkContext)).CompleteFiberPlantBuild();
		(new CompleteFiberPlantBuildTaskPage(frameworkContext)).ClickCompleteButton();
		(new CompleteFiberPlantBuildTaskPage(frameworkContext)).closePopup();
		(new ServiceLevelTasks(frameworkContext)).ContactCustomer();
		(new ContactCustomerTaskPage(frameworkContext)).ContactCustomer();
		(new ServiceLevelTasks(frameworkContext)).UpdateDesign();
		(new UpdateDesignTaskPage(frameworkContext)).UpdateDesign();
		(new SiteLevelTasks(frameworkContext)).ClickBackButton();
		(new WorkOrderTabPageCSO(frameworkContext)).ClickServiceFlow();
		serviceLevelTaskInfo = ServiceLevelTaskInfo.loadFromDatatable(dataTable);
		(new ServiceLevelTasks(frameworkContext)).BULBA();
		(new BULBATaskPage(frameworkContext)).BULBA(serviceLevelTaskInfo);
		(new ServiceLevelTasks(frameworkContext)).ShipCPE();
		(new ShipCPETaskPage(frameworkContext)).ShipCPE(serviceLevelTaskInfo);
		(new ServiceLevelTasks(frameworkContext)).CAE();
		//(new CAETaskPage(frameworkContext)).CAE(serviceLevelTaskInfo);
		(new ServiceLevelTasks(frameworkContext)).ADI();
		//(new ADITaskPage(frameworkContext)).ADI(serviceLevelTaskInfo);
		(new ServiceLevelTasks(frameworkContext)).GenerateCoreConfigs();
		new GenerateCoreConfigsTaskPage(frameworkContext).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).GenerateCPEConfigs();
		(new GenerateCPEConfigsTaskPage(frameworkContext)).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).LoadCoreConfigs();
		(new LoadCoreConfigsTaskPage(frameworkContext)).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).InstallCPE();
		(new InstallCPETaskPage(frameworkContext)).InstallCPE();
		(new ServiceLevelTasks(frameworkContext)).SetCriticalDates();
		(new SetCriticalDatesTaskPage(frameworkContext)).SetCriticalDates();
		(new ServiceLevelTasks(frameworkContext)).DayofConfigs();
		(new DaysOfConfigsTaskPage(frameworkContext)).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).ActivateService();
		(new ActivateServiceTaskPage(frameworkContext)).activateService(serviceInfo,serviceLevelTaskInfo);
		(new ServiceLevelTasks(frameworkContext)).NotifyCustomerofServiceInstallation();
		(new NotifyCustomerofServiceInstallationTaskPage(frameworkContext)).NotifyCustomerofServiceInstallation();
		(new ServiceLevelTasks(frameworkContext)).CCAT();
		(new CCATTaskPage(frameworkContext)).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).StartBilling();
		(new ServiceLevelTasks(frameworkContext)).ClickBackButton();
		(new WorkOrderTabPageCSO(frameworkContext)).ClickEquipmentFeeFlow();
		(new EqFeeFlowTasks(frameworkContext)).EqFeeStartBilling();
		(new EqFeeStartBillingTaskPage(frameworkContext)).EqFeeStartBilling();
		(new EqFeeFlowTasks(frameworkContext)).ClickBackButton();
		(new WorkOrderTabPageCSO(frameworkContext)).ClickBGPFlow();
		(new BGPTasks(frameworkContext)).assignDesignBGP();
		(new AssignDesignBGPTaskPage(frameworkContext)).ClickCompleteButton();
		(new BGPTasks(frameworkContext)).genearteCPEConfigs();
		(new GenerateCPEConfigsTaskPage(frameworkContext)).ClickCompleteButton();
		(new BGPTasks(frameworkContext)).setCriticalDates();
		(new SCDBGPTaskPage(frameworkContext)).setCriticalDates();
		(new BGPTasks(frameworkContext)).dayOfConfigs();
		(new DaysOfConfigsTaskPage(frameworkContext)).ClickCompleteButton();
		(new BGPTasks(frameworkContext)).notifyCustomerOfSI();
		(new NotifyCustomerofServiceInstallationTaskPage(frameworkContext)).NotifyCustomerofServiceInstallation();
		(new BGPTasks(frameworkContext)).startBilling();
		(new BGPTasks(frameworkContext)).ClickBackButton();

	}

	@Test
	public void New_Connect_ME_EDI_ERATE_New_connect() throws InterruptedException, AWTException {

		String Site1;
		customerInfo = CustomerInfo.loadFromDatatable(dataTable);
		(new CustomerTabPageCM(frameworkContext)).createCustomer(customerInfo);
		accountInfo = AccountInfo.loadFromDatatable(dataTable);
		(new AccountTabPageCM(frameworkContext)).CreateErateServiceAccount(accountInfo);
		contactInfo = ContactInfo.loadFromDatatable(dataTable);
		(new ContactTabPageCM(frameworkContext)).CreateAccountPrimaryContact(contactInfo);
		(new ContactTabPageCM(frameworkContext)).ClickOnBackBtn();
		(new AccountTabPageCM(frameworkContext)).CreateBillingAccount(accountInfo);
		siteInfo = SiteInfo.loadFromDatatable(dataTable);
		(new AddressTabPageCM(frameworkContext)).ClickAddressTab(siteInfo);
		Site1 = (new AddressTabPageCM(frameworkContext)).EnterSiteDetailsInvalid(siteInfo);
		(new ContactTabPageCM(frameworkContext)).CreateSiteTechnicalContact(contactInfo);
		(new ServiceTabPageCM(frameworkContext)).ClickOnServiceTab();
		(new ServiceTabPageCM(frameworkContext)).SelectPricePlan();
		(new ServiceTabPageCM(frameworkContext)).EDI();
		(new ServiceTabPageCM(frameworkContext)).EquipmentFee(serviceInfo);
		(new ServiceTabPageCM(frameworkContext)).ClickOnContinueButton();
		(new FeatureTabPageCM(frameworkContext)).ClickOnContinueButton();
		processInfo = ProcessInfo.loadFromDatatable(dataTable);
		SRID = (new ProcessTabPageCM(frameworkContext)).ProcessConfiguration(processInfo);
		(new ProcessTabPageCM(frameworkContext)).UNIConfiguration(processInfo, Site1);
		(new ProcessTabPageCM(frameworkContext)).EVCConfiguration_EDI(processInfo);
		(new ProcessTabPageCM(frameworkContext)).EqFeeConfiguration(processInfo);
		(new ProcessTabPageCM(frameworkContext)).ClickOnContinueButton();
		orderSummaryInfo = OrderSummaryInfo.loadFromDatatable(dataTable);
		(new OrderSummaryTabCMPage(frameworkContext)).enterOrderDetails(orderSummaryInfo);
		(new OrderSummaryTabCMPage(frameworkContext)).mrcNrc_Value(orderSummaryInfo);
		(new OrderSummaryTabCMPage(frameworkContext)).Attachments(orderSummaryInfo);
		(new OrderSummaryTabCMPage(frameworkContext)).ClickSubmitOrderButton();
		(new OrderSummaryTabCMPage(frameworkContext)).NavigateToCSO(orderSummaryInfo);
		(new WorkOrderTabPageCSO(frameworkContext)).SearchForOrderInSO(SRID);
		(new WorkOrderTabPageCSO(frameworkContext)).ClickFirstSiteFlow();
		siteLevelTaskInfo = SiteLevelTaskInfo.loadFromDatatable(dataTable);
		(new SiteLevelTasks(frameworkContext)).ConductSiteSurvey();
		(new ConductSiteSurveyTaskPage(frameworkContext)).ConductSiteSurvey(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).ObtainSiteAgreement();
		(new ObtainSiteAgreementTaskPage(frameworkContext)).ObtainSiteAgreement(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).ConductFiberPlantSurvey();
		(new ConductFiberPlantSurveyTaskPage(frameworkContext)).ConductFiberPlantSurvey(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).BuildHouseAccount();
		(new BuildHouseAccountTaskPage(frameworkContext)).BuildHouseAccount(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).CompleteWavelengthReservation();
		(new CompleteWavelengthReservationTaskPage(frameworkContext)).CompleteWavelengthReservation(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).CompleteSiteBuild();
		(new CompleteSiteBuildTaskPage(frameworkContext)).ClickCompleteButton();
		(new CompleteSiteBuildTaskPage(frameworkContext)).closePopup();
		(new SiteLevelTasks(frameworkContext)).ObtainFiberPlantPermits();
		(new ObtainFiberPlantPermitsTaskPage(frameworkContext)).ObtainFiberPlantPermits();
		(new SiteLevelTasks(frameworkContext)).CompleteFiberPlantBuild();
		(new CompleteFiberPlantBuildTaskPage(frameworkContext)).ClickCompleteButton();
		(new CompleteFiberPlantBuildTaskPage(frameworkContext)).closePopup();
		(new ServiceLevelTasks(frameworkContext)).ContactCustomer();
		(new ContactCustomerTaskPage(frameworkContext)).ContactCustomer();
		(new ServiceLevelTasks(frameworkContext)).UpdateDesign();
		(new UpdateDesignTaskPage(frameworkContext)).UpdateDesign();
		(new SiteLevelTasks(frameworkContext)).ClickBackButton();
		(new WorkOrderTabPageCSO(frameworkContext)).ClickServiceFlow();
		serviceLevelTaskInfo = ServiceLevelTaskInfo.loadFromDatatable(dataTable);
		(new ServiceLevelTasks(frameworkContext)).BULBA();
		(new BULBATaskPage(frameworkContext)).BULBA(serviceLevelTaskInfo);
		(new ServiceLevelTasks(frameworkContext)).ShipCPE();
		(new ShipCPETaskPage(frameworkContext)).ShipCPE(serviceLevelTaskInfo);
		(new ServiceLevelTasks(frameworkContext)).CAE();
		//(new CAETaskPage(frameworkContext)).CAE(serviceLevelTaskInfo);
		(new ServiceLevelTasks(frameworkContext)).ADI();
		//(new ADITaskPage(frameworkContext)).ADI(serviceLevelTaskInfo);
		(new ServiceLevelTasks(frameworkContext)).GenerateCoreConfigs();
		new GenerateCoreConfigsTaskPage(frameworkContext).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).GenerateCPEConfigs();
		(new GenerateCPEConfigsTaskPage(frameworkContext)).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).LoadCoreConfigs();
		(new LoadCoreConfigsTaskPage(frameworkContext)).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).InstallCPE();
		(new InstallCPETaskPage(frameworkContext)).InstallCPE();
		(new ServiceLevelTasks(frameworkContext)).SetCriticalDates();
		(new SetCriticalDatesTaskPage(frameworkContext)).SetCriticalDates();
		(new ServiceLevelTasks(frameworkContext)).DayofConfigs();
		(new DaysOfConfigsTaskPage(frameworkContext)).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).ActivateService();
		(new ActivateServiceTaskPage(frameworkContext)).activateService(serviceInfo,serviceLevelTaskInfo);
		(new ServiceLevelTasks(frameworkContext)).NotifyCustomerofServiceInstallation();
		(new NotifyCustomerofServiceInstallationTaskPage(frameworkContext)).NotifyCustomerofServiceInstallation();
		(new ServiceLevelTasks(frameworkContext)).CCAT();
		(new CCATTaskPage(frameworkContext)).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).StartBilling();
		(new ServiceLevelTasks(frameworkContext)).ClickBackButton();
		(new WorkOrderTabPageCSO(frameworkContext)).ClickEquipmentFeeFlow();
		(new EqFeeFlowTasks(frameworkContext)).EqFeeStartBilling();
		(new EqFeeStartBillingTaskPage(frameworkContext)).EqFeeStartBilling();
		(new EqFeeFlowTasks(frameworkContext)).ClickBackButton();

	}

	@Test
	public void New_Connect_ME_EDI_ToF_New_connect() throws InterruptedException, AWTException {

		
		String Site1;
		customerInfo = CustomerInfo.loadFromDatatable(dataTable);
		(new CustomerTabPageCM(frameworkContext)).createCustomer(customerInfo);
		accountInfo = AccountInfo.loadFromDatatable(dataTable);
		(new AccountTabPageCM(frameworkContext)).CreateServiceAccount(accountInfo);
		contactInfo = ContactInfo.loadFromDatatable(dataTable);
		(new ContactTabPageCM(frameworkContext)).CreateAccountPrimaryContact(contactInfo);
		(new ContactTabPageCM(frameworkContext)).ClickOnBackBtn();
		(new AccountTabPageCM(frameworkContext)).CreateBillingAccount(accountInfo);
		(new ContactTabPageCM(frameworkContext)).CreateBillingContact(contactInfo);
		(new ContactTabPageCM(frameworkContext)).ClickOnBackBtn();
		siteInfo = SiteInfo.loadFromDatatable(dataTable);
		(new AddressTabPageCM(frameworkContext)).ClickAddressTab(siteInfo);
		Site1 = (new AddressTabPageCM(frameworkContext)).EnterSiteDetailsValid(siteInfo);
		(new ContactTabPageCM(frameworkContext)).CreateSiteTechnicalContact(contactInfo);
		(new ServiceTabPageCM(frameworkContext)).ClickOnServiceTab();
		(new ServiceTabPageCM(frameworkContext)).SelectPricePlan();
		(new ServiceTabPageCM(frameworkContext)).EDI();
		(new ServiceTabPageCM(frameworkContext)).EquipmentFee(serviceInfo);
		(new ServiceTabPageCM(frameworkContext)).TrunkPRI();
		(new ServiceTabPageCM(frameworkContext)).ClickOnContinueButton();
		(new FeatureTabPageCM(frameworkContext)).ClickOnContinueButton();
		processInfo = ProcessInfo.loadFromDatatable(dataTable);
		SRID = (new ProcessTabPageCM(frameworkContext)).ProcessConfiguration(processInfo);
		(new ProcessTabPageCM(frameworkContext)).UNIConfiguration(processInfo, Site1);
		(new ProcessTabPageCM(frameworkContext)).EVCConfiguration_EDI(processInfo);
		(new ProcessTabPageCM(frameworkContext)).EqFeeConfiguration(processInfo);
		(new ProcessTabPageCM(frameworkContext)).Trunk_PRI(processInfo);
		(new ProcessTabPageCM(frameworkContext)).UNIConfiguration_PRI(processInfo, Site1);
		(new ProcessTabPageCM(frameworkContext)).EVCConfiguration_PRI(processInfo);
		(new ProcessTabPageCM(frameworkContext)).ClickOnContinueButton();
		orderSummaryInfo = OrderSummaryInfo.loadFromDatatable(dataTable);
		(new OrderSummaryTabCMPage(frameworkContext)).enterOrderDetails(orderSummaryInfo);
		(new OrderSummaryTabCMPage(frameworkContext)).mrcNrc_Value(orderSummaryInfo);
		(new OrderSummaryTabCMPage(frameworkContext)).ClickSubmitOrderButton();
		(new OrderSummaryTabCMPage(frameworkContext)).NavigateToCSO(orderSummaryInfo);
		(new WorkOrderTabPageCSO(frameworkContext)).SearchForOrderInSO(SRID);
		(new WorkOrderTabPageCSO(frameworkContext)).ClickFirstSiteFlow();
		siteLevelTaskInfo = SiteLevelTaskInfo.loadFromDatatable(dataTable);
		(new SiteLevelTasks(frameworkContext)).ConductSiteSurvey();
		(new ConductSiteSurveyTaskPage(frameworkContext)).ConductSiteSurvey(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).ObtainSiteAgreement();
		(new ObtainSiteAgreementTaskPage(frameworkContext)).ObtainSiteAgreement(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).ConductFiberPlantSurvey();
		(new ConductFiberPlantSurveyTaskPage(frameworkContext)).ConductFiberPlantSurvey(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).BuildHouseAccount();
		(new BuildHouseAccountTaskPage(frameworkContext)).BuildHouseAccount(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).CompleteWavelengthReservation();
		(new CompleteWavelengthReservationTaskPage(frameworkContext)).CompleteWavelengthReservation(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).CompleteSiteBuild();
		(new CompleteSiteBuildTaskPage(frameworkContext)).ClickCompleteButton();
		(new CompleteSiteBuildTaskPage(frameworkContext)).closePopup();
		(new SiteLevelTasks(frameworkContext)).ObtainFiberPlantPermits();
		(new ObtainFiberPlantPermitsTaskPage(frameworkContext)).ObtainFiberPlantPermits();
		(new SiteLevelTasks(frameworkContext)).CompleteFiberPlantBuild();
		(new CompleteFiberPlantBuildTaskPage(frameworkContext)).ClickCompleteButton();
		(new CompleteFiberPlantBuildTaskPage(frameworkContext)).closePopup();
		(new ServiceLevelTasks(frameworkContext)).ContactCustomer();
		(new ContactCustomerTaskPage(frameworkContext)).ContactCustomer();
		(new ServiceLevelTasks(frameworkContext)).UpdateDesign();
		(new UpdateDesignTaskPage(frameworkContext)).UpdateDesign();
		(new SiteLevelTasks(frameworkContext)).ClickBackButton();
		(new WorkOrderTabPageCSO(frameworkContext)).ClickServiceFlow();
		serviceLevelTaskInfo = ServiceLevelTaskInfo.loadFromDatatable(dataTable);
		(new ServiceLevelTasks(frameworkContext)).BULBA();
		(new BULBATaskPage(frameworkContext)).BULBA(serviceLevelTaskInfo);
		(new ServiceLevelTasks(frameworkContext)).ShipCPE();
		(new ShipCPETaskPage(frameworkContext)).ShipCPE(serviceLevelTaskInfo);
		(new ServiceLevelTasks(frameworkContext)).CAE();
		//(new CAETaskPage(frameworkContext)).CAE(serviceLevelTaskInfo);
		(new ServiceLevelTasks(frameworkContext)).ADI();
		//(new ADITaskPage(frameworkContext)).ADI(serviceLevelTaskInfo);
		(new ServiceLevelTasks(frameworkContext)).GenerateCoreConfigs();
		new GenerateCoreConfigsTaskPage(frameworkContext).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).GenerateCPEConfigs();
		(new GenerateCPEConfigsTaskPage(frameworkContext)).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).LoadCoreConfigs();
		(new LoadCoreConfigsTaskPage(frameworkContext)).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).InstallCPE();
		(new InstallCPETaskPage(frameworkContext)).InstallCPE();
		(new ServiceLevelTasks(frameworkContext)).SetCriticalDates();
		(new SetCriticalDatesTaskPage(frameworkContext)).SetCriticalDates();
		(new ServiceLevelTasks(frameworkContext)).DayofConfigs();
		(new DaysOfConfigsTaskPage(frameworkContext)).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).ActivateService();
		(new ActivateServiceTaskPage(frameworkContext)).activateService(serviceInfo,serviceLevelTaskInfo);
		(new ServiceLevelTasks(frameworkContext)).NotifyCustomerofServiceInstallation();
		(new NotifyCustomerofServiceInstallationTaskPage(frameworkContext)).NotifyCustomerofServiceInstallation();
		(new ServiceLevelTasks(frameworkContext)).CCAT();
		(new CCATTaskPage(frameworkContext)).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).StartBilling();
		(new ServiceLevelTasks(frameworkContext)).ClickBackButton();
		//(new WorkOrderTabPageCSO(frameworkContext)).ClickEquipmentFeeFlow();
		//(new EqFeeFlowTasks(frameworkContext)).EqFeeStartBilling();
		//(new EqFeeStartBillingTaskPage(frameworkContext)).EqFeeStartBilling();
		//(new EqFeeFlowTasks(frameworkContext)).ClickBackButton();
		(new WorkOrderTabPageCSO(frameworkContext)).ClickTrunkPRIFlow();
		(new ServiceLevelTasks(frameworkContext)).BULBA();
		(new BULBATaskPage(frameworkContext)).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).CAE();
		//(new CAETaskPage(frameworkContext)).cAE_PRI(serviceLevelTaskInfo);
		(new ServiceLevelTasks(frameworkContext)).ShipCPE();
		(new ShipCPETaskPage(frameworkContext)).ShipCPE(serviceLevelTaskInfo);
		(new ServiceLevelTasks(frameworkContext)).ADI();
		//(new ADITaskPage(frameworkContext)).assignDesignInformationPRI(serviceLevelTaskInfo);
		(new ServiceLevelTasks(frameworkContext)).GenerateCPEConfigs();
		(new GenerateCPEConfigsTaskPage(frameworkContext)).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).GenerateCoreConfigs();
		(new GenerateCoreConfigsTaskPage(frameworkContext)).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).LoadCoreConfigs();
		(new LoadCoreConfigsTaskPage(frameworkContext)).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).InstallCPE();
		(new InstallCPETaskPage(frameworkContext)).installCPEPRI();
		(new ServiceLevelTasks(frameworkContext)).SetCriticalDates();
		(new SetCriticalDatesTaskPage(frameworkContext)).SetCriticalDates();
		(new ServiceLevelTasks(frameworkContext)).DayofConfigs();
		(new DaysOfConfigsTaskPage(frameworkContext)).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).ActivateService();
		(new ActivateServiceTaskPage(frameworkContext)).activateService(serviceInfo,serviceLevelTaskInfo);;
		(new ServiceLevelTasks(frameworkContext)).ClickBackButton();
		
		
	}

	@Test
	public void New_Connect_ME_ENS_New_connect() throws InterruptedException, AWTException {

		// (new HomePageCM(browser,report)).NavigateToServiceTab();
		String Site1, Site2;
		customerInfo = CustomerInfo.loadFromDatatable(dataTable);
		(new CustomerTabPageCM(frameworkContext)).createCustomer(customerInfo);
		accountInfo = AccountInfo.loadFromDatatable(dataTable);
		(new AccountTabPageCM(frameworkContext)).CreateServiceAccount(accountInfo);
		contactInfo = ContactInfo.loadFromDatatable(dataTable);
		(new ContactTabPageCM(frameworkContext)).CreateAccountPrimaryContact(contactInfo);
		(new ContactTabPageCM(frameworkContext)).ClickOnBackBtn();
		(new AccountTabPageCM(frameworkContext)).CreateBillingAccount(accountInfo);
		(new ContactTabPageCM(frameworkContext)).CreateBillingContact(contactInfo);
		(new ContactTabPageCM(frameworkContext)).ClickOnBackBtn();
		siteInfo = SiteInfo.loadFromDatatable(dataTable);
		(new AddressTabPageCM(frameworkContext)).ClickAddressTab(siteInfo);
		Site1 = (new AddressTabPageCM(frameworkContext)).EnterSiteDetailsValid(siteInfo);
		(new ContactTabPageCM(frameworkContext)).CreateSiteTechnicalContact(contactInfo);
		(new ContactTabPageCM(frameworkContext)).ClickOnBackBtn();
		(new AddressTabPageCM(frameworkContext)).CreateNewAddress();
		Site2 = (new AddressTabPageCM(frameworkContext)).EnterSiteDetailsValid(siteInfo);
		(new ContactTabPageCM(frameworkContext)).CreateSiteTechnicalContact(contactInfo);
		(new ServiceTabPageCM(frameworkContext)).ClickOnServiceTab();
		(new ServiceTabPageCM(frameworkContext)).SelectPricePlan();
		(new ServiceTabPageCM(frameworkContext)).ENS();
		(new ServiceTabPageCM(frameworkContext)).EquipmentFee(serviceInfo);
		(new ServiceTabPageCM(frameworkContext)).ClickOnContinueButton();
		(new FeatureTabPageCM(frameworkContext)).ENS();
		processInfo = ProcessInfo.loadFromDatatable(dataTable);
		SRID = (new ProcessTabPageCM(frameworkContext)).ProcessConfiguration(processInfo);
		(new ProcessTabPageCM(frameworkContext)).UNIConfiguration(processInfo, Site1);
		(new ProcessTabPageCM(frameworkContext)).UNI2Configuration(processInfo, Site2);
		(new ProcessTabPageCM(frameworkContext)).EVCConfiguration_ENS(processInfo);
		(new ProcessTabPageCM(frameworkContext)).EVC2Configuration_ENS(processInfo);
		(new ProcessTabPageCM(frameworkContext)).EqFeeConfiguration(processInfo);
		(new ProcessTabPageCM(frameworkContext)).EqFee2Configuration(processInfo);
		(new ProcessTabPageCM(frameworkContext)).ClickOnContinueButton();
		orderSummaryInfo = OrderSummaryInfo.loadFromDatatable(dataTable);
		//(new OrderSummaryTabCMPage(frameworkContext)).assignLabel(orderSummaryInfo);
		(new OrderSummaryTabCMPage(frameworkContext)).enterOrderDetails(orderSummaryInfo);
		(new OrderSummaryTabCMPage(frameworkContext)).mrcNrc_Value(orderSummaryInfo);
		(new OrderSummaryTabCMPage(frameworkContext)).ClickSubmitOrderButton();
		(new OrderSummaryTabCMPage(frameworkContext)).NavigateToCSO(orderSummaryInfo);
		(new WorkOrderTabPageCSO(frameworkContext)).SearchForOrderInSO(SRID);

	}

	@Test
	public void New_Connect_ME_EPL_New_connect() throws InterruptedException, AWTException {
	   
		
		String Site1, Site2;
		customerInfo = CustomerInfo.loadFromDatatable(dataTable);
		(new CustomerTabPageCM(frameworkContext)).createCustomer(customerInfo);
		accountInfo = AccountInfo.loadFromDatatable(dataTable);
		(new AccountTabPageCM(frameworkContext)).CreateServiceAccount(accountInfo);
		contactInfo = ContactInfo.loadFromDatatable(dataTable);
		(new ContactTabPageCM(frameworkContext)).CreateAccountPrimaryContact(contactInfo);
		(new ContactTabPageCM(frameworkContext)).ClickOnBackBtn();
		(new AccountTabPageCM(frameworkContext)).CreateBillingAccount(accountInfo);
		(new ContactTabPageCM(frameworkContext)).CreateBillingContact(contactInfo);
		(new ContactTabPageCM(frameworkContext)).ClickOnBackBtn();
		siteInfo = SiteInfo.loadFromDatatable(dataTable);
		(new AddressTabPageCM(frameworkContext)).ClickAddressTab(siteInfo);
		Site1 = (new AddressTabPageCM(frameworkContext)).EnterSiteDetailsValid(siteInfo);
		(new ContactTabPageCM(frameworkContext)).CreateSiteTechnicalContact(contactInfo);
		(new ContactTabPageCM(frameworkContext)).ClickOnBackBtn();
		(new AddressTabPageCM(frameworkContext)).CreateNewAddress();
		Site2 = (new AddressTabPageCM(frameworkContext)).EnterSiteDetailsValid(siteInfo);
		(new ContactTabPageCM(frameworkContext)).CreateSiteTechnicalContact(contactInfo);
		(new ServiceTabPageCM(frameworkContext)).ClickOnServiceTab();
		(new ServiceTabPageCM(frameworkContext)).SelectPricePlan();
		(new ServiceTabPageCM(frameworkContext)).EPL();
		(new ServiceTabPageCM(frameworkContext)).EquipmentFee(serviceInfo);
		(new ServiceTabPageCM(frameworkContext)).ClickOnContinueButton();
		(new FeatureTabPageCM(frameworkContext)).ClickOnContinueButton();
		processInfo = ProcessInfo.loadFromDatatable(dataTable);
		SRID = (new ProcessTabPageCM(frameworkContext)).ProcessConfiguration(processInfo);
		(new ProcessTabPageCM(frameworkContext)).UNIConfiguration(processInfo, Site1);
		(new ProcessTabPageCM(frameworkContext)).UNI2Configuration(processInfo, Site2);
		(new ProcessTabPageCM(frameworkContext)).EVCConfiguration_EPL(processInfo);
		(new ProcessTabPageCM(frameworkContext)).EqFeeConfiguration(processInfo);
		(new ProcessTabPageCM(frameworkContext)).EqFee2Configuration(processInfo);
		(new ProcessTabPageCM(frameworkContext)).ClickOnContinueButton();
		orderSummaryInfo = OrderSummaryInfo.loadFromDatatable(dataTable);
		//(new OrderSummaryTabCMPage(frameworkContext)).assignLabel(orderSummaryInfo);
		(new OrderSummaryTabCMPage(frameworkContext)).enterOrderDetails(orderSummaryInfo);
		(new OrderSummaryTabCMPage(frameworkContext)).mrcNrc_Value(orderSummaryInfo);
		(new OrderSummaryTabCMPage(frameworkContext)).ClickSubmitOrderButton();
		(new OrderSummaryTabCMPage(frameworkContext)).NavigateToCSO(orderSummaryInfo);
		(new WorkOrderTabPageCSO(frameworkContext)).SearchForOrderInSO(SRID);
	    (new WorkOrderTabPageCSO(frameworkContext)).ClickFirstSiteFlow();
		siteLevelTaskInfo = SiteLevelTaskInfo.loadFromDatatable(dataTable);
		(new SiteLevelTasks(frameworkContext)).ConductSiteSurvey();
		(new ConductSiteSurveyTaskPage(frameworkContext)).ConductSiteSurvey(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).ObtainSiteAgreement();
		(new ObtainSiteAgreementTaskPage(frameworkContext)).ObtainSiteAgreement(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).ConductFiberPlantSurvey();
		(new ConductFiberPlantSurveyTaskPage(frameworkContext)).ConductFiberPlantSurvey(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).BuildHouseAccount();
		(new BuildHouseAccountTaskPage(frameworkContext)).BuildHouseAccount(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).CompleteWavelengthReservation();
		(new CompleteWavelengthReservationTaskPage(frameworkContext)).CompleteWavelengthReservation(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).CompleteSiteBuild();
		(new CompleteSiteBuildTaskPage(frameworkContext)).ClickCompleteButton();
		(new CompleteSiteBuildTaskPage(frameworkContext)).closePopup();
		(new SiteLevelTasks(frameworkContext)).ObtainFiberPlantPermits();
		(new ObtainFiberPlantPermitsTaskPage(frameworkContext)).ObtainFiberPlantPermits();
		(new SiteLevelTasks(frameworkContext)).CompleteFiberPlantBuild();
		(new CompleteFiberPlantBuildTaskPage(frameworkContext)).ClickCompleteButton();
		(new CompleteFiberPlantBuildTaskPage(frameworkContext)).closePopup();
		(new SiteLevelTasks(frameworkContext)).ClickBackButton();
		(new WorkOrderTabPageCSO(frameworkContext)).ClickSecondSiteFlow();
		(new SiteLevelTasks(frameworkContext)).ConductSiteSurvey();
		(new ConductSiteSurveyTaskPage(frameworkContext)).ConductSiteSurvey(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).ObtainSiteAgreement();
		(new ObtainSiteAgreementTaskPage(frameworkContext)).ObtainSiteAgreement(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).ConductFiberPlantSurvey();
		(new ConductFiberPlantSurveyTaskPage(frameworkContext)).ConductFiberPlantSurvey(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).BuildHouseAccount();
		(new BuildHouseAccountTaskPage(frameworkContext)).BuildHouseAccount(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).CompleteWavelengthReservation();
		(new CompleteWavelengthReservationTaskPage(frameworkContext)).CompleteWavelengthReservation(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).CompleteSiteBuild();
		(new CompleteSiteBuildTaskPage(frameworkContext)).ClickCompleteButton();
		(new CompleteSiteBuildTaskPage(frameworkContext)).closePopup();
		(new SiteLevelTasks(frameworkContext)).ObtainFiberPlantPermits();
		(new ObtainFiberPlantPermitsTaskPage(frameworkContext)).ObtainFiberPlantPermits();
		(new SiteLevelTasks(frameworkContext)).CompleteFiberPlantBuild();
		(new CompleteFiberPlantBuildTaskPage(frameworkContext)).ClickCompleteButton();
		(new CompleteFiberPlantBuildTaskPage(frameworkContext)).closePopup();
		(new ServiceLevelTasks(frameworkContext)).ContactCustomer();
		(new ContactCustomerTaskPage(frameworkContext)).ContactCustomer();
		(new ServiceLevelTasks(frameworkContext)).UpdateDesign();
		(new UpdateDesignTaskPage(frameworkContext)).UpdateDesign();
		(new SiteLevelTasks(frameworkContext)).ClickBackButton();
		
		(new WorkOrderTabPageCSO(frameworkContext)).ClickEPLFlow();
		serviceLevelTaskInfo = ServiceLevelTaskInfo.loadFromDatatable(dataTable);
		(new ServiceLevelTasks(frameworkContext)).BULBA();
		(new BULBATaskPage(frameworkContext)).BULBA(serviceLevelTaskInfo);
		(new ServiceLevelTasks(frameworkContext)).BULBA2();
		(new BULBATaskPage(frameworkContext)).BULBA(serviceLevelTaskInfo);
		(new ServiceLevelTasks(frameworkContext)).ShipCPE();
		(new ShipCPETaskPage(frameworkContext)).ShipCPE(serviceLevelTaskInfo);
		(new ServiceLevelTasks(frameworkContext)).CAE();
		//(new CAETaskPage(frameworkContext)).CAE_EPL(serviceLevelTaskInfo);
		(new ServiceLevelTasks(frameworkContext)).ADI();
		//(new ADITaskPage(frameworkContext)).ADI(serviceLevelTaskInfo);
		(new ServiceLevelTasks(frameworkContext)).GenerateCoreConfigs();
		new GenerateCoreConfigsTaskPage(frameworkContext).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).GenerateCPEConfigs();
		(new GenerateCPEConfigsTaskPage(frameworkContext)).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).LoadCoreConfigs();
		(new LoadCoreConfigsTaskPage(frameworkContext)).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).InstallCPE();
		(new InstallCPETaskPage(frameworkContext)).InstallCPE();
		(new ServiceLevelTasks(frameworkContext)).InstallCPE2();
		(new InstallCPETaskPage(frameworkContext)).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).SetCriticalDates();
		(new SetCriticalDatesTaskPage(frameworkContext)).SetCriticalDates();
		(new ServiceLevelTasks(frameworkContext)).DayofConfigs();
		(new DaysOfConfigsTaskPage(frameworkContext)).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).ActivateService();
		(new ActivateServiceTaskPage(frameworkContext)).ActivateService_EPL(serviceLevelTaskInfo);
		(new ServiceLevelTasks(frameworkContext)).NotifyCustomerofServiceInstallation();
		(new NotifyCustomerofServiceInstallationTaskPage(frameworkContext)).NotifyCustomerofServiceInstallation();
		(new ServiceLevelTasks(frameworkContext)).CCAT();
		(new CCATTaskPage(frameworkContext)).ClickCompleteButton();
		(new ServiceLevelTasks(frameworkContext)).StartBilling();
		(new StartBillingTaskPage(browser,report)).verifyNotes();
		(new ServiceLevelTasks(frameworkContext)).ClickBackButton();
	}

	@Test
	public void New_Connect_ME_EVPL_New_connect() throws InterruptedException, AWTException {

		String Site1, Site2, Site3;
		customerInfo = CustomerInfo.loadFromDatatable(dataTable);
		(new CustomerTabPageCM(frameworkContext)).createCustomer(customerInfo);
		accountInfo = AccountInfo.loadFromDatatable(dataTable);
		(new AccountTabPageCM(frameworkContext)).CreateServiceAccount(accountInfo);
		contactInfo = ContactInfo.loadFromDatatable(dataTable);
		(new ContactTabPageCM(frameworkContext)).CreateAccountPrimaryContact(contactInfo);
		(new ContactTabPageCM(frameworkContext)).ClickOnBackBtn();
		(new AccountTabPageCM(frameworkContext)).CreateBillingAccount(accountInfo);
		(new ContactTabPageCM(frameworkContext)).CreateBillingContact(contactInfo);
		(new ContactTabPageCM(frameworkContext)).ClickOnBackBtn();
		siteInfo = SiteInfo.loadFromDatatable(dataTable);
		(new AddressTabPageCM(frameworkContext)).ClickAddressTab(siteInfo);
		Site1 = (new AddressTabPageCM(frameworkContext)).EnterSiteDetailsValid(siteInfo);
		(new ContactTabPageCM(frameworkContext)).CreateSiteTechnicalContact(contactInfo);
		(new ContactTabPageCM(frameworkContext)).ClickOnBackBtn();
		(new AddressTabPageCM(frameworkContext)).CreateNewAddress();
		Site2 = (new AddressTabPageCM(frameworkContext)).EnterSiteDetailsValid(siteInfo);
		(new ContactTabPageCM(frameworkContext)).CreateSiteTechnicalContact(contactInfo);
		(new ContactTabPageCM(frameworkContext)).ClickOnBackBtn();
		(new AddressTabPageCM(frameworkContext)).CreateNewAddress();
		Site3 = (new AddressTabPageCM(frameworkContext)).EnterSiteDetailsValid(siteInfo);
		(new ContactTabPageCM(frameworkContext)).CreateSiteTechnicalContact(contactInfo);
		(new ServiceTabPageCM(frameworkContext)).ClickOnServiceTab();
		(new ServiceTabPageCM(frameworkContext)).SelectPricePlan();
		(new ServiceTabPageCM(frameworkContext)).EVPL();
		(new ServiceTabPageCM(frameworkContext)).EquipmentFee(serviceInfo);
		(new ServiceTabPageCM(frameworkContext)).ClickOnContinueButton();
		(new FeatureTabPageCM(frameworkContext)).EVPL();
		processInfo = ProcessInfo.loadFromDatatable(dataTable);
		SRID = (new ProcessTabPageCM(frameworkContext)).ProcessConfiguration(processInfo);
		(new ProcessTabPageCM(frameworkContext)).UNIConfiguration(processInfo, Site1);
		(new ProcessTabPageCM(frameworkContext)).UNI2Configuration(processInfo, Site2);
		(new ProcessTabPageCM(frameworkContext)).UNI3Configuration(processInfo, Site3);
		(new ProcessTabPageCM(frameworkContext)).EVCConfiguration_EVPL(processInfo);
		(new ProcessTabPageCM(frameworkContext)).EVC2Configuration_EVPL(processInfo);
		(new ProcessTabPageCM(frameworkContext)).EqFeeConfiguration(processInfo);
		(new ProcessTabPageCM(frameworkContext)).EqFee2Configuration(processInfo);
		(new ProcessTabPageCM(frameworkContext)).EqFee3Configuration(processInfo);
		(new ProcessTabPageCM(frameworkContext)).ClickOnContinueButton();
		orderSummaryInfo = OrderSummaryInfo.loadFromDatatable(dataTable);
		(new OrderSummaryTabCMPage(frameworkContext)).SubmitOrder(orderSummaryInfo);
		(new OrderSummaryTabCMPage(frameworkContext)).mrcNrc_Value(orderSummaryInfo);
		(new OrderSummaryTabCMPage(frameworkContext)).ClickSubmitOrderButton();
		(new OrderSummaryTabCMPage(frameworkContext)).NavigateToCSO(orderSummaryInfo);
		(new WorkOrderTabPageCSO(frameworkContext)).SearchForOrderInSO(SRID);
	}
	
	@Test
	public void EDI_New_Connect_Local_Biller_Billertype_CSG() throws InterruptedException, AWTException{
		
		String Site1;
		customerInfo = CustomerInfo.loadFromDatatable(dataTable);
		(new CustomerTabPageCM(frameworkContext)).createCustomer(customerInfo);
		accountInfo = AccountInfo.loadFromDatatable(dataTable);
		(new AccountTabPageCM(frameworkContext)).CreateServiceAccount(accountInfo);
		contactInfo = ContactInfo.loadFromDatatable(dataTable);
		(new ContactTabPageCM(frameworkContext)).CreateAccountPrimaryContact(contactInfo);
		(new ContactTabPageCM(frameworkContext)).ClickOnBackBtn();
		(new AccountTabPageCM(frameworkContext)).CreateBillingAccount(accountInfo);
		(new ContactTabPageCM(frameworkContext)).CreateBillingContact(contactInfo);
		(new ContactTabPageCM(frameworkContext)).ClickOnBackBtn();
		siteInfo = SiteInfo.loadFromDatatable(dataTable);
		(new AddressTabPageCM(frameworkContext)).ClickAddressTab(siteInfo);
		Site1 = (new AddressTabPageCM(frameworkContext)).EnterSiteDetailsValid(siteInfo);
		(new ContactTabPageCM(frameworkContext)).CreateSiteTechnicalContact(contactInfo);
		(new ServiceTabPageCM(frameworkContext)).ClickOnServiceTab();
		(new ServiceTabPageCM(frameworkContext)).SelectPricePlan();
		(new ServiceTabPageCM(frameworkContext)).EDI();
		(new ServiceTabPageCM(frameworkContext)).EquipmentFee(serviceInfo);
		(new ServiceTabPageCM(frameworkContext)).ClickOnContinueButton();
		(new FeatureTabPageCM(frameworkContext)).ClickOnContinueButton();
		processInfo = ProcessInfo.loadFromDatatable(dataTable);
		SRID = (new ProcessTabPageCM(frameworkContext)).ProcessConfiguration(processInfo);
		(new ProcessTabPageCM(frameworkContext)).UNIConfiguration(processInfo, Site1);
		(new ProcessTabPageCM(frameworkContext)).EVCConfiguration_EDI(processInfo);
		(new ProcessTabPageCM(frameworkContext)).EqFeeConfiguration(processInfo);
		(new ProcessTabPageCM(frameworkContext)).ClickOnContinueButton();
		orderSummaryInfo = OrderSummaryInfo.loadFromDatatable(dataTable);
		//(new OrderSummaryTabCMPage(frameworkContext)).assignLabel(orderSummaryInfo);
		(new OrderSummaryTabCMPage(frameworkContext)).enterOrderDetails(orderSummaryInfo);
		(new OrderSummaryTabCMPage(frameworkContext)).mrcNrc_Value(orderSummaryInfo);
		(new OrderSummaryTabCMPage(frameworkContext)).ClickSubmitOrderButton();
		(new OrderSummaryTabCMPage(frameworkContext)).NavigateToCSO(orderSummaryInfo);
		(new WorkOrderTabPageCSO(frameworkContext)).SearchForOrderInSO(SRID);
		(new WorkOrderTabPageCSO(frameworkContext)).ClickFirstSiteFlow();
		siteLevelTaskInfo = SiteLevelTaskInfo.loadFromDatatable(dataTable);
		(new SiteLevelTasks(frameworkContext)).ConductSiteSurvey();
		(new ConductSiteSurveyTaskPage(frameworkContext)).ConductSiteSurvey(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).ObtainSiteAgreement();
		(new ObtainSiteAgreementTaskPage(frameworkContext)).ObtainSiteAgreement(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).ConductFiberPlantSurvey();
		(new ConductFiberPlantSurveyTaskPage(frameworkContext)).ConductFiberPlantSurvey(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).BuildHouseAccount();
		(new BuildHouseAccountTaskPage(frameworkContext)).BuildHouseAccount(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).CompleteWavelengthReservation();
		(new CompleteWavelengthReservationTaskPage(frameworkContext)).CompleteWavelengthReservation(siteLevelTaskInfo);
		(new SiteLevelTasks(frameworkContext)).CompleteSiteBuild();
		(new CompleteSiteBuildTaskPage(frameworkContext)).ClickCompleteButton();
		(new CompleteSiteBuildTaskPage(frameworkContext)).closePopup();
		(new SiteLevelTasks(frameworkContext)).ObtainFiberPlantPermits();
		(new ObtainFiberPlantPermitsTaskPage(frameworkContext)).ObtainFiberPlantPermits();
		(new SiteLevelTasks(frameworkContext)).CompleteFiberPlantBuild();
		(new CompleteFiberPlantBuildTaskPage(frameworkContext)).ClickCompleteButton();
		(new CompleteFiberPlantBuildTaskPage(frameworkContext)).closePopup();
		(new ServiceLevelTasks(frameworkContext)).ContactCustomer();
		(new ContactCustomerTaskPage(frameworkContext)).ContactCustomer();
		(new ServiceLevelTasks(frameworkContext)).UpdateDesign();
		(new UpdateDesignTaskPage(frameworkContext)).UpdateDesign();
		(new SiteLevelTasks(frameworkContext)).ClickBackButton();
		(new WorkOrderTabPageCSO(frameworkContext)).ClickServiceFlow();
		
	}

	@Test
	public void New_Connect_BVE_New_connect() throws InterruptedException, AWTException {

		// (new HomePageCM(browser,report)).NavigateToServiceTab();
		String Site1;
		customerInfo = CustomerInfo.loadFromDatatable(dataTable);
		(new CustomerTabPageCM(frameworkContext)).customerInformation(customerInfo);
		(new CustomerTabPageCM(frameworkContext)).addressInformationValid(customerInfo);
		accountInfo = AccountInfo.loadFromDatatable(dataTable);
		(new AccountTabPageCM(frameworkContext)).CreateServiceAccount(accountInfo);
		contactInfo = ContactInfo.loadFromDatatable(dataTable);
		(new ContactTabPageCM(frameworkContext)).CreateAccountPrimaryContact(contactInfo);
		(new ContactTabPageCM(frameworkContext)).ClickOnBackBtn();
		(new AccountTabPageCM(frameworkContext)).CreateBillingAccount(accountInfo);
		siteInfo = SiteInfo.loadFromDatatable(dataTable);
		Site1 = (new AddressTabPageCM(frameworkContext)).EnterSiteDetailsValid(siteInfo);
		(new ContactTabPageCM(frameworkContext)).CreateSiteTechnicalContact(contactInfo);
		(new ContactTabPageCM(frameworkContext)).CreateShippingContact(contactInfo);
		(new ContactTabPageCM(frameworkContext)).ClickOnBackBtn();
		(new AccountTabPageCM(frameworkContext)).CreateSecondBillingAccount(accountInfo);
		(new ServiceTabPageCM(frameworkContext)).ClickOnServiceTab();
		(new ServiceTabPageCM(frameworkContext)).SelectPricePlan();
		(new ServiceTabPageCM(frameworkContext)).BVE();
		(new ServiceTabPageCM(frameworkContext)).ClickOnContinueButton();
		(new FeatureTabPageCM(frameworkContext)).BVE();
		(new EquipmentTabPageCM(frameworkContext)).equipmentConfiguration();

	}

	
	
	@Test
	public void Survey() throws InterruptedException {
		orderSummaryInfo = OrderSummaryInfo.loadFromDatatable(dataTable);
		(new OrderSummaryTabCMPage(frameworkContext)).NavigateToCSO(orderSummaryInfo);
		System.out.println("Survey");
		(new SurveyTabPageCM(frameworkContext)).CreateSiteForSurvey();
		contactInfo = ContactInfo.loadFromDatatable(dataTable);
		(new ContactTabPageCM(frameworkContext)).CreateSiteTechnicalContact(contactInfo);
		(new ContactTabPageCM(frameworkContext)).ClickOnBackBtn();
		SurveyID = (new SurveyTabPageCM(frameworkContext)).SubmitSurveyRequest();
		
		 * orderSummaryInfo = OrderSummaryInfo.loadFromDatatable(dataTable);
		 * (new
		 * OrderSummaryTabCMPage(browser,report)).NavigateToCSO(orderSummaryInfo
		 * ); (new WorkOrderTabPageCSO(browser,
		 * report)).SearchForSurveyInPreSales(SurveyID); (new
		 * WorkOrderTabPageCSO(frameworkContext)).ClickWLAdvancedSearch(); (new
		 * WorkOrderTabPageCSO(frameworkContext)).ClickAllTask(); (new
		 * PresalesTasksPageCSO(browser,report)).ConductCoaxSurvey();
		 
	}

	
	
	@After
	public void closeApplication() throws IOException, Exception{
		centuryApplication = new CenturyApplication(frameworkContext);
		centuryApplication.close();
	}

	

}
*/