package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SvipMarketingSetting;
import com.jf.entity.SvipMarketingSettingExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SvipMarketingSettingMapper extends BaseDao<SvipMarketingSetting, SvipMarketingSettingExample> {
    int countByExample(SvipMarketingSettingExample example);

    int deleteByExample(SvipMarketingSettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SvipMarketingSetting record);

    int insertSelective(SvipMarketingSetting record);

    List<SvipMarketingSetting> selectByExample(SvipMarketingSettingExample example);

    SvipMarketingSetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SvipMarketingSetting record, @Param("example") SvipMarketingSettingExample example);

    int updateByExample(@Param("record") SvipMarketingSetting record, @Param("example") SvipMarketingSettingExample example);

    int updateByPrimaryKeySelective(SvipMarketingSetting record);

    int updateByPrimaryKey(SvipMarketingSetting record);
}