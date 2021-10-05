package com.vtiger.contact;

import org.apache.commons.compress.archivers.sevenz.CLI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewContactPage {

	WebDriver driver;

	@FindBy(name = "salutationtype")
	private WebElement salutationName;

	@FindBy(name = "firstname")
	private WebElement firstNameTextBox;

	@FindBy(name = "lastname")
	private WebElement lastNameTextBox;

	@FindBy(xpath = "//input[@title=\"Save [Alt+S]\"]")
	private WebElement saveButton;

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getSalutationName() {
		return salutationName;
	}

	public WebElement getFirstNameTextBox() {
		return firstNameTextBox;
	}

	public WebElement getLastNameTextBox() {
		return lastNameTextBox;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public CreatingNewContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Creating New Contact method
	public ContactInformationPage createNewContact(String salutationName, String firstName, String lastName) throws InterruptedException {
		Thread.sleep(2000);
		getSalutationName().click();
		Select select = new Select(getSalutationName());
		select.selectByVisibleText(salutationName);
		getFirstNameTextBox().sendKeys(firstName);
		getLastNameTextBox().sendKeys(lastName);
		getSaveButton().click();
		return new ContactInformationPage(driver);
	}

}
