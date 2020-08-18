package com.jf.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.jf.bean.ExcelBean;


/**
 * excel 工具类
 * 
 * @author yjc
 * 
 */
public class ExcelUtils {

	public static void export(ExcelBean excelBean,HttpServletResponse response) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(excelBean.getSheetName());
		HSSFRow row = sheet.createRow(0);

		// 设置样式
		HSSFCellStyle style = wb.createCellStyle();
		if (excelBean.isHeadBold()) {
			HSSFFont headfont = wb.createFont();
			headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style.setFont(headfont);
		}
		HSSFCell cell;
		String[] titles = excelBean.getTitles();
		for (int i = 0; i < titles.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(new HSSFRichTextString(titles[i]));
			cell.setCellStyle(style);
			sheet.setColumnWidth(i, excelBean.getColumnWidth());
		}

		HSSFDataFormat df = wb.createDataFormat();
		
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setWrapText(true);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setDataFormat(df.getBuiltinFormat("#,#0"));//数据格式只显示整数
		
		HSSFCellStyle hcs = wb.createCellStyle();
		hcs.setWrapText(true);
		hcs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		hcs.setDataFormat(df.getBuiltinFormat("#,##0.00"));//保留两位小数点
		
		HSSFCellStyle cs = wb.createCellStyle();
		HSSFDataFormat format = wb.createDataFormat();
		cs.setDataFormat(format.getFormat("@"));  
		cs.setWrapText(true);
		cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		int rowNumber = 1;
		for (String[] data : excelBean.getDataList()) {
			row = sheet.createRow(rowNumber++);
			for (int j = 0; j < data.length; j++) {
				cell = row.createCell(j);
				Boolean isNum = false;//data是否为数值型
                Boolean isInteger=false;//data是否为整数
                Boolean isPercent=false;//data是否为百分数
                if (data[j] != null || "".equals(data[j])) {
                    //判断data[j]是否为数值型
                    isNum = data[j].matches("^(-?\\d+)(\\.\\d+)?$");
                    //判断data[j]是否为整数（小数部分是否为0）
                    isInteger=data[j].matches("^[-\\+]?[\\d]*$");
                    //判断data[j]是否为百分数（是否包含“%”）
                    isPercent=data[j].contains("%");
                }
				//如果单元格内容是数值类型，涉及到金钱（金额、本、利），则设置cell的类型为数值型，设置data的类型为数值类型
                if (isNum && !isPercent) {
                    if (isInteger) {
                        cell.setCellStyle(cellStyle);
                    }else{
                        cell.setCellStyle(hcs);
                    }                   
                    // 设置单元格内容为double类型
                    cell.setCellValue(Double.parseDouble(data[j]));
                } else {
                	cell.setCellStyle(cs);
                    // 设置单元格内容为字符型
                	if(!StringUtils.isEmpty(data[j]) && data[j].startsWith("`")){//处理交易号等数字很长的字符串，使其导出时正常显示（即不会显示为科学计数法）
                		cell.setCellValue(data[j].substring(1));
                	}else{
                		cell.setCellValue(data[j]);
                	}
                }
			}
		}
		response.setContentType("application/octet-stream;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		String filename = excelBean.getName();
		filename = new String(filename.replaceAll("\\s|;", "").getBytes("utf-8"), "ISO8859-1");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + filename);
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	/**
	 * 读取xls\xlsx文件
	 * 
	 * @param file
	 * @param limitColumnNumber
	 * @param dealWithType 默认为0;
	 * 0.正常读取导入的excel数据，无需对导入的数据进行修改操作 1.支付宝,微信导入的excel，（如：支付平台收款明细导入）需要对日期数据列进行修改处理2.其他类型的特殊处理，需自定义处理方式以满足需求..
	 * @return
	 * @throws FileNotFoundException
	 * @throws Exception
	 *             List<ArrayList<String>>
	 */
	public static List<ArrayList<String>> read(File file, String fileName, int limitColumnNumber ,String dealWithType)
			throws FileNotFoundException, Exception {
		if (fileName.endsWith(".xls")) {
			return readXls(new FileInputStream(file), limitColumnNumber,dealWithType, 0);
		} else if (fileName.endsWith(".xlsx")) {
			return readXlsx(new FileInputStream(file), limitColumnNumber,dealWithType, 0);
		}
		return null;
	}
	
	public static List<ArrayList<String>> setCellRead(File file, String fileName, int limitColumnNumber ,String dealWithType, int startRow)
			throws FileNotFoundException, Exception {
		if (fileName.endsWith(".xls")) {
			return readXls(new FileInputStream(file), limitColumnNumber,dealWithType, startRow);
		} else if (fileName.endsWith(".xlsx")) {
			return readXlsx(new FileInputStream(file), limitColumnNumber,dealWithType, startRow);
		}
		return null;
	}
	
