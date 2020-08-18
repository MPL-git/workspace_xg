package com.jf.common.utils;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Excel_Bean {
	/**
	 * 报表bean
	 */
	public String titleStr;
	
	public HSSFWorkbook workbook;
	
	public List<?> datalist;
	
	
	public Excel_Bean(String titleStr, HSSFWorkbook workbook,
			List<?> datalist2) {
		super();
		this.titleStr = titleStr;
		this.workbook = workbook;
		this.datalist = datalist2;
	}
 
}
