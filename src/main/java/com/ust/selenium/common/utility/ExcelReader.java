package com.ust.selenium.common.utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.sun.media.sound.InvalidFormatException;

public class ExcelReader {

    private final List<Map<String, Object>> data;
    
    private List<String> columns;

    public ExcelReader(String filePath, String spreadsheetName) throws IOException {
        data = read(Res.get(filePath), spreadsheetName);
    }

    /**
     * Gets the data from the excel sheet in List<Hashtable<String,String>>
     * format
     *
     * @return
     */
    public List<Map<String, Object>> data() {
        return data;
    }
    
    public Object[][] convertedData() {
    	System.out.println("Calling convert.");
    	Object[][] convert = new Object[data.size()][columns.size()];
		for (int i = 0; i < data.size(); i++){
			Object[] item = new Object[columns.size()];
			Map<String, Object> subList = data.get(i);
			for (int j = 0; j < columns.size(); j++) {
				item[j] = subList.get(columns.get(j));
			}
			convert[i] = item;
		}
		
		return convert;
    }

    /**
     * Get all values associated with the specified column throughout the entire
     * sheet
     *
     * @param column
     * @return values
     */
    public List<Object> getValues(String column) {
        List<Object> columnValues = new ArrayList<Object>();
        for (Map<String, Object> ht : data()) {
            columnValues.add(ht.get(column));
        }
        return columnValues;
    }
    
    private String getHeader(Sheet sheet, int col) {
		return sheet.getRow(0).getCell(col).getStringCellValue();
	}
    
    public ArrayList<Map<String, Object>> read(URL sheetUrl, String sheetName) {

		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		try {
			HSSFSheet sheet = null;
	    	HSSFWorkbook workbook = new HSSFWorkbook(sheetUrl.openStream());
	        sheet = workbook.getSheet(sheetName);

			boolean firstRow = true;

			for (Row row : sheet) {
				if (firstRow) {
					firstRow = false;
					continue;
				}
				boolean allAreNull = true;
				HashMap<String, Object> items = new HashMap<String, Object>();
				for (Cell cell : row) {
					int colIndex = cell.getColumnIndex();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						allAreNull = false;
						if (cell.getStringCellValue().equals("BLANK")) {
							items.put(getHeader(sheet, colIndex), null);
						} else {
							items.put(getHeader(sheet, colIndex),
									cell.getStringCellValue());
						}
						break;
					case Cell.CELL_TYPE_NUMERIC:
						allAreNull = false;
						if (DateUtil.isCellDateFormatted(cell)) {
							items.put(getHeader(sheet, colIndex),
									cell.getDateCellValue());
						} else {
							double val = cell.getNumericCellValue();
							if (val == (int) val) {
								// val = (int)val;
								items.put(getHeader(sheet, colIndex), (int) val);
							} else {
								items.put(getHeader(sheet, colIndex), val);
							// items.add(val + "");
							}
						}
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						allAreNull = false;
						items.put(getHeader(sheet, colIndex),
								cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_FORMULA:
						allAreNull = false;
						items.put(getHeader(sheet, colIndex),
								cell.getCellFormula());
						break;
					case Cell.CELL_TYPE_BLANK:
						items.put(getHeader(sheet, colIndex),
								cell.getStringCellValue());
						break;
					default:
						allAreNull = false;
						items.put(getHeader(sheet, colIndex),
								cell.getStringCellValue());
					}

				}

				if (allAreNull) {
					break;
				}

				list.add(items);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}
}
