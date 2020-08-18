package com.jf.entity;


public class ViolateOrderCustom extends ViolateOrder{
	/**
	 * 状态：1.未申诉
	 */
	public static String VIOLATE_STATUS_COMPLAIN_NO = "1";
	/**
	 * 状态：2.不可申诉
	 */
	public static String VIOLATE_STATUS_COMPLAIN_REFUSE = "2";
	/**
	 * 状态：3.申诉中
	 */
	public static String VIOLATE_STATUS_COMPLAIN_ING = "3";
	/**
	 * 状态：4.超期未申诉
	 */
	public static String VIOLATE_STATUS_NOCOMPLAIN_OVERTIME  = "4";
	/**
	 * 状态：5.申诉失败
	 */
	public static String VIOLATE_STATUS_COMPLAIN_FAIL = "5";
	/**
	 * 状态：6.申诉成功
	 */
	public static String VIOLATE_STATUS_COMPLAIN_SUCCESS = "6";
	
	private String violateTypeDesc;
	private String violateActionDesc;
	private String belongActivity;
	private String statusDesc;
	private String subOrderCode;
	
	public String getViolateTypeDesc() {
		return violateTypeDesc;
	}
	public void setViolateTypeDesc(String violateTypeDesc) {
		this.violateTypeDesc = violateTypeDesc;
	}
	public String getBelongActivity() {
		return belongActivity;
	}
	public void setBelongActivity(String belongActivity) {
		this.belongActivity = belongActivity;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getSubOrderCode() {
		return subOrderCode;
	}
	public void setSubOrderCode(String subOrderCode) {
		this.subOrderCode = subOrderCode;
	}
	public String getViolateActionDesc() {
		return violateActionDesc;
	}
	public void setViolateActionDesc(String violateActionDesc) {
		this.violateActionDesc = violateActionDesc;
	}
	
}
