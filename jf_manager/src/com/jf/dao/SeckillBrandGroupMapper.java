package com.jf.dao;

import com.jf.entity.SeckillBrandGroup;
import com.jf.entity.SeckillBrandGroupExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SeckillBrandGroupMapper extends BaseDao<SeckillBrandGroup, SeckillBrandGroupExample> {
    int countByExample(SeckillBrandGroupExample example);

    int deleteByExample(SeckillBrandGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SeckillBrandGroup record);

    int insertSelective(SeckillBrandGroup record);

    List<SeckillBrandGroup> selectByExample(SeckillBrandGroupExample example);

    SeckillBrandGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SeckillBrandGroup record, @Param("example") SeckillBrandGroupExample example);

    int updateByExample(@Param("record") SeckillBrandGroup record, @Param("example") SeckillBrandGroupExample example);

    int updateByPrimaryKeySelective(SeckillBrandGroup record);

    int updateByPrimaryKey(SeckillBrandGroup record);
}