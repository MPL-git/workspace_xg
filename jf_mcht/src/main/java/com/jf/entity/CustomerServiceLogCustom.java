package com.jf.entity;

import java.util.ArrayList;
import java.util.List;


public class CustomerServiceLogCustom extends CustomerServiceLog{
	/**
	 * 操作者类型：1.客户
	 */
	public static String OPERATOR_TYPE_CLIENT = "1";
	/**
	 * 操作者类型：2.商家
	 */
	public static String OPERATOR_TYPE_MCHT = "2";
	/**
	 * 操作者类型：3.系统
	 */
	public static String OPERATOR_TYPE_SYS = "3";
	
	private List<ServiceLogPicCustom> serviceLogPicCustoms = new ArrayList<ServiceLogPicCustom>();

	public List<ServiceLogPicCustom> getServiceLogPics() {
		return serviceLogPicCustoms;
	}

	public void setServiceLogPics(List<ServiceLogPicCustom> serviceLogPicCustoms) {
		this.serviceLogPicCustoms = serviceLogPicCustoms;
	}
	
}
