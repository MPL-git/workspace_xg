package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ProductPropValueCustom;
import com.jf.entity.ProductPropValueCustomExample;
@Repository
public interface ProductPropValueCustomMapper{
	public List<ProductPropValueCustom> selectByExample (ProductPropValueCustomExample productpropvaluevustomexample);
	int countByCustomExample(ProductPropValueCustomExample example);
	
}