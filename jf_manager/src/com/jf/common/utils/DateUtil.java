package com.jf.common.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
	/**
	 * 取得报表默认的起始查询日期（前十六天）
	 * @param format
	 * @return
	 */
	public static String getDefaultSDay(String format){
		int x = -15;
		format = format==null?"yyyy-MM-dd":format;
		return getDate(x, format);
	}
	
	/**
	 * 取得报表默认的终止查询日期（前一天）
	 * @param format
	 * @return
	 */
	public static String getDefaultEDay(String format){
		int x = -1;
		format = format==null?"yyyy-MM-dd":format;
		return getDate(x, format);
	}
	
	/**
	 * 取得报表默认的起始查询月份（若当前的日期大于等于8号，则查询前6个月，反之查询前7个月）
	 * @param format
	 * @return
	 */
	public static String getDefaultSMon(String format){
		format = format==null?"yyyy-MM":format;
		String date  = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
		int x = -7;
		if(8<=Integer.parseInt(date)){
			x = -6;
		}
		return getMonth(x, format);
	}
	
	/**
	 * 取得报表默认的终止查询月份（若当前的日期大于等于8号，则查询上个月，反之查询上上个月）
	 * @param format
	 * @return
	 */
	public static String getDefaultEMon(String format){
		format = format==null?"yyyy-MM":format;
		String date  = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
		int x = -2;
		if(8<=Integer.parseInt(date)){
			x = -1;
		}
		return getMonth(x, format);
	}	
	
	
	/**
	 * 取得报表的默认查询日期
	 * @param format
	 * @return
	 */
	public static String getReportDate(String format){
		int x = -2;
		return getDate(x, format);
	}
	
	/**
	 * 取得日期【指定格式化】
	 * @param x
	 * @param format
	 * @return
	 */
	public static String getDate(int x, String format){
		Calendar calendar = Calendar.getInstance(); 
		calendar.add(Calendar.DATE, x);   
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String date  = sdf.format(calendar.getTime());
		return date;
	}
	
	/**
	 * 取指定分钟偏移量的时间
	 * @param x 
	 * @param format
	 * @return
	 */
	public static String getMinute(int x, String format) {
		Calendar calendar = Calendar.getInstance(); 
		calendar.add(Calendar.MINUTE, x);   
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String date  = sdf.format(calendar.getTime());
		return date;
	}
	
	/**
	 * 取得日期
	 * @param x
	 * @return
	 */
	public static String getDate(int x){
		Calendar calendar = Calendar.getInstance(); 
		calendar.add(Calendar.DATE, x);   
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date  = sdf.format(calendar.getTime());
		return date;
	}
	
	/**
	 * 取得报表的默认查询月份
	 * 判断当前日期是否小于等于当月10号 是查询上一个月份 否则查询本月
	 * @param x
	 * @param format
	 * @return
	 */
	public static String getReportMonth(String format){
		String date  = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
		int x = 0;
		if(10<=Integer.parseInt(date)){
			x = -1;
		}
		return getMonth(x, format);
	}
	/**
	 * 取得收入默认查询月份
	 * 比如现在是8月1号，那么展示的收入 是6月份的统计报表，8号后展现的是7月份的收入
	 * @param x
	 * @param format
	 * @return
	 */
	public static String getWarningMonth(String format){
		String date  = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
		int x = -2;
		if(8<=Integer.parseInt(date)){
			x = -1;
		}
		return getMonth(x, format);
	}
	
	/**
	 * 取得收入默认查询月份
	 * 比如现在是8月10号，那么展示的收入 是6月份的统计报表，10号后展现的是7月份的收入
	 * @param x
	 * @param format
	 * @return
	 */
	public static String getWarningMonthnew(String format){
		String date  = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
		int x = -2;
		if(10<=Integer.parseInt(date)){
			x = -1;
		}
		return getMonth(x, format);
	}
	/**
	 * 取得月份【指定格式化】
	 * @param x
	 * @return
	 */
	public static String getMonth(int x, String format){
		Calendar calendar = Calendar.getInstance(); 
		calendar.add(Calendar.MONTH, x);    
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String date  = sdf.format(calendar.getTime());
		return date;
	}
	
	/**
	 * 取得月份
	 * @param x
	 * @return
	 */
	public static String getMonth(int x){
		Calendar calendar = Calendar.getInstance(); 
		calendar.add(Calendar.MONTH, x);     
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String date  = sdf.format(calendar.getTime());
		return date;
	}
	
	/**
	 * 取得年份
	 * @param x
	 * @return
	 */
	public static String getYear(int x){
		Calendar calendar = Calendar.getInstance(); 
		calendar.add(Calendar.YEAR, x);     
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String date  = sdf.format(calendar.getTime());
		return date;
	}
	
	/**
	 * 取出指定的月份共有多少天
	 * @param mouth
	 * @return
	 */
    public static int getDayMouth(String mouth){
    	int i_year = Integer.parseInt(mouth.split("-")[0]);
    	int i_month = Integer.parseInt(mouth.split("-")[1]);
        int num=0;
        switch(i_month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                num=31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                num=30;
                break;
            case 2:
                num = isLeapYear(i_year)?29:28;
                break;
            default:
                System.out.println("非法月份");
                break;
            }
        return num;
        }

         //定义方法isLeapYear()方法判断指定的年份是否为闰年
        public static boolean isLeapYear(int year){  
        if((year%4==0)&&(year%100!=0)||(year%400==0))
        return true;
        else
        return false;
        }  
        
        /**
    	 * 处理界面上传上来的日期参数	2009-08 -> '200908'   or  2009-08-08 -> '20090808'
    	 * @param strdate
    	 * @return
    	 */
    	public static String fomatDate(String strdate){
    		StringBuffer famdate = new StringBuffer("");
    		String dt[] = strdate.split("-");
    		if(dt.length>0){
    			for(int i=0;i<dt.length;i++){
    				famdate.append(dt[i]);
    			}
    		}
    		return famdate.toString();
    	}
    	
    	/**
    	 * 得到上个月的日期
    	 * 
    	 * @param date
    	 * @return
    	 */
    	public static Date getPreDate(Date date) {
    		Calendar calendar = Calendar.getInstance();
    		calendar.setTime(date);
    		calendar.add(Calendar.MONTH, -1);
    		return new Date(calendar.getTime().getTime());
    	}

		public static Date getPreDate(Date date, int month) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, month);
			return new Date(calendar.getTime().getTime());
		}

    	/**
    	 * 给指定日期加减天数，返回结果日期
    	 *
    	 * @param date
    	 * @param day
    	 * @return
    	 */
    	public static Date getDateAfter(Date date, int day) {
    		Calendar calendar = Calendar.getInstance();
    		calendar.setTime(date);
    		calendar.add(Calendar.DAY_OF_YEAR, day);
    		Date newdate = calendar.getTime();
    		return newdate;
    	}

    	public static final Date getMonthsAgo(Date date, int monthCount){
  		  Calendar c = Calendar.getInstance();
  		  c.setTime(date);
  		  c.add(Calendar.MONTH, monthCount);
  		  Date m = c.getTime();
  		  return m;
  	  	}

  	  	public static final Date getYearsAgo(Date date, int yearCount){
  		  Calendar c = Calendar.getInstance();
  		  c.setTime(date);
  		  c.add(Calendar.YEAR, yearCount);
  		  Date m = c.getTime();
  		  return m;
  	  	}

    	/**
    	 * 将字符串时间转换为java.util.date时间格式(字符串格式：yyyy-MM-dd)
    	 *
    	 * @param str
    	 * @return
    	 */
    	public static Date getDateByFormat(String str) {
    		if (str == null || str.length() < 1)
    			return null;
    		Date result = null;
    		try {
    			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    			result = format.parse(str);
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
    	public static Date getYesterDayDate() {
    		Calendar calendar = Calendar.getInstance();
    		calendar.add(Calendar.DATE, -1);
    		return new Date(calendar.getTime().getTime());
    	}

    	/**
    	 * 取得指定月份的第一天
    	 *
    	 * @param strdate
    	 *            String
    	 * @return String
    	 */
    	public static String getMonthBegin(String strdate) {
    		Date date = StringToDate(strdate);
    		return formatDateByFormat(date, "yyyy-MM") + "-01";
    	}

    	/**
    	 * 取得指定月份的最后一天
    	 *
    	 * @param strdate
    	 *            String
    	 * @return String
    	 */
    	public static String getMonthEnd(String strdate) {
    		Date date = StringToDate(strdate);

    		Calendar calendar = Calendar.getInstance();
    		calendar.setTime(date);
    		calendar.add(Calendar.MONTH, 1);
    		calendar.add(Calendar.DAY_OF_YEAR, -1);
    		return formatDate(calendar.getTime());
    	}

    	/**
    	 * 取得当前月份的第一天
    	 *
    	 * @param strdate
    	 *            String
    	 * @return String
    	 */
    	public static String getMonthFirstDay() {
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    		Calendar calendar = Calendar.getInstance();
    		calendar.add(Calendar.MONTH, 0);
    		calendar.set(Calendar.DAY_OF_MONTH,1);
    		String firstDay = format.format(calendar.getTime());
    		return firstDay;
    	}

    	/**
    	 * String转化为java.sql.date类型，
    	 *
    	 * @param strDate
    	 * @return
    	 */
    	public static java.sql.Date StringToDate(String strDate) {
    		if (strDate != null && !strDate.equals("")) {
    			try {
    				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    				Date d = formatter.parse(strDate);
    				java.sql.Date numTime = new java.sql.Date(d.getTime());
    				return numTime;
    			} catch (Exception e) {
    				return null;
    			}
    		} else {
    			return null;
    		}
    	}

    	/**
    	 * 以指定的格式来格式化日期
    	 *
    	 * @param date
    	 *            Date
    	 * @param format
    	 *            String
    	 * @return String
    	 */
    	public static String formatDateByFormat(Date date, String format) {
    		String result = "";
    		if (date != null) {
    			try {
    				SimpleDateFormat sdf = new SimpleDateFormat(format);
    				result = sdf.format(date);
    			} catch (Exception ex) {
    				ex.printStackTrace();
    			}
    		}
    		return result;
    	}

    	/**
    	 * 常用的格式化日期
    	 *
    	 * @param date
    	 *            Date
    	 * @return String
    	 */
    	public static String formatDate(Date date) {
    		return formatDateByFormat(date, "yyyy-MM-dd");
    	}


    	/**
    	 *
    	 * @Title getBetweenDates
    	 * @Description TODO(获取两个日期之间的日期)
    	 * @author pengl
    	 * @date 2018年6月12日 下午6:18:08
    	 */
		public static List<String> getBetweenDates(Date start, Date end, SimpleDateFormat sdf, String dateFlag) {
    	    List<String> dateList = new ArrayList<String>();
    	    dateList.add(sdf.format(start));
    	    Calendar tempStart = Calendar.getInstance();
    	    tempStart.setTime(start);

    	    Calendar tempEnd = Calendar.getInstance();
    	    tempEnd.setTime(end);

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

    	/**
         *
         * @Title getLastDayOfMonth
         * @Description TODO(获得该月最后一天)
         * @author pengl
         * @date 2018年6月13日 上午11:06:17
         */
        public static String getLastDayOfMonth(int year,int month){
            Calendar cal = Calendar.getInstance();
            //设置年份
            cal.set(Calendar.YEAR, year);
            //设置月份
            cal.set(Calendar.MONTH, month-1);
            //获取某月最大天数
            int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            //设置日历中月份的最大天数
            cal.set(Calendar.DAY_OF_MONTH, lastDay);
            //格式化日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(cal.getTime());
        }

        /**
         *
         * @Title getDayBetween
         * @Description TODO(获取两个时间相差的天数)
         * @author pengl
         * @date 2018年6月28日 下午3:00:45
         */
    	public static long getDayBetween(Date startDate, Date endDate){
    		Calendar calst = Calendar.getInstance();
            Calendar caled = Calendar.getInstance();
            calst.setTime(startDate);
            caled.setTime(endDate);
            calst.set(Calendar.HOUR_OF_DAY, 0);
            calst.set(Calendar.MINUTE, 0);
            calst.set(Calendar.SECOND, 0);
            caled.set(Calendar.HOUR_OF_DAY, 0);
            caled.set(Calendar.MINUTE, 0);
            caled.set(Calendar.SECOND, 0);
            int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;
            return days;
    	}

    	/**
    	 *
    	 * @Title getHourAndMin
    	 * @Description TODO(获取两个时间相差的小时分钟)
    	 * @author pengl
    	 * @date 2018年8月22日 下午6:21:56
    	 */
    	public static String getHourAndMin(Date beginDate, Date endDate) {
    		long hh = 1000 * 60 * 60;
    		long mm = 1000 * 60;
    		long diff = endDate.getTime() - beginDate.getTime();
    		// 计算差多少小时
    		long hour = diff / hh;
    		// 计算差多少分钟
    		long min = diff % hh / mm;
    		return hour + "小时" + min + "分钟";
    	}

	/**
	 * 获取指定周的第一天（星期一）
	 * @param week
	 * @return
	 */
	public static Date getFirstDay0fWeek(int week) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.WEEK_OF_YEAR, week);
		//设置该周第一天为星期一
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return new Date(calendar.getTime().getTime());
	}

	/**
	 *
	 * @param date
	 * @param hour
	 * @return
	 */
	public static Date updateHour(Date date, int hour) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, hour);// 24小时制
		return cal.getTime();
	}

	public static Date getYearLast(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();
		return currYearLast;
	}



}
