package org.flipkart.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage
{
	public ProductPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	@FindBy (xpath ="//button[text()='✕']")
	private WebElement cancelButton;
	
	@FindBy(xpath = "//input[@name='q']")
	private WebElement searchTextField;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement searchIcon;
	
	@FindBy(xpath = "//div[@class='_13oc-S']/child::div[@data-id='ROHG9R5RWDHK9V2T']/child::div[@class='_4ddWXP']/child::a[@class='s1Q9rs']")
	private WebElement firstTextWinterHeater;
	
	@FindBy(xpath = "//div[@class='_13oc-S']/child::div[@data-id='ROHG9R5RWDHK9V2T']/child::div[@class='_4ddWXP']/child::a[contains(text(),'DARSHANAM')]")
    private WebElement winterHeaterText;
    //business library
	/**
	 * This method is used to click on the search the Product Item
	 */
	public void clickCancelButton()
	{
		cancelButton.click();
	}
	public void searchTextField(String value)
	{
		searchTextField.sendKeys(value);
		searchIcon.click();
	}
	public void searchIcon()
	{
		searchIcon.click();
	}
	public String getWinterheaterText()
	{
		return firstTextWinterHeater.getText().trim();
	}
	public String WinterheaterProduct()
	{
		return winterHeaterText.getAttribute("title").trim();
	}
	public void clickOnProduct()
	{
		winterHeaterText.click();
	}
}