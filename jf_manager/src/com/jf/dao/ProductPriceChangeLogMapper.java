package com.jf.dao;

import com.jf.entity.ProductPriceChangeLog;
import com.jf.entity.ProductPriceChangeLogExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceChangeLogMapper extends BaseDao<ProductPriceChangeLog, ProductPriceChangeLogExample> {
    int countByExample(ProductPriceChangeLogExample example);

    int deleteByExample(ProductPriceChangeLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductPriceChangeLog record);

    int insertSelective(ProductPriceChangeLog record);

    List<ProductPriceChangeLog> selectByExample(ProductPriceChangeLogExample example);

    ProductPriceChangeLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductPriceChangeLog record, @Param("example") ProductPriceChangeLogExample example);

    int updateByExample(@Param("record") ProductPriceChangeLog record, @Param("example") ProductPriceChangeLogExample example);

    int updateByPrimaryKeySelective(ProductPriceChangeLog record);

    int updateByPrimaryKey(ProductPriceChangeLog record);
}