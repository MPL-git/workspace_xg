package com.jf.service;

import com.google.common.collect.Lists;
import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.constant.StateConst;
import com.jf.dao.SubOrderMapper;
import com.jf.entity.OrderDtl;
import com.jf.entity.OrderDtlExample;
import com.jf.entity.SubOrder;
import com.jf.entity.SubOrderExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月27日 上午10:59:59 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class SubOrderService extends BaseService<SubOrder, SubOrderExample> {
	
	@Autowired
	private SubOrderMapper subOrderMapper;
	@Autowired
	private OrderDtlService orderDtlService;
	@Autowired
	private OrderLogService orderLogService;
	@Autowired
	private ButtonService buttonService;
	@Autowired
	public void setSubOrderMapper(SubOrderMapper subOrderMapper) {
		this.setDao(subOrderMapper);
		this.subOrderMapper = subOrderMapper;
	}

	public SubOrder findListById(Integer subOrderId) {
		
		return subOrderMapper.selectByPrimaryKey(subOrderId);
	}

	public void updateSubOrderStatus(Integer subOrderId) {
		//总订单状态 0 未付 1已付 4取消
		//自订单状态 0 待付款 1 待发货 2 待收货 3 完成 4 取消(未付款客户取消,平台取消) 5 关闭(退款后关闭) 6 删除
		
		//子订单修改标识为3
		Date date = new Date();
		SubOrder subOrder = new SubOrder();
		subOrder.setId(subOrderId);
		subOrder.setStatus(Const.ORDER_STATUS_SUCCESS);
		subOrder.setCompleteDate(date);
		updateModel(subOrder);
		//修改订单明细状态
		OrderDtlExample orderDtlExample = new OrderDtlExample();
		orderDtlExample.createCriteria().andSubOrderIdEqualTo(subOrderId).andDelFlagEqualTo("0").andProductStatusIsNull();
		OrderDtl orderDtl= new OrderDtl();
		orderDtl.setProductStatus("1");
		orderDtl.setUpdateDate(date);
		orderDtlService.updateByExampleSelective(orderDtl, orderDtlExample);
	}

	public void updateModel(SubOrder subOrder) {
		
		subOrderMapper.updateByPrimaryKeySelective(subOrder);	
	}

	/**
	 * 确认收货
	 * @param subOrderId
	 * @param memberId
	 */
	public void confirmReceiptOrderById(Integer subOrderId, Integer memberId) {
		//1待收货才能确认收货
		//2存在售后单据不能确认收货
		//3已收货的不能再次确认收货
		Date currentDate = new Date();
		SubOrder subOrder = selectByPrimaryKey(subOrderId);
		if(subOrder != null && subOrder.getId() != null){
			String status = subOrder.getStatus();
			Map<String, Object> map = buttonService.getReceiptButton(status,subOrderId.toString());
			if(!(boolean) map.get("button")){
				throw new ArgException(map.get("msg").toString());
			}
			subOrder.setStatus(Const.ORDER_STATUS_RECEIVED_GOODS);
			subOrder.setIsComment("0");
			subOrder.setReceiptDate(currentDate);
			subOrder.setReceiverType("1");//1用户2系统
			subOrder.setUpdateDate(currentDate);
			subOrder.setUpdateBy(memberId);
			updateModel(subOrder);
			orderLogService.insertOrderLog(subOrderId,Const.ORDER_STATUS_RECEIVED_GOODS,memberId);
		}else{
			throw new ArgException("确认收货失败");
		}
	}

	public void updateSubOrderRemind(Integer memberId, Integer subOrderId) {
		Date currentDate = new Date();
		SubOrderExample subOrderExample = new SubOrderExample();
		subOrderExample.createCriteria().andIdEqualTo(subOrderId).andDelFlagEqualTo("0");
		List<SubOrder> subOrders = selectByExample(subOrderExample);
		if(CollectionUtils.isNotEmpty(subOrders)){
			SubOrder subOrder = subOrders.get(0);
			String subOrderStatus = subOrder.getStatus();
			Date memberRemindDate = subOrder.getMemberRemindDate();
			Map<String, Object> remindDeliveryButtonMap = buttonService.getRemindDeliveryButton(subOrderStatus,memberRemindDate);
			if(!(boolean) remindDeliveryButtonMap.get("button")){
				throw new ArgException(remindDeliveryButtonMap.get("msg").toString());
			}
			subOrder.setMemberRemindCount(subOrder.getMemberRemindCount()+1);
			subOrder.setMemberRemindDate(currentDate);
			updateByPrimaryKeySelective(subOrder);
		}else{
			throw new ArgException("订单不存在");
		}
	}

	public boolean changeOrderAddressAble(Integer combineOrderId) {
		SubOrderExample example = new SubOrderExample();
		example.createCriteria()
				.andCombineOrderIdEqualTo(combineOrderId)
				.andStatusIn(Lists.newArrayList("0", "1")) //待付款、待收货
				.andAuditStatusIn(Lists.newArrayList("1", "2")) //批量待审、人工待审
				.andDelFlagEqualTo(StateConst.FALSE);
		return this.countByExample(example) > 0;
	}
}
