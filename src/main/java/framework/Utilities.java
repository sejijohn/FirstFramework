package framework;
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.ParsePosition;

import com.relevantcodes.extentreports.LogStatus;


public class Utilities 
{
	public static void OpenLogFile(String spath) throws IOException
	{
		File htmlFile = new File(spath);
		Desktop.getDesktop().browse(htmlFile.toURI());
	}
	public static void Scheduler(String sTime) throws InterruptedException
	{

		
		//String Str[]=sTime.split(":");
//		int Hour=Integer.parseInt(Str[0].trim());
//		int Min=Integer.parseInt(Str[1].trim());
		Date date=new Date();
		Calendar calendar = Calendar.getInstance();
		date = calendar.getTime();
		System.out.println(date);
		Date date2 = new Date(calendar.getTimeInMillis());
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
				// TODO Auto-generated catch block
				e.printStackTrace();
				    
		}
	}
}
	
	public static void createUserDir(String sFilename) throws IOException 
	{
		
		  File file=new File(sFilename);
	  	  try 
	  	  {
	  		  
		  	  if(!file.exists())
		  	  {
		  		file.mkdirs();
		  	  }
		  	  else 
		  	  {
		  		  System.out.println("Folder already exists");
		  	  }
	  	  }
		  catch(Exception e)
		  {
		  		  
		  }
		}
	  public static String FormatString(String sValue)
			{
				if (sValue.contains("\n"))
				{
					sValue = sValue.replace("\n", "").trim();
				}
				sValue.replaceAll(" ", "");
				return sValue;
			}
	  public static String GetCoverageStartDateNQE(int days)
	  {		
		  DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");	
		  Date covgDate = null;
		  Date date=getCurrentSystemDate();
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  cal.add(Calendar.DATE, days);
		  Date date2=cal.getTime();
		  String strdate2= dateFormat.format(date);
		  System.out.println(strdate2);		
	      cal.setTime(date2);
	      cal.add(cal.MONTH,1);
	      cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
	      covgDate=cal.getTime();
		  // Print the Date	  
		  String covgDateStr=dateFormat.format(covgDate);
		  System.out.println(covgDateStr);
		  return covgDateStr;		  
	  }	
	  public static String GetCoverageStartDateQE(int months)
	  {		
		  DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");	
		  Date covgDate = null;
		  Date date=getCurrentSystemDate();
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);	
	      cal.add(cal.MONTH,months);
	      cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
	      covgDate=cal.getTime();
		  String covgDateStr=dateFormat.format(covgDate);
		  System.out.println(covgDateStr);
		  return covgDateStr;		  
	  }	
	  public static Date getCurrentSystemDate()
	  {		  
		  Date date = new Date();
		  return date;
	  }	
	  public static int getYear(String date) throws ParseException
	  {		  
		  DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");	
		  Date date1=dateFormat.parse(date);
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date1);
		  int year=cal.YEAR;
		  return year;
	  }	
	  
	  public static void ClickObject(ObjectCreator object)
      {
             UserActions.bFound = false;
             try
             {
                   Constants.oLogin.placeComponents("ClickObject");
                   UserActions.WaitForElementPresent(object);
                   UserActions.elm = UserActions.FindElement(object);
                   if (UserActions.elm != null && UserActions.elm.isEnabled())
                   {
                          UserActions.elm.click();
                          UserActions.bFound = true;
                   }
             }
             catch (Exception e)
             {
                   UserActions.bFound = false;
             }
             if (UserActions.bFound == true)
             {
                   if (Constants.bScreenonPass)
                   {
                          //TakeScreenShot(object.GetObjectName());
                          Constants.oReports.Log(LogStatus.PASS, "Click", "Clicked " + object.GetObjectName() + " <span class='label success'>success</span>");
                   }
                   else
                   {
                          Constants.oReports.Log(LogStatus.PASS, "Click", "Clicked " + object.GetObjectName() + " <span class='label success'>success</span>");
                   }
             }
             else
             {
                   //TakeScreenShot(object.GetObjectName());
                   Constants.oReports.Log(LogStatus.FAIL, "Click  Object", "<span class='label success'>Fail</span>");
             }
      }

//newly added code --AC98587
	  
	  public static String firstdaynextmonth(String systemdate) throws ParseException
		{
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(systemdate));
			cal.add(Calendar.MONTH, 1);
			cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
			return sdf.format(cal.getTime());
		}
		
		public static String halfmonth(String systemdate) throws ParseException
		{
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(systemdate));
			int days=cal.get(Calendar.DAY_OF_MONTH);
			//System.out.println("date from excel"+systemdate);
			if(days>0&&days<=15)
			{
				cal.add(Calendar.MONTH, 1);
				cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
				//System.out.println("0-15 loop"+sdf.format(cal.getTime()));
				return sdf.format(cal.getTime());
				
			}
			
			else
			{
				cal.add(Calendar.MONTH, 2);
				cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
				//System.out.println(">15 loop"+sdf.format(cal.getTime()));
				return sdf.format(cal.getTime());
				
			}
			
				
		}
		
		public static String firstdaynextmonthforlqedate(String systemdate) throws ParseException
		{
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(systemdate));
			cal.add(Calendar.MONTH, 1);
			cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
			return sdf.format(cal.getTime());
		}
		
		public static String nvninetydayrule(String systemdate) throws ParseException
		{
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(systemdate));
			//cal.add(Calendar.MONTH, 4);
			//fix 0401
			cal.add(Calendar.DAY_OF_MONTH, 90);
			cal.add(Calendar.MONTH, 1);
			//fix end
			cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
			return sdf.format(cal.getTime());
		}
		
		public static String nypregrule(String systemdate) throws ParseException
		{
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(systemdate));
			cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
			return sdf.format(cal.getTime());
		}
		
		public static String nypregnextmonthrule(String systemdate) throws ParseException
		{
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(systemdate));
			cal.add(Calendar.MONTH, 1);
			cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
			return sdf.format(cal.getTime());
		}
		
		public static String nydatedifference(String systemdate, String lqedate) throws ParseException
		{
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Calendar sys = Calendar.getInstance();
			Calendar lqe= Calendar.getInstance();
			
			sys.setTime(sdf.parse(systemdate));
			lqe.setTime(sdf.parse(lqedate));
			
			long difference=sys.getTimeInMillis()-lqe.getTimeInMillis();
			int days=(int) (difference/(24*60*60*1000));
			
			if(days<=60)
			{
				return sdf.format(lqe.getTime());
			}
			else
			return sdf.format(sys.getTime());
		}
		
		public static String halfmonth2017(String systemdate) throws ParseException
		{
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(systemdate));
			int days=cal.get(Calendar.DAY_OF_MONTH);
			//System.out.println("date from excel"+systemdate);
			Date enrollDate = sdf.parse("01/01/2017");
			if(days>0&&days<=15)
			{
				cal.add(Calendar.MONTH, 1);
				cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
				//System.out.println("0-15 loop"+sdf.format(cal.getTime()));
				Date effectDate = sdf.parse(sdf.format(cal.getTime()));
				if(effectDate.before(enrollDate)){
					return sdf.format(enrollDate);
				}
				else
					
				return sdf.format(cal.getTime());
				
			}
			
			else
			{
				cal.add(Calendar.MONTH, 2);
				cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
				//System.out.println(">15 loop"+sdf.format(cal.getTime()));
				Date effectDate = sdf.parse(sdf.format(cal.getTime()));
				if(effectDate.before(enrollDate)){
					return sdf.format(enrollDate);
				}
				else
				return sdf.format(cal.getTime());
				
			}
			
				
		}
}
