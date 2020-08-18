package com.jf.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.constant.Const;

@Service
@Transactional
public class EnumsNameService {

	public String getCustomerServiceOrderStatusName(String customerServiceOrderStatus, String isGive, String serviceType, String subOrderStatus, String system, Integer version) {
		String statusName = "";
		if(!isGive.equals("1")){
			if(customerServiceOrderStatus.equals("0")){
				if(serviceType.equals("A")){
					statusName = "退款中";
				}else if(serviceType.equals("B")){
					statusName = "退货中";
				}else if(serviceType.equals("C")){
					statusName = "换货中";
				}else if(serviceType.equals("D")){
					statusName = "直赔中";
				}
			}
			if((system.equals(Const.ANDROID) && version <= 49) || (system.equals(Const.IOS) && version <= 52)){
				if(customerServiceOrderStatus.equals("1")){
					if(serviceType.equals("A")){
						statusName = "退款已完成";
					}else if(serviceType.equals("B")){
						statusName = "退货已完成";
					}else if(serviceType.equals("C")){
						statusName = "换货已完成";
						if(!subOrderStatus.equals(Const.ORDER_STATUS_SUCCESS)){
							statusName = "";
						}
					}else if(serviceType.equals("D")){
						statusName = "直赔已完成";
					}
				}
			}else{
				if(customerServiceOrderStatus.equals("1")){
					if(serviceType.equals("A")){
						statusName = "退款已完成";
					}else if(serviceType.equals("B")){
						statusName = "退货已完成";
					}else if(serviceType.equals("C")){
						statusName = "换货已完成";
					}else if(serviceType.equals("D")){
						statusName = "直赔已完成";
					}
				}
				if(customerServiceOrderStatus.equals("2")){
					statusName = "商家已拒绝";
				}
			}
		}
		return statusName;
	}
	
	public String getSubOrderStatusName(String subOrderStatus, String isComment) {
		String subOrderStatusName = "";
		if(Const.ORDER_STATUS_NOT_PAID.equals(subOrderStatus)){
			subOrderStatusName = "待付款";
		}else if(Const.ORDER_STATUS_PAID.equals(subOrderStatus)){
			subOrderStatusName = "待发货";
		}else if(Const.ORDER_STATUS_SHIPPED.equals(subOrderStatus)){
			subOrderStatusName = "待收货";
		}else if(Const.ORDER_STATUS_RECEIVED_GOODS.equals(subOrderStatus)){
			if("1".equals(isComment)){
				subOrderStatusName = "已评价";
			}else{
				subOrderStatusName = "待评价";
			}
		}else if(Const.ORDER_STATUS_SUCCESS.equals(subOrderStatus)){
			if("0".equals(isComment)){
				subOrderStatusName = "待评价";
			}else{
				subOrderStatusName = "已完成";
			}
		}else if(Const.ORDER_STATUS_CANCEL.equals(subOrderStatus)){
			subOrderStatusName = "交易取消";
		}else if(Const.ORDER_STATUS_CLOSE.equals(subOrderStatus)){
			subOrderStatusName = "交易关闭";
		}
		return subOrderStatusName;
	}
	
}
