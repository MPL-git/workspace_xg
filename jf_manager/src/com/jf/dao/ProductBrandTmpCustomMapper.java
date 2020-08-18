package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ProductBrandTmp;
import com.jf.entity.ProductBrandTmpCustom;
import com.jf.entity.ProductBrandTmpExample;

@Repository
public interface ProductBrandTmpCustomMapper extends BaseDao<ProductBrandTmp, ProductBrandTmpExample>{
    List<ProductBrandTmpCustom> selectByExampleCustom(ProductBrandTmpExample example);
}