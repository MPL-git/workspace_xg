package com.jf.common.utils;

import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface Excle_Inf {
	public HSSFRow[] init_table(HSSFSheet sheet, int headRow, int columnNum,String default_width, String default_height);
	
	public void setExcle_data(List<?> datalist, HSSFRow rows[],String key[]);

	public int createHeader(HSSFWorkbook workbook, HSSFSheet sheet,
			String string, String str[]);
	
	public HSSFCellStyle setTitleFontSize(HSSFWorkbook workbook, int fontSize);
}
