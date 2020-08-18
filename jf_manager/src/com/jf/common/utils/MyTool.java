package com.jf.common.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.gzs.common.utils.StringUtil;

public class MyTool {

	private static DecimalFormat df = new DecimalFormat("#.00");

	public static String getRandomNum(int length) {
		if (length <= 0) {
			length = 1;
		}
		StringBuilder res = new StringBuilder();
		Random random = new Random();
		int i = 0;
		while (i < length) {
			res.append(random.nextInt(10));
			i++;
		}
		return res.toString();
	}

	// format yyyy.MM.dd
	public static String getToday(String format) {
		SimpleDateFormat df = null;
		if ("".equals(MyTool.trimObj(format))) {
			df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		} else {
			df = new SimpleDateFormat(format);// 设置日期格式
		}

		return df.format(new Date());// new Date()为获取当前系统时间
	}

	public static String getMonthFirst(String formatStr) {
		Calendar cal_1 = Calendar.getInstance();// 获取当前日期
		SimpleDateFormat format = null;
		if ("".equals(MyTool.trimObj(formatStr))) {
			format = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		} else {
			format = new SimpleDateFormat(formatStr);// 设置日期格式
		}

		cal_1.add(Calendar.MONTH, 0);
		cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String firstDay = format.format(cal_1.getTime());
		return firstDay;
	}

	public static int createHeader(HSSFWorkbook workbook, HSSFSheet sheet,
			String string, String str[]) {
		// 创建表头
		HSSFRow row0 = sheet.createRow((short) 0);
		HSSFRow row1 = sheet.createRow((short) 1);

		row0.setHeight((short) 1000);

		// 定义所需列数
		int columnNum = str.length;

		HSSFCell titleCell1 = null;
		HSSFCell titleCell2 = null;

		for (int i = 0; i < columnNum; i++) {
			titleCell1 = row0.createCell((short) i);
			titleCell2 = row1.createCell((short) i);
			if (i == 0) {
				titleCell1.setCellStyle(setTitleFontSize(workbook, 20));
				titleCell1.setCellValue(new HSSFRichTextString(string));
			}
			titleCell2.setCellStyle(setTitleFontSize(workbook, 12));
			titleCell2.setCellValue(new HSSFRichTextString(str[i]));
		}

		sheet.addMergedRegion(new Region(0, (short) 0, 0,
				(short) (columnNum - 1)));
		return columnNum;
	}

	public static String sumAll(List<?> list) {
		double result = 0;
		for (Object num : list) {
			result += obj2Double(((Map<String, Object>) num).get("TOTAL_PRICE"));
		}

		return formatNum(result + "");

	}

	public static HSSFRow[] init_table(HSSFSheet sheet, int headRow,
			int columnNum, String default_width, String default_height,
			Excel_Bean excel) {
		if (default_width == null || default_height == null) {
			sheet.setDefaultColumnWidth((short) 20);// 默认列宽
			sheet.setDefaultRowHeight((short) 10);// 默认列长
		}

		HSSFCellStyle dataCellStyle = PoiUtil.getDataCellStyle(excel.workbook);

		HSSFRow rows[] = new HSSFRow[excel.datalist.size() + 2];
		for (int j = 0; j < rows.length; j++) {
			rows[j] = sheet.createRow(j + headRow);
			for (short i = 0; i < columnNum; i++) {
				HSSFCell cell = rows[j].createCell(i);
				cell.setCellStyle(dataCellStyle);
			}
		}
		return rows;
	}

	public static HSSFCellStyle setTitleFontSize(HSSFWorkbook workbook,
			int fontSize) {
		HSSFCellStyle titleCellStyle = PoiUtil.getHeaderCellStyle(workbook);
		HSSFFont titleFont = workbook.createFont();
		titleFont.setFontHeightInPoints((short) fontSize);
		titleCellStyle.setFont(titleFont);
		return titleCellStyle;
	}

