package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ProductTypeCustom;

@Repository
public interface ProductTypeCustomMapper {
    List<ProductTypeCustom> getMchtUserbleProductType(int mchtId);
    List<ProductTypeCustom> getMchtUserbleSmallwareProductType(int mchtId);
}