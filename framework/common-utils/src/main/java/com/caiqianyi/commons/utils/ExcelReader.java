package com.caiqianyi.commons.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ExcelReader {
	
	private Logger logger = LoggerFactory.getLogger(ExcelReader.class);
    private String filePath;
    private String sheetName;
    private Workbook workBook;    
    private Sheet sheet;
    private List<String> columnHeaderList;
    private List<List<String>> listData;
    private List<Map<String,String>> mapData;
    private boolean flag;
    
    public ExcelReader(String filePath) {
        this(filePath,null);
    } 

    public ExcelReader(String filePath, String sheetName) {
        this.filePath = filePath;
        this.sheetName = sheetName;
        this.flag = false;
        this.load();
    }    

    private String getCellValue(Cell cell) {
        String cellValue = "";
        DataFormatter formatter = new DataFormatter();
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                        cellValue = formatter.formatCellValue(cell);
                    } else {
                        double value = cell.getNumericCellValue();
                        int intValue = (int) value;
                        cellValue = value - intValue == 0 ? String.valueOf(intValue) : String.valueOf(value);
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    cellValue = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    cellValue = String.valueOf(cell.getCellFormula());
                    break;
                case Cell.CELL_TYPE_BLANK:
                    cellValue = "";
                    break;
                case Cell.CELL_TYPE_ERROR:
                    cellValue = "";
                    break;
                default:
                    cellValue = cell.toString().trim();
                    break;
            }
        }
        return cellValue.trim();
    }

    private void getSheetData() {
        listData = new ArrayList<List<String>>();
        mapData = new ArrayList<Map<String, String>>();    
        columnHeaderList = new ArrayList<String>();
        int numOfRows = sheet.getLastRowNum() + 1;
        for (int i = 0; i < numOfRows; i++) {
            Row row = sheet.getRow(i);
            Map<String, String> map = new HashMap<String, String>();
            List<String> list = new ArrayList<String>();
            if (row != null) {
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    
                    if (i == 0){
                        columnHeaderList.add(getCellValue(cell));
                    }
                    else{                        
                        map.put(columnHeaderList.get(j), this.getCellValue(cell));
                    }
                    list.add(this.getCellValue(cell));
                }
            }
            if (i > 0){
                mapData.add(map);
            }
            listData.add(list);
        }
        flag = true;
    }
    
    protected void load() {
        FileInputStream inStream = null;
        try {
            inStream = new FileInputStream(new File(filePath));
            workBook = WorkbookFactory.create(inStream);
            sheet = sheetName == null ? workBook.getSheetAt(0) : workBook.getSheet(sheetName);            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(inStream!=null){
                    inStream.close();
                }                
            } catch (IOException e) {                
                e.printStackTrace();
            }
        }
    }
    public Workbook getWorkBook() {
		return workBook;
	}

	public void setWorkBook(Workbook workBook) {
		this.workBook = workBook;
	}

	public Sheet getSheet() {
		return sheet;
	}

	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	public String getFilePath() {
		return filePath;
	}

	public String getSheetName() {
		return sheetName;
	}

	public boolean isFlag() {
		return flag;
	}

	public String getCellData(int row, int col){
        if(row>-1 && col>-1){
        	if(!flag){
                this.getSheetData();
            }
            int r = listData.size()>row ? row : -1;
            logger.info("===>r:{}",r);
            int maxCol = getMaxCol(r);
            if(r > -1 && maxCol>col){
                return listData.get(r).get(col);
            }
        }
        return null;
    }
    
    public int getMaxRow(){
    	if(!flag){
            this.getSheetData();
        }
    	return listData.size();
    }
    
    public int getMaxCol(int row){
    	if(!flag){
            this.getSheetData();
        }
    	logger.debug("row===>:{}",row);
    	int r = listData.size()>row ? row : listData.size() - 1;
    	return listData.get(r).size();
    }
    
    public String getCellData(int row, String headerName){
        if(row<=0){
            return null;
        }
        if(!flag){
            this.getSheetData();
        }
        int r = listData.size()>=row ? row-1 : -1;
        if(r > -1 && mapData.get(r).containsKey(headerName)){
            return mapData.get(r).get(headerName);
        }
        return null;
    }
    
    public boolean addColCell(List<String> cols){
    	int min = 0,max = this.getMaxRow(),maxCol = getMaxCol(min);
    	for (int i = min; i < max; i++) {
            addCell(cols.get(i), i, maxCol+1);
    	}
    	return true;
    }
    
    public boolean addCell(String value,int r,int c){
    	Row row = sheet.getRow(r);
        Cell cell = row.createCell(c);
        cell.setCellValue(value);
    	return true;
    }
    
    
    
    public void save(){
    	OutputStream os = null;
    	try {
			os = new FileOutputStream(filePath);
			this.getWorkBook().write(os);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
            try {
                if(os!=null){
                	os.close();
                }                
            } catch (IOException e) {                
                e.printStackTrace();
            }
        }
    }
}