	public static List<ArrayList<String>> setCellRead(InputStream inputstream, String fileName, int limitColumnNumber ,String dealWithType, int startRow)
			throws FileNotFoundException, Exception {
		if (fileName.endsWith(".xls")) {
			return readXls(inputstream, limitColumnNumber,dealWithType, startRow);
		} else if (fileName.endsWith(".xlsx")) {
			return readXlsx(inputstream, limitColumnNumber,dealWithType, startRow);
		}
		return null;
	}

	/**
	 * xlsx文档
	 */
	private static List<ArrayList<String>> readXlsx(InputStream inputstream, int limitColumnNumber, String dealWithType, int startRow) throws Exception {
		List<ArrayList<String>> arrayLists = new ArrayList<ArrayList<String>>();
		XSSFWorkbook hssfworkbook = new XSSFWorkbook(inputstream);
		for (int a = 0; a < hssfworkbook.getNumberOfSheets(); a++) {
			XSSFSheet hssfsheet = hssfworkbook.getSheetAt(a); //工作表
			if (hssfsheet != null ) {
				int totalrows = hssfsheet.getPhysicalNumberOfRows(); //获取sheet总行数
				if (totalrows > 1 ) {
					for (int i = startRow; i < totalrows; i++ ) {
						XSSFRow hssfrow = hssfsheet.getRow(i);
						if(hssfrow != null ) {
							int cellNumbers = hssfrow.getPhysicalNumberOfCells();
							List<String> columnValues = new ArrayList<String>();
							for (int j = 0; j < limitColumnNumber; j++) {
								if (j + 1 <= cellNumbers) {
									Cell cell = hssfrow.getCell(j);
									if(dealWithType.equals("1") ) {
										cell = dealWithWeChat(cell);
									}
									if (cell != null ) {
										cell.setCellType(Cell.CELL_TYPE_STRING);
										columnValues.add(cell.getStringCellValue());
										continue;
									}
								}
								columnValues.add("");
							}
							arrayLists.add((ArrayList<String>) columnValues);
						}
					}
				}
			}
		}
		return arrayLists;
	}

	/**
	 * xls文档
	 */
	private static List<ArrayList<String>> readXls(InputStream inputstream, int limitColumnNumber, String dealWithType, int startRow) throws Exception {
		List<ArrayList<String>> arrayLists = new ArrayList<ArrayList<String>>();
		HSSFWorkbook hssfworkbook = new HSSFWorkbook(inputstream);
		for (int a = 0; a < hssfworkbook.getNumberOfSheets(); a++) {
			HSSFSheet hssfsheet = hssfworkbook.getSheetAt(a); //工作表
			if (hssfsheet != null) {
				int totalrows = hssfsheet.getPhysicalNumberOfRows(); //获取sheet总行数
				if (totalrows > 1) {
					for (int i = startRow; i < totalrows; i++) {
						HSSFRow hssfrow = hssfsheet.getRow(i);
						if(hssfrow != null ) {
							int cellNumbers = hssfrow.getPhysicalNumberOfCells();
							List<String> columnValues = new ArrayList<String>();
							for (int j = 0; j < limitColumnNumber; j++) {
								if (j + 1 <= cellNumbers) {
									Cell cell = hssfrow.getCell(j);
									if(dealWithType.equals("1") ) {
										cell = dealWithWeChat(cell);
									}
									if (cell != null) {
										cell.setCellType(Cell.CELL_TYPE_STRING);
										columnValues.add(cell.getStringCellValue());
										continue;
									}
								}
								columnValues.add("");
							}
							arrayLists.add((ArrayList<String>) columnValues);
						}
					}
				}
			}
		}
		return arrayLists;
	}
	
	
	/**
	 * 商务开发记录Excel文档
	 */
	public static List<ArrayList<String>> swread(File file, String fileName, int limitColumnNumber ,String dealWithType)
			throws FileNotFoundException, Exception {
		if (fileName.endsWith(".xls")) {
			return swreadXls(new FileInputStream(file), limitColumnNumber,dealWithType, 0);
		} else if (fileName.endsWith(".xlsx")) {
			return swreadXlsx(new FileInputStream(file), limitColumnNumber,dealWithType, 0);
		}
		return null;
	}
	
