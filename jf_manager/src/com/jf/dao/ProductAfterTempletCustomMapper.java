package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ProductAfterTempletCustom;
import com.jf.entity.ProductAfterTempletCustomExample;
@Repository
public interface ProductAfterTempletCustomMapper{
    int countByExample(ProductAfterTempletCustomExample example);
    List<ProductAfterTempletCustom> selectByExample(ProductAfterTempletCustomExample example);
    ProductAfterTempletCustom selectByPrimaryKey(Integer id);
}