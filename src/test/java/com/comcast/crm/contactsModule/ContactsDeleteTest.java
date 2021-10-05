package com.comcast.crm.contactsModule;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.UnhandledAlertException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.vtiger.comcast.utilities.BaseClass;
import com.vtiger.contact.ContactsPage;
import com.vtiger.contact.EditingContactInformationPage;

@Listeners(com.vtiger.comcast.utilities.LisImplementClass.class)
public class ContactsDeleteTest extends BaseClass {

	@Test
	public void contactsSelectAndDeleteTest() throws Throwable {
		
			ContactsPage contactPage = new ContactsPage(driver);
			for (int i = 1; i < eu.getRowCount("Sheet2"); i++) {

				String ExcelLastName = eu.getDataFromExcel("Sheet2", i, 0);
				
				Assert.assertTrue(contactPage.isContactExist(ExcelLastName));			
					contactPage.selectContactByLastName(ExcelLastName);
					System.out.println(ExcelLastName + " contact selected");
				
					if (i + 1 == eu.getRowCount("Sheet2")) {

						try {
							contactPage.getDeleteButton().click();
							wu.acceptAlert(driver);
							wu.customWait();
							System.out.println("Contacts deleted");

						} catch (UnhandledAlertException e) {

						}

					}

				}
			}
		
	

	//@Test
	public void selectAndDeleteTest() throws Throwable, IOException {
		for (int i = 1; i < eu.getRowCount("Sheet2"); i++) {
			ContactsPage contactPage = new ContactsPage(driver);
			String LastName = eu.getDataFromExcel("Sheet2", i, 0);
			contactPage.deleteByLastName(LastName);
			System.out.println(LastName + " Contact Deleted Successfully");
		}
	}

	public void editContactsTest() throws Throwable, IOException, InterruptedException {
		ContactsPage contactPage = new ContactsPage(driver);
		EditingContactInformationPage editPage = new EditingContactInformationPage(driver);
		for (int i = 1; i < eu.getRowCount("Sheet2"); i++) {

			String lastName = eu.getDataFromExcel("Sheet2", i, 0);
			String emailId = eu.getDataFromExcel("Sheet2", i, 1);
			wu.customWait();

			if (emailId.isEmpty()) {
				System.out.println(lastName + " empty email id, can't update the contact");
			} else {
				contactPage.editContactByLastName(lastName);
				String emailTF = editPage.getEmailIdTextField().getText();
				if (emailTF.isEmpty()) {
					editPage.getEmailIdTextField().clear();
				}
				editPage.updateEmail(emailId);
				wu.customWait();
				if (contactPage.verifyEmail(lastName, emailId) == true) {
					System.out.println(lastName + " Email id updated to " + emailId);
				} else {
					System.out.println("Process Not Happend");
				}
			}
		}
	}

}
