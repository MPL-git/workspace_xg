package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.SignInSetting;
import com.jf.entity.SignInSettingCustom;
import com.jf.entity.SignInSettingExample;
@Repository
public interface SignInSettingCustomMapper{
    int countByExample(SignInSettingExample example);
    List<SignInSettingCustom> selectByExample(SignInSettingExample example);
	void batchInsert(List<SignInSetting> signInSettingList);
}