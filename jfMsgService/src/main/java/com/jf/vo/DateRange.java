package com.jf.vo;

import java.util.Date;

/**
 * @author luoyb
 * Created on 2020/1/15
 */
public class DateRange {

    private Date begin;
    private Date end;

    public static DateRange of(Date begin, Date end) {
        DateRange dateRange = new DateRange();
        dateRange.setBegin(begin);
        dateRange.setEnd(end);
        return dateRange;
    }

    private DateRange() {
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
