package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jf.dao.ProductVideoMapper;
import com.jf.entity.ProductVideo;
import com.jf.entity.ProductVideoExample;

@Service
@Transactional
public class ProductVideoService extends BaseService<ProductVideo, ProductVideoExample>{
	@Autowired
	private ProductVideoMapper productVideoMapper;
	
	@Autowired
	public void setActivityMapper(ProductVideoMapper productVideoMapper) {
		super.setDao(productVideoMapper);
		this.productVideoMapper = productVideoMapper;
	}
}
