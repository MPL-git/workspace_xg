package com.jf.common.ext.util;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateTimeUtil {

	private static final Log logger = LogFactory.getLog(DateTimeUtil.class);

	/**
	 * 切分一段日期，按天切
     */
	public static List<Date> segmentDateWithDay(Date startDate, Date endDate, int segment){
		int days = daysBetween(startDate, endDate) + 1;
		//System.out.println("days:" + days);
		int step = 1;

		if(days > segment){
			step = days / segment;
			if(days < segment * 2){
				segment = segment * 2;
			}
		}
		//System.out.println("step:" + step);

		List<Date> dateList = new ArrayList<Date>();
		Date date;
		for(int i = 0; i <= segment; i++){
			date = plusDays(startDate, i*step);
			if(!endDate.before(date)){
				dateList.add(date);
			}
		}
		if(dateList.get(dateList.size()-1).before(endDate)){
			dateList.add(endDate);
		}
		return dateList;
	}

	/**
	 * 计算两个日期之间相差的天数
	 * @param smdate 较小的时间
	 * @param bdate  较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate,Date bdate){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			smdate=sdf.parse(sdf.format(smdate));
			bdate=sdf.parse(sdf.format(bdate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days=(time2-time1)/(1000*3600*24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 指定日期加上天数后的日期
	 * @param date 创建时间
	 * @param days 为增加的天数
	 * @return
	 */
	public static Date plusDays(Date date, int days){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.DATE, days);// 增加的天数
		return ca.getTime();
	}

	/**
	 * 指定日期加上月数后的日期
	 * @param date 创建时间
	 * @param months 为增加的月数
	 * @return
	 */
	public static Date plusMonths(Date date, int months){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.MONTH, months);
		return ca.getTime();
	}

	public static Date plusYears(Date date, int years){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.YEAR, years);
		return ca.getTime();
	}

	public static String getFirstdayOfYear(Date date){
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		Calendar c=Calendar.getInstance(Locale.CHINA);
		c.setTime(date);
		c.add(Calendar.YEAR, -1);//拨回去年
		c.set(Calendar.DAY_OF_YEAR,c.getActualMinimum(Calendar.DAY_OF_YEAR));//最初一天
		return f.format(c.getTime());
	}

	public static String getLastdayOfYear(Date date)  {
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		Calendar c=Calendar.getInstance(Locale.CHINA);
		c.setTime(date);
		c.add(Calendar.YEAR, -1);//拨回去年
		c.set(Calendar.DAY_OF_YEAR,c.getActualMaximum(Calendar.DAY_OF_YEAR));//最后一天
		return f.format(c.getTime());
	}

	/**
	 * 获取给定月的第一天
	 */
	public static Date getFirstdayOfMonth(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.set(Calendar.DAY_OF_MONTH, 1);

		ca.set(Calendar.HOUR_OF_DAY,0);
		ca.set(Calendar.MINUTE,0);
		ca.set(Calendar.SECOND,0);
		ca.set(Calendar.MILLISECOND,0);
		return ca.getTime();
	}

	/**
	 * 获取给定月的最后一天
	 */
	public static Date getLastdayOfMonth(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.set(Calendar.DATE, ca.getActualMaximum(Calendar.DATE));

		ca.set(Calendar.HOUR_OF_DAY,23);
		ca.set(Calendar.MINUTE,59);
		ca.set(Calendar.SECOND,59);
		ca.set(Calendar.MILLISECOND,999);
		return ca.getTime();
	}

	public static Date getStartTimeOfDate(Date date){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
		return calendar.getTime();
	}

	public static Date getEndTimeOfDate(Date date){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY,23);
		calendar.set(Calendar.MINUTE,59);
		calendar.set(Calendar.SECOND,59);
		calendar.set(Calendar.MILLISECOND,999);
		return calendar.getTime();
	}

	/**
	 * 日期格式化
	 */
	public static String formatDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * 日期格式化
	 */
	public static String formatDateTime(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * 日期格式化
	 */
	public static String formatDate(Date date, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 日期格式化
	 */
	public static Date parseDate(String date, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 日期格式化
	 */
	public static Date parseDate(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 判断时间是不是今天
	 * @param date
	 * @return    是返回true，不是返回false
	 */
	public static boolean isToday(Date date) {
		//当前时间
		Date now = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		//获取今天的日期
		String nowDay = sf.format(now);
		//对比的时间
		String day = sf.format(date);
		return day.equals(nowDay);



	}

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1=sdf.parse("2012-09-01 20:10:10");
		Date d2=sdf.parse("2012-09-02 00:00:00");
		System.out.println(daysBetween(d1,d2));

		System.out.println("当前时间：" + sdf.format(d1));
		System.out.println(sdf.format(plusDays(d1, -3)));

		System.out.println("一个月最后一天：" + sdf.format(getLastdayOfMonth(d1)));
	}

}
