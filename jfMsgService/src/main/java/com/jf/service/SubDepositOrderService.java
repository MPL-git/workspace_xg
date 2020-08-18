package com.jf.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SubDepositOrderMapper;
import com.jf.entity.SubDepositOrder;
import com.jf.entity.SubDepositOrderExample;

@Service
@Transactional
public class SubDepositOrderService extends BaseService<SubDepositOrder, SubDepositOrderExample> {

	@Autowired
	private SubDepositOrderMapper subDepositOrderMapper;
	@Resource
	private DepositOrderStatusLogService depositOrderStatusLogService;
	
	@Autowired
	public void setSubDepositOrderMapper(SubDepositOrderMapper subDepositOrderMapper) {
		super.setDao(subDepositOrderMapper);
		this.subDepositOrderMapper = subDepositOrderMapper;
	}
	
	/**
	 * 根据订单明细更新预售子订单状态
	 * @param orderDtlId
	 * @param date
	 * @param subDepositStatusTailPaid 
	 * @param subDepositStatusTailArreadyOrder 
	 */
	public void updateSubDepositOrderStatus(Integer orderDtlId, Date date, String updateFrontStatus, String updateBackStatus) {
		SubDepositOrderExample subDepositOrderExample = new SubDepositOrderExample();
		subDepositOrderExample.createCriteria().andOrderDtlIdEqualTo(orderDtlId).andStatusEqualTo(updateFrontStatus).andDelFlagEqualTo("0");
		//添加日志
		List<SubDepositOrder> orders = selectByExample(subDepositOrderExample);
		if(CollectionUtils.isNotEmpty(orders)){
			SubDepositOrder subDepositOrder = new SubDepositOrder();
			subDepositOrder.setStatus(updateBackStatus);
			subDepositOrder.setUpdateDate(date);
			updateByExampleSelective(subDepositOrder, subDepositOrderExample);
			for (SubDepositOrder order : orders) {
				depositOrderStatusLogService.addLog(order.getId(), updateBackStatus, orderDtlId, order.getMemberId());
			}
		}		
	}
	
	
	
}
