package com.jf.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzs.common.utils.StringUtil;
import com.jf.dao.OrderAbnormalLogCustomMapper;
import com.jf.dao.OrderAbnormalLogMapper;
import com.jf.dao.RemarksLogMapper;
import com.jf.dao.SubOrderMapper;
import com.jf.entity.OrderAbnormalLog;
import com.jf.entity.OrderAbnormalLogCustom;
import com.jf.entity.OrderAbnormalLogCustomExample;
import com.jf.entity.OrderAbnormalLogExample;
import com.jf.entity.RemarksLog;
import com.jf.entity.StaffBean;
import com.jf.entity.SubOrder;

@Transactional
@Service
public class OrderAbnormalLogService extends BaseService<OrderAbnormalLog, OrderAbnormalLogExample> {

	@Autowired
	private OrderAbnormalLogMapper orderAbnormalLogMapper;
	
	@Autowired
	private OrderAbnormalLogCustomMapper orderAbnormalLogCustomMapper;
	
	@Autowired
	private SubOrderMapper subOrderMapper;
	
	@Autowired
	private RemarksLogMapper remarksLogMapper;
	
	@Autowired
	public void setOrderAbnormalLogMapper(OrderAbnormalLogMapper orderAbnormalLogMapper) {
		super.setDao(orderAbnormalLogMapper);
		this.orderAbnormalLogMapper = orderAbnormalLogMapper;
	}
	
	public int countByCustomExample(OrderAbnormalLogExample example) {
		return orderAbnormalLogCustomMapper.countByCustomExample(example);
	}
	
	public List<OrderAbnormalLogCustom> selectByCustomExample(OrderAbnormalLogExample example) {
		return orderAbnormalLogCustomMapper.selectByCustomExample(example);
	}
	
	public void addOrUpdateOrderAbnormal(HttpServletRequest request, StaffBean staffBean) {
		Integer staffId = Integer.valueOf(staffBean.getStaffID());
		String subOrderId = request.getParameter("subOrderId");
		String stockout = request.getParameter("stockout");
		String deliveryOvertime = request.getParameter("deliveryOvertime");
		String shamDelivery = request.getParameter("shamDelivery");
		String otherOne = request.getParameter("otherOne");
		String abnormalDesc = request.getParameter("abnormalDesc");
		Date date = new Date();
		if(!StringUtil.isEmpty(deliveryOvertime) && "1".equals(deliveryOvertime)) { //超时发货
			OrderAbnormalLog orderAbnormalLog = new OrderAbnormalLog();
			orderAbnormalLog.setSubOrderId(Integer.parseInt(subOrderId));
			orderAbnormalLog.setAbnormalType("3");
			orderAbnormalLog.setCreateBy(staffId);
			orderAbnormalLog.setCreateDate(date);
			orderAbnormalLogMapper.insertSelective(orderAbnormalLog);
		}
		if(!StringUtil.isEmpty(shamDelivery) && "2".equals(shamDelivery)) { //虚假发货
			OrderAbnormalLog orderAbnormalLog = new OrderAbnormalLog();
			orderAbnormalLog.setSubOrderId(Integer.parseInt(subOrderId));
			orderAbnormalLog.setAbnormalType("4");
			orderAbnormalLog.setCreateBy(staffId);
			orderAbnormalLog.setCreateDate(date);
			orderAbnormalLogMapper.insertSelective(orderAbnormalLog);
		}
		if(!StringUtil.isEmpty(stockout) && "3".equals(stockout)) { //缺货
			OrderAbnormalLog orderAbnormalLog = new OrderAbnormalLog();
			orderAbnormalLog.setSubOrderId(Integer.parseInt(subOrderId));
			orderAbnormalLog.setAbnormalType("1");
			orderAbnormalLog.setCreateBy(staffId);
			orderAbnormalLog.setCreateDate(date);
			orderAbnormalLogMapper.insertSelective(orderAbnormalLog);
		}
		if(!StringUtil.isEmpty(otherOne) && "4".equals(otherOne)) { //其他
			OrderAbnormalLog orderAbnormalLog = new OrderAbnormalLog();
			orderAbnormalLog.setSubOrderId(Integer.parseInt(subOrderId));
			orderAbnormalLog.setAbnormalType("2");
			orderAbnormalLog.setCreateBy(staffId);
			orderAbnormalLog.setCreateDate(date);
			orderAbnormalLog.setAbnormalDesc(abnormalDesc);
			orderAbnormalLogMapper.insertSelective(orderAbnormalLog);
		}
	}
	
	public void updateRemarks(SubOrder subOrder, OrderAbnormalLog orderAbnormalLog, RemarksLog remarksLog) {
		subOrderMapper.updateByPrimaryKeySelective(subOrder);
		orderAbnormalLogMapper.updateByPrimaryKeySelective(orderAbnormalLog);
		remarksLogMapper.insertSelective(remarksLog);
	}

	public List<HashMap<String, Object>> getFollowByList(Map<String, Object> paramMap) {
		return orderAbnormalLogCustomMapper.getFollowByList(paramMap);
	}

	public int countOrderAbnormalLogCustomByExample(OrderAbnormalLogCustomExample oalce) {
		return orderAbnormalLogCustomMapper.countByExample(oalce);
	}

	public List<OrderAbnormalLogCustom> selectOrderAbnormalLogCustomByExample(OrderAbnormalLogCustomExample oalce) {
		return orderAbnormalLogCustomMapper.selectByExample(oalce);
	}
	
}
