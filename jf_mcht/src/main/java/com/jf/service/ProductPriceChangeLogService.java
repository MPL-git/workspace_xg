package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ProductPriceChangeLogMapper;
import com.jf.entity.ProductPriceChangeLog;
import com.jf.entity.ProductPriceChangeLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ProductPriceChangeLogService extends BaseService<ProductPriceChangeLog, ProductPriceChangeLogExample> {
	
	@Autowired
	private ProductPriceChangeLogMapper productPriceChangeLogMapper;
	
	@Autowired
	public void setProductPriceChangeLogMapper(ProductPriceChangeLogMapper productPriceChangeLogMapper) {
		this.setDao(productPriceChangeLogMapper);
		this.productPriceChangeLogMapper = productPriceChangeLogMapper;
	}
}