	public static void setExcle_data(List<?> datalist, HSSFRow rows[],
			String[] key, Excel_Bean excel) {
		for (int i = 0; i < excel.datalist.size(); i++) {
			Map<String, Object> tempMap = (Map<String, Object>) excel.datalist
					.get(i);
			HSSFRow row = rows[i];
			for (short j = 0; j < key.length; j++) {
				Object object = tempMap.get(key[j]);
				String string = "";
				if (object != null)
					string = tempMap.get(key[j]).toString();

				/*
				 * if (j == 5) { String string2 = ""; Object object2 =
				 * tempMap.get(key[j + 1]); if (object2 != null) { string2 =
				 * object2.toString(); row.getCell(j).setCellValue( new
				 * HSSFRichTextString(string + string2)); } break; }
				 */

				try {
					Long number = Long.valueOf(string);
					row.getCell(j).setCellValue(number);
				} catch (Exception e) {
					row.getCell(j).setCellValue(new HSSFRichTextString(string));
				}

			}
		}
	}

	public static void setExcle_data2(List<?> datalist, HSSFRow rows[],
			String[] key, Excel_Bean excel) {
		for (int i = 0; i < excel.datalist.size(); i++) {
			Map<String, Object> tempMap = (Map<String, Object>) excel.datalist
					.get(i);
			HSSFRow row = rows[i];
			for (short j = 0; j < key.length; j++) {
				Object object = tempMap.get(key[j]);
				String string = "";
				if (object != null)
					string = tempMap.get(key[j]).toString();

				try {
					Long number = Long.valueOf(string);
					row.getCell(j).setCellValue(number);
				} catch (Exception e) {
					row.getCell(j).setCellValue(new HSSFRichTextString(string));
				}

			}

		}
		HSSFRow row = rows[excel.datalist.size() + 1];
		short s1 = 0;
		row.getCell(s1).setCellValue("总金额：");
		String sum = MyTool.sumAll(datalist);
		short s2 = 1;
		row.getCell(s2).setCellValue(sum);

	}

	public static String sumObj(Object s1, Object s2) {
		double a = Double.parseDouble(MyTool.trimObj(s1));
		double b = Double.parseDouble(MyTool.trimObj(s2));
		String result = String.valueOf(a + b);
		return formatNum(result);
	}

	public static String sum(String s1, String s2) {
		double a = Double.parseDouble(s1);
		double b = Double.parseDouble(s2);
		String result = String.valueOf(a + b);
		return formatNum(result);
	}

	public static String multiply(String s1, String s2) {
		double a = Double.parseDouble(s1);
		double b = Double.parseDouble(s2);
		String result = String.valueOf(a * b);
		return formatNum(result);
	}

	public static String difference(String s1, String s2) {
		double a = Double.parseDouble(s1);
		double b = Double.parseDouble(s2);
		String result = String.valueOf(a - b);
		return formatNum(result);
	}

	public static String differenceObj(Object s1, Object s2) {
		double a = Double.parseDouble(MyTool.trimObj(s1));
		double b = Double.parseDouble(MyTool.trimObj(s2));
		String result = String.valueOf(a - b);
		return formatNum(result);
	}

	public static String obj2Str(Object o) {
		if (o == null) {
			return "";
		}
		String result = trimStr(o.toString());
		return result;
	}

	public static int obj2Int(Object o) {
		if (o == null) {
			return 0;
		}
		String result = trimStr(o.toString());
		if (result.equals("")) {
			return 0;
		}
		return Integer.parseInt(result);
	}

	public static long obj2Long(Object o) {
		if (o == null || "".equals(o.toString())) {
			return 0;
		}
		String result = trimStr(o.toString());
		return Long.parseLong(result);
	}

	public static float obj2Float(Object o) {
		if (o == null || "".equals(trimStr(o.toString()))
				|| "null".equals(trimStr(o.toString()))) {
			return 0;
		}
		String result = trimStr(o.toString());
		return Float.parseFloat(result);
	}

	public static double obj2Double(Object o) {
		if (o == null || "".equals(trimStr(o.toString()))
				|| "null".equals(trimStr(o.toString()))) {
			return 0;
		}
		String result = trimStr(o.toString());
		return Double.parseDouble(result);
	}

	public static String formatNum(String num) {

		String result = new DecimalFormat("0.00")
				.format(String2Double(trimStr(num)));
		return result;
	}

	public static String trimStr(String s) {
		if (s == null) {
			return "";
		} else {
			return s.trim();
		}
	}

	public static String trimObj(Object s) {
		String str = obj2Str(s);
		if (str == null) {
			return "";
		} else {
			return str.trim();
		}
	}

