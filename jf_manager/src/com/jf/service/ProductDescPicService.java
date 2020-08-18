package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ProductDescPicMapper;
import com.jf.entity.ProductDescPic;
import com.jf.entity.ProductDescPicExample;

@Service
@Transactional
public class ProductDescPicService extends BaseService<ProductDescPic, ProductDescPicExample> {
	@Autowired
	private ProductDescPicMapper productDescPicMapper;

	@Autowired
	public void setProductDescPicMapper(ProductDescPicMapper productDescPicMapper) {
		super.setDao(productDescPicMapper);
		this.productDescPicMapper = productDescPicMapper;
	}
}
