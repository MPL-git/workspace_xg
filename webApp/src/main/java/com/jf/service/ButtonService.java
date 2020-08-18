package com.jf.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.constant.Const;
import com.jf.common.utils.CollectionUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.entity.InterventionOrder;

@Service
@Transactional
public class ButtonService {
	@Resource
	private CustomerServiceOrderService customerServiceOrderService;
	@Resource
	private SubOrderService subOrderService;
	
	/**
	 * 确认收货按钮
	 */
	public Map<String, Object> getReceiptButton(String status, String subOrderId) {
		boolean button = true;
		String msg = "";
		if(!StringUtil.isBlank(subOrderId)){
			if(Const.ORDER_STATUS_RECEIVED_GOODS.equals(status)){
				button = false;
				msg = "请勿重复确认收货";
			}else if(!Const.ORDER_STATUS_SHIPPED.equals(status)){
				button = false;
				msg = "待收货才能确认收货";
			}
			//查看是否存在正在进行中的售后单据
			if(button){
				List<String> csServiceList = new ArrayList<>();
				csServiceList.add(Const.CUSTOMER_ORDER_STATUS_REFUNDING);//售后进行中
				int count = customerServiceOrderService.findListBySubOrderIdAndStatusCount(Integer.valueOf(subOrderId), csServiceList);
				if(count > 0){
					msg = "售后申请正在处理中，请处理完成再次确认收货！";
				}
			}
		}else{
			button = false;
			msg = "订单不存在";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("button", button);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 查看物流按钮
	 * @param type 
	 * @param receiptDate 
	 */
	public boolean getSeeLogisticsButton(Date deliveryDate, String subOrderStatus, String type) {
		//type 1订单列表 2订单详情
		boolean button = false;
		if("1".equals(type)){ 
			if(subOrderStatus.equals(Const.ORDER_STATUS_SHIPPED)){
				button = true;
			}
		}else{
			if(deliveryDate != null){
				button = true;
			}
		}
		return button;
	}
	/**
	 * 删除订单按钮
	 */
	public Map<String, Object> getDelButton(String subOrderStatus) {
		boolean button = false;
		String msg = "";
		if(subOrderStatus.equals(Const.ORDER_STATUS_SUCCESS) || subOrderStatus.equals(Const.ORDER_STATUS_CANCEL) || subOrderStatus.equals(Const.ORDER_STATUS_CLOSE)){
			button = true;
		}else{
			msg = "未取消或未完成的订单不能删除";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("button", button);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 评价按钮
	 * @param orderDtlSize 
	 * @param isComment 
	 */
	public Map<String, Object> getEvaluateButtonButton(String subOrderId, int orderDtlSize, String isComment) {
		boolean button = true;
		String msg = "";
		if(!StringUtil.isBlank(subOrderId)){
			if(!"0".equals(isComment)){
				button = false;
				msg = "未确认收货不能评价";
			}
		}else{
			button = false;
			msg = "订单不存在";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("button", button);
		map.put("msg", msg);
		return map;
	}
	
	
	/**
	 * 退款按钮
	 * @param orderDtlSize 
	 * @param isComment 
	 */
	public Map<String, Object> getRefundMarkButton(String subOrderStatus, String customerServiceOrderStatus, String isGive,String activityType) {
		boolean button = false;
		String msg = "";
		customerServiceOrderStatus = customerServiceOrderStatus == null ? "" :customerServiceOrderStatus;
		if(!activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_BARGAIN) && !activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_INVITE_FREE_CHARGE)){
			if("0".equals(isGive)){
				if(subOrderStatus.equals(Const.ORDER_STATUS_PAID)){
					if(StringUtil.isBlank(customerServiceOrderStatus)){
						button = true;
					}else{
						msg = "退款正在处理中。。。";
					}
				}else{
					msg = "只能在未发货前可申请退款";
				}
			}else{
				msg = "赠送商品不能申请退款";
			}
		}else{
			msg = "砍价商品不能申请退款";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("button", button);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 重新退款按钮
	 * @param orderDtlSize 
	 * @param isComment 
	 */
	public Map<String, Object> getRefundMarkAgainButton(String subOrderStatus, String customerServiceOrderStatus,String isGive, String activityType) {
		boolean button = false;
		String msg = "";
		customerServiceOrderStatus = customerServiceOrderStatus == null ? "" :customerServiceOrderStatus;
		if(!activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_BARGAIN) && !activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_INVITE_FREE_CHARGE)){
			if("0".equals(isGive)){
				if(subOrderStatus.equals(Const.ORDER_STATUS_PAID)){
					if(!StringUtil.isBlank(customerServiceOrderStatus) 
							&& (customerServiceOrderStatus.equals(Const.CUSTOMER_ORDER_STATUS_REJECT) || customerServiceOrderStatus.equals(Const.CUSTOMER_ORDER_STATUS_CANCEL))){
						button = true;
					}else{
						msg = "退款正在处理中。。。";
					}
				}else{
					msg = "只能在未发货前可申请退款";
				}
			}else{
				msg = "赠送商品不能申请退款";
			}
		}else{
			msg = "砍价商品不能申请退款";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("button", button);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 售后申请按钮
	 * @param receiptDate 
	 * @param orderDtlSize 
	 * @param isComment 
	 */
	public Map<String, Object> getAfterSalesApplyButton(String subOrderStatus, String customerServiceOrderStatus,String isGive, String activityType) {
		boolean button = false;
		String msg = "";
		customerServiceOrderStatus = customerServiceOrderStatus == null ? "" :customerServiceOrderStatus;
		if(!activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_BARGAIN) && !activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_INVITE_FREE_CHARGE)){
			if("0".equals(isGive)){
				if(subOrderStatus.equals(Const.ORDER_STATUS_SHIPPED) || subOrderStatus.equals(Const.ORDER_STATUS_RECEIVED_GOODS)){
					if(StringUtil.isBlank(customerServiceOrderStatus)){
						button = true;
					}else{
						msg = "售后正在处理中。。。";
					}
				}else{
					msg = "只能在待收货或确认收货可申请售后";
				}
			}else{
				msg = "赠送商品不能申请售后";
			}
		}else{
			msg = "砍价商品不能申请售后";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("button", button);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * 重新售后申请按钮
	 * @param interventionOrders 
	 * @param receiptDate 
	 * @param orderDtlSize 
	 * @param isComment 
	 */
	public Map<String, Object> getAfterSalesApplyAgainButton(String subOrderStatus, String customerServiceOrderStatus,String isGive, String activityType, List<InterventionOrder> interventionOrders) {
		boolean button = false;
		String msg = "";
		customerServiceOrderStatus = customerServiceOrderStatus == null ? "" :customerServiceOrderStatus;
		if(!activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_BARGAIN) && !activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_INVITE_FREE_CHARGE)){
			if("0".equals(isGive)){
				if(subOrderStatus.equals(Const.ORDER_STATUS_SHIPPED) || subOrderStatus.equals(Const.ORDER_STATUS_RECEIVED_GOODS)){
					if(!StringUtil.isBlank(customerServiceOrderStatus) 
							&& (customerServiceOrderStatus.equals(Const.CUSTOMER_ORDER_STATUS_REJECT) || customerServiceOrderStatus.equals(Const.CUSTOMER_ORDER_STATUS_CANCEL))){
						if(CollectionUtil.isEmpty(interventionOrders)){
							button = true;
						}else{
							msg = "介入正在处理中。。。";
						}
					}else{
						msg = "售后正在处理中。。。";
					}
				}
			}else{
				msg = "赠送商品不能申请售后";
			}
		}else{
			msg = "砍价商品不能申请售后";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("button", button);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 提醒发货按钮
	 * @param subOrderStatus 
	 * @param memberRemindDate 
	 */
	public Map<String, Object> getRemindDeliveryButton(String subOrderStatus, Date memberRemindDate) {
		boolean button = false;
		String msg = "";
		Date currentDate = new Date();
		if(!subOrderStatus.equals(Const.ORDER_STATUS_PAID)){
			msg = "商家已发货";
		}else if(memberRemindDate != null && DateUtil.addMinute(memberRemindDate, 1).compareTo(currentDate) > 0){
			msg = "已提醒商家发货";
		}else{
			button = true;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("button", button);
		map.put("msg", msg);
		return map;
	}
	
}
