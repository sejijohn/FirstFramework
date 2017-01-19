package framework;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Constants 
{
	  public static final String sBrowser = "Mozilla";
	  public static WebElement elm;
	  public static WebDriver driver;
	  public static Boolean   bStatus=false;
      public static	Boolean bFound;
      public static Reports oReports = new Reports();
      public static int nSeconds =5;
      static Date date = new Date() ;
  	  static SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH-mm-ss");
  	  static SimpleDateFormat   todaysDate = new SimpleDateFormat("MM-dd-yyyy");
  	  public static String sFilePath = System.getProperty("user.dir");
  	  public static String sReportPath=sFilePath+"\\Result_Log";
  	  public static String sReportFileName = sReportPath+"\\"+"IOLSTestReport_"+dateFormat.format(date)+".html";
      public static String sDriverPath=sFilePath+"\\"+"Drivers";
      public static String sScreenshotFilepath=sFilePath+"\\Screenshots\\"+"IOLS_Screenshot_"+todaysDate.format(date)+"\\";
      public static	String sClassPath =sFilePath+"\\src\\Framework\\";
      public static PlayBackView oLogin = new PlayBackView();
  	  public static Boolean bScreenonPass = true;
 
}
