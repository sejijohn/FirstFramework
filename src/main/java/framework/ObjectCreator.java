package framework;

public class ObjectCreator 
{


	private String sSeleniumProperty = null;
	private String sSeleniumPropertyValue = null;
	private String sObjectName = null;
	public ObjectCreator(String sProperty, String sPropertyValue, String sName)
	{
		sSeleniumProperty = sProperty;
		sSeleniumPropertyValue = sPropertyValue;
		sObjectName = sName;
	}
	public String GetSeleniumProperty()
	{
		return sSeleniumProperty;
	}
	public String GetSeleniumPropertyValue()
	{
		return sSeleniumPropertyValue;
	}
	public String GetObjectName()
	{
		return sObjectName;
	}
}
