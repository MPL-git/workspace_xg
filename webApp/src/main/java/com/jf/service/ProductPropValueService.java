package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ProductPropValueCustomMapper;
import com.jf.dao.ProductPropValueMapper;
import com.jf.entity.ProductPropValue;
import com.jf.entity.ProductPropValueCustom;
import com.jf.entity.ProductPropValueExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年5月26日 下午8:22:14 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class ProductPropValueService extends BaseService<ProductPropValue, ProductPropValueExample> {
	
	@Autowired
	private ProductPropValueMapper productPropValueMapper;
	
	@Autowired
	private ProductPropValueCustomMapper productPropValueCustomMapper;
	
	@Autowired
	public void setProductPropValueMapper(ProductPropValueMapper productPropValueMapper) {
		this.setDao(productPropValueMapper);
		this.productPropValueMapper = productPropValueMapper;
	}

	public List<ProductPropValueCustom> getProductPropdesc(Map<String, Object> propValIdsMap) {
		
		return productPropValueCustomMapper.getProductPropdesc(propValIdsMap);
	}

	public List<ProductPropValue> getProductPropValueModelByIds(Map<String, Object> map) {
		
		return productPropValueCustomMapper.getProductPropValueModelByIds(map);
	}
	
}
