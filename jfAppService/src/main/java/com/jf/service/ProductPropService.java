package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ProductPropMapper;
import com.jf.entity.ProductProp;
import com.jf.entity.ProductPropExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年5月26日 下午8:20:38 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class ProductPropService extends BaseService<ProductProp, ProductPropExample> {
	
	@Autowired
	private ProductPropMapper productPropMapper;
	
	@Autowired
	public void setProductPropMapper(ProductPropMapper productPropMapper) {
		this.setDao(productPropMapper);
		this.productPropMapper = productPropMapper;
	}
	
}
