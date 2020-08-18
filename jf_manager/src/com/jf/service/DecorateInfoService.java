package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.DecorateInfoMapper;
import com.jf.entity.DecorateInfo;
import com.jf.entity.DecorateInfoExample;

@Service
@Transactional
public class DecorateInfoService extends BaseService<DecorateInfo,DecorateInfoExample> {
	@Autowired
	private DecorateInfoMapper decorateInfoMapper;
	@Autowired
	public void setDecorateInfoMapper(DecorateInfoMapper decorateInfoMapper) {
		super.setDao(decorateInfoMapper);
		this.decorateInfoMapper = decorateInfoMapper;
	}
	
}
