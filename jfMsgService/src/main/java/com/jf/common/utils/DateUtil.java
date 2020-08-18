package com.jf.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

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
	  
	  public static final Date getDate(String date,String format) {
		  Date d = null;
		  try {
			  SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.SIMPLIFIED_CHINESE);
			  d = sdf.parse(date);
		  } catch (ParseException e) {
			  e.printStackTrace();
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

	  public static void main(String[] args) {
//		  String payDate = "2018-2-13 01:00:00";
//		  String payBeginDate = "2018-2-12 00:00:00";
//			String payEndDate = "2018-2-22 23:59:59";
//			Date begin = DateUtil.getDate(payBeginDate);
//			Date end = DateUtil.getDate(payEndDate);
//			Date date = DateUtil.getDate(payDate);
//			if(date.after(begin) && date.before(end)){
//				System.out.println(begin);//今天零点零分零秒
//				System.out.println(end);//今天23点59分59秒
//			}
		  Date date = new Date();
		  System.out.println(DateUtil.getDateAfterAndBeginTime(date, 60));
		  System.out.println(DateUtil.getDateAfterAndEndTime(date, 60));
		  
	  }
	  
	  
	    /** 
	    * 根据当前日期获得上周的日期区间（上周周一和周日日期） 
	    *  
	    * @return 
	    * @author
	    */  
	    public static Map<String, String> getLastWeekDate() {  
	         Calendar calendar1 = Calendar.getInstance();  
	         Calendar calendar2 = Calendar.getInstance();  
	         int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;  
	         int offset1 = 1 - dayOfWeek;  
	         int offset2 = 7 - dayOfWeek;  
	         calendar1.add(Calendar.DATE, offset1 - 7);  
	         calendar2.add(Calendar.DATE, offset2 - 7);  
	         // System.out.println(sdf.format(calendar1.getTime()));// last Monday  
	         String lastMonday = getStandardDate(calendar1.getTime());  
	         // System.out.println(sdf.format(calendar2.getTime()));// last Sunday  
	         String lastSunday = getStandardDate(calendar2.getTime());  
	         
	         Map<String, String> resultMap=new HashMap<String, String>();
	         resultMap.put("lastMonday", lastMonday);
	         resultMap.put("lastSunday", lastSunday);
	         return resultMap;
	    }
	    
	    /** 
	     * 获取某月第一天 00:00:00
	     *  
	     * @return 
	     * @author
	     */  
	    public static Date getFirstMonthDate(int add) {  
	        Calendar calendar = Calendar.getInstance();  
	        calendar.add(Calendar.MONTH, add);
	        calendar.set(Calendar.DATE, calendar.getActualMinimum(calendar.DATE));
	        calendar.set(Calendar.HOUR_OF_DAY, 0);
	        calendar.set(Calendar.MINUTE, 0);
	        calendar.set(Calendar.SECOND, 0);
	        calendar.set(Calendar.MILLISECOND, 0);
	        return calendar.getTime();
	    } 
	    
	    /** 
	     * 获取某月第最后一天 23:59:59
	     * @param add -1表示上个月 0表示当前月 1 表示下个月 以此类推
	     * @return 
	     * @author
	     */  
	    public static Date getLastMonthDate(int add) {  
	    	Calendar calendar = Calendar.getInstance();  
	    	calendar.add(Calendar.MONTH, add);
	    	calendar.set(Calendar.DATE, calendar.getActualMaximum(calendar.DATE));
	    	calendar.set(Calendar.HOUR_OF_DAY, 23);
	    	calendar.set(Calendar.MINUTE, 59);
	    	calendar.set(Calendar.SECOND, 59);
	    	calendar.set(Calendar.MILLISECOND, 999);
	    	return calendar.getTime();
	    } 
	    
	    /**
    	 * 给指定日期加减天数，返回结果日期
    	 * 
    	 * @param date
    	 * @param day
    	 * @return
    	 */
    	public static java.util.Date getDateAfter(java.util.Date date, int day) {
    		Calendar calendar = Calendar.getInstance();
    		calendar.setTime(date);
    		calendar.add(Calendar.DAY_OF_YEAR, day);
    		java.util.Date newdate = calendar.getTime();
    		return newdate;
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
    	 * 给指定日期加减小时，返回结果日期
    	 * 
    	 * @param date
    	 * @param day
    	 * @return
    	 */
    	public static java.util.Date getDateAfterHour(java.util.Date date, int hour) {
    		Calendar calendar = Calendar.getInstance();
    		calendar.setTime(date);
    		calendar.add(Calendar.HOUR_OF_DAY, hour);
    		java.util.Date newdate = calendar.getTime();
    		return newdate;
    	}
    	
    	/**
    	 * 
    	 * @MethodName: getBetweenDates
    	 * @Description: (获取两个日期之间的日期)
    	 * @author Pengl
    	 * @date 2019年5月31日 下午5:00:09
    	 */
    	public static List<String> getBetweenDates(Date start, Date end, SimpleDateFormat sdf, String dateFlag) {
    	    List<String> dateList = new ArrayList<String>();
    	    Calendar tempStart = Calendar.getInstance();
    	    tempStart.setTime(start);

    	    Calendar tempEnd = Calendar.getInstance();
    	    tempEnd.setTime(end);

    	    if(tempStart.compareTo(tempEnd) < 1 ) {
    	    	dateList.add(sdf.format(start));
			}

    	    while (tempStart.before(tempEnd)) {
    	    	if("day".equals(dateFlag)) {
    		    	tempStart.add(Calendar.DAY_OF_YEAR, 1);
    		    }else if("month".equals(dateFlag)) {
    		    	tempStart.add(Calendar.MONTH, 1);
    		    }
    	    	dateList.add(sdf.format(tempStart.getTime()));
    	    }
    	    return dateList;
    	}
    	
}
