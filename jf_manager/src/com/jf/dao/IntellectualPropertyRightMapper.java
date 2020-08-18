package com.jf.dao;

import com.jf.entity.IntellectualPropertyRight;
import com.jf.entity.IntellectualPropertyRightExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IntellectualPropertyRightMapper extends BaseDao<IntellectualPropertyRight, IntellectualPropertyRightExample>{
    int countByExample(IntellectualPropertyRightExample example);

    int deleteByExample(IntellectualPropertyRightExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IntellectualPropertyRight record);

    int insertSelective(IntellectualPropertyRight record);

    List<IntellectualPropertyRight> selectByExample(IntellectualPropertyRightExample example);

    IntellectualPropertyRight selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IntellectualPropertyRight record, @Param("example") IntellectualPropertyRightExample example);

    int updateByExample(@Param("record") IntellectualPropertyRight record, @Param("example") IntellectualPropertyRightExample example);

    int updateByPrimaryKeySelective(IntellectualPropertyRight record);

    int updateByPrimaryKey(IntellectualPropertyRight record);
}