package com.qa.orangehrm.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.orangehrm.constants.AppConstants;
import com.qa.orangehrm.exceptions.FrameworkException;
import com.qa.orangehrm.utilities.ElementUtil;

public class AdminModulePage {
	private WebDriver driver;
	private ElementUtil eUtil;
	
	private static final Logger log= LogManager.getLogger(AdminModulePage.class);
	
	public AdminModulePage(WebDriver driver) {
		this.driver=driver;
		eUtil= new ElementUtil(driver);
	}
	
	private final By adminheaderLocator=By.cssSelector("span.oxd-topbar-header-breadcrumb");
			//By.tagName("h6");
	private final By confiHeaderLocator= By.xpath("///div[@class='oxd-topbar-body']//li");
		
		
	public String getAdminPageHeader() {
		return eUtil.waitForElementVisible(adminheaderLocator, AppConstants.DEFAULT_MEDIUM_WAIT).getText();
	}
	
	public List<String> getHeaderList() {
		
		List<String> list = new ArrayList<String>();
		
		List<WebElement> headersList = eUtil.waitForElementsVisible(confiHeaderLocator, AppConstants.DEFAULT_MEDIUM_WAIT);
				
		for(WebElement e: headersList) {
			list.add(e.getText());
		}
		log.info("Admin module headers contains : "+list);
		log.info("Headers size : "+ list.size());
		
		return list;
	}

}
