package com.jf.vo.response.allowance;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author luoyb
 * Created on 2020/5/25
 */
public class GetAllowanceInfoResponse {

    private Integer allowanceInfoId;
    private BigDecimal allowanceBalance;
    private Integer integral;

    private BigDecimal lastExchangeAllowance; //最近一次兑换
    private String exchangeDesc;
    private String areaAllowanceDesc;

    private final List<AllowanceExchangeView> exchangeList = Lists.newArrayList();

    public Integer getAllowanceInfoId() {
        return allowanceInfoId;
    }

    public void setAllowanceInfoId(Integer allowanceInfoId) {
        this.allowanceInfoId = allowanceInfoId;
    }

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

    public String getAreaAllowanceDesc() {
        return areaAllowanceDesc;
    }

    public void setAreaAllowanceDesc(String areaAllowanceDesc) {
        this.areaAllowanceDesc = areaAllowanceDesc;
    }

    public List<AllowanceExchangeView> getExchangeList() {
        return exchangeList;
    }
}
