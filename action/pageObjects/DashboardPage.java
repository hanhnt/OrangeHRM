package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.CommonUIs;

public class DashboardPage extends BasePage{
	WebDriver driver;
	public DashboardPage(WebDriver driver) {
		this.driver=driver;
	}
//	public EmployeeListPage openEmployeeList(String mainMenu, String subMenu) {
////		waitForElementVisible(driver, CommonUIs.MAIN_MENU_BY_NAME, mainMenu);
////		clickToElement(driver, CommonUIs.MAIN_MENU_BY_NAME, mainMenu);
//		
//		waitForElementVisible(driver, CommonUIs.SUB_MENU_BY_NAME, subMenu);
//		clickToElement(driver, CommonUIs.SUB_MENU_BY_NAME, subMenu);
//		return PageGeneratorManager.getEmployeeListPage(driver);
//	}
}