	/**
	 * 将字符集转换成整型
	 * 
	 * @param String
	 * @return int
	 */
	public static double String2Double(String s) {
		if (s == null) {
			return 0;
		}
		try {
			return Double.parseDouble(trimStr(s));
		} catch (NumberFormatException notint) {
			return 0;
		}
	}

	public static String enCodeStr(String str) {
		try {
			return new String(str.getBytes("iso-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static BigDecimal getBigDecimal(Object value) {
		BigDecimal ret = null;
		if (value != null) {
			if (value instanceof BigDecimal) {
				ret = (BigDecimal) value;
			} else if (value instanceof String) {
				ret = new BigDecimal((String) value);
			} else if (value instanceof BigInteger) {
				ret = new BigDecimal((BigInteger) value);
			} else if (value instanceof Number) {
				ret = new BigDecimal(((Number) value).doubleValue());
			} else {
				throw new ClassCastException("Not possible to coerce [" + value
						+ "] from class " + value.getClass()
						+ " into a BigDecimal.");
			}
		}
		return ret;
	}

	public String getIpAddr(HttpServletRequest request) {
	    String ip = request.getHeader("WL-Proxy-Client-IP");
	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
	    	ip = request.getHeader("X-Forwarded-For");
	    	if(!StringUtil.isEmpty(ip)&&!"unknown".equalsIgnoreCase(ip)){
	    		ip=(ip.split(",")[0]).trim();
	    	}
	    }
	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
	      ip = request.getHeader("Proxy-Client-IP");
	    }
	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
	      ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
	      ip = request.getHeader("HTTP_CLIENT_IP");
	    }
	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
	      ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	    }
	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
	      ip = request.getRemoteAddr();
	    }
	    return ip;
	  }

	public static String getNow() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}

	public static BigDecimal strToBig(String StrBd) {

		// 构造以字符串内容为值的BigDecimal类型的变量bd
		BigDecimal bd = new BigDecimal(StrBd);
		// 设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		// 转化为字符串输出
		return bd;

	}

	private static Date date = new Date();
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");

	public static String pas(String password, String userCode) {
		String ret = "";

		ret = SecurityUtil.md5Encode(userCode + password);
		ret = ret.substring(8, 24);
		return ret;
	}

	public static String genCode() {

		int k = 1;

		// JDK 1.5 or higher
		String number = String.format("%tY%<tm%<td%03d", date, k);
		System.out.println(number);

		// JDK 1.4 or lower

		String d = sdf.format(date);

		int number1 = 0;
		Set set = new HashSet();
		int[] array = new int[4];
		while (number1 < 4) {
			int temp = (int) (Math.random() * 10);
			if (set.add(temp)) {
				array[number1++] = temp;
			}
		}
		// print array
		StringBuffer sb = new StringBuffer();
		for (int index = 0; index < array.length; index++) {
			sb.append(array[index]);
		}

		return (d + sb);
	}

	/**
	 * 字符串编码转换的实现方法
	 * 
	 * @param str
	 *            待转换编码的字符串
	 * @param newCharset
	 *            目标编码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String changeCharset(String str, String newCharset)
			throws UnsupportedEncodingException {
		if (str != null) {
			// 用默认字符编码解码字符串。
			byte[] bs = str.getBytes();
			// 用新的字符编码生成字符串
			return new String(bs, newCharset);
		}
		return null;
	}

	public static boolean comparaDay(Date d) {
		Date nowdate = new Date();

		boolean flag = d.before(nowdate);
		return flag;
	}

	public static List<Map<String, Object>> getWeeks() {
		if (getDays() != 0) {
			return new ArrayList<Map<String, Object>>();
		}
		List<Map<String, Object>> weekList = new ArrayList<Map<String, Object>>();

		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
		int month = calendar.get(Calendar.MONTH);
		int count = 0;
		while (calendar.get(Calendar.MONTH) == month) {

			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
				if (comparaDay(calendar.getTime()) == false) {
					break;
				}
				Map<String, Object> map = new HashMap<String, Object>();
				count = count + 1;
				map.put("count", count);
				map.put("start", format.format(calendar.getTime()));
				calendar.add(Calendar.DATE, 6);
				map.put("end", format.format(calendar.getTime()));
				weekList.add(map);
				String monthFirst = MyTool.getMonthFirst();

				if (count == 1 && MyTool.getDays("yyyy.MM.dd", monthFirst) != 1) {
					Map<String, Object> beforeMap = new HashMap<String, Object>();
					beforeMap.put("count", 0);
					beforeMap.put(
							"start",
							MyTool.dateSubtraction(
									MyTool.trimObj(map.get("start")), 7));

					beforeMap.put(
							"end",
							MyTool.dateSubtraction(
									MyTool.trimObj(map.get("end")), 7));

					weekList.add(0, beforeMap);
				}

			}
			calendar.add(Calendar.DATE, 1);
		}
		System.out.println("weekList=" + weekList);
		if (weekList.size() > 0) {
			weekList.get(weekList.size() - 1).put("end",
					MyTool.getToday("yyyy.MM.dd"));
		}
		return weekList;
	}

	public static List<Map<String, Object>> getWeeks(String dateStr) {
		int isDay = getDays("yyyy.MM.dd", dateStr);
		String[] dateArr = dateStr.split("\\.");
		if (isDay != 0) {
			return new ArrayList<Map<String, Object>>();
		}
		List<Map<String, Object>> weekList = new ArrayList<Map<String, Object>>();

		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		Calendar calendar = Calendar.getInstance();
		/*
		 * calendar.set(MyTool.obj2Int(dateArr[0]), MyTool.obj2Int(dateArr[1]),
		 * MyTool.obj2Int(dateArr[2]), 0, 0, 0);
		 */
		calendar.set(Calendar.DATE, 1);
		int month = calendar.get(Calendar.MONTH);
		int count = 0;
		Date datePara = new Date();
		try {
			datePara = format.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (calendar.getTime().before(datePara)
				|| calendar.getTime().equals(datePara)) {

			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
				if (comparaDay(calendar.getTime()) == false) {
					break;
				}
				Map<String, Object> map = new HashMap<String, Object>();
				count = count + 1;
				map.put("count", count);
				map.put("start", format.format(calendar.getTime()));
				calendar.add(Calendar.DATE, 6);
				map.put("end", format.format(calendar.getTime()));
				weekList.add(map);
				String monthFirst = MyTool.getMonthFirst();
				if (count == 1 && MyTool.getDays("yyyy.MM.dd", monthFirst) != 1) {
					Map<String, Object> beforeMap = new HashMap<String, Object>();
					beforeMap.put("count", 0);
					beforeMap.put(
							"start",
							MyTool.dateSubtraction(
									MyTool.trimObj(map.get("start")), 7));

					beforeMap.put(
							"end",
							MyTool.dateSubtraction(
									MyTool.trimObj(map.get("end")), 7));

					weekList.add(0, beforeMap);
				}

			}
			calendar.add(Calendar.DATE, 1);
		}
		System.out.println("weekList=" + weekList);
		/*
		 * if (weekList.size() > 0) { weekList.get(weekList.size() -
		 * 1).put("end", MyTool.getToday("yyyy.MM.dd")); }
		 */
		return weekList;
	}

	/*
	 * public static List<Map<String, Object>> getWeeks() { List<Map<String,
	 * Object>> weekList = new ArrayList<Map<String, Object>>();
	 * 
	 * //SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd"); Calendar
	 * calendar = Calendar.getInstance(); System.out.println(calendar);
	 * System.out.println("Calendar.MONTH=" + Calendar.MONTH);
	 * calendar.set(Calendar.DATE, 1); System.out.println("Calendar.DATE=" +
	 * Calendar.DATE); Map<String, Object> map = new HashMap<String, Object>();
	 * map.put("count",0); map.put("start", "2016.05.30");
	 * 
	 * map.put("end", "2016.06.05"); weekList.add(map);
	 * 
	 * 
	 * return weekList; }
	 */
	public static String dateSubtraction(String dateStr, int num) {

		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
		Date d = new Date();
		try {
			d = df.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return df.format(new Date(d.getTime() - num * 24 * 60 * 60 * 1000));
	}

	public static int getDays() {
		Calendar cal = Calendar.getInstance();
		Date d = new Date();

		cal.setTime(d);
		int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return week;
	}

	public static int getDays(String formatStr, String dateStr) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(formatStr);
		try {
			Date d = df.parse(dateStr);
			cal.setTime(d);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return week;
	}

	public static String getMonthFirst() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		Calendar cal_1 = Calendar.getInstance();// 获取当前日期
		cal_1.add(Calendar.MONTH, 0);
		cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String firstDay = format.format(cal_1.getTime());
		return firstDay;
	}

	public static String getRemothref(HttpServletRequest request, String href) {
		StringBuffer url = new StringBuffer();
		url.append("http://").append(request.getServerName()).append(":")
				.append(request.getServerPort())
				.append(request.getContextPath()).append(href);
		return MyTool.trimObj(url);
	}

	public static Map<String, Object> getMonthDay(String dateStr) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		// 获取前月的第一天
		// 获取当前日期
		Calendar cal_1 = Calendar.getInstance();
		Date para_date = new Date();
		try {
			para_date = format.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cal_1.setTime(para_date);// 将这个日期set进去
		cal_1.add(Calendar.MONTH, -1);
		// 设置为1号,当前日期既为本月第一天
		cal_1.set(Calendar.DAY_OF_MONTH, 1);
		String lastMonfirstDay = format.format(cal_1.getTime());
		resultMap.put("lastMonfirstDay", lastMonfirstDay);
		// 获取前月的最后一天
		Calendar cale = Calendar.getInstance();
		cale.setTime(para_date);// 将这个日期set进去

		// 设置为1号,当前日期既为本月第一天
		cale.set(Calendar.DAY_OF_MONTH, 0);
		String lastMonlastDay = format.format(cale.getTime());
		resultMap.put("lastMonlastDay", lastMonlastDay);

		// 获取当前月第一天：
		Calendar c = Calendar.getInstance();
		c.setTime(para_date);
		c.add(Calendar.MONTH, 0);
		// 设置为1号,当前日期既为本月第一天
		c.set(Calendar.DAY_OF_MONTH, 1);
		String firstDay = format.format(c.getTime());
		resultMap.put("firstDay", firstDay);

		// 获取当前月最后一天
		Calendar ca = Calendar.getInstance();
		ca.setTime(para_date);
		ca.set(Calendar.DAY_OF_MONTH,
				ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		String lastDay = format.format(ca.getTime());
		resultMap.put("lastDay", lastDay);

		return resultMap;

	}

	public static Map<String, Object> getWeekDay(String dateStr) {

		Map<String, Object> map = new HashMap<String, Object>();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date para_date = new Date();
		try {
			para_date = df.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cal.setTime(para_date);// 将这个日期set进去
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
		map.put("mon", df.format(cal.getTime()));
		// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 增加一个星期，才是我们中国人理解的本周日的日期
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		map.put("sun", df.format(cal.getTime()));

		map.put("lastmon", getLastMonday(dateStr));
		map.put("lastsun", getLastSunday(dateStr));

		return map;
	}

	public static String getLastMonday(String dateStr) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date para_date = new Date();
		try {
			para_date = df.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cal.setTime(para_date);// 将这个日期set进去
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 将每周第一天设为星期一，默认是星期天
		cal.add(Calendar.DATE, -1 * 7);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		String monday = new SimpleDateFormat("yyyy-MM-dd")
				.format(cal.getTime());
		return monday;
	}

	public static String getLastSunday(String dateStr) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date para_date = new Date();
		try {
			para_date = df.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cal.setTime(para_date);// 将这个日期set进去
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 将每周第一天设为星期一，默认是星期天
		cal.add(Calendar.DATE, -1 * 7);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		String sunday = new SimpleDateFormat("yyyy-MM-dd")
				.format(cal.getTime());
		return sunday;
	}

	public static String getNextMonday() {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 将每周第一天设为星期一，默认是星期天
		cal.add(Calendar.DATE, 1 * 7);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		String monday = new SimpleDateFormat("yyyy-MM-dd")
				.format(cal.getTime());
		return monday;
	}

	public static String getNextSunday() {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 将每周第一天设为星期一，默认是星期天
		cal.add(Calendar.DATE, 1 * 7);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		String sunday = new SimpleDateFormat("yyyy-MM-dd")
				.format(cal.getTime());
		return sunday;
	}

	public static String lastDay(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (Exception e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);
		String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c
				.getTime());
		return dayBefore;
	}
}
