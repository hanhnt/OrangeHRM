package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.CommonUIs;
import pageUIs.EmployeeListPageUI;

public class EmployeeListPage extends BasePage{
	WebDriver driver;
	public EmployeeListPage(WebDriver driver) {
		this.driver=driver;
	}
//	public AddEmployeePage clickIntoButtonByID(String buttonName) {
//		waitForElementVisible(driver, CommonUIs.BUTTON_BY_ID, buttonName);
//		clickToElement(driver,CommonUIs.BUTTON_BY_ID, buttonName);
//		return PageGeneratorManager.getAddEmployeePage(driver);
//	}
	public void inputEmployeeName(String valueInput) {
		waitForElementInvisible(driver, EmployeeListPageUI.LOADING_ICON);
		sendkeyToElement(driver, EmployeeListPageUI.EMPLOYEE_NAME_TEXTBOX, valueInput);
	}
	public String getEmployeeInfor(String tableName, String columnName, String rowIndex) {
		int columnIndex=getElementSize(driver, CommonUIs.COLUMN_INDEX_BY_TABLE_NAME_AND_COLUMNAME,tableName,columnName)+1;		
		return getElementText(driver, CommonUIs.ROW_INDEX_BY_TABLE_NAME_AND_COLUMN_INDEX, tableName, rowIndex, String.valueOf(columnIndex) );
	}	
	
}
