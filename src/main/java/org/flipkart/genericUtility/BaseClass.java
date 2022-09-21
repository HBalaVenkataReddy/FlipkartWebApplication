package org.flipkart.genericUtility;

import org.flipkart.pom.AddProductPage;
import org.flipkart.pom.ProductPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass extends InstanceClass
{
	@BeforeSuite
	public void suiteSetup()
	{
		System.out.println("open database");
	}
	@BeforeClass
	public void classSetup()
	{
		fileUtility = new FileUtility();
		javaUtility=new JavaUtility();
		excelUtility=new ExcelUtility();
		webdriverUtility = new WebDriverUtility();
		//intiallize data from property file
		fileUtility.intiallizePropertyFile(IConstants.FLIPKARTPROPERTYFILEPATH);
		//get the control for particular sheet in excel
		excelUtility.intiallizeExcelFile(IConstants.FLIPKARTEXCELFILEPATH);

		browser = fileUtility.getDataFromProperty("browser").trim();
		url = fileUtility.getDataFromProperty("url").trim();
		String timeout = fileUtility.getDataFromProperty("timeout").trim();
		//convert string to long
		longTimeout = javaUtility.convertStringToLong(timeout);
		//launching the browser in run time based on browser key
		driver = webdriverUtility.setupDriver(browser);
		//call the setter method driver from driver class in order to set driver instance
		ThreadSafeClass.setDriver(driver);
		//pre-setting for the browser
		webdriverUtility.maximizeBrowser();
		//navigating the application
		webdriverUtility.openApplication(url);
		webdriverUtility.implicitWait(longTimeout);
		//Initialize the Explicit wait,Actions
		webdriverUtility.intiallizeActions();
		//create object for Common POM repo classes
		productPage=new ProductPage(driver);
		addProductPage=new AddProductPage(driver);
	}
	@BeforeMethod
	public void methodSetup()
	{
		//Generate the random number
		randomNumber = javaUtility.getRandomNumber();
		//login to the application
		productPage.clickCancelButton();
	}
	@AfterMethod
	public void methodTearDown()
	{
		//Signout
	}
	@AfterClass
	public void classTearDown()
	{
		//close the browser
		webdriverUtility.closeBrowser();
	}
}