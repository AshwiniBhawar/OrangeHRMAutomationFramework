package com.qa.orangehrm.utilities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.orangehrm.exceptions.ElementException;
import com.qa.orangehrm.factory.DriverFactory;


public class ElementUtil {

	private WebDriver driver;
	private Actions action;
	private Select select;
	private JavascriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		action= new Actions(driver);
		jsUtil= new JavascriptUtil(driver);
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public void doSendkeys(By locator, String... value) {
		if (value == null) {
			throw new ElementException("===value can not be null====");
		}
		getElement(locator).sendKeys(value);
	}

	public void doMultipleSendKeys(By locator, CharSequence... value) {
//		for(CharSequence v:value) {
//			if(v==null) {
//				throw new ElementException("===value can not be null====");
//			}
//		}
		getElement(locator).sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public String doElementGetText(By locator) {
		return getElement(locator).getText();
	}

	public boolean isElementDisplayed(By locator) {
		try {
			return getElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			System.out.println("Element is not displayed on the page: " + locator);
			return false;
		}
	}
	
	public boolean isElementDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			System.out.println("Element is not displayed on the page: " + element);
			return false;
		}
	}

	public boolean isElementDisplayedForMandortyFields(By locator) {
		try {
			return getElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			throw new ElementException("Element is not displayed on the page: " + locator);
		}
	}

	public boolean isElementEnabled(By locator) {
		try {
			return getElement(locator).isEnabled();
		} catch (NoSuchElementException e) {
			System.out.println("Element is not displayed on the page: " + locator);
			return false;
		}
	}

	public String getElementDOMAttributeValue(By locator, String attrName) {
		return getElement(locator).getDomAttribute(attrName);
	}

	public String getElementDOMPropertyValue(By locator, String propName) {
		return getElement(locator).getDomProperty(propName);
	}

	public boolean isElementExist(By locator) {
		if (getElements(locator).size() == 1) {
			System.out.println("the element : " + locator + " is present on the page one time");
			return true;
		} else {
			System.out.println("the element : " + locator + " is not present on the page one time");
			return false;
		}
	}

	public boolean isElementExist(By locator, int expectedEleCount) {
		if (getElements(locator).size() == expectedEleCount) {
			System.out.println("the element : " + locator + " is present on the page " + expectedEleCount + " time");
			return true;
		} else {
			System.out
					.println("the element : " + locator + " is not present on the page " + expectedEleCount + " time");
			return false;
		}
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public List<String> getElementsTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();// pc=0,vc=10; []
		for (WebElement ele : eleList) {
			String text = ele.getText();
			if (text.length() != 0) {
				eleTextList.add(text);
			}
		}
		return eleTextList;
	}

	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}

	public boolean clickElement(By locator, String eleText, String expectedPageTitle) throws InterruptedException {
		List<WebElement> eleList = getElements(locator);
		System.out.println("total number of elements: " + eleList.size());

		for (WebElement e : eleList) {
			String text = e.getText();
			System.out.println(text);
			if (text.contains(eleText)) {
				e.click();
				String actualPageTitle = driver.getTitle();
				System.out.println(actualPageTitle);
				Thread.sleep(7000);
				if (actualPageTitle.contains(expectedPageTitle)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean doSearch(By searchLocator, String searchKey, By suggestionsLocator, String suggestionValue,
			String expectedPageTitle) throws InterruptedException {

		doSendkeys(searchLocator, searchKey);
		Thread.sleep(4000);

		List<WebElement> suggList = getElements(suggestionsLocator);
		System.out.println("total number of suggestions: " + suggList.size());

		for (WebElement e : suggList) {
			String text = e.getText();
			System.out.println(text);
			if (text.contains(suggestionValue)) {
				e.click();
				String actualPageTitle = driver.getTitle();
				System.out.println(actualPageTitle);
				Thread.sleep(4000);
				if (actualPageTitle.contains(expectedPageTitle)) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	//*******************Select drop down utils*************//
	
	private void selectElement(By locator) {
		select = new Select(getElement(locator));
	}
	
	public void doSelectByIndex(By locator, int index) {
		selectElement(locator);
		select.selectByIndex(index);
	}
	
	public void doSelectByVisibleText(By locator, String eleText) {
		selectElement(locator);
		select.selectByVisibleText(eleText);
	}
	
	public void doSelectByContainsVisibleText(By locator, String value) {
		selectElement(locator);
		select.selectByContainsVisibleText(value);
	}
	
	public void doSelectByValue(By locator, String value) {
		selectElement(locator);
		select.selectByValue(value);
	}
	
	public List<String> getAllSelectedOptionsList(By locator) {
		selectElement(locator);
		List<WebElement> options = select.getAllSelectedOptions();
		System.out.println("total no. of selected values: " +options.size());
		List<String> allSelectedOptions= new ArrayList<String>();
		
		for(WebElement e:options) {
			allSelectedOptions.add(e.getText());
		}
		
		return allSelectedOptions;
	}
	
	public int getAllSelectedOptionsCount(By locator) {
		selectElement(locator);
		return select.getAllSelectedOptions().size();
	}

	public List<String> getDropDownValuesList(By locator) {
		selectElement(locator);
		List<WebElement> optionsList = select.getOptions();
		System.out.println("total number of options: " + optionsList.size());

		List<String> optionsValueList = new ArrayList<String>();// pc=0, size=0, []
		for (WebElement e : optionsList) {
			String text = e.getText();
			optionsValueList.add(text);
		}

		return optionsValueList;
	}
	
	public int getDropDownOptionsCount(By locator) {
		selectElement(locator);
		return select.getOptions().size();
	}
	
	
	public void selectDropDownValue(By locator, String value) {
		List<WebElement> optionsList = getElements(locator);
		System.out.println(optionsList.size());
		for (WebElement e : optionsList) {
			String text = e.getText();
			if (text.contains(value)) {
				e.click();
				break;
			}
		}
	}
	
	public String getDropDownFirstSelectValue(By locator) {
		selectElement(locator);
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isDropDownIsMultiple(By locator) {
		selectElement(locator);
		boolean isMultiple = select.isMultiple();
		System.out.println(isMultiple);
		return isMultiple;
	}
	
	public void doDeSelectByVisibleText(By locator, String eleText) {
		selectElement(locator);
		select.deselectByVisibleText(eleText);
	}
	
	public void doDeSelectByContainsVisibleText(By locator, String eleText) {
		selectElement(locator);
		select.deSelectByContainsVisibleText(eleText);
	}
	
	public void doDeSelectByIndex(By locator, int index) {
		selectElement(locator);
		select.deselectByIndex(index);
	}
	
	public void doDeSelectByValue(By locator, String eleText) {
		selectElement(locator);
		select.deselectByVisibleText(eleText);
	}
	
	public void doDeSelectAll(By locator) {
		selectElement(locator);
		select.deselectAll();
	}
	
	//*************************************Actions utils**************************************//
	
	private void moveToElement(By locator) throws InterruptedException {
		Thread.sleep(1000);
		action.moveToElement(getElement(locator)).perform();
		
	}
	
	public void menuSubMenuHandlingLevel2(By parentMenu,By childMenu) throws InterruptedException {
		moveToElement(parentMenu);
		Thread.sleep(1000);
		doClick(childMenu);
	}
	
	public void menuSubMenuHandlingLevel3(By menuLevel1, By menuLevel2, By menuLevel3) throws InterruptedException {
		doClick(menuLevel1);
		moveToElement(menuLevel2);
		Thread.sleep(1000);
		doClick(menuLevel3);
	}
	
	public void menuSubMenuHandlingLevel4(String actionType,By menuLevel1, By menuLevel2, By menuLevel3, By menuLevel4) throws InterruptedException {
		if(actionType.equalsIgnoreCase("click")) {
			doClick(menuLevel1);
		}
		else if(actionType.equalsIgnoreCase("mousehover")) {
			moveToElement(menuLevel1);
		}
		
		moveToElement(menuLevel2);
		moveToElement(menuLevel3);
		Thread.sleep(1000);
		doClick(menuLevel4);
	}
	
	public void dragAndDrop(By srcLocator, By destLocator) {
		action.dragAndDrop(getElement(srcLocator), getElement(destLocator));
	}
	
	public void doActionsSendKeys(By locator, String value) {
		action.sendKeys(getElement(locator), value).perform();
	}

	public void doActionsClick(By locator) {
		action.click(getElement(locator)).perform();
	}

	public void doSendKeysWithPause(By locator, String value, long pauseTime) {

		if (value == null) {
			throw new RuntimeException("===value can not be null");
		}

		char val[] = value.toCharArray();
		for (char ch : val) {
			action.sendKeys(getElement(locator), String.valueOf(ch)).pause(pauseTime).perform();
		}

	}

	public void doSendKeysWithPause(By locator, String value) {

		if (value == null) {
			throw new RuntimeException("===value can not be null");
		}

		char val[] = value.toCharArray();
		for (char ch : val) {
			action.sendKeys(getElement(locator), String.valueOf(ch)).pause(200).perform();
		}

	}
	
	public void scrollToElement(By locator) {
		action.scrollToElement(getElement(locator)).perform();
	}
	
	public void contextClick(By locator) {
		action.contextClick(getElement(locator)).perform();
	}
	

	// *********************Wait util****************//

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public WebElement waitForElementPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		if(DriverFactory.highlightElement.equals("true")) {
			jsUtil.flash(element);
		}
		return element;
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public WebElement waitForElementVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		if(DriverFactory.highlightElement.equals("true")) {
			jsUtil.flash(element);
		}
		return element;
	}
	/**
	 * An expectation for checking that there is at least one element present on a
	 * web page.
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public List<WebElement> waitForElementsPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

	}

	/**
	 * An expectation for checking that all elements present on the web page that
	 * match the locator are visible. Visibility means that the elements are not
	 * only displayed but also have a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public List<WebElement> waitForElementsVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param locator
	 * @param timeout
	 */
	public void clickElementWhenReady(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public Alert waitForAlert(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String getAlertText(int timeout) {
		return waitForAlert(timeout).getText();
	}

	public void acceptAlert(int timeout) {
		waitForAlert(timeout).accept();
	}

	public void dismissAlert(int timeout) {
		waitForAlert(timeout).dismiss();
	}

	public void sendKeysInAlert(int timeout, String value) {
		waitForAlert(timeout).sendKeys(value);
	}

	public String waitForTitleContains(String fractionTitleValue, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

		try {
			wait.until(ExpectedConditions.titleContains(fractionTitleValue));
		} catch (TimeoutException e) {
			System.out.println("expected title value : " + fractionTitleValue + " is not present");
		}

		return driver.getTitle();
	}

	public String waitForTitleIs(String expectedTitleValue, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

		try {
			wait.until(ExpectedConditions.titleIs(expectedTitleValue));
		} catch (TimeoutException e) {
			System.out.println("expected title value : " + expectedTitleValue + " is not present");
		}

		return driver.getTitle();
	}

	public String waitForURLContains(String fractionURLValue, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

		try {
			wait.until(ExpectedConditions.urlContains(fractionURLValue));
		} catch (TimeoutException e) {
			System.out.println("expected URL value : " + fractionURLValue + " is not present");
		}

		return driver.getCurrentUrl();
	}

	public String waitForURLIs(String epxectedURLValue, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

		try {
			wait.until(ExpectedConditions.urlToBe(epxectedURLValue));
		} catch (TimeoutException e) {
			System.out.println("expected URL value : " + epxectedURLValue + " is not present");
		}

		return driver.getCurrentUrl();
	}

	public boolean waitForWindow(int expectedNoOfWindows, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			return wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNoOfWindows));
		} catch (TimeoutException e) {
			System.out.println("expected number of windows are correct");
			return false;
		}
	}

	public boolean waitForFrame(By frameLocator, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
			return true;
		} catch (TimeoutException e) {
			System.out.println("frame is not present on the page");
			return false;
		}

	}

	public boolean waitForFrame(int frameIndex, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
			return true;
		} catch (TimeoutException e) {
			System.out.println("frame is not present on the page");
			return false;
		}

	}

	public boolean waitForFrame(String frameNameOrID, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNameOrID));
			return true;
		} catch (TimeoutException e) {
			System.out.println("frame is not present on the page");
			return false;
		}

	}
	
	
	//******************FluentWait Utils************//
	
	public WebElement waitForElementVisibleWithFluentWait(By locator, int timeout, int pollingtime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofMillis(pollingtime))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.withMessage("=====ELEMENT NOT VISIBLE ON THE PAGE====");
		
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	
	public WebElement waitForElementPresenceWithFluentWait(By locator, int timeout, int pollingtime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofMillis(pollingtime))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.withMessage("=====ELEMENT NOT PRESENT ON THE PAGE====");
		
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	
	
	public void waitForFrameWithFluentWait(By frameLocator, int timeout, int pollingtime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofMillis(pollingtime))
				.ignoring(NoSuchFrameException.class)
				.withMessage("=====FRAME NOT VISIBLE ON THE PAGE====");
		
		 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}
	
	
	public Alert waitForAlertWithFluentWait(int timeout, int pollingtime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofMillis(pollingtime))
				.ignoring(NoAlertPresentException.class)
				.withMessage("=====Alert NOT VISIBLE ON THE PAGE====");
		
		return wait.until(ExpectedConditions.alertIsPresent());
	}

}
