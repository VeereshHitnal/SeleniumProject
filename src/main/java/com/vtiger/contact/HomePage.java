package com.vtiger.contact;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@class='hdrLink']")
	private WebElement homePageText;
	
	public WebElement getHomePageText() {
		return homePageText;
	}

	@FindBy(linkText = "Contacts")
	private WebElement contactsLink;
	
	@FindBy(xpath="//img[@src=\"themes/softed/images/user.PNG\"]")
	private WebElement administratorImgDropDown;
	
	@FindBy(linkText = "Sign Out")
	private WebElement logOutLink;

	public WebElement getLogOutLink() {
		return logOutLink;
	}

	public WebElement getAdministratorImgDropDown() {
		return administratorImgDropDown;
	}

	public WebElement getContactsLink() {
		return contactsLink;
	}
	
	public void logout() {
		Actions actions=new Actions(driver);
		actions.moveToElement(administratorImgDropDown).perform();
		logOutLink.click();
			
	}
	
	public String homePageText() {
		return getHomePageText().getText();
	}
	
	}


