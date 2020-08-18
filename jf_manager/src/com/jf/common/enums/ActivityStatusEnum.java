package com.jf.common.enums;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 活动状态
 */
public enum ActivityStatusEnum {

	WAIT_COMMIT(1, "待提报"),
	WAIT_AUDIT(2, "待审核"),
	AUDITING(3, "审核中"),
	AUDIT_REJECT(4, "驳回"),
	PREPARING(11, "待开始"),
	PREHEAT(12, "预热中"),
	PROCESSING(13, "活动中"),
	FINISHED(14, "已结束");

	private int value;
	private String name;

	ActivityStatusEnum(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}


	public static List<JSONObject> list = new ArrayList<JSONObject>();
	public static Set<Integer> sets = new HashSet<Integer>();
	static{
		ActivityStatusEnum[] values = ActivityStatusEnum.values();
		JSONObject data;
		for(ActivityStatusEnum info : values){
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