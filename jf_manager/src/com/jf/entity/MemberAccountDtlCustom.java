package com.jf.entity;

import java.math.BigDecimal;

public class MemberAccountDtlCustom extends MemberAccountDtl {

	private String memberNick;
	private String memberMobile;
	private String bizTypeDesc;

	private String tallyModes;//收支类型
	private String bizTypes;//来源/去向
	
	private Integer memberId;//分润会员ID
	private String nick;//分润会员昵称
	private Integer newMemberId;//拉新会员ID
	private String newNick;//拉新会员昵称
	private Integer productId;//商品ID
	private String productName;//商品名称
	private String subOrderCode;//子订单号
	private BigDecimal payAmount;//实付金额
	private BigDecimal currentBalance;//当前现金余额（含提现中金额）
    private Integer subOrderId;
    
    private String eachDay;//日期
    private BigDecimal drawBackAmount;//退款分润（元）
    private BigDecimal first1;//期初余额（元）
    private BigDecimal orderDistribution;//订单分润（元）
    private BigDecimal inviteNewAwards;//邀新奖励（元）
    private BigDecimal cashWithdrawals;//提现支出（元）
    private BigDecimal end1;//期末余额（元）
    
	public BigDecimal getDrawBackAmount() {
		return drawBackAmount;
	}

	public void setDrawBackAmount(BigDecimal drawBackAmount) {
		this.drawBackAmount = drawBackAmount;
	}

	public BigDecimal getFirst1() {
		return first1;
	}

	public void setFirst1(BigDecimal first1) {
		this.first1 = first1;
	}

	public BigDecimal getEnd1() {
		return end1;
	}

	public void setEnd1(BigDecimal end1) {
		this.end1 = end1;
	}

	public Integer getNewMemberId() {
		return newMemberId;
	}

	public void setNewMemberId(Integer newMemberId) {
		this.newMemberId = newMemberId;
	}

	public String getNewNick() {
		return newNick;
	}

	public void setNewNick(String newNick) {
		this.newNick = newNick;
	}

	public String getEachDay() {
		return eachDay;
	}

	public void setEachDay(String eachDay) {
		this.eachDay = eachDay;
	}
	public BigDecimal getOrderDistribution() {
		return orderDistribution;
	}

	public void setOrderDistribution(BigDecimal orderDistribution) {
		this.orderDistribution = orderDistribution;
	}

	public BigDecimal getInviteNewAwards() {
		return inviteNewAwards;
	}

	public void setInviteNewAwards(BigDecimal inviteNewAwards) {
		this.inviteNewAwards = inviteNewAwards;
	}

	public BigDecimal getCashWithdrawals() {
		return cashWithdrawals;
	}

	public void setCashWithdrawals(BigDecimal cashWithdrawals) {
		this.cashWithdrawals = cashWithdrawals;
	}

	public Integer getSubOrderId() {
		return subOrderId;
	}

	public void setSubOrderId(Integer subOrderId) {
		this.subOrderId = subOrderId;
	}

	public String getTallyModes() {
		return tallyModes;
	}

	public void setTallyModes(String tallyModes) {
		this.tallyModes = tallyModes;
	}

	public String getBizTypes() {
		return bizTypes;
	}

	public void setBizTypes(String bizTypes) {
		this.bizTypes = bizTypes;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSubOrderCode() {
		return subOrderCode;
	}

	public void setSubOrderCode(String subOrderCode) {
		this.subOrderCode = subOrderCode;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
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

	public String getBizTypeDesc() {
		return bizTypeDesc;
	}

	public void setBizTypeDesc(String bizTypeDesc) {
		this.bizTypeDesc = bizTypeDesc;
	}

}
