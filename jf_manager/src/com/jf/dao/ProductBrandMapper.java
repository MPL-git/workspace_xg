package com.jf.dao;

import com.jf.entity.ProductBrand;
import com.jf.entity.ProductBrandExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductBrandMapper extends BaseDao<ProductBrand, ProductBrandExample>{
    int countByExample(ProductBrandExample example);

    int deleteByExample(ProductBrandExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductBrand record);

    int insertSelective(ProductBrand record);

    List<ProductBrand> selectByExample(ProductBrandExample example);

    ProductBrand selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductBrand record, @Param("example") ProductBrandExample example);

    int updateByExample(@Param("record") ProductBrand record, @Param("example") ProductBrandExample example);

    int updateByPrimaryKeySelective(ProductBrand record);

    int updateByPrimaryKey(ProductBrand record);
}