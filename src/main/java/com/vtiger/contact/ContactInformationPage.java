package com.vtiger.contact;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationPage {

	WebDriver driver;

	public ContactInformationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(xpath="//span[@class=\"dvHeaderText\"]")
	private WebElement contactInformationCreationText;

	public WebElement getContactInformationCreationText() {
		return contactInformationCreationText;
	}
	
	public String contactInformationCreationText() {
		return	getContactInformationCreationText().getText();
		}
	@FindBy(name="Duplicate")
	private WebElement duplicateButton;

	public WebElement getDuplicateButton() {
		return duplicateButton;
	}
	
	
}
