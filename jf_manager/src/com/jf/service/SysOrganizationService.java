package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SysOrganizationMapper;
import com.jf.entity.SysOrganization;
import com.jf.entity.SysOrganizationExample;

@Service
@Transactional
public class SysOrganizationService extends BaseService<SysOrganization, SysOrganizationExample> {

	@Autowired
	private SysOrganizationMapper sysOrganizationMapper;

	@Autowired
	public void setSysOrganizationMapper(SysOrganizationMapper sysOrganizationMapper) {
		super.setDao(sysOrganizationMapper);
		this.sysOrganizationMapper = sysOrganizationMapper;
	}
	
}
