package org.flipkart.genericUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class contains selenium reusable methods
 * @author user
 *
 */
public final class WebDriverUtility
{
	private WebDriver driver;
	private Actions act;
	private WebDriverWait wait;
	/**
	 * This method is used to setup the driver instance
	 * @param browser
	 * @return
	 */
	public WebDriver setupDriver(String browser)
	{
		switch (browser) 
		{
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.chromedriver().setup();
			driver = new FirefoxDriver();
			break;
		case "ie":
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			break;
		default:
			System.out.println("You entered wrong Browser key in the Property file");
			break;
		}
		return driver;
	}
	/**
	 * This method is used to maximize the browser
	 */
	public void maximizeBrowser()
	{
		driver.manage().window().maximize();
	}
	/**
	 * This method is used to wait the page by using implicit wait
	 * @param Timeout
	 */
	public void implicitWait(long longTimeout)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(longTimeout));
	}
	/**
	 * This method is used to navigate the application
	 * @param url
	 */
	public void openApplication(String url)
	{
		driver.get(url);
	}
	/**
	 * This method is used to intiallize the Actions class
	 */
	public void intiallizeActions()
	{
		act = new Actions(driver);
	}
	/**
	 * This method is used to Mouser hover on Element
	 * @param element
	 */
	public void mouseHoverOnElement(WebElement element)
	{
		act.moveToElement(element).perform();
	}
	/**
	 * This method is used to perform right click action on particular webelement
	 * @param element
	 */
	public void rightClickElement(WebElement element)
	{
		act.contextClick(element).perform();
	}
	/**
	 * This method is used to perform double click action on particular webelement
	 * @param element
	 */
	public void doubleClickElement(WebElement element)
	{
		act.doubleClick(element).perform();
	}
	/**
	 * This method is used to open New Tab or Window
	 * @param nameOrID
	 */
	public void switchFrame(String nameOrID)
	{
		driver.switchTo().frame(nameOrID);
	}
	/**
	 * This method is used to switch frame based on index
	 * @param index
	 */
	public void switchFrame(int index)
	{
		driver.switchTo().frame(index);
	}
	/**
	 * This method is used to switch back from frame to parent web page
	 * @param strategy
	 */
	public void switchBackFromFrame(String strategy)
	{
		switch (strategy.toLowerCase().trim())
		{
		case "default":
			driver.switchTo().defaultContent();
			break;
		case "parent":
			driver.switchTo().parentFrame();
		default:
			System.out.println("please enter valid strategy either <default or parent>");
			break;
		}
	}
	/**
	 * This method is used to handle <select> tag Dropdown by using visible text
	 * @param dropDownElement
	 * @param visibleText
	 */
	public void handleSelectDropdown(WebElement dropDownElement, String visibleText)
	{
		Select s=new Select(dropDownElement);
		s.selectByVisibleText(visibleText);;
	}
	/**
	 * This method is used to handle <select> tag Dropdown by using value attribute of the <option> tag
	 * @param value
	 * @param dropDownElement
	 */
	public void handleSelectDropdown(String value, WebElement dropDownElement)
	{
		Select s=new Select(dropDownElement);
		s.selectByValue(value);
	}
	/**
	 * This method is used to handle <select> tag Dropdown by using index
	 * @param dropDownElement
	 * @param indexNumber
	 */
	public void handleSelectDropdown(WebElement dropDownElement, int indexNumber)
	{
		Select s=new Select(dropDownElement);
		s.selectByIndex(indexNumber);
	}
	/**
	 * This method is used to take the current page Screenshot
	 * @param currentClass
	 * @param javaUtility
	 */
	public void takeScreenShotPage(Object currentClass, JavaUtility javaUtility)
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst = new File("./errorshots"+currentClass.getClass().getSimpleName()+javaUtility.getCurrentDate("dd_MM_yyyy_HH_mm_sss")+".png");
		try
		{
			FileUtils.copyFile(src, dst);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	/**
	 * This method is used to take the Screenshot from driver 
	 * @param driver
	 * @return
	 */
	public String takeScreenShotPage(WebDriver driver)
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		String path = ts.getScreenshotAs(OutputType.BASE64);
		return path;
	}	
	/**
	 * This method is used to take the particular element screen shot
	 * @param element
	 * @param currentClass
	 * @param javaUtility
	 */
	public void takeScreenShotElement(WebElement element, Object currentClass, JavaUtility javaUtility)
	{
		File src = element.getScreenshotAs(OutputType.FILE);
		File dst = new File("./elementScreenshots"+currentClass.getClass().getSimpleName()+javaUtility.getCurrentDate("dd_MM_yyyy_HH_mm_sss"));
		try
		{
			FileUtils.copyFile(src, dst);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	/**
	 * This method will wait till the element is clickable(custom wait)
	 * @param totalDuration
	 * @param pollingTime
	 * @param element
	 */
	public void waitTillElementClickable(int totalDuration, long pollingTime, WebElement element)
	{
		int currentTime=0;
		while(currentTime<=totalDuration)
		{
			try
			{
				element.click();
				element.isDisplayed();
			}
			catch(Exception e)
			{
			try 
			{
				Thread.sleep(pollingTime);
			} 
			catch (InterruptedException e1)
			{
				e.printStackTrace();
			}
			currentTime++;
			}
		}
	}
	/**
	 * This method is used to initialize the Explicit wait or Webdriverwait
	 * @param timeOuts
	 * @param pollingTime
	 */
	public void intiallizeExplicitWait(long timeOuts, long pollingTime)
	{
		wait=new WebDriverWait(driver, Duration.ofSeconds(timeOuts));
		wait.pollingEvery(Duration.ofMillis(pollingTime));
		wait.ignoring(Exception.class);
	}
	/**
	 * This method is used to wait until element is visible
	 * @param element
	 */
	public void waitTillElementVisible(WebElement element)
	{
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	/**
	 * This method is used to wait until element is invisible
	 * @param element
	 */
	public void waitTillElementInvisible(WebElement element)
	{
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	/**
	 * This method is used to jsPopupaccept by using accept()
	 */
	public void jsPopupaccept()
	{
		driver.switchTo().alert().accept();
	}
	/**
	 * This method is used to jsPopupdecline by using dismiss()
	 */
	public void jsPopupdecline()
	{
		driver.switchTo().alert().dismiss();
	}
	/**
	 * This method is used to jsPopupSendData by using getText()
	 * @param data
	 */
	public void jsPopupSendData(String data)
	{
		driver.switchTo().alert().getText();
	}
	/**
	 * This method is used to switch the window
	 * @param partialText
	 * @param Strategy
	 */
//	public void switchWindow(String partialText, String strategy)
//	{
//		Set<String> winIds = driver.getWindowHandles();
//		for(String id:winIds)
//		{
//			driver.switchTo().window(id);
//			if(strategy.equalsIgnoreCase("URL"))
//			{
//				if(driver.getCurrentUrl().contains(partialText))
//				{
//					break;
//				}
//			}
//			else if(strategy.equalsIgnoreCase("TITLE"))
//			{
//				if(driver.getTitle().contains(partialText))
//				{
//					break;
//				}
//			}
//		}
//	}
	
	public void switchWindow()
	{
		Set<String> winIds = driver.getWindowHandles();
		for(String id:winIds)
		{
			driver.switchTo().window(id);
		}
	}
	/**
	 * This method is used to close particular Browser
	 */
	public void closeBrowser()
	{
		driver.quit();
	}
	/**
	 *  This method is used to close particular Tab
	 */
	public void closeTab()
	{
		driver.close();
	}
}
