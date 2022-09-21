package org.flipkart.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This class contains reusable methods for file and property file
 * @author user
 *
 */
public final class FileUtility
{ 
	private Properties properties;
	/**
	 * This method is used for intiallize the excel file
	 * @param filePath
	 */
	public void intiallizePropertyFile(String propertyFilePath)
	{
		FileInputStream fis = null;
		try 
		{
			fis = new FileInputStream(propertyFilePath);
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		try
		{
			properties = new Properties();
			 properties.load(fis);
		} 
		catch (IOException e) 
		{
				e.printStackTrace();
		}
	}
	/**
	 * This method is used to fetch the data from property file
	 * @param key
	 * @return
	 */
	public String getDataFromProperty(String key)	
	{
		return properties.getProperty(key).trim();
	}
}
