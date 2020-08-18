package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SignInCnfDtlMapper;
import com.jf.entity.SignInCnfDtl;
import com.jf.entity.SignInCnfDtlExample;

@Service
@Transactional
public class SignInCnfDtlService extends BaseService<SignInCnfDtl, SignInCnfDtlExample> {

	@Autowired
	private SignInCnfDtlMapper signInCnfDtlMapper;
	
	@Autowired
	public void setSignInCnfDtlMapper(SignInCnfDtlMapper signInCnfDtlMapper) {
		super.setDao(signInCnfDtlMapper);
		this.signInCnfDtlMapper = signInCnfDtlMapper;
	}
	
	
	
}
