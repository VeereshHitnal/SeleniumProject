package jdbc;


import java.io.IOException;
import java.util.Properties;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.vtiger.comcast.utilities.ExcelUtility;
import com.vtiger.comcast.utilities.FileUtility;
import com.vtiger.comcast.utilities.WebDriverUtilities;
import com.vtiger.contact.ContactInformationPage;
import com.vtiger.contact.ContactsPage;
import com.vtiger.contact.CreatingNewContactPage;
import com.vtiger.contact.EditingContactInformationPage;
import com.vtiger.contact.HomePage;
import com.vtiger.contact.LoginPage;

public class EditContactTest_TC15 {

	@Test
	public void editContactTest() throws Throwable {
		WebDriver driver = new ChromeDriver();
		
		FileUtility file= new FileUtility();
		WebDriverUtilities wu=new WebDriverUtilities();
		ExcelUtility eu=new ExcelUtility();
		
		
		String URL = file.getPropertyKeyValue("url");
		
		String USERNAME =file.getPropertyKeyValue("username");
	
		String PASSWORD =file.getPropertyKeyValue("password");

		driver.get(URL);
		if (driver.getCurrentUrl().equals(URL)) {
			System.out.println("URL is correct");
		} else {
			System.out.println("URL is not correct");
		}
		
		LoginPage lp = new LoginPage(driver);
		if(lp.getSubmitButton().isDisplayed()) {
			System.out.println("Login Page Displayed");
		}else {
			System.out.println("Login Page Not Displayed");
		}

		wu.customWait();


		HomePage hp = lp.loginAction(USERNAME, PASSWORD);
		String homePageText = eu.getDataFromExcel("Sheet3", 1, 1);
		if (hp.homePageText().contains(homePageText)) {
			System.out.println("Home Page Displayed");
		} else {
			System.out.println("Home Page not Displayed");
		}

		hp.getContactsLink().click();

		ContactsPage contactPage = new ContactsPage(driver);
		contactPage.getCreateContactImg().click();
		String contactsPageText = eu.getDataFromExcel("Sheet3", 2, 1);
		if (contactPage.contactsPageOpenConfirmationText().equals(contactsPageText)) {
			System.out.println("Contacts List Page Displayed");
		} else {
			System.out.println("Contacts List Page Not Displayed");
		}


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

				if (ci.contactInformationCreationText().contains(lastName + " " + firstName)) {
					System.out.println(lastName + " Contact created successfully");
				} else {
					System.out.println(lastName + " Contact not created successfully");
				}

				if (i + 1 <= eu.getRowCount("Sheet1")) {
					contactPage.getCreateContactImg().click();
				}

			}

		}
		hp.getContactsLink().click();
		EditingContactInformationPage editPage = new EditingContactInformationPage(driver);
		for (int i = 1; i <eu.getRowCount("Sheet2"); i++) {

			String lastName = eu.getDataFromExcel("Sheet2", i, 0);
			String emailId = eu.getDataFromExcel("Sheet2", i, 1);
			wu.customWait();
			
			if(emailId.isEmpty()) {
				System.out.println(lastName+" empty email id, can't update the contact");
			}else {
			contactPage.editContactByLastName(lastName);
			 String emailTF = editPage.getEmailIdTextField().getText();
			if(emailTF.isEmpty()) {
				editPage.getEmailIdTextField().clear();
			}
			editPage.updateEmail(emailId);
			wu.customWait();
			if(contactPage.verifyEmail(lastName, emailId)==true) {
				System.out.println(lastName+" Email id updated to "+emailId);
			}else {
				System.out.println("Process Not Happend");
			}
			}
		}
		hp.logout();
		driver.quit();

	}

}
