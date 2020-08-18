package com.jf.entity;

import java.math.BigDecimal;

public class WithdrawOrderCustom extends WithdrawOrder {

	private String weixinId;
	private String couponName;
	private String statusDesc;
	private String withdrawTypeDesc;
	private String memberNick;
	private String memberMobile;
	private String memberAccountBalance;
	private Integer combineOrderCount;
	private Integer withdrawOrderCount;
	private Integer amountSum;
	private String withdrawCnfName;
	private BigDecimal memberAccountFreeze;
 
	private String nick;
	private String yyName;
	private String cwName;
	private String branchName;
	private String cityAreaName;
	private String provinceAreaName;

	public String getCityAreaName() {
		return cityAreaName;
	}

	public void setCityAreaName(String cityAreaName) {
		this.cityAreaName = cityAreaName;
	}

	public String getProvinceAreaName() {
		return provinceAreaName;
	}

	public void setProvinceAreaName(String provinceAreaName) {
		this.provinceAreaName = provinceAreaName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getYyName() {
		return yyName;
	}

	public void setYyName(String yyName) {
		this.yyName = yyName;
	}

	public String getCwName() {
		return cwName;
	}

	public void setCwName(String cwName) {
		this.cwName = cwName;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getWeixinId() {
		return weixinId;
	}

	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getWithdrawTypeDesc() {
		return withdrawTypeDesc;
	}

	public void setWithdrawTypeDesc(String withdrawTypeDesc) {
		this.withdrawTypeDesc = withdrawTypeDesc;
	}

	public String getMemberNick() {
		return memberNick;
	}

	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}

	public String getMemberMobile() {
		return memberMobile;
	}

	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}

	public String getMemberAccountBalance() {
		return memberAccountBalance;
	}

	public void setMemberAccountBalance(String memberAccountBalance) {
		this.memberAccountBalance = memberAccountBalance;
	}

	public Integer getCombineOrderCount() {
		return combineOrderCount;
	}

	public void setCombineOrderCount(Integer combineOrderCount) {
		this.combineOrderCount = combineOrderCount;
	}

	public Integer getWithdrawOrderCount() {
		return withdrawOrderCount;
	}

	public void setWithdrawOrderCount(Integer withdrawOrderCount) {
		this.withdrawOrderCount = withdrawOrderCount;
	}

	public Integer getAmountSum() {
		return amountSum;
	}

	public void setAmountSum(Integer amountSum) {
		this.amountSum = amountSum;
	}

	public String getWithdrawCnfName() {
		return withdrawCnfName;
	}

	public void setWithdrawCnfName(String withdrawCnfName) {
		this.withdrawCnfName = withdrawCnfName;
	}

	public BigDecimal getMemberAccountFreeze() {
		return memberAccountFreeze;
	}

	public void setMemberAccountFreeze(BigDecimal memberAccountFreeze) {
		this.memberAccountFreeze = memberAccountFreeze;
	}

}
