package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SupplementarySignInSetting;
import com.jf.entity.SupplementarySignInSettingExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplementarySignInSettingMapper extends BaseDao<SupplementarySignInSetting, SupplementarySignInSettingExample> {
    int countByExample(SupplementarySignInSettingExample example);

    int deleteByExample(SupplementarySignInSettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SupplementarySignInSetting record);

    int insertSelective(SupplementarySignInSetting record);

    List<SupplementarySignInSetting> selectByExample(SupplementarySignInSettingExample example);

    SupplementarySignInSetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SupplementarySignInSetting record, @Param("example") SupplementarySignInSettingExample example);

    int updateByExample(@Param("record") SupplementarySignInSetting record, @Param("example") SupplementarySignInSettingExample example);

    int updateByPrimaryKeySelective(SupplementarySignInSetting record);

    int updateByPrimaryKey(SupplementarySignInSetting record);
}
