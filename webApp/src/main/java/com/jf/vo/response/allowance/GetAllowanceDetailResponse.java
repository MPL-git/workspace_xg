package com.jf.vo.response.allowance;

import com.google.common.collect.Lists;
import com.jf.entity.ProductType;
import com.jf.vo.response.ProductTypeView;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author luoyb
 * Created on 2020/5/25
 */
public class GetAllowanceDetailResponse {

    private Integer informationId; //津贴活动规则ID

    private BigDecimal allowanceBalance;
    private final List<String> allowanceDescList = Lists.newArrayList();
    private Integer activityAreaId;
    private boolean activityFinish; //活动已结束
    private String tipContent;
    private final List<AllowanceExchangeView> exchangeList = Lists.newArrayList();
    private final List<AllowanceProductTypeView> productTypeList = Lists.newArrayList();

    public Integer getActivityAreaId() {
        return activityAreaId;
    }

    public void setActivityAreaId(Integer activityAreaId) {
        this.activityAreaId = activityAreaId;
    }

    public String getTipContent() {
        return tipContent;
    }

    public void setTipContent(String tipContent) {
        this.tipContent = tipContent;
    }

    public boolean isActivityFinish() {
        return activityFinish;
    }

    public void setActivityFinish(boolean activityFinish) {
        this.activityFinish = activityFinish;
    }

    public List<String> getAllowanceDescList() {
        return allowanceDescList;
    }

    public List<AllowanceExchangeView> getExchangeList() {
        return exchangeList;
    }

    public BigDecimal getAllowanceBalance() {
        return allowanceBalance;
    }

    public void setAllowanceBalance(BigDecimal allowanceBalance) {
        this.allowanceBalance = allowanceBalance;
    }

    public Integer getInformationId() {
        return informationId;
    }

    public void setInformationId(Integer informationId) {
        this.informationId = informationId;
    }

    public List<AllowanceProductTypeView> getProductTypeList() {
        return productTypeList;
    }
}
