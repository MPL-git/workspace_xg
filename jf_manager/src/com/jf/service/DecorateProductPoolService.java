package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.DecorateProductPoolMapper;
import com.jf.entity.DecorateProductPool;
import com.jf.entity.DecorateProductPoolExample;

@Service
@Transactional
public class DecorateProductPoolService extends BaseService<DecorateProductPool,DecorateProductPoolExample> {
	@Autowired
	private DecorateProductPoolMapper decorateProductPoolMapper;
	@Autowired
	public void setDecorateProductPoolMapper(DecorateProductPoolMapper decorateProductPoolMapper) {
		super.setDao(decorateProductPoolMapper);
		this.decorateProductPoolMapper = decorateProductPoolMapper;
	}
	
}
