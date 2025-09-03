package com.qa.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.orangehrm.base.BaseTest;
import com.qa.orangehrm.constants.AppConstants;

public class DashboardPageTest extends BaseTest{
	
	@BeforeClass
	public void accPageSetup() {
		dashboardPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void isDashboardPageHeaderExistTest() {
		String actualHeaderValue=dashboardPage.getDashboardPageHeader();
		Assert.assertEquals(actualHeaderValue, AppConstants.DASHBOARD_MODULE_HEADER_TEXT);
	}

}
