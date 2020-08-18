package com.jf.entity;


public class OrderLogCustom extends OrderLog{
	/**
	 * 订单状态：0.待付款
	 */
	public static String ORDER_STATUS_WAIT_TPAY = "0";
	/**
	 * 订单状态：1.待发货
	 */
	public static String ORDER_STATUS_WAIT_DELIVERY = "1";
	/**
	 * 订单状态：2.待收货
	 */
	public static String ORDER_STATUS_WAIT_RECEIPT = "2";
	/**
	 * 订单状态：3.完成
	 */
	public static String ORDER_STATUS_COMPLETE = "3";
	/**
	 * 订单状态：4.取消(未付款客户取消,平台取消)
	 */
	public static String ORDER_STATUS_CANCLE = "4";
	
	/**
	 * 订单状态：5.关闭(退款后关闭)
	 */
	public static String ORDER_STATUS_CLOSE = "5";
}
