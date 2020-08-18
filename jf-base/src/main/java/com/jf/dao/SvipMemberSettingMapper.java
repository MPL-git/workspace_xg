package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SvipMemberSetting;
import com.jf.entity.SvipMemberSettingExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SvipMemberSettingMapper extends BaseDao<SvipMemberSetting, SvipMemberSettingExample> {
    int countByExample(SvipMemberSettingExample example);

    int deleteByExample(SvipMemberSettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SvipMemberSetting record);

    int insertSelective(SvipMemberSetting record);

    List<SvipMemberSetting> selectByExample(SvipMemberSettingExample example);

    SvipMemberSetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SvipMemberSetting record, @Param("example") SvipMemberSettingExample example);

    int updateByExample(@Param("record") SvipMemberSetting record, @Param("example") SvipMemberSettingExample example);

    int updateByPrimaryKeySelective(SvipMemberSetting record);

    int updateByPrimaryKey(SvipMemberSetting record);
}
