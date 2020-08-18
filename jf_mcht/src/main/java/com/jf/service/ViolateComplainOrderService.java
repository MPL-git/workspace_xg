package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ViolateComplainOrderCustomMapper;
import com.jf.dao.ViolateComplainOrderMapper;
import com.jf.entity.ViolateComplainOrder;
import com.jf.entity.ViolateComplainOrderCustom;
import com.jf.entity.ViolateComplainOrderCustomExample;
import com.jf.entity.ViolateComplainOrderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ViolateComplainOrderService extends BaseService<ViolateComplainOrder,ViolateComplainOrderExample> {
	@Autowired
	private ViolateComplainOrderMapper dao;
	
	@Autowired
	private ViolateComplainOrderCustomMapper violateComplainOrderCustomMapper;
	
	@Autowired
	public void setViolateComplainOrderMapper(ViolateComplainOrderMapper violateComplainOrderMapper) {
		super.setDao(violateComplainOrderMapper);
		this.dao = violateComplainOrderMapper;
	}
	
	public ViolateComplainOrder getViolateComplainOrderByViolateOrderId(Integer violateOrderId){
		return violateComplainOrderCustomMapper.getViolateComplainOrderByViolateOrderId(violateOrderId);
	}

	public List<ViolateComplainOrderCustom> selectByViolateComplainOrderCustomExample(
			ViolateComplainOrderCustomExample example) {
		return violateComplainOrderCustomMapper.selectByExample(example);
	}
}
