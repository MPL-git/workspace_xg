package com.jf.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ProductBrand;
import com.jf.entity.ProductBrandCustom;
import com.jf.entity.ProductBrandExample;

@Repository
public interface ProductBrandCustomMapper {
    int countByExample(ProductBrandExample example);

    List<ProductBrandCustom> selectByExample(ProductBrandExample example);

    ProductBrandCustom selectByPrimaryKey(Integer id);

	List<ProductBrand> getMchtProductBrands(HashMap<String, Object> paramMap);
}