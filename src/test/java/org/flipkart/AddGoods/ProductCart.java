package org.flipkart.AddGoods;

import org.flipkart.genericUtility.BaseClass;
import org.flipkart.genericUtility.IConstants;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class ProductCart extends BaseClass
{
	@Test
	public void addProductCart()
	{		
		String productValue = excelUtility.getDataFromExcel(2, 1,IConstants.productPage);
		productPage.searchIcon();
		productPage.searchTextField(productValue);
		String actualProduct=productPage.WinterheaterProduct();
		productPage.clickOnProduct();
		javaUtility.printStatement(actualProduct);
		addProductPage.switchWindow(webdriverUtility);
		String expectedProduct = addProductPage.textProduct();
		javaUtility.printStatement(expectedProduct);
		Assert.assertEquals(actualProduct, expectedProduct); 
	    Reporter.log("Validation Successfull", true);
	}
}