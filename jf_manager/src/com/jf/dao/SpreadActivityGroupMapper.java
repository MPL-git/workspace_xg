package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.SpreadActivityGroup;
import com.jf.entity.SpreadActivityGroupExample;

@Repository
public interface SpreadActivityGroupMapper extends BaseDao<SpreadActivityGroup, SpreadActivityGroupExample>{
    int countByExample(SpreadActivityGroupExample example);

    int deleteByExample(SpreadActivityGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpreadActivityGroup record);

    int insertSelective(SpreadActivityGroup record);

    List<SpreadActivityGroup> selectByExample(SpreadActivityGroupExample example);

    SpreadActivityGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpreadActivityGroup record, @Param("example") SpreadActivityGroupExample example);

    int updateByExample(@Param("record") SpreadActivityGroup record, @Param("example") SpreadActivityGroupExample example);

    int updateByPrimaryKeySelective(SpreadActivityGroup record);

    int updateByPrimaryKey(SpreadActivityGroup record);
}