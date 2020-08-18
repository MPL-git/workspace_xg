package com.jf.dao;

import com.jf.entity.IntegralGive;
import com.jf.entity.IntegralGiveExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface IntegralGiveMapper extends BaseDao<IntegralGive, IntegralGiveExample> {
    int countByExample(IntegralGiveExample example);

    int deleteByExample(IntegralGiveExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IntegralGive record);

    int insertSelective(IntegralGive record);

    List<IntegralGive> selectByExample(IntegralGiveExample example);

    IntegralGive selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IntegralGive record, @Param("example") IntegralGiveExample example);

    int updateByExample(@Param("record") IntegralGive record, @Param("example") IntegralGiveExample example);

    int updateByPrimaryKeySelective(IntegralGive record);

    int updateByPrimaryKey(IntegralGive record);
}