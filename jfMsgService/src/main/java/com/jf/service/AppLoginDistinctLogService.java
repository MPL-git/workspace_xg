package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.AppLoginDistinctLogCustomMapper;
import com.jf.dao.AppLoginDistinctLogMapper;
import com.jf.entity.AppLoginDistinctLog;
import com.jf.entity.AppLoginDistinctLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author Pengl
 * @create 2019-11-21 下午 4:11
 */
@Service
@Transactional
public class AppLoginDistinctLogService extends BaseService<AppLoginDistinctLog, AppLoginDistinctLogExample> {

	@Autowired
	private AppLoginDistinctLogMapper appLoginDistinctLogMapper;

	@Autowired
	private AppLoginDistinctLogCustomMapper appLoginDistinctLogCustomMapper;

	@Autowired
	public void setAppLoginDistinctLogMapper(AppLoginDistinctLogMapper appLoginDistinctLogMapper) {
		this.setDao(appLoginDistinctLogMapper);
		this.appLoginDistinctLogMapper = appLoginDistinctLogMapper;
	}

	public void insertSelect(Map<String, Object> paramMap) {
		appLoginDistinctLogCustomMapper.insertSelect(paramMap);
	}



}
