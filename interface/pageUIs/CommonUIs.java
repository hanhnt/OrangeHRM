package pageUIs;

public class CommonUIs {
	public static final String MAIN_MENU_BY_NAME = "xpath=//div[@id='mainMenu']//b[text()='%s']";
	public static final String SUB_MENU_BY_NAME = "xpath=//a[@id='menu_pim_%s']";
	public static final String BUTTON_BY_ID = "id=%s";
	public static final String ROW_INDEX_BY_TABLE_NAME_AND_COLUMN_INDEX="xpath=//table[@id='%s']//tr[%s]/td[%s]";
	public static final String COLUMN_INDEX_BY_TABLE_NAME_AND_COLUMNAME="xpath=//table[@id='resultTable']//th[string()='Last Name']/preceding-sibling::th";
}
