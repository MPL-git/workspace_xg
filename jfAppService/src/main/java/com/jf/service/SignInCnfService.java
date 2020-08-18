package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.SignInCnfMapper;
import com.jf.entity.SignInCnf;
import com.jf.entity.SignInCnfExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年5月17日 上午9:23:57 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class SignInCnfService extends BaseService<SignInCnf, SignInCnfExample> {
	@Autowired
	private SignInCnfMapper signInCnfMapper;

	@Autowired
	public void setSignInCnfMapper(SignInCnfMapper signInCnfMapper) {
		this.setDao(signInCnfMapper);
		this.signInCnfMapper = signInCnfMapper;
	}
	
	
}
