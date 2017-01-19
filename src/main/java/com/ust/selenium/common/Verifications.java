package com.ust.selenium.common;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


/**
 * EA AUTOMATION
 * 
 * @author :u42946 Date :Jan 27, 2015 11:29:20 PM
 * 
 *         Modified Date: Modified By :
 * 
 */

public class Verifications extends Utilities
{
	protected WebDriverWait wait;
	/**
	 * EA AUTOMATION
	 * 
	 * @author :u37380 Date :Jan 27, 2015 11:29:20 PM
	 * 
	 *         Description :Accepts WebElement instance, verify value and
	 *         check if the element text is same as verify value Modified Date:
	 *         Modified By :
	 * 
	 */
	public void verifyTextPresent(WebElement elm, String sExpected)
	{
		waitForVisible(elm);
		sExpected = sExpected.trim().toUpperCase();
		String sActual = getText(elm);
		
		Assert.assertTrue(sActual.toUpperCase().contains(sExpected.trim()), "Expected: " + sExpected + " Actual: " + sActual);
	}

	public void verifyTextBoxAsBlank(WebElement elm)
	{
		
		String sActual = elm.getText();
		
		Assert.assertTrue(sActual.toUpperCase().contentEquals(""), "Expected: is NULL and  Actual: " + sActual);
	}
	
	public void verifyObjectNotBlank(WebElement elm)
	{
		String sActual = elm.getText();	
		Assert.assertTrue(!(sActual.toUpperCase().contentEquals("")));
	}
	
	/**
	 * EA AUTOMATION
	 * 
	 * @author :u37380 Date :Mar 19, 2016 
	 * 
	 *         Description :Gets the text for the element
	 *         Modified By :
	 * 
	 */
	protected String getText(WebElement elm)
	{
		String sActual = elm.getText().toUpperCase();
		if (sActual.equalsIgnoreCase(""))
		{
			try
			{
				sActual = elm.getAttribute("value");
			}
			catch (Exception e)
			{}
			try
			{
				if (sActual.equalsIgnoreCase(""))
				{
					sActual = elm.getAttribute("innerHTML");
				}
			}
			catch (Exception e)
			{}
			try
			{
				if (sActual.equalsIgnoreCase(""))
				{
					sActual = elm.getAttribute("text");
				}
			}
			catch (Exception e)
			{}
		}
		return sActual.toUpperCase();
	}

	/**
	 * EA AUTOMATION
	 * 
	 * @author :u37380 Date :Mar 19, 2016
	 * 
	 *         Description :Verify text is not present
	 *         Date: Modified By :
	 * 
	 */
	public void verifyTextNotPresent(WebElement object, String sExpected)
	{
		waitForVisible(object);
		sExpected = sExpected.trim().toUpperCase();
		String sActual = getText(object);

		Assert.assertFalse(sActual.contains(sExpected.trim()), "Expected: " + sExpected + " Actual: " + sActual);

	}

	/**
	 * EA AUTOMATION
	 * 
	 * @author :u37380 Date :Mar 19, 2016
	 * 
	 *         Description :Verifies the title
	 *         Date: Modified By :
	 * 
	 */
	public void verifyTitle(WebDriver driver, String sExpected)
	{
		String sActual = driver.getTitle().toUpperCase();
		sExpected = sExpected.trim().toUpperCase();
		Assert.assertTrue(sActual.contains(sExpected), "Expected: " + sExpected + " Actual: " + sActual);

	}

	/**
	 * EA AUTOMATION
	 * 
	 * @author :u37380 Date :Mar 19, 2016
	 * 
	 *         Description :Verifies the title is not present
	 *         Date: Modified By :
	 * 
	 */
	public void verifyTitleNotPresent(WebDriver driver, String sExpected)
	{
		String sActual = driver.getTitle().toUpperCase();
		sExpected = sExpected.trim().toUpperCase();
		Assert.assertFalse(sActual.contains(sExpected), "Expected: " + sExpected + " Actual: " + sActual);
	}

	/**
	 * EA AUTOMATION
	 * 
	 * @author :u37380 Date :Mar 19, 2016
	 * 
	 *         Description :Verifies the text in the alert window
	 *         Date: Modified By :
	 * 
	 */
	public void verifyAlertText(WebDriver driver, String sExpected)
	{
		Alert alert = driver.switchTo().alert();
		String sActual = alert.getText().toUpperCase();
		sExpected = sExpected.trim().toUpperCase();
		Assert.assertTrue(sActual.contains(sExpected), "Expected: " + sExpected + " Actual: " + sActual);
	}
	
	
	/**
	 * EA AUTOMATION
	 * 
	 * @author :u37380 Date :Mar 19, 2016
	 * 
	 *         Description :Verifies the text in the alert window
	 *         Date: Modified By :
	 * 
	 */
	public void verifyObjectNotNull(WebElement elm )
	{
		Assert.assertNotNull(elm);
	}
	

