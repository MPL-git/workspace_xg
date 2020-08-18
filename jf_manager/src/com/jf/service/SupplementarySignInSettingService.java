package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SupplementarySignInSettingCustomMapper;
import com.jf.dao.SupplementarySignInSettingMapper;
import com.jf.entity.SupplementarySignInSetting;
import com.jf.entity.SupplementarySignInSettingCustom;
import com.jf.entity.SupplementarySignInSettingExample;

@Service
@Transactional
public class SupplementarySignInSettingService extends BaseService<SupplementarySignInSetting,SupplementarySignInSettingExample> {
	@Autowired
	private SupplementarySignInSettingMapper supplementarySignInSettingMapper;
	@Autowired
	private SupplementarySignInSettingCustomMapper supplementarySignInSettingCustomMapper;
	@Autowired
	public void setSupplementarySignInSettingMapper(SupplementarySignInSettingMapper supplementarySignInSettingMapper) {
		super.setDao(supplementarySignInSettingMapper);
		this.supplementarySignInSettingMapper = supplementarySignInSettingMapper;
	}
	
	public int countSupplementarySignInSettingCustomByExample(SupplementarySignInSettingExample example){
		return supplementarySignInSettingCustomMapper.countByExample(example);
	}
    public List<SupplementarySignInSettingCustom> selectSupplementarySignInSettingCustomByExample(SupplementarySignInSettingExample example){
    	return supplementarySignInSettingCustomMapper.selectByExample(example);
    }
}
