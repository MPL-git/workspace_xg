package com.jf.common.enums;

import com.alibaba.fastjson.JSONObject;
import com.jf.common.utils.StringUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 开票类型
 */
public enum InvoiceTypeEnum {

//	SPECIAL(1, "增值税专用发票"),
	NORMAL(2, "普通发票"),
	NOT(3, "不需要发票");

	private int value;
	private String name;

	InvoiceTypeEnum(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}


	public static List<JSONObject> list = new ArrayList<>();
	public static Set<Integer> sets = new HashSet<>();
	static{
		InvoiceTypeEnum[] values = InvoiceTypeEnum.values();
		JSONObject data;
		for(InvoiceTypeEnum info : values){
			data = new JSONObject();
			data.put("value", info.value);
			data.put("name", info.name);
			list.add(data);
			sets.add(info.value);
		}
	}

	public static boolean contains(Integer value){
		return sets.contains(value);
	}

	public static String getNameOf(String value){
		if(StringUtil.isEmpty(value) || !contains(Integer.valueOf(value))){
			return "未确认";
		}
		String res = "";
		InvoiceTypeEnum[] values = InvoiceTypeEnum.values();
		for(InvoiceTypeEnum info : values){
			if(info.value == Integer.valueOf(value)){
				res = info.name;
			}
		}
		return res;
	}
}