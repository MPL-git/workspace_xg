package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.IntellectualPropertyRightAppeal;
import com.jf.entity.IntellectualPropertyRightAppealExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntellectualPropertyRightAppealMapper extends BaseDao<IntellectualPropertyRightAppeal, IntellectualPropertyRightAppealExample> {
    int countByExample(IntellectualPropertyRightAppealExample example);

    int deleteByExample(IntellectualPropertyRightAppealExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IntellectualPropertyRightAppeal record);

    int insertSelective(IntellectualPropertyRightAppeal record);

    List<IntellectualPropertyRightAppeal> selectByExample(IntellectualPropertyRightAppealExample example);

    IntellectualPropertyRightAppeal selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IntellectualPropertyRightAppeal record, @Param("example") IntellectualPropertyRightAppealExample example);

    int updateByExample(@Param("record") IntellectualPropertyRightAppeal record, @Param("example") IntellectualPropertyRightAppealExample example);

    int updateByPrimaryKeySelective(IntellectualPropertyRightAppeal record);

    int updateByPrimaryKey(IntellectualPropertyRightAppeal record);
}
