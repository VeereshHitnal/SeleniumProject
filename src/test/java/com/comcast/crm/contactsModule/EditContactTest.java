package com.comcast.crm.contactsModule;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.UnhandledAlertException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.vtiger.comcast.utilities.BaseClass;
import com.vtiger.contact.ContactsPage;
import com.vtiger.contact.EditingContactInformationPage;

public class EditContactTest extends BaseClass {

	@Test(groups = "regressionTest")
	public void editContactsTest() throws Throwable, IOException, InterruptedException {
		ContactsPage contactPage = new ContactsPage(driver);
		EditingContactInformationPage editPage = new EditingContactInformationPage(driver);
		for (int i = 1; i < eu.getRowCount("Sheet5"); i++) {

			String lastName = eu.getDataFromExcel("Sheet5", i, 0);
			String emailId = eu.getDataFromExcel("Sheet5", i, 1);
			wu.customWait();
			
			if(emailId.isEmpty());
			
				contactPage.editContactByLastName(lastName);
				String emailTF = editPage.getEmailIdTextField().getText();
				
				if(emailTF.isEmpty()) {
					editPage.getEmailIdTextField().clear();
				}
				editPage.updateEmail(emailId);
				wu.customWait();
				Assert.assertTrue(contactPage.verifyEmail(lastName, emailId));
					System.out.println(lastName + " Email id updated to " + emailId);
				
			}
		}
	}
	

