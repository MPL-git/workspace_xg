package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SignInSettingCustomMapper;
import com.jf.dao.SignInSettingMapper;
import com.jf.entity.SignInSetting;
import com.jf.entity.SignInSettingCustom;
import com.jf.entity.SignInSettingExample;

@Service
@Transactional
public class SignInSettingService extends BaseService<SignInSetting,SignInSettingExample> {
	@Autowired
	private SignInSettingMapper signInSettingMapper;
	@Autowired
	private SignInSettingCustomMapper signInSettingCustomMapper;
	@Autowired
	public void setSignInSettingMapper(SignInSettingMapper signInSettingMapper) {
		super.setDao(signInSettingMapper);
		this.signInSettingMapper = signInSettingMapper;
	}
	
	public int countSignInSettingCustomByExample(SignInSettingExample example){
		return signInSettingCustomMapper.countByExample(example);
	}
    public List<SignInSettingCustom> selectSignInSettingCustomByExample(SignInSettingExample example){
    	return signInSettingCustomMapper.selectByExample(example);
    }

	public void batchInsert(List<SignInSetting> signInSettingList) {
		signInSettingCustomMapper.batchInsert(signInSettingList);
	}
}
