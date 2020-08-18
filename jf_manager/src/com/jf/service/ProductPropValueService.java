package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ProductPropValueCustomMapper;
import com.jf.dao.ProductPropValueMapper;
import com.jf.entity.ProductPropValue;
import com.jf.entity.ProductPropValueCustom;
import com.jf.entity.ProductPropValueCustomExample;
import com.jf.entity.ProductPropValueExample;

@Service
@Transactional
public class ProductPropValueService extends BaseService<ProductPropValue, ProductPropValueExample> {
	@Autowired
	private ProductPropValueMapper productPropValueMapper;
	@Autowired
	private ProductPropValueCustomMapper productpropvaluecustommapper;

	@Autowired
	public void setProductPropValueMapper(ProductPropValueMapper productPropValueMapper) {
		super.setDao(productPropValueMapper);
		this.productPropValueMapper = productPropValueMapper;
	}
	
	public List<ProductPropValueCustom> selectByCustomExample(ProductPropValueCustomExample example){
		return productpropvaluecustommapper.selectByExample(example);
	}
	   
   public Integer countByCustomExample(ProductPropValueCustomExample example){
	   return productpropvaluecustommapper.countByCustomExample(example);
   }
   
	
}
