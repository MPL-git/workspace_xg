package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SysAppMessageMapper;
import com.jf.entity.SysAppMessage;
import com.jf.entity.SysAppMessageExample;

@Service
@Transactional
public class SysAppMessageService extends BaseService<SysAppMessage, SysAppMessageExample> {
	@Autowired
	private SysAppMessageMapper sysAppMessageMapper;

	@Autowired
	public void setSysAppMessageMapper(SysAppMessageMapper sysAppMessageMapper) {
		super.setDao(sysAppMessageMapper);
		this.sysAppMessageMapper = sysAppMessageMapper;
	}
}
