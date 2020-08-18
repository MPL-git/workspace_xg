package com.jf.dao;

import com.jf.entity.ProductItem;
import com.jf.entity.ProductItemExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductItemMapper extends BaseDao<ProductItem, ProductItemExample> {
    int countByExample(ProductItemExample example);

    int deleteByExample(ProductItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductItem record);

    int insertSelective(ProductItem record);

    List<ProductItem> selectByExample(ProductItemExample example);

    ProductItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductItem record, @Param("example") ProductItemExample example);

    int updateByExample(@Param("record") ProductItem record, @Param("example") ProductItemExample example);

    int updateByPrimaryKeySelective(ProductItem record);

    int updateByPrimaryKey(ProductItem record);
}