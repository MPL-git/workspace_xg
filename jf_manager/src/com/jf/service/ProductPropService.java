package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ProductPropMapper;
import com.jf.entity.ProductProp;
import com.jf.entity.ProductPropExample;

@Service
@Transactional
public class ProductPropService extends BaseService<ProductProp, ProductPropExample> {
	@Autowired
	private ProductPropMapper productPropMapper;

	@Autowired
	public void setProductPropMapper(ProductPropMapper productPropMapper) {
		super.setDao(productPropMapper);
		this.productPropMapper = productPropMapper;
	}
}
