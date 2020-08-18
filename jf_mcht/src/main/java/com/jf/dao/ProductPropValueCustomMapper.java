package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.ProductPropValue;
import com.jf.entity.ProductPropValueCustom;
import com.jf.entity.ProductPropValueExample;
@Repository
public interface ProductPropValueCustomMapper{
    int countByExample(ProductPropValueExample example);


    List<ProductPropValueCustom> selectByExample(ProductPropValueExample example);

    ProductPropValueCustom selectByPrimaryKey(Integer id);
    

}