package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ShopownerCustom extends Shopowner{
	private String nick;
	private Integer laNewCount;
	private BigDecimal fenrunTotal;
	private Date payDate;
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public Integer getLaNewCount() {
		return laNewCount;
	}
	public void setLaNewCount(Integer laNewCount) {
		this.laNewCount = laNewCount;
	}
	public BigDecimal getFenrunTotal() {
		return fenrunTotal;
	}
	public void setFenrunTotal(BigDecimal fenrunTotal) {
		this.fenrunTotal = fenrunTotal;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
}