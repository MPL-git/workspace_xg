package com.jf.common.enums;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 活动促销方式
 */
public enum ActivityPreferentialTypeEnum {

	NULL(0, "无"),
	COUPON(1, "优惠券"),
	CUT(2, "满减"),
	GIVE(3, "满赠"),
	DISCOUNT(4, "多买优惠"),
	ALLOWANCE(5,"购物津贴");



	private int value;
	private String name;

	ActivityPreferentialTypeEnum(int value, String name) {
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
		ActivityPreferentialTypeEnum[] values = ActivityPreferentialTypeEnum.values();
		JSONObject data;
		for(ActivityPreferentialTypeEnum info : values){
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
}