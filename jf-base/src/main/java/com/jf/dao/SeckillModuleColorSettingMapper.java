package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.common.base.BaseDao;
import com.jf.entity.SeckillModuleColorSetting;
import com.jf.entity.SeckillModuleColorSettingExample;
@Repository
public interface SeckillModuleColorSettingMapper extends BaseDao<SeckillModuleColorSetting, SeckillModuleColorSettingExample>{
    int countByExample(SeckillModuleColorSettingExample example);

    int deleteByExample(SeckillModuleColorSettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SeckillModuleColorSetting record);

    int insertSelective(SeckillModuleColorSetting record);

    List<SeckillModuleColorSetting> selectByExample(SeckillModuleColorSettingExample example);

    SeckillModuleColorSetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SeckillModuleColorSetting record, @Param("example") SeckillModuleColorSettingExample example);

    int updateByExample(@Param("record") SeckillModuleColorSetting record, @Param("example") SeckillModuleColorSettingExample example);

    int updateByPrimaryKeySelective(SeckillModuleColorSetting record);

    int updateByPrimaryKey(SeckillModuleColorSetting record);
}