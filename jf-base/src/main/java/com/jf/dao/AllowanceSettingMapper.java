package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.AllowanceSetting;
import com.jf.entity.AllowanceSettingExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllowanceSettingMapper extends BaseDao<AllowanceSetting, AllowanceSettingExample> {
    int countByExample(AllowanceSettingExample example);

    int deleteByExample(AllowanceSettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AllowanceSetting record);

    int insertSelective(AllowanceSetting record);

    List<AllowanceSetting> selectByExample(AllowanceSettingExample example);

    AllowanceSetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AllowanceSetting record, @Param("example") AllowanceSettingExample example);

    int updateByExample(@Param("record") AllowanceSetting record, @Param("example") AllowanceSettingExample example);

    int updateByPrimaryKeySelective(AllowanceSetting record);

    int updateByPrimaryKey(AllowanceSetting record);
}