package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.IndexTopTabCustomMapper;
import com.jf.dao.IndexTopTabMapper;
import com.jf.entity.IndexTopTab;
import com.jf.entity.IndexTopTabCustom;
import com.jf.entity.IndexTopTabExample;

@Service
@Transactional
public class IndexTopTabService extends BaseService<IndexTopTab,IndexTopTabExample> {
	@Autowired
	private IndexTopTabMapper dao;
	
	@Autowired
	private IndexTopTabCustomMapper indexTopTabCustomMapper;
	
	
	@Autowired
	public void setIndexTopTabMapper(IndexTopTabMapper indexTopTabMapper) {
		super.setDao(indexTopTabMapper);
		this.dao = indexTopTabMapper;
	}

	public int countByCustomExample(IndexTopTabExample example) {
		return indexTopTabCustomMapper.countByExample(example);
	}
	
	public List<IndexTopTabCustom> selectByCustomExample(IndexTopTabExample example) {
		return indexTopTabCustomMapper.selectByExample(example);
	}

	public void update(IndexTopTab indexTopTab) {
		indexTopTabCustomMapper.update(indexTopTab);
	}

}
