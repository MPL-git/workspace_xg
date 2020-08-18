package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MemberSignInSetting;
import com.jf.entity.MemberSignInSettingExample;
@Repository
public interface MemberSignInSettingMapper extends BaseDao<MemberSignInSetting, MemberSignInSettingExample>{
    int countByExample(MemberSignInSettingExample example);

    int deleteByExample(MemberSignInSettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberSignInSetting record);

    int insertSelective(MemberSignInSetting record);

    List<MemberSignInSetting> selectByExample(MemberSignInSettingExample example);

    MemberSignInSetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberSignInSetting record, @Param("example") MemberSignInSettingExample example);

    int updateByExample(@Param("record") MemberSignInSetting record, @Param("example") MemberSignInSettingExample example);

    int updateByPrimaryKeySelective(MemberSignInSetting record);

    int updateByPrimaryKey(MemberSignInSetting record);
}