package com.qa.orangehrm.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.orangehrm.constants.AppConstants;
import com.qa.orangehrm.utilities.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eUtil;
	
	private static final Logger log= LogManager.getLogger(DashboardPage.class);
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eUtil= new ElementUtil(driver);
	}
	
	private final By usernameLocator=By.name("username");
	private final By passwordLocator=By.name("password");
	private final By loginBtnLocator=By.xpath("//button[@type='submit']");
	private final By forgotPwdLinkLocator=By.xpath("//div[@class='orangehrm-login-forgot']/p");
	private final By orangeHRMLogoLocator=By.xpath("//div[@class='orangehrm-login-logo']//img");
	private final By orangeHRMCompanyBrandingLogoLocator= By.xpath("//img[@alt='company-branding']");	
	
	public DashboardPage doLogin(String username, String password) {
		log.info("Enter username :"+username+ " and password :"+password);
		eUtil.waitForElementVisible(usernameLocator, AppConstants.DEFAULT_MEDIUM_WAIT).sendKeys(username);
		eUtil.doSendkeys(passwordLocator, password);
		eUtil.doClick(loginBtnLocator);
		return new DashboardPage(driver);
	}
	
	public boolean isForgotPwdLinkExit() {
		boolean result=eUtil.waitForElementVisible(forgotPwdLinkLocator, AppConstants.DEFAULT_MEDIUM_WAIT).isDisplayed();
		log.info("is forgot pwd link exist: "+result);
		return result;
	}
	
	public boolean isOrangeHRMLogoExist() {
		boolean result=eUtil.waitForElementVisible(orangeHRMLogoLocator, AppConstants.DEFAULT_MEDIUM_WAIT).isDisplayed();
		log.info("is orange hrm logo exist: "+result);
		return result;
	}

	public boolean isOrangeHRMCompanyBrandingLogoExist() {
		boolean result=eUtil.waitForElementVisible(orangeHRMCompanyBrandingLogoLocator, AppConstants.DEFAULT_MEDIUM_WAIT).isDisplayed();
		log.info("is orange hrm company branding logo exist: "+result);
		return result; 
	}
}
