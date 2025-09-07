package com.qa.orangehrm.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.orangehrm.constants.AppConstants;
import com.qa.orangehrm.utilities.ElementUtil;

public class DashboardPage {
	private WebDriver driver;
	private ElementUtil eUtil;
	
	private static final Logger log= LogManager.getLogger(DashboardPage.class);
	
	public DashboardPage(WebDriver driver) {
		this.driver=driver;
		eUtil= new ElementUtil(driver);
	}
	
	private final By headerLocator=By.tagName("h6");
	private final By pimModuleLocator= By.xpath("//a[normalize-space()='PIM']/span");
	private final By adminModuleLocator=By.xpath("//a[normalize-space()='Admin']/span");
	
	public String getDashboardPageUrl() {
		String url=eUtil.waitForURLContains(AppConstants.DASHBOARD_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_WAIT);
		log.info("Dashboard page url is :"+url);
		return url;
	}
	
	public String getDashboardPageHeader() {
		String result=eUtil.waitForElementVisible(headerLocator, AppConstants.DEFAULT_MEDIUM_WAIT).getText();
		log.info("get the text of dashboard page header" +result);
		return result;
	}
	
	public PIMModulePage selectPIMModuleValue() {
		log.info("select PIM from the left hand side menu");
		eUtil.waitForElementVisible(pimModuleLocator, AppConstants.DEFAULT_MEDIUM_WAIT).click();
		return new PIMModulePage(driver);
	}
	
	public AdminModulePage selectAdminModuleValue() {
		log.info("select ADMIN from the left hand side menu");
		eUtil.waitForElementVisible(adminModuleLocator, AppConstants.DEFAULT_MEDIUM_WAIT).click();
		return new AdminModulePage(driver);
	}
	
}
