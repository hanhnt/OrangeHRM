package commons;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.AddEmployeePage;
import pageObjects.PageGeneratorManager;
import pageUIs.AddEmployeePageUI;
import pageUIs.CommonUIs;

public class BasePage {
	public void openPageUlr(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	
	protected String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	protected String getPageUrl(WebDriver driver) {
		 return driver.getCurrentUrl();
	}
	
	protected String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}
	
	protected void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	protected void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	protected Alert waitForAlertPresent(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
		
	}
	
	protected void acceptAlert(WebDriver driver) {
		waitForAlertPresent(driver).accept();
		//driver.switchTo().alert().accept();
	}
	
	protected void cancelAlert(WebDriver driver) {
		waitForAlertPresent(driver).dismiss();
	}
	
	protected String getTextAlert(WebDriver driver) {
		return waitForAlertPresent(driver).getText();
	}
	
	protected void sendkeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresent(driver).sendKeys(textValue);
	}
	
	protected void switchToWindowByID(WebDriver driver, String windowID) {
		Set<String> allWindows=driver.getWindowHandles();
		for (String windows: allWindows) {
			if(!windows.equals(windowID)) {
				driver.switchTo().window(windows);
			} 
		}
	}
	
	protected void closeAllWindowWithoutParentWindow(WebDriver driver, String parentWindow) {
		Set<String> allWindow=driver.getWindowHandles();
		for (String window: allWindow) {
			if(!window.equals(parentWindow)) {
				driver.switchTo().window(window);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
	}
	
	protected void switchWindowByTitle(WebDriver driver, String expectedPageTitle) {
		Set<String> allWindows=driver.getWindowHandles();
		for (String window: allWindows) {
			driver.switchTo().window(window);
			
			String actualTitle=driver.getTitle();
			if(actualTitle.equals(expectedPageTitle)) {break;}			
		}
	}
	
//	protected By getByXpath(String xpathLocator) {
//		return By.xpath(xpathLocator);
//	}
	
	protected By getByLocator(String locatorType) {
		By by=null;
		if (locatorType.startsWith("id=")) {
			by=By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("xpath=")) {
			by=By.xpath(locatorType.substring(6));
		} else if (locatorType.startsWith("css=")) {
			by=By.cssSelector(locatorType.substring(4));
		} else if (locatorType.startsWith("name=")) {
			by=By.name(locatorType.substring(5));
		} else if (locatorType.startsWith("className=")) {
			by=By.className(locatorType.substring(10));
		} else {throw new RuntimeException("Locator type is not supported");}
		return by;
	}
	
	protected String getDynamicXpath(String locatorType, String...restParameters) {
		if(locatorType.startsWith("xpath")) {
			locatorType=String.format(locatorType, (Object[])restParameters);
		} else if (locatorType.startsWith("id")) {
			locatorType=String.format(locatorType, (Object[])restParameters);}
		return locatorType;
	}
	
	protected WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}
	protected WebElement getWebElement(WebDriver driver, String locatorType, String...restParameters) {
		locatorType= getDynamicXpath(locatorType, restParameters);
		return driver.findElement(getByLocator(locatorType));
	}
	
	protected List<WebElement> getListElement(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}
	
	protected void clickToElement(WebDriver driver, String locatorType) {
		getWebElement(driver, locatorType).click();
	}
	
	protected void clickToElement(WebDriver driver, String locatorType, String...restParameters) {
		getWebElement(driver, getDynamicXpath(locatorType,restParameters)).click();
	}
	
	protected void sendkeyToElement(WebDriver driver, String locatorType, String textValue) {
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}
	
	protected void sendkeyToElement(WebDriver driver, String locatorType, String textValue, String...restParameters) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType,restParameters));
		element.clear();
		element.sendKeys(textValue);
	}
	
	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locatorType),key).perform();		
	}
	
	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String...params) {
		Actions action = new Actions(driver);	
		locatorType= getDynamicXpath(locatorType, params);
		action.sendKeys(getWebElement(driver, locatorType),key).perform();
	}
	
	protected String getElementText(WebDriver driver, String locatorType, String...restParameters) {
		return getWebElement(driver, getDynamicXpath(locatorType,restParameters)).getText().trim();
	}
	
	protected String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText().trim();
	}
	
	protected void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByValue(textItem);
	}
	
	protected void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem, String...restParameters) {
		locatorType=getDynamicXpath(locatorType, restParameters);
		Select select = new Select(getWebElement(driver, locatorType));
		//select.selectByValue(textItem);
		select.selectByVisibleText(textItem);
	}
	
	protected String getItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}
	
	protected boolean isDropdownMultiple(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.isMultiple();
	}
	
	protected void selectItemInDropDownList(WebDriver driver, String locatorType, String childXpath, String expectedText) {
		WebDriverWait explicitWait= new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
		getWebElement(driver, locatorType).click();		
		List<WebElement> allElements = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));
		for (WebElement element : allElements) {
			if (element.getText().trim().equals(expectedText)) {
				if (element.isDisplayed()) {
					element.click();
				} else {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
					element.click();
				}
				break;
			}

		}
	}
	
	protected String getAttribute(WebDriver driver, String locatorType, String attributeName) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}
	
	protected String getAttribute(WebDriver driver, String locatorType, String attributeName, String...restParameters) {
		locatorType=getDynamicXpath(locatorType, restParameters);
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}
	
	protected String getCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	}
	
	protected String convertRgbaToHex(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	protected int getElementSize(WebDriver driver, String locatorType) {
		return getListElement(driver, locatorType).size();
	}
	
	protected int getElementSize(WebDriver driver, String locatorType, String...restParameters) {
		return getListElement(driver, getDynamicXpath(locatorType, restParameters)).size();
	}
	
	protected void checkToDefaultCheckbox(WebDriver driver, String locatorType) {
		WebElement element= getWebElement(driver, locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	protected void UnCheckToDefaultCheckbox(WebDriver driver, String locatorType) {
		WebElement element= getWebElement(driver, locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}
	
	protected boolean isElementDisplay(WebDriver driver, String locatorType) {
		try {
			return getWebElement(driver, locatorType).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	protected boolean isElementUndisplay(WebDriver driver, String locatorType) {
		System.out.print("Start time: " + new Date().toString());
		overrideGlobalTimeout(driver, shorTimeout); //set timeout is shortTimeout before findElement.
		List<WebElement> elements= getListElement(driver, locatorType); //find element.
		overrideGlobalTimeout(driver, longTimeout); //set timeout is longTimeout after findElement to not affect other function.
		if(elements.size()==0) {
			return true; //element not in DOM
		}
		if(elements.size()>0 && !elements.get(0).isDisplayed()){
			return true; //element in DOM but not display
		}
		else return false; //element in DOM and display
	}
	
	public void overrideGlobalTimeout(WebDriver driver,long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}
	
	protected boolean isElementDisplay(WebDriver driver, String locatorType, String...restParameters) {
		return getWebElement(driver, getDynamicXpath(locatorType, restParameters)).isDisplayed();
	}
	
	protected boolean isElementEnable(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}
	
	protected boolean isElementSelect(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}
	
	protected void switchFrameIfram(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}
	
	protected void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	protected void hoverMouseToElement(WebDriver driver, String locatorType) {
		Actions action= new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}
	
	protected void rightClickToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.contextClick(getWebElement(driver, locatorType)).perform();
	}

	protected void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor= (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	protected void hightlightElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor= (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	protected void clickToElementByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor= (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver,locatorType));
	}

	protected void scrollToElementOnTop(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor= (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver,locatorType));
	}

	protected void scrollToElementOnDown(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor= (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver,locatorType));
	}
	
	protected void scrollToElementOnDown(WebDriver driver, String locatorType, String... restParameters) {
		locatorType=getDynamicXpath(locatorType, restParameters);
		JavascriptExecutor jsExecutor= (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver,locatorType));
	}
	
	protected void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
		JavascriptExecutor jsExecutor= (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver,locatorType));
	}

	protected String getElementValidationMessage(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor= (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver,locatorType));
	}

	protected boolean isImageLoaded(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor= (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getWebElement(driver, locatorType));
		if (status) {
			return true;
		}
		return false;
	}
	
	protected boolean isJQueryLoadedSuccess1(WebDriver driver) {
		WebDriverWait explicitWait;
		explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor;
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> JQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return（window.jQuery!= null）&&（jQuery.active == 0);");
			}
		};
		return explicitWait.until(JQueryLoad);
	}
	
	protected void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}
	
	protected void waitForElementVisible(WebDriver driver, String locatorType, String...restParameters) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, restParameters))));
	}
	
	protected void waitForAllElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait= new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}
	
	protected void waitForAllElementVisible(WebDriver driver, String locatorType, String...restParameters) {
		WebDriverWait explicitWait= new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, restParameters))));
	}
	
	protected void waitForElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shorTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}
	
	protected void waitForElementInvisible(WebDriver driver, String locatorType, String...restParameters) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shorTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}
	
	protected void waitForAllElementInvisible(WebDriver driver, String locatorType, String...restParameters) {
		WebDriverWait explicitWait= new WebDriverWait(driver, shorTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListElement(driver, getDynamicXpath(locatorType, restParameters))));
	}
	
	protected void waitForElementClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait= new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}
	
	protected void waitForElementClickable(WebDriver driver, String locatorType, String...restParameters) {
		WebDriverWait explicitWait= new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, restParameters))));
	}
	
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void openMainMenu(WebDriver driver, String mainMenu) {
		waitForElementVisible(driver, CommonUIs.MAIN_MENU_BY_NAME, mainMenu);
		clickToElement(driver, CommonUIs.MAIN_MENU_BY_NAME, mainMenu);
	}
	
	public void openSubMenu(WebDriver driver, String mainMenu, String subMenu) {
		waitForElementVisible(driver, CommonUIs.MAIN_MENU_BY_NAME, mainMenu);
		clickToElement(driver, CommonUIs.MAIN_MENU_BY_NAME, mainMenu);
		waitForElementVisible(driver, CommonUIs.SUB_MENU_BY_NAME, subMenu);
		clickToElement(driver, CommonUIs.SUB_MENU_BY_NAME, subMenu);
	}
	
	public void clickIntoButtonByID(WebDriver driver,String buttonName) {
		waitForElementVisible(driver, CommonUIs.BUTTON_BY_ID, buttonName);
		clickToElement(driver,CommonUIs.BUTTON_BY_ID, buttonName);
	}
	
	public void inputTextboxtName(WebDriver driver, String valueInput, String txtName) {
		waitForElementVisible(driver, AddEmployeePageUI.TEXTBOX_BY_NAME, txtName);
		sendkeyToElement(driver, AddEmployeePageUI.TEXTBOX_BY_NAME, valueInput, txtName);		
	}
	
	private long longTimeout=GloalConstants.LONG_TIME_OUT;
	private long shorTimeout=GloalConstants.SHORT_TIME_OUT;
}
