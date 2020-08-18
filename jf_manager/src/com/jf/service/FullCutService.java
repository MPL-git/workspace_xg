package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.FullCutCustomMapper;
import com.jf.dao.FullCutMapper;
import com.jf.entity.FullCut;
import com.jf.entity.FullCutCustom;
import com.jf.entity.FullCutExample;
@Service
@Transactional
public class FullCutService extends BaseService<FullCut, FullCutExample> {

	@Autowired
	private FullCutMapper fullCutMapper;
	
	@Autowired
	private FullCutCustomMapper fullCutCustomMapper;
	
	@Autowired
	public void setFullCutMapper(FullCutMapper fullCutMapper) {
		super.setDao(fullCutMapper);
		this.fullCutMapper = fullCutMapper;
	}
	/**
	 * 通过会场Id搜索满减表
	 * @param activityId
	 * @return
	 */
	public FullCutCustom selectByActivityIdList(Integer activityId){
		return fullCutCustomMapper.selectByActivityIdList(activityId);
	}
	/**
	 * 详情
	 * @param activityId
	 * @return
	 */
	public FullCutCustom selectByActivityId(Integer activityId){
		return fullCutCustomMapper.selectByActivityId(activityId);
	}
	
}
