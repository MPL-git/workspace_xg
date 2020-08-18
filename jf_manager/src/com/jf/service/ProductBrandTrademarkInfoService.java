package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ProductBrandTrademarkInfoMapper;
import com.jf.entity.ProductBrandTrademarkInfo;
import com.jf.entity.ProductBrandTrademarkInfoExample;

@Service
@Transactional
public class ProductBrandTrademarkInfoService extends BaseService<ProductBrandTrademarkInfo, ProductBrandTrademarkInfoExample> {

	@Autowired
	private ProductBrandTrademarkInfoMapper productBrandTrademarkInfoMapper;
	
	@Autowired
	public void setProductBrandTrademarkInfoMapper(ProductBrandTrademarkInfoMapper productBrandTrademarkInfoMapper) {
		super.setDao(productBrandTrademarkInfoMapper);
		this.productBrandTrademarkInfoMapper = productBrandTrademarkInfoMapper;
	}
	
}
