package pageUIs;

public class EmployeeListPageUI {
	public static final String EMPLOYEE_NAME_TEXTBOX = "xpath=//input[@name='empsearch[employee_name][empName]']";
	public static final String SEARCH_RESULT = "xpath=//a[contains(text(),'%s')]/parent::td/following-sibling::td/a[contains(text(),'%s')]";
	public static final String LOADING_ICON = "xpath=//input[@class='ac_loading']";
}
