package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MchtBrandAptitudeCustomMapper;
import com.jf.dao.MchtBrandAptitudeMapper;
import com.jf.entity.MchtBrandAptitude;
import com.jf.entity.MchtBrandAptitudeCustom;
import com.jf.entity.MchtBrandAptitudeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MchtBrandAptitudeService extends BaseService<MchtBrandAptitude,MchtBrandAptitudeExample> {
	@Autowired
	private MchtBrandAptitudeMapper dao;
	
	@Autowired
	private MchtBrandAptitudeCustomMapper mchtBrandAptitudeCustomMapper;
	
	@Autowired
	public void setMchtBrandAptitudeMapper(MchtBrandAptitudeMapper mchtBrandAptitudeMapper) {
		super.setDao(mchtBrandAptitudeMapper);
		this.dao = mchtBrandAptitudeMapper;
	}
	
	public List<MchtBrandAptitudeCustom> selectCustomByExample(MchtBrandAptitudeExample example){
		return mchtBrandAptitudeCustomMapper.selectByExample(example);
	}
	
	public List<Integer> getIdsByMchtProductBrandId(Integer mchtProductBrandId){
		return mchtBrandAptitudeCustomMapper.getIdsByMchtProductBrandId(mchtProductBrandId);
	}
}
