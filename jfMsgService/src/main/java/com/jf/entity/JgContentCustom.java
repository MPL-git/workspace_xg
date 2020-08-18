package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年2月9日 下午6:03:59 
  * @version 1.0 
  * @parameter  
  * @return  
*/
public class JgContentCustom extends JgContent{
	
	private Integer couponNum;
	private Integer bizId;
	private String proStatus;
	private BigDecimal amount;
	private String expressNo;
	private String expressCompany;
	private String subOrderCode;
	private Date endDate;


	/**  
	 * couponNum  
	 * @return couponNum couponNum  
	 */
	public Integer getCouponNum() {
		return couponNum;
	}
	

	/**  
	 * couponNum  
	 * @param couponNum couponNum  
	 */
	public void setCouponNum(Integer couponNum) {
		this.couponNum = couponNum;
	}


	public Integer getBizId() {
		return bizId;
	}
	


	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	


	public String getProStatus() {
		return proStatus;
	}
	


	public void setProStatus(String proStatus) {
		this.proStatus = proStatus;
	}
	


	public BigDecimal getAmount() {
		return amount;
	}
	


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	


	public String getExpressNo() {
		return expressNo;
	}
	


	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	


	public String getExpressCompany() {
		return expressCompany;
	}
	


	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}
	


	public String getSubOrderCode() {
		return subOrderCode;
	}
	


	public void setSubOrderCode(String subOrderCode) {
		this.subOrderCode = subOrderCode;
	}
	


	public Date getEndDate() {
		return endDate;
	}
	


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}
