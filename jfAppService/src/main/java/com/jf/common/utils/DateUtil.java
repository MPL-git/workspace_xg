package com.jf.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	public static final boolean isDate(String date)
	  {
	    boolean b = true;
	    try {
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.SIMPLIFIED_CHINESE);
	      sdf.setLenient(false);
	      sdf.parse(date);
	    } catch (ParseException e) {
	      b = false;
	    }
	    return b;
	  }

	  public static final Date getDate(String date) {
	    Date d = null;
	    try {
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.SIMPLIFIED_CHINESE);
	      d = sdf.parse(date);
	    } catch (ParseException e) {
	      e.printStackTrace();
	      throw new RuntimeException("日期格式异常");
	    }
	    return d;
	  }

	  public static final Date getDate() {
	    return new Date();
	  }

	  public static final String getFormatDate(Date date, String format)
	  {
	    SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.SIMPLIFIED_CHINESE);
	    return sdf.format(date);
	  }
	  
	  public static final Date getFormatString(String str, String format)
	  {
	    SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.SIMPLIFIED_CHINESE);
	    try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		    throw new RuntimeException("日期格式异常");
		}
	  }

	  public static final String getStandardDate(Date date)
	  {
	    return getFormatDate(date, "yyyy-MM-dd");
	  }

	  public static final String getStandardDate()
	  {
	    return getStandardDate(getDate());
	  }

	  public static final String getStandardDateTime(Date date)
	  {
	    return getFormatDate(date, "yyyy-MM-dd HH:mm:ss");
	  }

	  public static final String getStandardDateTime()
	  {
	    return getStandardDateTime(getDate());
	  }

	  private static final Date addInteger(Date date, int amount, int field)
	  {
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    c.add(field, amount);
	    return c.getTime();
	  }

	  public static final Date addHour(Date date, int amount)
	  {
	    return addInteger(date, amount, 11);
	  }

	  public static final Date addMinute(Date date, int amount)
	  {
	    return addInteger(date, amount, 12);
	  }

	  public static final Date addSecond(Date date, int amount)
	  {
	    return addInteger(date, amount, 13);
	  }

	  public static final Date addYear(Date date, int amount)
	  {
	    return addInteger(date, amount, 1);
	  }

	  public static final Date addMonth(Date date, int amount)
	  {
	    return addInteger(date, amount, 2);
	  }

	  public static final Date addDay(Date date, int amount)
	  {
	    return addInteger(date, amount, 6);
	  }

	  public static final Date addWeek(Date date, int amount)
	  {
	    return addInteger(date, amount, 3);
	  }

	  public static final Boolean isWeekend(Date date)
	  {
	    return Boolean.valueOf(false);
	  }
	  
	  public static final String getDateInfo(long ms,boolean d,boolean h,boolean m,boolean s) {
		    try {
	    	 	Integer ss = 1000;  
	    	    Integer mi = ss * 60;  
	    	    Integer hh = mi * 60;  
	    	    Integer dd = hh * 24;  
	    	  
	    	    Long day = ms / dd;  
	    	    Long hour = (ms - day * dd) / hh;  
	    	    Long minute = (ms - day * dd - hour * hh) / mi;  
	    	    Long second = (ms - day * dd - hour * hh - minute * mi) / ss;  
	    	    //Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;  
	    	      
	    	    StringBuffer sb = new StringBuffer();  
	    	    if(day > 0 && d) {  
	    	        sb.append(day+"天");  
	    	    }  
	    	    if(hour > 0 && h) {  
	    	        sb.append(hour+"小时");  
	    	    }  
	    	    if(minute > 0 && m) {  
	    	        sb.append(minute+"分");  
	    	    }  
	    	    if(second > 0 && s) {  
	    	        sb.append(second+"秒");  
	    	    }  
//	    	    if(milliSecond > 0) {  
//	    	        sb.append(milliSecond+"毫秒");  
//	    	    }  
	    	    return sb.toString();
		    } catch (Exception e) {
		      e.printStackTrace();
		      throw new RuntimeException("日期格式转化异常");
		    }
		  }
	  
	  /**
	   * 
	   * 方法描述 ：获取活动剩余结束时间
	   * @author  chenwf: 
	   * @date 创建时间：2018年2月8日 上午10:52:33 
	   * @version
	   * @param activityEndTime
	   * @return
	   */
	public static String getActivityRemainingTime(Date activityEndTime) {
		String activityRemainingTime = "";
		Integer ss = 1000;  
	    Integer mi = ss * 60;  
	    Integer hh = mi * 60;  
	    Integer dd = hh * 24;
		long ms = activityEndTime.getTime()-DateUtil.getDate().getTime();
		Long day = ms / dd;  
	    Long hour = (ms - day * dd) / hh;  
	    Long minute = (ms - day * dd - hour * hh) / mi;  
	    Long second = (ms - day * dd - hour * hh - minute * mi) / ss; 
	    
	    //String activityTime = "";
	    if(day > 0){
	    	if(hour > 0 || minute > 0 || second > 0){
	    		day = day + 1;
	    	}
	    	activityRemainingTime = " 剩"+day.toString()+"天";
	    }else if(hour > 0){
	    	activityRemainingTime = " 剩"+hour.toString()+"小时";
	    }else if(minute > 0){
	    	activityRemainingTime = " 剩"+minute.toString()+"分";
	    }else{
	    	activityRemainingTime = " 剩1分";
	    }
		return activityRemainingTime;
	}
	
	/**
	 * 给指定日期加减天数，返回0点
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static java.util.Date getDateAfterAndBeginTime(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, day);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		java.util.Date newdate = calendar.getTime();
		return newdate;
	}

	/**
	 * 给指定日期加减天数，返回23:59:59:999
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static java.util.Date getDateAfterAndEndTime(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, day);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		java.util.Date newdate = calendar.getTime();
		return newdate;
	}

	/**
	 * 获得当前月第一天
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static java.util.Date getMonthBeginData(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		// 将小时至0
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		// 将分钟至0
		calendar.set(Calendar.MINUTE, 0);
		// 将秒至0
		calendar.set(Calendar.SECOND, 0);
		// 将毫秒至0
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获得当前月最后一天
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static java.util.Date getMonthEndData(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		// 将小时至0
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		// 将分钟至0
		calendar.set(Calendar.MINUTE, 0);
		// 将秒至0
		calendar.set(Calendar.SECOND, 0);
		// 将毫秒至0
		calendar.set(Calendar.MILLISECOND, 0);
		// 将当前月加1；
		calendar.add(Calendar.MONTH, 1);
		// 在当前月的下一月基础上减去1毫秒
		calendar.add(Calendar.MILLISECOND, -1);
		return calendar.getTime();
	}
	  
	  public static String getWeekOfDate(Date date) { 
			String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" }; 
			String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" }; 
			Calendar calendar = Calendar.getInstance(); 
			calendar.setTime(date); 
			int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1; 
			return weekDaysName[intWeek]; 
		}

	public static Calendar calendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	public static int get(Date date, int field) {
		Calendar calendar = calendar(date);
		return calendar.get(field);
	}

	private static final String DATE_FORMAT_1 = "MM月dd日 HH:mm";
	private static final String DATE_FORMAT_2 = "yyyy年MM月dd日 HH:mm";

	/**
	 * 装饰时间，规则如下：
	 * 当年的用 XX月XX日  时分 如：8月1日 15:00
	 * 往年的用 XXXX年XX月XX日 时分   如：2017年8月1日 15:00
	 */
	public static String decorateDate(Date date) {
		Date now = new Date();
		if (get(now, Calendar.YEAR) == get(date, Calendar.YEAR)) {
			return getFormatDate(date, DATE_FORMAT_1);
		}
		return getFormatDate(date, DATE_FORMAT_2);
	}

	public static java.util.Date getDateHourTime(Date date, int hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hour);
		java.util.Date newdate = calendar.getTime();
		return newdate;
	}


}
