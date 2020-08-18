package com.jf.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberSignInSettingCustom;
import com.jf.entity.MemberSignInSettingCustomExample;
@Repository
public interface MemberSignInSettingCustomMapper{
    int countByExample(MemberSignInSettingCustomExample example);
    List<MemberSignInSettingCustom> selectByExample(MemberSignInSettingCustomExample example);
	Integer countSignInMemberManageList(Map<String, Object> paramMap);
	List<HashMap<String, Object>> selectSignInMemberManageList(Map<String, Object> paramMap);
}