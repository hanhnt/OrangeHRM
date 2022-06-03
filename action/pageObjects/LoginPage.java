package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.LoginPageUI;

public class LoginPage extends BasePage{
	WebDriver driver;
	public LoginPage (WebDriver driver) {
		this.driver=driver;
	}
	
	
	public DashboardPage loginWithAdminAcc(String username, String password, String txtUsername, String txtPassword, String btnLogin) {
		waitForAllElementVisible(driver, LoginPageUI.LOGIN_TEXTBOX_NAME, txtUsername);
		sendkeyToElement(driver, LoginPageUI.LOGIN_TEXTBOX_NAME, username, txtUsername);
		
		waitForAllElementVisible(driver, LoginPageUI.LOGIN_TEXTBOX_NAME, txtPassword);
		sendkeyToElement(driver, LoginPageUI.LOGIN_TEXTBOX_NAME, password, txtPassword);
		
		waitForAllElementVisible(driver, LoginPageUI.LOGIN_TEXTBOX_NAME, btnLogin);
		clickToElement(driver, LoginPageUI.LOGIN_TEXTBOX_NAME, btnLogin);
		return PageGeneratorManager.getDashboardPageO(driver);
	}
	
}
