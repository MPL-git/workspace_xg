package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.CumulativeSignInSetting;
import com.jf.entity.CumulativeSignInSettingExample;
@Repository
public interface CumulativeSignInSettingMapper extends BaseDao<CumulativeSignInSetting, CumulativeSignInSettingExample>{
    int countByExample(CumulativeSignInSettingExample example);

    int deleteByExample(CumulativeSignInSettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CumulativeSignInSetting record);

    int insertSelective(CumulativeSignInSetting record);

    List<CumulativeSignInSetting> selectByExample(CumulativeSignInSettingExample example);

    CumulativeSignInSetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CumulativeSignInSetting record, @Param("example") CumulativeSignInSettingExample example);

    int updateByExample(@Param("record") CumulativeSignInSetting record, @Param("example") CumulativeSignInSettingExample example);

    int updateByPrimaryKeySelective(CumulativeSignInSetting record);

    int updateByPrimaryKey(CumulativeSignInSetting record);
}