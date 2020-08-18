package com.jf.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Excel Bean
 * @author yjc
 *
 */
public class ExcelBean {
	
	private String name;
	
	private String sheetName;
	
	private String[] titles;
	
	private List<String[]> dataList;
	
	private boolean headBold = true;
	
	private int columnWidth = 6000;
	
	public ExcelBean(String name, String sheetName, String[] titles){
		this.name = name;
		this.sheetName = sheetName;
		this.titles = titles;
		this.dataList = new ArrayList<String[]>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String[] getTitles() {
		return titles;
	}

	public void setTitles(String[] titles) {
		this.titles = titles;
	}

	public List<String[]> getDataList() {
		return dataList;
	}

	public void setDataList(List<String[]> dataList) {
		this.dataList = dataList;
	}
	
	public boolean isHeadBold() {
		return headBold;
	}

	public void setHeadBold(boolean headBold) {
		this.headBold = headBold;
	}

	public int getColumnWidth() {
		return columnWidth;
	}

	public void setColumnWidth(int columnWidth) {
		this.columnWidth = columnWidth;
	}

	public void add(String[] data){
		this.dataList.add(data);
	}
	

}
