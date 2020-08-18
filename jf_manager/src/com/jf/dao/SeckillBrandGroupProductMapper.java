package com.jf.dao;

import com.jf.entity.SeckillBrandGroupProduct;
import com.jf.entity.SeckillBrandGroupProductExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SeckillBrandGroupProductMapper extends BaseDao<SeckillBrandGroupProduct, SeckillBrandGroupProductExample> {
    int countByExample(SeckillBrandGroupProductExample example);

    int deleteByExample(SeckillBrandGroupProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SeckillBrandGroupProduct record);

    int insertSelective(SeckillBrandGroupProduct record);

    List<SeckillBrandGroupProduct> selectByExample(SeckillBrandGroupProductExample example);

    SeckillBrandGroupProduct selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SeckillBrandGroupProduct record, @Param("example") SeckillBrandGroupProductExample example);

    int updateByExample(@Param("record") SeckillBrandGroupProduct record, @Param("example") SeckillBrandGroupProductExample example);

    int updateByPrimaryKeySelective(SeckillBrandGroupProduct record);

    int updateByPrimaryKey(SeckillBrandGroupProduct record);
}