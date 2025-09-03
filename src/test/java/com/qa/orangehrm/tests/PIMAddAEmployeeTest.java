package com.qa.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.orangehrm.base.BaseTest;
import com.qa.orangehrm.constants.AppConstants;
import com.qa.orangehrm.utilities.ExcelUtil;

public class PIMAddAEmployeeTest extends BaseTest{

	@BeforeClass
	public void setUpAcc() {
		dashboardPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getExcelData() {
		return ExcelUtil.getTestData("OrangeHrm","PIMAddEmployee");
	}
	
	@Test(dataProvider="getExcelData")
	public void addAEmployeeTest(String firstName,String middleName, String lastName) {
		String msg=dashboardPage.selectPIMModuleValue().clickOnAddButton().addEmployeeWithoutLoginDetails(firstName, middleName, lastName);
		Assert.assertEquals(msg, AppConstants.ADD_EMPLOYEE_SUCCESS_MSG);
	}
}
