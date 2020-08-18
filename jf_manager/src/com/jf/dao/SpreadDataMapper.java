package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.SpreadData;
import com.jf.entity.SpreadDataExample;

@Repository
public interface SpreadDataMapper extends BaseDao<SpreadData, SpreadDataExample>{
    int countByExample(SpreadDataExample example);

    int deleteByExample(SpreadDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpreadData record);

    int insertSelective(SpreadData record);

    List<SpreadData> selectByExample(SpreadDataExample example);

    SpreadData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpreadData record, @Param("example") SpreadDataExample example);

    int updateByExample(@Param("record") SpreadData record, @Param("example") SpreadDataExample example);

    int updateByPrimaryKeySelective(SpreadData record);

    int updateByPrimaryKey(SpreadData record);
}