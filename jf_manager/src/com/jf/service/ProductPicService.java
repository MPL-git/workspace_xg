package com.jf.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ProductPicCustomMapper;
import com.jf.dao.ProductPicMapper;
import com.jf.entity.ProductPic;
import com.jf.entity.ProductPicExample;

@Service
@Transactional
public class ProductPicService extends BaseService<ProductPic, ProductPicExample> {
	@Autowired
	private ProductPicMapper productPicMapper;
	@Autowired
	private ProductPicCustomMapper productPicCustomMapper;

	@Autowired
	public void setProductPicMapper(ProductPicMapper productPicMapper) {
		super.setDao(productPicMapper);
		this.productPicMapper = productPicMapper;
	}
	
	public List<String> getPics(HashMap<String, Object> paramMap){
		return productPicCustomMapper.getPics(paramMap);
	}

	public List<String> getPicsByProductIds(List<Integer> productIds) {
		return productPicCustomMapper.getPicsByProductIds(productIds);
	}
}
