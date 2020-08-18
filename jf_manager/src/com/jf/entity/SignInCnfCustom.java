package com.jf.entity;

import java.math.BigDecimal;

public class SignInCnfCustom extends SignInCnf {

	private BigDecimal sqBeginRate; // 首次签到
	private BigDecimal sqEndRate;
	private BigDecimal ljBeginRate; // 累计签到n天额外比例
	private BigDecimal ljEndRate;
	private BigDecimal syqBeginRate; // 首次好友签
	private BigDecimal syqEndRate;
	private BigDecimal xyqBeginRate; // 邀请新用户签到比例
	private BigDecimal xyqEndRate;
	private BigDecimal lyqBeginRate; // 邀请老用户签比例
	private BigDecimal lyqEndRate;

	public BigDecimal getSqBeginRate() {
		return sqBeginRate;
	}

	public void setSqBeginRate(BigDecimal sqBeginRate) {
		this.sqBeginRate = sqBeginRate;
	}

	public BigDecimal getSqEndRate() {
		return sqEndRate;
	}

	public void setSqEndRate(BigDecimal sqEndRate) {
		this.sqEndRate = sqEndRate;
	}

	public BigDecimal getLjBeginRate() {
		return ljBeginRate;
	}

	public void setLjBeginRate(BigDecimal ljBeginRate) {
		this.ljBeginRate = ljBeginRate;
	}

	public BigDecimal getLjEndRate() {
		return ljEndRate;
	}

	public void setLjEndRate(BigDecimal ljEndRate) {
		this.ljEndRate = ljEndRate;
	}

	public BigDecimal getSyqBeginRate() {
		return syqBeginRate;
	}

	public void setSyqBeginRate(BigDecimal syqBeginRate) {
		this.syqBeginRate = syqBeginRate;
	}

	public BigDecimal getSyqEndRate() {
		return syqEndRate;
	}

	public void setSyqEndRate(BigDecimal syqEndRate) {
		this.syqEndRate = syqEndRate;
	}

	public BigDecimal getXyqBeginRate() {
		return xyqBeginRate;
	}

	public void setXyqBeginRate(BigDecimal xyqBeginRate) {
		this.xyqBeginRate = xyqBeginRate;
	}

	public BigDecimal getXyqEndRate() {
		return xyqEndRate;
	}

	public void setXyqEndRate(BigDecimal xyqEndRate) {
		this.xyqEndRate = xyqEndRate;
	}

	public BigDecimal getLyqBeginRate() {
		return lyqBeginRate;
	}

	public void setLyqBeginRate(BigDecimal lyqBeginRate) {
		this.lyqBeginRate = lyqBeginRate;
	}

	public BigDecimal getLyqEndRate() {
		return lyqEndRate;
	}

	public void setLyqEndRate(BigDecimal lyqEndRate) {
		this.lyqEndRate = lyqEndRate;
	}

}
