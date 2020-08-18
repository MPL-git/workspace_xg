package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SignInManageMapper;
import com.jf.entity.SignInManage;
import com.jf.entity.SignInManageExample;

@Service
@Transactional
public class SignInManageService extends BaseService<SignInManage, SignInManageExample> {
	@Autowired
	private SignInManageMapper signInManageMapper;
	@Autowired
	public void setSignInManageMapper(SignInManageMapper signInManageMapper) {
		this.setDao(signInManageMapper);
		this.signInManageMapper = signInManageMapper;
	}
	
	
}
