package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.CombineOrderMapper;
import com.jf.entity.CombineOrder;
import com.jf.entity.CombineOrderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CombineOrderService extends BaseService<CombineOrder,CombineOrderExample> {
	@Autowired
	private CombineOrderMapper dao;
	
	@Autowired
	public void setCombineOrderMapper(CombineOrderMapper combineOrderMapper) {
		super.setDao(combineOrderMapper);
		this.dao = combineOrderMapper;
	}
	
	
}
