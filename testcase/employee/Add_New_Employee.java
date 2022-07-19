package employee;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Verify;

import commons.BaseTest;
import pageObjects.AddEmployeePage;
import pageObjects.LoginPage;
import pageObjects.DashboardPage;
import pageObjects.EmployeeListPage;
import pageObjects.PageGeneratorManager;
import pageObjects.PersonalDetailPage;

public class Add_New_Employee extends BaseTest {
	WebDriver driver;
	LoginPage adminLoginPage;
	DashboardPage dashboardPage;
	EmployeeListPage employeeListPage;
	AddEmployeePage addEmployeePage;
	PersonalDetailPage personalDetailPage;
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browser, String url) {
		log.info("Pre-condition-Step 01: Open browser "+browser+ "with url"+url);
		driver=getBroswerDriver(browser, url);
		adminLoginPage=PageGeneratorManager.getLoginPageO(driver);
		log.info("Pre-condition-Step 02: Login page with admin role");	
		dashboardPage=adminLoginPage.loginWithAdminAcc("Admin","admin123", "txtUsername","txtPassword","btnLogin");
	}
	
	@Test
	public void Employee_01_Add_New_Employee() {
		log.info("Add_New_Emp Step 01: Open employee list");
		 dashboardPage.openSubMenu(driver,"PIM","viewEmployeeList");
		 employeeListPage=PageGeneratorManager.getEmployeeListPage(driver);
		
		log.info("Add_New_Emp Step 02: Click to add button");
		employeeListPage.clickIntoButtonByID(driver,"btnAdd");
		addEmployeePage=PageGeneratorManager.getAddEmployeePage(driver);
		
		log.info("Add_New_Emp Step 03: Input fullname");
		addEmployeePage.inputTextboxtName(driver,"Le","firstName");
		
		log.info("Add_New_Emp Step 04: Input lastname");
		addEmployeePage.inputTextboxtName(driver,"An3","lastName");
		
		log.info("Add_New_Emp Step 05: Get employee ID");
		addEmployeePage.getEmployeeID();
		
		log.info("Add_New_Emp Step 06: Click into 'Create Login Details'");
		addEmployeePage.clickIntoCreateLoginDetails();
		
		log.info("Add_New_Emp Step 07: Input data into Username");
		addEmployeePage.inputTextboxtName(driver, "LeAn3","user_name");
		
		log.info("Add_New_Emp Step 08: Input data into Password");
		addEmployeePage.inputTextboxtName(driver,"lean1234","user_password");
		
		log.info("Add_New_Emp Step 09: Input data into Confirm Password");
		addEmployeePage.inputTextboxtName(driver,"lean1234","re_password");
	
		log.info("Add_New_Emp Step 10: Select Disabe at Status");
		addEmployeePage.selectDisable("Disabled");
		
		log.info("Add_New_Emp Step 11: Click into save button and Come to persional Detail Page");
		addEmployeePage.clickIntoButtonByID("btnSave");
		personalDetailPage=PageGeneratorManager.getPersonalDetailPage(driver);
		
		log.info("Add_New_Emp Step 12: Navigate to persional Detail Page");
		verifyTrue(personalDetailPage.openPersonalDetailPage());
		
		log.info("Add_New_Emp Step 13: Come to employee list page");
		personalDetailPage.openSubMenu(driver,"PIM","viewEmployeeList");
		employeeListPage=PageGeneratorManager.getEmployeeListPage(driver);
		
		log.info("Add_New_Emp Step 14: Input data into Employee name");
		employeeListPage.inputEmployeeName("Admin A");
		
		log.info("Add_New_Emp Step 15: Click into Search button");
		employeeListPage.clickIntoButtonByID(driver,"searchBtn");
				
		log.info("Add_New_Emp Step 16: Verify Employee infor display correctly at result table");
		verifyEquals(employeeListPage.getEmployeeInfor("resultTable","Last Name","1"),"Admin");
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
	@AfterClass(alwaysRun=true)
	public void afterClass() {
		cleanBrowserAndDriver();
	}
}
