package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ProductDescPicMapper;
import com.jf.entity.ProductDescPic;
import com.jf.entity.ProductDescPicExample;
	
/**
 * @author  chenwf: 
 * @date 创建时间：2017年8月1日 上午9:51:22 
 * @version 1.0 
 * @parameter  
 * @return  
*/

@Service
@Transactional
public class ProductDescPicService extends BaseService<ProductDescPic, ProductDescPicExample> {
	@Autowired
	private ProductDescPicMapper productDescPicMapper;

	@Autowired
	public void setProductDescPicMapper(ProductDescPicMapper productDescPicMapper) {
		this.setDao(productDescPicMapper);
		this.productDescPicMapper = productDescPicMapper;
	}
	
	
}
