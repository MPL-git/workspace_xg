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

@Service
@Transactional
public class ProductPropValueService extends BaseService<ProductPropValue, ProductPropValueExample> {
	@Autowired
	private ProductPropValueMapper productPropValueMapper;
	
	@Autowired
	private ProductPropValueCustomMapper productPropValueCustomMapper;

	@Autowired
	public void setProductPropValueMapper(ProductPropValueMapper productPropValueMapper) {
		super.setDao(productPropValueMapper);
		this.productPropValueMapper = productPropValueMapper;
	}
	

    public List<ProductPropValueCustom> selectProductPropValueCustomByExample(ProductPropValueExample example){
    	return productPropValueCustomMapper.selectByExample(example);
    }
}
