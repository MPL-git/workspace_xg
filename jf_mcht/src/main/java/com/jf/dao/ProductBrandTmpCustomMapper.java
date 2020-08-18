package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ProductBrandTmp;
import com.jf.entity.ProductBrandTmpCustom;
import com.jf.entity.ProductBrandTmpCustomExample;

@Repository
public interface ProductBrandTmpCustomMapper {
    List<ProductBrandTmpCustom> selectByExample(ProductBrandTmpCustomExample example);
    ProductBrandTmpCustom selectByPrimaryKey(Integer id);
}