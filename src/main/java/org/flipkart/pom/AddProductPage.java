package org.flipkart.pom;

import org.flipkart.genericUtility.WebDriverUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddProductPage
{
	public AddProductPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath ="//button[@class='_2KpZ6l _2U9uOA _3v1-ww']")
	private WebElement clickOnAddProductCart;
	
	@FindBy(xpath = "//div[@class='_2-uG6-']/child::a[contains(text(),'DARSHANAM')]")
	private WebElement clickOnProductName;

	//business library
	/**
	 * This method is used to click on the Select the Add Product Item to cart
	 */
	public void addProductCart()
	{
		clickOnAddProductCart.click();
	}

	public void switchWindow(WebDriverUtility webDriverUtility)
	{
		webDriverUtility.switchWindow();
		clickOnAddProductCart.click();
	}
	public String textProduct()
	{
		return clickOnProductName.getText().trim();
	}
}