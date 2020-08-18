package com.jf.entity;

import java.math.BigDecimal;

public class ColumnPvStatisticalCustom extends ColumnPvStatistical {
    private String eachDay;//哪一天
    private BigDecimal addConversion;//加购转化
    private BigDecimal orderConversion;//下单转化
    private BigDecimal paymentConversion; //支付转化


    public String getEachDay() {
        return eachDay;
    }

    public void setEachDay(String eachDay) {
        this.eachDay = eachDay;
    }

    public BigDecimal getAddConversion() {
        return addConversion;
    }

    public void setAddConversion(BigDecimal addConversion) {
        this.addConversion = addConversion;
    }

    public BigDecimal getOrderConversion() {
        return orderConversion;
    }

    public void setOrderConversion(BigDecimal orderConversion) {
        this.orderConversion = orderConversion;
    }

    public BigDecimal getPaymentConversion() {
        return paymentConversion;
    }

    public void setPaymentConversion(BigDecimal paymentConversion) {
        this.paymentConversion = paymentConversion;
    }
    
}