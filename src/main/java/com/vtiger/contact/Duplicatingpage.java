package com.vtiger.contact;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Duplicatingpage {
	@FindBy
	private WebElement saveButton;

	public WebElement getSaveButton() {
		return saveButton;
	}
	

}
