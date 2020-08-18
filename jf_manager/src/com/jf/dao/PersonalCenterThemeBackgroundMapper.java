package com.jf.dao;

import com.jf.entity.PersonalCenterThemeBackground;
import com.jf.entity.PersonalCenterThemeBackgroundExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalCenterThemeBackgroundMapper extends BaseDao<PersonalCenterThemeBackground, PersonalCenterThemeBackgroundExample>{
    int countByExample(PersonalCenterThemeBackgroundExample example);

    int deleteByExample(PersonalCenterThemeBackgroundExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PersonalCenterThemeBackground record);

    int insertSelective(PersonalCenterThemeBackground record);

    List<PersonalCenterThemeBackground> selectByExample(PersonalCenterThemeBackgroundExample example);

    PersonalCenterThemeBackground selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PersonalCenterThemeBackground record, @Param("example") PersonalCenterThemeBackgroundExample example);

    int updateByExample(@Param("record") PersonalCenterThemeBackground record, @Param("example") PersonalCenterThemeBackgroundExample example);

    int updateByPrimaryKeySelective(PersonalCenterThemeBackground record);

    int updateByPrimaryKey(PersonalCenterThemeBackground record);
}