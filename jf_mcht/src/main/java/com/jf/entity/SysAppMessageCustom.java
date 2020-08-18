package com.jf.entity;



public class SysAppMessageCustom extends SysAppMessage{
	/**
	 * 消息类型：1.客户
	 */
	public static String TYPE_SYS = "1";
	/**
	 * 消息类型：2.交易物流
	 */
	public static String TYPE_WULIU = "2";
	
	/**
	 * 消息标题：1.商家已发货
	 */
	public static String TITLE_DELIVERY = "1";
	/**
	 * 消息标题：2.订单已签收
	 */
	public static String TITLE_ORDER_SIGN = "2";
	/**
	 * 消息标题：3.订单派送中
	 */
	public static String TITLE_ORDER_SEND = "3";
	/**
	 * 消息标题：4.退款申请
	 */
	public static String TITLE_REFUND_APPLY = "4";
	/**
	 * 消息标题：5.售后申请
	 */
	public static String TITLE_SERVICE_APPLY = "5";
	/**
	 * 消息标题：6.退货退款
	 */
	public static String TITLE_RETURNGOODS_REFUND = "6";
	/**
	 * 消息标题：7.换货提醒
	 */
	public static String TITLE_EXCHANGEGOODS_REMIND = "7";
	/**
	 * 消息标题：8.退款进度提醒
	 */
	public static String TITLE_REFUND_PROGRESS_REMIND = "8";
	/**
	 * 消息标题：9.投诉
	 */
	public static String TITLE_APPEAL = "9";
	/**
	 * 消息标题：80.开售提醒
	 */
	public static String TITLE_SALE_REMIND = "80";
	
	/**
	 * 业务类型：1.子订单
	 */
	public static String LINKTYPE_SUBORDER = "1";
	/**
	 * 业务类型：2.售后单
	 */
	public static String LINKTYPE_SERVICE_ORDER = "2";
	/**
	 * 业务类型：3.投诉单
	 */
	public static String LINKTYPE_APPEAL_ORDER = "3";
	/**
	 * 业务类型：91.会场
	 */
	public static String LINKTYPE_HALL = "91";
	/**
	 * 业务类型：92.活动
	 */
	public static String LINKTYPE_ACTIVITY = "92";
	
	
}
