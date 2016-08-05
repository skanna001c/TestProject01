package com.comcast.century.newConnect;


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
	private ProcessInfo processInfo;
	private OrderSummaryInfo orderSummaryInfo;
	private SiteLevelTaskInfo siteLevelTaskInfo;
	private ServiceLevelTaskInfo serviceLevelTaskInfo;
	String SRID;
	String SurveyID;

	@BeforeMethod
	public void openApplication() throws Exception {
		LoginDetails loginInfo = LoginDetails.loadFromDatatable(dataTable);
		centuryApplication = new CenturyApplication(browser, report);
		centuryApplication.openUrl(loginInfo);
	}



	@Test
	public void New_Connect_ME_EDI_New_connect() throws InterruptedException, AWTException {
      
	
		
		String Site1;
		customerInfo = CustomerInfo.loadFromDatatable(dataTable);
		(new CustomerTabPageCM(browser, report)).createCustomer(customerInfo);
		accountInfo = AccountInfo.loadFromDatatable(dataTable);
		(new AccountTabPageCM(browser, report)).CreateServiceAccount(accountInfo);
		contactInfo = ContactInfo.loadFromDatatable(dataTable);
		(new ContactTabPageCM(browser, report)).CreateAccountPrimaryContact(contactInfo);
		(new ContactTabPageCM(browser, report)).ClickOnBackBtn();
		(new AccountTabPageCM(browser, report)).CreateBiilingAccount(accountInfo);
		(new ContactTabPageCM(browser, report)).CreateBillingContact(contactInfo);
		(new ContactTabPageCM(browser, report)).ClickOnBackBtn();
		siteInfo = SiteInfo.loadFromDatatable(dataTable);
		(new AddressTabPageCM(browser, report)).ClickAddressTab(siteInfo);
		Site1 = (new AddressTabPageCM(browser, report)).EnterSiteDetailsValid(siteInfo);
		(new ContactTabPageCM(browser, report)).CreateSiteTechnicalContact(contactInfo);
		(new ServiceTabPageCM(browser, report)).ClickOnServiceTab();
		(new ServiceTabPageCM(browser, report)).SelectPricePlan();
		(new ServiceTabPageCM(browser, report)).EDI();
		(new ServiceTabPageCM(browser, report)).EquipmentFee();
		(new ServiceTabPageCM(browser, report)).ClickOnContinueButton();
		(new FeatureTabPageCM(browser, report)).ClickOnContinueButton();
		processInfo = ProcessInfo.loadFromDatatable(dataTable);
		SRID = (new ProcessTabPageCM(browser, report)).ProcessConfiguration(processInfo);
		(new ProcessTabPageCM(browser, report)).UNIConfiguration(processInfo, Site1);
		(new ProcessTabPageCM(browser, report)).EVCConfiguration_EDI(processInfo);
		(new ProcessTabPageCM(browser, report)).EqFeeConfiguration(processInfo);
		(new ProcessTabPageCM(browser, report)).ClickOnContinueButton();
		orderSummaryInfo = OrderSummaryInfo.loadFromDatatable(dataTable);
		//(new OrderSummaryTabCMPage(browser, report)).assignLabel(orderSummaryInfo);
		(new OrderSummaryTabCMPage(browser, report)).SubmitOrder(orderSummaryInfo);
		(new OrderSummaryTabCMPage(browser, report)).mrcNrc_Value(orderSummaryInfo);
		(new OrderSummaryTabCMPage(browser, report)).ClickSubmitOrderButton();
		(new OrderSummaryTabCMPage(browser, report)).NavigateToCSO(orderSummaryInfo);
		(new WorkOrderTabPageCSO(browser, report)).SearchForOrderInSO(SRID);
		(new WorkOrderTabPageCSO(browser, report)).ClickFirstSiteFlow();
		siteLevelTaskInfo = SiteLevelTaskInfo.loadFromDatatable(dataTable);
		(new SiteLevelTasks(browser, report)).ConductSiteSurvey();
		(new ConductSiteSurveyTaskPage(browser, report)).ConductSiteSurvey(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).ObtainSiteAgreement();
		(new ObtainSiteAgreementTaskPage(browser, report)).ObtainSiteAgreement(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).ConductFiberPlantSurvey();
		(new ConductFiberPlantSurveyTaskPage(browser, report)).ConductFiberPlantSurvey(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).BuildHouseAccount();
		(new BuildHouseAccountTaskPage(browser, report)).BuildHouseAccount(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).CompleteWavelengthReservation();
		(new CompleteWavelengthReservationTaskPage(browser, report)).CompleteWavelengthReservation(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).CompleteSiteBuild();
		(new CompleteSiteBuildTaskPage(browser, report)).ClickCompleteButton();
		(new CompleteSiteBuildTaskPage(browser, report)).closePopup();
		(new SiteLevelTasks(browser, report)).ObtainFiberPlantPermits();
		(new ObtainFiberPlantPermitsTaskPage(browser, report)).ObtainFiberPlantPermits();
		(new SiteLevelTasks(browser, report)).CompleteFiberPlantBuild();
		(new CompleteFiberPlantBuildTaskPage(browser, report)).ClickCompleteButton();
		(new CompleteFiberPlantBuildTaskPage(browser, report)).closePopup();
		(new ServiceLevelTasks(browser, report)).ContactCustomer();
		(new ContactCustomerTaskPage(browser, report)).ContactCustomer();
		(new ServiceLevelTasks(browser, report)).UpdateDesign();
		(new UpdateDesignTaskPage(browser, report)).UpdateDesign();
		(new SiteLevelTasks(browser, report)).ClickBackButton();
		(new WorkOrderTabPageCSO(browser, report)).ClickEDIFlow();
		serviceLevelTaskInfo = ServiceLevelTaskInfo.loadFromDatatable(dataTable);
		(new ServiceLevelTasks(browser, report)).BULBA();
		(new BULBATaskPage(browser, report)).BULBA(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).ShipCPE();
		(new ShipCPETaskPage(browser, report)).ShipCPE(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).CAE();
		(new CAETaskPage(browser, report)).CAE(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).ADI();
		(new ADITaskPage(browser, report)).ADI(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).GenerateCoreConfigs();
		new GenerateCoreConfigsTaskPage(browser, report).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).GenerateCPEConfigs();
		(new GenerateCPEConfigsTaskPage(browser, report)).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).LoadCoreConfigs();
		(new LoadCoreConfigsTaskPage(browser, report)).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).InstallCPE();
		(new InstallCPETaskPage(browser, report)).InstallCPE();
		(new ServiceLevelTasks(browser, report)).SetCriticalDates();
		(new SetCriticalDatesTaskPage(browser, report)).SetCriticalDates();
		(new ServiceLevelTasks(browser, report)).DayofConfigs();
		(new DaysOfConfigsTaskPage(browser, report)).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).ActivateService();
		(new ActivateServiceTaskPage(browser, report)).ActivateService(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).NotifyCustomerofServiceInstallation();
		(new NotifyCustomerofServiceInstallationTaskPage(browser, report)).NotifyCustomerofServiceInstallation();
		(new ServiceLevelTasks(browser, report)).CCAT();
		(new CCATTaskPage(browser, report)).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).StartBilling();
		(new ServiceLevelTasks(browser, report)).ClickBackButton();


	}

	
	@Test
	public void New_Connect_ME_EDI_BGP_New_connect() throws InterruptedException, AWTException {

		String Site1;
		customerInfo = CustomerInfo.loadFromDatatable(dataTable);
		(new CustomerTabPageCM(browser, report)).createCustomer(customerInfo);
		accountInfo = AccountInfo.loadFromDatatable(dataTable);
		(new AccountTabPageCM(browser, report)).CreateServiceAccount(accountInfo);
		contactInfo = ContactInfo.loadFromDatatable(dataTable);
		(new ContactTabPageCM(browser, report)).CreateAccountPrimaryContact(contactInfo);
		(new ContactTabPageCM(browser, report)).ClickOnBackBtn();
		(new AccountTabPageCM(browser, report)).CreateBiilingAccount(accountInfo);
		(new ContactTabPageCM(browser, report)).CreateBillingContact(contactInfo);
		(new ContactTabPageCM(browser, report)).ClickOnBackBtn();
		siteInfo = SiteInfo.loadFromDatatable(dataTable);
		(new AddressTabPageCM(browser, report)).ClickAddressTab(siteInfo);
		Site1 = (new AddressTabPageCM(browser, report)).EnterSiteDetailsValid(siteInfo);
		(new ContactTabPageCM(browser, report)).CreateSiteTechnicalContact(contactInfo);
		(new ServiceTabPageCM(browser, report)).ClickOnServiceTab();
		(new ServiceTabPageCM(browser, report)).SelectPricePlan();
		(new ServiceTabPageCM(browser, report)).EDI();
		(new ServiceTabPageCM(browser, report)).EquipmentFee();
		(new ServiceTabPageCM(browser, report)).ClickOnContinueButton();
		(new FeatureTabPageCM(browser, report)).BGP();
		(new FeatureTabPageCM(browser, report)).ClickOnContinueButton();
		processInfo = ProcessInfo.loadFromDatatable(dataTable);
		SRID = (new ProcessTabPageCM(browser, report)).ProcessConfiguration(processInfo);
		(new ProcessTabPageCM(browser, report)).UNIConfiguration(processInfo, Site1);
		(new ProcessTabPageCM(browser, report)).EVCConfiguration_EDI(processInfo);
		(new ProcessTabPageCM(browser, report)).BGPConfiguration();
		(new ProcessTabPageCM(browser, report)).EqFeeConfiguration(processInfo);
		(new ProcessTabPageCM(browser, report)).ClickOnContinueButton();
		orderSummaryInfo = OrderSummaryInfo.loadFromDatatable(dataTable);
		(new OrderSummaryTabCMPage(browser, report)).SubmitOrder(orderSummaryInfo);
		(new OrderSummaryTabCMPage(browser, report)).mrcNrc_Value(orderSummaryInfo);
		(new OrderSummaryTabCMPage(browser, report)).ClickSubmitOrderButton();
		(new OrderSummaryTabCMPage(browser, report)).NavigateToCSO(orderSummaryInfo);
		(new WorkOrderTabPageCSO(browser, report)).SearchForOrderInSO(SRID);
		(new WorkOrderTabPageCSO(browser, report)).ClickFirstSiteFlow();
		siteLevelTaskInfo = SiteLevelTaskInfo.loadFromDatatable(dataTable);
		(new SiteLevelTasks(browser, report)).ConductSiteSurvey();
		(new ConductSiteSurveyTaskPage(browser, report)).ConductSiteSurvey(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).ObtainSiteAgreement();
		(new ObtainSiteAgreementTaskPage(browser, report)).ObtainSiteAgreement(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).ConductFiberPlantSurvey();
		(new ConductFiberPlantSurveyTaskPage(browser, report)).ConductFiberPlantSurvey(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).BuildHouseAccount();
		(new BuildHouseAccountTaskPage(browser, report)).BuildHouseAccount(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).CompleteWavelengthReservation();
		(new CompleteWavelengthReservationTaskPage(browser, report)).CompleteWavelengthReservation(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).CompleteSiteBuild();
		(new CompleteSiteBuildTaskPage(browser, report)).ClickCompleteButton();
		(new CompleteSiteBuildTaskPage(browser, report)).closePopup();
		(new SiteLevelTasks(browser, report)).ObtainFiberPlantPermits();
		(new ObtainFiberPlantPermitsTaskPage(browser, report)).ObtainFiberPlantPermits();
		(new SiteLevelTasks(browser, report)).CompleteFiberPlantBuild();
		(new CompleteFiberPlantBuildTaskPage(browser, report)).ClickCompleteButton();
		(new CompleteFiberPlantBuildTaskPage(browser, report)).closePopup();
		(new ServiceLevelTasks(browser, report)).ContactCustomer();
		(new ContactCustomerTaskPage(browser, report)).ContactCustomer();
		(new ServiceLevelTasks(browser, report)).UpdateDesign();
		(new UpdateDesignTaskPage(browser, report)).UpdateDesign();
		(new SiteLevelTasks(browser, report)).ClickBackButton();
		(new WorkOrderTabPageCSO(browser, report)).ClickEDIFlow();
		serviceLevelTaskInfo = ServiceLevelTaskInfo.loadFromDatatable(dataTable);
		(new ServiceLevelTasks(browser, report)).BULBA();
		(new BULBATaskPage(browser, report)).BULBA(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).ShipCPE();
		(new ShipCPETaskPage(browser, report)).ShipCPE(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).CAE();
		(new CAETaskPage(browser, report)).CAE(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).ADI();
		(new ADITaskPage(browser, report)).ADI(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).GenerateCoreConfigs();
		new GenerateCoreConfigsTaskPage(browser, report).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).GenerateCPEConfigs();
		(new GenerateCPEConfigsTaskPage(browser, report)).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).LoadCoreConfigs();
		(new LoadCoreConfigsTaskPage(browser, report)).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).InstallCPE();
		(new InstallCPETaskPage(browser, report)).InstallCPE();
		(new ServiceLevelTasks(browser, report)).SetCriticalDates();
		(new SetCriticalDatesTaskPage(browser, report)).SetCriticalDates();
		(new ServiceLevelTasks(browser, report)).DayofConfigs();
		(new DaysOfConfigsTaskPage(browser, report)).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).ActivateService();
		(new ActivateServiceTaskPage(browser, report)).ActivateService(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).NotifyCustomerofServiceInstallation();
		(new NotifyCustomerofServiceInstallationTaskPage(browser, report)).NotifyCustomerofServiceInstallation();
		(new ServiceLevelTasks(browser, report)).CCAT();
		(new CCATTaskPage(browser, report)).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).StartBilling();
		(new ServiceLevelTasks(browser, report)).ClickBackButton();
		(new WorkOrderTabPageCSO(browser, report)).ClickEquipmentFeeFlow();
		(new EqFeeFlowTasks(browser, report)).EqFeeStartBilling();
		(new EqFeeStartBillingTaskPage(browser, report)).EqFeeStartBilling();
		(new EqFeeFlowTasks(browser, report)).ClickBackButton();
		(new WorkOrderTabPageCSO(browser, report)).ClickBGPFlow();
		(new BGPTasks(browser, report)).assignDesignBGP();
		(new AssignDesignBGPTaskPage(browser, report)).ClickCompleteButton();
		(new BGPTasks(browser, report)).genearteCPEConfigs();
		(new GenerateCPEConfigsTaskPage(browser, report)).ClickCompleteButton();
		(new BGPTasks(browser, report)).setCriticalDates();
		(new SCDBGPTaskPage(browser, report)).setCriticalDates();
		(new BGPTasks(browser, report)).dayOfConfigs();
		(new DaysOfConfigsTaskPage(browser, report)).ClickCompleteButton();
		(new BGPTasks(browser, report)).notifyCustomerOfSI();
		(new NotifyCustomerofServiceInstallationTaskPage(browser, report)).NotifyCustomerofServiceInstallation();
		(new BGPTasks(browser, report)).startBilling();
		(new BGPTasks(browser, report)).ClickBackButton();

	}

	@Test
	public void New_Connect_ME_EDI_ERATE_New_connect() throws InterruptedException, AWTException {

		String Site1;
		customerInfo = CustomerInfo.loadFromDatatable(dataTable);
		(new CustomerTabPageCM(browser, report)).createCustomer(customerInfo);
		accountInfo = AccountInfo.loadFromDatatable(dataTable);
		(new AccountTabPageCM(browser, report)).CreateErateServiceAccount(accountInfo);
		contactInfo = ContactInfo.loadFromDatatable(dataTable);
		(new ContactTabPageCM(browser, report)).CreateAccountPrimaryContact(contactInfo);
		(new ContactTabPageCM(browser, report)).ClickOnBackBtn();
		(new AccountTabPageCM(browser, report)).CreateBiilingAccount(accountInfo);
		siteInfo = SiteInfo.loadFromDatatable(dataTable);
		(new AddressTabPageCM(browser, report)).ClickAddressTab(siteInfo);
		Site1 = (new AddressTabPageCM(browser, report)).EnterSiteDetailsInvalid(siteInfo);
		(new ContactTabPageCM(browser, report)).CreateSiteTechnicalContact(contactInfo);
		(new ServiceTabPageCM(browser, report)).ClickOnServiceTab();
		(new ServiceTabPageCM(browser, report)).SelectPricePlan();
		(new ServiceTabPageCM(browser, report)).EDI();
		(new ServiceTabPageCM(browser, report)).EquipmentFee();
		(new ServiceTabPageCM(browser, report)).ClickOnContinueButton();
		(new FeatureTabPageCM(browser, report)).ClickOnContinueButton();
		processInfo = ProcessInfo.loadFromDatatable(dataTable);
		SRID = (new ProcessTabPageCM(browser, report)).ProcessConfiguration(processInfo);
		(new ProcessTabPageCM(browser, report)).UNIConfiguration(processInfo, Site1);
		(new ProcessTabPageCM(browser, report)).EVCConfiguration_EDI(processInfo);
		(new ProcessTabPageCM(browser, report)).EqFeeConfiguration(processInfo);
		(new ProcessTabPageCM(browser, report)).ClickOnContinueButton();
		orderSummaryInfo = OrderSummaryInfo.loadFromDatatable(dataTable);
		(new OrderSummaryTabCMPage(browser, report)).SubmitOrder(orderSummaryInfo);
		(new OrderSummaryTabCMPage(browser, report)).mrcNrc_Value(orderSummaryInfo);
		(new OrderSummaryTabCMPage(browser, report)).Attachments(orderSummaryInfo);
		(new OrderSummaryTabCMPage(browser, report)).ClickSubmitOrderButton();
		(new OrderSummaryTabCMPage(browser, report)).NavigateToCSO(orderSummaryInfo);
		(new WorkOrderTabPageCSO(browser, report)).SearchForOrderInSO(SRID);
		(new WorkOrderTabPageCSO(browser, report)).ClickFirstSiteFlow();
		siteLevelTaskInfo = SiteLevelTaskInfo.loadFromDatatable(dataTable);
		(new SiteLevelTasks(browser, report)).ConductSiteSurvey();
		(new ConductSiteSurveyTaskPage(browser, report)).ConductSiteSurvey(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).ObtainSiteAgreement();
		(new ObtainSiteAgreementTaskPage(browser, report)).ObtainSiteAgreement(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).ConductFiberPlantSurvey();
		(new ConductFiberPlantSurveyTaskPage(browser, report)).ConductFiberPlantSurvey(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).BuildHouseAccount();
		(new BuildHouseAccountTaskPage(browser, report)).BuildHouseAccount(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).CompleteWavelengthReservation();
		(new CompleteWavelengthReservationTaskPage(browser, report)).CompleteWavelengthReservation(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).CompleteSiteBuild();
		(new CompleteSiteBuildTaskPage(browser, report)).ClickCompleteButton();
		(new CompleteSiteBuildTaskPage(browser, report)).closePopup();
		(new SiteLevelTasks(browser, report)).ObtainFiberPlantPermits();
		(new ObtainFiberPlantPermitsTaskPage(browser, report)).ObtainFiberPlantPermits();
		(new SiteLevelTasks(browser, report)).CompleteFiberPlantBuild();
		(new CompleteFiberPlantBuildTaskPage(browser, report)).ClickCompleteButton();
		(new CompleteFiberPlantBuildTaskPage(browser, report)).closePopup();
		(new ServiceLevelTasks(browser, report)).ContactCustomer();
		(new ContactCustomerTaskPage(browser, report)).ContactCustomer();
		(new ServiceLevelTasks(browser, report)).UpdateDesign();
		(new UpdateDesignTaskPage(browser, report)).UpdateDesign();
		(new SiteLevelTasks(browser, report)).ClickBackButton();
		(new WorkOrderTabPageCSO(browser, report)).ClickEDIFlow();
		serviceLevelTaskInfo = ServiceLevelTaskInfo.loadFromDatatable(dataTable);
		(new ServiceLevelTasks(browser, report)).BULBA();
		(new BULBATaskPage(browser, report)).BULBA(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).ShipCPE();
		(new ShipCPETaskPage(browser, report)).ShipCPE(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).CAE();
		(new CAETaskPage(browser, report)).CAE(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).ADI();
		(new ADITaskPage(browser, report)).ADI(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).GenerateCoreConfigs();
		new GenerateCoreConfigsTaskPage(browser, report).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).GenerateCPEConfigs();
		(new GenerateCPEConfigsTaskPage(browser, report)).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).LoadCoreConfigs();
		(new LoadCoreConfigsTaskPage(browser, report)).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).InstallCPE();
		(new InstallCPETaskPage(browser, report)).InstallCPE();
		(new ServiceLevelTasks(browser, report)).SetCriticalDates();
		(new SetCriticalDatesTaskPage(browser, report)).SetCriticalDates();
		(new ServiceLevelTasks(browser, report)).DayofConfigs();
		(new DaysOfConfigsTaskPage(browser, report)).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).ActivateService();
		(new ActivateServiceTaskPage(browser, report)).ActivateService(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).NotifyCustomerofServiceInstallation();
		(new NotifyCustomerofServiceInstallationTaskPage(browser, report)).NotifyCustomerofServiceInstallation();
		(new ServiceLevelTasks(browser, report)).CCAT();
		(new CCATTaskPage(browser, report)).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).StartBilling();
		(new ServiceLevelTasks(browser, report)).ClickBackButton();
		(new WorkOrderTabPageCSO(browser, report)).ClickEquipmentFeeFlow();
		(new EqFeeFlowTasks(browser, report)).EqFeeStartBilling();
		(new EqFeeStartBillingTaskPage(browser, report)).EqFeeStartBilling();
		(new EqFeeFlowTasks(browser, report)).ClickBackButton();

	}

	@Test
	public void New_Connect_ME_EDI_ToF_New_connect() throws InterruptedException, AWTException {

		
		String Site1;
		customerInfo = CustomerInfo.loadFromDatatable(dataTable);
		(new CustomerTabPageCM(browser, report)).createCustomer(customerInfo);
		accountInfo = AccountInfo.loadFromDatatable(dataTable);
		(new AccountTabPageCM(browser, report)).CreateServiceAccount(accountInfo);
		contactInfo = ContactInfo.loadFromDatatable(dataTable);
		(new ContactTabPageCM(browser, report)).CreateAccountPrimaryContact(contactInfo);
		(new ContactTabPageCM(browser, report)).ClickOnBackBtn();
		(new AccountTabPageCM(browser, report)).CreateBiilingAccount(accountInfo);
		(new ContactTabPageCM(browser, report)).CreateBillingContact(contactInfo);
		(new ContactTabPageCM(browser, report)).ClickOnBackBtn();
		siteInfo = SiteInfo.loadFromDatatable(dataTable);
		(new AddressTabPageCM(browser, report)).ClickAddressTab(siteInfo);
		Site1 = (new AddressTabPageCM(browser, report)).EnterSiteDetailsValid(siteInfo);
		(new ContactTabPageCM(browser, report)).CreateSiteTechnicalContact(contactInfo);
		(new ServiceTabPageCM(browser, report)).ClickOnServiceTab();
		(new ServiceTabPageCM(browser, report)).SelectPricePlan();
		(new ServiceTabPageCM(browser, report)).EDI();
		(new ServiceTabPageCM(browser, report)).EquipmentFee();
		(new ServiceTabPageCM(browser, report)).TrunkPRI();
		(new ServiceTabPageCM(browser, report)).ClickOnContinueButton();
		(new FeatureTabPageCM(browser, report)).ClickOnContinueButton();
		processInfo = ProcessInfo.loadFromDatatable(dataTable);
		SRID = (new ProcessTabPageCM(browser, report)).ProcessConfiguration(processInfo);
		(new ProcessTabPageCM(browser, report)).UNIConfiguration(processInfo, Site1);
		(new ProcessTabPageCM(browser, report)).EVCConfiguration_EDI(processInfo);
		(new ProcessTabPageCM(browser, report)).EqFeeConfiguration(processInfo);
		(new ProcessTabPageCM(browser, report)).Trunk_PRI(processInfo);
		(new ProcessTabPageCM(browser, report)).UNIConfiguration_PRI(processInfo, Site1);
		(new ProcessTabPageCM(browser, report)).EVCConfiguration_PRI(processInfo);
		(new ProcessTabPageCM(browser, report)).ClickOnContinueButton();
		orderSummaryInfo = OrderSummaryInfo.loadFromDatatable(dataTable);
		(new OrderSummaryTabCMPage(browser, report)).SubmitOrder(orderSummaryInfo);
		(new OrderSummaryTabCMPage(browser, report)).mrcNrc_Value(orderSummaryInfo);
		(new OrderSummaryTabCMPage(browser, report)).ClickSubmitOrderButton();
		(new OrderSummaryTabCMPage(browser, report)).NavigateToCSO(orderSummaryInfo);
		(new WorkOrderTabPageCSO(browser, report)).SearchForOrderInSO(SRID);
		(new WorkOrderTabPageCSO(browser, report)).ClickFirstSiteFlow();
		siteLevelTaskInfo = SiteLevelTaskInfo.loadFromDatatable(dataTable);
		(new SiteLevelTasks(browser, report)).ConductSiteSurvey();
		(new ConductSiteSurveyTaskPage(browser, report)).ConductSiteSurvey(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).ObtainSiteAgreement();
		(new ObtainSiteAgreementTaskPage(browser, report)).ObtainSiteAgreement(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).ConductFiberPlantSurvey();
		(new ConductFiberPlantSurveyTaskPage(browser, report)).ConductFiberPlantSurvey(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).BuildHouseAccount();
		(new BuildHouseAccountTaskPage(browser, report)).BuildHouseAccount(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).CompleteWavelengthReservation();
		(new CompleteWavelengthReservationTaskPage(browser, report)).CompleteWavelengthReservation(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).CompleteSiteBuild();
		(new CompleteSiteBuildTaskPage(browser, report)).ClickCompleteButton();
		(new CompleteSiteBuildTaskPage(browser, report)).closePopup();
		(new SiteLevelTasks(browser, report)).ObtainFiberPlantPermits();
		(new ObtainFiberPlantPermitsTaskPage(browser, report)).ObtainFiberPlantPermits();
		(new SiteLevelTasks(browser, report)).CompleteFiberPlantBuild();
		(new CompleteFiberPlantBuildTaskPage(browser, report)).ClickCompleteButton();
		(new CompleteFiberPlantBuildTaskPage(browser, report)).closePopup();
		(new ServiceLevelTasks(browser, report)).ContactCustomer();
		(new ContactCustomerTaskPage(browser, report)).ContactCustomer();
		(new ServiceLevelTasks(browser, report)).UpdateDesign();
		(new UpdateDesignTaskPage(browser, report)).UpdateDesign();
		(new SiteLevelTasks(browser, report)).ClickBackButton();
		(new WorkOrderTabPageCSO(browser, report)).ClickEDIFlow();
		serviceLevelTaskInfo = ServiceLevelTaskInfo.loadFromDatatable(dataTable);
		(new ServiceLevelTasks(browser, report)).BULBA();
		(new BULBATaskPage(browser, report)).BULBA(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).ShipCPE();
		(new ShipCPETaskPage(browser, report)).ShipCPE(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).CAE();
		(new CAETaskPage(browser, report)).CAE(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).ADI();
		(new ADITaskPage(browser, report)).ADI(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).GenerateCoreConfigs();
		new GenerateCoreConfigsTaskPage(browser, report).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).GenerateCPEConfigs();
		(new GenerateCPEConfigsTaskPage(browser, report)).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).LoadCoreConfigs();
		(new LoadCoreConfigsTaskPage(browser, report)).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).InstallCPE();
		(new InstallCPETaskPage(browser, report)).InstallCPE();
		(new ServiceLevelTasks(browser, report)).SetCriticalDates();
		(new SetCriticalDatesTaskPage(browser, report)).SetCriticalDates();
		(new ServiceLevelTasks(browser, report)).DayofConfigs();
		(new DaysOfConfigsTaskPage(browser, report)).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).ActivateService();
		(new ActivateServiceTaskPage(browser, report)).ActivateService(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).NotifyCustomerofServiceInstallation();
		(new NotifyCustomerofServiceInstallationTaskPage(browser, report)).NotifyCustomerofServiceInstallation();
		(new ServiceLevelTasks(browser, report)).CCAT();
		(new CCATTaskPage(browser, report)).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).StartBilling();
		(new ServiceLevelTasks(browser, report)).ClickBackButton();
		//(new WorkOrderTabPageCSO(browser, report)).ClickEquipmentFeeFlow();
		//(new EqFeeFlowTasks(browser, report)).EqFeeStartBilling();
		//(new EqFeeStartBillingTaskPage(browser, report)).EqFeeStartBilling();
		//(new EqFeeFlowTasks(browser, report)).ClickBackButton();
		(new WorkOrderTabPageCSO(browser, report)).ClickTrunkPRIFlow();
		(new ServiceLevelTasks(browser, report)).BULBA();
		(new BULBATaskPage(browser, report)).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).CAE();
		(new CAETaskPage(browser, report)).cAE_PRI(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).ShipCPE();
		(new ShipCPETaskPage(browser, report)).ShipCPE(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).ADI();
		(new ADITaskPage(browser, report)).assignDesignInformationPRI(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).GenerateCPEConfigs();
		(new GenerateCPEConfigsTaskPage(browser, report)).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).GenerateCoreConfigs();
		(new GenerateCoreConfigsTaskPage(browser, report)).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).LoadCoreConfigs();
		(new LoadCoreConfigsTaskPage(browser, report)).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).InstallCPE();
		(new InstallCPETaskPage(browser, report)).installCPEPRI();
		(new ServiceLevelTasks(browser, report)).SetCriticalDates();
		(new SetCriticalDatesTaskPage(browser, report)).SetCriticalDates();
		(new ServiceLevelTasks(browser, report)).DayofConfigs();
		(new DaysOfConfigsTaskPage(browser, report)).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).ActivateService();
		(new ActivateServiceTaskPage(browser, report)).ActivateService(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).ClickBackButton();
		
		
	}

	@Test
	public void New_Connect_ME_ENS_New_connect() throws InterruptedException, AWTException {

		// (new HomePageCM(browser,report)).NavigateToServiceTab();
		String Site1, Site2;
		customerInfo = CustomerInfo.loadFromDatatable(dataTable);
		(new CustomerTabPageCM(browser, report)).createCustomer(customerInfo);
		accountInfo = AccountInfo.loadFromDatatable(dataTable);
		(new AccountTabPageCM(browser, report)).CreateServiceAccount(accountInfo);
		contactInfo = ContactInfo.loadFromDatatable(dataTable);
		(new ContactTabPageCM(browser, report)).CreateAccountPrimaryContact(contactInfo);
		(new ContactTabPageCM(browser, report)).ClickOnBackBtn();
		(new AccountTabPageCM(browser, report)).CreateBiilingAccount(accountInfo);
		(new ContactTabPageCM(browser, report)).CreateBillingContact(contactInfo);
		(new ContactTabPageCM(browser, report)).ClickOnBackBtn();
		siteInfo = SiteInfo.loadFromDatatable(dataTable);
		(new AddressTabPageCM(browser, report)).ClickAddressTab(siteInfo);
		Site1 = (new AddressTabPageCM(browser, report)).EnterSiteDetailsValid(siteInfo);
		(new ContactTabPageCM(browser, report)).CreateSiteTechnicalContact(contactInfo);
		(new ContactTabPageCM(browser, report)).ClickOnBackBtn();
		(new AddressTabPageCM(browser, report)).CreateNewAddress();
		Site2 = (new AddressTabPageCM(browser, report)).EnterSiteDetailsValid(siteInfo);
		(new ContactTabPageCM(browser, report)).CreateSiteTechnicalContact(contactInfo);
		(new ServiceTabPageCM(browser, report)).ClickOnServiceTab();
		(new ServiceTabPageCM(browser, report)).SelectPricePlan();
		(new ServiceTabPageCM(browser, report)).ENS();
		(new ServiceTabPageCM(browser, report)).EquipmentFee();
		(new ServiceTabPageCM(browser, report)).ClickOnContinueButton();
		(new FeatureTabPageCM(browser, report)).ENS();
		processInfo = ProcessInfo.loadFromDatatable(dataTable);
		SRID = (new ProcessTabPageCM(browser, report)).ProcessConfiguration(processInfo);
		(new ProcessTabPageCM(browser, report)).UNIConfiguration(processInfo, Site1);
		(new ProcessTabPageCM(browser, report)).UNI2Configuration(processInfo, Site2);
		(new ProcessTabPageCM(browser, report)).EVCConfiguration_ENS(processInfo);
		(new ProcessTabPageCM(browser, report)).EVC2Configuration_ENS(processInfo);
		(new ProcessTabPageCM(browser, report)).EqFeeConfiguration(processInfo);
		(new ProcessTabPageCM(browser, report)).EqFee2Configuration(processInfo);
		(new ProcessTabPageCM(browser, report)).ClickOnContinueButton();
		orderSummaryInfo = OrderSummaryInfo.loadFromDatatable(dataTable);
		(new OrderSummaryTabCMPage(browser, report)).assignLabel(orderSummaryInfo);
		(new OrderSummaryTabCMPage(browser, report)).SubmitOrder(orderSummaryInfo);
		(new OrderSummaryTabCMPage(browser, report)).mrcNrc_Value(orderSummaryInfo);
		(new OrderSummaryTabCMPage(browser, report)).ClickSubmitOrderButton();
		(new OrderSummaryTabCMPage(browser, report)).NavigateToCSO(orderSummaryInfo);
		(new WorkOrderTabPageCSO(browser, report)).SearchForOrderInSO(SRID);

	}

	@Test
	public void New_Connect_ME_EPL_New_connect() throws InterruptedException, AWTException {
	   
		
		String Site1, Site2;
		customerInfo = CustomerInfo.loadFromDatatable(dataTable);
		(new CustomerTabPageCM(browser, report)).createCustomer(customerInfo);
		accountInfo = AccountInfo.loadFromDatatable(dataTable);
		(new AccountTabPageCM(browser, report)).CreateServiceAccount(accountInfo);
		contactInfo = ContactInfo.loadFromDatatable(dataTable);
		(new ContactTabPageCM(browser, report)).CreateAccountPrimaryContact(contactInfo);
		(new ContactTabPageCM(browser, report)).ClickOnBackBtn();
		(new AccountTabPageCM(browser, report)).CreateBiilingAccount(accountInfo);
		(new ContactTabPageCM(browser, report)).CreateBillingContact(contactInfo);
		(new ContactTabPageCM(browser, report)).ClickOnBackBtn();
		siteInfo = SiteInfo.loadFromDatatable(dataTable);
		(new AddressTabPageCM(browser, report)).ClickAddressTab(siteInfo);
		Site1 = (new AddressTabPageCM(browser, report)).EnterSiteDetailsValid(siteInfo);
		(new ContactTabPageCM(browser, report)).CreateSiteTechnicalContact(contactInfo);
		(new ContactTabPageCM(browser, report)).ClickOnBackBtn();
		(new AddressTabPageCM(browser, report)).CreateNewAddress();
		Site2 = (new AddressTabPageCM(browser, report)).EnterSiteDetailsValid(siteInfo);
		(new ContactTabPageCM(browser, report)).CreateSiteTechnicalContact(contactInfo);
		(new ServiceTabPageCM(browser, report)).ClickOnServiceTab();
		(new ServiceTabPageCM(browser, report)).SelectPricePlan();
		(new ServiceTabPageCM(browser, report)).EPL();
		(new ServiceTabPageCM(browser, report)).EquipmentFee();
		(new ServiceTabPageCM(browser, report)).ClickOnContinueButton();
		(new FeatureTabPageCM(browser, report)).ClickOnContinueButton();
		processInfo = ProcessInfo.loadFromDatatable(dataTable);
		SRID = (new ProcessTabPageCM(browser, report)).ProcessConfiguration(processInfo);
		(new ProcessTabPageCM(browser, report)).UNIConfiguration(processInfo, Site1);
		(new ProcessTabPageCM(browser, report)).UNI2Configuration(processInfo, Site2);
		(new ProcessTabPageCM(browser, report)).EVCConfiguration_EPL(processInfo);
		(new ProcessTabPageCM(browser, report)).EqFeeConfiguration(processInfo);
		(new ProcessTabPageCM(browser, report)).EqFee2Configuration(processInfo);
		(new ProcessTabPageCM(browser, report)).ClickOnContinueButton();
		orderSummaryInfo = OrderSummaryInfo.loadFromDatatable(dataTable);
		(new OrderSummaryTabCMPage(browser, report)).assignLabel(orderSummaryInfo);
		(new OrderSummaryTabCMPage(browser, report)).SubmitOrder(orderSummaryInfo);
		(new OrderSummaryTabCMPage(browser, report)).mrcNrc_Value(orderSummaryInfo);
		(new OrderSummaryTabCMPage(browser, report)).ClickSubmitOrderButton();
		(new OrderSummaryTabCMPage(browser, report)).NavigateToCSO(orderSummaryInfo);
		(new WorkOrderTabPageCSO(browser, report)).SearchForOrderInSO(SRID);
	    (new WorkOrderTabPageCSO(browser, report)).ClickFirstSiteFlow();
		siteLevelTaskInfo = SiteLevelTaskInfo.loadFromDatatable(dataTable);
		(new SiteLevelTasks(browser, report)).ConductSiteSurvey();
		(new ConductSiteSurveyTaskPage(browser, report)).ConductSiteSurvey(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).ObtainSiteAgreement();
		(new ObtainSiteAgreementTaskPage(browser, report)).ObtainSiteAgreement(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).ConductFiberPlantSurvey();
		(new ConductFiberPlantSurveyTaskPage(browser, report)).ConductFiberPlantSurvey(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).BuildHouseAccount();
		(new BuildHouseAccountTaskPage(browser, report)).BuildHouseAccount(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).CompleteWavelengthReservation();
		(new CompleteWavelengthReservationTaskPage(browser, report)).CompleteWavelengthReservation(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).CompleteSiteBuild();
		(new CompleteSiteBuildTaskPage(browser, report)).ClickCompleteButton();
		(new CompleteSiteBuildTaskPage(browser, report)).closePopup();
		(new SiteLevelTasks(browser, report)).ObtainFiberPlantPermits();
		(new ObtainFiberPlantPermitsTaskPage(browser, report)).ObtainFiberPlantPermits();
		(new SiteLevelTasks(browser, report)).CompleteFiberPlantBuild();
		(new CompleteFiberPlantBuildTaskPage(browser, report)).ClickCompleteButton();
		(new CompleteFiberPlantBuildTaskPage(browser, report)).closePopup();
		(new SiteLevelTasks(browser, report)).ClickBackButton();
		(new WorkOrderTabPageCSO(browser, report)).ClickSecondSiteFlow();
		(new SiteLevelTasks(browser, report)).ConductSiteSurvey();
		(new ConductSiteSurveyTaskPage(browser, report)).ConductSiteSurvey(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).ObtainSiteAgreement();
		(new ObtainSiteAgreementTaskPage(browser, report)).ObtainSiteAgreement(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).ConductFiberPlantSurvey();
		(new ConductFiberPlantSurveyTaskPage(browser, report)).ConductFiberPlantSurvey(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).BuildHouseAccount();
		(new BuildHouseAccountTaskPage(browser, report)).BuildHouseAccount(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).CompleteWavelengthReservation();
		(new CompleteWavelengthReservationTaskPage(browser, report)).CompleteWavelengthReservation(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).CompleteSiteBuild();
		(new CompleteSiteBuildTaskPage(browser, report)).ClickCompleteButton();
		(new CompleteSiteBuildTaskPage(browser, report)).closePopup();
		(new SiteLevelTasks(browser, report)).ObtainFiberPlantPermits();
		(new ObtainFiberPlantPermitsTaskPage(browser, report)).ObtainFiberPlantPermits();
		(new SiteLevelTasks(browser, report)).CompleteFiberPlantBuild();
		(new CompleteFiberPlantBuildTaskPage(browser, report)).ClickCompleteButton();
		(new CompleteFiberPlantBuildTaskPage(browser, report)).closePopup();
		(new ServiceLevelTasks(browser, report)).ContactCustomer();
		(new ContactCustomerTaskPage(browser, report)).ContactCustomer();
		(new ServiceLevelTasks(browser, report)).UpdateDesign();
		(new UpdateDesignTaskPage(browser, report)).UpdateDesign();
		(new SiteLevelTasks(browser, report)).ClickBackButton();
		(new WorkOrderTabPageCSO(browser, report)).ClickEPLFlow();
		serviceLevelTaskInfo = ServiceLevelTaskInfo.loadFromDatatable(dataTable);
		(new ServiceLevelTasks(browser, report)).BULBA();
		(new BULBATaskPage(browser, report)).BULBA(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).BULBA2();
		(new BULBATaskPage(browser, report)).BULBA(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).ShipCPE();
		(new ShipCPETaskPage(browser, report)).ShipCPE(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).CAE();
		(new CAETaskPage(browser, report)).CAE_EPL(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).ADI();
		(new ADITaskPage(browser, report)).ADI(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).GenerateCoreConfigs();
		new GenerateCoreConfigsTaskPage(browser, report).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).GenerateCPEConfigs();
		(new GenerateCPEConfigsTaskPage(browser, report)).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).LoadCoreConfigs();
		(new LoadCoreConfigsTaskPage(browser, report)).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).InstallCPE();
		(new InstallCPETaskPage(browser, report)).InstallCPE();
		(new ServiceLevelTasks(browser, report)).InstallCPE2();
		(new InstallCPETaskPage(browser, report)).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).SetCriticalDates();
		(new SetCriticalDatesTaskPage(browser, report)).SetCriticalDates();
		(new ServiceLevelTasks(browser, report)).DayofConfigs();
		(new DaysOfConfigsTaskPage(browser, report)).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).ActivateService();
		(new ActivateServiceTaskPage(browser, report)).ActivateService_EPL(serviceLevelTaskInfo);
		(new ServiceLevelTasks(browser, report)).NotifyCustomerofServiceInstallation();
		(new NotifyCustomerofServiceInstallationTaskPage(browser, report)).NotifyCustomerofServiceInstallation();
		(new ServiceLevelTasks(browser, report)).CCAT();
		(new CCATTaskPage(browser, report)).ClickCompleteButton();
		(new ServiceLevelTasks(browser, report)).StartBilling();
		(new StartBillingTaskPage(browser,report)).verifyNotes();
		(new ServiceLevelTasks(browser, report)).ClickBackButton();
	}

	@Test
	public void New_Connect_ME_EVPL_New_connect() throws InterruptedException, AWTException {

		String Site1, Site2, Site3;
		customerInfo = CustomerInfo.loadFromDatatable(dataTable);
		(new CustomerTabPageCM(browser, report)).createCustomer(customerInfo);
		accountInfo = AccountInfo.loadFromDatatable(dataTable);
		(new AccountTabPageCM(browser, report)).CreateServiceAccount(accountInfo);
		contactInfo = ContactInfo.loadFromDatatable(dataTable);
		(new ContactTabPageCM(browser, report)).CreateAccountPrimaryContact(contactInfo);
		(new ContactTabPageCM(browser, report)).ClickOnBackBtn();
		(new AccountTabPageCM(browser, report)).CreateBiilingAccount(accountInfo);
		(new ContactTabPageCM(browser, report)).CreateBillingContact(contactInfo);
		(new ContactTabPageCM(browser, report)).ClickOnBackBtn();
		/*siteInfo = SiteInfo.loadFromDatatable(dataTable);
		(new AddressTabPageCM(browser, report)).ClickAddressTab(siteInfo);
		Site1 = (new AddressTabPageCM(browser, report)).EnterSiteDetailsValid(siteInfo);
		(new ContactTabPageCM(browser, report)).CreateSiteTechnicalContact(contactInfo);
		(new ContactTabPageCM(browser, report)).ClickOnBackBtn();
		(new AddressTabPageCM(browser, report)).CreateNewAddress();
		Site2 = (new AddressTabPageCM(browser, report)).EnterSiteDetailsValid(siteInfo);
		(new ContactTabPageCM(browser, report)).CreateSiteTechnicalContact(contactInfo);
		(new ContactTabPageCM(browser, report)).ClickOnBackBtn();
		(new AddressTabPageCM(browser, report)).CreateNewAddress();
		Site3 = (new AddressTabPageCM(browser, report)).EnterSiteDetailsValid(siteInfo);
		(new ContactTabPageCM(browser, report)).CreateSiteTechnicalContact(contactInfo);*/
		/*(new ServiceTabPageCM(browser, report)).ClickOnServiceTab();
		(new ServiceTabPageCM(browser, report)).SelectPricePlan();
		(new ServiceTabPageCM(browser, report)).EVPL();
		(new ServiceTabPageCM(browser, report)).EquipmentFee();
		(new ServiceTabPageCM(browser, report)).ClickOnContinueButton();
		(new FeatureTabPageCM(browser, report)).EVPL();
		processInfo = ProcessInfo.loadFromDatatable(dataTable);
		SRID = (new ProcessTabPageCM(browser, report)).ProcessConfiguration(processInfo);
		(new ProcessTabPageCM(browser, report)).UNIConfiguration(processInfo, Site1);
		(new ProcessTabPageCM(browser, report)).UNI2Configuration(processInfo, Site2);
		(new ProcessTabPageCM(browser, report)).UNI3Configuration(processInfo, Site3);
		(new ProcessTabPageCM(browser, report)).EVCConfiguration_EVPL(processInfo);
		(new ProcessTabPageCM(browser, report)).EVC2Configuration_EVPL(processInfo);
		(new ProcessTabPageCM(browser, report)).EqFeeConfiguration(processInfo);
		(new ProcessTabPageCM(browser, report)).EqFee2Configuration(processInfo);
		(new ProcessTabPageCM(browser, report)).EqFee3Configuration(processInfo);
		(new ProcessTabPageCM(browser, report)).ClickOnContinueButton();
		orderSummaryInfo = OrderSummaryInfo.loadFromDatatable(dataTable);
		(new OrderSummaryTabCMPage(browser, report)).SubmitOrder(orderSummaryInfo);
		(new OrderSummaryTabCMPage(browser, report)).mrcNrc_Value(orderSummaryInfo);
		(new OrderSummaryTabCMPage(browser, report)).ClickSubmitOrderButton();
		(new OrderSummaryTabCMPage(browser, report)).NavigateToCSO(orderSummaryInfo);
		(new WorkOrderTabPageCSO(browser, report)).SearchForOrderInSO(SRID);*/
	}
	
	@Test
	public void EDI_New_Connect_Local_Biller_Billertype_CSG() throws InterruptedException, AWTException{
		
		String Site1;
		customerInfo = CustomerInfo.loadFromDatatable(dataTable);
		(new CustomerTabPageCM(browser, report)).createCustomer(customerInfo);
		accountInfo = AccountInfo.loadFromDatatable(dataTable);
		(new AccountTabPageCM(browser, report)).CreateServiceAccount(accountInfo);
		contactInfo = ContactInfo.loadFromDatatable(dataTable);
		(new ContactTabPageCM(browser, report)).CreateAccountPrimaryContact(contactInfo);
		(new ContactTabPageCM(browser, report)).ClickOnBackBtn();
		(new AccountTabPageCM(browser, report)).CreateBiilingAccount(accountInfo);
		(new ContactTabPageCM(browser, report)).CreateBillingContact(contactInfo);
		(new ContactTabPageCM(browser, report)).ClickOnBackBtn();
		siteInfo = SiteInfo.loadFromDatatable(dataTable);
		(new AddressTabPageCM(browser, report)).ClickAddressTab(siteInfo);
		Site1 = (new AddressTabPageCM(browser, report)).EnterSiteDetailsValid(siteInfo);
		(new ContactTabPageCM(browser, report)).CreateSiteTechnicalContact(contactInfo);
		(new ServiceTabPageCM(browser, report)).ClickOnServiceTab();
		(new ServiceTabPageCM(browser, report)).SelectPricePlan();
		(new ServiceTabPageCM(browser, report)).EDI();
		(new ServiceTabPageCM(browser, report)).EquipmentFee();
		(new ServiceTabPageCM(browser, report)).ClickOnContinueButton();
		(new FeatureTabPageCM(browser, report)).ClickOnContinueButton();
		processInfo = ProcessInfo.loadFromDatatable(dataTable);
		SRID = (new ProcessTabPageCM(browser, report)).ProcessConfiguration(processInfo);
		(new ProcessTabPageCM(browser, report)).UNIConfiguration(processInfo, Site1);
		(new ProcessTabPageCM(browser, report)).EVCConfiguration_EDI(processInfo);
		(new ProcessTabPageCM(browser, report)).EqFeeConfiguration(processInfo);
		(new ProcessTabPageCM(browser, report)).ClickOnContinueButton();
		orderSummaryInfo = OrderSummaryInfo.loadFromDatatable(dataTable);
		(new OrderSummaryTabCMPage(browser, report)).assignLabel(orderSummaryInfo);
		(new OrderSummaryTabCMPage(browser, report)).SubmitOrder(orderSummaryInfo);
		(new OrderSummaryTabCMPage(browser, report)).mrcNrc_Value(orderSummaryInfo);
		(new OrderSummaryTabCMPage(browser, report)).ClickSubmitOrderButton();
		(new OrderSummaryTabCMPage(browser, report)).NavigateToCSO(orderSummaryInfo);
		(new WorkOrderTabPageCSO(browser, report)).SearchForOrderInSO(SRID);
		(new WorkOrderTabPageCSO(browser, report)).ClickFirstSiteFlow();
		siteLevelTaskInfo = SiteLevelTaskInfo.loadFromDatatable(dataTable);
		(new SiteLevelTasks(browser, report)).ConductSiteSurvey();
		(new ConductSiteSurveyTaskPage(browser, report)).ConductSiteSurvey(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).ObtainSiteAgreement();
		(new ObtainSiteAgreementTaskPage(browser, report)).ObtainSiteAgreement(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).ConductFiberPlantSurvey();
		(new ConductFiberPlantSurveyTaskPage(browser, report)).ConductFiberPlantSurvey(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).BuildHouseAccount();
		(new BuildHouseAccountTaskPage(browser, report)).BuildHouseAccount(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).CompleteWavelengthReservation();
		(new CompleteWavelengthReservationTaskPage(browser, report)).CompleteWavelengthReservation(siteLevelTaskInfo);
		(new SiteLevelTasks(browser, report)).CompleteSiteBuild();
		(new CompleteSiteBuildTaskPage(browser, report)).ClickCompleteButton();
		(new CompleteSiteBuildTaskPage(browser, report)).closePopup();
		(new SiteLevelTasks(browser, report)).ObtainFiberPlantPermits();
		(new ObtainFiberPlantPermitsTaskPage(browser, report)).ObtainFiberPlantPermits();
		(new SiteLevelTasks(browser, report)).CompleteFiberPlantBuild();
		(new CompleteFiberPlantBuildTaskPage(browser, report)).ClickCompleteButton();
		(new CompleteFiberPlantBuildTaskPage(browser, report)).closePopup();
		(new ServiceLevelTasks(browser, report)).ContactCustomer();
		(new ContactCustomerTaskPage(browser, report)).ContactCustomer();
		(new ServiceLevelTasks(browser, report)).UpdateDesign();
		(new UpdateDesignTaskPage(browser, report)).UpdateDesign();
		(new SiteLevelTasks(browser, report)).ClickBackButton();
		(new WorkOrderTabPageCSO(browser, report)).ClickEDIFlow();
		
	}

	@Test
	public void New_Connect_BVE_New_connect() throws InterruptedException, AWTException {

		// (new HomePageCM(browser,report)).NavigateToServiceTab();
		String Site1;
		customerInfo = CustomerInfo.loadFromDatatable(dataTable);
		(new CustomerTabPageCM(browser, report)).customerInformation(customerInfo);
		(new CustomerTabPageCM(browser, report)).addressInformationValid(customerInfo);
		accountInfo = AccountInfo.loadFromDatatable(dataTable);
		(new AccountTabPageCM(browser, report)).CreateServiceAccount(accountInfo);
		contactInfo = ContactInfo.loadFromDatatable(dataTable);
		(new ContactTabPageCM(browser, report)).CreateAccountPrimaryContact(contactInfo);
		(new ContactTabPageCM(browser, report)).ClickOnBackBtn();
		(new AccountTabPageCM(browser, report)).CreateBiilingAccount(accountInfo);
		siteInfo = SiteInfo.loadFromDatatable(dataTable);
		Site1 = (new AddressTabPageCM(browser, report)).EnterSiteDetailsValid(siteInfo);
		(new ContactTabPageCM(browser, report)).CreateSiteTechnicalContact(contactInfo);
		(new ContactTabPageCM(browser, report)).CreateShippingContact(contactInfo);
		(new ContactTabPageCM(browser, report)).ClickOnBackBtn();
		(new AccountTabPageCM(browser, report)).CreateSecondBillingAccount(accountInfo);
		(new ServiceTabPageCM(browser, report)).ClickOnServiceTab();
		(new ServiceTabPageCM(browser, report)).SelectPricePlan();
		(new ServiceTabPageCM(browser, report)).BVE();
		(new ServiceTabPageCM(browser, report)).ClickOnContinueButton();
		(new FeatureTabPageCM(browser, report)).BVE();
		(new EquipmentTabPageCM(browser, report)).equipmentConfiguration();

	}

	
	
	@Test
	public void Survey() throws InterruptedException {
		orderSummaryInfo = OrderSummaryInfo.loadFromDatatable(dataTable);
		(new OrderSummaryTabCMPage(browser, report)).NavigateToCSO(orderSummaryInfo);
		System.out.println("Survey");
		(new SurveyTabPageCM(browser, report)).CreateSiteForSurvey();
		contactInfo = ContactInfo.loadFromDatatable(dataTable);
		(new ContactTabPageCM(browser, report)).CreateSiteTechnicalContact(contactInfo);
		(new ContactTabPageCM(browser, report)).ClickOnBackBtn();
		SurveyID = (new SurveyTabPageCM(browser, report)).SubmitSurveyRequest();
		/*
		 * orderSummaryInfo = OrderSummaryInfo.loadFromDatatable(dataTable);
		 * (new
		 * OrderSummaryTabCMPage(browser,report)).NavigateToCSO(orderSummaryInfo
		 * ); (new WorkOrderTabPageCSO(browser,
		 * report)).SearchForSurveyInPreSales(SurveyID); (new
		 * WorkOrderTabPageCSO(browser, report)).ClickWLAdvancedSearch(); (new
		 * WorkOrderTabPageCSO(browser, report)).ClickAllTask(); (new
		 * PresalesTasksPageCSO(browser,report)).ConductCoaxSurvey();
		 */
	}

	
	
	/*@After
	public void closeApplication() throws IOException, Exception{
		centuryApplication = new CenturyApplication(browser, report);
		centuryApplication.close();
	}*/

	

}
