package com.jf.entity;

import java.math.BigDecimal;

public class CombineOrderInvoiceCustom extends CombineOrderInvoice{

    private Integer combineOrderId;

    private String combineOrderCode;

    private BigDecimal orderPayAmount;

    @Override
    public Integer getCombineOrderId() {
        return combineOrderId;
    }

    @Override
    public void setCombineOrderId(Integer combineOrderId) {
        this.combineOrderId = combineOrderId;
    }

    public String getCombineOrderCode() {
        return combineOrderCode;
    }

    public void setCombineOrderCode(String combineOrderCode) {
        this.combineOrderCode = combineOrderCode;
    }

    public BigDecimal getOrderPayAmount() {
        return orderPayAmount;
    }

    public void setOrderPayAmount(BigDecimal orderPayAmount) {
        this.orderPayAmount = orderPayAmount;
    }
}