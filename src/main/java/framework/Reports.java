package framework;
import org.apache.log4j.xml.DOMConfigurator;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class Reports 
{
	public  static   ExtentReports extent ;
	public  static   ExtentTest  Test ;
	
	//Starts a Test with the given Testcase Name
	public void StartTest (String sTestCaseName)
	{
		try 
			{
				DOMConfigurator.configure("Log4j.xml");
				extent = new ExtentReports(Constants.sReportFileName, false);
				 extent.config()
			    .documentTitle("EA Test Report")
			    .reportName("EA Regression")
			    .reportHeadline("");
				 Log.startTestCase(sTestCaseName);
			// optional
			extent
			    .addSystemInfo("Selenium Version", "2.53")
			    .addSystemInfo("Environment", "QA");
				 Test = extent.startTest(sTestCaseName);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		 
		
	}
	
	public void  Log(LogStatus oStatus,String sStepName,String Details)
	{
		Test.log(oStatus,sStepName,Details); 
		Log.info(oStatus+""+""+sStepName+""+Details);
		
		 
		
	}
	public void EndTest()
	{
		extent.endTest(Test);
		 Log.endTestCase("asdasd");
		 extent.flush();
	}
	public void ScreenshotLog(LogStatus oStatus,String sStepName,String sFilepath)
	{
		Test.log(oStatus,sStepName+ Test.addScreenCapture(sFilepath));
	}
}
