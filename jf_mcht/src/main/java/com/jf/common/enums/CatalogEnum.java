package com.jf.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 信息栏目配置
 */
public enum CatalogEnum {

	HELP(1, "帮助中心"),
	OPERATION_STANDARD(4, "运营规范"),
	INFORMATION(8, "信息公告");

	private int value;
	private String name;

	CatalogEnum(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}


	public static List<Integer> valueList = new ArrayList<>();
	static{
		for(CatalogEnum info : CatalogEnum.values()){
			valueList.add(info.value);
		}
	}

}