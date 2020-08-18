package com.jf.entity;

import java.util.ArrayList;
import java.util.List;


public class MchtSettleOrderCustom extends MchtSettleOrder{
	private List<Integer> orderDtlIds=new ArrayList<Integer>();
	private List<Integer> subDepositOrderIds=new ArrayList<Integer>();
	
	private List<Integer> customerServiceOrderIds=new ArrayList<Integer>();
	

	public List<Integer> getOrderDtlIds() {
		return orderDtlIds;
	}

	public void setOrderDtlIds(List<Integer> orderDtlIds) {
		this.orderDtlIds = orderDtlIds;
	}

	public List<Integer> getCustomerServiceOrderIds() {
		return customerServiceOrderIds;
	}

	public void setCustomerServiceOrderIds(List<Integer> customerServiceOrderIds) {
		this.customerServiceOrderIds = customerServiceOrderIds;
	}

	public List<Integer> getSubDepositOrderIds() {
		return subDepositOrderIds;
	}

	public void setSubDepositOrderIds(List<Integer> subDepositOrderIds) {
		this.subDepositOrderIds = subDepositOrderIds;
	}
	
	
	
}