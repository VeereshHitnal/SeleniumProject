package com.vtiger.comcast.utilities;

import java.time.LocalDateTime;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.vtiger.contact.ContactInformationPage;
import com.vtiger.contact.ContactsPage;
import com.vtiger.contact.CreatingNewContactPage;
import com.vtiger.contact.HomePage;
import com.vtiger.contact.LoginPage;

public class BaseClass {

public	WebDriver driver;
public	static WebDriver sDriver;
public ExcelUtility eu=new ExcelUtility();

public FileUtility file=new FileUtility();
public WebDriverUtilities wu=new WebDriverUtilities();
ExtentHtmlReporter reporter;
ExtentReports reports;
ExtentTest test;

@BeforeSuite
public void suiteExe() {
	String ldt = LocalDateTime.now().toString().replace(":", "_");
	reporter=new ExtentHtmlReporter("../SeleniumProject/ExtentReports/SDET20_"+ldt+".html");
	System.out.println("====SuiteExecution=====");
	reporter.config().setDocumentTitle("SDET");
	reporter.config().setTheme(Theme.DARK);
	reports=new ExtentReports();
	reports.attachReporter(reporter);
}

/*@BeforeClass
public void configBC() {
	
	driver=new ChromeDriver();
	wu.waitUntilPageLoad(driver);
	driver.manage().window().maximize();
}*/

@BeforeClass
public void configBC() {
	System.out.println("=========Launch the browser=====");
	String BROWSER =System.getProperty("browser");
	if(BROWSER.equals("chrome")) {
	driver=new ChromeDriver();
	}else if(BROWSER.equals("firefox")){
		driver=new FirefoxDriver();
	}else {
		driver=new ChromeDriver();
	}
	wu.waitUntilPageLoad(driver);
	driver.manage().window().maximize();
	}


@BeforeMethod(groups = {"ST", "regressionTest"})
public void configBM() throws Throwable {
	
	String URL = System.getProperty("url");
	
	String USERNAME =file.getPropertyKeyValue("username");

	String PASSWORD =file.getPropertyKeyValue("password");

	driver.get(URL);
	
	Assert.assertEquals(driver.getCurrentUrl(),URL);

		System.out.println("URL is correct");

	LoginPage lp = new LoginPage(driver);
	Assert.assertTrue(lp.getSubmitButton().isDisplayed());
	
		System.out.println("Login Page Displayed");
	
	HomePage hp = lp.loginAction(USERNAME, PASSWORD);
	String homePageText = eu.getDataFromExcel("Sheet3", 1, 1);
	Assert.assertEquals(hp.homePageText(), homePageText);
	
		System.out.println("Home Page Displayed");
	

	hp.getContactsLink().click();

	ContactsPage contactPage = new ContactsPage(driver);
	contactPage.getCreateContactImg().click();
	String contactsPageText = eu.getDataFromExcel("Sheet3", 2, 1);
	Assert.assertEquals(contactPage.contactsPageOpenConfirmationText(), contactsPageText);
	
		System.out.println("Contacts List Page Displayed");
	


	CreatingNewContactPage creatingNewContact = new CreatingNewContactPage(driver);

	for (int i = 1; i <= eu.getRowCount("Sheet1"); i++) {
		

		String nameTitle =eu.getDataFromExcel("Sheet1", i, 0);
		String firstName = eu.getDataFromExcel("Sheet1", i, 1);
		String lastName = eu.getDataFromExcel("Sheet1", i, 2);

		
		if (lastName.isEmpty()) {
			System.out.println("Last Name cannot be empty");

		} else {
			creatingNewContact.createNewContact(nameTitle, firstName, lastName);
			Thread.sleep(3000);
			ContactInformationPage ci = new ContactInformationPage(driver);
			boolean b= ci.contactInformationCreationText().contains((lastName + " " + firstName)) ;
			Assert.assertTrue(b);
			System.out.println(lastName + " Contact created successfully");
			
			if (i + 1 <= eu.getRowCount("Sheet1")) {
				contactPage.getCreateContactImg().click();
			}

		}

	}
	hp.getContactsLink().click();
}

@AfterMethod(groups = {"ST", "regressionTest"})
public void configAM() throws Throwable {
	wu.customWait();
	HomePage hp=new HomePage(driver);
	hp.logout();
}

@AfterClass(groups = {"ST", "regressionTest"})
public void configAC() {
	driver.quit();
}
@AfterSuite
public void flushReport() {
	reports.flush();
}

}
