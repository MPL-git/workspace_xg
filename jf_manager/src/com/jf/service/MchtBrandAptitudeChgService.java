package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtBrandAptitudeChgCustomMapper;
import com.jf.dao.MchtBrandAptitudeChgMapper;
import com.jf.entity.MchtBrandAptitudeChg;
import com.jf.entity.MchtBrandAptitudeChgCustom;
import com.jf.entity.MchtBrandAptitudeChgExample;

@Service
@Transactional
public class MchtBrandAptitudeChgService extends BaseService<MchtBrandAptitudeChg,MchtBrandAptitudeChgExample> {
	@Autowired
	private MchtBrandAptitudeChgMapper dao;
	
	@Autowired
	private MchtBrandAptitudeChgCustomMapper mchtBrandAptitudeChgCustomMapper;
	
	@Autowired
	public void setMchtBrandAptitudeChgMapper(MchtBrandAptitudeChgMapper mchtBrandAptitudeChgMapper) {
		super.setDao(mchtBrandAptitudeChgMapper);
		this.dao = mchtBrandAptitudeChgMapper;
	}
	
	public List<MchtBrandAptitudeChgCustom> selectCustomByExample(MchtBrandAptitudeChgExample example){
		return mchtBrandAptitudeChgCustomMapper.selectByExample(example);
	}
	
	public List<Integer> getIdsByMchtBrandChgId(Integer mchtBrandChgId){
		return mchtBrandAptitudeChgCustomMapper.getIdsByMchtBrandChgId(mchtBrandChgId);
	}
}
