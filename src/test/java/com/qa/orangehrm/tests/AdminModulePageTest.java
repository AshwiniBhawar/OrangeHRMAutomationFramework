package com.qa.orangehrm.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.orangehrm.base.BaseTest;
import com.qa.orangehrm.constants.AppConstants;

public class AdminModulePageTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		dashboardPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void validatePIMHeaderTest() {
		String actualHeaderValue = dashboardPage.selectAdminModuleValue().getAdminPageHeader();
		System.out.println(actualHeaderValue);
		Assert.assertEquals(actualHeaderValue, AppConstants.ADMIN_MODULE_HEADER_TEXT);
	}

//	@Test
//	public void validateConfiguartionDropdownListTest() {
//		List<String> actualList = dashboardPage.selectAdminModuleValue().getHeaderList();
//		System.out.println(actualList);
//		//Assert.assertEquals(actualList, AppConstants.CONFIGURATION_DROPDOWN_LIST);
//	}

	
}
