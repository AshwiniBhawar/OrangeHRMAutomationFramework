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
	public void validateAdminHeaderTest() {
		String actualHeaderValue = dashboardPage.selectAdminModuleValue().getAdminPageHeader();
		Assert.assertEquals(actualHeaderValue, AppConstants.ADMIN_MODULE_HEADER_TEXT);
	}

	@Test
	public void validateHeadersListTest() {
		List<String> actualList = dashboardPage.selectAdminModuleValue().getHeaderList();
		Assert.assertEquals(actualList, AppConstants.ADMIN_HEADERS_LIST);
	}

	
}
