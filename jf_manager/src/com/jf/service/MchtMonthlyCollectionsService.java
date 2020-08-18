package com.jf.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtMonthlyCollectionsCustomMapper;
import com.jf.dao.MchtMonthlyCollectionsMapper;
import com.jf.entity.MchtMonthlyCollections;
import com.jf.entity.MchtMonthlyCollectionsCustom;
import com.jf.entity.MchtMonthlyCollectionsCustomExample;
import com.jf.entity.MchtMonthlyCollectionsExample;

@Service
@Transactional
public class MchtMonthlyCollectionsService extends BaseService<MchtMonthlyCollections,MchtMonthlyCollectionsExample> {
	@Autowired
	private MchtMonthlyCollectionsMapper mchtMonthlyCollectionsMapper;
	
	@Autowired
	private MchtMonthlyCollectionsCustomMapper mchtMonthlyCollectionsCustomMapper;
	
	@Autowired
	private MchtInfoService mchtInfoService;
	
	@Autowired
	public void setMchtMonthlyCollectionsMapper(MchtMonthlyCollectionsMapper mchtMonthlyCollectionsMapper) {
		super.setDao(mchtMonthlyCollectionsMapper);
		this.mchtMonthlyCollectionsMapper = mchtMonthlyCollectionsMapper;
	}
	
	public List<MchtMonthlyCollectionsCustom> selectByExample(MchtMonthlyCollectionsCustomExample example){
		return mchtMonthlyCollectionsCustomMapper.selectByExample(example);
	}
	
	public int countByExample(MchtMonthlyCollectionsCustomExample example){
		return mchtMonthlyCollectionsCustomMapper.countByExample(example);
	}

	public List<MchtMonthlyCollectionsCustom> getListByYear(Map<String, String> paramMap) {
		return mchtMonthlyCollectionsCustomMapper.getListByYear(paramMap);
	}

	public List<MchtMonthlyCollectionsCustom> getListByDay(Map<String, String> paramMap) {
		return mchtMonthlyCollectionsCustomMapper.getListByDay(paramMap);
	}

	public List<MchtMonthlyCollectionsCustom> getMonthlyCollectionsByMonth(HashMap<String, String> paramMap) {
		return mchtMonthlyCollectionsCustomMapper.getMonthlyCollectionsByMonth(paramMap);
	}
	
	
}
