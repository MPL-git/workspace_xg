package com.jf.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	private static SimpleDateFormat longFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String TIME_START = " 00:00:00";
	public static final String TIME_END = " 23:59:59";

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

	  public static final Date getMonthsAgo(Date date,int monthCount){
		  Calendar c = Calendar.getInstance();
		  c.setTime(date);
		  c.add(Calendar.MONTH, monthCount);
		  Date m = c.getTime();
		  return m;
	  }

	  /**
		 * 将字符串时间转换为java.util.date时间格式(字符串格式：yyyy-MM-dd hh:mm:ss)
		 *
		 * @param str
		 * @return
		 */
		public static java.util.Date getDateByLongFormat(String str) {
			if (str == null || str.length() < 1)
				return null;
			java.util.Date result = null;
			try {
				result = longFormat.parse(str);
			} catch (Exception e) {
			}
			return result;
		}

		/**
		 * 得到昨天的日期
		 *
		 * @param date
		 * @return
		 */
		public static java.util.Date getYesterDayDate() {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -1);
			return new java.util.Date(calendar.getTime().getTime());
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
    	 * 获取两个时间相差的天数
    	 */
    	public static long getDayBetween(java.util.Date startDate, java.util.Date endDate){
    		Calendar calst = Calendar.getInstance();
            Calendar caled = Calendar.getInstance();
            calst.setTime(startDate);
            caled.setTime(endDate);
            calst.set(java.util.Calendar.HOUR_OF_DAY, 0);
            calst.set(java.util.Calendar.MINUTE, 0);
            calst.set(java.util.Calendar.SECOND, 0);
            caled.set(java.util.Calendar.HOUR_OF_DAY, 0);
            caled.set(java.util.Calendar.MINUTE, 0);
            caled.set(java.util.Calendar.SECOND, 0);
            int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;
            return days;
    	}
	public static Date stringToDate(String dateStr) {
		return stringToDate(dateStr, DATE_TIME_FORMAT);
	}

	public static Date stringToDate(String dateStr, String format) {
		return toDate(dateStr, format, null);
	}

	public static Date toDate(String dateStr, String formatPattern, Date defaultValue) {
		if (StringUtil.isBlank(dateStr)) {
			return defaultValue;
		}
		try {
			SimpleDateFormat format = new SimpleDateFormat(formatPattern);
			return format.parse(dateStr);
		} catch (ParseException e) {
			return defaultValue;
		}
	}

	public static boolean validateDate(String date) {
		if (StringUtil.isBlank(date)) {
			return false;
		}
        if (stringToDate(date, DATE_FORMAT) != null) {
            return true;
        }
        if (stringToDate(date, DATE_TIME_FORMAT) != null) {
            return true;
        }
        return false;
	}

	  public static void main(String[] args) {
	    System.out.println(isDate("2014-11-31"));
	  }
}
