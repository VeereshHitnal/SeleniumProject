package jdbc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.vtiger.comcast.utilities.BaseClass;
import com.vtiger.contact.ContactInformationPage;
import com.vtiger.contact.ContactsPage;

public class LastViewedContactstest extends BaseClass{
	
	@Test
	public void lastViewedContactsListTest() {
		ContactsPage cp=new ContactsPage(driver);
		System.out.println("Last viewed contacts are");
		List<WebElement> ls = cp.getLastViewedContactsList();
		for(WebElement ele:ls) {
			if(ele.getText().isEmpty()) {
				System.out.println("No contacts viewed ");
			}else {
				System.out.println(ele.getText());
				
			}
			
		}
	}
	
	@Test
	public void duplicateContactTest() throws Throwable, IOException {
		ContactsPage cp=new ContactsPage(driver);
		String lastName = eu.getDataFromExcel("Sheet4", 1, 0);
		cp.selectOnLastName(lastName);
		ContactInformationPage cip=new ContactInformationPage(driver);
		cip.getDuplicateButton().click();
	}
	

}
