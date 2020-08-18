package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.common.base.BaseDao;
import com.jf.entity.ActivityAreaSetting;
import com.jf.entity.ActivityAreaSettingExample;
@Repository
public interface ActivityAreaSettingMapper extends BaseDao<ActivityAreaSetting,ActivityAreaSettingExample> {
    int countByExample(ActivityAreaSettingExample example);

    int deleteByExample(ActivityAreaSettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ActivityAreaSetting record);

    int insertSelective(ActivityAreaSetting record);

    List<ActivityAreaSetting> selectByExample(ActivityAreaSettingExample example);

    ActivityAreaSetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ActivityAreaSetting record, @Param("example") ActivityAreaSettingExample example);

    int updateByExample(@Param("record") ActivityAreaSetting record, @Param("example") ActivityAreaSettingExample example);

    int updateByPrimaryKeySelective(ActivityAreaSetting record);

    int updateByPrimaryKey(ActivityAreaSetting record);
}