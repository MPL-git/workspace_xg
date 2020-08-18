package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ProductBrandMapper;
import com.jf.entity.ProductBrand;
import com.jf.entity.ProductBrandExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年9月1日 上午12:41:35 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class ProductBrandService extends BaseService<ProductBrand, ProductBrandExample> {
	
	@Autowired
	private ProductBrandMapper productBrandMapper;
	
	@Autowired
	public void setProductBrandMapper(ProductBrandMapper productBrandMapper) {
		this.setDao(productBrandMapper);
		this.productBrandMapper = productBrandMapper;
	}
}
