package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SignInManageCustomMapper;
import com.jf.dao.SignInManageMapper;
import com.jf.entity.SignInManage;
import com.jf.entity.SignInManageCustom;
import com.jf.entity.SignInManageExample;

@Service
@Transactional
public class SignInManageService extends BaseService<SignInManage,SignInManageExample> {
	@Autowired
	private SignInManageMapper signInManageMapper;
	@Autowired
	private SignInManageCustomMapper signInManageCustomMapper;
	@Autowired
	public void setSignInManageMapper(SignInManageMapper signInManageMapper) {
		super.setDao(signInManageMapper);
		this.signInManageMapper = signInManageMapper;
	}
	
	public int countSignInManageCustomByExample(SignInManageExample example){
		return signInManageCustomMapper.countByExample(example);
	}
    public List<SignInManageCustom> selectSignInManageCustomByExample(SignInManageExample example){
    	return signInManageCustomMapper.selectByExample(example);
    }
}
