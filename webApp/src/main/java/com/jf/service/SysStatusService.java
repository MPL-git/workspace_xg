package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SysStatusMapper;
import com.jf.entity.SysStatus;
import com.jf.entity.SysStatusExample;

@Service
@Transactional
public class SysStatusService extends BaseService<SysStatus, SysStatusExample> {
	@Autowired
	private SysStatusMapper sysStatusMapper;

	@Autowired
	public void setSysStatusMapper(SysStatusMapper sysStatusMapper) {
		super.setDao(sysStatusMapper);
		this.sysStatusMapper = sysStatusMapper;
	}
}
