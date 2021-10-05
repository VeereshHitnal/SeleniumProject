package com.vtiger.contact;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditingContactInformationPage {
	
	WebDriver driver;

	public EditingContactInformationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="email")
	private WebElement emailIdTextField;
	
	@FindBy(xpath="//input[@title=\"Save [Alt+S]\"]")
	private WebElement saveButton;

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getEmailIdTextField() {
		return emailIdTextField;
	}
	
	public ContactsPage updateEmail(String emailId) {
		getEmailIdTextField().sendKeys(emailId);
		getSaveButton().click();
		return new ContactsPage(driver);
	}
	
	
}
