package com.jf.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 获取nonce_str随机数
 * @author chenwf
 *
 */
public class RandCharsUtils {
	private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

	public static String getRandomString(int length) { //
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";   
		Random random = new Random();   
		StringBuffer sb = new StringBuffer();
		int number = 0;
		for (int i = 0; i < length; i++) {   
			number = random.nextInt(base.length());   
			sb.append(base.charAt(number));   
		}   
		return sb.toString();   
	}   
	
	/*
	 * 获取当前时间
	 */
	public static String timeStart(){
		return df.format(new Date());
	}
	
	/*
	 * 获取有效时间
	 */
	public static String timeExpire(){
		Calendar now=Calendar.getInstance();
		now.add(Calendar.MINUTE,720);
		return df.format(now.getTimeInMillis());
	}


	public static void main(String[] args) {
	}

}
