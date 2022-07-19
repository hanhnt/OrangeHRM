package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.CommonUIs;
import pageUIs.PersonalDetailPageUI;

public class PersonalDetailPage extends BasePage{
	WebDriver driver;
	public PersonalDetailPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public boolean openPersonalDetailPage() {
		waitForElementVisible(driver, PersonalDetailPageUI.HEADER_TEXT);
		return isElementDisplay(driver, PersonalDetailPageUI.HEADER_TEXT);
	}
}
