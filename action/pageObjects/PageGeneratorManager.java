package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static LoginPage getLoginPageO(WebDriver driver) {
		return new LoginPage(driver);
	}
	
	public static DashboardPage getDashboardPage(WebDriver driver) {
		return new DashboardPage(driver);
	}

	public static EmployeeListPage getEmployeeListPage(WebDriver driver) {
		return new EmployeeListPage(driver);
	}
	
	public static PersonalDetailPage getPersonalDetailPage(WebDriver driver) {
		return new PersonalDetailPage(driver);
	}
	
	public static AddEmployeePage getAddEmployeePage(WebDriver driver) {
		return new AddEmployeePage(driver);
	}
}