	/**
	 * EA AUTOMATION
	 * 
	 * @author :u37380 Date :Mar 19, 2016
	 * 
	 *         Description :Verifies the text in table
	 *         Date: Modified By :
	 * 
	 */
	public void verifyTableText(WebElement object, String sText)
	{
		waitForVisible(object);
		String sElementText = null;
		sText = sText.trim().toUpperCase();
		boolean bFound = false;
		List<WebElement> child = findChildren(object);
		if (child != null)
		{
			for (WebElement webElement : child)
			{
				sElementText = webElement.getText().trim().toUpperCase();
				if (sElementText.contains(sText.trim()))
				{
					bFound = true;
					break;
				}
			}
		}
		Assert.assertTrue(bFound, "Expected text: " + sText + " Table contents: " + object.getText());
	}

	/**
	 * EA AUTOMATION
	 * 
	 * @author :u37380 Date :Mar 19, 2016
	 * 
	 *         Description :Gets the list of children 
	 *         Date: Modified By :
	 * 
	 */
	public List<WebElement> findChildren(WebElement object)
	{
		waitForVisible(object);
		List<WebElement> children = object.findElements(By.cssSelector("*"));

		return children;
	}

	/**
	 * EA AUTOMATION
	 * 
	 * @author :u37380 Date :Mar 19, 2016
	 * 
	 *         Description :Verifies that radio button is selected
	 *         Date: Modified By :
	 * 
	 */
	public void verifyRadioSelected(WebElement object)
	{
		waitForVisible(object);
		Assert.assertTrue(object.isSelected());
	}

	/**
	 * EA AUTOMATION
	 * 
	 * @author :u37380 Date :Mar 19, 2016
	 * 
	 *         Description :Verifies the radio button is not selected
	 *         Date: Modified By :
	 * 
	 */

	public void verifyRadioNotSelected(WebElement object)
	{
		waitForVisible(object);
		Assert.assertFalse(object.isSelected());
	}

	/**
	 * EA AUTOMATION
	 * 
	 * @author :u37380 Date :Mar 19, 2016
	 * 
	 *         Description :Verifies the table row count
	 *         Date: Modified By :
	 * 
	 */
	public void verifyTableRowCount(WebElement object, int RowCount)
	{
		waitForVisible(object);
		List<WebElement> sRow = object.findElements(By.xpath(".//tr"));
		Assert.assertEquals(sRow.size() - 1, RowCount, "Actual: " + (sRow.size() - 1) + " Expected: " + RowCount);
	}

	/**
	 * EA AUTOMATION
	 * 
	 * @author :u37380 Date :Mar 19, 2016
	 * 
	 *         Description :Verifies the value
	 *         Date: Modified By :
	 * 
	 */
	public void verifyValue(WebElement elm, String sExpected)
	{
		waitForVisible(elm);
		sExpected = sExpected.trim().toUpperCase();
		String sActual = elm.getAttribute("value");
		sActual = sActual.toUpperCase();
		Assert.assertTrue(sActual.contains(sExpected.trim()), "Expected: " + sExpected + " Actual: " + sActual);
	}
	/**
	 * EA AUTOMATION
	 * 
	 * @author :u37380 Date :Mar 19, 2016
	 * 
	 *         Description :Verifies the element existence
	 *         Date: Modified By :
	 * 
	 */
	public void verifyElementExistence (WebElement element)
	{
		waitForVisible(element);
		Assert.assertEquals(element.isDisplayed(), true);		
	}
	
	public void verifyIsEnabled (WebElement element)
	{
		waitForVisible(element);
		Assert.assertEquals(element.isEnabled(), true);
	}

	public void verifyIsDisabled(WebElement element)
	{
		waitForVisible(element);
		Assert.assertEquals(element.isEnabled(), false);
	}
	
	public void verifyTableHeaders(WebElement element, String sText)
	{
		waitForVisible(element);
		boolean bfound = false;
		ArrayList<String> headers = new ArrayList<String>();
		for (String s1 : sText.split(";"))
		{
			headers.add(s1);
		}
		String ss = "";
		List<WebElement> colHeaders = element.findElements(By.tagName("th"));
		System.out.println(colHeaders.size());
		for (WebElement col : colHeaders)
		{
			System.out.println(col.getText());
			ss = col.getText();
			if (ss.length() > 1)
			{
				if (headers.contains(ss))
					bfound = true;				
				else
				{
					bfound = false;
					break;
				}
			}
		}
		Assert.assertEquals(bfound,true);
	}
	
	public void waitForVisible(WebElement element)
	{
//		wait.until(ExpectedConditions.visibilityOf(element));
	}

}