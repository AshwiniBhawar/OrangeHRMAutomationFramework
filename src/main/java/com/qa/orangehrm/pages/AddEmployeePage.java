package com.qa.orangehrm.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.orangehrm.constants.AppConstants;
import com.qa.orangehrm.utilities.ElementUtil;

public class AddEmployeePage {
	private WebDriver driver;
	private ElementUtil eUtil;
	
	public AddEmployeePage(WebDriver driver) {
		this.driver=driver;
		eUtil = new ElementUtil(driver);
	}
	
	private final By firstNameLocator=By.name("firstName");
	private final By middleNameLocator= By.name("middleName");
	private final By lastNameLocator= By.name("lastName");
	private final By submitButton=By.xpath("//button[@type='submit']");
	private final By nationalityLocator=By.xpath("(//div[@class='oxd-select-text--after']/i)[1]");
	private final By nationalityDropdwonValuesLocator=By.xpath("//div[@class='oxd-select-option']/span");
	private final By successMsgLocator=By.cssSelector(".oxd-toast-content.oxd-toast-content--success");
	
	public String addEmployeeWithoutLoginDetails(String firstName, String middleName,String lastName) {
		eUtil.waitForElementVisible(firstNameLocator, AppConstants.DEFAULT_MEDIUM_WAIT).sendKeys(firstName);
		eUtil.doSendkeys(middleNameLocator, middleName);
		eUtil.doSendkeys(lastNameLocator, lastName);
		eUtil.doClick(submitButton);
		String msg= eUtil.waitForElementVisible(successMsgLocator, AppConstants.DEFAULT_MEDIUM_WAIT).getText();
		return msg;
	}
	
}
