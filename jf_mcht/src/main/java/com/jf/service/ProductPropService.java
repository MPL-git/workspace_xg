package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ProductPropMapper;
import com.jf.entity.ProductProp;
import com.jf.entity.ProductPropExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
