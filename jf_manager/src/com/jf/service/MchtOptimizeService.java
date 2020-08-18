package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtOptimizeMapper;
import com.jf.entity.MchtOptimize;
import com.jf.entity.MchtOptimizeExample;

@Service
@Transactional
public class MchtOptimizeService extends BaseService<MchtOptimize, MchtOptimizeExample> {

	@Autowired
	private MchtOptimizeMapper mchtOptimizeMapper;
	
	@Autowired
	public void setMchtOptimizeMapper(MchtOptimizeMapper mchtOptimizeMapper) {
		super.setDao(mchtOptimizeMapper);
		this.mchtOptimizeMapper = mchtOptimizeMapper;
	}
	
	
	
	
}
