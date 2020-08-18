package com.jf.service.jixin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述: 日期操作工具
 * @Version 0.0.0
 * @Date 2019/3/12
 */
public class DateUtil {
    public static enum DateType{
        /**
         * 时间戳格式：yyyyMMddHHmmssSS
         */
        TIMESTAMP_WITH_MILLISECOND("yyyyMMddHHmmssSS"),
        /**
         * 时间戳格式：yyyyMMddHHmmss
         */
        TIMESTAMP_NO_MILLISECOND("yyyyMMddHHmmss"),
        TIMESTAMP_FULL("yyyyMMddHHmmssSSS");
        private String pattern;
        DateType(String pattern){
            this.pattern = pattern;
        }
    }

    /**
     * 日期格式化
     * @param date
     * @param dateType
     * @return
     */
    public static String format(Date date, DateType dateType){
        return new SimpleDateFormat(dateType.pattern).format(date);
    }
}
