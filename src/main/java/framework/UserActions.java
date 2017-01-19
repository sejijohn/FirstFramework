package framework;

import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.Driver;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.hssf.record.formula.functions.Value;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TakesScreenshot;

import framework.ObjectCreator;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;
public class UserActions 
{
  public static Boolean bScreenonPass = true;
  public static WebElement elm;
  public static WebDriver driver;
  public static Boolean   bStatus=false;
  public static	Boolean bFound;
  public static Reports oReports = new Reports();
  public static LoginView oLogin = new LoginView();
  public static String sActual="";
  public ObjectCreator objects;
  public static String lqedate;
    
  public static WebElement FindElement(ObjectCreator object) 
  {
	  WebElement element = null;	     
      try
      {   //checks the element identifier	             

         switch (object.GetSeleniumProperty().toUpperCase())
         {
             case "ID": element = driver.findElement(By.id(object.GetSeleniumPropertyValue())); break;
             case "CLASSNAME": element = driver.findElement(By.className(object.GetSeleniumPropertyValue())); break;
             case "XPATH": element = driver.findElement(By.xpath(object.GetSeleniumPropertyValue())); break;
             case "LINKTEXT": element = driver.findElement(By.linkText(object.GetSeleniumPropertyValue())); break;
             case "NAME": element = driver.findElement(By.name(object.GetSeleniumPropertyValue())); break;
             case "CSS": element = driver.findElement(By.cssSelector(object.GetSeleniumPropertyValue())); break;
             default:break;	                     
         }	           
      }
      catch(Exception e)
      {
    	  Log.info(object+"Not Found");
      }	     
      return element;			
  }
  
