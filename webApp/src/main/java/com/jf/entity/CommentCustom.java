package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年6月29日 下午4:28:14 
  * @version 1.0 
  * @parameter  
  * @return  
*/
public class CommentCustom extends Comment{
	/**
	 * sku商品图片
	 */
	private String skuPic;
	/**
	 * 商品sku描述
	 */
	private String productPropDesc;
	/**
	 * 确认收货时间
	 */
	private Date completeDate;
	/**
	 * 用户图片
	 */
	private String memberPic;
	/**
	 * 会员昵称
	 */
	private String memberNick;
	/**
	 * 商家平均评分
	 */
	private BigDecimal mchtAvgScore;
	/**
	 * 物流平均评分
	 */
	private BigDecimal wuliuAvgScore;
	/**
	 * 商品平均评分
	 */
	private BigDecimal productAvgScore;
	/**
	 * 是否允许修改评论(0 可设置 1 允许 2 已修改)
	 */
	private String isAllowModifyComment;
	
	/**  
	 * sku商品图片  
	 * @return skuPic sku商品图片  
	 */
	public String getSkuPic() {
		return skuPic;
	}
	
	/**  
	 * sku商品图片  
	 * @param skuPic sku商品图片  
	 */
	public void setSkuPic(String skuPic) {
		this.skuPic = skuPic;
	}
	
	/**  
	 * 商品sku描述  
	 * @return productPropDesc 商品sku描述  
	 */
	public String getProductPropDesc() {
		return productPropDesc;
	}
	
	/**  
	 * 商品sku描述  
	 * @param productPropDesc 商品sku描述  
	 */
	public void setProductPropDesc(String productPropDesc) {
		this.productPropDesc = productPropDesc;
	}

	/**  
	 * 确认收货时间  
	 * @return completeDate 确认收货时间  
	 */
	public Date getCompleteDate() {
		return completeDate;
	}
	

	/**  
	 * 确认收货时间  
	 * @param completeDate 确认收货时间  
	 */
	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	public String getMemberPic() {
		return memberPic;
	}
	

	public void setMemberPic(String memberPic) {
		this.memberPic = memberPic;
	}
	

	public String getMemberNick() {
		return memberNick;
	}
	

	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}
	

	public BigDecimal getMchtAvgScore() {
		return mchtAvgScore;
	}
	

	public void setMchtAvgScore(BigDecimal mchtAvgScore) {
		this.mchtAvgScore = mchtAvgScore;
	}
	

	public BigDecimal getWuliuAvgScore() {
		return wuliuAvgScore;
	}
	

	public void setWuliuAvgScore(BigDecimal wuliuAvgScore) {
		this.wuliuAvgScore = wuliuAvgScore;
	}
	

	public BigDecimal getProductAvgScore() {
		return productAvgScore;
	}
	

	public void setProductAvgScore(BigDecimal productAvgScore) {
		this.productAvgScore = productAvgScore;
	}

	public String getIsAllowModifyComment() {
		return isAllowModifyComment;
	}
	

	public void setIsAllowModifyComment(String isAllowModifyComment) {
		this.isAllowModifyComment = isAllowModifyComment;
	}
	
}
