package com.vtiger.contact;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import com.vtiger.comcast.utilities.BaseClass;

public class LoginPage {
	
	WebDriver driver;

	@FindBy(name = "user_name")
	private WebElement userNameTextField;

	@FindBy(name = "user_password")
	private WebElement passwordTextField;

	@FindBy(id = "submitButton")
	private WebElement loginButton;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getUserNameTextField() {
		return userNameTextField;
	}

	public WebElement getPasswordTextField() {
		return passwordTextField;
	}

	public WebElement getSubmitButton() {
		return loginButton;
	}
	
	//login Action method
	public HomePage loginAction(String userName, String password) {
		userNameTextField.sendKeys(userName);
		passwordTextField.sendKeys(password);
		loginButton.click();
		return new HomePage(driver);
	}
	@AfterMethod(groups = {"ST", "regressionTest"})
	public void configAM() throws Throwable {
	
		HomePage hp=new HomePage(driver);
		hp.logout();
	}

	@AfterClass(groups = {"ST", "regressionTest"})
	public void configAC() {
		driver.quit();
	}
}
