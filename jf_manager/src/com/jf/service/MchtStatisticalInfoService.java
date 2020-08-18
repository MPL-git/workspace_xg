package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtStatisticalInfoMapper;
import com.jf.entity.MchtStatisticalInfo;
import com.jf.entity.MchtStatisticalInfoExample;

@Service
@Transactional
public class MchtStatisticalInfoService extends BaseService<MchtStatisticalInfo,MchtStatisticalInfoExample> {
	
	@Autowired
	private MchtStatisticalInfoMapper mchtStatisticalInfoMapper;
	
	@Autowired
	public void setMchtStatisticalInfoMapper(MchtStatisticalInfoMapper mchtStatisticalInfoMapper) {
		super.setDao(mchtStatisticalInfoMapper);
		this.mchtStatisticalInfoMapper = mchtStatisticalInfoMapper;
	}
	
}
