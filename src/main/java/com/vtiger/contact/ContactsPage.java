package com.vtiger.contact;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {

	public WebDriver driver;

	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//span[@class=\"genHeaderSmall\"]")
	private WebElement allRecordsDeleteMessage;

	@FindBy(xpath = "//img[@title=\"Create Contact...\"]")
	private WebElement createContactImg;

	@FindBy(xpath = "//input[@id=\"selectCurrentPageRec\"]")
	private WebElement massDeleteCheckbox;

	@FindBy(xpath = "//input[@value=\"Delete\"]")
	private WebElement deleteButton;

	@FindBy(xpath = "//a[@class=\"hdrLink\"]")
	private WebElement contactPageOpenConfirmation;

	@FindBy(linkText = "del")
	private WebElement deleteParticularContactLink;
	
	@FindBy(xpath="//span[@vtfieldname='lastname']/preceding-sibling::a[@title='Contacts']")
	private WebElement contactLastName;
	
	@FindBy(xpath="//img[@title='Last Viewed']")
	private WebElement lastViewedContactsImg;
	

	public WebElement getLastViewedContactsImg() {
		return lastViewedContactsImg;
	}

	public WebElement getContactLastName() {
		return contactLastName;
	}

	public WebElement getDeleteParticularContactLink() {
		return deleteParticularContactLink;
	}

	public WebElement getContactPageOpenConfirmation() {
		return contactPageOpenConfirmation;
	}

	public WebElement getCreateContactImg() {
		return createContactImg;
	}

	public WebElement getMassDeleteCheckbox() {
		return massDeleteCheckbox;
	}

	public WebElement getDeleteButton() {
		return deleteButton;
	}

	public String getAllRecordsDeleteMessage() {
		return allRecordsDeleteMessage.getText();
	}

	public String contactsPageOpenConfirmationText() {
		return getContactPageOpenConfirmation().getText();
	}

	@FindBy(xpath = "//td")
	private WebElement deleteBoxParent;

	public void deleteByLastName(String lastName) {

		deleteBoxParent
				.findElement(
						By.xpath("//a[text()='" + lastName + "']/parent::td/following-sibling::td//a[text()='del']"))
				.click();
		driver.switchTo().alert().accept();

	}

	public void deleteAllRecords() {
		getMassDeleteCheckbox().click();
		getDeleteButton().click();
		driver.switchTo().alert().accept();
	}

	@FindBy(xpath="//td")
	private WebElement editBoxParent;
	public EditingContactInformationPage editContactByLastName(String lastName) {

		editBoxParent
				.findElement(
						By.xpath("//a[text()='" + lastName + "']/parent::td/following-sibling::td//a[text()='edit']"))
				.click();
		return new EditingContactInformationPage(driver);
	}

	@FindBy(xpath = "//td")
	private WebElement selectBoxParent;

	public void selectContactByLastName(String lastName) {
		selectBoxParent.findElement(By.xpath("//a[text()='" + lastName + "']/parent::td/preceding-sibling::td//input[@name='selected_id']")).click();
	
	}
	
	public boolean isContactExist(String lastName) {
		List<WebElement> ls=driver.findElements(By.xpath("//span[@vtfieldname='lastname']/preceding-sibling::a[@title='Contacts']"));
		for(WebElement ele: ls) {
			if(ele.getText().equals(lastName)) {
				return true;
			}
				
			}
		return false;
			
		}
	
	@FindBy(xpath="//td")
	private WebElement parntEmailText;
	
	public boolean verifyEmail(String lastName, String email) {
		List<WebElement> ls=driver.findElements(By.xpath("//a[text()='"+lastName+"']/parent::td/following-sibling::td//span[@vtfieldname='email']/preceding-sibling::a[contains(@href, 'javascript')]"));
		
		for(WebElement ele:ls) {
			if(ele.getText().equals(email)) {
				return true;
			}
		}
		
		return false;
	}
	
	public List<WebElement> getLastViewedContactsList(){
		getLastViewedContactsImg().click();
		List<WebElement> ls = driver.findElements(By.xpath("//td[@class='trackerList small']/a"));
		return ls;
		
	}
	
	@FindBy(xpath="//td")
	private WebElement parentLastName;
	
	public void selectOnLastName(String lastName) {
		WebElement lastNameLink = parentLastName.findElement(By.xpath("//a[text()='"+lastName+"']"));
		lastNameLink.click();
	}
		
	
		
	}