	/**
	 * 商务开发记录.xls文档
	 */
	private static List<ArrayList<String>> swreadXls(InputStream inputstream, int limitColumnNumber, String dealWithType, int startRow) throws Exception {
		List<ArrayList<String>> arrayLists = new ArrayList<ArrayList<String>>();
		HSSFWorkbook hssfworkbook = new HSSFWorkbook(inputstream);
		for (int a = 0; a < hssfworkbook.getNumberOfSheets(); a++) {
			HSSFSheet hssfsheet = hssfworkbook.getSheetAt(a); //工作表
			if (hssfsheet != null) {
				int totalrows = hssfsheet.getPhysicalNumberOfRows(); //获取表单总行数
				if (totalrows > 1) {
					for (int i = startRow; i < totalrows; i++) {
						HSSFRow hssfrow = hssfsheet.getRow(i);
						if(hssfrow != null ) {
							int cellNumbers = hssfrow.getPhysicalNumberOfCells();
							List<String> columnValues = new ArrayList<String>();
							for (int j = 0; j < limitColumnNumber; j++) {
								if (j <= cellNumbers) {
									Cell cell = hssfrow.getCell(j);
									if(dealWithType.equals("1") ) {
										cell = dealWithWeChat(cell);
									}
									if (cell != null) {
										cell.setCellType(Cell.CELL_TYPE_STRING);
										columnValues.add(cell.getStringCellValue());
										continue;
									}
								}
								columnValues.add("");
							}
							arrayLists.add((ArrayList<String>) columnValues);
						}
					}
				}
			}
		}
		return arrayLists;
	}
	
	
	/**
	 * 商务开发记录.xlsx文档
	 */
	private static List<ArrayList<String>> swreadXlsx(InputStream inputstream, int limitColumnNumber, String dealWithType, int startRow) throws Exception {
		List<ArrayList<String>> arrayLists = new ArrayList<ArrayList<String>>();
		XSSFWorkbook hssfworkbook = new XSSFWorkbook(inputstream);
		for (int a = 0; a < hssfworkbook.getNumberOfSheets(); a++) {
			XSSFSheet hssfsheet = hssfworkbook.getSheetAt(a); //工作表
			if (hssfsheet != null ) {
				int totalrows = hssfsheet.getPhysicalNumberOfRows(); //获取表单总行数
				if (totalrows > 1 ) {
					for (int i = startRow; i < totalrows; i++ ) {
						XSSFRow hssfrow = hssfsheet.getRow(i);
						if(hssfrow != null ) {
							int cellNumbers = hssfrow.getPhysicalNumberOfCells();
							List<String> columnValues = new ArrayList<String>();
							for (int j = 0; j < limitColumnNumber; j++) {
								if (j <= cellNumbers) {
									Cell cell = hssfrow.getCell(j);
									if(dealWithType.equals("1") ) {
										cell = dealWithWeChat(cell);
									}
									if (cell != null ) {
										cell.setCellType(Cell.CELL_TYPE_STRING);
										columnValues.add(cell.getStringCellValue());
										continue;
									}
								}
								columnValues.add("");
							}
							arrayLists.add((ArrayList<String>) columnValues);
						}
					}
				}
			}
		}
		return arrayLists;
	}
	
	
	
	/**
	 * 针对微信Excel导入的表格所做的特殊处理。
	 */
	private static Cell dealWithWeChat(Cell cell) {
		if(cell!=null){
			try {
				String[] array = cell.toString().split("-");
				if(array[1].equals("十二月")){
					cell.setCellValue(array[2]+"-12-"+array[0]);
				}else if(array[1].equals("十一月")){
					cell.setCellValue(array[2]+"-11-"+array[0]);
				}else if(array[1].equals("十月")){
					cell.setCellValue(array[2]+"-10-"+array[0]);
				}else if(array[1].equals("九月")){
					cell.setCellValue(array[2]+"-09-"+array[0]);
				}else if(array[1].equals("八月")){
					cell.setCellValue(array[2]+"-08-"+array[0]);
				}else if(array[1].equals("七月")){
					cell.setCellValue(array[2]+"-07-"+array[0]);
				}else if(array[1].equals("六月")){
					cell.setCellValue(array[2]+"-06-"+array[0]);
				}else if(array[1].equals("五月")){
					cell.setCellValue(array[2]+"-05-"+array[0]);
				}else if(array[1].equals("四月")){
					cell.setCellValue(array[2]+"-04-"+array[0]);
				}else if(array[1].equals("三月")){
					cell.setCellValue(array[2]+"-03-"+array[0]);
				}else if(array[1].equals("二月")){
					cell.setCellValue(array[2]+"-02-"+array[0]);
				}else if(array[1].equals("一月")){
					cell.setCellValue(array[2]+"-01-"+array[0]);
				}else{
					String[] arr = cell.toString().split(" ");
					cell.setCellValue(arr[0]);
				}
			} catch (Exception e) {
				return cell;
			}
		}
		return cell;
	}
}
