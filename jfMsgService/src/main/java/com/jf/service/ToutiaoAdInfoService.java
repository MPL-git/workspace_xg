package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ToutiaoAdInfoMapper;
import com.jf.entity.ToutiaoAdInfo;
import com.jf.entity.ToutiaoAdInfoExample;

@Service
@Transactional
public class ToutiaoAdInfoService extends BaseService<ToutiaoAdInfo, ToutiaoAdInfoExample> {

	@Autowired
	private ToutiaoAdInfoMapper toutiaoAdInfoMapper;
	
	@Autowired
	public void setToutiaoAdInfoMapper(ToutiaoAdInfoMapper toutiaoAdInfoMapper) {
		super.setDao(toutiaoAdInfoMapper);
		this.toutiaoAdInfoMapper = toutiaoAdInfoMapper;
	}
	
	
}
