package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ProductDescPicMapper;
import com.jf.entity.ProductDescPic;
import com.jf.entity.ProductDescPicExample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月28日 上午11:11:23 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class ProductDescPicService extends BaseService<ProductDescPic, ProductDescPicExample> {
	
	@Resource
	private ProductDescPicMapper productDescPicMapper;
	
	@Resource
	public void setProductDescPicMapper(ProductDescPicMapper productDescPicMapper) {
		this.setDao(productDescPicMapper);
		this.productDescPicMapper = productDescPicMapper;
	}
	
}
