package framework;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcel 
{  
	public static String sObjectPath ="Objects_final.xlsx";
	String[] sObject= new String[2];
	public static FileInputStream FS ;
    public static XSSFWorkbook WB; 
	public static String[][] sObjectList;
	public static String[][] sTestDataList;
	public static String[] TestData( String SheetName, String sFilepath ,String TestDataName) throws IOException 
	{
		Boolean bFlag = false;
		FileInputStream FS = new FileInputStream(sFilepath);
	    XSSFWorkbook WB = new XSSFWorkbook(FS);
	    XSSFSheet WS = WB.getSheet(SheetName);
	    int Row = WS.getLastRowNum()+1;
	    int Column = WS.getRow(0).getLastCellNum()+1;
	    String[] sObject =new String[1000];
	    int count=0;
	    for(int i=1;i<Row&&bFlag==false;i++)
	    {	
	    	
        XSSFRow RowName = WS.getRow(i);
        if(RowName==null)
        {  }
        else 
        {    	
	    	//for(int j=2;j<Column&&bFlag==false;j++)
        	for(int j=0;j<Column&&bFlag==false;j++)
	        {   
		     //if(RowName.getCell(1)!=null)
        		if(RowName.getCell(0)!=null) 
		     {
		    	 //XSSFCell Cellvalue=RowName.getCell(1); 
        			
        			XSSFCell Cellvalue=RowName.getCell(0); 
        			//XSSFCell Cellexecute=RowName.getCell(25);
		    	 if (Cellvalue == null || Cellvalue.getCellType() == Cell.CELL_TYPE_BLANK) 
		    	 { }
		    	 else 
	             {
	            	 if(Cellvalue.toString().equals(TestDataName))
	                 {
	                     XSSFCell script = RowName.getCell(j);
	                     if (script == null || script.getCellType() == Cell.CELL_TYPE_BLANK) 
	                     {
	                    	 sObject[count]= "";
		                             count++;                                                 
		                      }
		                      else 
		                      {
	                        	 System.out.print(script);
	                             sObject[count]= script.toString();
	                             count++;                                                  
		                       }                                          
		                  }
		   	              else 
		   	            	 break; 
	                    } 
	 
	             }//else                         
	        }//for        
         }//else
	   }    
	   return sObject;
	}
}


