package com.jf.entity;

import java.util.List;

public class CustomerServiceLogCustom extends CustomerServiceLog{
	private String operatorTypeDesc;
	private List<ServiceLogPic> serviceLogPics;

	public String getOperatorTypeDesc() {
		return operatorTypeDesc;
	}
	public void setOperatorTypeDesc(String operatorTypeDesc) {
		this.operatorTypeDesc = operatorTypeDesc;
	}
	public List<ServiceLogPic> getServiceLogPics() {
		return serviceLogPics;
	}
	public void setServiceLogPics(List<ServiceLogPic> serviceLogPics) {
		this.serviceLogPics = serviceLogPics;
	}
}