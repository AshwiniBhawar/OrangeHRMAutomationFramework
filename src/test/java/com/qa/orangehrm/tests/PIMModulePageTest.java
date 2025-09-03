package com.qa.orangehrm.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.orangehrm.base.BaseTest;
import com.qa.orangehrm.constants.AppConstants;

public class PIMModulePageTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		dashboardPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void validatePIMHeaderTest() {
		String actualHeaderValue = dashboardPage.selectPIMModuleValue().getPIMPageHeader();
		Assert.assertEquals(actualHeaderValue, AppConstants.PIM_MODULE_HEADER_TEXT);
	}

	@Test
	public void validateConfiguartionDropdownListTest() {
		List<String> actualList = dashboardPage.selectPIMModuleValue().getConfigurationList();
		Assert.assertEquals(actualList, AppConstants.CONFIGURATION_DROPDOWN_LIST);
	}

	@Test
	public void validateHeadersTest() {
		List<String> actualList = dashboardPage.selectPIMModuleValue().getHeaderList();
		Assert.assertEquals(actualList, AppConstants.PIM_HEADERS_LIST);
	}

	@DataProvider
	public Object[][] searchEmployeeData() {
		return new Object[][] { { "0290", "yes" },
			{"0294", "no"}};
	}

	@Test(dataProvider = "searchEmployeeData")
	public void deleteEmployeeMsgTest(String employeeId, String deleteYesorNo) {
		String msg = dashboardPage.selectPIMModuleValue().deleteRecord(employeeId, deleteYesorNo);
		System.out.println(msg);
 		if(deleteYesorNo.contains("yes")) {
			Assert.assertEquals(msg, AppConstants.DELETE_EMPLOYEE_SUCCESS_MSG);
		}
		else {
			System.out.println("employee record deletion is cancelled");
			Assert.assertEquals(msg, null);
		}	
	}
	
	@Test
	public void noRecordFoundMsgTest() {
		String msg = dashboardPage.selectPIMModuleValue().noRecordFoundMessage("0404");
		System.out.println(msg);
		Assert.assertEquals(msg, AppConstants.NO_EMPLOYEE_RECORD_FOUND_TEST);
	}
}
