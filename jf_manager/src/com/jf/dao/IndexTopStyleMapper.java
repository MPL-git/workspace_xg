package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.IndexTopStyle;
import com.jf.entity.IndexTopStyleExample;
@Repository
public interface IndexTopStyleMapper extends BaseDao<IndexTopStyle, IndexTopStyleExample>{
    int countByExample(IndexTopStyleExample example);

    int deleteByExample(IndexTopStyleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IndexTopStyle record);

    int insertSelective(IndexTopStyle record);

    List<IndexTopStyle> selectByExample(IndexTopStyleExample example);

    IndexTopStyle selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IndexTopStyle record, @Param("example") IndexTopStyleExample example);

    int updateByExample(@Param("record") IndexTopStyle record, @Param("example") IndexTopStyleExample example);

    int updateByPrimaryKeySelective(IndexTopStyle record);

    int updateByPrimaryKey(IndexTopStyle record);
}