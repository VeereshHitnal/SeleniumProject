package com.vtiger.comcast.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * This class is used to read common data from the property file
 * @author HP
 *
 */
public class FileUtility {
	
	/**
	 * This method is used to get the value corresponding to key from commonData property file
	 * @param keyValue
	 * @return
	 * @throws Throwable
	 */
	public String getPropertyKeyValue(String keyValue) throws Throwable {
		FileInputStream fis= new FileInputStream("./data/commonData.properties.txt");
		Properties pobj= new Properties();
		pobj.load(fis);
		String value = pobj.getProperty(keyValue);
		return value;
	}
	

}
