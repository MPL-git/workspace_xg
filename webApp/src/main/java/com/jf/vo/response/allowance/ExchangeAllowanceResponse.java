package com.jf.vo.response.allowance;

import java.math.BigDecimal;

/**
 * @author luoyb
 * Created on 2020/5/29
 */
public class ExchangeAllowanceResponse {

    private BigDecimal allowanceBalance;
    private Integer integral;

    private BigDecimal lastExchangeAllowance; //最近一次兑换
    private String exchangeDesc;

    public BigDecimal getAllowanceBalance() {
        return allowanceBalance;
    }

    public void setAllowanceBalance(BigDecimal allowanceBalance) {
        this.allowanceBalance = allowanceBalance;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public BigDecimal getLastExchangeAllowance() {
        return lastExchangeAllowance;
    }

    public void setLastExchangeAllowance(BigDecimal lastExchangeAllowance) {
        this.lastExchangeAllowance = lastExchangeAllowance;
    }

    public String getExchangeDesc() {
        return exchangeDesc;
    }

    public void setExchangeDesc(String exchangeDesc) {
        this.exchangeDesc = exchangeDesc;
    }
}
