package com.vtiger.comcast.utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Random;

/**
 * This class contains java specific generic libraries
 * @author HP
 *
 */

public class JavaUtilities {
	/**
	 * This method is used to return random int type numbers
	 * @return
	 */

	public int getRandomNumber() {
		Random random= new Random();
		int randomNumber = random.nextInt();
		return randomNumber;
	}
	
	/**
	 * This method is used to get current System date and time
	 * @return
	 */
	public String getCurrentSystemDate() {
		Date date=new Date();
		String systemDateAndTime = date.toString();
		return systemDateAndTime;
	}
	/**
	 * this method is used to get system date in yyyy-mm-dd format
	 * @return
	 */
	public String getSystemDate_yyyy_mm_dd() {
		Date date = new Date();
		String systemDateAndTime = date.toString();
		String[] arr=systemDateAndTime.split(" ");
		String dd=arr[2];
		String yyyy=arr[5];
		int mm = date.getMonth()+1;
		String finalDateFormat=yyyy+"-"+mm+"-"+dd;
		return finalDateFormat;
		
	}
	/**
	 * This method is used to press enter key virtually
	 * @throws Throwable
	 */
	public void pressVirtualEnterKey() throws Throwable {
		Robot robot= new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}



}
