package com.jf.entity;


import java.math.BigDecimal;

public class ProductTypeCustom extends ProductType {
	
    private String parentName;
    
    private String zsproducttypename;


    //类目流量统计报表
    private Integer memberVisitorsNum;//会员访客数
    private Integer unmemberVisitorsNum;//非会员访客数
    private Integer memberPageView;//会员浏览数
    private Integer unmemberPageView;//非会员访客数
    private Integer addProductAmount;//加购数
    private Integer submitOrderAmount;//提交订单数
    private Integer paymentAmount;//付款件数
    private String addProductRate;//加购转化
    private String submitOrderRate;//下单转化
    private String paymentRate;//付款转化
    private BigDecimal payMoney;//销售金额


    public String getParentName() {
        return parentName;
    }
	public void setParentName(String parentName) {
        this.parentName = parentName == null ? null : parentName.trim();
    }
	public String getZsproducttypename() {
		return zsproducttypename;
	}
	public void setZsproducttypename(String zsproducttypename) {
		this.zsproducttypename = zsproducttypename;
	}

    public Integer getMemberVisitorsNum() {
        return memberVisitorsNum;
    }

    public void setMemberVisitorsNum(Integer memberVisitorsNum) {
        this.memberVisitorsNum = memberVisitorsNum;
    }

    public Integer getUnmemberVisitorsNum() {
        return unmemberVisitorsNum;
    }

    public void setUnmemberVisitorsNum(Integer unmemberVisitorsNum) {
        this.unmemberVisitorsNum = unmemberVisitorsNum;
    }

    public Integer getMemberPageView() {
        return memberPageView;
    }

    public void setMemberPageView(Integer memberPageView) {
        this.memberPageView = memberPageView;
    }

    public Integer getUnmemberPageView() {
        return unmemberPageView;
    }

    public void setUnmemberPageView(Integer unmemberPageView) {
        this.unmemberPageView = unmemberPageView;
    }

    public Integer getAddProductAmount() {
        return addProductAmount;
    }

    public void setAddProductAmount(Integer addProductAmount) {
        this.addProductAmount = addProductAmount;
    }

    public Integer getSubmitOrderAmount() {
        return submitOrderAmount;
    }

    public void setSubmitOrderAmount(Integer submitOrderAmount) {
        this.submitOrderAmount = submitOrderAmount;
    }

    public Integer getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Integer paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getAddProductRate() {
        return addProductRate;
    }

    public void setAddProductRate(String addProductRate) {
        this.addProductRate = addProductRate;
    }

    public String getSubmitOrderRate() {
        return submitOrderRate;
    }

    public void setSubmitOrderRate(String submitOrderRate) {
        this.submitOrderRate = submitOrderRate;
    }

    public String getPaymentRate() {
        return paymentRate;
    }

    public void setPaymentRate(String paymentRate) {
        this.paymentRate = paymentRate;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }
}