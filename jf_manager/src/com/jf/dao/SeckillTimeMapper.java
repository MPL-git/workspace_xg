package com.jf.dao;

import com.jf.entity.SeckillTime;
import com.jf.entity.SeckillTimeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SeckillTimeMapper extends BaseDao<SeckillTime, SeckillTimeExample> {
    int countByExample(SeckillTimeExample example);

    int deleteByExample(SeckillTimeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SeckillTime record);

    int insertSelective(SeckillTime record);

    List<SeckillTime> selectByExample(SeckillTimeExample example);

    SeckillTime selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SeckillTime record, @Param("example") SeckillTimeExample example);

    int updateByExample(@Param("record") SeckillTime record, @Param("example") SeckillTimeExample example);

    int updateByPrimaryKeySelective(SeckillTime record);

    int updateByPrimaryKey(SeckillTime record);
}