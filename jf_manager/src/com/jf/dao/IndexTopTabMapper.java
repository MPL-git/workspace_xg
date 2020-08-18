package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.IndexTopTab;
import com.jf.entity.IndexTopTabExample;
@Repository
public interface IndexTopTabMapper extends BaseDao<IndexTopTab, IndexTopTabExample>{
    int countByExample(IndexTopTabExample example);

    int deleteByExample(IndexTopTabExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IndexTopTab record);

    int insertSelective(IndexTopTab record);

    List<IndexTopTab> selectByExample(IndexTopTabExample example);

    IndexTopTab selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IndexTopTab record, @Param("example") IndexTopTabExample example);

    int updateByExample(@Param("record") IndexTopTab record, @Param("example") IndexTopTabExample example);

    int updateByPrimaryKeySelective(IndexTopTab record);

    int updateByPrimaryKey(IndexTopTab record);
}