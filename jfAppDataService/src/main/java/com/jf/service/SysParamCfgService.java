package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SysParamCfgMapper;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;

@Service
@Transactional
public class SysParamCfgService extends BaseService<SysParamCfg, SysParamCfgExample, SysParamCfgMapper> {

	@Autowired
	private SysParamCfgMapper sysParamCfgMapper;
	
}
