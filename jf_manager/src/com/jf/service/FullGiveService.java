package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.FullGiveCustomMapper;
import com.jf.dao.FullGiveMapper;
import com.jf.entity.FullGive;
import com.jf.entity.FullGiveExample;
@Service
@Transactional
public class FullGiveService extends BaseService<FullGive, FullGiveExample> {

	@Autowired
	private FullGiveMapper fullGiveMapper;
	
	@Autowired
	private FullGiveCustomMapper fullGiveCustomMapper;
	
	@Autowired
	public void setFullCutMapper(FullGiveMapper fullGiveMapper) {
		super.setDao(fullGiveMapper);
		this.fullGiveMapper = fullGiveMapper;
	}
	/**
	 * 详情
	 * @param activityId
	 * @return
	 */
	public FullGive selectByActivityId(Integer activityId){
		return fullGiveCustomMapper.selectByActivityId(activityId);
	}
	
}
