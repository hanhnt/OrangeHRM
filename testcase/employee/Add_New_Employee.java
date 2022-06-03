package employee;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.AddEmployeePage;
import pageObjects.LoginPage;
import pageObjects.DashboardPage;
import pageObjects.EmployeeListPage;
import pageObjects.PageGeneratorManager;
import pageObjects.PersonalDetailPage;

public class Add_New_Employee extends BaseTest{
	WebDriver driver;
	LoginPage adminLoginPage;
	DashboardPage dashboardPage;
	EmployeeListPage employeeListPage;
	AddEmployeePage addEmployeePage;
	PersonalDetailPage persionalDetailPage;
	
	@Parameters({"browserName","url"})
	@BeforeClass
	public void beforeClass(String browser, String url) {
		log.info("Pre-condition-Step 01: Open browser "+browser+ "with url"+url);
		driver=getBroswerDriver(browser, url);
		adminLoginPage=PageGeneratorManager.getAdminLoginPageO(driver);
		log.info("Pre-condition-Step 02: Login page with admin role");	
		dashboardPage=adminLoginPage.loginWithAdminAcc("Admin","admin123", "txtUsername","txtPassword","btnLogin");
	}
	
	@Test
	public void Employee_01_Add_New_Employee() {
		log.info("Add_New_Emp Step 01: Open employee list");
		employeeListPage = dashboardPage.openEmployeeList();
		
		log.info("Add_New_Emp Step 02: Click to add button");
		addEmployeePage=employeeListPage.clickIntoAddButton();
		
		log.info("Add_New_Emp Step 03: Input fullname");
		addEmployeePage.inputFirstName("Le");
		
		log.info("Add_New_Emp Step 04: Input lastname");
		addEmployeePage.inputLastName("An");
		
		log.info("Add_New_Emp Step 05: Get employee ID");
		addEmployeePage.getEmployeeID();
		
		log.info("Add_New_Emp Step 06: Click into 'Create Login Details'");
		addEmployeePage.clickIntoCreateLoginDetails();
		
		log.info("Add_New_Emp Step 07: Input data into Username");
		addEmployeePage.inputUserName("LeAn");
		
		log.info("Add_New_Emp Step 08: Input data into Password");
		addEmployeePage.inputPassword("lean1234");
		
		log.info("Add_New_Emp Step 09: Input data into Confirm Password");
		addEmployeePage.inputConfirmPassword("lean1234");
		
		log.info("Add_New_Emp Step 10: Select Disabe at Status");
		addEmployeePage.selectDisable("Disable");
		
		log.info("Add_New_Emp Step 11: Click into save button and Come to persional Detail Page");
		persionalDetailPage=addEmployeePage.clickToSaveButton();
		
		log.info("Add_New_Emp Step 12: Come to employee list page");
		employeeListPage= persionalDetailPage.openEmployeeList();
		
		log.info("Add_New_Emp Step 13: Input data into Employee name");
		employeeListPage.inputEmployeeName("Le An");
		
		log.info("Add_New_Emp Step 14: Click into Search button");
		employeeListPage.clickToSearchButton();
		
		log.info("Add_New_Emp Step 15: Verify Employee infor display correctly at result table");
		verifyTrue(employeeListPage.isDisplayEmployeeInforCorrectly("","",""));
		
		
	}
	
	@Test
	public void Employee_02_Upload_Avatar() {
		
	}
	
	@Test
	public void Employee_03_Personal_Detail() {
		
	}
	
	@Test
	public void Employee_04_Contact_Detail() {
		
	}
	
	@Test
	public void Employee_05_Emergency_Detail() {
		
	}
	
	@Test
	public void Employee_06_Assign_Dependent() {
		
	}
	
	@Test
	public void Employee_07_Edit_View_Job() {
		
	}
	
	@Test
	public void Employee_08_Edit_View_Salary() {
		
	}
	
	@Test
	public void Employee_09_Edit_View_Tax() {
		
	}
	
	@Test
	public void Employee_10_Qualification() {
		
	}
	
	@Test
	public void Employee_11_Search_Employee() {
		
	}

}
