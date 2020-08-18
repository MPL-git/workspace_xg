package com.jf.entity;

import java.math.BigDecimal;

public class TmpOrderDtl {
    private Integer orderDtlId;

    private BigDecimal payAmount;

    private BigDecimal platAmount;

    private BigDecimal integralAmount;

    private Integer subOrderId;

    private Integer combineOrderId;

    public Integer getOrderDtlId() {
        return orderDtlId;
    }

    public void setOrderDtlId(Integer orderDtlId) {
        this.orderDtlId = orderDtlId;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getPlatAmount() {
        return platAmount;
    }

    public void setPlatAmount(BigDecimal platAmount) {
        this.platAmount = platAmount;
    }

    public BigDecimal getIntegralAmount() {
        return integralAmount;
    }

    public void setIntegralAmount(BigDecimal integralAmount) {
        this.integralAmount = integralAmount;
    }

    public Integer getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(Integer subOrderId) {
        this.subOrderId = subOrderId;
    }

    public Integer getCombineOrderId() {
        return combineOrderId;
    }

    public void setCombineOrderId(Integer combineOrderId) {
        this.combineOrderId = combineOrderId;
    }
}