package com.jf.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ViolateComplainOrderCustomMapper;
import com.jf.dao.ViolateComplainOrderMapper;
import com.jf.entity.ViolateComplainOrder;
import com.jf.entity.ViolateComplainOrderCustom;
import com.jf.entity.ViolateComplainOrderCustomExample;
import com.jf.entity.ViolateComplainOrderExample;
import com.jf.entity.ViolateOrder;

@Service
@Transactional
public class ViolateComplainOrderService extends BaseService<ViolateComplainOrder,ViolateComplainOrderExample> {
	@Autowired
	private ViolateComplainOrderMapper dao;
	
	@Autowired
	private ViolateComplainOrderCustomMapper violateComplainOrderCustomMapper;
	
	@Autowired
	private ViolateOrderService violateOrderService;
	
	@Autowired
	public void setViolateComplainOrderMapper(ViolateComplainOrderMapper violateComplainOrderMapper) {
		super.setDao(violateComplainOrderMapper);
		this.dao = violateComplainOrderMapper;
	}

	public void update(ViolateComplainOrder violateComplainOrder,
			ViolateOrder violateOrder) {
		this.updateByPrimaryKeySelective(violateComplainOrder);
		violateOrderService.updateByPrimaryKeySelective(violateOrder);
	}

	public List<Map<String, Object>> getAllProcesBy() {
		return violateComplainOrderCustomMapper.getAllProcesBy();
	}
	
	public List<ViolateComplainOrderCustom> selectViolateComplainOrderCustomByExample(ViolateComplainOrderCustomExample example){
		return violateComplainOrderCustomMapper.selectByExample(example);
	}
}
