package com.jf.dao;

import com.jf.entity.ProductAfterTemplet;
import com.jf.entity.ProductAfterTempletExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductAfterTempletMapper extends BaseDao<ProductAfterTemplet, ProductAfterTempletExample>{
    int countByExample(ProductAfterTempletExample example);

    int deleteByExample(ProductAfterTempletExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductAfterTemplet record);

    int insertSelective(ProductAfterTemplet record);

    List<ProductAfterTemplet> selectByExample(ProductAfterTempletExample example);

    ProductAfterTemplet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductAfterTemplet record, @Param("example") ProductAfterTempletExample example);

    int updateByExample(@Param("record") ProductAfterTemplet record, @Param("example") ProductAfterTempletExample example);

    int updateByPrimaryKeySelective(ProductAfterTemplet record);

    int updateByPrimaryKey(ProductAfterTemplet record);
}