package com.jf.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SubDepositOrderCustomMapper;
import com.jf.dao.SubDepositOrderMapper;
import com.jf.entity.SubDepositOrder;
import com.jf.entity.SubDepositOrderCustom;
import com.jf.entity.SubDepositOrderCustomExample;
import com.jf.entity.SubDepositOrderExample;

@Service
@Transactional
public class SubDepositOrderService extends BaseService<SubDepositOrder, SubDepositOrderExample> {

	@Autowired
	private SubDepositOrderMapper subDepositOrderMapper;
	
	@Autowired
	private SubDepositOrderCustomMapper subDepositOrderCustomMapper;
	
	@Resource
	private DepositOrderStatusLogService depositOrderStatusLogService;
	
	@Autowired
	public void setSubDepositOrderMapper(SubDepositOrderMapper subDepositOrderMapper) {
		super.setDao(subDepositOrderMapper);
		this.subDepositOrderMapper = subDepositOrderMapper;
	}
	
	public List<SubDepositOrderCustom> getSubDepositOrderList(Map<String, Object> paramMap) {
		return subDepositOrderCustomMapper.getSubDepositOrderList(paramMap);
	}
	
	public List<SubDepositOrderCustom> selectGroupByCombineDepositOrder(SubDepositOrderCustomExample example) {
		return subDepositOrderCustomMapper.selectGroupByCombineDepositOrder(example);
	}
	
	/**
	 * 根据订单明细更新预售子订单状态
	 * @param orderDtlId
	 * @param date
	 * @param payDate 
	 * @param subDepositStatusTailPaid 
	 * @param subDepositStatusTailArreadyOrder 
	 */
	public void updateSubDepositOrderStatus(Integer orderDtlId, Date date, String updateFrontStatus, String updateBackStatus, Date payDate) {
		SubDepositOrderExample subDepositOrderExample = new SubDepositOrderExample();
		subDepositOrderExample.createCriteria().andOrderDtlIdEqualTo(orderDtlId).andStatusEqualTo(updateFrontStatus).andDelFlagEqualTo("0");
		//添加日志
		List<SubDepositOrder> orders = selectByExample(subDepositOrderExample);
		if(CollectionUtils.isNotEmpty(orders)){
			SubDepositOrder subDepositOrder = new SubDepositOrder();
			subDepositOrder.setStatus(updateBackStatus);
			subDepositOrder.setPayDate(payDate);
			subDepositOrder.setUpdateDate(date);
			updateByExampleSelective(subDepositOrder, subDepositOrderExample);
			for (SubDepositOrder order : orders) {
				depositOrderStatusLogService.addLog(order.getId(), updateBackStatus, orderDtlId, order.getMemberId());
			}
		}		
	}
	
}
