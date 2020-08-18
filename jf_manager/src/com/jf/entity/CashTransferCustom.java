package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CashTransferCustom extends CashTransfer {

	private Integer memberId;
	private String memberNick;
	private String realName;
	private String alipayAccount;
	private String withdrawTypeDesc;
	private BigDecimal amount;
	private Date applyCreateDate;
	private String statusDesc;
	
	public String getMemberNick() {
		return memberNick;
	}
	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getAlipayAccount() {
		return alipayAccount;
	}
	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}
	public String getWithdrawTypeDesc() {
		return withdrawTypeDesc;
	}
	public void setWithdrawTypeDesc(String withdrawTypeDesc) {
		this.withdrawTypeDesc = withdrawTypeDesc;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getApplyCreateDate() {
		return applyCreateDate;
	}
	public void setApplyCreateDate(Date applyCreateDate) {
		this.applyCreateDate = applyCreateDate;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	
}
