package com.ust.selenium.common;

import java.awt.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * EA AUTOMATION
 * @author u42946
 * Date: Jan 28, 2016 11:07:53 AM
 *
 */
public class Utilities
{
	public void OpenLogFile(String spath) throws IOException
	{
		File htmlFile = new File(spath);
		Desktop.getDesktop().browse(htmlFile.toURI());
	}
	@SuppressWarnings("deprecation")
	public void Scheduler(String sTime) throws InterruptedException
	{
		String Str[] = sTime.split(":");
		int Hour = Integer.parseInt(Str[0].trim());
		int Min = Integer.parseInt(Str[1].trim());
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		date = calendar.getTime();
		System.out.println(date);
		Date date2 = new Date(calendar.getTimeInMillis());
		date2.setHours(Hour);
		date2.setMinutes(Min);
		System.out.println(date2);
		while (date2.after(date))
		{
			Robot robot;
			try
			{
				robot = new Robot();
				robot.mouseMove(100, 100);
				Thread.sleep(30);
				robot.mouseMove(200, 100);
				Thread.sleep(30);
				calendar = Calendar.getInstance();
				date = calendar.getTime();
			}
			catch (AWTException e)
			{
				e.printStackTrace();

			}
		}
	}
	public void createUserDir(String sFilename) throws IOException
	{
		File file = new File(sFilename);
		try
		{
			if (!file.exists())
			{
				file.mkdirs();
			}
			else
			{
				System.out.println("Folder already exists");
			}
		}
		catch (Exception e)
		{}
	}
	public void deletetemp(String path)
	{
		try
		{
			Runtime.getRuntime().exec(path);
		}
		catch (Exception delex)
		{}

	}
	public DesiredCapabilities bypassProxy(String Browser)
	{
		DesiredCapabilities capabilities = null;
		Proxy newProxy = new Proxy();
		newProxy.setProxyType(Proxy.ProxyType.DIRECT);
		if (Browser == "IE")
		{
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability("ie.setProxyByServer", true);
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability(CapabilityType.PROXY, newProxy);
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		}
		else if (Browser == "Chrome")
		{
			capabilities = DesiredCapabilities.chrome();
		}
		return capabilities;
	}
	public String FormatString(String sValue)
	{
		if (sValue.contains("\n"))
		{
			sValue = sValue.replace("\n", "").trim();
		}
		sValue.replaceAll(" ", "");
		return sValue;
	}
	public String StringTrimmer(String sText)
	{
		return sText.trim().replaceAll("\n", "").replaceAll(" ", "").replaceAll(":", "").replaceAll("_", "");
	}
	public String GetSystemDateinFormatSpecified(String Format)
	{
		String ActualDate = null;
		try
		{

			Calendar currentCal = Calendar.getInstance();
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			System.out.println("Current date of Calendar : " + dateFormat.format(currentCal.getTime()));
			currentCal.add(Calendar.MONTH, Integer.parseInt(Format.split(":")[0].trim()));
			currentCal.add(Calendar.DATE, Integer.parseInt(Format.split(":")[1].trim()));
			currentCal.add(Calendar.YEAR, Integer.parseInt(Format.split(":")[2].trim()));
			ActualDate = dateFormat.format(currentCal.getTime());
		}
		catch (Exception e)
		{
			System.out.println("Exception in Function GetSystemDateinFormatSpecified");
		}
		return ActualDate;
	}
	public String GenerateRandomAlphaNumericValue(String sChar)
	{
		String ActualValue = null;
		try
		{
			int iNumber = 0;
			iNumber = (int) ((Math.random() * 900000) + 100000);
			String value = String.valueOf(iNumber);
			ActualValue = sChar + value;

		}
		catch (Exception e)
		{
			System.out.println("Exception in Function GenerateRandomAlphaNumericValue");
		}
		return ActualValue;
	}
	/**
	 * EA AUTOMATION
	 * 
	 * @author :u42946 Date :Jan 27, 2015 11:29:20 PM
	 * 
	 *         Description :Accepts Element instance, Row Count and Verfiy if
	 *         the table have the desired Row Count Modified Date: Modified By :
	 * 
	 */


//	public boolean verifyColValueNotNull_1(WebElement element, String ColumnIndex, int nStoponError)
//	{
//		boolean bfound = false;
//		String Value = null;
//		WebElement element = null;
//
//		element = FindElement(element);
//		// WebElement element = driver.findElement(By.id(propvalue));
//		try
//		{
//			List<WebElement> allRows = element.findElements(By.tagName("tr"));
//			System.out.println(allRows.size());
//			for (int row = 2; row <= allRows.size() - 1; row = row + 2)
//			{
//				try
//				{
//					Value = driver.findElement(By.xpath(element.GetSeleniumPropertyValue() + "/tbody/tr[" + row + "]/td[" + ColumnIndex + "]")).getText();
//				}
//				catch (Exception e)
//				{
//					System.out.println(e);
//				}
//				if (Value != null)
//				{
//					bfound = true;
//					System.out.println("Value " + Value + " is verified in row no " + row);
//				}
//				else
//					bfound = false;
//				if (bfound == true)
//				{
//					System.out.println(element.GetelementName() + "element found");
//					if (Constants.bScreenonPass)
//					{
//						TakeScreenShot(element.GetelementName());
//						Constants.oReports.ScreenshotLog(LogStatus.PASS, "Value " + Value + " is present in  " + row, Constants.sScreenshotFilepath + element.GetelementName() + ".jpeg");
//					}
//				}
//				else
//				{
//					TakeScreenShot(element.GetelementName());
//					Constants.oReports.ScreenshotLog(LogStatus.FAIL, "Value is null in" + row + element.GetelementName(), Constants.sScreenshotFilepath + element.GetelementName() + ".jpeg");
//				}
//			}
//		}
//		catch (Exception e)
//		{
//			System.out.println(e);
//		}
//
//		return bfound;
//	}
//
//	/**
//	 * EA AUTOMATION
//	 * 
//	 * @author :u42946 Date :Jan 27, 2015 11:29:20 PM
//	 * 
//	 *         Description :Accepts Element instance, Row Count and Verfiy if
//	 *         the table have the desired Row Count Modified Date: Modified By :
//	 * 

//	public boolean VerifyDateFormat(WebElement element, String ColumnIndex)
////	{
//		String propvalue = element.GetSeleniumPropertyValue();
//		System.out.println(propvalue);
//		// boolean bfound = false;
//		String Value = "test";
//		// int i = 0;
//		WebElement element = null;
//		// List<WebElement> Children = null;
//		element = FindElement(element);
//		// WebElement element = driver.findElement(By.id(propvalue));
//		List<WebElement> allRows = element.findElements(By.tagName("tr"));
//		System.out.println(allRows.size());
//		for (int row = 2; row <= allRows.size() - 1; row = row + 2)
//		{
//			try
//			{
//				Value = driver.findElement(By.xpath("//tbody/tr[" + row + "]/td[" + ColumnIndex + "]")).getText();
//				SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
//				Date inputDate = null;
//				inputDate = sdf.parse(Value);
//				bFound = true;
//			}
//			catch (Exception e)
//			{
//				// Incorrect input date format. Program exits.
//				System.out.println("Incorrect date format: " + Value);
//				System.out.println("Valid Date format:     mm/dd/yyyy");
//				bFound = false;
//
//			}
//			if (bFound == true)
//			{
//				// System.out.println(element.GetelementName() +
//				// "element found");
//				if (Constants.bScreenonPass)
//				{
//					TakeScreenShot(element.GetelementName());
//					Constants.oReports.ScreenshotLog(LogStatus.PASS, "Value " + Value + " format is mm/dd/yyyy in row  " + row, Constants.sScreenshotFilepath + element.GetelementName() + ".jpeg");
//				}
//			}
//			else
//			{
//				TakeScreenShot(element.GetelementName());
//				Constants.oReports.ScreenshotLog(LogStatus.PASS, "Value " + Value + " is not in the specified format " + row, Constants.sScreenshotFilepath + element.GetelementName() + ".jpeg");
//			}
//		}
//
//		return bFound;
//
//	}
//
//	/**
//	 * EA AUTOMATION
//	 * 
//	 * @author :u42946 Date :Jan 27, 2015 11:29:20 PM
//	 * 
//	 *         Description :Accepts Element instance, Row Count and Verfiy if
//	 *         the table have the desired Row Count Modified Date: Modified By :
//	 * 
//	 */
//
//	public boolean VerifyDateAscendingInTable_1(WebElement element, String sText, String ColumnIndex, int nStoponError)
//	{
//		List<WebElement> sRow = null;
//
//		List<String> displayedNames = new ArrayList<String>();
//		List<String> sortedNames = new ArrayList<String>();
//
//		Boolean bFound = false;
//		String Value;
//		try
//		{
//			Constants.oLogin.placeComponents("VerifyDateAscendingInTable");
//			WaitForElementPresent(element);
//			elm = FindElement(element);
//			sRow = elm.findElements(By.xpath(".//tr"));
//			System.out.println(sRow);
//
//			for (int row = 2; row <= sRow.size() - 1; row = row + 2)
//			{
//				Value = driver.findElement(By.xpath(element.GetSeleniumPropertyValue() + "/tbody/tr[" + row + "]/td[" + ColumnIndex + "]")).getText();
//				System.out.println(Value);
//				// Date utilDate = (Date) dateFormat.parse(Value);
//
//				displayedNames.add(Value);
//				sortedNames.add(Value);
//			}
//
//			Collections.sort(sortedNames);
//
//			System.out.println("Actual List " + displayedNames);
//			System.out.println("Sorted List " + sortedNames);
//
//			if (!displayedNames.equals(sortedNames))
//			{
//				System.out.println("Not in Ascending Order...!!!");
//				bFound = false;
//			}
//			else
//			{
//				System.out.println("Values are in Ascending Order.");
//				bFound = true;
//			}
//
//		}
//		catch (Exception e)
//		{
//			TakeScreenShot(sText);
//			Constants.oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Ascending Order in Table Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>" + element.GetelementName() + "</td><td style='border:solid black 1px;'>" + sText + "</td></tr></table>", Constants.sScreenshotFilepath + sText + ".jpeg");
//		}
//		if (bFound)
//		{
//			if (Constants.bScreenonPass)
//			{
//				TakeScreenShot(sText);
//				Constants.oReports.ScreenshotLog(LogStatus.PASS, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Ascending Order in Table Passed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>" + element.GetelementName() + "</td><td style='border:solid black 1px;'>" + sText + "</td></tr></table>", Constants.sScreenshotFilepath + sText + ".jpeg");
//			}
//			else
//				Constants.oReports.Log(LogStatus.PASS, "Verify Ascending Order in Table ", "Verify Ascending Order in Table Text <span class='label success'>Success</span>");
//			return true;
//		}
//		else
//		{
//			TakeScreenShot(sText);
//			System.out.println("Verify Ascending Order in Table failed ");
//			Constants.oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Ascending Order in Table Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>" + element.GetelementName() + "</td><td style='border:solid black 1px;'>" + sText + "</td></tr></table>", Constants.sScreenshotFilepath + sText + ".jpeg");
//			if (nStoponError == 1)
//			{
//				Constants.oReports.EndTest();
//				System.exit(0);
//			}
//			return false;
//		}
//	}
//
//	/**
//	 * EA AUTOMATION
//	 * 
//	 * @author :u42946 Date :Jan 27, 2015 11:29:20 PM
//	 * 
//	 *         Description :Accepts Element instance, Row Count and Verfiy if
//	 *         the table have the desired Row Count Modified Date: Modified By :
//	 * 
//	 */
//	public boolean VerifyTimeFormat(WebElement element, String ColumnIndex)
//	{
//		String propvalue = element.GetSeleniumPropertyValue();
//		System.out.println(propvalue);
//		// boolean bfound = false;
//		String Value = "test";
//		// int i = 0;
//		WebElement element = null;
//		// List<WebElement> Children = null;
//		element = FindElement(element);
//		// WebElement element = driver.findElement(By.id(propvalue));
//		List<WebElement> allRows = element.findElements(By.tagName("tr"));
//		System.out.println(allRows.size());
//		for (int row = 2; row <= allRows.size() - 1; row = row + 2)
//		{
//			try
//			{
//				Value = driver.findElement(By.xpath("//tbody/tr[" + row + "]/td[" + ColumnIndex + "]")).getText();
//				SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a zzz ");
//				Date inputDate = null;
//				inputDate = sdf.parse(Value);
//				bFound = true;
//			}
//			catch (Exception e)
//			{
//				// Incorrect input date format. Program exits.
//				System.out.println("Incorrect date format: " + Value);
//				System.out.println("Valid Date format:    hh:mm:ss a zzz");
//				bFound = false;
//
//			}
//			if (bFound == true)
//			{
//				// System.out.println(element.GetelementName() +
//				// "element found");
//				if (Constants.bScreenonPass)
//				{
//					TakeScreenShot(element.GetelementName());
//					Constants.oReports.ScreenshotLog(LogStatus.PASS, "Value " + Value + " format is valid in row" + row, Constants.sScreenshotFilepath + element.GetelementName() + ".jpeg");
//				}
//			}
//			else
//			{
//				TakeScreenShot(element.GetelementName());
//				Constants.oReports.ScreenshotLog(LogStatus.PASS, "Value " + Value + " format is not valid " + row, Constants.sScreenshotFilepath + element.GetelementName() + ".jpeg");
//			}
//		}
//
//		return bFound;
//
//	}
//
//	/**
//	 * EA AUTOMATION
//	 * 
//	 * @author :u42946 Date :Jan 27, 2015 11:29:20 PM
//	 * 
//	 *         Description :Accepts Element instance, Row Count and Verfiy if
//	 *         the table have the desired Row Count Modified Date: Modified By :
//	 * 
//	 */
//
//	public String getCellValue(WebElement element, String rowIndex, String ColumnIndex)
//	{
//		boolean bfound = false;
//		String Value = null;
//		int i = 0;
//		WebElement element = null;
//		element = FindElement(element);
//		try
//		{
//			Value = driver.findElement(By.xpath(element.GetSeleniumPropertyValue() + "/tbody/tr[" + rowIndex + "]/td[" + ColumnIndex + "]")).getText();
//			System.out.println(Value);
//
//		}
//		catch (Exception e)
//		{
//
//			System.out.println(e);
//			bFound = false;
//
//		}
//		if (bFound == true)
//		{
//			if (Constants.bScreenonPass)
//			{
//				TakeScreenShot(element.GetelementName());
//				Constants.oReports.ScreenshotLog(LogStatus.PASS, "CellValue is " + Value + element.GetelementName(), Constants.sScreenshotFilepath + element.GetelementName() + ".jpeg");
//			}
//		}
//		else
//		{
//			TakeScreenShot(element.GetelementName());
//			Constants.oReports.ScreenshotLog(LogStatus.FAIL, "CellValue not able to retrieve" + element.GetelementName(), Constants.sScreenshotFilepath + element.GetelementName() + ".jpeg");
//		}
//
//		return Value;
//
//	}
//
//	/**
//	 * EA AUTOMATION
//	 * 
//	 * @author :u42946 Date :Jan 27, 2015 11:29:20 PM
//	 * 
//	 *         Description :Accepts Element instance, Row Count and Verfiy if
//	 *         the table have the desired Row Count Modified Date: Modified By :
//	 * 
//	 */
//
//	public boolean verifyTableValueNotPresent(WebElement element, String value, String ColumnIndex)
//	{
//
//		boolean bfound = false;
//		String Value = null;
//		int i = 0;
//		WebElement element = null;
//		element = FindElement(element);
//		List<WebElement> allRows = element.findElements(By.tagName("tr"));
//		System.out.println(allRows.size());
//		for (int row = 2; row <= allRows.size() - 1; row = row + 2)
//		{
//			try
//			{
//				Value = driver.findElement(By.xpath(element.GetSeleniumPropertyValue() + "/tbody/tr[" + row + "]/td[" + ColumnIndex + "]")).getText();
//				if (!(Value.contains(value)))
//					bFound = true;
//				else
//					bFound = false;
//			}
//			catch (Exception e)
//			{
//				System.out.println(e);
//				bFound = false;
//
//			}
//			if (bFound == true)
//			{
//				// System.out.println(element.GetelementName() +
//				// "element found");
//				if (Constants.bScreenonPass)
//				{
//					TakeScreenShot(element.GetelementName());
//					// oReports.ScreenshotLog(LogStatus.PASS,"Value " +Value+
//					// " is present in  "+row,
//					// Constants.sScreenshotFilepath+element.GetelementName()+".jpeg");
//				}
//			}
//			else
//			{
//				TakeScreenShot(element.GetelementName());
//				// oReports.ScreenshotLog(LogStatus.FAIL,"Value is null in"
//				// +row+
//				// element.GetelementName(),
//				// Constants.sScreenshotFilepath+element.GetelementName()+".jpeg");
//			}
//		}
//
//		return bFound;
//
//	}
//
//	/**
//	 * EA AUTOMATION
//	 * 
//	 * @author :u42946 Date :Jan 27, 2015 11:29:20 PM
//	 * 
//	 *         Description :Accepts Element instance, Row Count and Verfiy if
//	 *         the table have the desired Row Count Modified Date: Modified By :
//	 * 
//	 */
//
//	public boolean VerifyColValues(WebElement element, String sText, String ColumnIndex, int nStoponError)
//	{
//		String propvalue = element.GetSeleniumPropertyValue();
//		System.out.println(propvalue);
//		boolean bfound = false;
//		String Value;
//		// int i = 0;
//		WebElement element = null;
//		// List<WebElement> Children = null;
//		element = FindElement(element);
//		// WebElement element = driver.findElement(By.id(propvalue));
//		List<WebElement> allRows = element.findElements(By.tagName("tr"));
//		System.out.println(allRows.size());
//		for (int row = 2; row <= allRows.size() - 1; row++)
//		{
//			Value = driver.findElement(By.xpath("//tbody/tr[" + row + "]/td[" + ColumnIndex + "]")).getText();
//			if (Value.trim().equals(sText))
//			{
//				bfound = true;
//				// System.out.println("Value " +Value+ " is verified in row no "
//				// +row);
//			}
//			else
//				bfound = false;
//			if (bfound == true)
//			{
//				// System.out.println(element.GetelementName() +
//				// "element found");
//				if (Constants.bScreenonPass)
//				{
//					TakeScreenShot(element.GetelementName());
//					Constants.oReports.ScreenshotLog(LogStatus.PASS, "Value " + Value + " is verified in row no " + row, Constants.sScreenshotFilepath + element.GetelementName() + ".jpeg");
//				}
//			}
//			else
//			{
//				TakeScreenShot(element.GetelementName());
//				Constants.oReports.ScreenshotLog(LogStatus.FAIL, "Value " + Value + " is not present in row no " + row + element.GetelementName(), Constants.sScreenshotFilepath + element.GetelementName() + ".jpeg");
//			}
//		}
//
//		return bfound;
//	}
//
//	/**
//	 * EA AUTOMATION
//	 * 
//	 * @author :u42946 Date :Jan 27, 2015 11:29:20 PM
//	 * 
//	 *         Description :Accepts Element instance, Row Count and Verfiy if
//	 *         the table have the desired Row Count Modified Date: Modified By :
//	 * 
//	 */
//	public boolean VerifyTableHeaderValue(WebElement element, String sText, int ColumnIndex, int nStoponError)
//	{
//		List<WebElement> sCol = null;
//
//		List<String> displayedNames = new ArrayList<String>();
//		String[] vals = sText.split(";");
//		List<String> expectedVals = Arrays.asList(vals);
//
//		Boolean bFound = false;
//		String Value;
//		try
//		{
//			Constants.oLogin.placeComponents("VerifyTableHeaderValue");
//			WaitForElementPresent(element);
//			elm = FindElement(element);
//			sCol = elm.findElements(By.xpath(".//th"));
//
//			for (int col = 1; col <= sCol.size(); col++)
//			{
//				Value = driver.findElement(By.xpath("//thead/tr/th[" + col + "]")).getText();
//				displayedNames.add(Value);
//			}
//			System.out.println("Actual List " + displayedNames);
//
//			if (!displayedNames.equals(expectedVals))
//			{
//				System.out.println("Expected Value is not same as Actual Value... Actual Value : " + displayedNames + " Expected Value : " + expectedVals);
//				bFound = false;
//			}
//			else
//			{
//				System.out.println("Expected Value is equal to Actual Value... " + expectedVals);
//				bFound = true;
//			}
//			bFound = true;
//		}
//		catch (Exception e)
//		{
//			TakeScreenShot(sText);
//			Constants.oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verification Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>" + element.GetelementName() + "</td><td style='border:solid black 1px;'>" + sText + "</td></tr></table>", Constants.sScreenshotFilepath + sText + ".jpeg");
//		}
//		if (bFound)
//		{
//			if (Constants.bScreenonPass)
//			{
//				TakeScreenShot(sText);
//				Constants.oReports.ScreenshotLog(LogStatus.PASS, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verification of Tabel header Passed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>" + element.GetelementName() + "</td><td style='border:solid black 1px;'>" + sText + "</td></tr></table>", Constants.sScreenshotFilepath + sText + ".jpeg");
//			}
//			else
//				Constants.oReports.Log(LogStatus.PASS, "Verify Header in Table ", "Verification of Table Header<span class='label success'>Success</span>");
//			return true;
//		}
//		else
//		{
//			TakeScreenShot(sText);
//			System.out.println("Verify Header in Table failed ");
//			Constants.oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Ascending Order in Table Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>" + element.GetelementName() + "</td><td style='border:solid black 1px;'>" + sText + "</td></tr></table>", Constants.sScreenshotFilepath + sText + ".jpeg");
//			if (nStoponError == 1)
//			{
//				Constants.oReports.EndTest();
//				System.exit(0);
//			}
//			return false;
//		}
//	}
}

