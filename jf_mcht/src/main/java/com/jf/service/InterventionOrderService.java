package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.InterventionOrderCustomMapper;
import com.jf.dao.InterventionOrderMapper;
import com.jf.dao.InterventionOrderStatusLogMapper;
import com.jf.entity.InterventionOrder;
import com.jf.entity.InterventionOrderCustom;
import com.jf.entity.InterventionOrderCustomExample;
import com.jf.entity.InterventionOrderExample;
import com.jf.entity.InterventionOrderStatusLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年4月3日 上午9:21:48 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class InterventionOrderService extends BaseService<InterventionOrder, InterventionOrderExample> {
	
	@Autowired
	private InterventionOrderMapper interventionOrderMapper;
	@Autowired
	private InterventionOrderCustomMapper interventionOrderCustomMapper;
	@Autowired
	private InterventionOrderStatusLogMapper interventionOrderStatusLogMapper;
	@Autowired
	public void setInterventionOrderMapper(InterventionOrderMapper interventionOrderMapper) {
		this.setDao(interventionOrderMapper);
		this.interventionOrderMapper = interventionOrderMapper;
	}

	public int countInterventionOrderCustomByExample(InterventionOrderCustomExample ioce) {
		return interventionOrderCustomMapper.countByExample(ioce);
	}

	public List<InterventionOrderCustom> selectInterventionOrderCustomByExample(InterventionOrderCustomExample ioce) {
		return interventionOrderCustomMapper.selectByExample(ioce);
	}

	public void updateInterventionOrder(InterventionOrder interventionOrder,
			InterventionOrderStatusLog iosl) {
		this.updateByPrimaryKeySelective(interventionOrder);
		interventionOrderStatusLogMapper.insertSelective(iosl);
	}
	
}
