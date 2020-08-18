package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ToutiaoTokenInfoMapper;
import com.jf.entity.ToutiaoTokenInfo;
import com.jf.entity.ToutiaoTokenInfoExample;

@Service
@Transactional
public class ToutiaoTokenInfoService extends BaseService<ToutiaoTokenInfo, ToutiaoTokenInfoExample> {

	@Autowired
	private ToutiaoTokenInfoMapper toutiaoTokenInfoMapper;
	
	@Autowired
	public void setToutiaoTokenInfoMapper(ToutiaoTokenInfoMapper toutiaoTokenInfoMapper) {
		super.setDao(toutiaoTokenInfoMapper);
		this.toutiaoTokenInfoMapper = toutiaoTokenInfoMapper;
	}
	
}
