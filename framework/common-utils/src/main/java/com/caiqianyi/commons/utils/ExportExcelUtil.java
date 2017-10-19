package com.caiqianyi.commons.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * 利用开源组件POI 动态导出EXCEL
 * 
 * @author tanhb
 * @version v1.0
 * @param <T> 应用泛型，javabean风格的类
 */
public class ExportExcelUtil<T> {

	/**
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 * 
	 * @param title
	 *            表格标题名
	 * @param headers
	 *            [表格属性列名]数组
	 * @param dataset 需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
	 *            javabean属性的数据类型有基本数据类型及String,Date
	 * @param out
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @param pattern
	 *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
	 * @param sheetSize
	 *            sheet单页条数，小大于100的值都用默认值：1000
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	public void exportExcel(String title, String[] headers, List<T> olddataset, OutputStream out, String pattern, int sheetSize) {
		if(sheetSize < 100){
			sheetSize = 1000;
		}
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		int dsize = olddataset.size() / sheetSize;
		if((olddataset.size() % sheetSize) > 0){
			dsize += 1;
		}
		
		//设置样式
				// 生成一个样式
				HSSFCellStyle style = workbook.createCellStyle();
				// 设置标题样式
				//居中
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				//背景色
				style.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
				style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				//设置边框
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);
				// 生成一个字体
				HSSFFont font = workbook.createFont();
				font.setColor(HSSFColor.BLACK.index);
				font.setFontHeightInPoints((short) 12);
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				// 把字体应用到当前的样式
				style.setFont(font);
				// 生成并设置另一个样式
				HSSFCellStyle style2 = workbook.createCellStyle();
				style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
				style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
				
				style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
				style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		//设置样式
		
		for (int ds = 0; ds < dsize; ds++) {
			int end = (ds+1) * sheetSize;
			if(end > olddataset.size()){
				end = olddataset.size();
			}
			List<T> dataset = olddataset.subList(ds * sheetSize, end);
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet();
			workbook.setSheetName(ds, title+ds);
			// 设置表格默认列宽度为18个字节
			sheet.setDefaultColumnWidth((short) 18);
			// 产生表格标题行
			HSSFRow row = sheet.createRow(0);
			for (short i = 0; i < headers.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style);
				HSSFRichTextString text = new HSSFRichTextString(headers[i]);
				cell.setCellValue(text);
			}
	
			// 遍历集合数据，产生数据行
			Iterator<T> it = dataset.iterator();
			int index = 0;
			while (it.hasNext()) {
				index++;
				row = sheet.createRow(index);
				T t = (T) it.next();
				if(t instanceof Map) {
					
				}
				// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
				Field[] fields = t.getClass().getDeclaredFields();
				for (short i = 0; i < fields.length; i++) {
					HSSFCell cell = row.createCell(i);
					cell.setCellStyle(style2);
					Field field = fields[i];
					String fieldName = field.getName();
					String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					try {
						Class tCls = t.getClass();
						Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
						Object value = getMethod.invoke(t, new Object[] {});
						
						// 判断值的类型后进行强制类型转换
						String textValue = null;
						if(value instanceof Boolean) {
							boolean bValue = (Boolean) value;
							textValue = "X";
							if (bValue) {
								textValue = "√";
							}
						}else if (value instanceof Date) {
							Date date = (Date) value;
							SimpleDateFormat sdf = new SimpleDateFormat(pattern);
							textValue = sdf.format(date);
						}else {// 其它数据类型都当作字符串简单处理
							textValue = value + "";
							if(fieldName.indexOf("time") > -1 && textValue.length() == 10){
								textValue = DateUtil.stampToDate(textValue);
							}
						}
						// 利用正则表达式判断textValue是否全部由数字组成
						if(textValue != null) {
							Pattern p = Pattern.compile("^//d+(//.//d+)?$");
							Matcher matcher = p.matcher(textValue);
							if(matcher.matches()) {// 是数字当作double处理
								cell.setCellValue(Double.parseDouble(textValue));
							}else {
								HSSFRichTextString richString = new HSSFRichTextString(textValue);
								cell.setCellValue(richString);
							}
						}
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} finally {
						// 清理资源
					}
				}
			}
		}
		
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 * 
	 * @param title
	 *            表格标题名
	 * @param headermaps
	 *            [表格属性列名][表格属性字段名/KEY]数组
	 * @param dataset 需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
	 *            javabean属性的数据类型有基本数据类型及String,Date
	 * @param out
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @param pattern
	 *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
	 * @param sheetSize
	 *            sheet单页条数，小大于100的值都用默认值：1000
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	public void exportExcelPlus(String title, String[][] headermaps, List<T> olddataset, OutputStream out, String pattern, int sheetSize) {
		if(sheetSize < 100){
			sheetSize = 1000;
		}
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		int dsize = olddataset.size() / sheetSize;
		if((olddataset.size() % sheetSize) > 0){
			dsize += 1;
		}
		
		//设置样式
				// 生成一个样式
				HSSFCellStyle style = workbook.createCellStyle();
				// 设置标题样式
				//居中
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				//背景色
				style.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
				style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				//设置边框
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);
				// 生成一个字体
				HSSFFont font = workbook.createFont();
				font.setColor(HSSFColor.BLACK.index);
				font.setFontHeightInPoints((short) 12);
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				// 把字体应用到当前的样式
				style.setFont(font);
				// 生成并设置另一个样式
				HSSFCellStyle style2 = workbook.createCellStyle();
				style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
				style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
				
				style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
				style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		//设置样式
		
		for (int ds = 0; ds < dsize; ds++) {
			int end = (ds+1) * sheetSize;
			if(end > olddataset.size()){
				end = olddataset.size();
			}
			List<T> dataset = olddataset.subList(ds * sheetSize, end);
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet();
			workbook.setSheetName(ds, title+ds);
			// 设置表格默认列宽度为18个字节
			sheet.setDefaultColumnWidth((short) 18);
			// 产生表格标题行
			HSSFRow row = sheet.createRow(0);
			for (short i = 0; i < headermaps[0].length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style);
				HSSFRichTextString text = new HSSFRichTextString(headermaps[0][i]);
				cell.setCellValue(text);
			}
	
			// 遍历集合数据，产生数据行
			for(int index=0; index < dataset.size(); index++) {
				T t = dataset.get(index);
				row = sheet.createRow(index+1);
				if(t instanceof Map) {
					Map tt = (Map)t;
					for(int i=0; i < headermaps[1].length; i++){
						HSSFCell cell = row.createCell(i);
						cell.setCellStyle(style2);
						
						String fieldName = headermaps[1][i];
						Object value = tt.get(fieldName);
						// 判断值的类型后进行强制类型转换
						String textValue = value + "";
						if(fieldName.indexOf("time") > -1 && textValue.length() == 10){
							textValue = DateUtil.stampToDate(textValue);
						}
						// 利用正则表达式判断textValue是否全部由数字组成
						if(textValue != null) {
							Pattern p = Pattern.compile("^//d+(//.//d+)?$");
							Matcher matcher = p.matcher(textValue);
							if(matcher.matches()) {// 是数字当作double处理
								cell.setCellValue(Double.parseDouble(textValue));
							}else {
								HSSFRichTextString richString = new HSSFRichTextString(textValue);
								cell.setCellValue(richString);
							}
						}
					}
				}else{ //只支持javabean
					for(int i=0; i < headermaps[1].length; i++){
						HSSFCell cell = row.createCell(i);
						cell.setCellStyle(style2);
						
						String fieldName = headermaps[1][i];
						String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
						Class tCls = t.getClass();
						Method getMethod;
						try {
							getMethod = tCls.getMethod(getMethodName, new Class[] {});
							Object value = getMethod.invoke(t, new Object[] {});
							// 判断值的类型后进行强制类型转换
							String textValue = value + "";
							if(fieldName.toLowerCase().indexOf("time") > -1 && textValue.length() == 10){
								textValue = DateUtil.stampToDate(textValue);
							}
							// 利用正则表达式判断textValue是否全部由数字组成
							if(textValue != null) {
								Pattern p = Pattern.compile("^//d+(//.//d+)?$");
								Matcher matcher = p.matcher(textValue);
								if(matcher.matches()) {// 是数字当作double处理
									cell.setCellValue(Double.parseDouble(textValue));
								}else {
									HSSFRichTextString richString = new HSSFRichTextString(textValue);
									cell.setCellValue(richString);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				
			}
			
		}
		
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
