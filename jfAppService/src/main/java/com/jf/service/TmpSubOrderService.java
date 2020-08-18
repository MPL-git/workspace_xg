package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.TmpSubOrderMapper;
import com.jf.entity.TmpSubOrder;
import com.jf.entity.TmpSubOrderExample;

@Service
@Transactional
public class TmpSubOrderService extends BaseService<TmpSubOrder, TmpSubOrderExample> {
	
	@Autowired
	private TmpSubOrderMapper tmpSubOrderMapper;
	@Autowired
	public void setTmpSubOrderMapper(TmpSubOrderMapper tmpSubOrderMapper) {
		this.setDao(tmpSubOrderMapper);
		this.tmpSubOrderMapper = tmpSubOrderMapper;
	}
	public void updateBySubOrderId(TmpSubOrder ts) {
		tmpSubOrderMapper.updateBySubOrderId(ts);
		
	}
	
	
}
