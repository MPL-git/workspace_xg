package com.jf.entity;

import java.math.BigDecimal;

public class CutPriceOrderDtlCustom extends CutPriceOrderDtl {

	private String memberNick; //昵称
	private String memberWeixinHead; //微信头像
	private Integer cutPriceOrderDtlCount; //今日参与砍价次数
	private BigDecimal amountSum; //累计砍价金额
	private Integer superCutPriceOrderDtlCount; //累计助力次数
	private Integer xfCount; //消费次数
	private Integer ljkjCount; //累计砍价次数
	private Integer fqkjCount; //发起砍价次数
	private Integer cgkjCount; //成功砍价次数

	public String getMemberNick() {
		return memberNick;
	}

	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}

	public Integer getCutPriceOrderDtlCount() {
		return cutPriceOrderDtlCount;
	}

	public void setCutPriceOrderDtlCount(Integer cutPriceOrderDtlCount) {
		this.cutPriceOrderDtlCount = cutPriceOrderDtlCount;
	}

	public BigDecimal getAmountSum() {
		return amountSum;
	}

	public void setAmountSum(BigDecimal amountSum) {
		this.amountSum = amountSum;
	}

	public String getMemberWeixinHead() {
		return memberWeixinHead;
	}

	public void setMemberWeixinHead(String memberWeixinHead) {
		this.memberWeixinHead = memberWeixinHead;
	}

	public Integer getSuperCutPriceOrderDtlCount() {
		return superCutPriceOrderDtlCount;
	}

	public void setSuperCutPriceOrderDtlCount(Integer superCutPriceOrderDtlCount) {
		this.superCutPriceOrderDtlCount = superCutPriceOrderDtlCount;
	}

	public Integer getXfCount() {
		return xfCount;
	}

	public void setXfCount(Integer xfCount) {
		this.xfCount = xfCount;
	}

	public Integer getLjkjCount() {
		return ljkjCount;
	}

	public void setLjkjCount(Integer ljkjCount) {
		this.ljkjCount = ljkjCount;
	}

	public Integer getFqkjCount() {
		return fqkjCount;
	}

	public void setFqkjCount(Integer fqkjCount) {
		this.fqkjCount = fqkjCount;
	}

	public Integer getCgkjCount() {
		return cgkjCount;
	}

	public void setCgkjCount(Integer cgkjCount) {
		this.cgkjCount = cgkjCount;
	}
	

}
