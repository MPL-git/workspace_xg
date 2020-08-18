package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;


public class MchtDepositDtlCustom extends MchtDepositDtl {
	
    private String mchtCode;
    
    private String companyName;

    private String orderCode;
    
    private String violateTypeDesc;
    
    private String subOrderCode;
    
    private Date violateDate;
    
    private Integer subOrderId;
    
    private String txnTypeDesc;
    
    private String typeSubDesc;
    
    private String summary;
    
    private BigDecimal first;
    
    private BigDecimal cashPayment;
    
    private BigDecimal paymentOfGoods;
    
    private BigDecimal violateMoney;
    
    private BigDecimal refundDeposit;
    
    private BigDecimal appealMoney;
    
    private BigDecimal afterSaleMoney;
    
    private BigDecimal last;
    
    private BigDecimal totalAmt;
    
    private BigDecimal needPay;
    
    private String eachDay;
    
    private Integer mchtId;
    
    private Integer mchtDepositid;

    private String violateOrderCode;

	public String getViolateOrderCode() {
		return violateOrderCode;
	}

	public void setViolateOrderCode(String violateOrderCode) {
		this.violateOrderCode = violateOrderCode;
	}

	public String getMchtCode() {
        return mchtCode;
    }

    public void setMchtCode(String mchtCode) {
        this.mchtCode = mchtCode == null ? null : mchtCode.trim();
    }
    
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getViolateTypeDesc() {
		return violateTypeDesc;
	}

	public void setViolateTypeDesc(String violateTypeDesc) {
		this.violateTypeDesc = violateTypeDesc;
	}

	public String getSubOrderCode() {
		return subOrderCode;
	}

	public void setSubOrderCode(String subOrderCode) {
		this.subOrderCode = subOrderCode;
	}

	public Date getViolateDate() {
		return violateDate;
	}

	public void setViolateDate(Date violateDate) {
		this.violateDate = violateDate;
	}

	public Integer getSubOrderId() {
		return subOrderId;
	}

	public void setSubOrderId(Integer subOrderId) {
		this.subOrderId = subOrderId;
	}

	public String getTxnTypeDesc() {
		return txnTypeDesc;
	}

	public void setTxnTypeDesc(String txnTypeDesc) {
		this.txnTypeDesc = txnTypeDesc;
	}

	public String getTypeSubDesc() {
		return typeSubDesc;
	}

	public void setTypeSubDesc(String typeSubDesc) {
		this.typeSubDesc = typeSubDesc;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public BigDecimal getFirst() {
		return first;
	}

	public void setFirst(BigDecimal first) {
		this.first = first;
	}

	public BigDecimal getCashPayment() {
		return cashPayment;
	}

	public void setCashPayment(BigDecimal cashPayment) {
		this.cashPayment = cashPayment;
	}

	public BigDecimal getPaymentOfGoods() {
		return paymentOfGoods;
	}

	public void setPaymentOfGoods(BigDecimal paymentOfGoods) {
		this.paymentOfGoods = paymentOfGoods;
	}

	public BigDecimal getViolateMoney() {
		return violateMoney;
	}

	public void setViolateMoney(BigDecimal violateMoney) {
		this.violateMoney = violateMoney;
	}

	public BigDecimal getRefundDeposit() {
		return refundDeposit;
	}

	public void setRefundDeposit(BigDecimal refundDeposit) {
		this.refundDeposit = refundDeposit;
	}

	public BigDecimal getAppealMoney() {
		return appealMoney;
	}

	public void setAppealMoney(BigDecimal appealMoney) {
		this.appealMoney = appealMoney;
	}

	public BigDecimal getLast() {
		return last;
	}

	public void setLast(BigDecimal last) {
		this.last = last;
	}

	public BigDecimal getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	public BigDecimal getNeedPay() {
		return needPay;
	}

	public void setNeedPay(BigDecimal needPay) {
		this.needPay = needPay;
	}

	public String getEachDay() {
		return eachDay;
	}

	public void setEachDay(String eachDay) {
		this.eachDay = eachDay;
	}

	public Integer getMchtId() {
		return mchtId;
	}

	public void setMchtId(Integer mchtId) {
		this.mchtId = mchtId;
	}

	public Integer getMchtDepositid() {
		return mchtDepositid;
	}

	public void setMchtDepositid(Integer mchtDepositid) {
		this.mchtDepositid = mchtDepositid;
	}

	public BigDecimal getAfterSaleMoney() {
		return afterSaleMoney;
	}

	public void setAfterSaleMoney(BigDecimal afterSaleMoney) {
		this.afterSaleMoney = afterSaleMoney;
	}
	
}