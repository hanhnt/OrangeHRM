package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static LoginPage getAdminLoginPageO(WebDriver driver) {
		return new LoginPage(driver);
	}
	
	public static DashboardPage getDashboardPageO(WebDriver driver) {
		return new DashboardPage(driver);
	}

}
