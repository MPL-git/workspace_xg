package com.jf.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

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
                	if(!StringUtil.isEmpty(data[j]) && data[j].startsWith("`")){//处理交易号等数字很长的字符串，使其导出时正常显示（即不会显示为科学计数法）
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
		filename = new String(filename.replaceAll("\\s|;", "").getBytes("gbk"), "ISO8859-1");
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
	 * @return
	 * @throws FileNotFoundException
	 * @throws Exception
	 *             List<ArrayList<String>>
	 */
	public static List<ArrayList<String>> read(File file, String fileName, int limitColumnNumber)
			throws FileNotFoundException, Exception {
		if (fileName.endsWith(".xls")) {
			return readXls(new FileInputStream(file), limitColumnNumber);
		} else if (fileName.endsWith(".xlsx")) {
			return readXlsx(new FileInputStream(file), limitColumnNumber);
		}
		return null;
	}

	/**
	 * xlsx文档
	 */
	private static List<ArrayList<String>> readXlsx(InputStream inputstream, int limitColumnNumber) throws Exception {
		List<ArrayList<String>> arrayLists = new ArrayList<ArrayList<String>>();
		XSSFWorkbook hssfworkbook = new XSSFWorkbook(inputstream);
		XSSFSheet hssfsheet = hssfworkbook.getSheetAt(0);// 第一个工作表
		if (hssfsheet != null) {
			int totalrows = hssfsheet.getPhysicalNumberOfRows();// --获取sheet总行数
			if (totalrows > 1) {
				for (int i = 0; i < totalrows; i++) {
					XSSFRow hssfrow = hssfsheet.getRow(i);
					if(hssfrow!=null){
						int cellNumbers = hssfrow.getPhysicalNumberOfCells();
						List<String> columnValues = new ArrayList<String>();
						for (int j = 0; j < limitColumnNumber; j++) {
							if (j + 1 <= cellNumbers) {
								Cell cell = hssfrow.getCell(j);
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
		return arrayLists;
	}

	/**
	 * xls文档
	 */
	private static List<ArrayList<String>> readXls(InputStream inputstream, int limitColumnNumber) throws Exception {
		List<ArrayList<String>> arrayLists = new ArrayList<ArrayList<String>>();
		HSSFWorkbook hssfworkbook = new HSSFWorkbook(inputstream);
		HSSFSheet hssfsheet = hssfworkbook.getSheetAt(0);// 第一个工作表
		if (hssfsheet != null) {
			int totalrows = hssfsheet.getPhysicalNumberOfRows();// --获取sheet总行数
			if (totalrows > 1) {
				for (int i = 0; i < totalrows; i++) {
					HSSFRow hssfrow = hssfsheet.getRow(i);
					if(hssfrow!=null){
						int cellNumbers = hssfrow.getPhysicalNumberOfCells();
						List<String> columnValues = new ArrayList<String>();
						for (int j = 0; j < limitColumnNumber; j++) {
							if (j + 1 <= cellNumbers) {
								Cell cell = hssfrow.getCell(j);
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
		return arrayLists;
	}

	public static List<ArrayList<String>> read(MultipartFile file,String originalFilename, int limitColumnNumber) throws IOException, Exception {
		if (originalFilename.endsWith(".xls")) {
			return readXls(file.getInputStream(), limitColumnNumber);
		} else if (originalFilename.endsWith(".xlsx")) {
			return readXlsx(file.getInputStream(), limitColumnNumber);
		}
		return null;
	}

}
