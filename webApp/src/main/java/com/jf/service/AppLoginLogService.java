package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.AppLoginLogMapper;
import com.jf.entity.AppLoginLog;
import com.jf.entity.AppLoginLogExample;

@Service
@Transactional
public class AppLoginLogService extends BaseService<AppLoginLog,AppLoginLogExample> {
	@Autowired
	private AppLoginLogMapper appLoginLogMapper;
	
	@Autowired
	public void setAppLoginLogMapper(AppLoginLogMapper appLoginLogMapper) {
		super.setDao(appLoginLogMapper);
		this.appLoginLogMapper = appLoginLogMapper;
	}

	public AppLoginLog saveModel(AppLoginLog appLoginLog) {
		appLoginLogMapper.insertSelective(appLoginLog);
		return appLoginLog;
	}
	
}
