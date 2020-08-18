package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SignInCnfDtlMapper;
import com.jf.entity.SignInCnfDtl;
import com.jf.entity.SignInCnfDtlExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年5月17日 上午9:22:43 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class SignInCnfDtlService extends BaseService<SignInCnfDtl, SignInCnfDtlExample> {
	@Autowired
	private SignInCnfDtlMapper signInCnfDtlMapper;

	@Autowired
	public void setSignInCnfDtlMapper(SignInCnfDtlMapper signInCnfDtlMapper) {
		this.setDao(signInCnfDtlMapper);
		this.signInCnfDtlMapper = signInCnfDtlMapper;
	}
	
	
}
