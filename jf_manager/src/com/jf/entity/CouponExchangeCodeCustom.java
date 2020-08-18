package com.jf.entity;


public class CouponExchangeCodeCustom extends CouponExchangeCode {

	private String exchangeLimitDesc; //兑换限制
	private String channelDesc; //推广渠道
	private String isExchangeDesc; //是否兑换
	private String isAllocationDesc; //是否分配
	private String memberCouponStatus; //是否使用
	private String memberCouponStatusDesc; //是否使用
	
	public String getChannelDesc() {
		return channelDesc;
	}
	public void setChannelDesc(String channelDesc) {
		this.channelDesc = channelDesc;
	}
	public String getIsExchangeDesc() {
		return isExchangeDesc;
	}
	public void setIsExchangeDesc(String isExchangeDesc) {
		this.isExchangeDesc = isExchangeDesc;
	}
	public String getIsAllocationDesc() {
		return isAllocationDesc;
	}
	public void setIsAllocationDesc(String isAllocationDesc) {
		this.isAllocationDesc = isAllocationDesc;
	}
	public String getMemberCouponStatus() {
		return memberCouponStatus;
	}
	public void setMemberCouponStatus(String memberCouponStatus) {
		this.memberCouponStatus = memberCouponStatus;
	}
	public String getMemberCouponStatusDesc() {
		return memberCouponStatusDesc;
	}
	public void setMemberCouponStatusDesc(String memberCouponStatusDesc) {
		this.memberCouponStatusDesc = memberCouponStatusDesc;
	}
	public String getExchangeLimitDesc() {
		return exchangeLimitDesc;
	}
	public void setExchangeLimitDesc(String exchangeLimitDesc) {
		this.exchangeLimitDesc = exchangeLimitDesc;
	}
	
	
	
	
}