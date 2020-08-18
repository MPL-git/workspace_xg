package com.jf.common.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class WeekHelper {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
  
    /** 
     * @author yjc
     * @param startDate 
     * @param endDate 
     * @param args the command line arguments 
     * @return 
     */  
    public static List<Week> getWeekSplit(Date startDate, Date endDate) {  
        ArrayList<Week> WeekList = new ArrayList<Week>();
        Calendar startCal = Calendar.getInstance();  
        startCal.setTime(startDate);  
        Calendar endCal = Calendar.getInstance();  
        endCal.setTime(endDate);  
        int startYear = startCal.get(Calendar.YEAR);  
        while (startCal.compareTo(endCal) < 0) {  
            int endYear = startCal.get(Calendar.YEAR);  
            if (startYear < endYear) {  
                startYear = endYear;  
            }  
            Week everyWeek = new Week();  
            everyWeek.setWeekBegin(sdf.format(startCal.getTime()));  
            startCal.add(Calendar.DATE, 6);  
            everyWeek.setWeekEnd(sdf.format(startCal.getTime()));  
            startCal.add(Calendar.DATE, 1);  
            WeekList.add(everyWeek);  
        }  
        Iterator<Week> iter = WeekList.iterator();  
        System.out.println("开始打印");  
        while (iter.hasNext()) {  
            Week everyweek = iter.next();  
            //System.out.println("开始时间：" + everyweek.getWeekBegin() + "\t结束时间" + everyweek.getWeekEnd());  
        }  
        return WeekList;  
    }  
  
    public static void main(String[] args) {  
        String start = "2017-08-17";  
        String end = "2017-09-20";  
        try {  
            Date startDate = sdf.parse(start);  
            Date endDate = sdf.parse(end);  
            getWeekSplit(startDate, endDate);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    } 
}
	 