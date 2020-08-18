package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SysAppMessageMemberMapper;
import com.jf.entity.SysAppMessageMember;
import com.jf.entity.SysAppMessageMemberExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年6月15日 上午10:26:19 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class SysAppMessageMemberService extends BaseService<SysAppMessageMember, SysAppMessageMemberExample> {
	
	@Autowired
	private SysAppMessageMemberMapper sysAppMessageMemberMapper;

	@Autowired
	public void setSysAppMessageMemberMapper(SysAppMessageMemberMapper sysAppMessageMemberMapper) {
		this.setDao(sysAppMessageMemberMapper);
		this.sysAppMessageMemberMapper = sysAppMessageMemberMapper;
	}
	
	
	
}
