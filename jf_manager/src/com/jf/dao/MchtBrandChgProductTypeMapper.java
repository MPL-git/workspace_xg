package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MchtBrandChgProductType;
import com.jf.entity.MchtBrandChgProductTypeExample;
@Repository
public interface MchtBrandChgProductTypeMapper extends BaseDao<MchtBrandChgProductType, MchtBrandChgProductTypeExample>{
    int countByExample(MchtBrandChgProductTypeExample example);

    int deleteByExample(MchtBrandChgProductTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtBrandChgProductType record);

    int insertSelective(MchtBrandChgProductType record);

    List<MchtBrandChgProductType> selectByExample(MchtBrandChgProductTypeExample example);

    MchtBrandChgProductType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtBrandChgProductType record, @Param("example") MchtBrandChgProductTypeExample example);

    int updateByExample(@Param("record") MchtBrandChgProductType record, @Param("example") MchtBrandChgProductTypeExample example);

    int updateByPrimaryKeySelective(MchtBrandChgProductType record);

    int updateByPrimaryKey(MchtBrandChgProductType record);
}