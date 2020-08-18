package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberSignInSettingCustom;

@Repository
public interface MemberSignInSettingCustomMapper {

	List<MemberSignInSettingCustom> getMonthSignInSetting(Map<String, Object> params);

	List<MemberSignInSettingCustom> getNewSignInBroadcastContent();

}
