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

public class PIMModulePage {
	private WebDriver driver;
	private ElementUtil eUtil;
	
	private static final Logger log= LogManager.getLogger(PIMModulePage.class);
	
	public PIMModulePage(WebDriver driver) {
		this.driver=driver;
		eUtil= new ElementUtil(driver);
	}
	
	private final By pimheaderLocator=By.tagName("h6");
	private final By confiHeaderLocator= By.xpath("//div[@class='oxd-topbar-body']//li/span");
	private final By headersLocator= By.xpath("//div[@class='oxd-topbar-body']//li/a");
	private final By selectConfiDropdownLocator= By.xpath("//div[@class='oxd-topbar-body']//li/span/i");
	private final By confiDropdwonLocator= By.xpath("//ul[@class='oxd-dropdown-menu']/li/a");
	private final By deleteRecordBinLocator= By.xpath("//i[@class='oxd-icon bi-trash']");
	private final By employeeIDLocator= By.cssSelector("input.oxd-input.oxd-input--active:nth-child(1)");
	private final By searchEmployeeBtnlocator= By.xpath("//button[@type='submit']");
	private final By selectEmployeeIdFromListLocator= By.xpath("//div[contains(@class,'oxd-table-row--clickable')]/div");
	private final By cancelDeleteRecordLocator= By.xpath("//div[@class='orangehrm-modal-footer']/button[1]");
	private final By acceptDeleteRecordLocator= By.xpath("//div[@class='orangehrm-modal-footer']/button[2]");
	private final By deleteSuccesMsgLocator= By.xpath("//div[contains(@class,'oxd-toast-content--success')]");
	private final By noEmployeeIdFound= By.xpath("//div[contains(@class,'oxd-toast-content--info')]");
	private final By addButtonLocator=By.xpath("//div[@class='orangehrm-header-container']/button");
	//.oxd-button.oxd-button--medium.oxd-button--secondary:nth-of-type(1)
	
	public String getPIMPageHeader() {
		return eUtil.waitForElementVisible(pimheaderLocator, AppConstants.DEFAULT_MEDIUM_WAIT).getText();
	}
	
	public List<String> getConfigurationList() {
		eUtil.waitForElementVisible(selectConfiDropdownLocator, AppConstants.DEFAULT_MEDIUM_WAIT).click();
		List<WebElement> dropdownList = eUtil.waitForElementsVisible(confiDropdwonLocator, AppConstants.DEFAULT_MEDIUM_WAIT);
		List<String> list = new ArrayList<String>();
		
		for(WebElement e: dropdownList) {
			list.add(e.getText());
		}
		log.info("Configuration dropdown values : "+list);
		log.info("Configuartion dropdown size : "+ list.size());
		
		return list;
	}
	
	public List<String> getHeaderList() {
		
		List<String> list = new ArrayList<String>();
		
		String confiHeaderText=eUtil.waitForElementVisible(confiHeaderLocator, AppConstants.DEFAULT_MEDIUM_WAIT).getText();
		list.add(confiHeaderText);
		List<WebElement> headersList = eUtil.waitForElementsVisible(headersLocator, AppConstants.DEFAULT_MEDIUM_WAIT);
				
		for(WebElement e: headersList) {
			list.add(e.getText());
		}
		log.info("Headers contains : "+list);
		log.info("Headers size : "+ list.size());
		
		return list;
	}
	
	public void serachRecord(String employeeId) {
		eUtil.waitForElementVisible(employeeIDLocator, AppConstants.DEFAULT_MEDIUM_WAIT).sendKeys(employeeId);
		eUtil.doClick(searchEmployeeBtnlocator);		
	}
	
	public String deleteRecord(String employeeId, String deleteYesOrNo) {
		serachRecord(employeeId);
		String msgText=null;
		try {
			msgText=eUtil.waitForElementVisible(noEmployeeIdFound, AppConstants.DEFAULT_SHORT_WAIT).getText();
		}
		catch(Exception e){
			throw new FrameworkException("no record found");
		}
		
		eUtil.waitForElementVisible(selectEmployeeIdFromListLocator, AppConstants.DEFAULT_SHORT_WAIT).click();
		eUtil.waitForElementVisible(deleteRecordBinLocator, AppConstants.DEFAULT_SHORT_WAIT).click();
		if(deleteYesOrNo.toLowerCase().equals("yes")) {
			eUtil.waitForElementVisible(acceptDeleteRecordLocator,AppConstants.DEFAULT_SHORT_WAIT).click();
			msgText=eUtil.waitForElementVisible(deleteSuccesMsgLocator, AppConstants.DEFAULT_SHORT_WAIT).getText();
		}
		else {
			eUtil.waitForElementVisible(cancelDeleteRecordLocator,AppConstants.DEFAULT_SHORT_WAIT).click();
		}
		log.info("delete record message text: "+msgText);
		return msgText;
	}
	
	public String noRecordFoundMessage(String employeeId) {
		serachRecord(employeeId);
		String msgText=eUtil.waitForElementVisible(noEmployeeIdFound, AppConstants.DEFAULT_SHORT_WAIT).getText();
		log.info("no record found message text: "+msgText);
		return msgText;
	}
	
	public AddEmployeePage clickOnAddButton() {
		eUtil.waitForElementVisible(addButtonLocator, AppConstants.DEFAULT_MEDIUM_WAIT).click();
		return new AddEmployeePage(driver);
	}
}
