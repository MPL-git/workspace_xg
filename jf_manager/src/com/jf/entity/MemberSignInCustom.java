package com.jf.entity;

import java.math.BigDecimal;

public class MemberSignInCustom extends MemberSignIn {

	private String memberNick;
	private String memberMobile;
	private BigDecimal memberBalance;
	private Integer memberAccountId;
	private BigDecimal oneAmountSum;
	private BigDecimal twoAmountSum;
	private BigDecimal threeAmountSum;
	private BigDecimal fourAmountSum;
	private BigDecimal fiveAmountSum;
	private BigDecimal sixAmountSum;
	private BigDecimal amountSum;
	private Integer expenseCount;
	private Integer twoAmountCount;

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

	public BigDecimal getMemberBalance() {
		return memberBalance;
	}

	public void setMemberBalance(BigDecimal memberBalance) {
		this.memberBalance = memberBalance;
	}

	public BigDecimal getTwoAmountSum() {
		return twoAmountSum;
	}

	public void setTwoAmountSum(BigDecimal twoAmountSum) {
		this.twoAmountSum = twoAmountSum;
	}

	public BigDecimal getOneAmountSum() {
		return oneAmountSum;
	}

	public void setOneAmountSum(BigDecimal oneAmountSum) {
		this.oneAmountSum = oneAmountSum;
	}

	public BigDecimal getFiveAmountSum() {
		return fiveAmountSum;
	}

	public void setFiveAmountSum(BigDecimal fiveAmountSum) {
		this.fiveAmountSum = fiveAmountSum;
	}

	public Integer getExpenseCount() {
		return expenseCount;
	}

	public void setExpenseCount(Integer expenseCount) {
		this.expenseCount = expenseCount;
	}

	public Integer getMemberAccountId() {
		return memberAccountId;
	}

	public void setMemberAccountId(Integer memberAccountId) {
		this.memberAccountId = memberAccountId;
	}

	public BigDecimal getThreeAmountSum() {
		return threeAmountSum;
	}

	public void setThreeAmountSum(BigDecimal threeAmountSum) {
		this.threeAmountSum = threeAmountSum;
	}

	public BigDecimal getFourAmountSum() {
		return fourAmountSum;
	}

	public void setFourAmountSum(BigDecimal fourAmountSum) {
		this.fourAmountSum = fourAmountSum;
	}

	public BigDecimal getSixAmountSum() {
		return sixAmountSum;
	}

	public void setSixAmountSum(BigDecimal sixAmountSum) {
		this.sixAmountSum = sixAmountSum;
	}

	public BigDecimal getAmountSum() {
		return amountSum;
	}

	public void setAmountSum(BigDecimal amountSum) {
		this.amountSum = amountSum;
	}

	public Integer getTwoAmountCount() {
		return twoAmountCount;
	}

	public void setTwoAmountCount(Integer twoAmountCount) {
		this.twoAmountCount = twoAmountCount;
	}

}
