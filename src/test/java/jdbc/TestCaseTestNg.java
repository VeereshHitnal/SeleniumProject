package jdbc;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.UnhandledAlertException;
import org.testng.annotations.Test;

import com.vtiger.comcast.utilities.BaseClass;
import com.vtiger.contact.ContactsPage;
import com.vtiger.contact.EditingContactInformationPage;

public class TestCaseTestNg extends BaseClass {
	@Test
	public void ontactsSelectAndDelete() throws Throwable {

		ContactsPage contactPage = new ContactsPage(driver);
		for (int i = 1; i < eu.getRowCount("Sheet2"); i++) {

			String ExcelLastName = eu.getDataFromExcel("Sheet2", i, 0);
			if (contactPage.isContactExist(ExcelLastName) == true) {
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

	}

	@Test
	public void electAndDeleteTest() throws Throwable, IOException {
		for (int i = 1; i < eu.getRowCount("Sheet2"); i++) {
			ContactsPage contactPage = new ContactsPage(driver);
			String LastName = eu.getDataFromExcel("Sheet2", i, 0);
			contactPage.deleteByLastName(LastName);
			System.out.println(LastName + " Contact Deleted Successfully");
		}
	}

	@Test
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
