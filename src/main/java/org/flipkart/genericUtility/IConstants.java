package org.flipkart.genericUtility;

/**
 * This interface contains all the constant variables for the application
 * @author user
 *
 */
public interface IConstants
{
	String ABSOLUTEPATHTOPROJECT=System.getProperty("user.dir");
	/**
	 * This static final variable gives the path of property file
	 */
	public String FLIPKARTPROPERTYFILEPATH =ABSOLUTEPATHTOPROJECT+"./src/test/resources/commonData.Properties";
	/**
	 * This static final variable gives the path of Excel file
	 */
	public String FLIPKARTEXCELFILEPATH =ABSOLUTEPATHTOPROJECT+"./src/test/resources/testData.xlsx";
    
	public String productPage="AddProduct";
}
