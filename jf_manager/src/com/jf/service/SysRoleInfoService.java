package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SysRoleInfoMapper;
import com.jf.entity.SysRoleInfo;
import com.jf.entity.SysRoleInfoExample;

@Service
@Transactional
public class SysRoleInfoService extends BaseService<SysRoleInfo, SysRoleInfoExample> {

	@Autowired
	private SysRoleInfoMapper sysRoleInfoMapper;
	
	@Autowired
	public void setSysRoleInfoMapper(SysRoleInfoMapper sysRoleInfoMapper) {
		super.setDao(sysRoleInfoMapper);
		this.sysRoleInfoMapper = sysRoleInfoMapper;
	}
	
}
