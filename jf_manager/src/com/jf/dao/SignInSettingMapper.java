package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.SignInSetting;
import com.jf.entity.SignInSettingExample;
@Repository
public interface SignInSettingMapper extends BaseDao<SignInSetting, SignInSettingExample>{
    int countByExample(SignInSettingExample example);

    int deleteByExample(SignInSettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SignInSetting record);

    int insertSelective(SignInSetting record);

    List<SignInSetting> selectByExample(SignInSettingExample example);

    SignInSetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SignInSetting record, @Param("example") SignInSettingExample example);

    int updateByExample(@Param("record") SignInSetting record, @Param("example") SignInSettingExample example);

    int updateByPrimaryKeySelective(SignInSetting record);

    int updateByPrimaryKey(SignInSetting record);
}