package pageObjects;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

import commons.BasePage;
import pageUIs.AddEmployeePageUI;
import pageUIs.CommonUIs;
import pageUIs.PersonalDetailPageUI;

public class AddEmployeePage extends BasePage{
	WebDriver driver;
	public AddEmployeePage(WebDriver driver) {
		this.driver=driver;
	}

	public void getEmployeeID() {
		// TODO Auto-generated method stub
		
	}
	public void clickIntoCreateLoginDetails() {
		waitForElementVisible(driver, AddEmployeePageUI.CREATE_LOGIN_DETAIL_CHECKBOX);
		clickToElement(driver, AddEmployeePageUI.CREATE_LOGIN_DETAIL_CHECKBOX);
		
	}

	public void selectDisable(String statusValue) {
		waitForAllElementVisible(driver, AddEmployeePageUI.STATUS_DROPDOWN);
		selectItemInDefaultDropdown(driver, AddEmployeePageUI.STATUS_DROPDOWN,statusValue);
		
	}
	
	public PersonalDetailPage clickIntoButtonByID(String buttonName) {
		waitForElementClickable(driver, CommonUIs.BUTTON_BY_ID,buttonName);
		clickToElement(driver, CommonUIs.BUTTON_BY_ID,buttonName);
		return PageGeneratorManager.getPersonalDetailPage(driver);
	}
	
	
}
