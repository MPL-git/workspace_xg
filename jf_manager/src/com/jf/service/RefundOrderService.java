package com.jf.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CustomerServiceLogMapper;
import com.jf.dao.CustomerServiceStatusLogMapper;
import com.jf.dao.RefundOrderCustomMapper;
import com.jf.dao.RefundOrderMapper;
import com.jf.entity.CustomerServiceLog;
import com.jf.entity.CustomerServiceOrder;
import com.jf.entity.CustomerServiceStatusLog;
import com.jf.entity.OrderDtl;
import com.jf.entity.RefundOrder;
import com.jf.entity.RefundOrderCustom;
import com.jf.entity.RefundOrderCustomExample;
import com.jf.entity.RefundOrderExample;
import com.jf.entity.SysAppMessage;

@Service
@Transactional
public class RefundOrderService extends BaseService<RefundOrder,RefundOrderExample> {
	@Autowired
	private RefundOrderMapper dao;
	
	@Autowired
	private RefundOrderCustomMapper refundOrderCustomMapper;
	
	@Autowired
	private CustomerServiceOrderService customerServiceOrderService;
	
	@Autowired
	private OrderDtlService orderDtlService;
	
	@Autowired
	private SysAppMessageService sysAppMessageService;
	
	@Autowired
	private CustomerServiceStatusLogMapper customerServiceStatusLogMapper;
	
	@Autowired
	private CustomerServiceLogMapper customerServiceLogMapper;
	
	@Autowired
	public void setRefundOrderMapper(RefundOrderMapper refundOrderMapper) {
		super.setDao(refundOrderMapper);
		this.dao = refundOrderMapper;
	}
	
	public int countRefundOrderCustomByExample(RefundOrderCustomExample example) {
		return refundOrderCustomMapper.countByExample(example);
	}

	public List<RefundOrderCustom> selectRefundOrderCustomByExample(RefundOrderCustomExample example) {
		return refundOrderCustomMapper.selectByExample(example);
	}

	public void updateRefundOrder(RefundOrder refundOrder,CustomerServiceOrder customerServiceOrder) {
		this.updateByPrimaryKey(refundOrder);
		if(customerServiceOrder!=null){
			customerServiceOrderService.updateByPrimaryKey(customerServiceOrder);
		}
	}

	public List<RefundOrderCustom> refundOrderCountEachDayList(HashMap<String, Object> paramMap) {
		return refundOrderCustomMapper.refundOrderCountEachDayList(paramMap);
	}
	
	public List<RefundOrderCustom> paymenExpenditureCountEachDayList(HashMap<String, Object> paramMap) {
		return refundOrderCustomMapper.paymenExpenditureCountEachDayList(paramMap);
	}
	
	public int paymenExpenditureCountEachDayCount(HashMap<String, Object> paramMap) {
		return refundOrderCustomMapper.paymenExpenditureCountEachDayCount(paramMap);
	}

	public void updateRefundOrder(RefundOrder refundOrder,
			CustomerServiceOrder customerServiceOrder, OrderDtl orderDtl,SysAppMessage sysAppMessage,CustomerServiceStatusLog customerServiceStatusLog,CustomerServiceLog customerServiceLog) {
		this.updateByPrimaryKeySelective(refundOrder);
		if(customerServiceOrder!=null){
			customerServiceOrder.setUpdateDate(new Date());
			customerServiceOrderService.updateByPrimaryKey(customerServiceOrder);
		}
		if(orderDtl!=null){
			orderDtl.setUpdateDate(new Date());
			orderDtlService.updateByPrimaryKey(orderDtl);
		}
		if(sysAppMessage!=null){
			sysAppMessageService.insertSelective(sysAppMessage);
		}
		if(customerServiceStatusLog!=null){
			customerServiceStatusLogMapper.insertSelective(customerServiceStatusLog);
		}
		if(customerServiceLog!=null){
			customerServiceLogMapper.insertSelective(customerServiceLog);
		}
	}
	
	public List<Integer> getIdsByRefundCode(HashMap<String, Object> paramMap) {
		return refundOrderCustomMapper.getIdsByRefundCode(paramMap);
	}

	public List<Map<String, Object>> refundOrderCount(Map<String, Object> paramMap) {
		return refundOrderCustomMapper.refundOrderCount(paramMap);
	}
	
	/**
	 * 
	 * @Title updateRefundOrderAndOrderDtl   
	 * @Description TODO(退款任务中的操作：修改退款单 与 子订单明细)   
	 * @author pengl
	 * @date 2018年10月8日 下午2:35:49
	 */
	public void updateRefundOrderAndOrderDtl(RefundOrder refundOrder, OrderDtl orderDtl) {
		//修改退款单表
		dao.updateByPrimaryKeySelective(refundOrder);
		//修改子订单明细的退款时间
		if(orderDtl != null) {
			orderDtlService.updateByPrimaryKeySelective(orderDtl);
		}
		
	}
	
	/**
	 * 
	 * @Title updateRefundOrderAndOrderDtlAndCustomer   
	 * @Description TODO(退款任务中的操作：修改退款单 与 子订单明细 与 售后单)   
	 * @author pengl
	 * @date 2018年10月8日 下午3:07:49
	 */
	public void updateRefundOrderAndOrderDtlAndCustomer(RefundOrder refundOrder, OrderDtl orderDtl, CustomerServiceOrder customerServiceOrder) {
		//修改退款单表
		dao.updateByPrimaryKeySelective(refundOrder);
		//修改子订单明细的退款时间
		if(orderDtl != null) {
			orderDtlService.updateByPrimaryKeySelective(orderDtl);
		}
		//修改售后单
		if(customerServiceOrder != null){
			customerServiceOrderService.updateByPrimaryKey(customerServiceOrder);
		}
	}
	
	/**
	 * 
	 * @Title updateRefundOrderAndOrderDtlAndCustomerList   
	 * @Description TODO(退款任务中的操作：批量修改退款单 与 子订单明细 与 售后单)   
	 * @author pengl
	 * @date 2018年10月8日 下午3:30:21
	 */
	public void updateRefundOrderAndOrderDtlAndCustomerList(List<RefundOrder> refundOrderList, List<OrderDtl> orderDtlList, List<CustomerServiceOrder> customerServiceOrderList) {
		//修改退款单表
		for(RefundOrder refundOrder : refundOrderList) {
			dao.updateByPrimaryKeySelective(refundOrder);
		}
		//修改子订单明细的退款时间
		for(OrderDtl orderDtl : orderDtlList) {
			orderDtlService.updateByPrimaryKeySelective(orderDtl);
		}
		//修改售后单
		for(CustomerServiceOrder customerServiceOrder : customerServiceOrderList) {
			customerServiceOrderService.updateByPrimaryKey(customerServiceOrder);
		}
	}

	public List<RefundOrderCustom> depositOrderRefundOrderCountEachDayList(	HashMap<String, Object> paramMap) {
		return refundOrderCustomMapper.depositOrderRefundOrderCountEachDayList(paramMap);
	}

	public List<Map<String, Object>> refundOrderStatistics(Map<String, Object> paramMap) {
		return refundOrderCustomMapper.refundOrderStatistics(paramMap);
	}

}
