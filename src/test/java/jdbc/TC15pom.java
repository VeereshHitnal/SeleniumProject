package jdbc;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.vtiger.contact.ContactInformationPage;
import com.vtiger.contact.ContactsPage;
import com.vtiger.contact.CreatingNewContactPage;
import com.vtiger.contact.EditingContactInformationPage;
import com.vtiger.contact.HomePage;
import com.vtiger.contact.LoginPage;

public class TC15pom {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.get("http://localhost:8888/");
		
		Thread.sleep(1000);
		
		LoginPage lp= new LoginPage(driver);
		lp.loginAction("admin", "admin").getContactsLink().click();
		
		ContactsPage contactPage=new ContactsPage(driver);
		contactPage.getCreateContactImg().click();
		if(contactPage.contactsPageOpenConfirmationText().equals("Contacts")) {
			System.out.println("Contacts List Page Displayed");
		}else {
			System.out.println("Contacts List Page Not Displayed");
		}
		
		CreatingNewContactPage creatingNewContact= new CreatingNewContactPage(driver);
		creatingNewContact.createNewContact("Mr.", "Veeresh", "Hitnal");
		ContactInformationPage ci= new ContactInformationPage(driver);
		if(ci.contactInformationCreationText().contains("Hitnal Veeresh")) {
			System.out.println("Contact created successfully");
		}else {
			System.out.println("Contact not created successfully");
		}
		
		
		HomePage hp=new HomePage(driver);
		contactPage.getCreateContactImg().click();
		
		creatingNewContact.createNewContact("Mr.", "Moto", "Fusion");

		if(ci.contactInformationCreationText().contains("Fusion Moto")) {
			System.out.println("Contact created successfully");
		}else {
			System.out.println("Contact not created successfully");
		}
		hp.getContactsLink().click();
		contactPage.editContactByLastName("Maarley");
		
		EditingContactInformationPage editPage=new EditingContactInformationPage(driver);
		editPage.updateEmail("maarley@gmail.com");
					
		hp.logout();

	}

}
