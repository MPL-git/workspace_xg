package com.jf.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MemberSignInSettingCustomMapper;
import com.jf.dao.MemberSignInSettingMapper;
import com.jf.entity.MemberSignInSetting;
import com.jf.entity.MemberSignInSettingCustom;
import com.jf.entity.MemberSignInSettingCustomExample;
import com.jf.entity.MemberSignInSettingExample;

@Service
@Transactional
public class MemberSignInSettingService extends BaseService<MemberSignInSetting,MemberSignInSettingExample> {
	@Autowired
	private MemberSignInSettingMapper memberSignInSettingMapper;
	@Autowired
	private MemberSignInSettingCustomMapper memberSignInSettingCustomMapper;
	@Autowired
	public void setMemberSignInSettingMapper(MemberSignInSettingMapper memberSignInSettingMapper) {
		super.setDao(memberSignInSettingMapper);
		this.memberSignInSettingMapper = memberSignInSettingMapper;
	}
	
	public int countMemberSignInSettingCustomByExample(MemberSignInSettingCustomExample example){
		return memberSignInSettingCustomMapper.countByExample(example);
	}
    public List<MemberSignInSettingCustom> selectMemberSignInSettingCustomByExample(MemberSignInSettingCustomExample example){
    	return memberSignInSettingCustomMapper.selectByExample(example);
    }

	public Integer countSignInMemberManageList(Map<String, Object> paramMap) {
		return memberSignInSettingCustomMapper.countSignInMemberManageList(paramMap);
	}

	public List<HashMap<String, Object>> selectSignInMemberManageList(Map<String, Object> paramMap) {
		return memberSignInSettingCustomMapper.selectSignInMemberManageList(paramMap);
	}
}
