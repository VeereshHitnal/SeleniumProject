package com.vtiger.comcast.utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class contains webdriver specific generic methods
 * @author HP
 *
 */
public class WebDriverUtilities {
	
	
	/**
	 * This method is used to wait until the page loads
	 * @param driver
	 */
	public void waitUntilPageLoad(WebDriver driver) {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
	}
	
	/**
	 * This method is used to wait until the element is visible
	 * @param driver
	 * @param ele
	 */
	public void waitForElementVisibility(WebDriver driver, WebElement ele) {
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	/**
	 * This is a custom wait, waits until the element appears and clicks on it
	 * @param element
	 * @throws InterruptedException
	 */
	public void waitAndClickElement(WebElement element) throws InterruptedException {
		int count=0;
		while(count<10) {
			try {
				element.click();
				break;
			} catch(Throwable e) {
				Thread.sleep(1000);
				count++;
			}
		}
	}
	
	public void customWait() throws InterruptedException {
		Thread.sleep(2000);
	}
	
	/**
	 * this method selects the WebElement from drop down by visible text
	 * @param element
	 * @param option
	 */
	public void select(WebElement element, String option) {
		Select select= new Select(element);
		select.selectByVisibleText(option);
	}
	
	/**
	 * This method selects WebElement from dropdown by index
	 * @param element
	 * @param option
	 */
	public void select(WebElement element, int option) {
		Select select= new Select(element);
		select.selectByIndex(option);
	}
	
	/**
	 * This method perform mouseHover on the WebElement
	 * @param driver
	 * @param element
	 */
	public void mouseHover(WebDriver driver, WebElement element) {
		Actions actions=new Actions(driver);
		actions.moveToElement(element).perform();
	}
	
	/**
	 * This method perform right click or context click on WebElement
	 * @param driver
	 * @param element
	 */
	public void rightClick(WebDriver driver, WebElement element) {
		Actions actions=new Actions(driver);
		actions.contextClick(element).perform();
	}
	
	/**
	 * This method accepts the alert
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
		
	}
	
	/**
	 * This method rejects the alert
	 * @param driver
	 */
	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
		
	}
	
	/**
	 * This method will Scrolls to the webElement
	 * @param driver
	 * @param element
	 */
	public void scrollToWebElement(WebDriver driver, WebElement element) {
		JavascriptExecutor jse=(JavascriptExecutor)driver;
		 int y = element.getLocation().getY();
		 jse.executeScript("window.scrollBy(0,"+y+")", element);
	}
	
	/**
	 * This method switches driver control to frame by Frame index
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}
	
	/**
	 * This method switches driver control to frame by WebElement
	 * @param driver
	 * @param element
	 */
	public void switchToFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}
	
	/**
	 * This method switches driver control to frame by id or name
	 * @param driver
	 * @param idOrName
	 */
	public void switchToFrame(WebDriver driver, String idOrName) {
			driver.switchTo().frame(idOrName);
	}
	
	/**
	 * This method is used to press enter key in browser window
	 * @param driver
	 */
	public void passEnterKey(WebDriver driver) {
		Actions actions= new Actions(driver);
		actions.sendKeys(Keys.ENTER);
		
	}
	
	
}
