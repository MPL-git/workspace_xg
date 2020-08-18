package com.jf.entity;


public class CustomerServiceStatusLogCustom extends CustomerServiceStatusLog{
	/**
	 * 进度状态：A1.客户已申请（退款）
	 */
	public static String ORDER_HAS_APPLY_REFUND = "A1";
	/**
	 * 进度状态：A2.商家同意申请（退款）
	 */
	public static String ORDER_AGREE_APPLY_REFUND = "A2";
	/**
	 * 进度状态：A3.商家不同意申请（退款）
	 */
	public static String ORDER_DISAGREE_APPLY_REFUND = "A3";
	/**
	 * 进度状态：A4.平台已退款
	 */
	public static String ORDER_PLATFORM_HAS_REFUND = "A4";
	
	/**
	 * 进度状态：B1.客户已申请（退货）
	 */
	public static String ORDER_HAS_APPLY_RETURN_GOODS = "B1";
	
	/**
	 * 进度状态：B2.商家同意申请（退货）
	 */
	public static String ORDER_AGREE_APPLY_RETURN_GOODS = "B2";
	
	/**
	 * 进度状态：B3.商家不同意申请（退货）
	 */
	public static String ORDER_DISAGREE_APPLY_RETURN_GOODS = "B3";
	
	/**
	 * 进度状态：B4.客户已寄件(退货)
	 */
	public static String ORDER_HAS_SEND_RETURN_GOODS = "B4";
	
	/**
	 * 进度状态：B5.商家已同意退款(退货)
	 */
	public static String ORDER_HAS_ACCEPT_RETURN_GOODS = "B5";
	
	/**
	 * 进度状态：B6.商家不同意退款(退货)
	 */
	public static String ORDER_NOT_ACCEPT_RETURN_GOODS = "B6";
	
	/**
	 * 进度状态：B7.平台已退款(退货)
	 */
	public static String ORDER_PLATFORM_HAS_REFUND_RETURN_GOODS  = "B7";
	
	/**
	 * 进度状态：C1.客户已申请(换货)
	 */
	public static String ORDER_HAS_APPLY_EXCHANGE_GOODS  = "C1";
	
	/**
	 * 进度状态：C2.商家同意申请(换货)
	 */
	public static String ORDER_AGREE_APPLY_EXCHANGE_GOODS  = "C2";
	
	/**
	 * 进度状态：C3.商家不同意申请(换货)
	 */
	public static String ORDER_DISAGREE_APPLY_EXCHANGE_GOODS  = "C3";
	
	/**
	 * 进度状态：C4.客户已寄件(换货)
	 */
	public static String ORDER_CLIENT_HAS_SEND_EXCHANGE_GOODS  = "C4";
	
	
	/**
	 * 进度状态：C5.商家已接受(换货)
	 */
	public static String ORDER_HAS_ACCEPT_EXCHANGE_GOODS  = "C5";
	
	/**
	 * 进度状态：C6.商家不接受(换货)
	 */
	public static String ORDER_NOT_ACCEPT_EXCHANGE_GOODS  = "C6";
	
	/**
	 * 进度状态：C7.商家已寄件(换货)
	 */
	public static String ORDER_MCHT_HAS_SEND_EXCHANGE_GOODS  = "C7";
	
	/**
	 * 进度状态：D1.商家创建直赔(直赔)
	 */
	public static String ORDER_MCHT_CREATE_DIRECT_COMPENSATION  = "D1";
	
	/**
	 * 进度状态：D2.平台已退款(直赔)
	 */
	public static String ORDER_PLATFORM_HAS_REFUND_DIRECT_COMPENSATION  = "D2";
	
	
	
}