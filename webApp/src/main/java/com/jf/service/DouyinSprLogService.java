package com.jf.service;

import java.util.Date;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.DouyinSprLogMapper;
import com.jf.entity.DouyinSprLog;
import com.jf.entity.DouyinSprLogExample;

@Service
@Transactional
public class DouyinSprLogService extends BaseService<DouyinSprLog, DouyinSprLogExample> {
	@Autowired
	private DouyinSprLogMapper douyinSprLogMapper;
	@Autowired
	public void setDouyinSprLogMapper(DouyinSprLogMapper douyinSprLogMapper) {
		this.setDao(douyinSprLogMapper);
		this.douyinSprLogMapper = douyinSprLogMapper;
	}
	public void addModel(Integer sprPlanId, Integer memberId, String regIp) {
		DouyinSprLog sprLog = new DouyinSprLog();
		sprLog.setSprPlanId(sprPlanId);
		sprLog.setMemberId(memberId);
		sprLog.setIp(regIp);
		sprLog.setCreateDate(new Date());
		sprLog.setDelFlag("0");
		douyinSprLogMapper.insert(sprLog);
	}
	
	
}
