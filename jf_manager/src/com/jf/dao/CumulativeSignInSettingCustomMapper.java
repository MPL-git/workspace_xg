package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.CumulativeSignInSetting;
import com.jf.entity.CumulativeSignInSettingCustom;
import com.jf.entity.CumulativeSignInSettingExample;
@Repository
public interface CumulativeSignInSettingCustomMapper{
    int countByExample(CumulativeSignInSettingExample example);
    List<CumulativeSignInSettingCustom> selectByExample(CumulativeSignInSettingExample example);
	void batchInsert(List<CumulativeSignInSetting> cumulativeSignInSettingList);
	String getSingleProductActivityStatus(Integer productId);
}