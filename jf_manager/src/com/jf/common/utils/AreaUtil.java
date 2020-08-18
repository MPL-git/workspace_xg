package com.jf.common.utils;

import com.jf.common.ext.util.StrKit;

public class AreaUtil {


	public static String getShortProvinceName(String proviceName) {
		String shortName = proviceName;
		if(StrKit.isBlank(shortName)){
			return "未知";
		}

		if(proviceName.equals("北京市")){
			shortName = "北京";
		}else if(proviceName.equals("天津市")){
			shortName = "天津";
		}else if(proviceName.equals("河北省")){
			shortName = "河北";
		}else if(proviceName.equals("山西省")){
			shortName = "山西";
		}else if(proviceName.equals("内蒙古自治区")){
			shortName = "内蒙古";
		}else if(proviceName.equals("辽宁省")){
			shortName = "辽宁";
		}else if(proviceName.equals("吉林省")){
			shortName = "吉林";
		}else if(proviceName.equals("黑龙江省")){
			shortName = "黑龙江";
		}else if(proviceName.equals("上海市")){
			shortName = "上海";
		}else if(proviceName.equals("江苏省")){
			shortName = "江苏";
		}else if(proviceName.equals("浙江省")){
			shortName = "浙江";
		}else if(proviceName.equals("安徽省")){
			shortName = "安徽";
		}else if(proviceName.equals("福建省")){
			shortName = "福建";
		}else if(proviceName.equals("江西省")){
			shortName = "江西";
		}else if(proviceName.equals("山东省")){
			shortName = "山东";
		}else if(proviceName.equals("河南省")){
			shortName = "河南";
		}else if(proviceName.equals("湖北省")){
			shortName = "湖北";
		}else if(proviceName.equals("湖南省")){
			shortName = "湖南";
		}else if(proviceName.equals("广东省")){
			shortName = "广东";
		}else if(proviceName.equals("广西壮族自治区")){
			shortName = "广西";
		}else if(proviceName.equals("海南省")){
			shortName = "海南";
		}else if(proviceName.equals("重庆市")){
			shortName = "重庆";
		}else if(proviceName.equals("四川省")){
			shortName = "四川";
		}else if(proviceName.equals("贵州省")){
			shortName = "贵州";
		}else if(proviceName.equals("云南省")){
			shortName = "云南";
		}else if(proviceName.equals("西藏自治区")){
			shortName = "西藏";
		}else if(proviceName.equals("陕西省")){
			shortName = "陕西";
		}else if(proviceName.equals("甘肃省")){
			shortName = "甘肃";
		}else if(proviceName.equals("青海省")){
			shortName = "青海";
		}else if(proviceName.equals("宁夏回族自治区")){
			shortName = "宁夏";
		}else if(proviceName.equals("新疆维吾尔自治区")){
			shortName = "新疆";
		}else if(proviceName.equals("台湾省")){
			shortName = "台湾";
		}

		return shortName;
	}



}