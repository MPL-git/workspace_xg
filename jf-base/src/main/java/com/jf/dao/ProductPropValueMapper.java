package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ProductPropValue;
import com.jf.entity.ProductPropValueExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPropValueMapper extends BaseDao<ProductPropValue, ProductPropValueExample> {
    int countByExample(ProductPropValueExample example);

    int deleteByExample(ProductPropValueExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductPropValue record);

    int insertSelective(ProductPropValue record);

    List<ProductPropValue> selectByExample(ProductPropValueExample example);

    ProductPropValue selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductPropValue record, @Param("example") ProductPropValueExample example);

    int updateByExample(@Param("record") ProductPropValue record, @Param("example") ProductPropValueExample example);

    int updateByPrimaryKeySelective(ProductPropValue record);

    int updateByPrimaryKey(ProductPropValue record);
}