  public static List<WebElement> FindElements(ObjectCreator object)
  {
	  List<WebElement> elements=null;
	  try
	  {
		  //System.out.println("checkfunction 444");
		  switch(object.GetSeleniumProperty().toUpperCase())
		  {
		     case "ID": elements = driver.findElements(By.id(object.GetSeleniumPropertyValue())); break;
             case "CLASSNAME": elements = driver.findElements(By.className(object.GetSeleniumPropertyValue())); break;
             case "XPATH": elements = driver.findElements(By.xpath(object.GetSeleniumPropertyValue())); break;
             case "LINKTEXT": elements = driver.findElements(By.linkText(object.GetSeleniumPropertyValue())); break;
             case "NAME": elements = driver.findElements(By.name(object.GetSeleniumPropertyValue())); break;
             case "CSS": elements = driver.findElements(By.cssSelector(object.GetSeleniumPropertyValue())); break;
             default:break;	
		  
		  }
		  
	  }
	  catch(Exception e)
	  {
		  Log.info(object+"Not Found");  
	  }
	  return elements;	
  }
  
  
  public static void WaitForElementPresent(ObjectCreator object)
  {
	WebDriverWait wait;
	@SuppressWarnings("unused")
	WebElement elm ;
    try
	{	    	
	  switch (object.GetSeleniumProperty().toUpperCase())
      {
          
      case "ID"		  : wait =  new WebDriverWait(driver, Constants.nSeconds);
      			  		elm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(object.GetSeleniumPropertyValue()))); break;
      case "CLASSNAME": wait =  new WebDriverWait(driver, Constants.nSeconds);
      					elm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(object.GetSeleniumPropertyValue()))); break;
      case "XPATH"    : wait =  new WebDriverWait(driver, Constants.nSeconds);
      					elm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(object.GetSeleniumPropertyValue())));break;
      case "TEXT"     : wait =  new WebDriverWait(driver, Constants.nSeconds);
      					elm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(object.GetSeleniumPropertyValue())));break;
      case "NAME"     : wait =  new WebDriverWait(driver, Constants.nSeconds);
      					elm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(object.GetSeleniumPropertyValue())));break;
      case "CSS"      : wait =  new WebDriverWait(driver, Constants.nSeconds);
              					elm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(object.GetSeleniumPropertyValue())));break;
      default		  :  break;
          }	          
      }
      catch(Exception e)
      {
    	  System.out.println(e);
       
      }                       
  }
  public static boolean ClearText(ObjectCreator object)
  {
    try
    {
	   oLogin.placeComponents("ClearText");             
	   WaitForElementPresent(object);
	   elm = FindElement(object);
	   if (elm != null)
	   {
		   elm.clear();	                  
	   }
   }
   catch (Exception e)
   {
	   System.out.println("Cleartext failed on " +object);
	   return false;
   }
   return true;
  }
  public static boolean OpenBrowser(String Url,String Browser)
  {         
      try
      {
    	  System.out.println("\n"+Browser);
    	  oLogin.placeComponents("OpenBrowser");
          if (Browser == "IE")
          {System.setProperty("webdriver.ie.driver", Constants.sDriverPath+"\\IEDriverServer.exe");
              driver = new InternetExplorerDriver();
          }
          else if (Browser.equals("Mozilla"))
          {
            //driver = new FirefoxDriver();
              //String pathToBinary = "C:\\Users\\ac98587\\AppData\\Local\\Mozilla Firefox\\firefox.exe"; 
              System.setProperty("webdriver.firefox.bin", "C:\\Users\\ac98587\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
			    driver = new FirefoxDriver();
        		//FirefoxBinary ffBinary = new FirefoxBinary(new File(pathToBinary)); 
        		//FirefoxProfile firefoxProfile = new FirefoxProfile();
        		//driver = new FirefoxDriver(ffBinary, firefoxProfile); 
          }
          else if(Browser.equals("Chrome"))
          {
        	  //System.setProperty("webdriver.chrome.driver", Constants.sDriverPath+"\\chromedriver.exe");
        	  System.setProperty("webdriver.chrome.driver", Constants.sFilePath+"\\Chrome Driver\\chromedriver.exe");
              driver =new ChromeDriver();
          }        
          driver.manage().window().maximize();
          driver.navigate().to(Url);
          oReports.Log(LogStatus.PASS, "OpenBrowser: "+Browser+","+Url.substring(0, 20), "Passed");
          Log.info("OpenBrowser Passed");
      }
      catch (Exception e)
      {
    	  System.out.println(e);
    	  System.exit(1);              
      }
      return true;         
  }
  public static boolean EnterText(ObjectCreator object, String sValue)
  { 
	  try 
	   {
		   oLogin.placeComponents("EnterText");
		   WaitForElementPresent(object);
		   elm = FindElement(object);
           if (elm != null)
           {
               elm.clear();
               elm.sendKeys(sValue);
           }	       
	   }
	   catch(Exception e)
	   {
		   TakeScreenShot(object.GetObjectName());
		   oReports.ScreenshotLog(LogStatus.FAIL, "Enter Text",  Constants.sScreenshotFilepath+object.GetObjectName()+".jpeg");
		   return false;
	   }
      	oReports.Log(LogStatus.PASS, "Enter Text", "Enter Text into "+object.GetObjectName()+" <span class='label success'>Success</span>");
      	return true;			 
	   }
  public static boolean SelectElement(ObjectCreator object, String sValue)
  {
    try
    {   
	   oLogin.placeComponents("SelectElement");        
       WaitForElementPresent(object);
       elm = FindElement(object);
       if (elm != null)
       {
           Select dropdown = new Select(elm);
           String content = elm.getText();
           if (!content.contentEquals(sValue))
           	dropdown.selectByVisibleText(sValue);
       }   
    }
    catch(Exception e)
    { 
	   TakeScreenShot(object.GetObjectName());
	   oReports.ScreenshotLog(LogStatus.FAIL, "Select Element",  Constants.sScreenshotFilepath+object.GetObjectName()+".jpeg");
       System.out.println("Drop down selection issue");
	   return false;
    }
    if(bScreenonPass)
	   oReports.Log(LogStatus.PASS, "Select Element", "Select Element For "+object.GetObjectName()+" <span class='label success'>Success</span>");
    return true;
  }
  public static boolean SelectElementcontains(ObjectCreator object, String sValue)
  {
    try
    {       
    	oLogin.placeComponents("SelectElementcontains");
        List<WebElement> sOptions;	        
        WaitForElementPresent(object);
        elm = FindElement(object);
        String content = elm.getText();
        if (elm != null)
        {
            Select dropdown = new Select(elm);
            sOptions = dropdown.getOptions();
            for(WebElement s : sOptions)
            {
                if (s.getText().contains(sValue) && !sValue.contains(content))
                {
                    dropdown.selectByVisibleText(s.getText());
                    elm.sendKeys("[Enter]");
                    if(bScreenonPass)
                    	oReports.Log(LogStatus.PASS, "Select", "Selected " + object.GetObjectName() + " <span class='label success'>success</span>");
                    break;
                }
            }	
        }
    }
    catch (Exception e)
    { 	
    	TakeScreenShot(object.GetObjectName());
    	oReports.ScreenshotLog(LogStatus.FAIL, "Select Element",  Constants.sScreenshotFilepath+object.GetObjectName()+".jpeg");
        return false;
    }	           
    return true;
  }
  public static boolean ClickObject(ObjectCreator object)
  {
      try
      {
    	oLogin.placeComponents("ClickObject");
    	WaitForElementPresent(object);
    	elm =FindElement(object);
    	if (elm != null)
    	{   
    		if(bScreenonPass)
    		{
    			TakeScreenShot(object.GetObjectName());
    			oReports.Log(LogStatus.PASS, "Click", "Clicked " + object.GetObjectName() + " <span class='label success'>success</span>");
    		}
    		elm.click();
    		return true;
    	}                        
      }
      catch(Exception e)
      {
    	  System.out.println(e);
    	  TakeScreenShot(object.GetObjectName());
    	  oReports.ScreenshotLog(LogStatus.FAIL, "Click  Object",  Constants.sScreenshotFilepath+object.GetObjectName()+".jpeg");
    	  oReports.EndTest();
    	  return false;
       }      
      return false;	
   }
  public static boolean acceptalert()
  {
	  try 
	  {
		driver.switchTo().alert();
		oLogin.placeComponents("acceptalert");
   	 	Alert alert = driver.switchTo().alert();
   	 	alert.accept();      	 	
	  } 
	  catch (NoAlertPresentException e) 
	  {
	  return false;
	  }    	  
     return true;
  }
  public static void CloseBrowser()
   {
       driver.quit();	      
   }
  public static boolean VerifyTextPresent(ObjectCreator object, String sExpected, int nStoponError) 
   {
  	oLogin.placeComponents("VerifyText");
    Boolean bFound = false;
    try
    {            
       WaitForElementPresent(object);
       elm = FindElement(object);
       sExpected=sExpected.trim().toUpperCase();
       sActual = elm.getText().toUpperCase();
       if(sActual.equalsIgnoreCase(""))
        {
        	try
        	{
        		sActual = elm.getAttribute("value");
        	}
        	catch(Exception e)
        	{}
        	try
        	{
        		sActual = elm.getAttribute("innerHTML");
        	}
        	catch(Exception e)
        	{}
        	try
        	{
        		sActual = elm.getAttribute("text");
        	}
        	catch(Exception e)
        	{}               	
        }
        sActual = FormatString(sActual).toUpperCase();
        if(sActual.contains(FormatString(sExpected).trim()))
        {
            bFound = true;
            System.out.println("Verify Text of " + sExpected + " passed");
        	
        	if(bScreenonPass)
    		{
        		//TakeScreenShot(UserActions.StringTrimmer(sExpected));
        		//to scroll for screenshot
        		takeSpecificScreenshot(elm,UserActions.StringTrimmer(sExpected));
        		//replaced sExpected to UserActions.StringTrimmer(sExpected)
        		oReports.ScreenshotLog(LogStatus.PASS, "<table style='border:solid black 1px;'><col width='400'><col width='400'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Text Passed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Actual</td><td style='border:solid black 1px;'>Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+sActual+"</td><td style='border:solid black 1px;'>"+sExpected+"</td></tr></table>",  Constants.sScreenshotFilepath+UserActions.StringTrimmer(sExpected)+".jpeg");
    		}
        	
        }
        else 
        {
        	System.out.println("Verify Text of " + sExpected + " failed");
        	//TakeScreenShot(sExpected);
        	takeSpecificScreenshot(elm,sExpected);
        	oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Text Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Actual</td><td style='border:solid black 1px;'>Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+sActual+"</td><td style='border:solid black 1px;'>"+sExpected+"</td></tr></table>",  Constants.sScreenshotFilepath+sExpected+".jpeg");
        	if(nStoponError == 1)
        	{
        	  oReports.EndTest();
        	  System.exit(0);
        	}
        }               
    }
    catch (Exception e)
    {
        System.out.println(e);
    	bFound = false;
    }
    return bFound;
  }
  public static boolean VerifyTextNotPresent(ObjectCreator object, String sExpected, int nStoponError)
  {
	oLogin.placeComponents("VerifyText");
    Boolean bFound = false;
    @SuppressWarnings("unused")
	List<WebElement> children;
    try
    {          
       WaitForElementPresent(object);
       elm = FindElement(object);
       children = elm.findElements(By.cssSelector("*"));
       sExpected=sExpected.trim().toUpperCase();
       sActual = elm.getText().toUpperCase();
       if(sActual.equalsIgnoreCase(""))
       {
        	try
        	{
        		sActual = elm.getAttribute("value");
        	}
        	catch(Exception e)
        	{
        	}
        	try
        	{
        		sActual = elm.getAttribute("innerHTML");
        	}
        	catch(Exception e)
        	{
        	}                	
        }
        sActual = FormatString(sActual).toUpperCase();
        if(sActual.contains(sExpected.trim()))
        {
            bFound = true;
        }
        else 
        {
            bFound = false;
        }
       
    }
    catch (Exception e)
    {
        bFound = false;
    }
    if(bFound == true)
    {
    	System.out.println("Verify Not Text of " + sExpected + " Failed");
    	if(bScreenonPass)
    	{
    		TakeScreenShot(sExpected);
    		oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Text Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Actual</td><td style='border:solid black 1px;'>Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+sActual+"</td><td style='border:solid black 1px;'>"+sExpected+"</td></tr></table>",  Constants.sScreenshotFilepath+sActual+".jpeg");
    	}
    	else
    		oReports.Log(LogStatus.PASS, "Verifyr Not Text", "Verify Not Text "+object.GetObjectName()+" <span class='label success'>Success</span>");
    }
    else
    {
      System.out.println("Verify Not Text of " + sExpected + " Passed");
	  TakeScreenShot(sExpected);
	  oReports.ScreenshotLog(LogStatus.PASS, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Text Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Actual</td><td style='border:solid black 1px;'>Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+sActual+"</td><td style='border:solid black 1px;'>"+sExpected+"</td></tr></table>",  Constants.sScreenshotFilepath+sActual+".jpeg");
	  if(nStoponError == 1)
    	  System.exit(0);
    }
  
    return bFound;
 }
  public static boolean ObjectExists(ObjectCreator object)
    {
    	boolean bfound=false;
        try
        {
        	oLogin.placeComponents("VerifyObject");
            WaitForElementPresent(object);
            elm = FindElement(object);
            if(elm.isDisplayed())
            	bfound=true;
        }
        catch (Exception e)
        {
            elm = null;
            System.out.println("Element Not Found"+object.GetObjectName());
            bfound= false;
        }
        if (bfound == true)
        {
            System.out.println(object.GetObjectName() + "Object found");
            if(bScreenonPass)
            {
            	TakeScreenShot(object.GetObjectName());
            	oReports.ScreenshotLog(LogStatus.PASS, "Element Found " + object.GetObjectName(),  Constants.sScreenshotFilepath+object.GetObjectName()+".jpeg");
            }
        }
        else 
        {
        	TakeScreenShot(object.GetObjectName());
        	oReports.ScreenshotLog(LogStatus.FAIL, "Element Not Found " + object.GetObjectName(),  Constants.sScreenshotFilepath+object.GetObjectName()+".jpeg");
        }
      return bfound;
    }
  public static boolean VerifyTitle(String sExpected, int nStoponError)
   {
        try
        {
        	oLogin.placeComponents("VerifyTitle");
            String sActual= FormatString(driver.getTitle()).toUpperCase();
            sExpected=sExpected.trim().toUpperCase();
            if(sActual.contains(sExpected))
            {
            	if(bScreenonPass)
            	{
            		TakeScreenShot(sExpected);
            		oReports.ScreenshotLog(LogStatus.PASS, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Ttile Passed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Actual</td><td style='border:solid black 1px;'>Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+sActual+"</td><td style='border:solid black 1px;'>"+sExpected+"</td></tr></table>",  Constants.sScreenshotFilepath+sExpected+".jpeg");
            	}
            	else
            		oReports.Log(LogStatus.PASS, "Verifyr Text", "Verify Title "+" <span class='label success'>Success</span>");
            }
            else 
            {
            	TakeScreenShot(sExpected);
            	oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Title Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Actual</td><td style='border:solid black 1px;'>Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+sActual+"</td><td style='border:solid black 1px;'>"+sExpected+"</td></tr></table>",  Constants.sScreenshotFilepath+sExpected+".jpeg");
            }
        }
        catch (Exception e)
        {          
        	if(nStoponError == 1)
          	  System.exit(0);
        	return false;
        }           
        return true;
    }
  public static boolean VerifyNotTitle(String sExpected, int nStoponError)
    {
    	try
    	{
    		oLogin.placeComponents("VerifyNotTitle");
    		 String sActual= FormatString(driver.getTitle());
    		 sExpected=sExpected.trim();
             if(!sActual.contains(sExpected))
             {
             	 if(bScreenonPass)
             	 {
                	 oReports.Log(LogStatus.PASS, "Verify NOT Title", "Verify NOT Title " + sExpected + " <span class='label success'>Success</span>");
                 	 return true;
             	 }
             }
             else 
             {                	 
             	oReports.Log(LogStatus.FAIL, "Verify NOT Title  Failed ", "Verify NOT Title Failed <span class='label failure'>Fail</span>");
             	return false;
             }
    	}
    	catch(Exception e)
    	{
    		oReports.Log(LogStatus.FAIL, "Verify NOT Title  Failed ", "Verify NOT Title Failed Actual="+sActual+"andExpected="+sExpected+" <span class='label failure'>Fail</span>");
    		if(nStoponError == 1)
            	  System.exit(0);
    		return false;
    	}
    	return false;
    }
  public static boolean VerifyAlert(String sExpected)
    {
    	 try
         {
    		 oLogin.placeComponents("VerifyAlert");
    		 if(isAlertPresent())
             {
                 Alert alert = driver.switchTo().alert();
                 String sActual = FormatString(alert.getText()).toUpperCase();
                 sExpected=sExpected.trim().toUpperCase();
                 if(sActual.contains(sExpected.trim()))
                 {
             	   oReports.Log(LogStatus.PASS, "Verify Alert Text", "Verify Alert " + sExpected + " <span class='label success'>Success</span>");
                 }
                 else 
                 {
             	   oReports.Log(LogStatus.FAIL, "Verify Alert  Failed ", "Verify Alert Failed <span class='label failure'>Fail</span>");
                 }
             }
    		 else 
    		 {
    			 oReports.Log(LogStatus.FAIL, "Verify Alert  Failed ", "Verify Alert Failed Actual="+sActual+" and Expected="+sExpected+"<span class='label failure'>Fail</span>");
    		 }
         }
         catch (Exception e)
         {
       	  TakeScreenShot(sActual);
       	  oReports.ScreenshotLog(LogStatus.FAIL, "Verify Text Failed",  Constants.sScreenshotFilepath+sExpected+".jpeg");
          return false;
         }          
         return true;
    }
  public static boolean isAlertPresent() 
  {
        try 
        {
          driver.switchTo().alert();
          return true;
        }
        catch (NoAlertPresentException e) 
        {
          return false;
        }
  }
  public static boolean DismissAlert()
  {
	  if(isAlertPresent())
	  {
		  Alert alert = driver.switchTo().alert();
		  alert.dismiss();
		  return true;
	  }
	  else
		  return false; 
  }
  public static boolean UnCheckChkbox(ObjectCreator object)
    {
        try
        {
          WaitForElementPresent(object);
          elm = FindElement(object);
          String checked= elm.getAttribute("checked");
          if(!checked.equals(false))
          {            
           	elm.click();
           	bFound= true;
          }    
        }
        catch(Exception e)
        {               	
            	  bFound= false;
        }
		return bFound;      
    }
  public static boolean EnterKeyPress(ObjectCreator object)
  {
	try
    {
        WaitForElementPresent(object);
        elm = FindElement(object);
        elm.sendKeys(Keys.ENTER);
     }
    catch (Exception e)
    {
      oReports.Log(LogStatus.FAIL, "Key Press Failed ", "Key Press Enter Failed <span class='label failure'>Fail</span>");
      
      return false;
    }
    oReports.Log(LogStatus.PASS, "Enter Text", "Enter Text into " + object.GetObjectName() + " <span class='label success'>Success</span>");
    return true;
  }
  public static void TakeScreenShot(String sFileName) 
  {   
    try 
    {
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot,new File( Constants.sScreenshotFilepath+sFileName+".jpeg"));
    }
      catch(Exception e)
      {
        System.out.println(e.getMessage());
      }
  }
  
  //new added method
  public static void takeSpecificScreenshot(WebElement Screlement,String sFileName)
  {
	  try 
	    {
		    Point point = Screlement.getLocation();
		    ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,"+(point.getY())+");");
	        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        //BufferedImage  fullImg = ImageIO.read(screenshot);   
	        //int eleWidth = Screlement.getSize().getWidth();
	        //int eleHeight = Screlement.getSize().getHeight();
	        //BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(), eleWidth,eleHeight);
	        //ImageIO.write(eleScreenshot, "jpeg", screenshot);
	        FileUtils.copyFile(screenshot,new File( Constants.sScreenshotFilepath+sFileName+".jpeg"));
	    }
	      catch(Exception e)
	      {
	        System.out.println(e.getMessage());
	      }
  }
  
  
  public static String FormatString(String sValue)
    { 
	  if(sValue.contains("\n"))
	  {
    	sValue = sValue.replace("\n", "").trim();
	  }
	  sValue.replaceAll(" ", "");
	  return sValue;
    }
  public static boolean Verify_Object_Existence(ObjectCreator object)
  {
	   try
	   { 
		 WaitForElementPresent(object);
		 elm = FindElement(object);
		if(elm.isDisplayed())	        	
		{
			System.out.println("Object is identified" + object.GetObjectName());
			TakeScreenShot(object.GetObjectName());
    		oReports.ScreenshotLog(LogStatus.PASS, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Object Verified</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Actual</td><td style='border:solid black 1px;'>Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td></tr></table>",  Constants.sScreenshotFilepath+object.GetObjectName()+".jpeg");
		}
		else
		{
			System.out.println("Not able to find" + object.GetObjectName());
			TakeScreenShot(object.GetObjectName());
    		oReports.ScreenshotLog(LogStatus.PASS, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Ttile Passed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Actual</td><td style='border:solid black 1px;'>Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+"NULL"+"</td><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td></tr></table>",  Constants.sScreenshotFilepath+object.GetObjectName()+".jpeg");
		}
	   }
	   catch(Exception e)
	   {
	       return false;
	   }
   return true;
  }
  public static String StringTrimmer(String sText)
  {
	     //appended .replaceAll("\\/", "") for date
	   	  return sText.trim().replaceAll("\n","").replaceAll(" ","").replaceAll(":","").replaceAll("_","").replaceAll("\\/", "");
  }
  public static boolean VerifyTableText(ObjectCreator object, String sText, int nStoponError)
  {
	  List <WebElement> child = null;
	  Boolean bFound = false;
	  String sElementText = null;
	  sText = StringTrimmer(sText);
	  try 
	  {
		  oLogin.placeComponents("VerifyTableText");
		  WaitForElementPresent(object);
		  child = FindChildren(object);
	      if (child != null)
	      {
	    	 for (WebElement webElement : child) 
	    	 {
	    		 sElementText = StringTrimmer(webElement.getText().trim());
	    		 if(sElementText.contains(sText.trim()))
	    		 {
	        		  System.out.println("PASS Verify table for "+sText);
	        		  bFound = true;
	        		  break;
	    		 }
	    	 }
	      }	       
	  }
	  catch(Exception e)
	  {
		TakeScreenShot(sText);
		oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Table Text Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
	  }
      if(bFound)
      {
    	  if(bScreenonPass)
		   {
		   TakeScreenShot(sText);
		   oReports.ScreenshotLog(LogStatus.PASS, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Table Text Passed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
		   }
	  else
		oReports.Log(LogStatus.PASS, "Verify Table Text", "Verify Table Text "+sText+" <span class='label success'>Success</span>");
      return true;   			   
      }
      else
      {
		   TakeScreenShot(sText);
		   System.out.println("Verify Table Text failed for "+ sText);
		   oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Table Text Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
		   if(nStoponError == 1)
	       {
	         oReports.EndTest();
	         System.exit(0);
	       }
		   return false; 
      }      		 
  }
  public static List<WebElement> FindChildren(ObjectCreator object) 
  {
	  WebElement element = null;
	  List<WebElement> Children = null;
      try
      {   //checks the element identifier	             

         switch (object.GetSeleniumProperty().toUpperCase())
         {
             case "ID": element = driver.findElement(By.id(object.GetSeleniumPropertyValue())); break;
             case "CLASSNAME": element = driver.findElement(By.className(object.GetSeleniumPropertyValue())); break;
             case "XPATH": element = driver.findElement(By.xpath(object.GetSeleniumPropertyValue())); break;
             case "LINKTEXT": element = driver.findElement(By.linkText(object.GetSeleniumPropertyValue())); break;
             case "NAME": element = driver.findElement(By.name(object.GetSeleniumPropertyValue())); break;
             case "CSS": element = driver.findElement(By.cssSelector(object.GetSeleniumPropertyValue())); break;
             default:break;	                     
         }
         Children = element.findElements(By.cssSelector("*"));
      }
      catch(Exception e)
      {
    	  Log.info(object.GetObjectName()+"Not Found");
      }	     
      return Children;			
  }
  public static boolean VerifyRadioSelected(ObjectCreator object, int nStoponError)
  {
	  boolean bFound = false;
	  try
      {
    	oLogin.placeComponents("ClickObject");
	    WaitForElementPresent(object);
        elm =FindElement(object);
        if (elm != null)
        {
            String sSelected = elm.getAttribute("checked");
            if(sSelected.equals("true"))
            {
                if(bScreenonPass)
                {
                	TakeScreenShot(object.GetObjectName());
                	oReports.ScreenshotLog(LogStatus.PASS, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Radio Button</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name </td><td style='border:solid black 1px;'>State</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td></tr></table>",  Constants.sScreenshotFilepath+object.GetObjectName()+".jpeg");
                }
                else
                	oReports.Log(LogStatus.PASS, "Check Radio Selection Passed ", "Check Radio Selection Passed <span class='label success'>Pass</span>");           	
                bFound = true;
            }
        }
        else
        {
        	if(nStoponError == 0)System.exit(1);
        }
      }
      catch(Exception e)
      {
    	TakeScreenShot(object.GetObjectName());
       	oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Radio Button failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name </td><td style='border:solid black 1px;'>State</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td></tr></table>",  Constants.sScreenshotFilepath+object.GetObjectName()+".jpeg");
    	return bFound;
      }
	  return bFound;
  }
  public static boolean VerifyRadioNotSelected(ObjectCreator object, int nStoponError)
  {
	  try
	  {
		oLogin.placeComponents("ClickObject");
	    WaitForElementPresent(object);
	    elm =FindElement(object);
	    if (elm != null)
	    {
	        String sSelected = elm.getAttribute("checked");
	        if(sSelected != "checked")
	        {
	            if(bScreenonPass)
	            {
	            	TakeScreenShot(object.GetObjectName());
	            	oReports.Log(LogStatus.PASS, "Check Not Radio Selection", "Selected Radio " + object.GetObjectName() + " <span class='label success'>success</span>");
	            }
	        	return true;
	        }
	    }
	    else
        {
        	if(nStoponError == 0)System.exit(1);
        }
	  }
      catch(Exception e)
      {
    	   oReports.Log(LogStatus.FAIL, "Check Not Radio Selection Failed ", "Check Radio Selection Failed "+ object.GetObjectName() +" <span class='label failure'>Fail</span>");
    	   return false;
      }
	  return false;
  }
  public static boolean ClearText(ObjectCreator object, int nStoponError)
  {
    try
    {
	   oLogin.placeComponents("ClearText");             
	   WaitForElementPresent(object);
	   elm = FindElement(object);
	   if (elm != null)
	   {
		   elm.clear();	                  
	   }
   }
   catch (Exception e)
   {
	  System.out.println("Cleartext failed on " +object);
	  if(nStoponError == 1)
 	  {
 		oReports.EndTest();
 		System.exit(0);
 	  } 
	   return false;
   }
   return true;
  }
  public static boolean EnterText(ObjectCreator object, String sValue, int nStoponError)
  { 
	  try 
	   {
		   oLogin.placeComponents("EnterText");
		   WaitForElementPresent(object);
		   elm = FindElement(object);
           if (elm != null)
           {
               elm.clear();
               elm.sendKeys(sValue);
           }	       
	   }
	   catch(Exception e)
	   {
		   TakeScreenShot(object.GetObjectName());
		   oReports.ScreenshotLog(LogStatus.FAIL, "Enter Text",  Constants.sScreenshotFilepath+object.GetObjectName()+".jpeg");
		   if(nStoponError==1)
	       {
	    	  oReports.EndTest();
	    	  System.exit(0);
	       } 
		   return false;
	   }
      	oReports.Log(LogStatus.PASS, "Enter Text", "Enter Text into "+object.GetObjectName()+" <span class='label success'>Success</span>");
      	return true;			 
	   }
  public static boolean SelectElement(ObjectCreator object, String sValue,int nStoponError)
  {
    try
    {   
	   oLogin.placeComponents("SelectElement");        
       WaitForElementPresent(object);
       elm = FindElement(object);
       if (elm != null)
       {
           Select dropdown = new Select(elm);
           String content = elm.getText();
           if (!content.contentEquals(sValue))
           	dropdown.selectByVisibleText(sValue);
       }   
    }
    catch(Exception e)
    { 
	   TakeScreenShot(object.GetObjectName());
	   oReports.ScreenshotLog(LogStatus.FAIL, "Select Element",  Constants.sScreenshotFilepath+object.GetObjectName()+".jpeg");
       System.out.println("Drop down selection issue");
       if(nStoponError==1)
 	   {
 		  oReports.EndTest();
 		  System.exit(0);
 	   } 
	   return false;
    }
    if(bScreenonPass)
	   oReports.Log(LogStatus.PASS, "Select Element", "Select Element For "+object.GetObjectName()+" <span class='label success'>Success</span>");
    return true;
  }
  public static boolean SelectElementcontains(ObjectCreator object, String sValue,int nStoponError)
  {
    try
    {       
    	oLogin.placeComponents("SelectElementcontains");
        List<WebElement> sOptions;	        
        WaitForElementPresent(object);
        elm = FindElement(object);
        String content = elm.getText();
        if (elm != null)
        {
            Select dropdown = new Select(elm);
            sOptions = dropdown.getOptions();
            for(WebElement s : sOptions)
            {
                if (s.getText().contains(sValue) && !sValue.contains(content))
                {
                    dropdown.selectByVisibleText(s.getText());
                    elm.sendKeys("[Enter]");
                    if(bScreenonPass)
                    	oReports.Log(LogStatus.PASS, "Select", "Selected " + object.GetObjectName() + " <span class='label success'>success</span>");
                    break;
                }
            }	
        }
    }
    catch (Exception e)
    { 	
    	TakeScreenShot(object.GetObjectName());
    	oReports.ScreenshotLog(LogStatus.FAIL, "Select Element",  Constants.sScreenshotFilepath+object.GetObjectName()+".jpeg");
    	if(nStoponError==1)
	  	{
	  	  oReports.EndTest();
	  	  System.exit(0);
	  	} 
    	return false;
    }	           
    return true;
  }
  public static boolean ClickObject(ObjectCreator object, int nStoponError)
  {
      try
      {
    	oLogin.placeComponents("ClickObject");
    	WaitForElementPresent(object);
    	elm =FindElement(object);
    	if (elm != null)
    	{
    		elm.click();
    		if(bScreenonPass)
    		{
    			TakeScreenShot(object.GetObjectName());
    			oReports.Log(LogStatus.PASS, "Click", "Clicked " + object.GetObjectName() + " <span class='label success'>success</span>");
    		}
    		return true;
    	}                        
      }
      catch(Exception e)
      {
    	  TakeScreenShot(object.GetObjectName());
    	  oReports.ScreenshotLog(LogStatus.FAIL, "Click  Object",  Constants.sScreenshotFilepath+object.GetObjectName()+".jpeg");
    	  if(nStoponError==1)
    	  {
    		  oReports.EndTest();
    		  System.exit(0);
    	  }  
    	  return false;
       }      
      return false;	
   }
  
  public static boolean VerifyColumnValueInTable(ObjectCreator object, String sText, String ColumnIndex ,int nStoponError)
  {
	  List <WebElement> sRow = null;
	  Boolean bFound = false;
//	  String sElementText = null;
	  sText = StringTrimmer(sText);
	  String Value;
	  try 
	  {
		  oLogin.placeComponents("VerifyTableContent");
		  WaitForElementPresent(object);
		  elm = FindElement(object);
		  sRow = elm.findElements(By.xpath(".//tr"));
		  
		  for(int row = 1 ; row<=sRow.size()-1; row++)
		  {
			  Value = driver.findElement(By.xpath("//tbody/tr["+row+"]/td["+ColumnIndex+"]")).getText();
			  if (Value.trim().equals(sText))
			  {
				 System.out.println("Value " +Value+ " is verified in row no " +row+ ":)");
			  }
		  }
		 bFound=true;
	  }
	  catch(Exception e)
	  {
		TakeScreenShot(sText);
		oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Table Text Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
	  }
      if(bFound)
      {
    	  if(bScreenonPass)
		   {
		   TakeScreenShot(sText);
		   oReports.ScreenshotLog(LogStatus.PASS, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Table Text Passed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
		   }
	  else
		oReports.Log(LogStatus.PASS, "Verify Table Text", "Verify Table Text "+sText+" <span class='label success'>Success</span>");
      return true;   			   
      }
      else
      {
		   TakeScreenShot(sText);
		   System.out.println("Verify Table Text failed for "+ sText);
		   oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Table Text Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
		   if(nStoponError == 1)
	       {
	         oReports.EndTest();
	         System.exit(0);
	       }
		   return false; 
      }      		 
  }
  
  public static boolean VerifyAscendingInTable(ObjectCreator object, String sText, String ColumnIndex ,int nStoponError)
  {
	  List <WebElement> sRow = null;
	  
	  List<String> displayedNames = new ArrayList<String>();
      List<String> sortedNames = new ArrayList<String>();
      
	  Boolean bFound = false;
	  String Value;
	  try 
	  {
		  oLogin.placeComponents("VerifyAscendingInTable");
		  WaitForElementPresent(object);
		  elm = FindElement(object);
		  sRow = elm.findElements(By.xpath(".//tr"));
		  
		  for(int row = 1 ; row<=sRow.size()-1; row++)
		  {
			  Value = driver.findElement(By.xpath("//tbody/tr["+row+"]/td["+ColumnIndex+"]")).getText();
			  displayedNames.add(Value);
			  sortedNames.add(Value);
		  }
		 
		 Collections.sort(sortedNames);
		 System.out.println("Actual List "+displayedNames);
		 System.out.println("Sorted List "+sortedNames);
		  
		  if (!displayedNames.equals(sortedNames))
          {
              System.out.println("Not in Ascending Order...!!!");
              bFound=false;
          }
		  else
		  {
			  System.out.println("Values are in Ascending Order.");
			  bFound=true;
		  }
		 
	  }
	  catch(Exception e)
	  {
		TakeScreenShot(sText);
		oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Ascending Order in Table Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
	  }
      if(bFound)
      {
    	  if(bScreenonPass)
		   {
		   TakeScreenShot(sText);
		   oReports.ScreenshotLog(LogStatus.PASS, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Ascending Order in Table Passed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
		   }
	  else
		oReports.Log(LogStatus.PASS, "Verify Ascending Order in Table ", "Verify Ascending Order in Table Text <span class='label success'>Success</span>");
      return true;   			   
      }
      else
      {
		   TakeScreenShot(sText);
		   System.out.println("Verify Ascending Order in Table failed ");
		   oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Ascending Order in Table Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
		   if(nStoponError == 1)
	       {
	         oReports.EndTest();
	         System.exit(0);
	       }
		   return false; 
      }      		 
  }
  
  public static boolean VerifyDescendingInTable(ObjectCreator object, String sText, String ColumnIndex ,int nStoponError)
  {
	  List <WebElement> sRow = null;
	  
	  List<String> displayedNames = new ArrayList<String>();
      List<String> sortedNames = new ArrayList<String>();
      
	  Boolean bFound = false;
	  String Value;
	  try 
	  {
		  oLogin.placeComponents("VerifyDescendingInTable");
		  WaitForElementPresent(object);
		  elm = FindElement(object);
		  sRow = elm.findElements(By.xpath(".//tr"));
		  
		  for(int row = 1 ; row<=sRow.size()-1; row++)
		  {
			  Value = driver.findElement(By.xpath("//tbody/tr["+row+"]/td["+ColumnIndex+"]")).getText();
			  displayedNames.add(Value);
			  sortedNames.add(Value);
		  }
		 
		 Comparator<String> collections = Collections.reverseOrder();
		 Collections.sort(sortedNames, collections); 
		 
		 System.out.println("Actual List "+displayedNames);
		 System.out.println("Sorted List "+sortedNames);
		  
		  if (!displayedNames.equals(sortedNames))
          {
              System.out.println("Not in Descending Order...!!!");
              bFound=false;
          }
		  else
		  {
			  System.out.println("Values are in Descending Order.");
			  bFound=true;
		  }
		 
	  }
	  catch(Exception e)
	  {
		TakeScreenShot(sText);
		oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Descending Order in Table Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
	  }
      if(bFound)
      {
    	  if(bScreenonPass)
		   {
		   TakeScreenShot(sText);
		   oReports.ScreenshotLog(LogStatus.PASS, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Descending Order in Table Passed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
		   }
	  else
		oReports.Log(LogStatus.PASS, "Verify Ascending Order in Table ", "Verify Ascending Order in Table Text <span class='label success'>Success</span>");
      return true;   			   
      }
      else
      {
		   TakeScreenShot(sText);
		   System.out.println("Verify Descending Order in Table failed ");
		   oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Descending Order in Table Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
		   if(nStoponError == 1)
	       {
	         oReports.EndTest();
	         System.exit(0);
	       }
		   return false; 
      }      		 
  }
  
  public static boolean VerifyIntegerAscendingInTable(ObjectCreator object, String sText, String ColumnIndex ,int nStoponError)
  {
	  List <WebElement> sRow = null;
	  
	  List<Integer> displayedNames = new ArrayList<Integer>();
      List<Integer> sortedNames = new ArrayList<Integer>();
      
	  Boolean bFound = false;
	  String Value;
	  try 
	  {
		  oLogin.placeComponents("VerifyIntegerAscendingInTable");
		  WaitForElementPresent(object);
		  elm = FindElement(object);
		  sRow = elm.findElements(By.xpath(".//tr"));
		  
		  for(int row = 1 ; row<=sRow.size()-1; row++)
		  {
			  Value = driver.findElement(By.xpath("//tbody/tr["+row+"]/td["+ColumnIndex+"]")).getText();
			  int sValue = Integer.parseInt(Value);
			  displayedNames.add(sValue);
			  sortedNames.add(sValue);
		  }
		 
		 Collections.sort(sortedNames); 
		 
		 System.out.println("Actual List "+displayedNames);
		 System.out.println("Sorted List "+sortedNames);
		  
		  if (!displayedNames.equals(sortedNames))
          {
              System.out.println("Not in Ascending Order...!!!");
              bFound=false;
          }
		  else
		  {
			  System.out.println("Values are in Ascending Order.");
			  bFound=true;
		  }
		 
	  }
	  catch(Exception e)
	  {
		TakeScreenShot(sText);
		oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Ascending Order in Table Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
	  }
      if(bFound)
      {
    	  if(bScreenonPass)
		   {
		   TakeScreenShot(sText);
		   oReports.ScreenshotLog(LogStatus.PASS, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Ascending Order in Table Passed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
		   }
	  else
		oReports.Log(LogStatus.PASS, "Verify Ascending Order in Table ", "Verify Ascending Order in Table Text <span class='label success'>Success</span>");
      return true;   			   
      }
      else
      {
		   TakeScreenShot(sText);
		   System.out.println("Verify Ascending Order in Table failed ");
		   oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Ascending Order in Table Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
		   if(nStoponError == 1)
	       {
	         oReports.EndTest();
	         System.exit(0);
	       }
		   return false; 
      }      		 
  }
  
  public static boolean VerifyIntegerDescendingInTable(ObjectCreator object, String sText, String ColumnIndex ,int nStoponError)
  {
	  List <WebElement> sRow = null;
	  
	  List<Integer> displayedNames = new ArrayList<Integer>();
      List<Integer> sortedNames = new ArrayList<Integer>();
      
	  Boolean bFound = false;
	  String Value;
	  try 
	  {
		  oLogin.placeComponents("VerifyIntegerDescendingInTable");
		  WaitForElementPresent(object);
		  elm = FindElement(object);
		  sRow = elm.findElements(By.xpath(".//tr"));
		  
		  for(int row = 1 ; row<=sRow.size()-1; row++)
		  {
			  Value = driver.findElement(By.xpath("//tbody/tr["+row+"]/td["+ColumnIndex+"]")).getText();
			  int sValue = Integer.parseInt(Value);
			  displayedNames.add(sValue);
			  sortedNames.add(sValue);
		  }
		 
		  Comparator<Integer> collections = Collections.reverseOrder();
		  Collections.sort(sortedNames, collections); 
		 
		 System.out.println("Actual List "+displayedNames);
		 System.out.println("Sorted List "+sortedNames);
		  
		  if (!displayedNames.equals(sortedNames))
          {
              System.out.println("Not in Descending Order...!!!");
              bFound=false;
          }
		  else
		  {
			  System.out.println("Values are in Descending Order.");
			  bFound=true;
		  }
		 
	  }
	  catch(Exception e)
	  {
		TakeScreenShot(sText);
		oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Descending Order in Table Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
	  }
      if(bFound)
      {
    	  if(bScreenonPass)
		   {
		   TakeScreenShot(sText);
		   oReports.ScreenshotLog(LogStatus.PASS, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Descending Order in Table Passed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
		   }
	  else
		oReports.Log(LogStatus.PASS, "Verify Descending Order in Table ", "Verify Descending Order in Table Text <span class='label success'>Success</span>");
      return true;   			   
      }
      else
      {
		   TakeScreenShot(sText);
		   System.out.println("Verify Descending Order in Table failed ");
		   oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Descending Order in Table Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
		   if(nStoponError == 1)
	       {
	         oReports.EndTest();
	         System.exit(0);
	       }
		   return false; 
      }      		 
  }
  
  public static boolean VerifyDateAscendingInTable(ObjectCreator object, String sText, String ColumnIndex ,int nStoponError)
  {
	  List <WebElement> sRow = null;
	  
	  List<String> displayedNames = new ArrayList<String>();
      List<String> sortedNames = new ArrayList<String>();
      SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
            
	  Boolean bFound = false;
	  String Value;
	  try 
	  {
		  oLogin.placeComponents("VerifyDateAscendingInTable");
		  WaitForElementPresent(object);
		  elm = FindElement(object);
		  sRow = elm.findElements(By.xpath(".//tr"));
		  
		  for(int row = 1 ; row<=sRow.size()-1; row++)
		  {
			  
			  Value = driver.findElement(By.xpath("//tbody/tr["+row+"]/td["+ColumnIndex+"]")).getText();
//			  Date utilDate = (Date) dateFormat.parse(Value);
			  
			  displayedNames.add(Value);
			  sortedNames.add(Value);
		  }
		 
		 Collections.sort(sortedNames); 
		 
		 System.out.println("Actual List "+displayedNames);
		 System.out.println("Sorted List "+sortedNames);
		  
		  if (!displayedNames.equals(sortedNames))
          {
              System.out.println("Not in Ascending Order...!!!");
              bFound=false;
          }
		  else
		  {
			  System.out.println("Values are in Ascending Order.");
			  bFound=true;
		  }
		 
	  }
	  catch(Exception e)
	  {
		TakeScreenShot(sText);
		oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Ascending Order in Table Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
	  }
      if(bFound)
      {
    	  if(bScreenonPass)
		   {
		   TakeScreenShot(sText);
		   oReports.ScreenshotLog(LogStatus.PASS, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Ascending Order in Table Passed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
		   }
	  else
		oReports.Log(LogStatus.PASS, "Verify Ascending Order in Table ", "Verify Ascending Order in Table Text <span class='label success'>Success</span>");
      return true;   			   
      }
      else
      {
		   TakeScreenShot(sText);
		   System.out.println("Verify Ascending Order in Table failed ");
		   oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Ascending Order in Table Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
		   if(nStoponError == 1)
	       {
	         oReports.EndTest();
	         System.exit(0);
	       }
		   return false; 
      }      		 
  }
  
  public static boolean VerifyDateDescendingInTable(ObjectCreator object, String sText, String ColumnIndex ,int nStoponError)
  {
	  List <WebElement> sRow = null;
	  
	  List<String> displayedNames = new ArrayList<String>();
      List<String> sortedNames = new ArrayList<String>();
      SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
      
	  Boolean bFound = false;
	  String Value;
	  try 
	  {
		  oLogin.placeComponents("VerifyDateDescendingInTable");
		  WaitForElementPresent(object);
		  elm = FindElement(object);
		  sRow = elm.findElements(By.xpath(".//tr"));
		  
		  for(int row = 1 ; row<=sRow.size()-1; row++)
		  {
			  Value = driver.findElement(By.xpath("//tbody/tr["+row+"]/td["+ColumnIndex+"]")).getText();
//			  Date utilDate = (Date) dateFormat.parse(Value);
			  displayedNames.add(Value);
			  sortedNames.add(Value);
		  }
		 
		  Comparator<String> collections = Collections.reverseOrder();
		  Collections.sort(sortedNames, collections); 
		 
		 System.out.println("Actual List "+displayedNames);
		 System.out.println("Sorted List "+sortedNames);
		  
		  if (!displayedNames.equals(sortedNames))
          {
              System.out.println("Not in Descending Order...!!!");
              bFound=false;
          }
		  else
		  {
			  System.out.println("Values are in Descending Order.");
			  bFound=true;
		  }
		 
	  }
	  catch(Exception e)
	  {
		TakeScreenShot(sText);
		oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Descending Order in Table Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
	  }
      if(bFound)
      {
    	  if(bScreenonPass)
		   {
		   TakeScreenShot(sText);
		   oReports.ScreenshotLog(LogStatus.PASS, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Descending Order in Table Passed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
		   }
	  else
		oReports.Log(LogStatus.PASS, "Verify Descending Order in Table ", "Verify Descending Order in Table Text <span class='label success'>Success</span>");
      return true;   			   
      }
      else
      {
		   TakeScreenShot(sText);
		   System.out.println("Verify Descending Order in Table failed ");
		   oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Descending Order in Table Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
		   if(nStoponError == 1)
	       {
	         oReports.EndTest();
	         System.exit(0);
	       }
		   return false; 
      }      		 
  }
  
  public static boolean VerifyTableHeaderValue(ObjectCreator object, String sText, String ColumnIndex ,int nStoponError)
  {
	  List <WebElement> sCol = null;
	  
	  List<String> displayedNames = new ArrayList<String>();
	  String[] vals = sText.split(";");
	  List<String> expectedVals = Arrays.asList(vals);
	    
	  Boolean bFound = false;
	  String Value;
	  try 
	  {
		  oLogin.placeComponents("VerifyTableHeaderValue");
		  WaitForElementPresent(object);
		  elm = FindElement(object);
		  sCol = elm.findElements(By.xpath(".//th"));
		  
		  for(int col = 1 ; col<=sCol.size(); col++)
		  {
			  Value = driver.findElement(By.xpath("//thead/tr/th["+col+"]")).getText();
			  displayedNames.add(Value);
		  }
		 System.out.println("Actual List "+displayedNames);
		 
		 if (!displayedNames.equals(expectedVals))
          {
              System.out.println("Expected Value is not same as Actual Value... Actual Value : "+displayedNames+ " Expected Value : "+expectedVals);
              bFound=false;
          }
		  else
		  {
			  System.out.println("Expected Value is equal to Actual Value... "+expectedVals);
			  bFound=true;
		  }
		 bFound = true;
	  }
	  catch(Exception e)
	  {
		TakeScreenShot(sText);
		oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verification Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
	  }
      if(bFound)
      {
    	  if(bScreenonPass)
		   {
		   TakeScreenShot(sText);
		   oReports.ScreenshotLog(LogStatus.PASS, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verification of Tabel header Passed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
		   }
	  else
		oReports.Log(LogStatus.PASS, "Verify Header in Table ", "Verification of Table Header<span class='label success'>Success</span>");
      return true;   			   
      }
      else
      {
		   TakeScreenShot(sText);
		   System.out.println("Verify Header in Table failed ");
		   oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Ascending Order in Table Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
		   if(nStoponError == 1)
	       {
	         oReports.EndTest();
	         System.exit(0);
	       }
		   return false; 
      }      		 
  }
  
  public static boolean VerifyNoOfRowsInTable(ObjectCreator object, String sText, int nStoponError)
  {
	  List <WebElement> sRow = null;
	  
//	  List<String> displayedNames = new ArrayList<String>();
//      List<String> sortedNames = new ArrayList<String>();

	  Boolean bFound = false;
	
	  try 
	  {
		  oLogin.placeComponents("VerifyNoOfRowsInTable");
		  WaitForElementPresent(object);
		  elm = FindElement(object);
		  sRow = elm.findElements(By.xpath(".//tr"));
		  System.out.println("Count : "+sRow.size());		  
	  }
	  catch(Exception e)
	  {
		TakeScreenShot(sText);
		oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Ascending Order in Table Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
	  }
      if(bFound)
      {
    	  if(bScreenonPass)
		   {
		   TakeScreenShot(sText);
		   oReports.ScreenshotLog(LogStatus.PASS, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Ascending Order in Table Passed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
		   }
	  else
		oReports.Log(LogStatus.PASS, "Verify Ascending Order in Table ", "Verify Ascending Order in Table Text <span class='label success'>Success</span>");
      return true;   			   
      }
      else
      {
		   TakeScreenShot(sText);
		   System.out.println("Verify Ascending Order in Table failed ");
		   oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Ascending Order in Table Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
		   if(nStoponError == 1)
	       {
	         oReports.EndTest();
	         System.exit(0);
	       }
		   return false; 
      }      		 
  }
  
  public static boolean ClickLinkInTable(ObjectCreator object, Integer RowIndex, Integer ColumnIndex ,int nStoponError)
  {
	  Boolean bFound = false;
	  String sText = "Click Link In Table";
	  try 
	  {
		  oLogin.placeComponents("VerifyDateAscendingInTable");
		  WaitForElementPresent(object);
		  elm = driver.findElement(By.xpath("//tbody/tr["+RowIndex+"]/td["+ColumnIndex+"]/a"));
		  elm.click();	 
		  bFound=true;
	  }
	  
	  catch(Exception e)
	  {
		TakeScreenShot(sText);
		oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Ascending Order in Table Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
	  }
      if(bFound)
      {
    	  if(bScreenonPass)
		   {
		   TakeScreenShot(sText);
		   oReports.ScreenshotLog(LogStatus.PASS, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Ascending Order in Table Passed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
		   }
	  else
		oReports.Log(LogStatus.PASS, "Verify Ascending Order in Table ", "Verify Ascending Order in Table Text <span class='label success'>Success</span>");
      return true;   			   
      }
      else
      {
		   TakeScreenShot(sText);
		   System.out.println("Verify Ascending Order in Table failed ");
		   oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Ascending Order in Table Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
		   if(nStoponError == 1)
	       {
	         oReports.EndTest();
	         System.exit(0);
	       }
		   return false; 
      }      		 
  }
 
  public static boolean CheckChkbox(ObjectCreator object)
  {
      try
      {
        WaitForElementPresent(object);
        elm = FindElement(object);
        String checked= elm.getAttribute("checked");
        if(!checked.equals(true))
        {            
         	elm.click();
         	bFound= true;
        }    
      }
      catch(Exception e)
      {               	
          	  bFound= false;
      }
		return bFound;      
  }
  
  public static boolean Check(ObjectCreator object)
  {
      try
      {
        WaitForElementPresent(object);
        elm = FindElement(object);
        
        if(!elm.isSelected())
        {            
         	elm.click();
         	bFound= true;
        }    
      }
      catch(Exception e)
      {               	
          	  bFound= false;
      }
		return bFound;      
  }
  
  public static boolean EnterTextInTable(ObjectCreator object,String sLnd, String sText, int nStoponError)
  {
//	  List <WebElement> sRow = null;
	  String[] vals = sText.split(";");
	  Boolean bFound = false;
	  try 
	  {
		  oLogin.placeComponents("EnterTextInTable");
		  WaitForElementPresent(object);
		  elm = FindElement(object);
		 
		  for(int row = 0 ; row<vals.length; row++)
		  {
			 elm= driver.findElement(By.xpath("//tr[@id='employee"+(row+1)+"']/td[5]/input"));
			 elm.sendKeys(vals[row].split(",")[0]);
			 elm= driver.findElement(By.xpath("//tr[@id='employee"+(row+1)+"']/td[7]/input[2]"));
			 elm.sendKeys(vals[row].split(",")[1]);
		  }
		  if(sLnd.equalsIgnoreCase("Check"))
		  {
			  elm= driver.findElement(By.id("blue_icon_right"));
			  elm.click();
			  for(int row = 0 ; row<vals.length; row++)
			  {
				 elm= driver.findElement(By.xpath("//tr[@id='employee"+(row+1)+"']/td[15]/select"));
				 SelectElement((ObjectCreator) elm, vals[row].split(",")[2]);
				 elm= driver.findElement(By.xpath("//tr[@id='employee"+(row+1)+"']/td[16]/input"));
				 elm.sendKeys(vals[row].split(",")[3]);
			  }
		  }
		  
		bFound=true;
	  }
	  catch(Exception e)
	  {
		TakeScreenShot(sText);
		oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Enter Text in Table Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
	  }
      if(bFound)
      {
    	  if(bScreenonPass)
		   {
		   TakeScreenShot(sText);
		   oReports.ScreenshotLog(LogStatus.PASS, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Enter Text in Table Passed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
		   }
	  else
		oReports.Log(LogStatus.PASS, "Entered Text in Table ", "Entered Text in Table Text <span class='label success'>Success</span>");
      return true;   			   
      }
      else
      {
		   TakeScreenShot(sText);
		   System.out.println("Enter Text in Table failed ");
		   oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Enter Text in Table Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
		   if(nStoponError == 1)
	       {
	         oReports.EndTest();
	         System.exit(0);
	       }
		   return false; 
      }      		 
  }
  
  
  public static boolean EnterTextInTableForLnD(ObjectCreator object, String sText, int nStoponError)
  {
//	  List <WebElement> sRow = null;
	  String[] vals = sText.split(";");
	  Boolean bFound = false;
	  try 
	  {
		  oLogin.placeComponents("EnterTextInTable");
		  WaitForElementPresent(object);
		  elm = FindElement(object);
		 
		  for(int row = 0 ; row<vals.length; row++)
		  {
			 elm= driver.findElement(By.xpath("//tr[@id='employee"+(row+1)+"']/td[15]/select"));
			 elm.sendKeys(vals[row].split(",")[0]);
			 elm= driver.findElement(By.xpath("//tr[@id='employee"+(row+1)+"']/td[16]/input"));
			 elm.sendKeys(vals[row].split(",")[1]);
		  }
		bFound=true;
	  }
	  catch(Exception e)
	  {
		TakeScreenShot(sText);
		oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Enter Text in Table Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
	  }
      if(bFound)
      {
    	  if(bScreenonPass)
		   {
		   TakeScreenShot(sText);
		   oReports.ScreenshotLog(LogStatus.PASS, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Enter Text in Table Passed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
		   }
	  else
		oReports.Log(LogStatus.PASS, "Entered Text in Table ", "Entered Text in Table Text <span class='label success'>Success</span>");
      return true;   			   
      }
      else
      {
		   TakeScreenShot(sText);
		   System.out.println("Enter Text in Table failed ");
		   oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Enter Text in Table Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Table Name</td><td style='border:solid black 1px;'>Text Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>"+object.GetObjectName()+"</td><td style='border:solid black 1px;'>"+sText+"</td></tr></table>",  Constants.sScreenshotFilepath+sText+".jpeg");
		   if(nStoponError == 1)
	       {
	         oReports.EndTest();
	         System.exit(0);
	       }
		   return false; 
      }      		 
  }
  public static int FindTableCol(ObjectCreator object, String sColName)
  {
	  try
	  {
	  oLogin.placeComponents("FindTableCol");
	  WaitForElementPresent(object);
	  elm= FindElement(object);
	  List<WebElement> sCol = elm.findElements(By.xpath(".//th"));
	  boolean f=false;
	  
	  for(int col = 1 ; col<=sCol.size(); col++)
	  {
		  String Value = driver.findElement(By.xpath("//thead/tr/th["+col+"]")).getText();
		  
		  if(Value.equals(sColName))
		  {
			  f=true;
			  return col;
		  
		  }
	 
	  }
	  if (!f)
	  {
		  System.out.println("Column not Found..!!! ");
		  oReports.EndTest();
	  }
		  
	}
	  catch(Exception e)
	  {
		  System.out.println("Error occured in FindTableCol");
	  }
	return -1;
  }
	public static boolean VerifyValue(ObjectCreator object, String sExpected, int nStoponError)
	{
		bFound = false;
		try
		{
			//Constants.oLogin.placeComponents("VerifyText");
			WaitForElementPresent(object);
			elm = FindElement(object);
			sExpected = sExpected.trim().toUpperCase();
			if(elm!=null)
			{
				sActual = elm.getAttribute("value");
				sActual = Utilities.FormatString(sActual).toUpperCase();
				if (sActual.contains(Utilities.FormatString(sExpected).trim()))
				{
					bFound = true;
				}
			}
		}
		catch(Exception e)
		{
			bFound=false;
			TakeScreenShot(object.GetObjectName());
			Constants.oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Value Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Actual</td><td style='border:solid black 1px;'>Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>" + sActual + "</td><td style='border:solid black 1px;'>" + sExpected + "</td></tr></table>", Constants.sScreenshotFilepath + object.GetObjectName() + ".jpeg");	
			if (nStoponError == 1)
			{
				Constants.oReports.EndTest();
				System.exit(0);
			}
		}	
		if (bFound)
		{
			if (Constants.bScreenonPass)
			{
				TakeScreenShot(object.GetObjectName());
				Constants.oReports.ScreenshotLog(LogStatus.PASS, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Value Passed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Actual</td><td style='border:solid black 1px;'>Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>" + sActual + "</td><td style='border:solid black 1px;'>" + sExpected + "</td></tr></table>", Constants.sScreenshotFilepath + object.GetObjectName() + ".jpeg");
			}
			else
				Constants.oReports.Log(LogStatus.PASS, "Verify Value", "Verify Value " + object.GetObjectName() + " <span class='label success'>Success</span>");
		}
		else
		{
			System.out.println("Verify value of " + sExpected + " failed");
			TakeScreenShot(object.GetObjectName());
			Constants.oReports.ScreenshotLog(LogStatus.FAIL, "<table style='border:solid black 1px;'><tr style='border:solid black 1px;'><td colspan='2'><b>Verify Value Failed</b></td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'> Actual</td><td style='border:solid black 1px;'>Expected</td></tr><tr style='border:solid black 1px;'><td style='border:solid black 1px;'>" + sActual + "</td><td style='border:solid black 1px;'>" + sExpected + "</td></tr></table>", Constants.sScreenshotFilepath + object.GetObjectName() + ".jpeg");
			if (nStoponError == 1)
			{
				Constants.oReports.EndTest();
				System.exit(0);
			}
		}
		return bFound;
	}
	
	public static WebElement injectJavascript(WebElement ee,String ss)
	{
		try
		{
		  ((JavascriptExecutor) driver).executeScript(ss, ee);		 
		}
		catch(Exception ee2)
		{
			System.out.println("Inject Error" + ee2);
		}
		return ee;
	}



	public static String extractPdf()  {
		// TODO Auto-generated method stub
		String output="";
//		try
//		{
////			for(String winHandle : driver.getWindowHandles()){
////			    driver.switchTo().window(winHandle);
////			}
//			
////		driver.get("http://www.vandevenbv.nl/dynamics/modules/SFIL0200/view.php?fil_Id=5515");		 
////		driver.switchTo().window("dashboard");
//		//Thread.sleep(5000);	
//		
////		driver.get("https://sit1.shop.anthem.com/sales/eox/secure/abcbs/nv/en/applyoff/");
////		driver.findElement(By.id("hypertext/apply/dashboard/quickapply/pdf")).click();
//		URL url = new URL("https://sit1.shop.anthem.com/sales/eox/secure/abcbs/nv/en/applyoff/dashboard?execution=e5s1&tabId=c041ef2b-04db-43a1-b55f-1db8075a1ec8&_eventId=openpdf&acn=C21458549788749&tabId=c041ef2b-04db-43a1-b55f-1db8075a1ec8");
//		BufferedInputStream fileToParse = new BufferedInputStream(url.openStream()); 
//		PDFParser parser = new PDFParser(fileToParse);
//		parser.parse();
//		 
//	    output = new PDFTextStripper().getText(parser.getPDDocument());
//	    System.out.println(output);
////		parser.getPDDocument().close();
//		
//		}
//		catch(Exception e )
//		{
//		System.out.println(e);	
//	    }
		
		try{
			for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
			}
		    String getURL = driver.getCurrentUrl();
		    Assert.assertTrue(verifyPDFContent(driver.getCurrentUrl(),"Nevada"));
		}
		catch(Exception e1)
		{
			System.out.println(e1);
		}
		
		return output;
	}
	
public static boolean verifyPDFContent(String strURL, String reqTextInPDF) {
		
		boolean flag = false;
		
		PDFTextStripper pdfStripper = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		String parsedText = null;

		try {
			URL url = new URL(strURL);
			BufferedInputStream file = new BufferedInputStream(url.openStream());
			PDFParser parser = new PDFParser(file);
			
			parser.parse();
			cosDoc = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(1);
			
			pdDoc = new PDDocument(cosDoc);
			parsedText = pdfStripper.getText(pdDoc);
		} catch (MalformedURLException e2) {
			System.err.println("URL string could not be parsed "+e2.getMessage());
		} catch (IOException e) {
			System.err.println("Unable to open PDF Parser. " + e.getMessage());
			try {
				if (cosDoc != null)
					cosDoc.close();
				if (pdDoc != null)
					pdDoc.close();
			} catch (Exception e1) {
				e.printStackTrace();
			}
		}
		
		System.out.println("+++++++++++++++++");
		System.out.println(parsedText);
		System.out.println("+++++++++++++++++");
		if(parsedText.contains(reqTextInPDF)) {
			flag=true;
		}
		
		return flag;
	}
public void testVerifyPDFTextInBrowser() {
		
		
		//Assert.assertTrue(verifyPDFContent(driver.getCurrentUrl(), "Prince Cascading"));
	}

public static void getApplication(ObjectCreator object, int nStoponError){
	try{
	oLogin.placeComponents("GetACN");
  	WaitForElementPresent(object);
  	elm =FindElement(object); 
  	if (elm != null)
  	{
  		String text=elm.getAttribute("textContent").trim().substring(85, 122).toUpperCase();
  		if(bScreenonPass)
  		{
  			//takeSpecificScreenshot(elm,UserActions.StringTrimmer(text));
  			oReports.Log(LogStatus.PASS, "ACN", "TEXT " + text + " <span class='label success'>success</span>");
  		}
  		
  	} 
  	
	}
    catch(Exception e)
    {
  	  //TakeScreenShot(object.GetObjectName());
  	  oReports.ScreenshotLog(LogStatus.FAIL, "Click  Object",  Constants.sScreenshotFilepath+object.GetObjectName()+".jpeg");
  	  if(nStoponError==1)
  	  {
  		  oReports.EndTest();
  		  System.exit(0);
  	  }  
     } 
  	
	
}

public static boolean getACN(ObjectCreator object, int nStoponError)
{
    try
    {
  	oLogin.placeComponents("GetACN");
  	WaitForElementPresent(object);
  	//elm =FindElement(object);
  	
  	List<WebElement> tableRows =UserActions.FindElements(object);
  	for(int i=0;i<tableRows.size();i++)
  	{
  	String xpath=object.GetSeleniumPropertyValue()+"["+i+"]"+"/td["+1+"]";
  	System.out.println(xpath);
  	framework.ObjectCreator acn=new framework.ObjectCreator("xpath",xpath , "acn");
  	elm =FindElement(acn);
  	if (elm != null)
  	{
  		String text=elm.getText().toUpperCase();
  		if(bScreenonPass)
  		{
  			//takeSpecificScreenshot(elm,UserActions.StringTrimmer(text));
  			oReports.Log(LogStatus.PASS, "ACN", "TEXT " + text + " <span class='label success'>success</span>");
  		}
  		
  	} 
  	}
    }
    catch(Exception e)
    {
  	  //TakeScreenShot(object.GetObjectName());
  	  oReports.ScreenshotLog(LogStatus.FAIL, "Click  Object",  Constants.sScreenshotFilepath+object.GetObjectName()+".jpeg");
  	  if(nStoponError==1)
  	  {
  		  oReports.EndTest();
  		  System.exit(0);
  	  }  
  	  return false;
     }      
    return true;	
 }
  
}
