package com.vtiger.comcast.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class LisImplementClass implements ITestListener {

	public void onTestFailure(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		EventFiringWebDriver eDriver= new EventFiringWebDriver(BaseClass.sDriver);
		File srcFile=eDriver.getScreenshotAs(OutputType.FILE);
		File destFile= new File("./screenshots/"+methodName+".png");
		try {
			FileUtils.copyFile(srcFile,destFile );
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}
}
