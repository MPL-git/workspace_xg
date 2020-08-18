package com.jf.dao;

import com.jf.entity.ProductProp;
import com.jf.entity.ProductPropExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductPropMapper extends BaseDao<ProductProp, ProductPropExample>{
    int countByExample(ProductPropExample example);

    int deleteByExample(ProductPropExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductProp record);

    int insertSelective(ProductProp record);

    List<ProductProp> selectByExample(ProductPropExample example);

    ProductProp selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductProp record, @Param("example") ProductPropExample example);

    int updateByExample(@Param("record") ProductProp record, @Param("example") ProductPropExample example);

    int updateByPrimaryKeySelective(ProductProp record);

    int updateByPrimaryKey(ProductProp record);
}