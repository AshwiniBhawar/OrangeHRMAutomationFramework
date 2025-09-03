package com.qa.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.orangehrm.base.BaseTest;

public class LoginPageTest extends BaseTest{

	@Test(priority=Integer.MAX_VALUE)
	public void loginTest() {
		dashboardPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		ChainTestListener.log("login username is :"+prop.getProperty("username")+" and password is :"+prop.getProperty("password"));
		String actualUrl=dashboardPage.getDashboardPageUrl();
		Assert.assertTrue(actualUrl.contains("dashboard/index"));
	}
	
	@Test
	public void isForgotPwdLinkExistTest() {
		boolean actualResult=loginPage.isForgotPwdLinkExit();
		ChainTestListener.log("is forgot password link exist? :" +actualResult);
		Assert.assertTrue(actualResult);
	}
	
	@Test
	public void isOrangeHRMLogoExistTest() {
		boolean actualResult=loginPage.isOrangeHRMLogoExist();
		ChainTestListener.log("is orange hrm logo exist? :" +actualResult);
		Assert.assertTrue(actualResult);
	}
	
	@Test
	public void isOrangeHRMCompanyBrandingLogoExistTest() {
		boolean actualResult=loginPage.isOrangeHRMCompanyBrandingLogoExist();
		ChainTestListener.log("is orange hrm company barnding logo exist? :" +actualResult);
		Assert.assertTrue(actualResult);
		
	}

}
